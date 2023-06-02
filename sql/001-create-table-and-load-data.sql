DROP TABLE IF EXISTS names;

CREATE TABLE names (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age int NOT NULL,
  site VARCHAR(50) NOT NULL,
  staff VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO names (id, names) VALUES (1, "tanaka");
INSERT INTO names (id, names) VALUES (2, "suzuki");
INSERT INTO names (id, names) VALUES (3, "yamada");