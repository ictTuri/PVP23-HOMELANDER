package com.codeonmars.filesms.service;

import com.codeonmars.commonsms.dto.FileDto;
import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.filesms.model.file.UploadedFile;
import com.codeonmars.filesms.model.file.dto.FileUploadResponse;
import com.codeonmars.filesms.remote.UsersApi;
import com.codeonmars.filesms.repository.FileRepository;
import com.github.dozermapper.core.Mapper;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
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
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class FileService {
    public static final String IMAGE = "image";
    public static final String NO_IMAGE_RECEIVED = "No image received";
    public static final String CAN_NOT_ACCEPT_THIS_TYPE_OF_FILE = "Can not accept this type of file";
    public static final String ERROR_DURING_FILE_DETECTION = "Error during file detection!";
    public static final String TEXT = "text";

    private final Tika tika = new Tika();

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UsersApi usersApi;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private Mapper dozer;

    public FileUploadResponse saveProfilePicture(MultipartFile inputFile, String tag) {
        var uuid = uploadAndProcessFile(inputFile, Optional.ofNullable(tag).orElse("profile"), "profile_pic.jpg", false);
        usersApi.setProfileUUID(uuid.getUuid());
        return uuid;
    }

    public FileUploadResponse savePropertyFiles(MultipartFile inputFile, String tag) {
        var randomUuid = UUID.randomUUID();
        return uploadAndProcessFile(inputFile, Optional.ofNullable(tag).orElse("property"), "prop" + randomUuid +".jpg", true);
    }

    public FileDto getProfilePicture(String uuid) {
        var image = fileRepository.findByUuid(uuid).orElse(null);
        return this.mapImageToFileDto(image);
    }

    public List<FileDto> getPropertyImages(List<String> uuids) {
        var images = fileRepository.findAllByUuidIn(uuids);
        return images.stream().map(this::mapImageToFileDto).toList();
    }

    public FileDto getPropertyImage(String uuid) {
        var image = fileRepository.findByUuid(uuid).orElse(null);
        return this.mapImageToFileDto(image);
    }

    public void linkFile(String fileUUID) {
        var image = fileRepository.findByUuid(fileUUID);
        image.ifPresent(UploadedFile::setAssociatedTrue);
        image.ifPresent(fileRepository::save);
    }

    public void unlinkFile(String fileUUID) {
        var image = fileRepository.findByUuid(fileUUID);
        image.ifPresent(fileRepository::delete);
    }

    public void unlinkAndLinkFile(String fileUUIDUnlink, String fileUUIDLink) {
        this.unlinkFile(fileUUIDUnlink);
        this.linkFile(fileUUIDLink);
    }

    public void linkFiles(Set<String> fileUUIDs) {
        fileUUIDs.forEach(this::linkFile);
    }

    /* SUPPORTING METHODS */

    private FileUploadResponse uploadAndProcessFile(MultipartFile inputFile, String tag, String name, boolean isStatic) {
        var filename = Optional.ofNullable(inputFile.getOriginalFilename())
                .filter(StringUtils::isNotBlank)
                .orElse(name);
        try {
            var file = UploadedFile.builder()
                    .uuid(UUID.randomUUID().toString())
                    .associated(Boolean.FALSE)
                    .fileName(filename)
                    .fileSize(inputFile.getSize())
                    .fileContent(getCompressedFile(inputFile, isStatic).readAllBytes())
                    .uploadedBy(getLoggedUserEmail())
                    .uploadTimestamp(Instant.now())
                    .tag(tag).build();
            return dozer.map(fileRepository.save(file), FileUploadResponse.class);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static void verifyFileIsPresent(MultipartFile inputFile) {
        if (inputFile == null || inputFile.isEmpty()) {
            throw new IllegalArgumentException(NO_IMAGE_RECEIVED);
        }
    }

    private InputStream getCompressedFile(MultipartFile inputFile, boolean isStatic) throws IOException {
        try {
            var contentType = Optional.ofNullable(tika.detect(inputFile.getBytes())).orElse(TEXT);
            if (!contentType.startsWith(IMAGE)) {
                throw new IllegalStateException(CAN_NOT_ACCEPT_THIS_TYPE_OF_FILE);
            }
            return resizeProfilePic(inputFile, contentType.split("/")[1], isStatic);

        } catch (IOException e) {
            throw new IllegalStateException(ERROR_DURING_FILE_DETECTION);
        }
    }

    private InputStream resizeProfilePic(MultipartFile inputFile, String originalType, boolean isStatic) throws IOException {
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
        if (isStatic) {
            resizedImage = new BufferedImage(1080, 1080, BufferedImage.TYPE_INT_BGR);
        }
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

    private FileDto mapImageToFileDto(UploadedFile img) {
        if (img == null) {
            return new FileDto();
        }
        var toReturn = dozer.map(img, FileDto.class);
        toReturn.setFileContent(Base64.getEncoder().encodeToString(img.getFileContent()));
        return toReturn;
    }

}
