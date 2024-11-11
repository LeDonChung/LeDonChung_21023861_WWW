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


-- Dumping database structure for week01
CREATE DATABASE IF NOT EXISTS `week01` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `week01`;

-- Dumping structure for table week01.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week01.account: ~11 rows (approximately)
INSERT INTO `account` (`account_id`, `full_name`, `password`, `email`, `phone`, `status`) VALUES
	('1731332826', 'Kali', '123', 'kai@gmail.co', '0389127482', 1),
	('1731332830', 'Basa', '123', 'basa@gmail.com', '0389127482', 1),
	('1731332841', 'Naka', '123', 'naki@gmail.com', '0389127482', 1),
	('1731333123', 'Nika', '123', 'ao@gmail.com', '0389127482', 1),
	('1731333466', 'Don Nga', '123', 'obama@gmail.com', '0867713557', 1),
	('1731333474', 'Thanh Huyen', '123', 'joget@gmail.com', '0867713557', 1),
	('1731333510', 'Kim Nga', '123', 'buider@gmail.com', '0867713557', 1),
	('1731334157', 'OKI', '123', 'dbck@gmail.com', '08677155762', 1),
	('1731334189', 'BNA', '123', 'asasd@gmail.com', '0867713557', 1),
	('admin', 'Le Don Chung', '123', 'ledonchung@gmail.com', '0867713557', 1),
	('user', 'Thanh Tuyen', '123', 'thanhtuyen@gmail.com', '0396172224', 1);

-- Dumping structure for table week01.grant_access
CREATE TABLE IF NOT EXISTS `grant_access` (
  `role_id` varchar(50) NOT NULL,
  `account_id` varchar(50) NOT NULL,
  `is_grant` bit(1) NOT NULL DEFAULT b'1',
  `note` varchar(250) DEFAULT '',
  PRIMARY KEY (`role_id`,`account_id`),
  KEY `account_grant` (`account_id`),
  CONSTRAINT `account_grant` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_grant` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week01.grant_access: ~11 rows (approximately)
INSERT INTO `grant_access` (`role_id`, `account_id`, `is_grant`, `note`) VALUES
	('admin', '1731334157', b'1', ''),
	('admin', 'admin', b'1', ''),
	('doctor', '1731332830', b'1', ''),
	('doctor', '1731333474', b'1', ''),
	('doctor', '1731333510', b'1', ''),
	('patient', '1731332826', b'1', ''),
	('patient', '1731332841', b'1', ''),
	('patient', 'user', b'1', ''),
	('user', '1731332826', b'1', NULL),
	('user', '1731332830', b'1', NULL),
	('user', '1731332841', b'1', NULL),
	('user', '1731333123', b'1', ''),
	('user', '1731333466', b'1', ''),
	('user', '1731333474', b'1', NULL),
	('user', '1731333510', b'1', NULL),
	('user', '1731334189', b'1', ''),
	('user', 'user', b'1', NULL);

-- Dumping structure for table week01.log
CREATE TABLE IF NOT EXISTS `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(50) NOT NULL,
  `login_time` datetime DEFAULT current_timestamp(),
  `logout_time` datetime DEFAULT current_timestamp(),
  `notes` varchar(250) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='ghi logs';

-- Dumping data for table week01.log: ~3 rows (approximately)
INSERT INTO `log` (`id`, `account_id`, `login_time`, `logout_time`, `notes`) VALUES
	(37, 'admin', '2024-11-11 21:24:31', NULL, ''),
	(38, '1731334157', '2024-11-11 14:11:00', '2024-11-11 21:11:02', ''),
	(39, '1731333466', '2024-11-11 14:11:07', '2024-11-11 21:11:18', '');

-- Dumping structure for table week01.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week01.role: ~4 rows (approximately)
INSERT INTO `role` (`role_id`, `role_name`, `description`, `status`) VALUES
	('admin', 'administrator', 'admin role', 1),
	('doctor', 'doctor', 'doctor', 1),
	('patient', 'patient', 'patient', 1),
	('user', 'user', 'user role', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
