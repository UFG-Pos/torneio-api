DROP TABLE IF EXISTS equipes;

CREATE TABLE equipes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    torneio_id BIGSERIAL NOT NULL,
    CONSTRAINT fk_equipes_torneio
        FOREIGN KEY (torneio_id)
        REFERENCES torneios_eliminatoria_simples(id)
        ON DELETE CASCADE
);