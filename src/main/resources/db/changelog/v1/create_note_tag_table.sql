DROP TABLE IF EXISTS note_tag;

CREATE TABLE note_tag(
    note_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    FOREIGN KEY (note_id) REFERENCES note(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);