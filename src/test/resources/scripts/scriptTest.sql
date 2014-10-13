CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS Test.materia (
  id INT(11) NOT NULL,
  codigo INT(11) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  anio INT(11) NOT NULL,
  cuatrimestre INT(11) NOT NULL,
  horas INT(11) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS Test.carrera (
  id INT(11) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
);

CREATE TABLE IF NOT EXISTS Test.plan_de_estudio (
  id INT(11) NOT NULL,
  carrera_id INT(11) NOT NULL,
  anio INT(11) NOT NULL,
  PRIMARY KEY (`id`),
);

INSERT INTO Test.materia (id, codigo, nombre, anio, cuatrimestre, horas) VALUES
(111, 939, 'F�sica I', 1, 1, 90),
(112, 861, 'An�lisis Matem�tico I', 1, 1, 90),
(113, 742, 'Matem�tica Discreta I', 1, 1, 60),
(114, 743, 'Estructuras de Datos I', 1, 1, 60),
(115, 862, 'Algebra I', 1, 1, 60),
(116, 591, 'Problemas de Historia del Siglo 20', 1, 1, 60),
(117, 592, 'Taller de Introducci�n a la Problem�tica del Mundo Contempor�neo', 1, 1, 30),
(121, 942, 'F�sica II', 1, 2, 90),
(122, 941, 'An�lisis Matem�tico II', 1, 2, 90),
(123, 744, 'Matem�tica Discreta II', 1, 2, 60),
(124, 745, 'Lenguaje de Programaci�n I', 1, 2, 60),
(125, 952, 'Algebra II', 1, 2, 60),
(126, 2, 'Cultura Contempor�nea', 1, 2, 60),
(127, 592, 'Taller de Introducci�n a la Problem�tica del Mundo Contempor�neo', 1, 2, 30),
(211, 746, 'Probabilidad y Estad�stica I', 2, 1, 60),
(212, 943, 'An�lisis Matem�tico III', 2, 1, 90),
(213, 747, 'Estructuras de Datos II', 2, 1, 60),
(214, 748, 'Teor�a de Sistemas', 2, 1, 60),
(215, 749, 'Lenguaje de Programaci�n II', 2, 1, 60),
(216, 3, 'Cuestiones de Sociolog�a, Econom�a y Pol�tica', 2, 1, 60),
(221, 750, 'F�sica III', 2, 2, 90),
(222, 751, 'Probabilidad y Estad�stica II', 2, 2, 60),
(223, 752, 'Arquitectura de Computadoras', 2, 2, 60),
(224, 753, 'Estructuras de Datos III', 2, 2, 60),
(225, 754, 'An�lisis y Dise�o Estructurado', 2, 2, 60),
(226, 104, 'Investigaci�n Operativa', 2, 2, 60),
(311, 755, 'Qu�mica', 3, 1, 90),
(312, 756, 'Bases de Datos I', 3, 1, 90),
(313, 757, 'Teor�a de la Informaci�n', 3, 1, 60),
(314, 758, 'Lenguaje de Programaci�n III', 3, 1, 60),
(315, 1, 'Metodolog�a de la Informaci�n', 3, 1, 60),
(316, 759, 'Ingl�s B�sico', 3, 1, 60),
(321, 760, 'An�lisis y Dise�o Orientado a Objetos', 3, 2, 60),
(322, 761, 'Bases de Datos II', 3, 2, 90),
(323, 762, 'Sistemas Operativos', 3, 2, 120),
(324, 763, 'Lenguaje de Programaci�n IV', 3, 2, 60),
(325, 764, 'Teleinform�tica', 3, 2, 60),
(326, 765, 'Ingl�s T�cnico', 3, 2, 60),
(411, 766, 'An�lisis y Dise�o de Sistemas en Tiempo Real', 4, 1, 60),
(412, 767, 'Compiladores e Int�rpretes', 4, 1, 60),
(413, 768, 'Procesos Estoc�sticos', 4, 1, 60),
(414, 769, 'Ingenier�a en Software', 4, 1, 60),
(415, 770, 'Procesamiento de Se�ales I', 4, 1, 60),
(421, 771, 'Simulaci�n de Sistemas', 4, 2, 60),
(422, 772, 'Seguridad Inform�tica', 4, 2, 90),
(423, 773, 'Inteligencia Artificial', 4, 2, 60),
(424, 774, 'Procesamiento de Im�genes', 4, 2, 60),
(425, 775, 'Circuitos Electr�nicos', 4, 2, 90),
(511, 1101, 'Sistemas de Adquisici�n de Datos', 5, 1, 60),
(512, 777, 'Bioinform�tica', 5, 1, 60),
(513, 778, 'Rob�tica', 5, 1, 90),
(514, 779, 'Laboratorio de Electr�nica', 5, 1, 90),
(515, 780, 'Inform�tica M�dica', 5, 1, 60),
(521, 781, 'Software Legal', 5, 2, 60),
(522, 782, 'Procesamiento de Se�ales II', 5, 2, 60),
(523, 783, 'Auditor�a de Sistemas', 5, 2, 60),
(524, 784, 'Inform�tica Industrial', 5, 2, 60),
(525, 785, 'Trabajo de Tesis', 5, 2, 60);

INSERT INTO Test.carrera (id, nombre) VALUES
(15, 'Ingenieria en computacion');

INSERT INTO Test.plan_de_estudio (id, carrera_id, anio) VALUES
(1, 15, 2008);