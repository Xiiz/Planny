package view.components;

import controller.CalendarController;
import controller.PlannyController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

/**
 *
 * @author Amine
 */
public class MainPanel extends JPanel {

    private JToolBar toolBar;
    private JToolBar navigationBar;
    private PlanningTable planningTable;
    private JScrollPane planningSP;

    public MainPanel(PlannyController controller) {
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(900, 600));
        this.setBackground(Color.white);
        createToolBar();
        createNavigationBar();
        this.add(navigationBar, BorderLayout.SOUTH);
        add(toolBar, BorderLayout.NORTH);

        createPlanningTable(controller);

        this.add(planningSP, BorderLayout.CENTER);

        int rowSize = (this.getComponent(2).getPreferredSize().height + 100) / 2;
        planningTable.setRowHeight(rowSize);
    }

    public final void createToolBar() {
        toolBar = new ToolBar();
    }

    public final void createPlanningTable(PlannyController controller) {
        Object rowData[][] = {{"Matin", "Matin", "Matin", "Matin", "Matin", "Matin", "Matin"},
        {"Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi", "Après-Midi"}};

        ArrayList<String> daysColumns = new ArrayList();
        ArrayList<Date> weekDays = controller.getCalendar().getWeekDays();
        for (Date wd : weekDays) {
            daysColumns.add(CalendarController.getDayColumnLabel(wd));
        }
        planningTable = new PlanningTable(rowData, daysColumns.toArray());
        planningSP = new JScrollPane(planningTable);
        planningSP.setBorder(BorderFactory.createEmptyBorder());
    }

    public final void createNavigationBar() {
        navigationBar = new NavigationBar();
    }
}
