package controller;

import helper.CalendarHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellEditor;
import model.DAO;
import model.Formateur;
import model.Formation;
import model.Module;
import model.Planning;
import model.Seance;
import org.jsoup.Jsoup;
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

    public String getSelectedFormation() {
        JComboBox combo = mainFrame.getSidebarPanel().getComboFormations();
        return combo.getSelectedItem().toString();
    }

    public void updateFormationInfos(Calendar calendar) {
        ArrayList<String> formationsS = this.getFormations(CalendarHelper.getPlanningYear(calendar.getTime()));
        JComboBox combo = mainFrame.getSidebarPanel().getComboFormations();
        combo.removeAllItems();
        for (String s : formationsS) {
            combo.addItem(s);
        }
    }

    public void updateFormationLabel(String nom) {
        JLabel label = mainFrame.getSidebarPanel().getInfosFormationLabel();
        label.setText(this.getFormation(nom).toString());
    }

    public void updatePlanningView(Calendar calendar, String nomFormation) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(calendar.getTime());

        ArrayList<Date> weekDays = CalendarHelper.getWeekDays(cal);
        ArrayList<Seance> seances = getSeancesPlanning(CalendarHelper.getPlanningYear(cal.getTime()), nomFormation);

        PlanningTable planning = mainFrame.getMainPanel().getPlanningTable();
        // Repaint Planning
        for (Date d : weekDays) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(d);
            planning.setValueAt("Matin", 0, CalendarHelper.getWeekNumber(cd));
            planning.setValueAt("Après-Midi", 1, CalendarHelper.getWeekNumber(cd));
        }
        for (Date d : weekDays) {
            for (Seance s : seances) {
                boolean isSameDay = CalendarHelper.isSameDay(s.getDateSeance(), d);
                if (isSameDay) {
                    Calendar dateSeance = Calendar.getInstance();
                    dateSeance.setTime(s.getDateSeance());

                    if (dateSeance.get(Calendar.HOUR_OF_DAY) <= 12) {
                        // Matin
                        TableCellEditor c = planning.getCellEditor(0, CalendarHelper.getWeekNumber(dateSeance));

                        planning.setValueAt(s, 0, CalendarHelper.getWeekNumber(dateSeance));
                    } else {
                        // Aprem
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
    public ArrayList<Seance> getSeancesPlanning(String planningYear, String nomFormation) {
        Planning planning = getPlanning(planningYear);
        ArrayList<Seance> seances = new ArrayList();
        for (HashMap.Entry<Integer, Formation> entry : planning.getListeFormations().entrySet()) {
            if (entry.getValue().getNom().equals(nomFormation)) {
                for (HashMap.Entry<Integer, Module> entry2 : entry.getValue().getListeModules().entrySet()) {
                    for (HashMap.Entry<Integer, Seance> entry3 : entry2.getValue().getListeSeances().entrySet()) {
                        seances.add(entry3.getValue());
                    }
                }
            }
        }
        return seances;
    }

    public Seance getSeanceFromHtml(String html) {
        if (!html.equals("Matin") || !html.equals("Après-Midi")) {
            org.jsoup.nodes.Document doc = Jsoup.parse(html);
            org.jsoup.nodes.Element module = doc.select("h4").first();
            org.jsoup.nodes.Element seance = doc.select("p").first();

            int idModule = Integer.parseInt(module.id());
            int idSeance = Integer.parseInt(seance.id());

            return getSeance(idSeance, idModule);
        }
        return null;
    }

    public Seance getSeance(int idSeance, int idModule) {
        Planning planning = getCurrentPlanning();
        for (HashMap.Entry<Integer, Formation> entry : planning.getListeFormations().entrySet()) {
            for (HashMap.Entry<Integer, Module> entry2 : entry.getValue().getListeModules().entrySet()) {
                for (HashMap.Entry<Integer, Seance> entry3 : entry2.getValue().getListeSeances().entrySet()) {
                    if ((entry3.getValue().getId() == idSeance) && (entry3.getValue().getModule().getId() == idModule)) {
                        return entry3.getValue();
                    }
                }
            }
        }
        return null;
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
        Calendar calendar = sidebarPanel.getJCalendar().getCalendar();

        // refresh planning table
        PlanningTable planningTable = mainPanel.getPlanningTable();
        planningTable.changeColumnHeaders(CalendarHelper.getNextWeek(calendar.getTime()));

        // Change JCalendar Date
        sidebarPanel.getJCalendar().setDate(CalendarHelper.getMondayOfNextWeek(calendar));

        // Planning year
        sidebarPanel.getPlanningYear().setText("Planning : " + CalendarHelper.getPlanningYear(calendar.getTime()));

        // set weekLabel
        mainPanel.getNavigationBar().getWeekLabel().setText(CalendarHelper.getWeekInterval(calendar.getTime()));
    }

    public void updateViewPrevWeek() {
        SidebarPanel sidebarPanel = mainFrame.getSidebarPanel();
        MainPanel mainPanel = mainFrame.getMainPanel();

        // get selected Date
        Calendar calendar = sidebarPanel.getJCalendar().getCalendar();

        // refresh planning table
        PlanningTable planningTable = mainPanel.getPlanningTable();
        planningTable.changeColumnHeaders(CalendarHelper.getNextWeek(calendar.getTime()));

        // Change JCalendar Date
        sidebarPanel.getJCalendar().setDate(CalendarHelper.getMondayOfPrevWeek(calendar));

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

    public void addSeance(Seance seance, Module module, Formateur formateur) {
        try {
            // set seance to lists
            module.addSeance(seance.getId(), seance);
            formateur.addSeance(seance.getId(), seance);

            DAO.addSeance(seance);
        } catch (Exception ex) {
            Logger.getLogger(PlannyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNextSeanceId() {
        Planning planning = getPlanning(CalendarHelper.getPlanningYear(this.getSelectedDate().getTime()));
        Entry<Integer, Seance> maxEntry = null;
        for (HashMap.Entry<Integer, Formation> entry : planning.getListeFormations().entrySet()) {
            for (HashMap.Entry<Integer, Module> entry2 : entry.getValue().getListeModules().entrySet()) {
                for (HashMap.Entry<Integer, Seance> entry3 : entry2.getValue().getListeSeances().entrySet()) {
                    if (maxEntry == null || entry3.getKey() > maxEntry.getKey()) {
                        maxEntry = entry3;
                    }
                }
            }
        }
        if (maxEntry == null) {
            return 1;
        } else {
            return maxEntry.getKey() + 1;
        }
    }

    /**
     *
     * @param nom
     * @param dateSeance
     * @return
     */
    public Module getModule(String nom, Date dateSeance) {
        Planning planning = getPlanning(CalendarHelper.getPlanningYear(dateSeance));
        for (HashMap.Entry<Integer, Formation> entry : planning.getListeFormations().entrySet()) {
            return entry.getValue().getModuleByNom(nom);
        }
        return null;
    }

    public Formateur getFormateur(String nom, String planningYear) {
        Planning planning = getPlanning(planningYear);
        for (HashMap.Entry<Integer, Formation> entry : planning.getListeFormations().entrySet()) {
            for (HashMap.Entry<Integer, Module> entry2 : entry.getValue().getListeModules().entrySet()) {
                for (HashMap.Entry<Integer, Seance> entry3 : entry2.getValue().getListeSeances().entrySet()) {
                    String nomFormateur = entry3.getValue().getFormateur().getPrenom() + " " + entry3.getValue().getFormateur().getNom();
                    if (nomFormateur.equals(nom)) {
                        return entry3.getValue().getFormateur();
                    }
                }
            }
        }
        String[] parts = nom.split(" ");
        return DAO.getFormateur(parts[0], parts[1]);
    }

    public Calendar getSelectedDate() {
        return mainFrame.getSidebarPanel().getJCalendar().getCalendar();
    }

    public ArrayList<String> getAnneesPlannings() {
        ArrayList<String> listeAnnees = new ArrayList();
        for (HashMap.Entry<Integer, Planning> entry : this.getPlannings().entrySet()) {
            Planning unPlanning = entry.getValue();
            listeAnnees.add(unPlanning.getAnneePlanning());
        }
        return listeAnnees;
    }

    public int getNextFormationId() {
        Entry<Integer, Formation> maxEntry = null;
        for (HashMap.Entry<Integer, Planning> entry : plannings.entrySet()) {
            for (HashMap.Entry<Integer, Formation> entry2 : entry.getValue().getListeFormations().entrySet()) {
                if (maxEntry == null || entry2.getKey() > maxEntry.getKey()) {
                    maxEntry = entry2;
                }
            }
        }
        if (maxEntry == null) {
            return 1;
        } else {
            return maxEntry.getKey() + 1;
        }
    }

    public void addFormation(Formation formation, Planning planning) {
        planning.addFormation(formation.getId(), formation);

        DAO.addFormation(formation);
    }

    public ArrayList<String> getFormations(String planningYear) {
        ArrayList<String> listeFormations = new ArrayList();
        for (HashMap.Entry<Integer, Formation> entry : getPlanning(planningYear).getListeFormations().entrySet()) {
            listeFormations.add(entry.getValue().getNom());
        }
        return listeFormations;
    }

    public int getNextModuleId() {
        Entry<Integer, Module> maxEntry = null;
        for (HashMap.Entry<Integer, Planning> entry : plannings.entrySet()) {
            for (HashMap.Entry<Integer, Formation> entry2 : entry.getValue().getListeFormations().entrySet()) {
                for (HashMap.Entry<Integer, Module> entry3 : entry2.getValue().getListeModules().entrySet()) {
                    if (maxEntry == null || entry3.getKey() > maxEntry.getKey()) {
                        maxEntry = entry3;
                    }
                }
            }
        }
        if (maxEntry == null) {
            return 1;
        } else {
            return maxEntry.getKey() + 1;
        }
    }

    public Formation getFormation(String nom) {
        for (HashMap.Entry<Integer, Formation> entry : getCurrentPlanning().getListeFormations().entrySet()) {
            if (entry.getValue().getNom().equals(nom)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public int getNextPlanningId() {
        Entry<Integer, Planning> maxEntry = null;
        for (HashMap.Entry<Integer, Planning> entry : plannings.entrySet()) {
            if (maxEntry == null || entry.getKey() > maxEntry.getKey()) {
                maxEntry = entry;
            }
        }
        if (maxEntry == null) {
            return 1;
        } else {
            return maxEntry.getKey() + 1;
        }
    }

    public Planning getCurrentPlanning() {
        Planning planning = this.getPlanning(CalendarHelper.getPlanningYear(this.getSelectedDate().getTime()));
        if (planning == null) {
            return new Planning(getNextPlanningId(), CalendarHelper.getPlanningYear(this.getSelectedDate().getTime()), null);
        } else {
            return planning;
        }
    }

    public void addModule(Formation formation, Module module) {
        formation.addModule(module.getId(), module);
        DAO.addModule(module);
    }

    public void addFormateur(Formateur formateur) {
        DAO.addFormateur(formateur);
    }

    public int getNextFormateurId() {
        Integer maxEntry = null;
        Planning planning = this.getCurrentPlanning();
        for (HashMap.Entry<Integer, Formation> entry : planning.getListeFormations().entrySet()) {
            for (HashMap.Entry<Integer, Module> entry2 : entry.getValue().getListeModules().entrySet()) {
                for (HashMap.Entry<Integer, Seance> entry3 : entry2.getValue().getListeSeances().entrySet()) {
                    if (maxEntry == null || entry3.getValue().getFormateur().getId() > maxEntry) {
                        maxEntry = entry3.getValue().getFormateur().getId();
                    }
                }
            }
        }
        if (maxEntry == null) {
            return 1;
        } else {
            return maxEntry + 1;
        }
    }

    public void createPlanning() {

    }
}
