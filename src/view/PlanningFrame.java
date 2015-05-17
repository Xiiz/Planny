package view;

import controller.PlannyController;
import helper.CalendarHelper;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.*;
import view.components.MainPanel;
import view.components.SidebarPanel;
import view.components.MenuBar;
import view.components.PlanningTable;

/**
 * Classe de la fenêtre de planning
 *
 * @author Yassine Doghri
 * @author Mohammed Amine Bouazizi
 */
public class PlanningFrame extends JFrame {

    private final JSplitPane splitPaneH;
    private final SidebarPanel sidebarPanel;
    private final MainPanel mainPanel;
    private JPanel footerPanel;
    private final MenuBar menuBar;

    /**
     * Méthode constructeur pour la Fenêtre de Planning
     *
     * @param controller
     */
    public PlanningFrame(PlannyController controller) {
        setTitle("Planny");
        try {
            setIconImage(ImageIO.read(new File("src/view/components/images/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        getContentPane().setBackground(Color.white);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setSize(700, 700);
        topPanel.setBackground(Color.white);
        getContentPane().add(topPanel);

        //Create the menu bar
        menuBar = new MenuBar();

        // Create the panels
        sidebarPanel = new SidebarPanel(this);
        mainPanel = new MainPanel(this);
        createFooterPanel();

        // Create a splitter pane
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneH.setDividerSize(1);
        splitPaneH.setContinuousLayout(true);
        splitPaneH.setLeftComponent(sidebarPanel);
        splitPaneH.setRightComponent(mainPanel);
        topPanel.add(splitPaneH, BorderLayout.CENTER);
        topPanel.add(footerPanel, BorderLayout.SOUTH);
        this.setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
    }

    public final void createFooterPanel() {
        footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());

        JPanel copyrightPanel = new JPanel();
        copyrightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        copyrightPanel.add(new JLabel("© 2015 Planny Inc."));

        JPanel versionPanel = new JPanel();
        versionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        versionPanel.add(new JLabel("version 1"));

        footerPanel.add(copyrightPanel, BorderLayout.WEST);
        footerPanel.add(versionPanel, BorderLayout.EAST);
    }

    public MainPanel getMainPanel() {
        return this.mainPanel;
    }

    public SidebarPanel getSidebarPanel() {
        return this.sidebarPanel;
    }

    public void updateView(Calendar calendar) {
        // refresh Sidebar
        // Planning year
        sidebarPanel.getPlanningYear().setText(CalendarHelper.getPlanningYear(calendar.getTime()));

        // refresh planning week
        PlanningTable planningTable = mainPanel.getPlanningTable();
        planningTable.changeColumnHeaders(CalendarHelper.getWeekDays(calendar));

        // refresh week label interval
        mainPanel.getNavigationBar().getWeekLabel().setText(CalendarHelper.getWeekInterval(calendar.getTime()));
    }

    public void updateViewNextWeek() {
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
}
