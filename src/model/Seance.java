package model;

import java.util.Date;

/**
 * Classe modèle pour l'objet Seance
 * 
 * @author Yassine Doghri
 */
public class Seance {

    private int id;
    private int numSeance;
    private Date dateSeance;
    private Module module;
    private Formateur formateur;

    public Seance(int id, int numSeance, Date dateSeance, Module module, Formateur formateur) {
        this.id = id;
        this.numSeance = numSeance;
        this.dateSeance = dateSeance;
        this.module = module;
        this.formateur = formateur;
    }

    public Seance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
