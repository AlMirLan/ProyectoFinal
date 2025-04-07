-- Inserts local --
-- Juegos--
DELETE FROM BOARD_GAME;

INSERT INTO BOARD_GAME VALUES (1, 0.0, 2, 'Juego de mesa de rol que usa dados', 'NULL', 'Titulo del juego', 0,0);
INSERT INTO BOARD_GAME VALUES (2, 5.0, 5, 'Juego de mesa de estrategia', 'NULL', 'Otro titulo', 0,0);
INSERT INTO BOARD_GAME VALUES (3, 2.0, 3, 'Juego que usa cartas', 'NULL', 'Titulo 1', 0,0);
INSERT INTO BOARD_GAME VALUES (4, 6.0, 1, 'Juego de mesa infantil', 'NULL', 'Titulo 2', 0,0);
INSERT INTO BOARD_GAME VALUES (5, 8.0, 4, 'Juego que usa dados y cartas', 'NULL', 'Titulo 6', 1,1);
INSERT INTO BOARD_GAME VALUES (6, 10.0, 6, 'Juego que usa dados y cartas', 'NULL', 'Titulo muymuymuymuy pero que muy largo', 0,20);


-- Proyectos--
DELETE FROM project;

INSERT INTO project VALUES (1, 20.5, 2, 'Juego de mesa que te enseña a no encariñarte con nada', 'NULL', 'Titulo 1', 0,1);
INSERT INTO project VALUES (2, 70, 1, 'Descripcion del juego de mesa blablablablablablablablablablablablablablablabla', 'NULL', 'Titulo 2', 10,2);
INSERT INTO project VALUES (3, 52.3, 7, 'Descripcion del juego de mesa blablablablablablablablablablablablablablablabla', 'NULL', 'Titulo 3', 8,4);
INSERT INTO project VALUES (4, 1.0, 6, 'Descripcion del juego de mesa blablablablablablablablablablablablablablablabla', 'NULL', 'Titulo 4', 30,5);
INSERT INTO project VALUES (5, 0.0, 4, 'Descripcion del juego de mesa blablablablablablablablablablablablablablablabla', 'NULL', 'Titulo 5', 0,2);


-- Reviews--
INSERT INTO review VALUES (1, 1, 'Me gusta', 10, 1, 1);

--Usuarios--
DELETE FROM user;

INSERT INTO USER VALUES (1, '2023-04-07 12:15:04.000000', 'a@hotmail.com', 'a', '123456');
INSERT INTO USER VALUES (2, '2023-04-07 12:15:04.000000', 's@hotmail.com', 's', '123456');
INSERT INTO USER VALUES (3, '2023-04-07 12:15:04.000000', 'g@hotmail.com', 'g', '123456');
INSERT INTO USER VALUES (4, '2023-04-07 12:15:04.000000', 'd@hotmail.com', 'd', '123456');
INSERT INTO USER VALUES (5, '2023-04-07 12:15:04.000000', 'm@hotmail.com', 'm', '123456');
INSERT INTO USER VALUES (6, '2023-04-07 12:15:04.000000', 'n@hotmail.com', 'n', '123456');
INSERT INTO USER VALUES (7, '2023-04-07 12:15:04.000000', 'u@hotmail.com', 'u', '123456');
INSERT INTO USER VALUES (8, '2023-04-07 12:15:04.000000', 'p@hotmail.com', 'p', '123456');
INSERT INTO USER VALUES (9, '2023-04-07 12:15:04.000000', 't@hotmail.com', 't', '123456');