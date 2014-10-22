CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS Test.carrera (
  id INT(11) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS Test.plan_de_estudio (
  id INT(11) NOT NULL,
  anio INT(11) NOT NULL,
  carrera_id INT(11) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS Test.materia (
  id INT(11) NOT NULL,
  codigo INT(11) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS Test.plan_materia (
  id INT(11) NOT NULL,
  materia_id INT(11) NOT NULL,
  anio INT(11) NOT NULL,
  cuatrimestre INT(11) NOT NULL,
  correlativa_id INT(11) NULL,
  horas INT(11) NOT NULL,
  planEstudio_id INT(11) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS Test.alumno (
  id INT(11) NOT NULL,
  legajo INT(11) NOT NULL,
  apellido VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  planEstudio_id INT(11) NOT NULL,
  anioIngreso INT(11) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS Test.situacion_alumno (
  id INT(11) NOT NULL,
  alumno_id INT(11) NOT NULL,
  materia_id INT(11) NOT NULL,
  estado_situacion VARCHAR(255) NOT NULL,
  fecha_situacion VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
);

INSERT INTO Test.carrera(id, nombre)  VALUES (1, 'Ingenieria en computacion');

INSERT INTO Test.plan_de_estudio(id, anio, carrera_id)  VALUES (1, 2008, 1);

INSERT INTO Test.materia (id, codigo, nombre) VALUES
(111, 939, 'F�sica I'),
(112, 861, 'An�lisis Matem�tico I'),
(113, 742, 'Matem�tica Discreta I'),
(114, 743, 'Estructuras de Datos I'),
(115, 862, 'Algebra I'),
(116, 591, 'Problemas de Historia del Siglo 20'),
(117, 592, 'Taller de Introducci�n a la Problem�tica del Mundo Contempor�neo'),
(121, 942, 'F�sica II'),
(122, 941, 'An�lisis Matem�tico II'),
(123, 744, 'Matem�tica Discreta II'),
(124, 745, 'Lenguaje de Programaci�n I'),
(125, 952, 'Algebra II'),
(126, 2, 'Cultura Contempor�nea'),
(127, 592, 'Taller de Introducci�n a la Problem�tica del Mundo Contempor�neo'),
(211, 746, 'Probabilidad y Estad�stica I'),
(212, 943, 'An�lisis Matem�tico III'),
(213, 747, 'Estructuras de Datos II'),
(214, 748, 'Teor�a de Sistemas'),
(215, 749, 'Lenguaje de Programaci�n II'),
(216, 3, 'Cuestiones de Sociolog�a, Econom�a y Pol�tica'),
(221, 750, 'F�sica III'),
(222, 751, 'Probabilidad y Estad�stica II'),
(223, 752, 'Arquitectura de Computadoras'),
(224, 753, 'Estructuras de Datos III'),
(225, 754, 'An�lisis y Dise�o Estructurado'),
(226, 104, 'Investigaci�n Operativa'),
(311, 755, 'Qu�mica'),
(312, 756, 'Bases de Datos I'),
(313, 757, 'Teor�a de la Informaci�n'),
(314, 758, 'Lenguaje de Programaci�n III'),
(315, 1, 'Metodolog�a de la Informaci�n'),
(316, 759, 'Ingl�s B�sico'),
(321, 760, 'An�lisis y Dise�o Orientado a Objetos'),
(322, 761, 'Bases de Datos II'),
(323, 762, 'Sistemas Operativos'),
(324, 763, 'Lenguaje de Programaci�n IV'),
(325, 764, 'Teleinform�tica'),
(326, 765, 'Ingl�s T�cnico'),
(411, 766, 'An�lisis y Dise�o de Sistemas en Tiempo Real'),
(412, 767, 'Compiladores e Int�rpretes'),
(413, 768, 'Procesos Estoc�sticos'),
(414, 769, 'Ingenier�a en Software'),
(415, 770, 'Procesamiento de Se�ales I'),
(421, 771, 'Simulaci�n de Sistemas'),
(422, 772, 'Seguridad Inform�tica'),
(423, 773, 'Inteligencia Artificial'),
(424, 774, 'Procesamiento de Im�genes'),
(425, 775, 'Circuitos Electr�nicos'),
(511, 1101, 'Sistemas de Adquisici�n de Datos'),
(512, 777, 'Bioinform�tica'),
(513, 778, 'Rob�tica'),
(514, 779, 'Laboratorio de Electr�nica'),
(515, 780, 'Inform�tica M�dica'),
(521, 781, 'Software Legal'),
(522, 782, 'Procesamiento de Se�ales II'),
(523, 783, 'Auditor�a de Sistemas'),
(524, 784, 'Inform�tica Industrial'),
(525, 785, 'Trabajo de Tesis');

INSERT INTO Test.plan_materia (id, materia_id, anio, cuatrimestre, correlativa_id, horas, planEstudio_id) VALUES
(1, 111, 1, 1, null, 90, 1),
(2, 112, 1, 1, null, 90, 1),
(3, 113, 1, 1, null, 60, 1),
(4, 114, 1, 1, null, 60, 1),
(5, 115, 1, 1, null, 60, 1),
(6, 116, 1, 1, null, 60, 1),
(7, 117, 1, 1, null, 30, 1),
(8, 121, 1, 2, 1, 90, 1),
(9, 122, 1, 2, 2, 90, 1),
(10, 123, 1, 2, 3, 60, 1),
(11, 124, 1, 2, 4, 60, 1),
(12, 125, 1, 2, 5, 60, 1),
(13, 126, 1, 2, null, 60, 1),
(14, 127, 1, 2, null, 30, 1),
(15, 211, 2, 1, 2, 60, 1),
(16, 212, 2, 1, 9, 90, 1),
(17, 213, 2, 1, 4, 60, 1),
(18, 214, 2, 1, 10, 60, 1),
(19, 215, 2, 1, 11, 60, 1),
(20, 216, 2, 1, null, 60, 1),
(21, 221, 2, 2, 8, 90, 1),
(22, 222, 2, 2, 15, 60, 1),
(23, 223, 2, 2, 8, 60, 1),
(24, 224, 2, 2, 17, 60, 1),
(25, 225, 2, 2, 18, 60, 1),
(26, 226, 2, 2, 9, 60, 1),
(27, 311, 3, 1, 21, 90, 1),
(28, 312, 3, 1, 24, 90, 1),
(29, 313, 3, 1, 22, 60, 1),
(30, 314, 3, 1, 19, 60, 1),
(31, 315, 3, 1, null, 60, 1),
(32, 316, 3, 1, null, 60, 1),
(33, 321, 3, 2, 25, 60, 1),
(34, 322, 3, 2, 28, 90, 1),
(35, 323, 3, 2, 23, 120, 1),
(36, 324, 3, 2, 30, 60, 1),
(37, 325, 3, 2, 29, 60, 1),
(38, 326, 3, 2, 32, 60, 1),
(39, 411, 4, 1, 33, 60, 1),
(40, 412, 4, 1, 36, 60, 1),
(41, 413, 4, 1, 22, 60, 1),
(42, 414, 4, 1, 33, 60, 1),
(43, 415, 4, 1, 16, 60, 1),
(44, 421, 4, 2, 26, 60, 1),
(45, 422, 4, 2, 21, 90, 1),
(46, 423, 4, 2, 24, 60, 1),
(47, 424, 4, 2, 43, 60, 1),
(48, 425, 4, 2, 8, 90, 1),
(49, 511, 5, 1, 48, 60, 1),
(50, 512, 5, 1, 27, 60, 1),
(51, 513, 5, 1, 1, 90, 1),
(52, 514, 5, 1, 48, 90, 1),
(53, 515, 5, 1, 28, 60, 1),
(54, 521, 5, 2, null, 60, 1),
(55, 522, 5, 2, 43, 60, 1),
(56, 523, 5, 2, 45, 60, 1),
(57, 524, 5, 2, 51, 60, 1),
(58, 525, 5, 2, 42, 60, 1);

INSERT INTO Test.alumno (id, legajo, apellido, nombre, planEstudio_id, anioIngreso) VALUES
(1, 17478, 'Rivero', 'Gustavo Eduardo', 1, 2010),
(2, 19552, 'Etchepare Fajardo', 'Martin Alejandro', 1, 2010),
(3, 21178, 'Bengolea', 'Federico Alberto', 1, 2012),
(4, 23865, 'Fierro', 'Leandro Matias', 1, 2010),
(5, 26768, 'Garcia', 'Cristian Ezequiel', 1, 2011),
(6, 26834, 'Sethson', 'Sebastian Nahuel', 1, 2011),
(7, 27051, 'Alfonso', 'Lucas Daniel', 1, 2012),
(8, 27066, 'Romano', 'Leonardo Angel', 1, 2012),
(9, 29800, 'Neboli', 'Daniela Carla', 1, 2012),
(10, 29914, 'Klemenc', 'Marco', 1, 2012);

INSERT INTO Test.situacion_alumno (id, alumno_id, materia_id, estado_situacion, fecha_situacion) VALUES
(1, 1, 1, 'REGULAR', '2013-12-15'),
(2, 2, 2, 'APROBADO', '2013-12-15'),
(3, 3, 3, 'REGULAR', '2013-12-15'),
(4, 4, 4, 'ABANDONADO', '2013-12-15'),
(5, 5, 5, 'APROBADO', '2013-12-15'),
(6, 6, 6, 'CURSANDO', '2013-12-15'),
(7, 7, 7, 'CURSANDO', '2013-12-15'),
(8, 8, 13, 'APROBADO', '2013-12-15'),
(9, 9, 14, 'REGULAR', '2013-12-15'),
(10, 9, 9, 'CURSANDO', '2013-12-15'),
(11, 10, 17, 'APROBADO', '2013-12-15');
