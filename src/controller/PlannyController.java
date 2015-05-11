package controller;

import java.util.HashMap;
import model.DAO;
import model.Planning;

/**
 *
 * @author Yassine Doghri
 */
public class PlannyController {

    private HashMap<Integer, Planning> plannings;

    public void startApplication() {
        // TESTS Fonctionnels
//        DAO.initDatabase(); // Créer les tables si non existantes
//        plannings = DAO.initPlannings();
//
//        for (HashMap.Entry<Integer, Planning> entry : plannings.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue().getFormation(1).getModule(2).getSeance(1).getDateSeance());
//        }
    }

}
