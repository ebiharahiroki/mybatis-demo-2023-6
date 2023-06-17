DROP TABLE IF EXISTS twitter;

CREATE TABLE twitter (
  id int unsigned AUTO_INCREMENT,
  likes Integer NOT NULL,
  Followers VARCHAR(3) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO twitter (id, likes, Followers) VALUES (1, 10, "112");
INSERT INTO twitter (id, likes, Followers) VALUES (2, 15, "112");

