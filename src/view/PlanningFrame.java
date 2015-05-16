package view;

import java.awt.*;
import javax.swing.*;
import view.components.MainPanel;
import view.components.SidebarPanel;
import view.components.MenuBar;

public class PlanningFrame extends JFrame {

    private final JSplitPane splitPaneV;
    private final JSplitPane splitPaneH;
    private JPanel sideBarPanel;
    private JPanel mainPanel;
    private JPanel copyrightPanel;
    private JMenuBar menuBar;
    

    /**
     *
     */
    public PlanningFrame() {
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
        createMainPanel();
        createDownPanel();

        // Create a splitter pane
        splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneV.setDividerSize(1);
        splitPaneV.setContinuousLayout(true);
        topPanel.add(splitPaneV, BorderLayout.CENTER);
        splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneH.setDividerSize(1);
        splitPaneH.setContinuousLayout(true);
        splitPaneH.setLeftComponent(sideBarPanel);
        splitPaneH.setRightComponent(mainPanel);
        splitPaneV.setLeftComponent(splitPaneH);
        splitPaneV.setRightComponent(copyrightPanel);
        this.setJMenuBar(menuBar);
    }

    public final void createMenuBar() {
        menuBar = new MenuBar();
    }

    public final void createSidebarPanel() {
        sideBarPanel = new SidebarPanel();
    }

    public final void createMainPanel() {
        mainPanel = new MainPanel();
    }

    public final void createDownPanel() {
        copyrightPanel = new JPanel();
        copyrightPanel.setLayout(new BorderLayout());
        copyrightPanel.setPreferredSize(new Dimension(400, 20));
        copyrightPanel.add(new JLabel("  © 2015 Planny Inc."), BorderLayout.WEST);
    }
}
