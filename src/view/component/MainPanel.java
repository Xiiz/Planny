package view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Amine
 */
public class MainPanel extends JPanel {

    public MainPanel() {
        this.setLayout(new BorderLayout());
        JPanel toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new FlowLayout());
        this.add(toolBarPanel, BorderLayout.NORTH);

        this.setPreferredSize(new Dimension(600, 400));
        this.setBackground(Color.white);
        String[] choseView = {"Day view", "Week view", "Month view"};
        JComboBox comboBox = new JComboBox(choseView);
        toolBarPanel.add(comboBox);

        Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3"},
        {"Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column3"}};
        Object daysColumns[] = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        JTable planningTable = new JTable(rowData, daysColumns);
        JScrollPane scrollPane = new JScrollPane(planningTable);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
