CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    rut VARCHAR(13) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_cliente_rut (rut)
);