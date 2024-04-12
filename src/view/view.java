package view;
import model.connexion;
import model.client;
import controller.controller;
import model.utilisateur;
import model.vehicule;
import java.util.ArrayList;

import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;

public class view {
    static controller controller = new controller();
    public static void accueil(){
        Scanner scanner = new Scanner(System.in);

        // connexion
        System.out.println("1. creer un compte");
        System.out.println("2. se connecter en tant que client");
        System.out.println("3. se connecter en tant qu'employe");

        int choix = 0;

        do{
            choix = scanner.nextInt();
        }while(choix<1 || choix>3);

        switch(choix){
            case 1:
                creation_compte();
                accueil();
                break;
            case 2 :
                connexion_client();
                pageClient();
                break;

            case 3 :
                connexion_employe();
                pageEmploye();
                break;
        }
    }

    public static void clearConsole() {
        try {
            // Vérifier le système d'exploitation
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix/Linux
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Gérer les erreurs
            e.printStackTrace();
        }
    }

    public static void creation_compte(){
        Scanner scanner = new Scanner(System.in);

        String nom;
        String prenom;
        String mail;
        String password;
        int abonnement;
        boolean particulier;

        System.out.println("veuillez saisir votre nom");
        nom = scanner.nextLine();
        System.out.println("veuillez saisir votre prenom");
        prenom = scanner.nextLine();
        System.out.println("veuillez saisir votre mail");
        mail = scanner.nextLine();
        System.out.println("veuillez saisir votre mot de passe");
        password = scanner.nextLine();
        System.out.println("a quel abonnement souhaitez vous souscrire ? (1 à 5)");
        abonnement = scanner.nextInt();
        System.out.println("s'agit-t-il d'un compte particulier ?\n1.oui\n2.non");
        particulier = (scanner.nextInt() == 1);

        client c = new client(0, nom, prenom, mail, password, abonnement, Date.valueOf(LocalDate.now()), particulier);

        controller.ajouterClient(c);
    }

    public static void connexion_client(){
        Scanner scanner = new Scanner(System.in);

        String mail;
        String password;
        boolean secu = false;

        do{
            System.out.println("veuillez saisir votre mail");
            mail = scanner.nextLine();
            System.out.println("veuillez saisir votre mot de passe");
            password = scanner.nextLine();

            if(controller.connexionClient(mail, password)){
                System.out.println("connexion reussie");
                secu = false;
            }
            else {
                System.out.println("connexion echouee");
                secu = true;
            }
        }while(secu);
    }

    public static void connexion_employe(){
        Scanner scanner = new Scanner(System.in);

        String mail;
        String password;
        boolean secu = false;

        do{
            System.out.println("veuillez saisir votre mail");
            mail = scanner.nextLine();
            System.out.println("veuillez saisir votre mot de passe");
            password = scanner.nextLine();

            if(controller.connexionEmploye(mail, password)){
                System.out.println("connexion reussie");
                secu = false;
            }
            else {
                System.out.println("connexion echouee");
                secu = true;
            }
        }while(secu);
    }

    public static void pageClient(){
        Scanner scanner = new Scanner(System.in);

        int choix1 = 0;
        int choix2 = 0;
        int choix3 = 0;

        String selS = null;
        int selI = 0;

        do {
            controller.getLv().chargeListe();
            controller.getLv().afficheList();

            System.out.println("\n1.ajouter un filtre\n2.supprimer les filtres\n3.consulter un véhicule");

            choix1 = scanner.nextInt();
            scanner.nextLine();

            if(choix1 == 1){
                System.out.println("Quel filtre souhaitez-vous ajouter ?");
                System.out.println("1.marque\n2.modele\n3.annee\n4.type\n5.puissance\n6.transmission\n7.categorie\n");

                choix2 = scanner.nextInt();
                scanner.nextLine();

                switch(choix2){
                    case 1:
                        System.out.println("Veuillez saisir la marque de votre choix");
                        selS = scanner.nextLine();
                        controller.getLv().setFiltreMarque(selS);
                        break;
                    case 2:
                        System.out.println("Veuillez saisir le modele de votre choix");
                        selS = scanner.nextLine();
                        controller.getLv().setFiltreModele(selS);
                        break;
                    case 3:
                        System.out.println("Veuillez saisir l'annee' de votre choix");
                        selI = scanner.nextInt();
                        scanner.nextLine();
                        controller.getLv().setFiltreAnnee(selI);
                        break;
                    case 4:
                        System.out.println("Veuillez saisir le type de votre choix");
                        selS = scanner.nextLine();
                        controller.getLv().setFiltreType(selS);
                        break;
                    case 5:
                        System.out.println("Veuillez saisir la puissance de votre choix");
                        selI = scanner.nextInt();
                        scanner.nextLine();
                        controller.getLv().setFiltrePuissance(selI);
                        break;
                    case 6:
                        System.out.println("Veuillez saisir la transmission de votre choix");
                        selS = scanner.nextLine();
                        controller.getLv().setFiltreTransmission(selS);
                        break;
                    case 7:
                        System.out.println("Veuillez saisir la categorie de votre choix");
                        selI = scanner.nextInt();
                        scanner.nextLine();
                        controller.getLv().setFiltreCategorie(selI);
                        break;
                }
            }

            if (choix1 == 2){
                controller.getLv().resetFiltre();
                System.out.println("filtre efface");
            }

            if (choix1 == 3){
                System.out.println("quel véhicule souhaitez-vous consulter ?");
                choix2 = scanner.nextInt();
                scanner.nextLine();

                controller.getLv().afficheVehicule(choix2);

                System.out.println("1.consulter les autres vehicules\n2.louer ce vehicule");

                choix3 = scanner.nextInt();
                scanner.nextLine();

                if(choix3 == 2){
                    ArrayList<Integer> dateLocation = new ArrayList<>();

                    System.out.println("Veuillez indiquer la date de debut de location de véhicule");
                    System.out.println("Annee");
                    dateLocation.add(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Mois");
                    dateLocation.add(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Jour");
                    dateLocation.add(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println("\nVeuillez indiquer la date de fin de location de véhicule");
                    System.out.println("Annee");
                    dateLocation.add(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Mois");
                    dateLocation.add(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Jour");
                    dateLocation.add(scanner.nextInt());

                    int prixMois = controller.getPrixVoiture(choix2);
                    int nbmois = (dateLocation.get(3) - dateLocation.get(0)) * 12 + (dateLocation.get(4) - dateLocation.get(1)) + (dateLocation.get(5) - dateLocation.get(2)) / 30;

                    System.out.println("\nFrais à régler pour les " + nbmois + " mois de locations :");
                    System.out.println((prixMois * nbmois) + " $");

                    System.out.println("\nMerci de nous faire confiance, et bienvenu à bord du prestige automobile\n");

                    controller.ajouterLocation(controller.getIdLogClient(), choix2, dateLocation);
                }
            }
        }while(true);

    }

    public static void pageEmploye(){
        System.out.println("page employe");
    }
}
