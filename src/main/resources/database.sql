-- Creating tables.
CREATE TABLE course (
  id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR2(255)   NOT NULL
);

CREATE TABLE student (
  id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  surname    VARCHAR2(255)   NOT NULL,
  firstname  VARCHAR2(255)   NOT NULL,
  secondname VARCHAR2(255),
  sexid      INT             NOT NULL,
  course_id  INT             NOT NULL,
  FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE lecture (
  id        INT AUTO_INCREMENT PRIMARY KEY,
  begindate TIMESTAMP     NOT NULL,
  location  TEXT          NOT NULL,
  name      VARCHAR2(255) NOT NULL
);

CREATE TABLE student_lecture (
  student_id INT NOT NULL,
  lecure_id  INT NOT NULL,
  FOREIGN KEY (lecure_id) REFERENCES lecture (id),
  FOREIGN KEY (student_id) REFERENCES student (id)
);

-- inserting some junk data
INSERT INTO course (id, name) VALUES (1, 'Computer Science');
INSERT INTO course (id, name) VALUES (2, 'Software Engineering');

INSERT INTO student (surname, firstname, secondname, sexid, course_id) VALUES ('Smith', 'John', NULL, 1, 1);
INSERT INTO student (surname, firstname, secondname, sexid, course_id) VALUES ('Doe', 'Jane', NULL, 2, 2);

INSERT INTO lecture (begindate, location, name) VALUES (SYSTIMESTAMP, 'Tharsis, Mars', 'Algorithms. Lecture 1');
INSERT INTO lecture (begindate, location, name) VALUES (SYSTIMESTAMP , 'Kilimangaroo, Earth', 'Algorithms. Lecture 2');

INSERT INTO student_lecture (student_id, lecure_id) VALUES (1, 1);
INSERT INTO student_lecture (student_id, lecure_id) VALUES (2, 1);

