-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 20 Février 2018 à 15:23
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
-- Structure de la table `bonplan`
--

CREATE TABLE `bonplan` (
  `idBonPlan` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `nbreChambreDispo` int(11) DEFAULT NULL,
  `prixNuit` int(11) DEFAULT NULL,
  `nbrePlace` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bonplan`
--

INSERT INTO `bonplan` (`idBonPlan`, `nom`, `photo`, `description`, `adresse`, `ville`, `type`, `nbreChambreDispo`, `prixNuit`, `nbrePlace`) VALUES
(37, 'uu', 'C:\\wamp64\\www\\image\\IMG_5171.JPG', 'dddd', 'ddd', 'dd', 'sejour', 25, 25, 0);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `IdPersonne` int(11) NOT NULL,
  `idEvent` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `prix` float NOT NULL,
  `nbPlace` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `valide` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `evenement`
--

INSERT INTO `evenement` (`IdPersonne`, `idEvent`, `titre`, `description`, `lieu`, `image`, `date`, `prix`, `nbPlace`, `type`, `valide`) VALUES
(7774, 158547, 'a', 'a', 'a', 'a', '2018-02-23', 1, 2, 'a', 1),
(7775, 158548, '2', '2', '2', '2', '2018-03-16', 7, 2, 'm', 1),
(7776, 158549, '!titre!', 'zsasa', 'aaaa', 'Capture.PNG', '2018-02-22', 7444, 7, 'soiree', 1),
(7776, 158550, 'zzzzz', 'zzz', 'zzz', '14.PNG', '2018-02-27', 14, 78, 'soiree', 1);

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
(12, 'omar', 7, 7, 7, 7, '2018-02-18'),
(13, 'J\'aime trop votre cuisine;Parfaite', 3, 3, 3, 3, '2018-02-18'),
(14, 'oiadoj', 7, 7, 7, 7, '2018-02-19'),
(15, 'vrerefrferfe', 2, 2, 2, 2, '2018-02-20');

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
  `idParticipation` int(11) NOT NULL,
  `idPersonne` int(11) NOT NULL,
  `idTransport` int(11) NOT NULL,
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `participation`
--

INSERT INTO `participation` (`idParticipation`, `idPersonne`, `idTransport`, `type`) VALUES
(22, 22, 445, 'train'),
(23, 22, 445, 'train'),
(24, 22, 445, 'train'),
(25, 22, 445, 'train'),
(26, 77, 12333, 'covoiturage'),
(27, 77, 12333, 'covoiturage'),
(28, 77, 445, 'train'),
(29, 77, 12333, 'covoiturage');

-- --------------------------------------------------------

--
-- Structure de la table `participerevent`
--

CREATE TABLE `participerevent` (
  `idparticipation` int(11) NOT NULL,
  `idEvent` int(11) NOT NULL,
  `idpersonne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `participerevent`
--

INSERT INTO `participerevent` (`idparticipation`, `idEvent`, `idpersonne`) VALUES
(3, 158547, 7774),
(2, 158548, 7775),
(10, 158548, 7775),
(4, 158549, 7776),
(5, 158549, 7776),
(6, 158549, 7776),
(7, 158549, 7776),
(8, 158549, 7776),
(9, 158550, 7776);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `idPersonne` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `cin` varchar(50) NOT NULL,
  `tel` int(11) NOT NULL,
  `dateNaissance` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`idPersonne`, `nom`, `prenom`, `cin`, `tel`, `dateNaissance`, `email`, `role`, `login`, `mdp`) VALUES
(7774, 'bayoudh', 'med', '12', 22, '2018-02-08', 'mohamed.bayoudh@esprit.tn', 'admin', 'med', 'bayoudh'),
(7775, 'med', 'med', '4', 4, '2018-02-22', 'mohamed.bayoudh@esprit.tn', 'membre', 'med', 'med'),
(7776, 'bouga', 'bouga', '14', 1, '2018-02-22', 'bouga@bouga', 'bonplaneur', 'bouga', 'bouga');

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `idPersonne` int(11) NOT NULL,
  `idPromotion` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `prix` float NOT NULL,
  `nbPlace` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `promotion`
--

