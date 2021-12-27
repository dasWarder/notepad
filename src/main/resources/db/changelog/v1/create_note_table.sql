DROP TABLE IF EXISTS note;
DROP SEQUENCE IF EXISTS note_seq;

CREATE SEQUENCE note_seq START WITH 1;

CREATE TABLE note(
    id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('note_seq'),
    description VARCHAR NOT NULL,
    added_at TIMESTAMP NOT NULL,
    actual_for TIMESTAMP NOT NULL,
    reminder BOOLEAN DEFAULT FALSE,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);