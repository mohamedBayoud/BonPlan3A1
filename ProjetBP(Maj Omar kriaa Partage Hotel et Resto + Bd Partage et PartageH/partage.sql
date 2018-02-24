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
-- Structure de la table `partage`
--

CREATE TABLE `partage` (
  `idPartage` int(11) NOT NULL,
  `commentaireAvis` varchar(255) NOT NULL,
  `NoteCuisine` int(11) NOT NULL,
  `NoteRapport` int(11) NOT NULL,
  `NoteService` int(11) NOT NULL,
  `NoteAmbiance` int(11) NOT NULL,
  `DateCommentaire` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `partage`
--

INSERT INTO `partage` (`idPartage`, `commentaireAvis`, `NoteCuisine`, `NoteRapport`, `NoteService`, `NoteAmbiance`, `DateCommentaire`) VALUES
(1, 'Bonsoir,j\'admire beacuoup votre hotel', 4, 4, 4, 4, '2018-02-20'),
(3, 'Hotel parfait', 4, 4, 4, 4, '2018-02-21'),
(4, 'hi', 3, 0, 3, 3, '2018-02-21'),
(5, 'J\'admire bcq votre hotel', 5, 4, 3, 3, '2018-02-21');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `partage`
--
ALTER TABLE `partage`
  ADD PRIMARY KEY (`idPartage`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `partage`
--
ALTER TABLE `partage`
  MODIFY `idPartage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
