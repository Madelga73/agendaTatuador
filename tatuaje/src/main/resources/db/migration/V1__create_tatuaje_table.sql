CREATE TABLE IF NOT EXISTS tatuaje (
    id BIGINT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(500),
    zona VARCHAR(50),
    estilo VARCHAR(50),
    PRIMARY KEY (id)
);