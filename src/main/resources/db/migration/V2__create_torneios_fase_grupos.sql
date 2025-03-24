CREATE TABLE torneios_fase_grupos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    max_participantes INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    numero_grupos INT NOT NULL
);