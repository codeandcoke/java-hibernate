-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 04, 2013 at 11:01 AM
-- Server version: 5.1.44
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
--
CREATE DATABASE IF NOT EXISTS hibernate_relacion_1a1;
USE hibernate_relacion_1a1;
-- --------------------------------------------------------

--
-- Table structure for table `Arma`
--

CREATE TABLE IF NOT EXISTS `armas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `dano` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `Arma`
--

INSERT INTO `armas` (`id`, `nombre`, `descripcion`, `dano`) VALUES
(3, 'pistola', 'pistola de matar', 459),
(4, 'capa', 'capa roja', 10),
(5, 'espada', 'espada láser', 100);

-- --------------------------------------------------------

--
-- Table structure for table `Personaje`
--

CREATE TABLE IF NOT EXISTS `personajes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `vida` int(11) NOT NULL,
  `puntos` int(11) NOT NULL,
  `id_arma` int(11),
  INDEX (id_arma),
  FOREIGN KEY (id_arma)
    REFERENCES armas (id)
    ON DELETE SET NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `Personaje`
--

INSERT INTO `personajes` (`id`, `nombre`, `vida`, `puntos`, `id_arma`) VALUES
(4, 'superman', 10, 12, 4),
(5, 'Otro', 13, 23, 5),
(6, 'asd', 3, 3, 5),
(8, 'asd', 3, 3, 4);
