--CREATE FILES TABLE
create sequence files_ms.files_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE files_ms.files (
    id bigserial NOT NULL,
    "uuid" varchar(256) NOT NULL,
    filename varchar(256) NOT NULL,
    tag varchar(256) NOT NULL,
    filesize bigint NOT NULL,
    uploaded_by varchar(256) NOT NULL,
    upload_timestamp timestamp NOT NULL,
    associated boolean NOT NULL default false,
    file_content_byte oid NOT NULL,
    CONSTRAINT files_pkey PRIMARY KEY (id),
    CONSTRAINT files_uuid_key unique (uuid)
);