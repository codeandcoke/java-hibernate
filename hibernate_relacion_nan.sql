-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-01-2014 a las 13:25:51
-- Versión del servidor: 5.5.32
-- Versión de PHP: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `hibernate_relacion_nan`
--
CREATE DATABASE IF NOT EXISTS `hibernate_relacion_nan` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `hibernate_relacion_nan`;

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_personajes`()
begin
delete from personaje_pantalla;
delete from personajes;
end$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `get_total_puntos`() RETURNS int(11)
begin
declare x int;
set x = (select sum(puntos) from personajes);
return x;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `armas`
--

CREATE TABLE IF NOT EXISTS `armas` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `dano` int(10) unsigned DEFAULT '5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `armas`
--

INSERT INTO `armas` (`id`, `nombre`, `descripcion`, `dano`) VALUES
(1, 'espada', 'Una espada láser', 5),
(2, 'palo', 'Un palo', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enemigo_arma`
--

CREATE TABLE IF NOT EXISTS `enemigo_arma` (
  `id_enemigo` int(11) NOT NULL,
  `id_arma` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `enemigo_arma`
--

INSERT INTO `enemigo_arma` (`id_enemigo`, `id_arma`) VALUES
(1, 1),
(3, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enemigos`
--

CREATE TABLE IF NOT EXISTS `enemigos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) DEFAULT NULL,
  `vida` int(10) unsigned DEFAULT '100',
  `puntos` int(10) unsigned DEFAULT '10',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `enemigos`
--

INSERT INTO `enemigos` (`id`, `nombre`, `vida`, `puntos`) VALUES
(1, 'El malo', 2, 0),
(3, 'el malísimo', 100, 240);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
