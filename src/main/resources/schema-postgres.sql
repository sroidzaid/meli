
DROP TABLE IF EXISTS log;
CREATE TABLE log(id serial PRIMARY KEY, country VARCHAR(255), distance NUMERIC, invocations NUMERIC);
INSERT INTO log(country, distance, invocations) VALUES('Argentina', 1500, 20);