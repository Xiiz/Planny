package view.components;

import helper.CalendarHelper;
import controller.PlannyController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Amine
 */
public class MainPanel extends JPanel {

    private final ToolBar toolBar;
    private final NavigationBar navigationBar;
    private PlanningTable planningTable;
    private JScrollPane planningSP;
    private final PlannyController controller; 

    public MainPanel(PlannyController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(900, 600));
        this.setBackground(Color.white);
        toolBar = new ToolBar();
        navigationBar = new NavigationBar(controller);
        this.add(navigationBar, BorderLayout.SOUTH);
        add(toolBar, BorderLayout.NORTH);

        createPlanningTable(new Date());

        this.add(planningSP, BorderLayout.CENTER);

        int rowSize = (this.getComponent(2).getPreferredSize().height + 100) / 2;
        planningTable.setRowHeight(rowSize);
    }

    public final void createPlanningTable(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        Object rowData[][] = {{"Matin", "Matin", "Matin", "Matin", "Matin", "Matin", "Matin"},
        {"Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi"}};

        ArrayList<String> daysColumns = new ArrayList();
        ArrayList<Date> weekDays;
        if (date == null) {
            weekDays = CalendarHelper.getCurrentWeekDays();
        } else {
            weekDays = CalendarHelper.getCurrentWeekDays();
        }
        for (Date wd : weekDays) {
            daysColumns.add(CalendarHelper.getDayColumnLabel(wd));
        }
        planningTable = new PlanningTable(rowData, daysColumns.toArray(), controller);
        planningSP = new JScrollPane(planningTable);
        planningSP.setBorder(BorderFactory.createEmptyBorder());
    }

    public PlanningTable getPlanningTable() {
        return this.planningTable;
    }

    public NavigationBar getNavigationBar() {
        return this.navigationBar;
    }
}
