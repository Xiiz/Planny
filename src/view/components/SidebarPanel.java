package view.components;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
        calendar.setDecorationBackgroundVisible(false);
        calendar.setNullDateButtonVisible(false);
        calendar.setOpaque(false);
        calendar.getDayChooser().setDayBordersVisible(false);
        calendar.getDayChooser().setDecorationBackgroundVisible(false);
        calendar.setWeekOfYearVisible(false);

        // set background color of the calendar to #EEEEEE
        JPanel dayChooser = calendar.getDayChooser();
        Component components[] = dayChooser.getComponents();
        for (Component comp : components) {
            comp.setBackground(Color.decode("#EEEEEE"));
        }

        // set background color of the days to #EEEEEE
        Component components2[] = calendar.getDayChooser().getDayPanel().getComponents();
        for (Component comp : components2) {
            comp.setBackground(Color.decode("#EEEEEE"));
            comp.setIgnoreRepaint(true);
        }

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
