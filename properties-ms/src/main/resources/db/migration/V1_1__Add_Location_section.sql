--CREATE COUNTRIES TABLE
create sequence properties_ms.countries_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.countries (
    id bigserial NOT NULL,
    name varchar(255) NOT NULL UNIQUE,
    currency varchar(255) NULL,
    abbreviation varchar(255) NULL,
    approved boolean DEFAULT FALSE,
    creation_date TIMESTAMP NULL,
    last_updated_date TIMESTAMP NULL,
    CONSTRAINT country_pkey PRIMARY KEY (id)
);

--CREATE CITIES TABLE
create sequence properties_ms.cities_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.cities (
	id bigserial NOT NULL,
	country_id bigint NOT NULL,
	name varchar(100),
	approved boolean DEFAULT FALSE,
	is_capitol boolean DEFAULT FALSE,
    creation_date TIMESTAMP NULL,
    last_updated_date TIMESTAMP NULL,
    CONSTRAINT city_pkey PRIMARY KEY (id),
    CONSTRAINT city_country_id_fk
        FOREIGN KEY (country_id) REFERENCES countries(id)
);

--CREATE AREAS TABLE
create sequence properties_ms.areas_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.areas (
	id bigserial NOT NULL,
	city_id bigint NOT NULL,
	nr_of_properties bigint NULL,
	name varchar(255),
	description text,
	CONSTRAINT area_pkey PRIMARY KEY (id),
	CONSTRAINT area_city_id_fk
        FOREIGN KEY (city_id) REFERENCES cities(id)
);