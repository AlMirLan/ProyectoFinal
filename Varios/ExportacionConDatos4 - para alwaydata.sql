-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.7.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.10.0.7000
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para proyecto_db
USE `alejandroml_proyecto_db`;

-- Volcando estructura para tabla proyecto_db.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_banned_time` datetime(6) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- Volcando datos para la tabla proyecto_db.user: ~14 rows (aproximadamente)
INSERT INTO `user` (`user_id`, `user_banned_time`, `user_email`, `user_name`, `user_pass`) VALUES
	(1, NULL, 'admin@admin.ad', 'Admin', '$2a$10$YS48i/p6kGODkLj5/zxc/.7BVs.ppIm21pFj6hyFrVBkysVP6fmWi'),
	(2, NULL, 'prueba1@prueba.pr', 'Alejandro', '$2a$10$dQrkih4ucJeGSDc5EeZeRuyGVO3b0zMti7NGFrKIvxCCXspszbQW2'),
	(3, NULL, 'prueba2@prueba.pr', 'Manoli', '$2a$10$9gbd8fzx3SIG.15lsjjWy.uka88Azcr5IvoUPVwzmEjfdqAgO/wEW'),
	(4, NULL, 'prueba3@prueba.pr', 'Nina', '$2a$10$eEcrlxFGNSbkqYvGYl8XSOgdjbEuW3KxzhCnCwOltboWUnMl15mFy'),
	(5, NULL, 'prueba4@prueba.pr', 'Maria', '$2a$10$LE.sHjzoo9No0JyHLioso.qVyfvLb8huei3Zypw99.7o1LbhwFqNy'),
	(6, NULL, 'prueba5@prueba.pr', 'Eduardo', '$2a$10$rHel7.xxXrtanFJbxCtgBefqwW84hdTO7dZVvWm9bICe1MV6xdPwS'),
	(7, NULL, 'prueba6@prueba.pr', 'Juani', '$2a$10$e92ZjIz.zOPOGerVOO7Owu7p/Z2.b6HgHwLweBjRbyKfPEsoe0LV2'),
	(8, NULL, 'prueba7@prueba.pr', 'Luna', '$2a$10$rB2KW9N64dWcfK5cfS1zXOe6roEscMgWtMK4wJD7L1Pv1lQMWRZC6'),
	(9, NULL, 'prueba8@prueba.pr', 'Domingo', '$2a$10$Z/Q7vjUGZiVPrwoWgnBiBuD7ckIhl2VGok5izRSLVAboUPzMxdr6y'),
	(10, NULL, 'prueba9@prueba.pr', 'Francisco Javier', '$2a$10$sA6Zi3CpA.0G.rp33nJMVOFAV1kK1yNFbv.4bzGfGm4jPIKK0ZHD2'),
	(11, NULL, 'prueba10@prueba.pr', 'Isabel', '$2a$10$28SLtLuPJDtmoaWLPNK.xuBW8t3RwygXYFLi7DmqHDht1uExvGwWi'),
	(12, NULL, 'prueba11@prueba.pr', 'Cristo', '$2a$10$kTju9lk53PVfD7w6x4PlAOSdj44FYM.0EaLyg/E6LVbhaZ0WyjWI.'),
	(13, NULL, 'prueba12@prueba.pr', 'Mario', '$2a$10$E1uK7HYASzv4LmIyeFwQ0uzF54QAEaGTmM5/BZcoJPK9fNYImhOiC'),
	(14, NULL, 'prueba13@prueba.pr', 'Carlos', '$2a$10$Myzoo3RCLTDC28A2u0/0YO38AGiV0ChMP3paoA1nel.B9pasJAg9a'),
	(15, '2025-04-09 15:33:02.507991', 'prueba14@prueba.pr', 'Tasio', '$2a$10$rrMIwW/zxCQY2s5kuCYO5.efDJbFMPy/pEwN0TCNBkrmeGo7D4XyO');

-- Volcando estructura para tabla proyecto_db.board_game
DROP TABLE IF EXISTS `board_game`;
CREATE TABLE IF NOT EXISTS `board_game` (
  `game_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `average_rating` double DEFAULT NULL,
  `game_category` varchar(255) DEFAULT NULL,
  `game_description` varchar(1200) CHARACTER SET utf8mb3 COLLATE utf8mb3_uca1400_ai_ci DEFAULT NULL,
  `game_image` varchar(255) DEFAULT NULL,
  `game_name` varchar(255) DEFAULT NULL,
  `report_count` int(11) DEFAULT NULL,
  `rewiew_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla proyecto_db.board_game: ~4 rows (aproximadamente)
INSERT INTO `board_game` (`game_id`, `average_rating`, `game_category`, `game_description`, `game_image`, `game_name`, `report_count`, `rewiew_count`) VALUES
	(1, 7.33, 'CAMPAÑA', 'Arydia: The Paths We Dare Tread es un juego de rol de mesa de mundo abierto, basado en campañas, cooperativo y de fantasía, con un legado ecológico. Arydia se basa en cuatro pilares de diseño: Exploración, Progresión, Combate y Rol.', 'http://localhost:8081/files/g_8c2ca223-8dbb-49d8-9ac1-9bfa1915c1e3.png', 'Arydia: The Paths We Dare Tread', 0, 6),
	(2, 9, 'COOPERATIVO', '¿Recuerdas cuando jugabas al Street of rage en tu megadrive? Ahora puedes vivirlo de nuevo en la mesa de tu salón. Elige tu luchador/a, elige el escenario y una banda. \r\nMuévete por el escenario cumpliendo objetivos, repartiendo toñas y cargando tu poder especial.', 'http://localhost:8081/files/g_0b4822f1-24de-4cf6-8240-cb5c472e47e8.png', 'Street Masters', 0, 6),
	(3, 1, 'COMPETITIVO', 'Juego para reportar y hacer pruebas', 'http://localhost:8081/files/g_c32e1b10-ed6a-4b62-916d-4ba340effa91.png', 'Juego malo', 3, 1),
	(4, 0, 'CARRERAS', 'Esto es una prueba porque he cambiado el script', 'http://localhost:8081/files/g_131a2321-8d79-4873-8e42-80f1718dbc57.png', 'PruebaEdit', 0, 0),
	(5, 0, 'DEDUCCIÓN', 'Esto es una prueba al cambiar el nombre de la función', 'http://localhost:8081/files/g_1eca63e2-98fd-4f78-a457-f1f585121578.png', 'Prueba2Edit', 0, 0);

-- Volcando estructura para tabla proyecto_db.project
DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project` (
  `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approval` double DEFAULT NULL,
  `project_category` varchar(255) DEFAULT NULL,
  `project_description` varchar(1200) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `project_image` varchar(255) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `votes_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla proyecto_db.project: ~11 rows (aproximadamente)
INSERT INTO `project` (`project_id`, `approval`, `project_category`, `project_description`, `author_id`, `project_image`, `project_name`, `votes_count`) VALUES
	(1, 90, 'DADOS', 'Too Many Bones es un juego cooperativo y de estrategia que te pone en la piel de una nueva raza y te lleva en una aventura a los territorios del norte para erradicar y derrotar a las crecientes fuerzas enemigas y, por supuesto, al infame "maloso" responsable. Únete en equipo o juega solo en una campaña de 1 a 4 jugadores en modo cooperativo o solitario. Con más de 100 dados de habilidades únicas y 4 a 7 clases para elegir, cada batalla es su propio mini desafío para resolver. Tu aventura consta X batallas antes de llegar a tu destino final y enfrentarte a uno de varios posibles líderes para ganar. En el camino, te enfrentarás a decisiones de la trama que rápidamente te harán sopesar el riesgo/recompensa, las probabilidades y la lógica, ¡con dados entrelazados en cada aspecto! Tu grupo también se enfrentará a otras decisiones: ¡cuándo descansar, cuándo explorar o incluso qué peleas perseguir! Las cartas de encuentro ofrecen giros divertidos de la trama y algo de alivio cómico, ¡todo mientras preparan el escenario para tu próxima batalla!', 3, 'http://localhost:8081/files1/p_fa2eecdf-2130-42f0-9755-34ee56a72523.jpg', 'Too Many Bones', 10),
	(2, 90, 'DADOS', 'Expansión independiente de Too Many Bones. Explora el sur de Daelore, acompaña a Duster y Stanza en su viaje para derrotar a los nuevos tiranos, y descubrir que se esconde detrás.', 3, 'http://localhost:8081/files1/p_71aff76d-fc39-483c-bbdf-6f15008ab007.jpg', 'Too Many Bones: Undertow', 10),
	(3, 90, 'DADOS', 'Expansión independiente de Too Many Bones.\r\nVe más allá, explora la ciudad subterránea de Daelore, averigua quien está detrás de todo después de la derrota de Nobulous.', 3, 'http://localhost:8081/files1/p_4b8c861d-04a7-4a3c-8e12-f89ba4744a94.jpg', 'Too Many Bones: Unbreakable', 10),
	(4, 90, 'DADOS', 'Hoplomachus: Victorum es el primer juego en solitario de Chip Theory Games, un sistema táctico inmersivo que es fácil de aprender y difícil de dominar. Con una amplia variedad de escenarios de batalla, sincronicidad de gladiadores y nuevos niveles de componentes premium, Hoplomachus: Victorum ha sido diseñado desde cero como la experiencia estratégica definitiva para un jugador en solitario.', 2, 'http://localhost:8081/files1/p_34249193-ce9f-40f1-b114-2edd7298e9aa.jpg', 'Hoplomachus: Victorum', 10),
	(5, 80, 'DADOS', 'Burncycle es un trepidante juego de infiltración estratégica para 1 a 4 jugadores. Ya sea jugando solo o en modo cooperativo, los jugadores deben tomar riesgos y considerar sus mejores composiciones de turnos posibles para mover un equipo de robots a través de los corredores hostiles de corporaciones opresivas dirigidas por humanos en un futuro lejano.', 2, 'http://localhost:8081/files1/p_ac17070d-7c77-4f09-b385-1e3e6b1206e2.jpg', 'Burncycle', 10),
	(6, 50, 'CARTAS', '¡LOS CINCO MINUTOS MÁS DIVERTIDOS QUE PUEDES JUGAR!\r\n¿Eres un Bárbaro? ¿Un Mago? ¿Quizá un Ladrón? ¡Cada uno de los 10 héroes posee una habilidad exclusiva para ayudar al grupo!\r\nDerrota monstruos y obstáculos jugando cartas que contengan los mismos símbolos. No hay turnos, ¡aquí todos lucháis al mismo tiempo!', 4, 'http://localhost:8081/files1/p_9d98dc34-bce1-43f9-86f2-1844966ab241.jpg', '5 Minute Dungeon', 10),
	(7, 50, 'DADOS', 'Elige un personaje, lanza los dados e intenta destruir a tus enemigos antes de que te lo hagan a ti.', 5, 'http://localhost:8081/files1/p_a4c1fc7f-25bc-4af2-8a88-c584b8779893.jpg', 'Dice Throne', 10),
	(9, 70, 'RPG', 'Spire\'s End es un juego de cartas individual y cooperativo con encuentros con enemigos, escenarios interactivos con historia y un diseño artístico de alta gama. Es una experiencia de juego única sin mucho bagaje. Configuración sencilla, reglas intuitivas y componentes mínimos. Sin DM, sin contabilidad ni hoja de personaje. Solo tú, una baraja de cartas enorme, dados y marcadores de cubo.', 6, 'http://localhost:8081/files1/p_762cfec3-79ae-4a8f-97a0-002cbe1692b7.png', 'Spire\'s End: Volume 1', 10),
	(10, 80, 'RPG', 'Acompaña a Hildegard en su travesía. ¿Conseguirás superar su historia hasta llegar a la "spire"?', 7, 'http://localhost:8081/files1/p_65c45cf9-71f1-4f8c-b1dd-510c7149d810.png', 'Spire\'s End: Hildegard', 10),
	(11, 100, 'COMPETITIVO', '¿Te has preguntado como sería jugar a la ruleta rusa pero en vez de una pistola fuese una bomba y la acciona un gato? \r\nEngaña a tus rivales, fastídialos, lucha por tu supervivencia y no dejes que la bomba te explote.', 1, 'http://localhost:8081/files1/p_95fb008b-e947-49f4-bbaf-2738f959e205.jpg', 'Exploding Kittens', 1),
	(12, 100, 'DEDUCCIÓN', 'Descubre quien está de tu lado, elimina a los rivales, se el personaje más legendario del oeste.', 11, 'http://localhost:8081/files1/p_c04532c7-5227-46e6-afa5-c3028c03e471.jpg', 'Bang!', 1);


-- Volcando estructura para tabla proyecto_db.report
DROP TABLE IF EXISTS `report`;
CREATE TABLE IF NOT EXISTS `report` (
  `game_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`game_id`,`user_id`),
  KEY `FKj62onw73yx1qnmd57tcaa9q3a` (`user_id`),
  CONSTRAINT `FKj62onw73yx1qnmd57tcaa9q3a` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKmltj2lrdy0qjya4dhujbovvlq` FOREIGN KEY (`game_id`) REFERENCES `board_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla proyecto_db.report: ~2 rows (aproximadamente)
