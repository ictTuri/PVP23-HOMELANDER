package com.codeonmars.filesms.controller;

import com.codeonmars.commonsms.dto.FileDto;
import com.codeonmars.filesms.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/internal")
public class InternalController {

    private final FileService fileService;

    @PutMapping("/unlink-file")
    public void unlinkFile(@RequestParam(name = "fileUUID") String fileUUID) {
        fileService.unlinkFile(fileUUID);
    }

    @PutMapping("/link-file")
    public void linkFile(@RequestParam(name = "fileUUID") String fileUUID) {
        fileService.linkFile(fileUUID);
    }

    @PutMapping("/link-files")
    public void linkFile(@RequestParam(name = "fileUUIDs") Set<String> fileUUIDs) {
        fileService.linkFiles(fileUUIDs);
    }

    @PutMapping("/unlink-and-link-file")
    public void unlinkAndLinkFile(@RequestParam(name = "fileUUIDUnlink") String fileUUIDUnlink,
                                  @RequestParam(name = "fileUUIDLink") String fileUUIDLink) {
        fileService.unlinkAndLinkFile(fileUUIDUnlink, fileUUIDLink);
    }
    @PostMapping("/user/profile-image")
    @ResponseStatus(HttpStatus.OK)
    public FileDto getProfilePicture(@RequestParam(name = "uuid") String uuid) {
        return fileService.getProfilePicture(uuid);
    }
    @PostMapping(path = "/property/get-image")
    @ResponseStatus(HttpStatus.OK)
    public FileDto getPropertyImage(@RequestBody String uuid) {
        return fileService.getPropertyImage(uuid);
    }

    @PostMapping(path = "/property/get-images")
    @ResponseStatus(HttpStatus.OK)
    public List<FileDto> getPropertyImages(@RequestBody List<String> uuids) {
        return fileService.getPropertyImages(uuids);
    }
}
