-- 
-- Base de datos: `alumnos` 
-- 
-- -------------------------------------------------------- 
-- 
-- Estructura de tabla para la tabla `alumnos` 
-- 
CREATE TABLE IF NOT EXISTS `alumnos` ( 
  `DNI` varchar(9) NOT NULL, 
  `Nombre` varchar(50) NOT NULL, 
  `Apellidos` varchar(70) NOT NULL, 
  `Direccion` varchar(100) NOT NULL, 
  `FechaNac` date NOT NULL, 
  PRIMARY KEY (`DNI`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 
-- 
-- Volcar la base de datos para la tabla `alumnos` 
-- 
INSERT INTO `alumnos` (`DNI`, `Nombre`, `Apellidos`, `Direccion`, `FechaNac`) VALUES 
('12345678A', 'José Alberto', 'González Pérez', 'C/Albahaca, nº14, 1ºD', '1986-07-15'), 
('23456789B', 'Almudena', 'Cantero Verdemar', 'Avd/ Profesor Alvarado, n27, 8ºA', '1988-11-04'), 
('14785236d', 'Martín', 'Díaz Jiménez', 'C/Luis de Gongora, nº2.', '1987-03-09'), 
('96385274f', 'Lucas', 'Buendia Portes', 'C/Pintor Sorolla, nº 16, 4ºB', '1988-07-10');
-- 
-- Estructura de tabla para la tabla `calificaciones` 
-- 


CREATE TABLE IF NO EXISTS `calificaciones` (
  
  `id` int(11) auto_increment NOT NULL,
  
  `DNI` varchar(9) NOT NULL,
  
  `NombreModulo` varchar(60) NOT NULL,
  
  `Curso` varchar(5) NOT NULL,
  
  `Nota` double NOT NULL
,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 
-- Volcar la base de datos para la tabla `calificaciones` 
-- 
INSERT INTO `calificaciones` (`id`, `DNI`, `NombreModulo`, `Curso`, `Nota`) VALUES
(1, '12345678A', 'AD', '19-20', 6),
(2, '12345678A', 'PMDM', '19-20', 4),
(3, '23456789B', 'AD', '19-20', 8),
(4, '23456789B', 'PMDM', '19-20', 8),
(5, '14785236d', 'AD', '19-20', 4),
(6, '14785236d', 'PMDM', '19-20', 4),
(7, '96385274f', 'AD', '19-20', 10),
(8, '96385274f', 'PMDM', '19-20', 10),
(9, '98765432A', 'AD', '19-20', 9),
(10, '98765432A', 'PMDM', '19-20', 9),
(11, '48882828P', 'AD', '19-20', 9);

