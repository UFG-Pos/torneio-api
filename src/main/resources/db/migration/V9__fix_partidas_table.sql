ALTER TABLE partidas DROP CONSTRAINT fk_partidas_torneio;

ALTER TABLE partidas
ADD CONSTRAINT fk_partidas_torneio
FOREIGN KEY (torneio_id)
REFERENCES torneio_base(id);