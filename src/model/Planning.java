package model;

import java.util.HashMap;

/**
 * Classe modèle pour l'objet Planning
 * 
 * @author Yassine Doghri
 */
public class Planning {

    private int id;
    private String anneePlanning;
    private HashMap<Integer, Formation> listeFormations;

    public Planning(int id, String anneePlanning, HashMap<Integer, Formation> listeFormations) {
        this.id = id;
        this.anneePlanning = anneePlanning;
        this.listeFormations = listeFormations;
    }

    public Planning() {
        
    }

    /**
     * Méthode qui va parcourir chaque Module récupéré de la base de donnée pour
     * les lier aux formations
     *
     * @param lesModules Modules présents dans la base de données
     */
    private void linkFormateurs(HashMap<Integer, Module> lesModules) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnneePlanning() {
        return anneePlanning;
    }

    public void setAnneePlanning(String anneePlanning) {
        this.anneePlanning = anneePlanning;
    }

    public HashMap<Integer, Formation> getListeFormations() {
        return listeFormations;
    }

    public Formation getFormation(Integer key) {
        return listeFormations.get(key);
    }

    public void setListeFormations(HashMap<Integer, Formation> listeFormations) {
        this.listeFormations = listeFormations;
    }

    public void addFormation(Integer key, Formation formation) {
        this.listeFormations.put(key, formation);
    }

    public void removeFormation(Integer key) {
        this.listeFormations.remove(key);
    }
}
