package model;

import java.util.HashMap;

/**
 * Classe modèle pour l'objet Formation
 * 
 * @author Yassine Doghri
 */
public class Formation {

    private int id;
    private String nom;
    private int dureeSceance;
    private HashMap<Integer, Module> listeModules;

    public Formation(int id, String nom, int dureeSceance, HashMap<Integer, Module> listeModules) {
        this.id = id;
        this.nom = nom;
        this.dureeSceance = dureeSceance;
        this.listeModules = listeModules;
    }

    public Formation() {
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

    public int getDureeSceance() {
        return dureeSceance;
    }

    public void setDureeSceance(int dureeSceance) {
        this.dureeSceance = dureeSceance;
    }

    public HashMap<Integer, Module> getListeModules() {
        return listeModules;
    }
    
    public Module getModule(Integer key) {
        return listeModules.get(key);
    }

    public void setListeModules(HashMap<Integer, Module> listeModules) {
        this.listeModules = listeModules;
    }
    
    public void addModule(Integer key, Module module) {
        this.listeModules.put(key, module);
    }

}
