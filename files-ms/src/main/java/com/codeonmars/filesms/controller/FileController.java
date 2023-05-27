package com.codeonmars.filesms.controller;

import com.codeonmars.filesms.model.file.dto.FileUploadResponse;
import com.codeonmars.filesms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files/user")
public class FileController {

    public static final String NO_IMAGE_RECEIVED = "No image received";
    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<FileUploadResponse> uploadProfilePicture(@RequestParam("pt_file") MultipartFile inputFile,
                                                                   @RequestParam(name = "tag", required = false) String tag) {
        if (inputFile == null || inputFile.isEmpty()) {
            throw new IllegalArgumentException(NO_IMAGE_RECEIVED);
        }
        return new ResponseEntity<>(fileService.saveProfilePicture(inputFile, tag), HttpStatus.CREATED);
    }

}
