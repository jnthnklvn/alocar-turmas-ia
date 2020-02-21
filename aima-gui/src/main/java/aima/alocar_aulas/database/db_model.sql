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


insert into professor (nome) values ('Walter');
insert into professor (nome) values ('Elena');
insert into professor (nome) values ('Evelyn');
insert into professor (nome) values ('Mia');
insert into professor (nome) values ('Robert');



insert into habilidades (disciplina,professor_id)  values ('Banco de Dados I',1);
insert into habilidades (disciplina,professor_id)  values ('Eletronica I',1);
insert into habilidades (disciplina,professor_id)  values ('Engenharia de Software II',1);
insert into habilidades (disciplina,professor_id)  values ('Inteligencia Artificial',2);
insert into habilidades (disciplina,professor_id)  values ('Laboratorio de Redes de Computadores',2);
insert into habilidades (disciplina,professor_id)  values ('Sistemas Distribuidos',2);
insert into habilidades (disciplina,professor_id)  values ('Banco de Dados I',3);
insert into habilidades (disciplina,professor_id)  values ('Eletronica I',3);
insert into habilidades (disciplina,professor_id)  values ('Engenharia de Software II',3);
insert into habilidades (disciplina,professor_id)  values ('Inteligencia Artificial',4);
insert into habilidades (disciplina,professor_id)  values ('Laboratorio de Redes de Computadores',4);
insert into habilidades (disciplina,professor_id)  values ('Programacao Paralela e Concorrente',4);
insert into habilidades (disciplina,professor_id)  values ('Banco de Dados I',5);
insert into habilidades (disciplina,professor_id)  values ('Eletronica I',5);
insert into habilidades (disciplina,professor_id)  values ('Sistemas Distribuidos',5);
