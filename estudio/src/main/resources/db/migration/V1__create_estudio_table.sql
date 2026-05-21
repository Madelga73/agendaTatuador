CREATE TABLE IF NOT EXISTS estudio (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_estudio_nombre (nombre)
);