package model;

import java.util.HashMap;

/**
 * Classe modèle pour l'objet Formation
 *
 * @author Yassine Doghri
 */
public class Formation {

    private Integer id;
    private String nom;
    private int dureeSceance;
    private Planning planning;
    private HashMap<Integer, Module> listeModules = new HashMap();

    public Formation(Integer id, String nom, int dureeSceance, Planning planning, HashMap<Integer, Module> listeModules) {
        this.id = id;
        this.nom = nom;
        this.dureeSceance = dureeSceance;
        this.planning = planning;
        this.listeModules = listeModules;
    }

    public Formation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
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
