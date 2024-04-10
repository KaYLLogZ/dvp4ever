package view;
import model.connexion;
import model.client;
import controller.controller;
import model.utilisateur;

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
        System.out.println("page employe");
    }

    public static void pageEmploye(){
        System.out.println("page employe");
    }
}
