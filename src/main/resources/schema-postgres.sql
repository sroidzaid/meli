
DROP TABLE IF EXISTS log;
CREATE TABLE log(id serial PRIMARY KEY, country VARCHAR(255), distance NUMERIC(10,2), invocations NUMERIC);
INSERT INTO log(country, distance, invocations) VALUES('Argentina', 1500, 20);
INSERT INTO log(country, distance, invocations) VALUES('Brasil', 5000, 5);