-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.27-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para componentesmarey
CREATE DATABASE IF NOT EXISTS `componentesmarey` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `componentesmarey`;

-- Volcando estructura para tabla componentesmarey.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `Nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`Nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla componentesmarey.categoria: ~5 rows (aproximadamente)
REPLACE INTO `categoria` (`Nombre`) VALUES
	('Ordenadores'),
	('Placas base'),
	('Portátiles'),
	('Procesadores'),
	('Tarjetas gráficas');

-- Volcando estructura para tabla componentesmarey.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `Email` varchar(255) NOT NULL,
  `Contraseña` varchar(255) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Apellidos` varchar(255) NOT NULL,
  `Teléfono` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla componentesmarey.cliente: ~5 rows (aproximadamente)
REPLACE INTO `cliente` (`Email`, `Contraseña`, `Nombre`, `Apellidos`, `Teléfono`) VALUES
	('alba@outlook.com', 'clave456', 'Alba', 'Ontalba', '987654321'),
	('laura@hotmail.es', 'securepass', 'Laura', 'López', '111222333'),
	('miguel@gmail.com', 'secreto', 'Miguel', 'Rodríguez', '444333222'),
	('pedro@gmail.com', 'mipass', 'Pedro', 'Sánchez', '555555555'),
	('ruben@gmail.com', 'contraseña123', 'Ruben', 'Diaz', '123456789');

-- Volcando estructura para tabla componentesmarey.direccionesenvio
CREATE TABLE IF NOT EXISTS `direccionesenvio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Dirección1` varchar(255) NOT NULL,
  `Direccion2` varchar(255) DEFAULT NULL,
  `Ciudad` varchar(255) NOT NULL,
  `CodigoPostal` int(5) NOT NULL,
  `Email_Cliente` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Email_Cliente` (`Email_Cliente`),
  CONSTRAINT `direccionesenvio_ibfk_1` FOREIGN KEY (`Email_Cliente`) REFERENCES `cliente` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla componentesmarey.direccionesenvio: ~5 rows (aproximadamente)
REPLACE INTO `direccionesenvio` (`ID`, `Dirección1`, `Direccion2`, `Ciudad`, `CodigoPostal`, `Email_Cliente`) VALUES
	(1, 'Gran Via 58', NULL, 'Madrid', 28013, 'ruben@gmail.com'),
	(2, 'Calle Mayor 18', NULL, 'Griñon', 28971, 'alba@outlook.com'),
	(3, 'Calle Reus 18', NULL, 'Valencia', 46009, 'pedro@gmail.com'),
	(4, 'Avenida Ramón y Cajal, 5', NULL, 'Sevilla', 41018, 'laura@hotmail.es'),
	(5, 'Avinguda Diagonal 216', NULL, 'Barcelona', 8018, 'miguel@gmail.com');

-- Volcando estructura para tabla componentesmarey.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EstadoPedido` varchar(50) DEFAULT NULL,
  `FechaPedido` varchar(50) DEFAULT NULL,
  `Email_Cliente` varchar(255) DEFAULT NULL,
  `ID_Direccion` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Email_Cliente` (`Email_Cliente`),
  KEY `ID_Direccion` (`ID_Direccion`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`Email_Cliente`) REFERENCES `cliente` (`Email`),
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`ID_Direccion`) REFERENCES `direccionesenvio` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla componentesmarey.pedido: ~6 rows (aproximadamente)
REPLACE INTO `pedido` (`ID`, `EstadoPedido`, `FechaPedido`, `Email_Cliente`, `ID_Direccion`) VALUES
	(1, 'En proceso', '2023-09-26', 'ruben@gmail.com', 1),
	(2, 'Entregado', '2023-09-25', 'alba@outlook.com', 2),
	(3, 'Pendiente', '2023-09-24', 'pedro@gmail.com', 3),
	(4, 'En proceso', '2023-09-23', 'laura@hotmail.es', 4),
	(5, 'Entregado', '2023-09-22', 'miguel@gmail.com', 5),
	(10, 'Pendiente', '2024-02-05', 'alba@outlook.com', 2);

-- Volcando estructura para tabla componentesmarey.pedidoproducto
CREATE TABLE IF NOT EXISTS `pedidoproducto` (
  `ID_Pedido` int(11) NOT NULL,
  `Codigo_Producto` int(11) NOT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Pedido`,`Codigo_Producto`),
  KEY `Codigo_Producto` (`Codigo_Producto`),
  CONSTRAINT `pedidoproducto_ibfk_1` FOREIGN KEY (`ID_Pedido`) REFERENCES `pedido` (`ID`),
  CONSTRAINT `pedidoproducto_ibfk_2` FOREIGN KEY (`Codigo_Producto`) REFERENCES `producto` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla componentesmarey.pedidoproducto: ~8 rows (aproximadamente)
REPLACE INTO `pedidoproducto` (`ID_Pedido`, `Codigo_Producto`, `Cantidad`) VALUES
	(1, 1, 2),
	(1, 2, 1),
	(2, 3, 1),
	(3, 4, 3),
	(4, 5, 2),
	(5, 2, 2),
	(10, 1, 10),
	(10, 2, 10);

-- Volcando estructura para tabla componentesmarey.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `Codigo` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) NOT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Marca` varchar(255) DEFAULT NULL,
  `Descripción` varchar(255) DEFAULT NULL,
  `CantidadEnStock` int(11) NOT NULL,
  `Categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `Categoria` (`Categoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`Categoria`) REFERENCES `categoria` (`Nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla componentesmarey.producto: ~5 rows (aproximadamente)
REPLACE INTO `producto` (`Codigo`, `Nombre`, `Precio`, `Marca`, `Descripción`, `CantidadEnStock`, `Categoria`) VALUES
	(1, 'Placa base ASUS Z590', 199.99, 'ASUS', 'Placa base para Intel 10th y 11th Gen', 170, 'Placas base'),
	(2, 'Procesador Intel i9-12900K', 499.99, 'Intel', 'Procesador de 12 núcleos y 24 hilos', 10, 'Procesadores'),
	(3, 'PC de escritorio HP EliteDesk 800 G7', 899.99, 'HP', 'PC empresarial con procesador Intel Core i7', 20, 'Ordenadores'),
	(4, 'Portátil Dell XPS 13', 1299.99, 'Dell', 'Portátil ultraligero con pantalla táctil', 15, 'Portátiles'),
	(5, 'Tarjeta gráfica NVIDIA RTX 3080', 799.99, 'NVIDIA', 'Tarjeta gráfica de alto rendimiento', 40, 'Tarjetas gráficas');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
