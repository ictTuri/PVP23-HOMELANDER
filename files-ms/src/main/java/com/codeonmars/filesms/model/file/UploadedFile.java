package com.codeonmars.filesms.model.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.sql.Blob;
import java.time.Instant;

@Data
@Entity
@Builder
@Table(name = "files", schema = "files_ms")
@SequenceGenerator(name = "t_files_id_seq", sequenceName = "files_ms.t_files_id_seq", allocationSize = 1)
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private String uuid;
    @Column(name = "filename")
    private String fileName;
    @Column(name = "tag")
    private String tag;
    @Column(name = "filesize")
    private Long fileSize;
    @Column(name = "upload_timestamp")
    private Instant uploadTimestamp;
    @Column(name = "uploaded_by")
    private String uploadedBy;
    @Column(name = "associated")
    private Boolean associated;
    @Lob
    @Column(name = "file_content_blob")
    private Blob fileContent;
}
