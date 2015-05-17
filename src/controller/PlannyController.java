package controller;

import helper.CalendarHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableCellEditor;
import model.DAO;
import model.Formateur;
import model.Formation;
import model.Module;
import model.Planning;
import model.Seance;
import model.provider.FormateurProvider;
import view.PlanningFrame;
import view.PlannySplash;
import view.components.MainPanel;
import view.components.PlanningTable;
import view.components.SidebarPanel;

/**
 *
 * @author Yassine Doghri
 */
public class PlannyController {

    private HashMap<Integer, Planning> plannings;
    private PlanningFrame mainFrame;

    public void startApplication() {
        try {
            PlannySplash splashScreen = new PlannySplash(this);
            splashScreen.setVisible(true);

        } catch (IOException ex) {
            Logger.getLogger(PlannyController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startPlanningFrame() {
        mainFrame = new PlanningFrame(this);
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

    public void updatePlanningView(Calendar calendar) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());

        ArrayList<Date> weekDays = CalendarHelper.getWeekDays(cal);
        ArrayList<Seance> seances = getSeancesPlanning(CalendarHelper.getPlanningYear(cal.getTime()));

        PlanningTable planning = mainFrame.getMainPanel().getPlanningTable();
        // Repaint Planning
        for (Date d : weekDays) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(d);
            planning.setValueAt("Matin", 0, CalendarHelper.getWeekNumber(cd));
            planning.setValueAt("Après-midi", 1, CalendarHelper.getWeekNumber(cd));
        }
        for (Date d : weekDays) {
            System.out.println("test");
            for (Seance s : seances) {
                boolean isSameDay = CalendarHelper.isSameDay(s.getDateSeance(), d);
                if (isSameDay) {
                    Calendar dateSeance = Calendar.getInstance();
                    dateSeance.setTime(s.getDateSeance());

                    if (dateSeance.get(Calendar.HOUR_OF_DAY) <= 12) {
                        System.out.println("Matin");
                        TableCellEditor c = planning.getCellEditor(0, CalendarHelper.getWeekNumber(dateSeance));

                        planning.setValueAt(s, 0, CalendarHelper.getWeekNumber(dateSeance));
                    } else {
                        System.out.println("Aprem");
                        planning.setValueAt(s, 1, CalendarHelper.getWeekNumber(dateSeance));
                    }
                }
            }
        }
    }

    /**
     * Retourne toutes les séances disponibles
     *
     * @param planningYear
     * @return
     */
    public ArrayList<Seance> getSeancesPlanning(String planningYear) {
        Planning planning = getPlanning(planningYear);
        ArrayList<Seance> seances = new ArrayList();
        for (HashMap.Entry<Integer, Formation> entry : planning.getListeFormations().entrySet()) {
            for (HashMap.Entry<Integer, Module> entry2 : entry.getValue().getListeModules().entrySet()) {
                for (HashMap.Entry<Integer, Seance> entry3 : entry2.getValue().getListeSeances().entrySet()) {
                    seances.add(entry3.getValue());
                }
            }
        }
        return seances;
    }

    public void updateView(Calendar calendar) {
        SidebarPanel sidebarPanel = mainFrame.getSidebarPanel();
        MainPanel mainPanel = mainFrame.getMainPanel();
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());

        // refresh Sidebar
        // Planning year
        sidebarPanel.getPlanningYear().setText(CalendarHelper.getPlanningYear(cal.getTime()));

        // refresh planning week
        PlanningTable planningTable = mainPanel.getPlanningTable();
        planningTable.changeColumnHeaders(CalendarHelper.getWeekDays(cal));

        // refresh week label interval
        mainPanel.getNavigationBar().getWeekLabel().setText(CalendarHelper.getWeekInterval(cal.getTime()));
    }

    public void updateViewNextWeek() {
        SidebarPanel sidebarPanel = mainFrame.getSidebarPanel();
        MainPanel mainPanel = mainFrame.getMainPanel();

        // get selected Date
        Calendar calendar = sidebarPanel.getCalendar().getCalendar();

        // refresh planning table
        PlanningTable planningTable = mainPanel.getPlanningTable();
        planningTable.changeColumnHeaders(CalendarHelper.getNextWeek(calendar.getTime()));

        // Change JCalendar Date
        sidebarPanel.getCalendar().setDate(CalendarHelper.getMondayOfNextWeek(calendar));

        // Planning year
        sidebarPanel.getPlanningYear().setText("Planning : " + CalendarHelper.getPlanningYear(calendar.getTime()));

        // set weekLabel
        mainPanel.getNavigationBar().getWeekLabel().setText(CalendarHelper.getWeekInterval(calendar.getTime()));
    }

    public void updateViewPrevWeek() {
        SidebarPanel sidebarPanel = mainFrame.getSidebarPanel();
        MainPanel mainPanel = mainFrame.getMainPanel();

        // get selected Date
        Calendar calendar = sidebarPanel.getCalendar().getCalendar();

        // refresh planning table
        PlanningTable planningTable = mainPanel.getPlanningTable();
        planningTable.changeColumnHeaders(CalendarHelper.getNextWeek(calendar.getTime()));

        // Change JCalendar Date
        sidebarPanel.getCalendar().setDate(CalendarHelper.getMondayOfPrevWeek(calendar));

        // Planning year
        sidebarPanel.getPlanningYear().setText("Planning : " + CalendarHelper.getPlanningYear(calendar.getTime()));

        // set weekLabel
        mainPanel.getNavigationBar().getWeekLabel().setText(CalendarHelper.getWeekInterval(calendar.getTime()));
    }

    public ArrayList<String> getAllFormateurs() {
        ArrayList<String> formateurs = new ArrayList();
        for (Map.Entry<Integer, Formateur> entry : DAO.getAllFormateurs().entrySet()) {
            formateurs.add(entry.getValue().toString());
        }
        return formateurs;
    }

    public ArrayList<String> getAllModules() {
        ArrayList<String> modules = new ArrayList();
        for (Map.Entry<Integer, Module> entry : DAO.getAllModules().entrySet()) {
            modules.add(entry.getValue().toString());
        }
        return modules;
    }
    
    public void addSeance(Seance seance) {
        // getPlanning
//        Planning planning = getPlanning(CalendarHelper.getPlanningYear(seance.getDateSeance()));
        DAO.addSeance(seance);
    }
}
