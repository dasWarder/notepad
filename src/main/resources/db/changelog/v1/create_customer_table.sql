DROP TABLE IF EXISTS customer;
DROP SEQUENCE IF EXISTS customer_seq;

CREATE SEQUENCE customer_seq START WITH 1;

CREATE TABLE customer (
   id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('customer_seq'),
   email VARCHAR NOT NULL UNIQUE,
   login VARCHAR NOT NULL,
   password VARCHAR NOT NULL,
   enabled BOOLEAN DEFAULT true,
   registration_date TIMESTAMP NOT NULL
);