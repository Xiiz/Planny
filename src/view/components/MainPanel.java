package view.components;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

/**
 *
 * @author Amine
 */
public class MainPanel extends JPanel {

    private JToolBar toolBar;
    private JToolBar navigationBar;
    private final JPanel center;

    public MainPanel() {
        this.setLayout(new BorderLayout());
        JPanel toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new FlowLayout());
        this.add(toolBarPanel, BorderLayout.NORTH);

        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.white);
        String[] choseView = {"Day view", "Week view", "Month view"};
        JComboBox comboBox = new JComboBox(choseView);
        createToolBar();
        createNavigationBar();
        this.add(navigationBar, BorderLayout.SOUTH);
        toolBarPanel.add(toolBar, FlowLayout.LEFT);
        toolBarPanel.add(comboBox);

        Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3"},
        {"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3"}};
        Object daysColumns[] = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        JTable planningTable = new JTable(rowData, daysColumns);
        this.center = new JPanel();
        this.center.setLayout(new BorderLayout());
        this.center.add(planningTable, BorderLayout.CENTER);
        planningTable.setFillsViewportHeight(true);
        this.add(new JScrollPane(planningTable), BorderLayout.CENTER);

        
    }

    public final void createToolBar() {
        toolBar = new ToolBar();
    }
    public final void createNavigationBar() {
        navigationBar = new NavigationBar();
    }
}
