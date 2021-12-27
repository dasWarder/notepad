DELETE FROM customer;
ALTER SEQUENCE customer_seq RESTART WITH 1;

INSERT INTO customer VALUES

    (1, 'alex@gmail.com', 'alex', '$2a$12$dM5a1EmbqDZZzMjdrys.lOZCfnOAac8JqmiCb1tOcnh8iG6KHfWjK', true, '2021-12-12 12:00:00'),
    (2, 'peter@gmail.com', 'peter', '$2a$12$dM5a1EmbqDZZzMjdrys.lOZCfnOAac8JqmiCb1tOcnh8iG6KHfWjK', true, '2021-12-13 12:00:00');

SELECT setval('customer_seq', max(id)) FROM customer;