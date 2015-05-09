package model;

import java.util.ArrayList;

/**
 *
 * @author Yassine Doghri
 */
public class Formation {

    private String nom;
    private int dureeSceance;
    private ArrayList<Module> listeModules;

    public Formation(String nom, int dureeSceance, ArrayList<Module> listeModules) {
        this.nom = nom;
        this.dureeSceance = dureeSceance;
        this.listeModules = listeModules;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDureeSceance() {
        return dureeSceance;
    }

    public void setDureeSceance(int dureeSceance) {
        this.dureeSceance = dureeSceance;
    }

    public ArrayList<Module> getListeModules() {
        return listeModules;
    }

    public void setListeModules(ArrayList<Module> listeModules) {
        this.listeModules = listeModules;
    }

}
