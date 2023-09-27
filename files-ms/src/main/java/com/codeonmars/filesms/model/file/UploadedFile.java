package com.codeonmars.filesms.model.file;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files", schema = "files_ms")
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
    @Column(name = "file_content_byte")
    private byte[] fileContent;

    public void setAssociatedTrue() {
        this.associated = true;
    }
}
