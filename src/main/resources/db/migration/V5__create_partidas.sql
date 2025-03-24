CREATE TABLE partidas (
    id SERIAL PRIMARY KEY,
    horario TIMESTAMP NOT NULL,

    torneio_eliminatoria_simples_id INT REFERENCES torneios_eliminatoria_simples(id) ON DELETE CASCADE,
    torneio_fase_grupos_id INT REFERENCES torneios_fase_grupos(id) ON DELETE CASCADE,

    equipe_a_id INT NOT NULL REFERENCES equipes(id),
    equipe_b_id INT NOT NULL REFERENCES equipes(id),

    placar_equipe_a INT,
    placar_equipe_b INT,

    status VARCHAR(50) NOT NULL
);