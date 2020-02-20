create Table Professor (
	id  serial PRIMARY KEY, 
	nome  VARCHAR (200) NOT NULL,
	preferencia_disciplina  VARCHAR (50) NOT NULL,
	preferencia_horario VARCHAR (50),
	preferencia_dia  VARCHAR (50)
);

create table Habilidades (
	id  serial PRIMARY KEY, 
	disciplinas  VARCHAR (200) NOT NULL,
	professor_id BIGINT NOT NULL,
	CONSTRAINT professor_fkey FOREIGN KEY (professor_id)
    REFERENCES Professor (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);
