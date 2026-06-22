CREATE TABLE IF NOT EXISTS inventario (
    id BIGINT NOT NULL AUTO_INCREMENT,

    id_tatuador BIGINT NOT NULL,
    id_vendedor BIGINT NOT NULL,

    insumo VARCHAR(100) NOT NULL,

    cantidad INT NOT NULL,

    precio_unitario INT NOT NULL,

    PRIMARY KEY (id)
);