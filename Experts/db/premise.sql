-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2018 at 04:28 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `experts_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `premise`
--

CREATE TABLE `premise` (
  `id` int(11) NOT NULL,
  `question` varchar(256) NOT NULL,
  `true_val` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `premise`
--

INSERT INTO `premise` (`id`, `question`, `true_val`) VALUES
(1, 'lulus test math ?', 1),
(2, 'lulus test b.ing ?', 1),
(5, 'punya mobil pribadi ?', 2),
(7, 'pemakaian daya listrik di rekening lebih kecil sama dengan 2200VA', 1),
(8, 'uang gaji perbulan >= 1jt dari ortu ?', 2),
(9, 'ayah atau ibu memiliki nama marga asli putra daerah ?', 1),
(10, 'Akademik terpenuhi', 1),
(11, 'Finansial terpenuhi', 1),
(12, 'Putra daerah terpenuhi', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `premise`
--
ALTER TABLE `premise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `true_val` (`true_val`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `premise`
--
ALTER TABLE `premise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `premise`
--
ALTER TABLE `premise`
  ADD CONSTRAINT `premise_ibfk_1` FOREIGN KEY (`true_val`) REFERENCES `answer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
