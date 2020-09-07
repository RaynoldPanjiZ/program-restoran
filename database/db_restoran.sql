-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 07, 2020 at 03:58 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_restoran`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_karyawan`
--

CREATE TABLE `tbl_karyawan` (
  `ID_KARYAWAN` varchar(10) NOT NULL,
  `ID_LEVEL` int(2) NOT NULL,
  `NAMA_USER` varchar(30) DEFAULT NULL,
  `JK` char(1) DEFAULT NULL,
  `NOPE` varchar(13) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_karyawan`
--

INSERT INTO `tbl_karyawan` (`ID_KARYAWAN`, `ID_LEVEL`, `NAMA_USER`, `JK`, `NOPE`, `USERNAME`, `PASSWORD`) VALUES
('USER0001', 1, 'ASEP', 'L', '08922783978', 'ADMIN', 'ADMIN'),
('USER0002', 3, 'BUDI', 'L', '058662315965', 'GUDANG', 'GUDANG'),
('USER0003', 2, 'SINTA', 'P', '086632145965', 'KASIR', 'KASIR'),
('USER0004', 2, 'YULIA', 'P', '085663215789', 'KaSIR', 'KASiR'),
('USER0005', 1, 'Ray', 'L', '0808080', 'ray', 'ray'),
('USER0006', 1, 'Raynold', 'L', '009787', 'admin2', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_level`
--

CREATE TABLE `tbl_level` (
  `ID_LEVEL` int(2) NOT NULL,
  `LEVEL` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_level`
--

INSERT INTO `tbl_level` (`ID_LEVEL`, `LEVEL`) VALUES
(1, 'ADMIN'),
(2, 'KASIR'),
(3, 'GUDANG');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_menu`
--

CREATE TABLE `tbl_menu` (
  `ID_PAKET` varchar(10) NOT NULL,
  `NAMA_PAKET` varchar(32) NOT NULL,
  `KETERANGAN` text NOT NULL,
  `HARGA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_menu`
--

INSERT INTO `tbl_menu` (`ID_PAKET`, `NAMA_PAKET`, `KETERANGAN`, `HARGA`) VALUES
('P001', 'Super Besar', 'Ayam, Nasi, kentang, Pepsi', 35000),
('P002', 'Jumbo Burger', 'Burger, cola', 30000),
('P003', 'Paket Hemat 1', 'Ayam, Nasi, es teh manis', 25000),
('P004', 'Paket Hemat 2', 'Ayam, Nasi, es teh tawar', 25000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `ID_TRANSAKSI` int(11) NOT NULL,
  `ID_PAKET` varchar(10) NOT NULL,
  `ID_KARYAWAN` varchar(10) NOT NULL,
  `JUMLAH` int(11) NOT NULL,
  `TOTAL_HARGA` int(11) NOT NULL,
  `TANGGAL` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`ID_TRANSAKSI`, `ID_PAKET`, `ID_KARYAWAN`, `JUMLAH`, `TOTAL_HARGA`, `TANGGAL`) VALUES
(1, 'P001', 'USER0003', 1, 35000, '2020-07-29'),
(2, 'P001', 'USER0003', 1, 35000, '2020-07-29'),
(3, 'P002', 'USER0003', 3, 90000, '2020-07-29'),
(4, 'P002', 'USER0003', 1, 30000, '2020-07-29');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_karyawan`
--
ALTER TABLE `tbl_karyawan`
  ADD PRIMARY KEY (`ID_KARYAWAN`),
  ADD KEY `ID_LEVEL` (`ID_LEVEL`);

--
-- Indexes for table `tbl_level`
--
ALTER TABLE `tbl_level`
  ADD PRIMARY KEY (`ID_LEVEL`);

--
-- Indexes for table `tbl_menu`
--
ALTER TABLE `tbl_menu`
  ADD PRIMARY KEY (`ID_PAKET`);

--
-- Indexes for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`ID_TRANSAKSI`),
  ADD KEY `ID_PAKET` (`ID_PAKET`),
  ADD KEY `ID_KARYAWAN` (`ID_KARYAWAN`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_karyawan`
--
ALTER TABLE `tbl_karyawan`
  ADD CONSTRAINT `tbl_karyawan_ibfk_1` FOREIGN KEY (`ID_LEVEL`) REFERENCES `tbl_level` (`ID_LEVEL`);

--
-- Constraints for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD CONSTRAINT `tbl_transaksi_ibfk_1` FOREIGN KEY (`ID_PAKET`) REFERENCES `tbl_menu` (`id_paket`),
  ADD CONSTRAINT `tbl_transaksi_ibfk_2` FOREIGN KEY (`ID_KARYAWAN`) REFERENCES `tbl_karyawan` (`ID_KARYAWAN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
