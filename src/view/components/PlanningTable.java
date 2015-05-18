package view.components;

import controller.PlannyController;
import helper.CalendarHelper;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import model.Seance;
import view.forms.AddSeanceForm;
import view.forms.UpdateSeanceForm;

/**
 * Classe du planning basé sur une JTable
 *
 * @author Yassine Doghri
 */
public final class PlanningTable extends JTable implements ActionListener {

    private final JPopupMenu popup;
    private PlannyController controller;

    /**
     * Constructeur de la table Planning
     *
     * @param rowData
     * @param columnNames
     */
    public PlanningTable(Object[][] rowData, Object[] columnNames, PlannyController controller) {
        super(rowData, columnNames);

        this.controller = controller;

        getTableHeader().setReorderingAllowed(false);
        setFillsViewportHeight(true);
        setColumnSelectionAllowed(true);
        setRowSelectionAllowed(false);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        /**
         * ActionListener en cliquant sur le JTable
         */
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                Point p = me.getPoint();
                int row = rowAtPoint(p);
                int column = columnAtPoint(p);
                if (me.getClickCount() == 2) {
                    if (!getValueAt(row, column).toString().equals("Matin") || !getValueAt(row, column).toString().equals("Après-Midi")) {
                        Seance seance = controller.getSeanceFromHtml(getValueAt(row, column).toString());
                        UpdateSeanceForm updateSeanceForm = new UpdateSeanceForm(controller, seance);
                    }
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

        if (column >= 5 && row == 0) {
            c.setBackground(Color.decode("#F0F0F0"));
        }
        if (column >= 5 && row == 1) {
            c.setBackground(Color.decode("#F5F5F5"));
        }
        return c;
    }

    /**
     *
     * @param weekDays
     */
    public void changeColumnHeaders(ArrayList<Date> weekDays) {
        JTableHeader th = getTableHeader();
        TableColumnModel tcm = th.getColumnModel();

        for (Date day : weekDays) {
            TableColumn tc = tcm.getColumn(weekDays.indexOf(day));
            tc.setHeaderValue(CalendarHelper.getDayColumnLabel(day));
            th.repaint();
        }
    }

    /**
     * Methode action en cliquant sur une cellule de la table, elle lance le
     * frame AddSeanceForm
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int y = this.getSelectedRow() + 1;
        int d = this.getSelectedColumn() + 1;
        System.out.println(this.getColumnName(d - 1));
        System.out.println("ligne: " + y + " et colomne: " + d);

        // Open Add Séance Form
        AddSeanceForm addSeance = new AddSeanceForm(controller);
    }
}
