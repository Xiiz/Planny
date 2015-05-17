package view.components;

import helper.CalendarHelper;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import view.PlanningFrame;

/**
 *
 * @author Amine Bouazizi
 */
public class NavigationBar extends JToolBar {

    JLabel weekLabel;

    public NavigationBar(PlanningFrame mainFrame) {
        ImageIcon prevIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-arrow-prev.png"));
        Action prevAction = new AbstractAction("Previous", prevIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.updateViewPrevWeek();
            }
        };

        ImageIcon nextIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-arrow-next.png"));
        Action nextAction = new AbstractAction("Next", nextIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.updateViewNextWeek();
            }
        };

        weekLabel = new JLabel(CalendarHelper.getWeekInterval(new Date()));
        this.add(prevAction);
        this.add(Box.createHorizontalGlue());
        this.add(weekLabel);
        this.add(Box.createHorizontalGlue());
        this.add(nextAction);
        this.setFloatable(false);
    }

    public JLabel getWeekLabel() {
        return this.weekLabel;
    }
}
