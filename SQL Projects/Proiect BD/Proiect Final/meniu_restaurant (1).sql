-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2019 at 12:34 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `meniu_restaurant`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `min_timp` ()  BEGIN
SELECT nume,descriere,timp_preparare
	FROM Reteta 
	WHERE vegetariana='D' AND timp_preparare <= ALL(SELECT timp_preparare FROM Reteta);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `categorie`
--

CREATE TABLE `categorie` (
  `categ_id` int(11) NOT NULL,
  `tip` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorie`
--

INSERT INTO `categorie` (`categ_id`, `tip`) VALUES
(1, 'FEL PRINCIPAL'),
(2, 'APERITIV'),
(3, 'DESERT'),
(4, 'CIORBA'),
(5, 'SUPA'),
(6, 'BAUTURA'),
(7, 'SALATE');

-- --------------------------------------------------------

--
-- Table structure for table `ingrediente`
--

CREATE TABLE `ingrediente` (
  `ingred_id` int(11) NOT NULL,
  `ingredient` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ingrediente`
--

INSERT INTO `ingrediente` (`ingred_id`, `ingredient`) VALUES
(4, 'aluat'),
(5, 'usturoi'),
(6, 'ciuperci'),
(7, 'carne de porc'),
(8, 'carne de pui'),
(9, 'sos de rosii'),
(10, 'varza'),
(11, 'fasole'),
(12, 'rosii'),
(14, 'conopida'),
(15, 'castraveti'),
(16, 'branza'),
(17, 'ulei'),
(18, 'sare'),
(19, 'condimente'),
(20, 'carne supriza'),
(21, 'cartofi'),
(22, 'porumb'),
(23, 'lapte');

-- --------------------------------------------------------

--
-- Table structure for table `reteta`
--

CREATE TABLE `reteta` (
  `reteta_id` int(11) NOT NULL,
  `nume` varchar(255) NOT NULL,
  `descriere` varchar(255) DEFAULT NULL,
  `categ_id` int(11) DEFAULT NULL,
  `vegetariana` varchar(1) NOT NULL,
  `timp_preparare` int(11) DEFAULT NULL,
  `portii` int(11) NOT NULL,
  `sursa` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reteta`
--

INSERT INTO `reteta` (`reteta_id`, `nume`, `descriere`, `categ_id`, `vegetariana`, `timp_preparare`, `portii`, `sursa`) VALUES
(15, 'Gratar Pui', 'Atent asezonat cu condimente', 1, 'N', 20, 1, NULL),
(17, 'Cartofi prajiti', 'fara sare', 2, 'D', 17, 1, NULL),
(18, 'Mamaliga', '', 2, 'D', 22, 2, NULL),
(19, 'Cartofi la cuptor', '', 2, 'D', 22, 1, NULL),
(20, 'tocanita ciuperci', '', 1, 'D', 40, 2, NULL),
(22, 'Ciorba de fasole', '', 4, 'D', 30, 2, NULL),
(23, 'Ciorba de legume', 'Contine cele mai proaspete legume.', 4, 'D', 40, 3, NULL),
(27, 'Ciorba de usturoi', '', 4, 'D', 30, 2, NULL),
(31, 'Cotlet de porc', 'Foarte bun', 1, 'N', 15, 1, 'Din popor'),
(33, 'Pizza', 'Foarte buna', 1, 'N', 20, 1, 'Din italia.'),
(34, 'Supa Specialitatea casei', 'Contine cele mai proaspete legume.', 5, 'N', 10, 1, 'Jean DeSerena'),
(35, 'Pizza Vegetariana', 'Foarte buna', 1, 'D', 20, 1, 'Din italia dar vegetariana.'),
(43, 'Bere', 'Cu un pret mult mai mare fata de magazin sau producator pt ca putem.', 6, 'D', 0, 1, NULL),
(44, 'Suc de portocale', 'Cu un pret mult mai mare fata de magazin sau producator pt ca putem.', 6, 'D', 0, 1, NULL),
(46, 'Apa plata', 'Cu un pret mult mai mare fata de magazin sau producator pt ca putem.', 6, 'D', 0, 1, NULL),
(47, 'Apa minerala', 'Cu un pret mult mai mare fata de magazin sau producator pt ca putem.', 6, 'D', 0, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `set_ingrediente`
--

CREATE TABLE `set_ingrediente` (
  `reteta_id` int(11) NOT NULL,
  `ingred_id` int(11) NOT NULL,
  `cantitate` decimal(6,3) NOT NULL,
  `um` varchar(255) NOT NULL,
  `comentarii` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `set_ingrediente`
--

INSERT INTO `set_ingrediente` (`reteta_id`, `ingred_id`, `cantitate`, `um`, `comentarii`) VALUES
(15, 8, '400.000', 'gr', ''),
(15, 18, '11.000', 'gr', ''),
(15, 19, '1.000', 'lingura', ''),
(17, 17, '100.000', 'ml', ''),
(17, 21, '300.000', 'gr', ''),
(18, 22, '300.000', 'gr', ''),
(18, 23, '200.000', 'ml', ''),
(19, 5, '2.000', 'buc', ''),
(19, 16, '100.000', 'gr', ''),
(19, 21, '300.000', 'gr', ''),
(20, 5, '5.000', 'buc', ''),
(20, 6, '150.000', 'gr', 'cele mai bune ciuperci'),
(20, 9, '50.000', 'ml', ''),
(20, 19, '20.000', 'gr', ''),
(22, 5, '12.000', 'buc', ''),
(22, 11, '150.000', 'gr', ''),
(22, 12, '120.000', 'gr', ''),
(23, 12, '500.000', 'gr', ''),
(23, 14, '50.000', 'gr', ''),
(23, 15, '100.000', 'gr', ''),
(23, 21, '110.000', 'gr', ''),
(27, 19, '2.000', 'lingurita', ''),
(31, 7, '1.000', 'buc', ''),
(31, 18, '1.000', 'lingura', ''),
(31, 19, '2.000', 'lingura', ''),
(33, 4, '300.000', 'gr', 'cel mai pufos aluat din lume'),
(34, 5, '10.000', 'buc', ''),
(34, 7, '200.000', 'gr', ''),
(34, 8, '200.000', 'gr', ''),
(34, 9, '200.000', 'gr', ''),
(34, 14, '100.000', 'gr', ''),
(34, 16, '200.000', 'gr', ''),
(34, 17, '10.000', 'ml', ''),
(34, 20, '200.000', 'gr', ''),
(35, 4, '300.000', 'gr', 'cel mai pufos aluat din lume'),
(35, 6, '200.000', 'gr', ''),
(35, 10, '200.000', 'gr', ''),
(35, 16, '200.000', 'gr', ''),
(35, 21, '200.000', 'gr', ''),
(35, 22, '200.000', 'gr', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`categ_id`);

--
-- Indexes for table `ingrediente`
--
ALTER TABLE `ingrediente`
  ADD PRIMARY KEY (`ingred_id`);

--
-- Indexes for table `reteta`
--
ALTER TABLE `reteta`
  ADD PRIMARY KEY (`reteta_id`),
  ADD KEY `FK_Reteta` (`categ_id`);

--
-- Indexes for table `set_ingrediente`
--
ALTER TABLE `set_ingrediente`
  ADD PRIMARY KEY (`reteta_id`,`ingred_id`),
  ADD KEY `FK2_Set_ingrediente` (`ingred_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reteta`
--
ALTER TABLE `reteta`
  ADD CONSTRAINT `FK_Reteta` FOREIGN KEY (`categ_id`) REFERENCES `categorie` (`categ_id`);

--
-- Constraints for table `set_ingrediente`
--
ALTER TABLE `set_ingrediente`
  ADD CONSTRAINT `FK2_Set_ingrediente` FOREIGN KEY (`ingred_id`) REFERENCES `ingrediente` (`ingred_id`),
  ADD CONSTRAINT `FK_Set_ingrediente` FOREIGN KEY (`reteta_id`) REFERENCES `reteta` (`reteta_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
