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
-- Database: `hola_hibernate`
--
CREATE DATABASE IF NOT EXISTS hola_hibernate;
USE hola_hibernate;
-- --------------------------------------------------------

--
-- Table structure for table `Personaje`
--

CREATE TABLE IF NOT EXISTS `personajes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(500) NOT NULL,
  `vida` int(10) unsigned NOT NULL,
  `puntos` int(10) unsigned NOT NULL,
  `arma` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `Personaje`
--

INSERT INTO `personajes` (`id`, `nombre`, `vida`, `puntos`, `arma`) VALUES
(1, 'superman', 100, 50, 'capa'),
(2, 'spiderman', 40, 30, 'telaraña');
