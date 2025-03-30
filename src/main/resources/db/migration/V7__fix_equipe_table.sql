ALTER TABLE equipes
DROP CONSTRAINT fk_equipes_torneio;

ALTER TABLE equipes
ADD CONSTRAINT fk_equipes_torneio
FOREIGN KEY (torneio_id)
REFERENCES torneio_base(id)
ON DELETE CASCADE;