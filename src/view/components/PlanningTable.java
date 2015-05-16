/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import helper.CalendarHelper;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Yassine Doghri
 */
public final class PlanningTable extends JTable implements ActionListener {

    private final JPopupMenu popup;

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

        // set default selected column to sysdate
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        setSelectedColumn(cal);

        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // print column value from selected cell
//                System.out.println(getSelectedColumn());
            }
        });

        // JPopupMenu
        popup = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Ajouter Séance...");
        menuItem.addActionListener(this);
        popup.add(menuItem);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
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

    public void changeColumnHeaders(ArrayList<Date> weekDays) {
        JTableHeader th = getTableHeader();
        TableColumnModel tcm = th.getColumnModel();

        for (Date day : weekDays) {
            TableColumn tc = tcm.getColumn(weekDays.indexOf(day));
            tc.setHeaderValue(CalendarHelper.getDayColumnLabel(day));
            th.repaint();
        }
    }

    public void changeRowsContents(ArrayList<Date> weekDays) {

    }

    public void setSelectedColumn(Calendar cal) {
        int indice = CalendarHelper.getWeekNumber(cal);
        setColumnSelectionInterval(indice, indice);
//        getColumn(indice).getIdentifier();
//        this.get;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int y = this.getSelectedRow() + 1;
        int d = this.getSelectedColumn() + 1;
        System.out.println(this.getColumnName(d-1));
        System.out.println("ligne: " + y + " et colomne: " + d);
    }
}
