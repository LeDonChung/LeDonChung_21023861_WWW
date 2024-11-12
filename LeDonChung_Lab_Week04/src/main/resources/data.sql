-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for week04
CREATE DATABASE IF NOT EXISTS `week04` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `week05`;

-- Dumping structure for table week05.candidates
CREATE TABLE IF NOT EXISTS `candidates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(70) NOT NULL,
  `middle_name` varchar(70) NOT NULL,
  `first_name` varchar(70) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(70) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week05.candidates: ~5 rows (approximately)
INSERT INTO `candidates` (`id`, `last_name`, `middle_name`, `first_name`, `dob`, `email`, `address`, `phone`) VALUES
	(1, 'Chung', 'Don', 'Le', '1999-01-01', 'ledonchung@gmail.com', '44/10 ', '0867713557'),
	(3, 'Chung', 'Don', 'Le', '1999-01-01', 'ledonchu1g@gmail.com', '44/10 ', '0867713557'),
	(4, 'Chung', 'Don', 'Le', '1999-01-01', 'demo@gmail.com', '44/10 ', '0867713557'),
	(6, '', '', 'demo', '1999-01-01', 'donle@gmail.com', '44/10 ', '0867713557'),
	(7, '', '', 'demo', '1999-01-01', 'donle1@gmail.com', '44/10 ', '0867713557');

-- Dumping structure for table week05.candidates_skills
CREATE TABLE IF NOT EXISTS `candidates_skills` (
  `candidate_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `level` int(11) NOT NULL CHECK (`level` >= 1 and `level` <= 5),
  PRIMARY KEY (`candidate_id`,`skill_id`),
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `candidates_skills_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`id`),
  CONSTRAINT `candidates_skills_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week05.candidates_skills: ~9 rows (approximately)
INSERT INTO `candidates_skills` (`candidate_id`, `skill_id`, `level`) VALUES
	(1, 1, 3),
	(1, 2, 4),
	(1, 5, 4),
	(3, 1, 3),
	(3, 4, 4),
	(3, 5, 4),
	(6, 1, 4),
	(6, 2, 3),
	(6, 4, 4);

-- Dumping structure for table week05.jobs
CREATE TABLE IF NOT EXISTS `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week05.jobs: ~4 rows (approximately)
INSERT INTO `jobs` (`id`, `description`) VALUES
	(2, 'Java Developer'),
	(3, 'Frontend Developer'),
	(4, 'Backend Developer'),
	(5, 'C# Developer');

-- Dumping structure for table week05.jobs_skills
CREATE TABLE IF NOT EXISTS `jobs_skills` (
  `job_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `specific_level` int(11) NOT NULL CHECK (`specific_level` >= 1 and `specific_level` <= 5),
  PRIMARY KEY (`job_id`,`skill_id`),
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `jobs_skills_ibfk_1` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`id`),
  CONSTRAINT `jobs_skills_ibfk_2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week05.jobs_skills: ~2 rows (approximately)
INSERT INTO `jobs_skills` (`job_id`, `skill_id`, `specific_level`) VALUES
	(2, 2, 4),
	(2, 4, 3);

-- Dumping structure for table week05.skills
CREATE TABLE IF NOT EXISTS `skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(70) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `field` varchar(70) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week05.skills: ~6 rows (approximately)
INSERT INTO `skills` (`id`, `skill_name`, `description`, `field`) VALUES
	(1, 'Java', 'Java Developer', 'IT'),
	(2, 'C#', 'Java Developer', 'IT'),
	(3, 'Python', 'Java Developer', 'IT'),
	(4, 'C++', 'Java Developer', 'IT'),
	(5, 'MySQL', 'Java Developer', 'IT'),
	(6, 'MariaDB', 'Java Developer', 'IT');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
