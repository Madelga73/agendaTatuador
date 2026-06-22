CREATE TABLE IF NOT EXISTS resultado (
    id BIGINT NOT NULL AUTO_INCREMENT,

    id_cliente BIGINT NOT NULL,
    id_tatuaje BIGINT NOT NULL,

    valoracion INT NOT NULL,

    comentario VARCHAR(500),

    PRIMARY KEY (id)
);