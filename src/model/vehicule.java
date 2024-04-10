package model;

public class vehicule {
    private int id;
    private String marque;
    private String modele;
    private int annee;
    private String type;
    private int puissance;
    private String transmission;
    private int categorie;
    private int disponibilite;

    public vehicule(int i, String m, String m2, int a, String t, int p, String t2, int c, int d){
        this.id = i;
        this.marque = m;
        this.modele = m2;
        this.annee = a;
        this.type = t;
        this.puissance = p;
        this.transmission = t2;
        this.categorie = c;
        this.disponibilite = d;
    }

    public int getId(){
        return id;
    }
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPuissance() {
        return puissance;
    }
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }
    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    public int getCategorie() {
        return categorie;
    }
    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
    public int getDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }
    public void afficherAttributs() {
        System.out.println("ID: " + id);
        System.out.println("Marque: " + marque);
        System.out.println("Modèle: " + modele);
        System.out.println("Année: " + annee);
        System.out.println("Type: " + type);
        System.out.println("Puissance: " + puissance);
        System.out.println("Transmission: " + transmission);
        System.out.println("Catégorie: " + categorie);
        System.out.println("Disponibilité: " + disponibilite);
        System.out.println("\n");
    }
}
