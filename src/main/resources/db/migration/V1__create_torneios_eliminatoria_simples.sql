CREATE TABLE torneios_eliminatoria_simples (
    id BIGINT PRIMARY KEY,
    CONSTRAINT fk_eliminatoria_base
        FOREIGN KEY (id) REFERENCES torneio_base(id)
);