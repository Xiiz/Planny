package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe modèle pour l'objet Seance
 *
 * @author Yassine Doghri
 */
public class Seance {

    private Integer id;
    private int numSeance;
    private Date dateSeance;
    private Module module;
    private Formateur formateur;

    /**
     * Constructeur de la classe Seance
     * @param id
     * @param numSeance
     * @param dateSeance
     * @param module
     * @param formateur
     */
    public Seance(Integer id, int numSeance, Date dateSeance, Module module, Formateur formateur) {
        this.id = id;
        this.numSeance = numSeance;
        this.dateSeance = dateSeance;
        this.module = module;
        this.formateur = formateur;
    }

    /**
     *Constructeur de la classe Seance
     */
    public Seance() {
    }

    /**
     *Getter de l'attribut Id
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     *Setter de l'attribut Id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *Getter de l'attribut numSeance
     * @return int
     */
    public int getNumSeance() {
        return numSeance;
    }

    /**
     *Setter de l'attribut numSeance
     * @param numSeance
     */
    public void setNumSeance(int numSeance) {
        this.numSeance = numSeance;
    }

    /**
     *Getter de l'attribue dateSeance
     * @return Date
     */
    public Date getDateSeance() {
        return dateSeance;
    }

    /**
     *Setter de l'attribut dateSeance
     * @param dateSeance
     */
    public void setDateSeance(Date dateSeance) {
        this.dateSeance = dateSeance;
    }

    /**
     *Getter de l'attribut module
     * @return Module
     */
    public Module getModule() {
        return module;
    }

    /**
     *Setter de l'attribut module
     * @param module
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     *Getter de l'attribut formateur
     * @return Formateur
     */
    public Formateur getFormateur() {
        return formateur;
    }

    /**
     *Setter de l'attribut formateur
     * @param formateur
     */
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    /**
     *Renvoi dans une chaine de caractères le numSeance / le nombre de Seances total, la date de la séance et le formateur.
     * @return String
     */
    public String getInfoSeance() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMM yyyy");
        String date = sdf.format(dateSeance);
        return "Séance : n° de séance : " + numSeance + "/" + this.getModule().getNbSeances() + ", date de la séance : " + date + ", formateur : " + formateur + "<br />";
    }

    @Override
    public String toString() {
        String value = "<html>"
                + "<body style='width: 100%; text-align:center; padding: 10px; height:100px; color:#ffffff; background-color:" + module.getCouleur() + "'>"
                + "<h4 style='margin-bottom:0' id='" + module.getId() + "'>" + module.getNom() + " (" + module.getFormation().getNom() + ")</h4>"
                + "<br><span style=''>" + (float) module.getFormation().getDureeSceance() / 60 + " H</span>"
                + "<br>Form. " + formateur.getPrenom() + " " + formateur.getNom()
                + "<br><p id='" + this.id + "'>Seance : " + this.numSeance + "/" + module.getNbSeances()
                + "</body>"
                + "</html>";
        return value;
    }
}
