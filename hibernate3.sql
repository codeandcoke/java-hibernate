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
-- Database: `hibernate3`
--

-- --------------------------------------------------------

--
-- Table structure for table `Arma`
--

CREATE TABLE IF NOT EXISTS `Arma` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `dano` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `nombre_2` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Arma`
--

INSERT INTO `Arma` (`id`, `nombre`, `descripcion`, `dano`) VALUES
(3, 'pistola', 'aksdj', 2);

-- --------------------------------------------------------

--
-- Table structure for table `Personaje`
--

CREATE TABLE IF NOT EXISTS `Personaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `vida` int(11) NOT NULL,
  `puntos` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `Personaje`
--

INSERT INTO `Personaje` (`id`, `nombre`, `vida`, `puntos`) VALUES
(2, 'sdf', 23, 3),
(3, 'asd', 2, 3),
(4, 'asd', 3, 3),
(6, '2', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `personaje_arma`
--

CREATE TABLE IF NOT EXISTS `personaje_arma` (
  `id_personaje` int(11) NOT NULL,
  `id_arma` int(11) NOT NULL,
  PRIMARY KEY (`id_personaje`,`id_arma`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personaje_arma`
--

