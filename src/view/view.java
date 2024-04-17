package view;
import model.*;
import controller.controller;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;

public class view {
    static controller controller = new controller();
    static Scanner scanner = new Scanner(System.in);
    public static void accueil(){
        int choix = 0;
        do{
            System.out.println("\n\nAccueil\n");

            // connexion
            System.out.println("1. creer un compte");
            System.out.println("2. se connecter en tant que client");
            System.out.println("3. se connecter en tant qu'employe");
            System.out.println("4. quitter le programme");

            do{
                if(choix<0 || choix>4) System.out.println("\nveuillez saisir un chiffre valable");
                choix = scanner.nextInt();
            }while(choix<1 || choix>4);

            switch(choix){
                case 1:
                    creation_compte();
                    accueil();
                    break;
                case 2 :
                    connexion_client();
                    pageClientAccueil();
                    break;

                case 3 :
                    connexion_employe();
                    pageEmployeTableau();
                    break;
            }
        }while(choix != 4);
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

        controller.getClientAccess().ajouterClient(c);
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

           if(controller.getClientAccess().connexionClient(mail, password, controller.getClient())) {
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

            if(controller.getUtilisateurAccess().connexionEmploye(mail, password)){
                System.out.println("connexion reussie");
                secu = false;
            }
            else {
                System.out.println("connexion echouee");
                secu = true;
            }
        }while(secu);
    }

    public static void pageClientAccueil() {
        int choix = 0;
        do{
            System.out.println("\nAccueil Page Client");
            System.out.println("\n1.consulter notre catalogue\n2.acceder a votre espace client\n3.se déconnecter");
            do {
                if(choix < 0 || choix > 3) System.out.println("\nveuillez saisir un chiffre valable");
                choix = scanner.nextInt();
                scanner.nextLine();
            }while(choix < 1 || choix > 3);

            switch (choix) {
                case 1:
                    pageClientCatalogue();
                    break;
                case 2:
                    espaceClientAccueil();
                    break;
            }
        }while(choix != 3);
    }

    public static void pageClientCatalogue(){
        int choix = 0;

        do{
            System.out.println("\n\nCatalogue Page Client");
            controller.getLv().chargeListe();
            controller.getLv().afficheList();
            System.out.println("\n1.ajouter un filtre\n2.supprimer les filtres\n3.consulter un véhicule\n4.revenir en arriere");

            do {
                if(choix < 0 || choix > 4) System.out.println("\nveuillez saisir un chiffre valable");
                choix = scanner.nextInt();
                scanner.nextLine();
            }while(choix < 1 || choix > 4);

            if(choix == 1) pageClientCatalogueFiltre();
            if (choix == 2){
                controller.getLv().resetFiltre();
                System.out.println("filtre efface");
            }
            if (choix == 3) pageClientCatalogueShowroom();
        }while(choix != 4);
    }

