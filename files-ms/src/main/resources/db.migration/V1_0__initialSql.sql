--CREATE FILES TABLE
create TABLE files_ms.files (
    id bigserial NOT NULL,
    uuid varchar(256) NOT NULL,
    filename varchar(256) NOT NULL,
    filesize bigint NOT NULL,
    upload_timestamp timestamp NOT NULL,
    associated boolean NOT NULL default false,
    file_content_blob bytea NOT NULL,
    CONSTRAINT files_pkey PRIMARY KEY (id),
    CONSTRAINT files_uuid_key unique (uuid)
);

SELECT setval(pg_get_serial_sequence('files', 'id'), 1000);