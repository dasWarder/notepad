DELETE FROM note;
ALTER SEQUENCE note_seq RESTART WITH 1;

INSERT INTO note VALUES
    (1, 'widow debt', '2021-01-01 12:00:00', '2021-01-02 00:00:00', false),
    (2, 'people town bed', '2021-02-02 12:00:00', '2021-02-05 00:00:00', false),
    (3, 'some text', '2021-03-03 12:00:00', '2021-03-04 00:00:00', true);

SELECT setval('note_seq', max(id)) FROM note;