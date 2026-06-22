CREATE TABLE IF NOT EXISTS sesion (
    id BIGINT NOT NULL AUTO_INCREMENT,

    id_ayudante BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    id_estudio BIGINT NOT NULL,
    id_pago BIGINT NOT NULL,
    id_tatuador BIGINT NOT NULL,
    id_tatuaje BIGINT NOT NULL,

    fecha_sesion DATE NOT NULL,
    hora_sesion TIME NOT NULL,

    duracion_horas INT NOT NULL,

    PRIMARY KEY (id)
);