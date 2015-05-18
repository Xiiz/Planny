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

    /**
     * Constructeur
     */
    public Formateur() {
    }

    /**
     * Retourne l'id du Formateur
     *
     * @return int id du formateur
     */
    public Integer getId() {
        return id;
    }

    /**
     * Affecte un id donné à un formateur
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retourne le nom du Formateur
     *
     * @return String nom du formateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Affecte un nom donné à un formateur
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom du formateur
     *
     * @return String prénom du formateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Affecte un prénom donné à un formateur
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne les initiales du Formateur
     *
     * @return String initiales formateur
     */
    public String getInitiales() {
        return initiales;
    }

    /**
     * Affecte des initiales données à un formateur
     *
     * @param initiales
     */
    public void setInitiales(String initiales) {
        this.initiales = initiales;
    }

    /**
     * Retourne téléphone du Formateur
     *
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Affecte un numéro de téléphone donné à un formateur
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Retourne l'email du formateur
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Affecte un email donné à un formateur
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne la liste des séances du formateur
     *
     * @return
     */
    public HashMap<Integer, Seance> getListeSceances() {
        return listeSeance;
    }

    /**
     * Affecte une liste de séances données à un formateur
     *
     * @param listeSceances
     */
    public void setListeSceances(HashMap<Integer, Seance> listeSceances) {
        this.listeSeance = listeSceances;
    }

    /**
     * Ajoute une séance à la liste des séances du formateur
     *
     * @param key
     * @param seance
     */
    public void addSeance(Integer key, Seance seance) {
        this.listeSeance.put(key, seance);
    }

    /**
     * Retire une séance donnée à la liste des séances du formateur
     *
     * @param key
     */
    public void removeSeance(Integer key) {
        listeSeance.remove(key);
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }

}
