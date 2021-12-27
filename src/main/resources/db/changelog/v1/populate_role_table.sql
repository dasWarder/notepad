DELETE FROM role;
ALTER SEQUENCE role_seq RESTART WITH 100000;

INSERT INTO role VALUES
    (100000, 'USER'),
    (100001, 'ADMIN'),
    (100002, 'SUPER_ADMIN');

SELECT setval('role_seq', max(id)) FROM role;
