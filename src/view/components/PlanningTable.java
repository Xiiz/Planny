/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setColumnSelectionInterval(5, 5);

        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // print column value from selected cell
                System.out.println(getSelectedColumn());
            }
        });
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
