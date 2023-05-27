package com.codeonmars.filesms.service;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.filesms.model.file.UploadedFile;
import com.codeonmars.filesms.model.file.dto.FileUploadResponse;
import com.codeonmars.filesms.repository.FileRepository;
import com.github.dozermapper.core.Mapper;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FileService {

    public static final String IMAGE = "image";
    public static final String CAN_NOT_ACCEPT_THIS_TYPE_OF_FILE = "Can not accept this type of file";
    public static final String ERROR_DURING_FILE_DETECTION = "Error during file detection!";
    public static final String TEXT = "text";

    private final Tika tika = new Tika();

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private Mapper dozer;

    public FileUploadResponse saveProfilePicture(MultipartFile inputFile, String tag) {
        var filename = Optional.ofNullable(inputFile.getOriginalFilename())
                .filter(StringUtils::isNotBlank)
                .orElse("profile_pic.jpg");
        try {
            Blob blob = entityManager.unwrap(Session.class).getLobHelper().createBlob(getCompressedFile(inputFile), inputFile.getSize());
            var file = UploadedFile.builder()
                    .uuid(UUID.randomUUID().toString())
                    .associated(Boolean.FALSE)
                    .fileName(filename)
                    .fileSize(inputFile.getSize())
                    .fileContent(blob)
                    .uploadedBy(getLoggedUserEmail())
                    .uploadTimestamp(Instant.now())
                    .tag(tag).build();
            return dozer.map(fileRepository.save(file), FileUploadResponse.class);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }


    /* SUPPORTING METHODS */

    private InputStream getCompressedFile(MultipartFile inputFile) throws IOException {
        try {
            var contentType = Optional.ofNullable(tika.detect(inputFile.getInputStream())).orElse(TEXT);
            if (!contentType.startsWith(IMAGE)) {
                throw new IllegalStateException(CAN_NOT_ACCEPT_THIS_TYPE_OF_FILE);
            }
            return resizeProfilePic(inputFile, contentType.split("/")[1]);

        } catch (IOException e) {
            throw new IllegalStateException(ERROR_DURING_FILE_DETECTION);
        }
    }

    private InputStream resizeProfilePic(MultipartFile inputFile, String originalType) throws IOException {
        var needToScale = false;
        var originBufferedImage = ImageIO.read(inputFile.getInputStream());
        var height = originBufferedImage.getHeight();
        var width = originBufferedImage.getWidth();

        if (height > 1080) {
            needToScale = true;
            width = (1080 * width) / height;
            height = 1080;
        }

        if (!needToScale) {
            return inputFile.getInputStream();
        }

        var resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        var graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originBufferedImage, 0, 0, width, height, null);
        graphics2D.dispose();

        var byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, originalType, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private String getLoggedUserEmail() {
        /* TODO: fix the throw to a caught exception*/
        var user = UserContextHolder.getContext().orElseThrow();
        return user.getEmail();
    }
}
