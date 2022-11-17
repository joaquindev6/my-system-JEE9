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

CREATE TABLE shopping_car(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
id_user INT NOT NULL,
id_item_car INT NOT NULL
);
ALTER TABLE shopping_car ADD FOREIGN KEY(id_user) REFERENCES users(id);
ALTER TABLE shopping_car ADD FOREIGN KEY(id_item_car) REFERENCES item_shopping_car(id) ON DELETE CASCADE;

SELECT * FROM shopping_car;

INSERT INTO shopping_car(id_user, id_item_car) VALUE(1, 1);
INSERT INTO shopping_car(id_user, id_item_car) VALUE(1, 3);
INSERT INTO shopping_car(id_user, id_item_car) VALUE(1, 1);

CREATE TABLE item_shopping_car(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
id_product INT NOT NULL,
amount INT NOT NULL,
FOREIGN KEY(id_product) REFERENCES products(id) ON DELETE CASCADE
);

SELECT * FROM item_shopping_car;

INSERT INTO item_shopping_car(id_product, amount) VALUES(1, 8);
INSERT INTO item_shopping_car(id_product, amount) VALUES(3, 8);

CREATE TABLE tbl_ventas_cab(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
id_usuario INT NOT NULL,
fecha DATETIME NOT NULL,
prectotal DECIMAL(12,2) NOT NULL,
FOREIGN KEY(id_usuario) REFERENCES users(id)
);

CREATE TABLE tbl_ventas_det(
id_venta_cab INT NOT NULL,
id_producto INT NOT NULL,
cantidad INT NOT NULL,
precio DECIMAL(12,2) NOT NULL,
FOREIGN KEY(id_venta_cab) REFERENCES tbl_ventas_cab(id),
FOREIGN KEY(id_producto) REFERENCES products(id)
);



/**** INNER JOIN para item_shopping_car *****/
SELECT i.*, p.name, p.price, p.id_category, c.name FROM item_shopping_car AS i
INNER JOIN products AS p ON i.id_product = p.id
INNER JOIN product_category AS c ON p.id_category = c.id;

/**** INNER JOIN para shopping_car *****/
SELECT s.*, u.names, u.last_names, u.username, u.rol FROM shopping_car AS s INNER JOIN users AS u ON s.id_user = u.id INNER JOIN item_shopping_car AS i ON s.id_item_car = i.id;

SELECT s.*, u.names, u.last_names, u.username, u.rol FROM shopping_car AS s 
                INNER JOIN users AS u ON s.id_user = u.id
                INNER JOIN item_shopping_car AS i ON s.id_item_car = i.id WHERE id_user = 1;
                
SELECT i.*, p.name, p.price, p.id_category, c.name FROM item_shopping_car AS i
INNER JOIN products AS p ON i.id_product = p.id
INNER JOIN product_category AS c ON p.id_category = c.id WHERE i.id = 3;

SELECT s.* FROM shopping_car AS s 
INNER JOIN users AS u ON s.id_user = u.id 
INNER JOIN item_shopping_car AS i ON s.id_item_car = i.id 
INNER JOIN products AS p ON i.id_product = p.id WHERE s.id_user = 1 and i.id_product = 1;
