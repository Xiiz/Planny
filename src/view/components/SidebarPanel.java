package view.components;

import com.toedter.calendar.JCalendar;
import controller.PlannyController;
import helper.CalendarHelper;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Amine Bouazizi
 * @author Yassine Doghri
 */
public class SidebarPanel extends JPanel {

    private JLabel planningYear;
    private final JCalendar jcalendar;
    private JComboBox comboFormations;
    private JLabel infosFormation;

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
                    controller.updatePlanningView(c, controller.getSelectedFormation());
                }
            }
        });
        JPanel calendarPanel = new JPanel();
        calendarPanel.add(jcalendar);

        // Jlabel Planning Year
        planningYear = new JLabel();
        planningYear.setFont(new Font("Calibri", Font.PLAIN, 18));
        JPanel titlePanel = new JPanel();
        titlePanel.add(planningYear);

        comboFormations = new JComboBox();
        JPanel infosFormations = new JPanel();
        infosFormations.setLayout(new MigLayout("center"));
        infosFormations.add(comboFormations, "north");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        infosFormation = new JLabel();
        infosFormations.add(infosFormation, "wrap");

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        calendarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infosFormations.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(calendarPanel);
        bottomPanel.add(infosFormations);

        add(bottomPanel, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.NORTH);

        comboFormations.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    String item = event.getItem().toString();
                    controller.updateFormationLabel(item);
                    controller.updatePlanningView(controller.getSelectedDate(), controller.getSelectedFormation());
                }
            }
        });

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

    public JLabel getInfosFormationLabel() {
        return this.infosFormation;
    }

    public JComboBox getComboFormations() {
        return this.comboFormations;
    }

}
