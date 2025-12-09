CREATE TABLE "Product" (
    id INT PRIMARY KEY,
    "name" VARCHAR,
    price NUMERIC(10,2),
    creation_product TIMESTAMP
);

CREATE TABLE "Product_category" (
    id INT PRIMARY KEY,
    "name" VARCHAR,
    product_id INT,
    CONSTRAINT fk_product_product_category FOREIGN KEY (product_id) REFERENCES "Product"(id)
);