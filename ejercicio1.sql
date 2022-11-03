CREATE DATABASE ejercicio1;

USE ejercicio1;

CREATE TABLE users(
id INT AUTO_INCREMENT PRIMARY KEY,
names VARCHAR(30) NOT NULL,
last_names VARCHAR(30) NOT NULL,
age INT NOT NULL,
country VARCHAR(20) NOT NULL,
sex CHAR(1) NOT NULL,
rol VARCHAR(20) NOT NULL,
username VARCHAR(30) NOT NULL,
password VARCHAR(150) NOT NULL
);

/*Cada vez que se crea un indice se utiliza memoria*/
/*Cada gestor de base de datos tienen una forma diferente de colocar los indices*/
/*Los indices son automaticos, sirve para ubicar rapidamente una informacion por ejemplo si se busca por nombre, buscara automaticamente la letra que comienza en vez de hacerlo desde el inicio*/
CREATE INDEX idx_nameUser ON users(names);
CREATE INDEX idx_username ON users(username);
SHOW INDEX FROM users;

INSERT INTO users(names, last_names, age, country, sex, rol, username, password) VALUES('Joaquin Sebastian', 'Farro Quiroz', 21, 'Perú', 'M', 'ROLE_ADMIN', 'admin', 'admin');

CREATE TABLE products(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL,
id_category INT NOT NULL,
price DECIMAL(12,2) NOT NULL,
amount INT NOT NULL,
INDEX(name),
FOREIGN KEY(id_category) REFERENCES product_category(id)
);

INSERT INTO products(name, id_category, price, amount) VALUES('Monitor Asus V98GA 4K 144hz', 1, 1560.50, 1);

CREATE TABLE product_category(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(30) NOT NULL,
INDEX idx_nameProCategory(name)
);

INSERT INTO product_category(name) VALUES('monitor');

INSERT INTO products(name, price, amount) VALUES('');