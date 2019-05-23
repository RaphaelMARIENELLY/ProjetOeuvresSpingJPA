-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 23 mai 2019 à 14:48
-- Version du serveur :  10.1.28-MariaDB
-- Version de PHP :  7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `baseoeuvre`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherent`
--

CREATE TABLE `adherent` (
  `id_adherent` int(10) UNSIGNED NOT NULL,
  `nom_adherent` varchar(100) NOT NULL,
  `prenom_adherent` varchar(100) DEFAULT NULL,
  `ville_adherent` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `adherent`
--

INSERT INTO `adherent` (`id_adherent`, `nom_adherent`, `prenom_adherent`, `ville_adherent`) VALUES
(3, 'DUPONT', 'Didier', 'Grenoble'),
(4, 'Michel', 'DURANT', 'Givors'),
(5, 'CHOPINE', 'Danielle', 'Villefranchent'),
(6, 'MOZART', 'Albert', 'Craponne'),
(7, 'FRUCCI', 'Fraise', 'Craponne'),
(8, 'Lyons', 'Pierre', 'Lyon'),
(9, 'Oullin', 'Serge', 'Oullins'),
(10, 'Salaun', 'AmÃ©lie', 'Madrid'),
(11, 'VIAL', 'albert  ', 'Lyon'),
(13, 'Grand', 'Alain', 'Tours'),
(14, 'Vial', 'Guillaume', 'COPENHAGUE'),
(15, 'THOAR', 'Ingrid', 'BERLIN');

-- --------------------------------------------------------

--
-- Structure de la table `oeuvrepret`
--

CREATE TABLE `oeuvrepret` (
  `id_oeuvrepret` int(10) UNSIGNED NOT NULL,
  `titre_oeuvrepret` varchar(200) NOT NULL,
  `id_proprietaire` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `oeuvrepret`
--

INSERT INTO `oeuvrepret` (`id_oeuvrepret`, `titre_oeuvrepret`, `id_proprietaire`) VALUES
(1, 'Oeuvre en pret 1', 1000),
(2, 'Oeuvre en pret 2', 1000),
(3, 'TEST', 1000);

-- --------------------------------------------------------

--
-- Structure de la table `oeuvrevente`
--

CREATE TABLE `oeuvrevente` (
  `id_oeuvrevente` int(10) UNSIGNED NOT NULL,
  `titre_oeuvrevente` varchar(200) NOT NULL,
  `etat_oeuvrevente` varchar(1) NOT NULL,
  `prix_oeuvrevente` float NOT NULL,
  `id_proprietaire` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `oeuvrevente`
--

INSERT INTO `oeuvrevente` (`id_oeuvrevente`, `titre_oeuvrevente`, `etat_oeuvrevente`, `prix_oeuvrevente`, `id_proprietaire`) VALUES
(10001, 'Ete', 'R', 400, 1000),
(10002, 'Automne', 'L', 10, 1000),
(10003, 'Crepuscule', 'L', 111, 1001),
(10004, 'Hiver', 'R', 234, 1000),
(10005, 'Aurore', 'L', 655, 1001),
(10006, 'Nuit de printemps', 'L', 789, 1001),
(10011, 'oeuvretest', 'R', 21, 1002);

-- --------------------------------------------------------

--
-- Structure de la table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `id_proprietaire` int(10) UNSIGNED NOT NULL,
  `nom_proprietaire` varchar(100) CHARACTER SET latin1 NOT NULL,
  `prenom_proprietaire` varchar(100) CHARACTER SET latin1 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `proprietaire`
--

INSERT INTO `proprietaire` (`id_proprietaire`, `nom_proprietaire`, `prenom_proprietaire`) VALUES
(1000, 'DUPONT', 'Isabelle'),
(1001, 'DUPUIS', 'Valerie'),
(1002, 'MOZART', 'Albert');

-- --------------------------------------------------------

--
-- Structure de la table `reservation_oeuvrevente`
--

CREATE TABLE `reservation_oeuvrevente` (
  `id_reservation_oeuvrevente` int(10) UNSIGNED NOT NULL,
  `id_oeuvrevente` int(10) UNSIGNED NOT NULL,
  `id_adherent` int(10) UNSIGNED NOT NULL,
  `date_reservation` date NOT NULL,
  `statut` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reservation_oeuvrevente`
