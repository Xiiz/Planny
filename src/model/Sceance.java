package model;

import java.util.Date;

/**
 *
 * @author Yassine Doghri
 */
public class Sceance {

    private int id;
    private int numSceance;
    private Date dateSceance;
    private Module module;
    private Formateur formateur;

    public Sceance(int id, int numSceance, Date dateSceance, Module module, Formateur formateur) {
        this.id = id;
        this.numSceance = numSceance;
        this.dateSceance = dateSceance;
        this.module = module;
        this.formateur = formateur;
    }

    public Sceance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumSceance() {
        return numSceance;
    }

    public void setNumSceance(int numSceance) {
        this.numSceance = numSceance;
    }

    public Date getDateSceance() {
        return dateSceance;
    }

    public void setDateSceance(Date dateSceance) {
        this.dateSceance = dateSceance;
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
