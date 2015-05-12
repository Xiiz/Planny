package view.component;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Amine
 */
public class SideBarPanel extends JPanel {

    public SideBarPanel() {
        this.setPreferredSize(new Dimension(200, 400));
        this.setLayout(new BorderLayout(0, 0));
        JButton jsave = new JButton("Save");
        //mainPanel.add(jsave);
        JButton jcancel = new JButton("Cancel");
        //mainPanel.add(jcancel);
        JCalendar calendar = new JCalendar();
        this.add(calendar, BorderLayout.NORTH);
        JPanel testPanel = new JPanel();
        testPanel.setLayout(new FlowLayout());
        this.add(testPanel, BorderLayout.SOUTH);
        testPanel.add(jsave);
        testPanel.add(jcancel);
    }

}
