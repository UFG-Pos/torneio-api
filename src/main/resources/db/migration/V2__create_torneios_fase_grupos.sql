CREATE TABLE torneios_fase_grupos (
    id BIGINT PRIMARY KEY,
    numero_grupos INT NOT NULL,
    CONSTRAINT fk_fase_grupos_base
        FOREIGN KEY (id) REFERENCES torneio_base(id)
);