package model;

import java.util.HashMap;

/**
 * Classe modèle pour l'objet Module
 *
 * @author Yassine Doghri
 */
public class Module {

    private Integer id;
    private String nom;
    private String abbr;
    private String couleur;
    private int nbSeances;
    private Formation formation;
    private HashMap<Integer, Seance> listeSeances = new HashMap();

    /**
     * Constructeur de l'objet Module
     *
     * @param id
     * @param nom
     * @param abbr
     * @param couleur
     * @param nbSeances
     * @param formation
     * @param listeSeances
     */
    public Module(Integer id, String nom, String abbr, String couleur, int nbSeances, Formation formation, HashMap<Integer, Seance> listeSeances) {
        this.id = id;
        this.nom = nom;
        this.abbr = abbr;
        this.couleur = couleur;
        this.nbSeances = nbSeances;
        this.formation = formation;
        this.listeSeances = listeSeances;
    }

    /**
     * Constructeur
     */
    public Module() {
    }

    /**
     * Retourne l'id du module
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Affecte un id donné au module
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retourne le nom du module
     *
     * @return String nom module
     */
    public String getNom() {
        return nom;
    }

    /**
     * Affecte un nom donné au module
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne l'abbréviation du module
     *
     * @return String abbréviation module
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * Affecte une abbréviation donnée au module
     *
     * @param abbr
     */
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    /**
     * Retourne la couleure du module (en hexa)
     *
     * @return String hexa de la couleur
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Affecte une couleur donnée en hexa au module
     *
     * @param couleur
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Retourne le nombre de séances du module
     *
     * @return nombre séances
     */
    public int getNbSeances() {
        return nbSeances;
    }

    /**
     * Affecte le nombre de séances donné au module
     *
     * @param nbSeances
     */
    public void setNbSeances(int nbSeances) {
        this.nbSeances = nbSeances;
    }

    /**
     * Retourne la formation du module
     *
     * @return Formation
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * Affecte une formation donnée au module
     *
     * @param formation
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     * Retourne la liste des séances du module
     *
     * @return liste des séances
     */
    public HashMap<Integer, Seance> getListeSeances() {
        return listeSeances;
    }

    /**
     * Retourne la séance du module identifiée par la clé donnée
     *
     * @param key
     * @return Seance
     */
    public Seance getSeance(Integer key) {
        return listeSeances.get(key);
    }

    /**
     * Affecte une liste de séances à la liste de séances du module
     *
     * @param listeSeances
     */
    public void setListeSeances(HashMap<Integer, Seance> listeSeances) {
        this.listeSeances = listeSeances;
    }

    /**
     * Ajoute une séance donnée au module. Retourne une erreur si le nombre
     * d'éléments de la liste est supérieur au nombre de séances dans le module.
     *
     * @param key
     * @param seance
     * @throws Exception
     */
    public void addSeance(Integer key, Seance seance) throws Exception {
        if (listeSeances.size() + 1 > nbSeances) {
            throw new Exception("Attention ! Le nombre limite de séances est atteind !");
        } else {
            this.listeSeances.put(key, seance);
        }
    }

    /**
     * Retire une séance de la liste des séances du module grâce à une clé
     * donnée
     *
     * @param key
     */
    public void removeSeance(Integer key) {
        listeSeances.remove(key);
    }

    @Override
    public String toString() {
        return nom;
    }

    /**
     * Retourne un code HTML donnant le détail du module
     *
     * @return String code HTML
     */
    public String detailModule() {
        return "<p style='background-color:" + this.getCouleur() + ";diplay:inline-block;padding:10px; color:#ffffff'>" + this.getNom() + " </p> <br /> Abréviation : " + this.getAbbr() + ", de couleur : <span style='display: inline-block;background-color:" + this.getCouleur() + ";width:20px;height:10px'></span><br />";
    }
}
