/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Yassine Doghri
 */
public class PlanningTable extends JTable {

    /**
     * Constructeur de la table Planning
     *
     * @param rowData
     * @param columnNames
     */
    public PlanningTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);

        getTableHeader().setReorderingAllowed(false);
        setFillsViewportHeight(true);
        setColumnSelectionAllowed(true);
        setRowSelectionAllowed(false);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Component prepareRenderer(
            TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);

        return c;
    }
}
