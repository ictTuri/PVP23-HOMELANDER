package com.codeonmars.filesms.repository;

import com.codeonmars.filesms.model.file.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<UploadedFile, Long> {
    Optional<UploadedFile> findByUuid(String uuid);

    List<UploadedFile> findAllByUuidIn(List<String> uuids);

    @Query(nativeQuery = true, value = """
            SELECT * FROM files_ms.files f 
            WHERE f."uuid" IN (:uuids) limit 1""")
    UploadedFile findOneByUuid(@Param("uuids") List<String> uuids);
}
