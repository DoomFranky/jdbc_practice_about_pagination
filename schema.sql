CREATE TABLE Product (
    id INT PRIMARY KEY,
    "name" VARCHAR,
    price INT,
    creation_product TIMESTAP
)

CREATE TABLE Product_category (
    id INT PRIMARY KEY,
    "name" VARCHAR,
    product_id INT,
    CONSTAINT fk_product_product_category FOREIGN KEY (id_product) REFERENCES Product(id)
)