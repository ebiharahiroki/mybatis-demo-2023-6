DROP TABLE IF EXISTS twitter;

CREATE TABLE twitter (
  id int unsigned AUTO_INCREMENT,
  Likes VARCHAR(100) NOT NULL,
  Followers VARCHAR(3) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO twitter (id, Likes, Followers) VALUES (1, "15", "112");
