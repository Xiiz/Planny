package model;

import java.util.HashMap;

/**
 *
 * @author Yassine Doghri
 */
public class Module {

    private int id;
    private String nom;
    private String abbr;
    private String couleur;
    private int nbSceances;
    private HashMap<Integer, Sceance> listeSceances;

    public Module(int id, String nom, String abbr, String couleur, int nbSceances, HashMap<Integer, Sceance> listeSceances) {
        this.id = id;
        this.nom = nom;
        this.abbr = abbr;
        this.couleur = couleur;
        this.nbSceances = nbSceances;
        this.listeSceances = listeSceances;
    }

    public Module() {
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

    public HashMap<Integer, Sceance> getListeSceances() {
        return listeSceances;
    }

    public void setListeSceances(HashMap<Integer, Sceance> listeSceances) {
        this.listeSceances = listeSceances;
    }

}
