CREATE TABLE grupos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    torneio_id BIGINT NOT NULL,
    CONSTRAINT fk_grupo_torneio FOREIGN KEY (torneio_id)
        REFERENCES torneio_base(id) ON DELETE CASCADE
);

ALTER TABLE equipes
ADD COLUMN grupo_id BIGINT,
ADD CONSTRAINT fk_equipes_grupo FOREIGN KEY (grupo_id)
    REFERENCES grupos(id) ON DELETE SET NULL;