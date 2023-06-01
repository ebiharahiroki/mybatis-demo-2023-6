DROP TABLE IF EXISTS names;

CREATE TABLE customers (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age int NOT NULL,
  site VARCHAR(50) NOT NULL,
  staff VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO names (id, name) VALUES (1, "tanaka");
INSERT INTO names (id, name) VALUES (2, "suzuki");
INSERT INTO names (id, name) VALUES (3, "yamada");