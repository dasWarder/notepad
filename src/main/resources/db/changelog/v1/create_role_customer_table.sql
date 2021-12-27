DROP TABLE IF EXISTS role_customer;

CREATE TABLE role_customer (
    role_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY(role_id) REFERENCES role(id),
    FOREIGN KEY(customer_id) REFERENCES customer(id)
);