INSERT INTO `promotion` (`idPersonne`, `idPromotion`, `titre`, `description`, `lieu`, `image`, `date`, `prix`, `nbPlace`, `type`) VALUES
(11, 33, 'zzz', 'zzzz', 'zzzz', 'DSC_0057.JPG', '2018-02-14', 88, 66, 'ddd'),
(55, 87, 'aaa', 'ddd', 'ddddd', 'DSC_0039 (2).JPG', '2018-02-28', 65, 55, 'azzzz'),
(55, 88, 'kmar', 'louhichi	', 'tunis', 'DSC_0058.JPG', '2018-02-20', 250, 22, 'hotel '),
(1, 1882, 'kdkdd', 'MC8angou', 'bjkn', 'bjk', '2018-02-08', 12, 2, 'uikl'),
(8555555, 7774, 'ddd', 'eee', 'ee', 'CLQJ5804.JPG', '2018-02-24', 25, 25, 'k');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idReservation` int(11) NOT NULL,
  `nbrPersonnes` int(11) DEFAULT '0',
  `dateEntree` date NOT NULL,
  `dateSortie` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `idPersonne` int(11) DEFAULT NULL,
  `nbrChambre` int(2) NOT NULL,
  `nbrNuit` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `reservation`
--

INSERT INTO `reservation` (`idReservation`, `nbrPersonnes`, `dateEntree`, `dateSortie`, `type`, `idPersonne`, `nbrChambre`, `nbrNuit`) VALUES
(61, 0, '2018-02-20', '2018-02-23', 'pension Complette', NULL, 1, 2),
(62, 0, '2018-02-21', '2018-02-23', 'pension complette', NULL, 2, 1),
(63, 0, '2018-02-20', '2018-02-23', 'pension Complette', NULL, 1, 2),
(64, 0, '2018-02-24', '2018-03-04', 'pension Complette', NULL, 2, 2),
(66, 0, '2018-03-09', '2018-03-11', 'pension Complette', NULL, 855, 855);

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE `transport` (
  `idTransport` int(11) NOT NULL,
  `villeDepart` varchar(50) NOT NULL,
  `villeArrive` varchar(50) NOT NULL,
  `nbrPlaceDispo` int(11) NOT NULL,
  `dateDepart` date NOT NULL,
  `heureDepart` int(11) NOT NULL,
  `heureArrivee` int(11) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `idPersonne` int(11) NOT NULL,
  `prixPersonne` float DEFAULT NULL,
  `classLuxe` float DEFAULT NULL,
  `classEco` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `transport`
--

INSERT INTO `transport` (`idTransport`, `villeDepart`, `villeArrive`, `nbrPlaceDispo`, `dateDepart`, `heureDepart`, `heureArrivee`, `type`, `idPersonne`, `prixPersonne`, `classLuxe`, `classEco`) VALUES
(445, '2', '3', 0, '2018-02-22', 1, 1, 'train', 22, 0, 78, 98),
(12333, 'tunis', 'gafsa', 4, '2018-02-21', 5, 5, 'covoiturage', 77, 4, 0, 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `bonplan`
--
ALTER TABLE `bonplan`
  ADD PRIMARY KEY (`idBonPlan`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`idEvent`),
  ADD KEY `idEvent` (`idEvent`),
  ADD KEY `IdPersonne` (`IdPersonne`);

--
-- Index pour la table `partage`
--
ALTER TABLE `partage`
  ADD PRIMARY KEY (`idPartage`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
  ADD PRIMARY KEY (`idParticipation`),
  ADD KEY `idPersonne` (`idPersonne`),
  ADD KEY `idTransport` (`idTransport`);

--
-- Index pour la table `participerevent`
--
ALTER TABLE `participerevent`
  ADD PRIMARY KEY (`idparticipation`),
  ADD KEY `idEvent` (`idEvent`,`idpersonne`),
  ADD KEY `idpersonne` (`idpersonne`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`idPersonne`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`idPromotion`),
  ADD KEY `idMembre` (`idPersonne`),
  ADD KEY `idMembre_2` (`idPersonne`),
  ADD KEY `idMembre_3` (`idPersonne`),
  ADD KEY `idPromotion` (`idPromotion`),
  ADD KEY `idMembre_4` (`idPersonne`),
  ADD KEY `idPersonne` (`idPersonne`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idReservation`),
  ADD KEY `idPersonne` (`idPersonne`),
  ADD KEY `idPersonne_2` (`idPersonne`),
  ADD KEY `idPersonne_3` (`idPersonne`),
  ADD KEY `idPersonne_4` (`idPersonne`),
  ADD KEY `idPersonne_5` (`idPersonne`),
  ADD KEY `idPersonne_6` (`idPersonne`),
  ADD KEY `idPersonne_7` (`idPersonne`);

--
-- Index pour la table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`idTransport`),
  ADD KEY `idClient` (`idPersonne`),
  ADD KEY `idTransport` (`idTransport`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `bonplan`
--
ALTER TABLE `bonplan`
  MODIFY `idBonPlan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `idEvent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=158551;
--
-- AUTO_INCREMENT pour la table `partage`
--
ALTER TABLE `partage`
  MODIFY `idPartage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT pour la table `participation`
--
ALTER TABLE `participation`
  MODIFY `idParticipation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT pour la table `participerevent`
--
ALTER TABLE `participerevent`
  MODIFY `idparticipation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `idPersonne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7777;
--
-- AUTO_INCREMENT pour la table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `idPromotion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7775;
--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idReservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `id_personne` FOREIGN KEY (`IdPersonne`) REFERENCES `personne` (`idPersonne`);

--
-- Contraintes pour la table `participerevent`
--
ALTER TABLE `participerevent`
  ADD CONSTRAINT `participerevent_ibfk_1` FOREIGN KEY (`idEvent`) REFERENCES `evenement` (`idEvent`),
  ADD CONSTRAINT `participerevent_ibfk_2` FOREIGN KEY (`idpersonne`) REFERENCES `personne` (`idPersonne`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `idPersonne` FOREIGN KEY (`idPersonne`) REFERENCES `personne` (`idPersonne`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
