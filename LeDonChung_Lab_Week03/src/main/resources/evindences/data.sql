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


-- Dumping database structure for week03
CREATE DATABASE IF NOT EXISTS `week03` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `week03`;

-- Dumping structure for table week03.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(250) DEFAULT NULL,
  `img_path` varchar(250) DEFAULT NULL,
  `manufacturer_name` varchar(100) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `unit` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week03.products: ~6 rows (approximately)
INSERT INTO `products` (`product_id`, `description`, `img_path`, `manufacturer_name`, `name`, `unit`) VALUES
	(8, NULL, 'https://cdnv2.tgdd.vn/mwg-static/tgdd/Products/Images/42/303825/iphone-15-plus-black-2-638629457762670263-750x500.jpg', 'Apple', 'iPhone 15 Plus 512GB', 'chiec'),
	(9, NULL, 'https://cdn.tgdd.vn/Products/Images/10618/238092/thiet-bi-dinh-vi-thong-minh-airtag-1-pack-mx532-2-750x500.jpg', 'Apple', 'AirTag 1', 'chiec'),
	(10, NULL, 'https://cdn.tgdd.vn/Products/Images/1363/314735/mieng-dan-kinh-cuong-luc-iphone-15-plus-jcpal-1-750x500.jpg', 'Apple', 'Cuong Luc iPhone 15 Plus JCPAL', 'chiec'),
	(11, NULL, 'https://cdn.tgdd.vn/Products/Images/54/327554/tai-nghe-bluetooth-true-wireless-samsung-galaxy-buds-3-pro-r630n-xam-1-750x500.jpg', 'SamSung', 'TWS Samsung Galaxy Buds3 Pro R630N', 'Chiec'),
	(12, NULL, 'https://cdn.tgdd.vn/Products/Images/1902/329142/o-cung-ssd-1tb-samsung-portable-t9-mu-pg1t0b-ww-1-750x500.jpg', 'Samsung', 'SSD 1TB Samsung Portable T9 MU-PG1T0B/WW', 'Cai'),
	(13, NULL, 'https://cdn.tgdd.vn/Products/Images/42/329938/xiaomi-14t-grey-thumb-600x600.jpg', 'Xiaomi', 'Xiaomi 14T Grey', 'Chiec');

-- Dumping structure for table week03.product_prices
CREATE TABLE IF NOT EXISTS `product_prices` (
  `apply_date` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `price_date_time` datetime NOT NULL DEFAULT current_timestamp(),
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`price_date_time`,`product_id`),
  KEY `FK_product_prices_product_id` (`product_id`),
  CONSTRAINT `FK_product_prices_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table week03.product_prices: ~14 rows (approximately)
INSERT INTO `product_prices` (`apply_date`, `note`, `STATUS`, `price`, `price_date_time`, `product_id`) VALUES
	('2024-10-04 16:56:02', NULL, 0, 25000000, '2024-10-04 16:56:02', 8),
	('2024-10-04 16:56:33', NULL, 0, 800000, '2024-10-04 16:56:33', 9),
	('2024-10-04 16:57:07', NULL, 0, 200000, '2024-10-04 16:57:07', 10),
	('2024-10-04 16:57:47', NULL, 1, 3200000, '2024-10-04 16:57:47', 11),
	('2024-10-04 16:58:26', NULL, 1, 4500000, '2024-10-04 16:58:26', 12),
	('2024-10-04 17:00:04', NULL, 1, 1200000, '2024-10-04 17:00:04', 13),
	('2024-11-12 01:22:17', NULL, 0, 35000000, '2024-11-12 01:22:17', 8),
	('2024-11-12 01:22:22', NULL, 0, 25000000, '2024-11-12 01:22:22', 8),
	('2024-11-12 01:22:51', NULL, 0, 45000000, '2024-11-12 01:22:51', 8),
	('2024-11-12 01:24:39', NULL, 0, 45000000, '2024-11-12 01:24:39', 8),
	('2024-11-12 01:25:06', NULL, 0, 900000, '2024-11-12 01:25:06', 9),
	('2024-11-12 01:25:09', NULL, 0, 35000000, '2024-11-12 01:25:09', 8),
	('2024-11-12 01:25:13', NULL, 0, 25000000, '2024-11-12 01:25:13', 8),
	('2024-11-12 01:26:28', NULL, 1, 200000, '2024-11-12 01:26:28', 10),
	('2024-11-12 01:29:15', NULL, 0, 65000000, '2024-11-12 01:29:15', 8),
	('2024-11-12 01:29:53', NULL, 1, 100000, '2024-11-12 01:29:53', 9),
	('2024-11-12 01:30:17', NULL, 0, 45000000, '2024-11-12 01:30:17', 8),
	('2024-11-12 01:31:52', NULL, 1, 15000000, '2024-11-12 01:31:52', 8);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
