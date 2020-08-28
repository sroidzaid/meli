
DROP TABLE IF EXISTS log;
CREATE TABLE log(id serial PRIMARY KEY, country VARCHAR(255), distance NUMERIC(10,2), invocations NUMERIC);