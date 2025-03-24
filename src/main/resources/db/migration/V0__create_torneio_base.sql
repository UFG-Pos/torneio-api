CREATE TABLE torneio_base (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    max_participantes INT NOT NULL,
    status VARCHAR(50) NOT NULL
);