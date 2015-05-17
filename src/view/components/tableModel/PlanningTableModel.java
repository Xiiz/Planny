package view.components.tableModel;

import controller.PlannyController;
import helper.CalendarHelper;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Seance;

public class PlanningTableModel extends AbstractTableModel {

    List<Seance> seances;
    PlannyController controller;

    public PlanningTableModel(List<Seance> seances, PlannyController controller) {
        this.seances = seances;
        this.controller = controller;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Date.class;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "Jour " + columnIndex;
    }

    @Override
    public int getRowCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (seances == null) ? null : seances.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int columnIndex, int rowIndex) {
        return true;
    }
}
