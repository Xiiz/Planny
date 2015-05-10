package model;

import java.util.HashMap;

/**
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
