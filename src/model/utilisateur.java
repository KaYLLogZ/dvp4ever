package model;

public class utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private String password;

    public utilisateur(){}
    public utilisateur(int i, String n, String p, String m, String pswd){
        this.id = i;
        this.nom = n;
        this.prenom = p;
        this.mail = m;
        this.password = pswd;
    }

    public int getId(){
        return id;
    }

    public void setId(int i){
        this.id = i;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAttribut1(int i, String n, String p, String m, String pa){
        id = i;
        nom = n;
        prenom = p;
        mail = m;
        pa = p;
    }

    public void afficherAttributs() {
        System.out.println("1. Nom : " + nom);
        System.out.println("2. Prénom : " + prenom);
        System.out.println("3. Mail : " + mail);
        System.out.println("4. Mot de passe : " + password);
    }
}
