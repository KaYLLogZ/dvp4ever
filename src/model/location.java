package model;
import java.sql.*;

public class location {
    private int id;
    private int id_client;
    private int id_vehicule;
    private Date date_debut;
    private Date date_fin;

    public location(int i, int idc, int idv, Date dd, Date df){
        this.id = i;
        this.id_client = idc;
        this.id_vehicule = idv;
        this.date_debut = dd;
        this.date_fin = df;
    }

    public int getId(){
        return id;
    }
    public int getId_client() {
        return id_client;
    }
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    public int getId_vehicule() {
        return id_vehicule;
    }
    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }
    public Date getDate_debut() {
        return date_debut;
    }
    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }
    public Date getDate_fin() {
        return date_fin;
    }
    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void afficherAttributs() {
        System.out.println("ID : " + id);
        System.out.println("ID client : " + id_client);
        System.out.println("ID véhicule : " + id_vehicule);
        System.out.println("Date début : " + date_debut);
        System.out.println("Date fin : " + date_fin);
        System.out.println("\n");
    }
}
