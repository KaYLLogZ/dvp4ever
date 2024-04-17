CREATE TABLE vehicule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marque VARCHAR(255),
    modele VARCHAR(255),
    annee INT,
    type VARCHAR(255),
    puissance INT,
    transmission VARCHAR(255),
    categorie INT,
    disponibilite INT
);

INSERT INTO vehicule (marque, modele, annee, type, puissance, transmission, categorie, disponibilite) VALUES ('Porsche', 'Cayman 718', 2022, 'coupe', 300, 'arriere', 1, 10);
INSERT INTO vehicule (marque, modele, annee, type, puissance, transmission, categorie, disponibilite) VALUES ('Mercedes', 'GT 53 AMG', 2023, 'coupe', 430, 'arriere', 2, 10);
INSERT INTO vehicule (marque, modele, annee, type, puissance, transmission, categorie, disponibilite) VALUES ('BMW', 'M3 Competition', 2023, 'coupe', 500, 'arriere', 2, 10);
INSERT INTO vehicule (marque, modele, annee, type, puissance, transmission, categorie, disponibilite) VALUES ('Aston Martin', 'Vantage', 2024, 'coupe', 510, 'arriere', 3, 5);
INSERT INTO vehicule (marque, modele, annee, type, puissance, transmission, categorie, disponibilite) VALUES ('Lamborgini', 'Huracan EVO', 2021, 'coupe', 630, 'arriere', 4, 3);
INSERT INTO vehicule (marque, modele, annee, type, puissance, transmission, categorie, disponibilite) VALUES ('Ferrari', '488 Pista', 2020, 'coupe', 710, 'arriere', 5, 3);

CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    mail VARCHAR(255),
    pswd VARCHAR(255),
    categorie_abonnement INT,
    date_inscription DATE,
    particulier BOOLEAN
);

INSERT INTO client (nom, prenom, mail, pswd, categorie_abonnement, date_inscription, particulier) VALUES ('Nicolas', 'Meny', 'nicolas.meny@edu.ece.fr', 'ri52phpc', 1,  '2024-04-10', true);
INSERT INTO client (nom, prenom, mail, pswd, categorie_abonnement, date_inscription, particulier) VALUES ('Jules', 'Pinte', 'jules.pinte@edu.ece.fr', 'ri53phpc', 4,  '2024-04-10', true);
INSERT INTO client (nom, prenom, mail, pswd, categorie_abonnement, date_inscription, particulier) VALUES ('Ruben', 'Tarin-Darnis', 'ruben.tarindarnis@edu.ece.fr', 'ri54phpc', 2,  '2024-04-10', true);
INSERT INTO client (nom, prenom, mail, pswd, categorie_abonnement, date_inscription, particulier) VALUES ('ECE', 'Paris', 'omnes.education@edu.ece.fr', 'ri55phpc', 5,  '2024-04-10', false);
INSERT INTO client (nom, prenom, mail, pswd, categorie_abonnement, date_inscription, particulier) VALUES ('Lucas', 'Meny', 'lucas.meny@edu.ece.fr', 'ri56phpc', 3,  '2024-04-10', true);
INSERT INTO client (nom, prenom, mail, pswd, categorie_abonnement, date_inscription, particulier) VALUES ('Antonin', 'Meny', 'antonin.meny@edu.ece.fr', 'ri57phpc', 1,  '2024-04-10', true);

CREATE TABLE employe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    mail VARCHAR(255),
    pswd VARCHAR(255)
);

INSERT INTO employe (nom, prenom, mail, pswd) VALUES ('Florence', 'Meny', 'florence.meny@edu.ece.fr', 'mahmoud3006');
INSERT INTO employe (nom, prenom, mail, pswd) VALUES ('Pascal', 'Meny', 'pascal.meny@edu.ece.fr', 'mahmoud3007');
INSERT INTO employe (nom, prenom, mail, pswd) VALUES ('Nathalie', 'Housseau', 'nathalie.housseau@edu.ece.fr', 'mahmoud3008');
INSERT INTO employe (nom, prenom, mail, pswd) VALUES ('Isabelle', 'Gelin', 'isabelle.gelin@edu.ece.fr', 'mahmoud3009');
INSERT INTO employe (nom, prenom, mail, pswd) VALUES ('Cecilia', 'Uribe', 'cecilia.uribe@edu.ece.fr', 'mahmoud3010');
INSERT INTO employe (nom, prenom, mail, pswd) VALUES ('John', 'Doe', 'john.doe@edu.ece.fr', 'mahmoud3011');

CREATE TABLE location (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_client INT,
    id_vehicule INT,
    date_debut DATE,
    date_fin DATE
);

INSERT INTO location (id_client, id_vehicule, date_debut, date_fin) VALUES (1, 6, '2024-04-10', '2024-07-10');
INSERT INTO location (id_client, id_vehicule, date_debut, date_fin) VALUES (2, 5, '2024-04-10', '2024-09-10');
INSERT INTO location (id_client, id_vehicule, date_debut, date_fin) VALUES (3, 4, '2024-04-10', '2024-08-10');
INSERT INTO location (id_client, id_vehicule, date_debut, date_fin) VALUES (4, 3, '2024-04-10', '2024-06-10');
INSERT INTO location (id_client, id_vehicule, date_debut, date_fin) VALUES (5, 2, '2024-04-10', '2024-08-25');
INSERT INTO location (id_client, id_vehicule, date_debut, date_fin) VALUES (6, 1, '2024-04-10', '2024-06-19');