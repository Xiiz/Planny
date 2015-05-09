package model;

import java.util.ArrayList;

/**
 *
 * @author Yassine Doghri
 */
public class Module {

    private String nom;
    private String abbr;
    private String couleur;
    private int nbSceances;
    private ArrayList<Sceance> listeSceances;

    public Module(String nom, String abbr, String couleur, int nbSceances, ArrayList<Sceance> listeSceances) {
        this.nom = nom;
        this.abbr = abbr;
        this.couleur = couleur;
        this.nbSceances = nbSceances;
        this.listeSceances = listeSceances;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNbSceances() {
        return nbSceances;
    }

    public void setNbSceances(int nbSceances) {
        this.nbSceances = nbSceances;
    }

    public ArrayList<Sceance> getListeSceances() {
        return listeSceances;
    }

    public void setListeSceances(ArrayList<Sceance> listeSceances) {
        this.listeSceances = listeSceances;
    }

}
