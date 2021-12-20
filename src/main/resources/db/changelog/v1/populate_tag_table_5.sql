DELETE FROM tag;
ALTER SEQUENCE tag_seq RESTART WITH 1;

INSERT INTO Tag VALUES
    (1, 'thoughts'),
    (2, 'job'),
    (3, 'idea'),
    (4, 'world'),
    (5, 'family'),
    (6, 'plans');

SELECT setval('tag_seq', max(id)) FROM tag;