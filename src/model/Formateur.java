package model;

/**
 *
 * @author Yassine Doghri
 */
public class Formateur {

    private String nom;
    private String prenom;
    private String initiales;
    private String telephone;
    private String email;

    public Formateur(String nom, String prenom, String initiales, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.initiales = initiales;
        this.telephone = telephone;
        this.email = email;
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

}
