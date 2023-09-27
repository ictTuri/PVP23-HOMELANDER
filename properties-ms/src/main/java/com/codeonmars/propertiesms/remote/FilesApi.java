package com.codeonmars.propertiesms.remote;

import com.codeonmars.commonsms.dto.FileDto;
import com.codeonmars.commonsms.security.UserContextRetriever;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Set;

@FeignClient(value = "files-ms")
public interface FilesApi {
    @PutMapping("/files/internal/unlink-file")
    void unlinkFile(@RequestParam(name = "fileUUID") String fileUUID);

    @PutMapping("/files/internal/link-files")
    void linkFiles(@RequestParam(name = "fileUUIDs") Set<String> fileUUIDs);

    @PostMapping(path = "/files/internal/property/get-image")
    FileDto getPropertyImage(@RequestBody String uuid);

    @PostMapping(path = "/files/internal/property/get-images")
    List<FileDto> getPropertyImages(@RequestBody List<String> uuids);
}
