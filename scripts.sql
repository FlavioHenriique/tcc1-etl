create table empenho(
	id serial primary key,
	valor numeric,
	codacao varchar,
	coddata integer,
	codfavorecido varchar,
	codunidadegestora integer,
	foreign key (codacao) references Acao(codigoAcao),
	foreign key (coddata) references Data(codigo),
	foreign key (codfavorecido) references Favorecido(codigo),
	foreign key (codunidadegestora) references Unidadegestora(codigounidadegestora)
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
END $$
LANGUAGE PLPGSQL;


