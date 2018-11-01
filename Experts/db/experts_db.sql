-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 01, 2018 at 12:21 PM
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
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `answer` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `answer`) VALUES
(1, 'YA'),
(2, 'NO');

-- --------------------------------------------------------

--
-- Table structure for table `experts`
--

CREATE TABLE `experts` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `experts`
--

INSERT INTO `experts` (`id`, `name`, `description`) VALUES
(1, 'beasiswa', 'uts '),
(2, 'Smartphone', 'diagnosa kerusakan pada Smartphone');

-- --------------------------------------------------------

--
-- Table structure for table `logic_operator`
--

CREATE TABLE `logic_operator` (
  `id` int(11) NOT NULL,
  `operator` varchar(84) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logic_operator`
--

INSERT INTO `logic_operator` (`id`, `operator`) VALUES
(1, 'AND'),
(2, 'OR'),
(3, 'None');

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
(12, 'Putra daerah terpenuhi', 1),
(13, 'Baterai Tidak Mengisi ?', 1),
(14, 'Baterai Cepat Panas ?', 1),
(15, 'Baterai menggelembung ?', 1),
(16, 'Sering terjadi White Screen ?', 1),
(17, 'Sering terjadi Blank Screen ?', 1),
(18, 'Smartphone sering panas ?', 1),
(22, 'Layar buram ?', 1),
(23, 'Layar Pecah ?', 1),
(24, 'Terdapat bercak atau garis pada layar ? ', 1),
(25, 'Tidak berhenti mengisi daya ? ', 1),
(26, 'Smartphone reboot secara tiba-tiba ? ', 1),
(27, 'Tidak dapat memproses aplikasi ? ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `premise_answer_list`
--

CREATE TABLE `premise_answer_list` (
  `id` int(11) NOT NULL,
  `premise_id` int(11) NOT NULL,
  `answer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `premise_answer_list`
--

INSERT INTO `premise_answer_list` (`id`, `premise_id`, `answer_id`) VALUES
(1, 10, 1),
(2, 10, 2),
(3, 9, 1),
(4, 9, 2),
(5, 11, 1),
(6, 11, 2),
(7, 2, 1),
(8, 2, 2),
(9, 1, 1),
(10, 1, 2),
(11, 7, 1),
(12, 7, 2),
(13, 5, 1),
(14, 5, 2),
(15, 12, 1),
(16, 12, 2),
(17, 8, 1),
(18, 8, 2),
(19, 22, 1),
(20, 22, 2),
(21, 23, 1),
(22, 23, 2),
(23, 24, 1),
(24, 24, 2),
(25, 16, 1),
(26, 16, 2),
(27, 18, 1),
(28, 18, 2),
(29, 17, 1),
(30, 17, 2),
(31, 13, 1),
(32, 13, 2),
(33, 25, 1),
(34, 25, 2),
(35, 26, 1),
(36, 26, 2),
(37, 27, 1),
(38, 27, 2),
(39, 14, 1),
(40, 14, 2),
(41, 15, 1),
(42, 15, 2);

-- --------------------------------------------------------

--
-- Table structure for table `premise_premises`
--

CREATE TABLE `premise_premises` (
  `id` int(11) NOT NULL,
  `rules_premise_id` int(11) NOT NULL,
  `premise_premise_id` int(11) NOT NULL,
  `premise_value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `premise_premises`
--

INSERT INTO `premise_premises` (`id`, `rules_premise_id`, `premise_premise_id`, `premise_value`) VALUES
(7, 19, 2, 1),
(8, 19, 1, 1),
(9, 20, 5, 1),
(10, 20, 8, 1),
(11, 20, 7, 1),
(12, 21, 9, 1),
(13, 22, 2, 2),
(14, 22, 1, 2),
(15, 23, 5, 2),
(16, 23, 8, 2),
(17, 23, 7, 2),
(18, 25, 9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `rule`
--

CREATE TABLE `rule` (
  `id` int(11) NOT NULL,
  `conclusion` varchar(32) NOT NULL,
  `expert_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rule`
--

INSERT INTO `rule` (`id`, `conclusion`, `expert_id`) VALUES
(1, 'Terima', 1),
(2, 'Tolak', 1),
(3, 'LCD', 2),
(4, 'IC, PowerSupply', 2),
(5, 'Software', 2),
(6, 'Baterai', 2);

-- --------------------------------------------------------

--
-- Table structure for table `rules_premise`
--

CREATE TABLE `rules_premise` (
  `id` int(11) NOT NULL,
  `rule_id` int(11) NOT NULL,
  `premise_id` int(11) NOT NULL,
  `operator_id` int(11) NOT NULL,
  `premise_val` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rules_premise`
--

INSERT INTO `rules_premise` (`id`, `rule_id`, `premise_id`, `operator_id`, `premise_val`) VALUES
(19, 1, 10, 1, 1),
(20, 1, 11, 1, 1),
(21, 1, 12, 3, 1),
(22, 2, 10, 2, 2),
(23, 2, 11, 2, 2),
(25, 2, 12, 3, 2),
(26, 3, 24, 1, 1),
(27, 3, 23, 1, 1),
(28, 3, 22, 1, 1),
(29, 3, 16, 1, 1),
(30, 4, 18, 1, 1),
(31, 4, 16, 1, 1),
(32, 4, 17, 1, 1),
(33, 4, 13, 1, 1),
(34, 5, 27, 1, 1),
(35, 5, 26, 1, 1),
(36, 5, 25, 1, 1),
(37, 6, 15, 1, 1),
(38, 6, 14, 1, 1),
(39, 6, 25, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `experts`
--
ALTER TABLE `experts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `logic_operator`
--
ALTER TABLE `logic_operator`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `premise`
--
ALTER TABLE `premise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `true_val` (`true_val`);

--
-- Indexes for table `premise_answer_list`
--
ALTER TABLE `premise_answer_list`
  ADD PRIMARY KEY (`id`),
  ADD KEY `answer_id` (`answer_id`),
  ADD KEY `question_id` (`premise_id`) USING BTREE;

--
-- Indexes for table `premise_premises`
--
ALTER TABLE `premise_premises`
  ADD PRIMARY KEY (`id`),
  ADD KEY `premise_premise_id` (`premise_premise_id`),
  ADD KEY `rules_premise_id` (`rules_premise_id`);

--
-- Indexes for table `rule`
--
ALTER TABLE `rule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `expert_id` (`expert_id`);

--
-- Indexes for table `rules_premise`
--
ALTER TABLE `rules_premise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rule_id` (`rule_id`),
  ADD KEY `question_id` (`premise_id`) USING BTREE,
  ADD KEY `operator_id` (`operator_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `experts`
--
ALTER TABLE `experts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `logic_operator`
--
ALTER TABLE `logic_operator`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `premise`
--
ALTER TABLE `premise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `premise_answer_list`
--
ALTER TABLE `premise_answer_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `premise_premises`
--
ALTER TABLE `premise_premises`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `rule`
--
ALTER TABLE `rule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `rules_premise`
--
ALTER TABLE `rules_premise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `premise`
--
ALTER TABLE `premise`
  ADD CONSTRAINT `premise_ibfk_1` FOREIGN KEY (`true_val`) REFERENCES `answer` (`id`);

--
-- Constraints for table `premise_answer_list`
--
ALTER TABLE `premise_answer_list`
  ADD CONSTRAINT `premise_answer_list_ibfk_1` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`),
  ADD CONSTRAINT `premise_answer_list_ibfk_2` FOREIGN KEY (`premise_id`) REFERENCES `premise` (`id`);

--
-- Constraints for table `premise_premises`
--
ALTER TABLE `premise_premises`
  ADD CONSTRAINT `premise_premises_ibfk_2` FOREIGN KEY (`premise_premise_id`) REFERENCES `premise` (`id`),
  ADD CONSTRAINT `premise_premises_ibfk_3` FOREIGN KEY (`rules_premise_id`) REFERENCES `rules_premise` (`id`);

--
-- Constraints for table `rule`
--
ALTER TABLE `rule`
  ADD CONSTRAINT `rule_ibfk_1` FOREIGN KEY (`expert_id`) REFERENCES `experts` (`id`);

--
-- Constraints for table `rules_premise`
--
ALTER TABLE `rules_premise`
  ADD CONSTRAINT `rules_premise_ibfk_1` FOREIGN KEY (`premise_id`) REFERENCES `premise` (`id`),
  ADD CONSTRAINT `rules_premise_ibfk_2` FOREIGN KEY (`rule_id`) REFERENCES `rule` (`id`),
  ADD CONSTRAINT `rules_premise_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `logic_operator` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
