package model;

import java.util.HashMap;

/**
 * Classe modèle pour l'objet Planning
 *
 * @author Yassine Doghri
 */
public class Planning {

    private Integer id;
    private String anneePlanning;
    private HashMap<Integer, Formation> listeFormations = new HashMap();

    /**
     * Constructeur de l'objet Planning
     *
     * @param id
     * @param anneePlanning
     * @param listeFormations
     */
    public Planning(Integer id, String anneePlanning, HashMap<Integer, Formation> listeFormations) {
        this.id = id;
        this.anneePlanning = anneePlanning;
        this.listeFormations = listeFormations;
    }

    /**
     * Constructeur
     */
    public Planning() {
    }

    /**
     * Retourne l'id du planning
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Affecte une id donnée au planning
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retourne l'année du planning
     * @return String année planning
     */
    public String getAnneePlanning() {
        return anneePlanning;
    }

    /**
     * Affecte une année donnée au planning
     * @param anneePlanning
     */
    public void setAnneePlanning(String anneePlanning) {
        this.anneePlanning = anneePlanning;
    }

    /**
     * Retourne la liste des fomations du planning
     * @return liste formations
     */
    public HashMap<Integer, Formation> getListeFormations() {
        return listeFormations;
    }

    /**
     * Retourne une formation grâce à une clé donnée
     * @param key
     * @return Formation
     */
    public Formation getFormation(Integer key) {
        return listeFormations.get(key);
    }

    /**
     * Affecte une liste de formation donnée à la liste de formation du planning
     * @param listeFormations
     */
    public void setListeFormations(HashMap<Integer, Formation> listeFormations) {
        this.listeFormations = listeFormations;
    }

    /**
     * Ajoute une formation donnée à la liste de formations du planning
     * @param key
     * @param formation
     */
    public void addFormation(Integer key, Formation formation) {
        this.listeFormations.put(key, formation);
    }

    /**
     * Retire une formation identifiée par une clé donnée
     * @param key
     */
    public void removeFormation(Integer key) {
        this.listeFormations.remove(key);
    }
}
