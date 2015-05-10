package model;

import java.util.HashMap;

/**
 *
 * @author Yassine Doghri
 */
public class Formateur {

    private int id;
    private String nom;
    private String prenom;
    private String initiales;
    private String telephone;
    private String email;
    private HashMap<Integer, Sceance> listeSceances;

    /**
     * Constructeur de l'objet Formateur
     *
     * @param id
     * @param nom
     * @param prenom
     * @param initiales
     * @param telephone
     * @param email
     * @param sceances
     */
    public Formateur(int id, String nom, String prenom, String initiales, String telephone, String email, HashMap<Integer, Sceance> sceances) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.initiales = initiales;
        this.telephone = telephone;
        this.email = email;
        this.listeSceances = sceances;
    }

    public Formateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getInitiales() {
        return initiales;
    }

    public void setInitiales(String initiales) {
        this.initiales = initiales;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<Integer, Sceance> getListeSceances() {
        return listeSceances;
    }

    public void setListeSceances(HashMap<Integer, Sceance> listeSceances) {
        this.listeSceances = listeSceances;
    }

}
