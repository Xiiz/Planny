package view.components;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

/**
 *
 * @author Amine Bouazizi
 */
public class NavigationBar extends JToolBar {

    public NavigationBar() {
        ImageIcon prevIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-arrow-prev.png"));
        Action prevAction = new AbstractAction("Previous", prevIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Previous");
            }
        };

        ImageIcon nextIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-arrow-next.png"));
        Action nextAction = new AbstractAction("Next", nextIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next");
            }
        };

        JLabel weekLabel = new JLabel("Week 5");
        this.add(prevAction);
        this.add(Box.createHorizontalGlue());
        this.add(weekLabel);
        this.add(Box.createHorizontalGlue());
        this.add(nextAction);
        this.setFloatable(false);
    }
}
