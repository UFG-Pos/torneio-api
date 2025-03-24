CREATE TABLE equipes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,

    torneio_eliminatoria_simples_id INT REFERENCES torneios_eliminatoria_simples(id) ON DELETE CASCADE,
    torneio_fase_grupos_id INT REFERENCES torneios_fase_grupos(id) ON DELETE CASCADE
);