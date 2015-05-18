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

    /**
     * Contructeur de l'objet formation
     *
     * @param id
     * @param nom
     * @param dureeSceance
     * @param planning
     * @param listeModules
     */
    public Formation(Integer id, String nom, int dureeSceance, Planning planning, HashMap<Integer, Module> listeModules) {
        this.id = id;
        this.nom = nom;
        this.dureeSceance = dureeSceance;
        this.planning = planning;
        this.listeModules = listeModules;
    }

    /**
     * Constructeur
     */
    public Formation() {
    }

    /**
     * Retourne l'id de la formation
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Affecte un id donné à une formation
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la formation
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Affecte un nom donné à une formation
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la durée type d'une séance pour la formation
     *
     * @return
     */
    public int getDureeSceance() {
        return dureeSceance;
    }

    /**
     * Affecte une durée type de séance à une formation
     *
     * @param dureeSceance
     */
    public void setDureeSceance(int dureeSceance) {
        this.dureeSceance = dureeSceance;
    }

    /**
     * Retourne le planning de la formation
     *
     * @return
     */
    public Planning getPlanning() {
        return planning;
    }

    /**
     * Affecte un planning à la formation
     *
     * @param planning
     */
    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    /**
     * Retourne la liste des modules de la formation
     *
     * @return
     */
    public HashMap<Integer, Module> getListeModules() {
        return listeModules;
    }

    /**
     * Retourne un des modules de la formation identifé par une clé
     *
     * @param key
     * @return module
     */
    public Module getModule(Integer key) {
        return listeModules.get(key);
    }

    /**
     * Retourne le module de la formation avec un nom donné
     *
     * @param nom
     * @return module
     */
    public Module getModuleByNom(String nom) {
        for (HashMap.Entry<Integer, Module> entry : listeModules.entrySet()) {
            if (entry.getValue().getNom().equals(nom)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Affecte une liste de modules à la formation
     *
     * @param listeModules
     */
    public void setListeModules(HashMap<Integer, Module> listeModules) {
        this.listeModules = listeModules;
    }

    /**
     * Ajoute un module à la liste des modules de la formation
     *
     * @param key
     * @param module
     */
    public void addModule(Integer key, Module module) {
        this.listeModules.put(key, module);
    }

    /**
     * Retourne le nombre total d'heures de la formation
     *
     * @return float nombre d'heures
     */
    public float getHeureTotalFormation() {
        int i = 0;
        for (HashMap.Entry<Integer, Module> unModule : this.getListeModules().entrySet()) {
            Module leModule = unModule.getValue();
            i += leModule.getListeSeances().size() * this.getDureeSceance();
        }
        return (float) i / 60;
    }

    @Override
    public String toString() {
        String string = "<html><h3>Formation : " + nom + " [" + this.getHeureTotalFormation() + "h]<h3>"
                + "<p>Duree type /séance : " + (float) dureeSceance / 60 + "h</p>";

        if (listeModules.size() > 0) {
            string += "<p>Modules :</p><ul>";
            for (HashMap.Entry<Integer, Module> entry : listeModules.entrySet()) {
                string += "<li>" + entry.getValue() + "</li>";
            }
            string += "</ul>";
        } else {
            string += "<em>Aucun Module</em>";
        }
        return string + "</html>";
    }

}
