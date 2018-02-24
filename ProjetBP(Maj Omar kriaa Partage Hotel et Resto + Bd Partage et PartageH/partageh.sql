-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Sam 24 Février 2018 à 16:31
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bonplan`
--

-- --------------------------------------------------------

--
-- Structure de la table `partageh`
--

CREATE TABLE `partageh` (
  `idPartageH` int(11) NOT NULL,
  `commentaireAvisH` varchar(255) NOT NULL,
  `NoteServiceH` int(11) NOT NULL,
  `NoteRapportH` int(11) NOT NULL,
  `NoteConfortH` int(11) NOT NULL,
  `NotePersonnelH` int(11) NOT NULL,
  `DateCommentaireH` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `partageh`
--

INSERT INTO `partageh` (`idPartageH`, `commentaireAvisH`, `NoteServiceH`, `NoteRapportH`, `NoteConfortH`, `NotePersonnelH`, `DateCommentaireH`) VALUES
(4, 'j\'adoooooooooorrre', 2, 2, 4, 1, '2018-02-24'),
(3, 'trop bien', 1, 1, 1, 1, '2018-02-24');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `partageh`
--
ALTER TABLE `partageh`
  ADD PRIMARY KEY (`idPartageH`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `partageh`
--
ALTER TABLE `partageh`
  MODIFY `idPartageH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
