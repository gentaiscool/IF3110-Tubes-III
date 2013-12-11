-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 11, 2013 at 04:34 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `chintalian`
--
CREATE DATABASE IF NOT EXISTS `chintalian` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `chintalian`;

-- --------------------------------------------------------

--
-- Table structure for table `inventori`
--

CREATE TABLE IF NOT EXISTS `inventori` (
  `id_inventori` int(10) NOT NULL AUTO_INCREMENT,
  `id_kategori` int(10) NOT NULL,
  `nama_inventori` varchar(100) NOT NULL,
  `jumlah` int(100) NOT NULL,
  `gambar` varchar(200) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `harga` int(10) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id_inventori`),
  KEY `id_kategori` (`id_kategori`),
  KEY `id_kategori_2` (`id_kategori`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

--
-- Dumping data for table `inventori`
--

INSERT INTO `inventori` (`id_inventori`, `id_kategori`, `nama_inventori`, `jumlah`, `gambar`, `description`, `harga`, `count`) VALUES
(1, 1, 'Salamisssa', 920991, 'salami.jpg', 'aaaaa', 101, 0),
(2, 1, 'Mortadella', 94, 'mortadella.jpg', 'Mortadella is a large Italian sausage or cold cut made of finely hashed or ground, heat-cured pork sausage, which incorporates at least 15% small cubes of pork fat (principally the hard fat from the neck of the pig).', 9, 0),
(5, 1, 'Panino', 100, 'panino.jpg', 'Panino is the word for a sandwich made from bread other than sliced bread.', 9, 0),
(6, 1, 'Bruschetta', 100, 'bruschetta.jpg', 'Bruschetta is an antipasto from Italy consisting of grilled bread rubbed with garlic and topped with olive oil, salt and pepper.', 9, 0),
(7, 1, 'Tramezzino', 100, 'tramezzino.jpg', 'Tramezzino is a usually triangular sandwich constructed from two slices of soft white bread with the crusts removed.', 9, 0),
(8, 1, 'Crostino', 100, 'crostino.jpg', 'Crostino is an Italian appetizer consisting of a small slice of grilled or toasted bread and toppings.', 9, 0),
(9, 1, 'Prawn cocktail', 100, 'prawncocktail.jpg', 'Prawn cocktail, also known as shrimp cocktail, is a seafood dish consisting of "shelled prawns in Marie Rose sauce, served in a glass".', 9, 0),
(10, 2, 'Pizza Napoletanas', 96, 'napoletana.jpg', '', 9, 0),
(11, 2, 'Mexican pizza', 100, 'mexican.jpg', 'Mexican pizza is a pizza made with ingredients typical of Mexican cuisine.', 9, 0),
(12, 2, 'Brazilian pizza', 100, 'brazilian.jpg', 'Brazilian pizza tends to have less tomato sauce than the Italian version, or uses slices of tomato in place of sauce.', 9, 0),
(13, 2, 'Swedish pizza', 100, 'swedish.jpg', 'Swedish pizza is mainly of the Neapolitan type and most pizzerias in Sweden have pizzas Margherita, Capricciosa and Quattro Stagioni at the top of the menu, although with altered recipes.', 9, 0),
(14, 2, 'Frozen pizza', 100, 'frozen.jpg', 'Pizza is available frozen, as round traditional pizzas or in portion-size pieces.', 9, 0),
(15, 3, 'Bigoli', 100, 'bigoli.jpg', 'Thick tubes, often made of buckwheat or wholewheat flour.', 9, 0),
(16, 3, 'Bucatini', 100, 'bucatini.jpg', 'A thick spaghetti-like pasta with a hole running through the center.', 9, 0),
(17, 3, 'Capellini', 100, 'capellini.jpg', 'The thinnest type of long pasta.', 9, 0),
(18, 3, 'Fusilli', 100, 'fusilli.jpg', 'Long, thick, corkscrew shaped pasta that may be solid or hollow. Hollow fusilli are also called fusilli bucati. Pictured is fusilli in a pesto sauce.', 9, 0),
(19, 3, 'Pici', 100, 'pici.jpg', 'Very thick, long, hand rolled pasta.', 9, 0),
(20, 3, 'Spaghetti', 100, 'spaghetti.jpg', 'A long, thin, cylindrical pasta of Italian origin.', 9, 0),
(21, 3, 'Spaghettini', 100, 'spaghettini.jpg', 'Thin spaghetti.', 9, 0),
(22, 3, 'Spaghettoni', 100, 'spaghettoni.jpg', 'A spaghetti that is extra thick or extra long.', 9, 0),
(23, 3, 'Ziti', 100, 'ziti.jpg', 'Long, narrow hose-like tubes sized smaller than rigatoni but larger than mezzani.', 9, 0),
(24, 3, 'Bavette', 100, 'bavette.jpg', 'Narrower version of tagliatelle.', 9, 0),
(25, 3, 'Fettuccine', 100, 'fettuccine.jpg', 'Ribbon of pasta approximately 6.5 millimeters wide.', 9, 0),
(26, 3, 'Lasagne', 100, 'lasagne.jpg', 'Very wide pasta that often have fluted edges.', 9, 0),
(27, 3, 'Lasagnette', 100, 'lasagnette.jpg', 'Narrower version of lasagne.', 9, 0),
(28, 3, 'Pizzoccheri', 100, 'pizzoccheri.jpg', 'A type of short tagliatelle, a flat ribbon pasta, made with 80% buckwheat flour and 20% wheat flour.', 9, 0),
(29, 3, 'Stringozzi', 100, 'stringozzi.jpg', 'Similar to shoelaces.', 9, 0),
(30, 3, 'Tagliatelle', 100, 'tagliatelle.jpg', 'Ribbon, generally narrower than fettuccine.', 9, 0),
(31, 3, 'Trenette', 100, 'trenette.jpg', 'Thin ribbon ridged on one side.', 9, 0),
(32, 4, 'Tiramisu', 100, 'tiramisu.jpg', 'Tiramisu is a popular coffee-flavoured Italian dessert.', 9, 0),
(33, 4, 'Zuppa Inglese', 100, 'zuppainglese.jpg', 'Zuppa Inglese is an Italian custard-based dessert.', 9, 0),
(34, 4, 'Panna cotta', 100, 'pannacotta.jpg', 'Panna cotta is an Italian dessert made by simmering together cream, milk and sugar, mixing this with gelatin, and letting it cool until set.', 9, 0),
(35, 4, 'Panettone', 100, 'panettone.jpg', 'Panettone is a type of sweet bread loaf originally from Milan usually prepared and enjoyed for Christmas and New Year in Italy, southeastern France, Portugal, Brazil, Peru, Malta, Germany and Switzerland, and is one of the symbols of the city of Milan.', 9, 0),
(36, 4, 'Pandoro', 100, 'pandoro.jpg', 'Pandoro is a traditional Italian sweet yeast bread, most popular around Christmas and New Year.', 9, 0),
(37, 4, 'Gelato', 100, 'gelato.jpg', 'Gelato is the Italian word for ice cream.', 9, 0),
(38, 4, 'Sorbet', 100, 'sorbet.jpg', 'Sorbet is a frozen dessert made from sweetened water flavoured with fruit (typically juice or puree), wine, and/or liqueur.', 9, 0),
(42, 5, 'Aperol', 100, 'aperol.jpg', 'Aperol is an Italian aperitif originally produced by the Barbieri company, based in Padua.', 9, 0),
(44, 5, 'Wine', 100, 'wine.jpg', 'Wine is an alcoholic beverage made from fermented grapes or other fruits.', 9, 0),
(45, 5, 'Champagne', 100, 'champagne.jpg', 'Champagne is a sparkling wine produced from grapes grown in the Champagne region of France following rules that demand secondary fermentation of the wine in the bottle to create carbonation.', 9, 0),
(46, 5, 'Grappa', 100, 'grappa.jpg', 'Grappa is an alcoholic beverage, a fragrant, grape-based pomace brandy of Italian origin that contains 35%?60% alcohol by volume (70 to 120 US proof).', 9, 0),
(47, 5, 'Amaro', 100, 'amaro.jpg', 'Amaro is an Italian herbal liqueur that is commonly drunk as an after-dinner digestif.', 9, 0),
(48, 5, 'Limoncello', 100, 'limoncello.jpg', 'Limoncello is an Italian lemon liqueur mainly produced in Southern Italy.', 9, 0),
(49, 5, 'Espresso', 100, 'espresso.jpg', 'Espresso is coffee brewed by forcing a small amount of nearly boiling water under pressure through finely ground coffee beans.', 9, 0),
(50, 1, 'assss', 22121, 'asasas', 'asasas', 1111, 0);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `id_kategori` int(10) NOT NULL AUTO_INCREMENT,
  `nama_kategori` varchar(100) NOT NULL,
  PRIMARY KEY (`id_kategori`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
(1, 'Appetizer'),
(2, 'Pizza'),
(3, 'Pasta'),
(4, 'Desserts'),
(5, 'Beverages');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE IF NOT EXISTS `pengguna` (
  `id_pengguna` int(10) NOT NULL AUTO_INCREMENT,
  `nama_pengguna` varchar(100) NOT NULL,
  `role` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nomor_hp` varchar(50) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `provinsi` varchar(100) NOT NULL,
  `kota_kabupaten` varchar(100) NOT NULL,
  `kode_pos` varchar(10) NOT NULL,
  `total_transaksi` int(11) NOT NULL,
  `nomor_credit_card` varchar(15) NOT NULL,
  `expired_date` varchar(10) NOT NULL,
  `nama_on_card` varchar(50) NOT NULL,
  PRIMARY KEY (`id_pengguna`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `role`, `username`, `password`, `email`, `nomor_hp`, `alamat`, `provinsi`, `kota_kabupaten`, `kode_pos`, `total_transaksi`, `nomor_credit_card`, `expired_date`, `nama_on_card`) VALUES
(1, 'Genta Winataaaaaaaa', 0, 'genta', 'gentaiscool', 'genta@genta.com', '121212', 'Gaaaaaaaaaaaaaaaaaaaaaaa', 'aaaaaa', 'Jua', '12345', 5, '12121212121', '2013-12-02', 'asas asa'),
(2, 'Ryan Ignatius', 0, 'ryanignatius', 'ryaniscool', 'rikeren@keren.com', '08122222222', 'di Bandung', 'di Daerah', 'KOta', '12134', 0, '', '', ''),
(3, 'admin admin', 1, 'admin', 'adminadmin', 'admin@admin.com', '0', 'a', 'aaaa', 'a', 'a', 0, '', '', ''),
(4, 'haha haha', 0, 'gentax', 'hahahaha', 'haha@haha.com', '8', 'a', 'a', 'a', '1', 0, '', '', ''),
(5, 'haahahah aaaa', 0, 'asasasqwqw', 'asasasqw', 'asasa@jsajisja.com', '212122', 'ASA', 'as', 'asasa', '121212', 0, '', '00-00-00', 'dummy'),
(6, 'haahahah aaaaa', 0, 'asasasqwqwa', 'asasasqwqw', 'asasa@jsajisja.co', '212122', 'ASA', 'as', 'asasa', '121212', 0, '', '00-00-00', 'dummy'),
(7, 'aa aa', 0, 'gentaaaaaaz', 'aaaaaaaaaaa', 'asasa@jsajisja.coa', '1111', 'aaaaa', 'aa', 'aa', '1212', 0, '', '00-00-00', 'dummy');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `id_transaksi` int(10) NOT NULL AUTO_INCREMENT,
  `id_pengguna` int(10) NOT NULL,
  `id_inventori` int(10) NOT NULL,
  `jumlah` int(100) NOT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `id_pengguna` (`id_pengguna`),
  KEY `id_inventori` (`id_inventori`),
  KEY `id_pengguna_2` (`id_pengguna`),
  KEY `id_inventori_2` (`id_inventori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventori`
--
ALTER TABLE `inventori`
  ADD CONSTRAINT `inventori_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_inventori`) REFERENCES `inventori` (`id_inventori`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
