package view.components;

import com.toedter.calendar.JCalendar;
import controller.PlannyController;
import helper.CalendarHelper;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.PlanningFrame;

/**
 *
 * @author Amine Bouazizi
 * @author Yassine Doghri
 */
public class SidebarPanel extends JPanel {

    private JLabel planningYear;
    private final JCalendar jcalendar;

    public SidebarPanel(PlannyController controller) {
        setPreferredSize(new Dimension(200, 400));
        setLayout(new BorderLayout());

        jcalendar = new JCalendar();
        jcalendar.setWeekOfYearVisible(false);
        jcalendar.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals("calendar")) {
                    Calendar c = (Calendar) e.getNewValue();
                    controller.updateView(c);
                    controller.updatePlanningView(c);
                }
            }
        });
        JPanel calendarPanel = new JPanel();
        calendarPanel.add(jcalendar);

        // Jlabel Planning Year
        planningYear = new JLabel("Planning : " + CalendarHelper.getPlanningYear(new Date()));
        planningYear.setFont(new Font("Calibri", Font.PLAIN, 18));
        JPanel titlePanel = new JPanel();
        titlePanel.add(planningYear);

        add(calendarPanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Retourne le JCalendar
     *
     * @return JCalendar
     */
    public JCalendar getJCalendar() {
        return this.jcalendar;
    }

    /**
     * Retourne le JLabel de l'année du planning
     *
     * @return JLabel annnée planning
     */
    public JLabel getPlanningYear() {
        return this.planningYear;
    }

}
