CREATE TABLE empenho(
	id serial PRIMARY KEY,
	valor NUMERIC,
	codacao VARCHAR,
	coddata INTEGER,
	codfavorecido VARCHAR,
	codunidadegestora INTEGER,
	FOREIGN KEY (codacao) REFERENCES Acao(codigoAcao),
	FOREIGN KEY (coddata) REFERENCES Data(codigo),
	FOREIGN KEY (codfavorecido) REFERENCES Favorecido(codigo),
	FOREIGN KEY (codunidadegestora) REFERENCES Unidadegestora(codigounidadegestora)
);

CREATE OR REPLACE FUNCTION insereempenhos() 
RETURNS VOID AS $$
DECLARE
rec RECORD;
BEGIN
	FOR rec IN
		SELECT sum(valor) as soma, codacao,coddata,codfavorecido,codunidadegestora
		FROM empenhotemporario
		GROUP BY (codacao,coddata,codfavorecido,codunidadegestora)
	LOOP
		INSERT INTO empenho (codacao,coddata,codfavorecido,codunidadegestora,valor)
		VALUES (rec.codacao,rec.coddata,rec.codfavorecido,rec.codunidadegestora,rec.soma);
	END LOOP;

	DELETE FROM EMPENHOTEMPORARIO;
END $$
LANGUAGE PLPGSQL;


