package view.components;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Amine Bouazizi
 * @author Yassine Doghri
 */
public class SidebarPanel extends JPanel {

    public SidebarPanel() {
        setPreferredSize(new Dimension(200, 400));
        setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar();
        calendar.setWeekOfYearVisible(false);
        calendar.getDayChooser().addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        System.out.println(e.getPropertyName() + ": " + e.getNewValue());
                    }
                });

        setBackground(Color.decode("#EEEEEE"));

        JPanel calendarPanel = new JPanel();
        calendarPanel.add(calendar);

        // Jlabel Planning Year
        JLabel planningYear = new JLabel("Planning : 2014-2015");
        JPanel titlePanel = new JPanel();
        titlePanel.add(planningYear);

        add(calendarPanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
    }

}
