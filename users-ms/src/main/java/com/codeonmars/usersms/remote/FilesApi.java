package com.codeonmars.usersms.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "files-ms")
public interface FilesApi {

    @PutMapping("/files/internal/unlink-file")
    void unlinkFile(@RequestParam(name = "fileUUID") String fileUUID);

    @PutMapping("/files/internal/link-file")
    void linkFile(@RequestParam(name = "fileUUID") String fileUUID);

    @PutMapping("/files/internal/unlink-and-link-file")
    void unlinkAndLinkFile(@RequestParam(name = "fileUUIDUnlink") String fileUUIDUnlink,
                           @RequestParam(name = "fileUUIDLink") String fileUUIDLink);
}
