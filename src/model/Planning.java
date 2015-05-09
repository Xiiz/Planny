package model;

import java.util.ArrayList;

/**
 *
 * @author Yassine Doghri
 */
public class Planning {

    private ArrayList<Formation> listeFormations;

    public Planning(ArrayList<Formation> listeFormations) {
        this.listeFormations = listeFormations;
    }

    public ArrayList<Formation> getListeFormations() {
        return listeFormations;
    }

    public void setListeFormations(ArrayList<Formation> listeFormations) {
        this.listeFormations = listeFormations;
    }

    public void addFormation(Formation formation) {
        this.listeFormations.add(formation);
    }

    public void removeFormation(Formation formation) {
        for (Formation f : this.listeFormations) {
            if (f == formation) {
                this.listeFormations.remove(f);
            }
        }
    }
}
