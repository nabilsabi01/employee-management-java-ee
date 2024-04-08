CREATE DATABASE employee_db;
USE employee_db;
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    salary DOUBLE,
    department VARCHAR(50)
);