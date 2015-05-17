package view.components.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import model.Seance;

public class PlanningCellRenderer extends JPanel implements TableCellRenderer {

    public PlanningCellRenderer() {
        super();
//        setLineWrap(true);
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Seance seance = (Seance) value;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("<strong>" + seance.getId() + "</strong>"
                + seance.getId() + "Articles " + seance.getNumSeance() + "");

        label.setFont(new Font(table.getFont().getName(), Font.BOLD, 20));
        this.add(label);
        this.setBackground(Color.decode(seance.getModule().getCouleur()));

        return this;
    }
}
