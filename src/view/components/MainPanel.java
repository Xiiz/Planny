package view.components;

import helper.CalendarHelper;
import controller.PlannyController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import view.PlanningFrame;

/**
 *
 * @author Amine
 */
public class MainPanel extends JPanel {

    private ToolBar toolBar;
    private NavigationBar navigationBar;
    private PlanningTable planningTable;
    private JScrollPane planningSP;

    public MainPanel(PlanningFrame mainFrame) {
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(900, 600));
        this.setBackground(Color.white);
        createToolBar();
        navigationBar = new NavigationBar(mainFrame);
        this.add(navigationBar, BorderLayout.SOUTH);
        add(toolBar, BorderLayout.NORTH);

        createPlanningTable(null);

        this.add(planningSP, BorderLayout.CENTER);

        int rowSize = (this.getComponent(2).getPreferredSize().height + 100) / 2;
        planningTable.setRowHeight(rowSize);
    }

    public final void createToolBar() {
        toolBar = new ToolBar();
    }

    public final void createPlanningTable(Date date) {
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
        planningTable = new PlanningTable(rowData, daysColumns.toArray());
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
