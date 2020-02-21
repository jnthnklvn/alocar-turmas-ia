create Table Professor (
                           id  serial PRIMARY KEY,
                           nome  VARCHAR (200) NOT NULL
);

create table Habilidades (
                             id  serial PRIMARY KEY,
                             disciplina  VARCHAR (200) NOT NULL,
                             professor_id BIGINT NOT NULL,
                             CONSTRAINT professor_fkey FOREIGN KEY (professor_id)
                                 REFERENCES Professor (id) MATCH SIMPLE
                                 ON UPDATE NO ACTION ON DELETE NO ACTION
);

create table Preferencias (
                              id  serial PRIMARY KEY,
                              disciplina  VARCHAR (200) NOT NULL,
                              professor_id BIGINT NOT NULL,
                              CONSTRAINT professor_fkey FOREIGN KEY (professor_id)
                                  REFERENCES Professor (id) MATCH SIMPLE
                                  ON UPDATE NO ACTION ON DELETE NO ACTION
);

