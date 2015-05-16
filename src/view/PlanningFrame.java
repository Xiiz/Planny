package view;

import controller.PlannyController;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import view.components.MainPanel;
import view.components.SidebarPanel;
import view.components.MenuBar;

/**
 * Classe de la fenêtre de planning
 *
 * @author Yassine Doghri
 * @author Mohammed Amine Bouazizi
 */
public class PlanningFrame extends JFrame {

//    private final JSplitPane splitPaneV;
    private final JSplitPane splitPaneH;
    private JPanel sidebarPanel;
    private JPanel mainPanel;
    private JPanel footerPanel;
    private JMenuBar menuBar;

    /**
     * Méthode constructeur pour la Fenêtre de Planning
     *
     * @param controller
     */
    public PlanningFrame(PlannyController controller) {
        setTitle("Planny");
        getContentPane().setBackground(Color.white);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setSize(700, 700);
        topPanel.setBackground(Color.white);
        getContentPane().add(topPanel);

        //Create the menu bar
        createMenuBar();

        // Create the panels
        createSidebarPanel();
        createMainPanel(controller);
        createDownPanel();

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

    public final void createMenuBar() {
        menuBar = new MenuBar();
    }

    public final void createSidebarPanel() {
        sidebarPanel = new SidebarPanel();
    }

    public final void createMainPanel(PlannyController controller) {
        mainPanel = new MainPanel(controller);
    }

    public final void createDownPanel() {
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
}
