package com.codeonmars.filesms.controller;

import com.codeonmars.commonsms.aspects.CanAddProperties;
import com.codeonmars.filesms.model.file.dto.FileUploadResponse;
import com.codeonmars.filesms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files/user")
public class FileController {


    @Autowired
    private FileService fileService;

    @PostMapping(path = "/profile", consumes = "multipart/form-data")
    public ResponseEntity<FileUploadResponse> uploadProfilePicture(@RequestParam(name = "pt_file") MultipartFile inputFile,
                                                                   @RequestParam(name = "tag", required = false) String tag) {
        FileService.verifyFileIsPresent(inputFile);
        return new ResponseEntity<>(fileService.saveProfilePicture(inputFile, tag), HttpStatus.CREATED);
    }

    @CanAddProperties
    @PostMapping(path = "/property", consumes = "multipart/form-data")
    public ResponseEntity<FileUploadResponse> uploadPropertyPictures(@RequestParam(name = "pt_file") MultipartFile inputFile,
                                                                     @RequestParam(name = "tag", required = false) String tag) {
        FileService.verifyFileIsPresent(inputFile);
        return new ResponseEntity<>(fileService.savePropertyFiles(inputFile, tag), HttpStatus.CREATED);
    }

}
