CREATE TABLE IF NOT EXISTS pago (
    id BIGINT NOT NULL AUTO_INCREMENT,
    num_boleta VARCHAR(50) NOT NULL,
    monto_total INTEGER NOT NULL,
    monto_insumos INTEGER NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_pago_num_boleta (num_boleta)
);