INSERT INTO `report` (`game_id`, `user_id`, `comment`) VALUES
	(3, 1, 'Reporte 1 desde Admin. Haciendo prueba.'),
	(3, 2, 'Claramente se ha saltado el filtro y no debería estar subido.'),
	(3, 5, 'Que es esto!!?? Bórrenlo por favor');

-- Volcando estructura para tabla proyecto_db.review
DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review` (
  `game_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`game_id`,`user_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKa845cimw068803h8smhfex6ov` FOREIGN KEY (`game_id`) REFERENCES `board_game` (`game_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla proyecto_db.review: ~11 rows (aproximadamente)
INSERT INTO `review` (`game_id`, `user_id`, `comment`, `rating`) VALUES
	(1, 1, 'Me encanta! No le pongo el 10 por no estar en español.', 9),
	(1, 2, 'Juegaso. Me encanta. Me queda poco para terminarlo.', 9),
	(1, 6, 'No me gustó mucho lo que jugué. No se inglés así que no me enteré de nada.', 1),
	(1, 7, 'Me encanta. Tan detallado, figuras pintadas de fábrica. la calidad de los componentes. Menos mal que me van traduciendo mientras jugamos.', 10),
	(1, 9, 'Ok', 7),
	(1, 10, '', 8),
	(2, 1, 'Buah! Es genial. Ir por el escenario, haciendo combos y derrotando enemigos. Que recuerdos.', 8),
	(2, 4, 'Me encanta, muchas figuritas que tirar de la mesa.', 10),
	(2, 5, 'Me gusta tirar dados y reventar cosas. Encima me dan dinerito muahahaha', 9),
	(2, 7, 'Me ha gustado mucho. No me parece nada complicado. Como pega, debería traer más dados para no estar pasándolos de mano en mano.', 8),
	(2, 8, 'Opino como Nina, trae muchas cosas para tirar de la mesa, me encanta.', 10),
	(2, 9, 'No está malote.', 9),
	(3, 2, 'Esto no debería estar permitido subirse.', 1);

-- Volcando estructura para tabla proyecto_db.vote
DROP TABLE IF EXISTS `vote`;
CREATE TABLE IF NOT EXISTS `vote` (
  `project_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `posite` bit(1) DEFAULT NULL,
  PRIMARY KEY (`project_id`,`user_id`),
  KEY `FKcsaksoe2iepaj8birrmithwve` (`user_id`),
  CONSTRAINT `FKcsaksoe2iepaj8birrmithwve` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKgvxmn6n3g7nsa79x2yf5v0c84` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;


-- Volcando datos para la tabla proyecto_db.vote: ~86 rows (aproximadamente)
INSERT INTO `vote` (`project_id`, `user_id`, `posite`) VALUES
	(1, 1, b'1'),
	(1, 2, b'1'),
	(1, 3, b'1'),
	(1, 4, b'1'),
	(1, 5, b'1'),
	(1, 6, b'1'),
	(1, 7, b'1'),
	(1, 8, b'1'),
	(1, 9, b'0'),
	(1, 10, b'1'),
	(2, 1, b'1'),
	(2, 2, b'1'),
	(2, 3, b'1'),
	(2, 4, b'1'),
	(2, 5, b'1'),
	(2, 6, b'1'),
	(2, 7, b'1'),
	(2, 8, b'1'),
	(2, 9, b'0'),
	(2, 10, b'1'),
	(3, 1, b'1'),
	(3, 2, b'1'),
	(3, 3, b'1'),
	(3, 4, b'1'),
	(3, 5, b'1'),
	(3, 6, b'1'),
	(3, 7, b'1'),
	(3, 8, b'1'),
	(3, 9, b'0'),
	(3, 10, b'1'),
	(4, 1, b'1'),
	(4, 2, b'1'),
	(4, 3, b'1'),
	(4, 4, b'1'),
	(4, 5, b'1'),
	(4, 6, b'1'),
	(4, 7, b'0'),
	(4, 8, b'1'),
	(4, 9, b'1'),
	(4, 10, b'1'),
	(5, 1, b'1'),
	(5, 2, b'1'),
	(5, 3, b'1'),
	(5, 4, b'1'),
	(5, 5, b'1'),
	(5, 6, b'0'),
	(5, 7, b'1'),
	(5, 8, b'1'),
	(5, 9, b'0'),
	(5, 10, b'1'),
	(6, 1, b'0'),
	(6, 2, b'0'),
	(6, 3, b'0'),
	(6, 4, b'0'),
	(6, 5, b'1'),
	(6, 6, b'1'),
	(6, 7, b'1'),
	(6, 8, b'1'),
	(6, 9, b'1'),
	(6, 10, b'0'),
	(7, 1, b'0'),
	(7, 2, b'0'),
	(7, 3, b'0'),
	(7, 4, b'0'),
	(7, 5, b'1'),
	(7, 6, b'1'),
	(7, 7, b'1'),
	(7, 8, b'1'),
	(7, 9, b'1'),
	(7, 10, b'0'),
	(9, 1, b'1'),
	(9, 2, b'1'),
	(9, 3, b'1'),
	(9, 4, b'1'),
	(9, 5, b'1'),
	(9, 6, b'0'),
	(9, 7, b'1'),
	(9, 8, b'1'),
	(9, 9, b'0'),
	(9, 10, b'0'),
	(10, 1, b'1'),
	(10, 2, b'1'),
	(10, 3, b'1'),
	(10, 4, b'1'),
	(10, 5, b'1'),
	(10, 6, b'0'),
	(10, 7, b'1'),
	(10, 8, b'1'),
	(10, 9, b'0'),
	(10, 10, b'1'),
	(11, 1, b'1'),
	(12, 11, b'1');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
