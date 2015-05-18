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

    public Seance(Integer id, int numSeance, Date dateSeance, Module module, Formateur formateur) {
        this.id = id;
        this.numSeance = numSeance;
        this.dateSeance = dateSeance;
        this.module = module;
        this.formateur = formateur;
    }

    public Seance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumSeance() {
        return numSeance;
    }

    public void setNumSeance(int numSeance) {
        this.numSeance = numSeance;
    }

    public Date getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(Date dateSeance) {
        this.dateSeance = dateSeance;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public String getInfoSeance() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMM yyyy");
        String date = sdf.format(dateSeance);
        return "Séance : n° de séance : " + numSeance + ", date de la séance : " + date + ", formateur : " + formateur + "<br />";
    }
}
