package view.components;

import com.toedter.calendar.JCalendar;
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

    public SidebarPanel(PlanningFrame mainFrame) {
        setPreferredSize(new Dimension(200, 400));
        setLayout(new BorderLayout());

        JCalendar calendar = new JCalendar();
        calendar.setWeekOfYearVisible(false);
        calendar.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals("calendar")) {
                    Calendar c = (Calendar) e.getNewValue();
                    System.out.println(c.getTime().toString());
                    System.out.println(calendar.getParent().getParent().getName());

                    // rafraichir le tableau du planning
                    PlanningTable planningTable = mainFrame.getMainPanel().getPlanningTable();
                    planningTable.changeColumnHeaders(CalendarHelper.getWeekDays(c));

                    // rafraichir l'année du planning
                    planningTable.setColumnSelectionInterval(c.get(Calendar.DAY_OF_WEEK), c.get(Calendar.DAY_OF_WEEK));
                    planningYear.setText("Planning : " + CalendarHelper.getPlanningYear(c.getTime()));
                }
            }
        });
        JPanel calendarPanel = new JPanel();
        calendarPanel.add(calendar);

        // Jlabel Planning Year
        planningYear = new JLabel("Planning : " + CalendarHelper.getPlanningYear(new Date()));
        planningYear.setFont(new Font("Calibri", Font.PLAIN, 18));
        JPanel titlePanel = new JPanel();
        titlePanel.add(planningYear);

        add(calendarPanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);
    }

}
