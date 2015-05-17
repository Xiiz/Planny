package view;

import controller.PlannyController;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
        menuBar = new MenuBar(controller);

        // Create the panels
        sidebarPanel = new SidebarPanel(controller);
        mainPanel = new MainPanel(controller);
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
}
