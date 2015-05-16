package model;

import java.util.HashMap;

/**
 * Classe modèle pour l'objet Formateur
 * 
 * @author Yassine Doghri
 */
public class Formateur {

    private Integer id;
    private String nom;
    private String prenom;
    private String initiales;
    private String telephone;
    private String email;
    private HashMap<Integer, Seance> listeSeance = new HashMap();

    /**
     * Constructeur de l'objet Formateur
     *
     * @param id
     * @param nom
     * @param prenom
     * @param initiales
     * @param telephone
     * @param email
     * @param seances
     */
    public Formateur(Integer id, String nom, String prenom, String initiales, String telephone, String email, HashMap<Integer, Seance> seances) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.initiales = initiales;
        this.telephone = telephone;
        this.email = email;
        this.listeSeance = seances;
    }

    public Formateur() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public HashMap<Integer, Seance> getListeSceances() {
        return listeSeance;
    }

    public void setListeSceances(HashMap<Integer, Seance> listeSceances) {
        this.listeSeance = listeSceances;
    }
    
    public void addSeance(Integer key, Seance seance) {
        this.listeSeance.put(key, seance);
    }

}
