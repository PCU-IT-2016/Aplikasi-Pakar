-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2018 at 04:32 PM
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
(1, 'beasiswa', 'uts ');

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
(12, 'Putra daerah terpenuhi', 1);

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
(6, 11, 1),
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
(18, 8, 2);

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
(5, 'Finansial', 1),
(6, 'Akademik', 1),
(7, 'Putra daerah', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rules_premise`
--

CREATE TABLE `rules_premise` (
  `id` int(11) NOT NULL,
  `rule_id` int(11) NOT NULL,
  `premise_id` int(11) NOT NULL,
  `operator_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rules_premise`
--

INSERT INTO `rules_premise` (`id`, `rule_id`, `premise_id`, `operator_id`) VALUES
(13, 1, 9, 1),
(14, 1, 2, 1),
(15, 1, 1, 1),
(16, 1, 7, 1),
(17, 1, 5, 1),
(18, 1, 8, 3);

-- --------------------------------------------------------

--
-- Table structure for table `rules_status`
--

CREATE TABLE `rules_status` (
  `id` int(11) NOT NULL,
  `rule_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `Symbol` varchar(8) NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Indexes for table `rule`
--
ALTER TABLE `rule`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rules_premise`
--
ALTER TABLE `rules_premise`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rule_id` (`rule_id`),
  ADD KEY `question_id` (`premise_id`) USING BTREE,
  ADD KEY `operator_id` (`operator_id`);

--
-- Indexes for table `rules_status`
--
ALTER TABLE `rules_status`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rule_id` (`rule_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `logic_operator`
--
ALTER TABLE `logic_operator`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `premise`
--
ALTER TABLE `premise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `premise_answer_list`
--
ALTER TABLE `premise_answer_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `rule`
--
ALTER TABLE `rule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rules_premise`
--
ALTER TABLE `rules_premise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `rules_status`
--
ALTER TABLE `rules_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
-- Constraints for table `rules_premise`
--
ALTER TABLE `rules_premise`
  ADD CONSTRAINT `rules_premise_ibfk_1` FOREIGN KEY (`premise_id`) REFERENCES `premise` (`id`),
  ADD CONSTRAINT `rules_premise_ibfk_2` FOREIGN KEY (`rule_id`) REFERENCES `rule` (`id`),
  ADD CONSTRAINT `rules_premise_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `logic_operator` (`id`);

--
-- Constraints for table `rules_status`
--
ALTER TABLE `rules_status`
  ADD CONSTRAINT `rules_status_ibfk_1` FOREIGN KEY (`rule_id`) REFERENCES `rule` (`id`),
  ADD CONSTRAINT `rules_status_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