    public static void pageClientCatalogueFiltre(){
        int choix = 0;
        System.out.println("\n\nPage Client Filtre catalogue\n");
        System.out.println("Quel filtre souhaitez-vous ajouter ?");
        System.out.println("1.marque\n2.modele\n3.annee\n4.type\n5.puissance\n6.transmission\n7.categorie\n");

        do {
            if(choix < 0 || choix > 7) System.out.println("\nveuillez saisir un chiffre valable");
            choix = scanner.nextInt();
            scanner.nextLine();
        }while(choix < 1 || choix > 7);

        String selS = null;
        int selI = 0;

        switch(choix){
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

    public static void pageClientCatalogueShowroom(){
        System.out.println("\n\nPage Client Showroom\n");
        System.out.println("quel véhicule souhaitez-vous consulter ?");

        int choixv = scanner.nextInt();
        scanner.nextLine();
        controller.getVehiculeAccess().afficheVehicule(choixv);

        System.out.println("1.louer ce vehicule\n2.revenir en arriere");

        int choix = scanner.nextInt();
        scanner.nextLine();

        if(choix == 1){
            if(controller.getClient().getCategorie_abonnement() < controller.getVehiculeAccess().getCateVehicule(choixv)){
                System.out.println("Votre abonnement actuel ne vous offre pas l'accès à un véhicule de cette catégorie.");
            }
            else {
                ArrayList<Integer> dateLocation = new ArrayList<>();

                System.out.println("Veuillez indiquer la date de debut de location de véhicule");
                System.out.println("Annee");
                int anneeDebut = scanner.nextInt();
                scanner.nextLine();
                dateLocation.add(anneeDebut);
                System.out.println("Mois");
                int moisDebut = scanner.nextInt();
                scanner.nextLine();
                dateLocation.add(moisDebut);

                System.out.println("\nVeuillez indiquer la date de fin de location de véhicule");
                System.out.println("Annee");
                int anneeFin = scanner.nextInt();
                scanner.nextLine();
                dateLocation.add(anneeFin);
                System.out.println("Mois");
                int moisFin = scanner.nextInt();
                scanner.nextLine();
                dateLocation.add(moisFin);

                boolean secu = true;

                if(!controller.getLocationAccess().dispoClient(controller.getClient().getId(), anneeDebut, moisDebut, anneeFin, moisFin)){
                    System.out.println("\nVous avez deja une reservation enregistre sur cette periode\n");
                    secu = false;
                }
                if(!controller.getLocationAccess().dispoVehicule(choixv, anneeDebut, moisDebut, anneeFin, moisFin)) {
                    System.out.println("\nLe vehicule n'est pas disponible sur cette periode\n");
                    secu = false;
                }
                if(secu) {
                    System.out.println("\nReservation du vehicule effectue avec succès\nMerci de nous faire confiance, et bienvenu à bord du prestige automobile\n");
                    controller.getLocationAccess().ajouterLocation(controller.getClient().getId(), choixv, dateLocation);
                }
            }

        }
    }

    public static void espaceClientAccueil(){
        int choix = 0;
        do{
            System.out.println("\n\nAccueil Espace Client\n");
            System.out.println("\n1.consulter votre profil\n2.consulter vos reservation\n3.changer d'abonnements\n4.revenir en arriere");
            do {
                if(choix < 0 || choix > 4) System.out.println("\nveuillez saisir un chiffre valable");
                choix = scanner.nextInt();
                scanner.nextLine();
            }while(choix < 1 || choix > 4);

            switch (choix) {
                case 1:
                    pageClientProfil();
                    break;
                case 2:
                    pageClientReservations();
                    break;
                case 3:
                    pageClientAbonnements();
                    break;
            }
        }while(choix != 4);
    }

    public static void pageClientAbonnements(){
        int choix = 1;

            System.out.println("\n\nEspace Client Reservations\n");
            System.out.println("1. Turismo Discovery : accès au véhicule de catégorie une");
            System.out.println("2. Turismo Gold : accès au véhicule de catégorie deux et moins");
            System.out.println("3. Turismo Platinum : accès au véhicule de catégorie trois et moins");
            System.out.println("4. Turismo Diamond : accès au véhicule de catégorie quatre et moins");
            System.out.println("5. Turismo VIP : accès au véhicule de catégorie cinq et moins");

            switch(controller.getClient().getCategorie_abonnement()){
                case 1:
                    System.out.println("\nvous possédez actuellement un abonnements Turismo Discovery\n");
                    break;
                case 2:
                    System.out.println("\nvous possédez actuellement un abonnements Turismo Gold\n");
                    break;
                case 3:
                    System.out.println("\nvous possédez actuellement un abonnements Turismo Platinum\n");
                    break;
                case 4:
                    System.out.println("\nvous possédez actuellement un abonnements Turismo Diamond\n");
                    break;
                case 5:
                    System.out.println("\nvous possédez actuellement un abonnements Turismo VIP\n");
                    break;
            }

            System.out.println("1.explorer une offre\n2.revenir en arriere");

            do {
                if(choix < 1 || choix > 2) System.out.println("\nveuillez saisir un chiffre valable");
                choix = scanner.nextInt();
                scanner.nextLine();
            }while(choix < 1 || choix > 2);

            if(choix == 1){
                int choix_ = 1;
                System.out.println("\n\nVeuillez saisir l'offre qui vous intéresse\n");
                do {
                    if(choix_ < 1 || choix_ > 5) System.out.println("\nveuillez saisir un chiffre valable");
                    choix_ = scanner.nextInt();
                    scanner.nextLine();
                }while(choix_ < 1 || choix_ > 5);

                System.out.println("\n\nExemple de véhicule disponible avec cet abonnement :\n");
                controller.getLv().resetFiltre();
                controller.getLv().setFiltreCategorie(choix_);
                controller.getLv().chargeListe();
                controller.getLv().afficheList();

                switch(choix_){
                    case 1:
                        System.out.println("\nOffre disponible à partir de 1999$ par mois\n");
                        break;
                    case 2:
                        System.out.println("\nOffre disponible à partir de 2999$ par mois\n");
                        break;
                    case 3:
                        System.out.println("\nOffre disponible à partir de 4999$ par mois\n");
                        break;
                    case 4:
                        System.out.println("\nOffre disponible à partir de 7999$ par mois\n");
                        break;
                    case 5:
                        System.out.println("\nOffre disponible à partir de 9999$ par mois\n");
                        break;
                }

                System.out.println("1.souscrire a cet abonnement\n2.annuler");
                int sel = 1;
                do {
                    if(sel < 1 || sel > 2) System.out.println("\nveuillez saisir un chiffre valable");
                    sel = scanner.nextInt();
                    scanner.nextLine();
                }while(sel < 1 || sel > 2);

                if(sel == 1){
                    controller.getUtilisateurAccess().changeAbonnement(controller.getClient().getId(), choix);
                }
            }
    }

    public static void  pageClientReservations(){
        int choix = 0;

        controller.getLl().chargeLocationClient(controller.getClient().getId());

        System.out.println("\n\nAccueil Espace Client\n");
        if(controller.getLl().isEmpty()) System.out.println("\nVous n'avez aucune reservation sur les 12 prochains mois\n");
        else System.out.println("\nVoici la liste de vos reservations sur les 12 prochains mois\n");

        controller.getLl().display();

        System.out.println("\n\n1.supprimer une réservation\n2. revenir en arriere");
        do {
            if(choix < 0 || choix > 2) System.out.println("\nveuillez saisir un chiffre valable");
            choix = scanner.nextInt();
            scanner.nextLine();
        }while(choix < 1 || choix > 2);

        if(choix == 1){
            System.out.println("\n\nVeuillez saisir l'identifiant de la location que vous souhaitez supprimer");
            do {
                if(choix < 1) System.out.println("\nveuillez saisir un chiffre valable");
                choix = scanner.nextInt();
                scanner.nextLine();
            }while(choix < 1);
            controller.getLocationAccess().supprimerLocation(choix);
        }
    }

    public static void  pageClientProfil(){
        int choix = 0;

        System.out.println("\n\nPage Client Profil\n");
        controller.getClient().afficherAttributs();

        System.out.println("\n\n1.changer un attribut\n2. revenir en arriere");
        do {
            if(choix < 0 || choix > 2) System.out.println("\nveuillez saisir un chiffre valable");
            choix = scanner.nextInt();
            scanner.nextLine();
        }while(choix < 1 || choix > 2);

        if (choix == 1) {
            System.out.println("\nVeuillez saisir l'attribut que vous souhaitez modifier");
            int choix_a = 0;

            do {
                if(choix_a < 0 || choix_a > 7) System.out.println("\nveuillez saisir un chiffre valable");
                choix_a = scanner.nextInt();
                scanner.nextLine();
            }while(choix_a < 1 || choix_a > 7);

            if(choix_a == 1 || choix_a == 2 || choix_a == 3 || choix_a == 4){
                System.out.println("\nVeuillez saisir la nouvelle valeur de l'attribut");
                String sel = scanner.nextLine();
                controller.getClientAccess().modifierString(choix_a, sel, controller.getClient().getId());
            }

            if(choix_a == 6 || choix_a == 5){
                System.out.println("\nVous ne pouvez pas modifier cet attribut");
            }

            if(choix_a == 7){
                controller.getClientAccess().modifierBool(choix_a, controller.getClient().getId());
            }
        }
    }

    public static void pageEmployeTableau(){
        int choix = 0;

        System.out.println("\n\nTableau de bord\n");
        ArrayList<ArrayList<Integer>> dl = controller.getLocationAccess().locQuatreTrimestre(2024, 4);

        System.out.println("Nombre de locations par periodes : \n");
        for(ArrayList<Integer> l : dl){
            System.out.println("du " + l.get(1) + "/" + l.get(0) + "/01 au " + l.get(3) + "/" + l.get(2) + "/28 : " + l.get(4) + " locations");
        }

        System.out.println("\n");

        ArrayList<ArrayList<Integer>> dlb = controller.getLocationAccess().locAnnuelVehicule(2024, 4);

        System.out.println("Nombre de locations par vehicule sur les 12 prochains mois : \n");
        for(ArrayList<Integer> l : dlb){
            System.out.println(controller.getVehiculeAccess().getNomVehciule(l.get(0)) + " : " + l.get(1) + " locations");
        }

        System.out.println("\n");

        pageEmployeSelection();
    }

    public static void pageEmployeSelection(){
        int choix = 0;
        do{
            System.out.println("\n1.ajouter une gamme de véhicule\n2.supprimer une gamme de véhicule\n3.modifier les stocks d'une gamme de vehicule\n4.revenir en arriere");
            do {
                if(choix < 0 || choix > 4) System.out.println("\nveuillez saisir un chiffre valable");
                choix = scanner.nextInt();
                scanner.nextLine();
            }while(choix < 1 || choix > 4);

            if(choix == 1){
                String marque;
                String modele;
                int annee;
                String type;
                int puissance;
                String transmission;
                int categorie;
                int disponibilite;

                System.out.println("veuillez saisir la marque");
                marque = scanner.nextLine();
                System.out.println("veuillez saisir le modele");
                modele = scanner.nextLine();
                System.out.println("veuillez saisir l'annee");
                annee = scanner.nextInt();
                scanner.nextLine();
                System.out.println("veuillez saisir le type");
                type = scanner.nextLine();
                System.out.println("veuillez saisir la puissance");
                puissance = scanner.nextInt();
                scanner.nextLine();
                System.out.println("veuillez saisir la transmission");
                transmission = scanner.nextLine();
                System.out.println("veuillez saisir la categorie");
                categorie = scanner.nextInt();
                scanner.nextLine();
                System.out.println("veuillez saisir la disponiblite");
                disponibilite = scanner.nextInt();
                scanner.nextLine();

                controller.getVehiculeAccess().ajouterVehicule(marque, modele, annee, type, puissance, transmission, categorie, disponibilite);
            }

            if(choix == 2){
                System.out.println("\nveuillez saisir l'identifiant du véhicule que vous souhaitez supprier du catalogue");
                int choix_ = scanner.nextInt();
                scanner.nextLine();

                controller.getVehiculeAccess().supprimerVehicule(choix_);
            }

            if(choix == 3){
                System.out.println("\nveuillez saisir l'identifiant du véhicule dont vous souhaitez modifier le stock");
                int choix_vehicule = scanner.nextInt();
                scanner.nextLine();

                int choix_ = 0;
                System.out.println("\n1.je souhaite ajouter des vehicules\n2.je souhaite supprimer des vehicules");
                do{
                    if(choix_ < 0 || choix_ > 2) System.out.println("\nveuillez saisir un chiffre valable");
                    choix_ = scanner.nextInt();
                    scanner.nextLine();
                }while(choix_ < 1 || choix_ > 2);

                int choix_nb_piece = 0;
                System.out.println("\nVeuillez selectionner le nombre de piece");
                do{
                    if(choix_nb_piece < 0) System.out.println("\nveuillez saisir un chiffre valable");
                    choix_nb_piece = scanner.nextInt();
                    scanner.nextLine();
                }while(choix_nb_piece < 0);

                switch(choix_){
                    case 1:
                        controller.getVehiculeAccess().modifierStock(choix_vehicule, choix_nb_piece);
                        break;
                    case 2:
                        controller.getVehiculeAccess().modifierStock(choix_vehicule, -choix_nb_piece);
                        break;
                }
            }
        }while(choix != 4);
    }
}