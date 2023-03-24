-- create schema if not exists testschema;

CREATE TABLE IF NOT EXISTS public.post (
    id uuid NOT NULL,
    external_id VARCHAR(45) NULL,
    content TEXT NULL,
PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS public.comment (
    id uuid NOT NULL,
    external_id VARCHAR(45) NULL,
    content TEXT NULL,
    post_id uuid NOT NULL,
    CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES post(id),
PRIMARY KEY (id));