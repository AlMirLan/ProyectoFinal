-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.6.5-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para proyecto_db
CREATE DATABASE IF NOT EXISTS `proyecto_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `proyecto_db`;

-- Volcando estructura para tabla proyecto_db.board_game
CREATE TABLE IF NOT EXISTS `board_game` (
  `game_id` bigint(20) NOT NULL,
  `average_rating` double DEFAULT NULL,
  `game_category` int(11) DEFAULT NULL,
  `game_description` varchar(255) DEFAULT NULL,
  `game_image` varchar(255) DEFAULT NULL,
  `game_name` varchar(255) DEFAULT NULL,
  `report_count` int(11) DEFAULT NULL,
  `rewiew_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_db.board_game: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `board_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_game` ENABLE KEYS */;

-- Volcando estructura para tabla proyecto_db.project
CREATE TABLE IF NOT EXISTS `project` (
  `project_id` bigint(20) NOT NULL,
  `approval` double DEFAULT NULL,
  `project_category` int(11) DEFAULT NULL,
  `proyect_description` varchar(255) DEFAULT NULL,
  `project_image` varchar(255) DEFAULT NULL,
  `proyect_name` varchar(255) DEFAULT NULL,
  `votes_count` int(11) DEFAULT NULL,
  `author_idd` bigint(20) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FKjtofqkq85gx97aeyedfp47vla` (`author_idd`),
  KEY `FKte6bms4bq1ixfhn024qtysmcg` (`author_id`),
  CONSTRAINT `FKjtofqkq85gx97aeyedfp47vla` FOREIGN KEY (`author_idd`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKte6bms4bq1ixfhn024qtysmcg` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_db.project: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;

-- Volcando estructura para tabla proyecto_db.report
CREATE TABLE IF NOT EXISTS `report` (
  `juego_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `is_reportered` bit(1) DEFAULT NULL,
  `reporte` bigint(20) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`juego_id`,`usuario_id`),
  KEY `FKgp2up7g7bh1d1lya3nfexxrsq` (`usuario_id`),
  CONSTRAINT `FK19qtqgpktjjs3o2uxvj410j4q` FOREIGN KEY (`juego_id`) REFERENCES `board_game` (`game_id`),
  CONSTRAINT `FKgp2up7g7bh1d1lya3nfexxrsq` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_db.report: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;

-- Volcando estructura para tabla proyecto_db.review
CREATE TABLE IF NOT EXISTS `review` (
  `juego_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `revieww` bigint(20) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`juego_id`,`usuario_id`),
  KEY `FKso039cs0supdxeiit2prjo1gn` (`usuario_id`),
  CONSTRAINT `FK2ipfqhrrv74bd7he3m1ywp3rx` FOREIGN KEY (`juego_id`) REFERENCES `board_game` (`game_id`),
  CONSTRAINT `FKso039cs0supdxeiit2prjo1gn` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_db.review: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;

-- Volcando estructura para tabla proyecto_db.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_banned_time` datetime(6) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_db.user: ~14 rows (aproximadamente)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `user_banned_time`, `user_email`, `user_name`, `user_pass`) VALUES
	(3, '2023-04-07 12:15:04.000000', 'g@hotmail.com', 'g', '123456'),
	(4, '2023-04-07 12:15:04.000000', 'd@hotmail.com', 'd', '123456'),
	(6, '2023-04-07 12:15:04.000000', 'n@hotmail.com', 'n', '123456'),
	(7, '2023-04-07 12:15:04.000000', 'u@hotmail.com', 'u', '123456'),
	(9, '2023-04-07 12:15:04.000000', 't@hotmail.com', 't', '123456'),
	(10, '2023-04-12 19:05:02.439000', 'hola@gmail.com', 'hola', '$2a$10$.jwknPSLTKQgxbYfCTtlkukvaAHJTDi7RJHpindNrwV74lWGRl8ty'),
	(11, NULL, 'm@hotamil.com', 'm', '$2a$10$pdw5mj.AM3pdvWKuBe0YMOURonPM3o9Qu2Su8qf9XQjkdGuV6mxVS'),
	(12, NULL, 'manoli@hotmail.com', 'manoli', '$2a$10$l1HPL9GYDI5pwGUp8Yqm7ufAtYNpC0i5A1KmtLKXudmjkq9fMAMw6'),
	(13, NULL, 'a@hotmail.com', 'a', '$2a$10$hpw4syU3DgZGZZLdRxUpfeFuhEPyMRBvNjktrDbfZEbkFi3UjrqbS'),
	(14, NULL, 'b@hotmail.com', 'b', '$2a$10$M4ZIVMIcl9KosU1rrbr1/.GbBde.oTeGlTGoeRQnDs5uy0sgV3KZu'),
	(15, NULL, 'w@hotmail.com', 'w', '$2a$10$/t4JwNFJ/R6n4DV9Yv9AqOZjPrc/15oqbFwDnQbh3aZcXVnqCZtJ6'),
	(16, NULL, 'manoli@gmail.com', 'manoli', '$2a$10$ttkbYWldTF.XbquJLCo5VOCwHdZjKOGK1zrkVZHc7tftTurku3JBm'),
	(17, NULL, 'prueba@hotmail.com', 'prueba', '$2a$10$qjvUqUI3R1h.rxgN3eQbAeSXh8SOpgxzz7nA2A4uNQ8vDAe9gMoc2'),
	(18, NULL, 'v@hot.com', 'v', '$2a$10$GXx/M5dm7zZAKDmze2uJ3uG2IYqrqTgd1LznKXdjFdiqs0DhYdw0e');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Volcando estructura para tabla proyecto_db.vote
CREATE TABLE IF NOT EXISTS `vote` (
  `proyecto_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  `posite` bit(1) DEFAULT NULL,
  `voto` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`proyecto_id`,`usuario_id`),
  KEY `FK1kgv78tjqi13tij7ljwllchqj` (`usuario_id`),
  KEY `FKgvxmn6n3g7nsa79x2yf5v0c84` (`project_id`),
  KEY `FKcsaksoe2iepaj8birrmithwve` (`user_id`),
  CONSTRAINT `FK1kgv78tjqi13tij7ljwllchqj` FOREIGN KEY (`usuario_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK3emaco59u42x98g1d46o4d9x3` FOREIGN KEY (`proyecto_id`) REFERENCES `project` (`project_id`),
  CONSTRAINT `FKcsaksoe2iepaj8birrmithwve` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKgvxmn6n3g7nsa79x2yf5v0c84` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla proyecto_db.vote: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
