package com.codeonmars.filesms.repository;

import com.codeonmars.filesms.model.file.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<UploadedFile, Long> {
}
