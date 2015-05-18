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

    public Module(Integer id, String nom, String abbr, String couleur, int nbSeances, Formation formation, HashMap<Integer, Seance> listeSeances) {
        this.id = id;
        this.nom = nom;
        this.abbr = abbr;
        this.couleur = couleur;
        this.nbSeances = nbSeances;
        this.formation = formation;
        this.listeSeances = listeSeances;
    }

    public Module() {
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

    public int getNbSeances() {
        return nbSeances;
    }

    public void setNbSeances(int nbSeances) {
        this.nbSeances = nbSeances;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public HashMap<Integer, Seance> getListeSeances() {
        return listeSeances;
    }

    public Seance getSeance(Integer key) {
        return listeSeances.get(key);
    }

    public void setListeSeances(HashMap<Integer, Seance> listeSeances) {
        this.listeSeances = listeSeances;
    }

    public void addSeance(Integer key, Seance seance) throws Exception {
        if (listeSeances.size() + 1 > nbSeances) {
            throw new Exception("Attention ! Le nombre limite de séances est atteind !");
        } else {
            this.listeSeances.put(key, seance);
        }
    }

    public void removeSeance(Integer key) {
        listeSeances.remove(key);
    }

    @Override
    public String toString() {
        return nom;
    }

    public String detailModule() {
        return "Module : " + this.getNom() + "<br /> Abréviation : " + this.getAbbr() + ", de couleur : <span style='display: inline-block;background-color:" + this.getCouleur() + ";width:20px;height:10px'></span><br />";
    }
}