--

INSERT INTO `reservation_oeuvrevente` (`id_reservation_oeuvrevente`, `id_oeuvrevente`, `id_adherent`, `date_reservation`, `statut`) VALUES
(1, 10011, 3, '2019-05-23', 'en attente'),
(2, 10004, 3, '2019-05-23', 'en attente'),
(3, 10001, 9, '2019-05-23', 'en attente');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `NumUtil` int(11) NOT NULL,
  `NomUtil` varchar(100) COLLATE utf8_bin NOT NULL,
  `MotPasse` varchar(100) COLLATE utf8_bin NOT NULL,
  `role` varchar(100) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`NumUtil`, `NomUtil`, `MotPasse`, `role`) VALUES
(1, 'Merlot', '5ebe2294ecd0e0f08eab7690d2a6ee69', 'admin'),
(2, 'Lalande', '5ebe2294ecd0e0f08eab7690d2a6ee69', 'visiteur'),
(3, 'Pinot', '5ebe2294ecd0e0f08eab7690d2a6ee69', 'visiteur');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherent`
--
ALTER TABLE `adherent`
  ADD PRIMARY KEY (`id_adherent`);

--
-- Index pour la table `oeuvrepret`
--
ALTER TABLE `oeuvrepret`
  ADD PRIMARY KEY (`id_oeuvrepret`),
  ADD KEY `id_proprietaire` (`id_proprietaire`);

--
-- Index pour la table `oeuvrevente`
--
ALTER TABLE `oeuvrevente`
  ADD PRIMARY KEY (`id_oeuvrevente`),
  ADD KEY `id_proprietaire` (`id_proprietaire`);

--
-- Index pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`id_proprietaire`);

--
-- Index pour la table `reservation_oeuvrevente`
--
ALTER TABLE `reservation_oeuvrevente`
  ADD PRIMARY KEY (`id_reservation_oeuvrevente`),
  ADD KEY `id_oeuvrevente` (`id_oeuvrevente`),
  ADD KEY `id_adherent` (`id_adherent`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`NumUtil`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adherent`
--
ALTER TABLE `adherent`
  MODIFY `id_adherent` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `oeuvrepret`
--
ALTER TABLE `oeuvrepret`
  MODIFY `id_oeuvrepret` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `oeuvrevente`
--
ALTER TABLE `oeuvrevente`
  MODIFY `id_oeuvrevente` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10012;

--
-- AUTO_INCREMENT pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  MODIFY `id_proprietaire` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1003;

--
-- AUTO_INCREMENT pour la table `reservation_oeuvrevente`
--
ALTER TABLE `reservation_oeuvrevente`
  MODIFY `id_reservation_oeuvrevente` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100001;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `oeuvrepret`
--
ALTER TABLE `oeuvrepret`
  ADD CONSTRAINT `oeuvrepret_ibfk_1` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaire` (`id_proprietaire`);

--
-- Contraintes pour la table `oeuvrevente`
--
ALTER TABLE `oeuvrevente`
  ADD CONSTRAINT `oeuvrevente_ibfk_1` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaire` (`id_proprietaire`);

--
-- Contraintes pour la table `reservation_oeuvrevente`
--
ALTER TABLE `reservation_oeuvrevente`
  ADD CONSTRAINT `reservation_oeuvrevente_ibfk_1` FOREIGN KEY (`id_oeuvrevente`) REFERENCES `oeuvrevente` (`id_oeuvrevente`),
  ADD CONSTRAINT `reservation_oeuvrevente_ibfk_2` FOREIGN KEY (`id_adherent`) REFERENCES `adherent` (`id_adherent`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
