DO
$$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_database
      WHERE datname = 'dbrpe'
   ) THEN
      PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE dbrpe');
   END IF;
END
$$;

\c dbrpe;

DROP TABLE IF EXISTS client;

CREATE TABLE client (
    client_id BIGSERIAL PRIMARY KEY,
    client_name VARCHAR(255) NOT NULL,
    client_cpf VARCHAR(14) NOT NULL,
    client_status VARCHAR(10) CHECK (client_status IN ('ATIVO', 'BLOQUEADO', 'CANCELADO')) NOT NULL,
    client_date_of_birth DATE NOT NULL,
    client_date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_date_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);