DROP TABLE IF EXISTS anime;

CREATE TABLE anime (
  id int unsigned AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  published_year VARCHAR(4) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO anime (title, evaluated_value) VALUES ('地獄楽','評価4');
