CREATE TABLE partidas (
    id BIGSERIAL PRIMARY KEY,
    horario TIMESTAMP NOT NULL,

    torneio_id BIGINT NOT NULL,
    equipe_a_id BIGINT NOT NULL,
    equipe_b_id BIGINT NOT NULL,

    placar_equipe_a INT,
    placar_equipe_b INT,

    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_partidas_torneio
        FOREIGN KEY (torneio_id)
        REFERENCES torneios_eliminatoria_simples(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_partidas_equipe_a
        FOREIGN KEY (equipe_a_id)
        REFERENCES equipes(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_partidas_equipe_b
        FOREIGN KEY (equipe_b_id)
        REFERENCES equipes(id)
        ON DELETE CASCADE
);