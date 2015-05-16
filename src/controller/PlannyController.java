package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAO;
import model.Planning;
import view.PlanningFrame;
import view.PlannySplash;

/**
 *
 * @author Yassine Doghri
 */
public class PlannyController {

    private HashMap<Integer, Planning> plannings;
    private final CalendarController calendar;
    
    public PlannyController() {
        calendar = new CalendarController();
    }

    public void startApplication() {
        try {
            PlannySplash splashScreen = new PlannySplash(this);
            splashScreen.setVisible(true);

            // TESTS Fonctionnels
//            for (HashMap.Entry<Integer, Planning> entry : plannings.entrySet()) {
//                System.out.println(entry.getKey() + " : " + entry.getValue().getFormation(1).getModule(2).getSeance(1).getDateSeance());
//            }
        } catch (IOException ex) {
            Logger.getLogger(PlannyController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startPlanningFrame() {
        PlanningFrame mainFrame = new PlanningFrame(this);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void loadData() {
        DAO.initDatabase(); // Créer les tables si non existantes
        plannings = DAO.initPlannings();
    }

    public Planning getPlanning(String planningYear) {
        for (HashMap.Entry<Integer, Planning> entry : plannings.entrySet()) {
            if (entry.getValue().getAnneePlanning().equals(planningYear)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public HashMap<Integer, Planning> getPlannings() {
        return plannings;
    }

    public CalendarController getCalendar() {
        return calendar;
    }

}
