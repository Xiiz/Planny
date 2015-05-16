package view.components;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;

import javax.swing.JToolBar;

/**
 *
 * @author Amine
 */
public class ToolBar extends JToolBar {

    public ToolBar() {
        // creating the icons
        ImageIcon undoIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-undo.png"));
        ImageIcon redoIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-redo.png"));
        ImageIcon copyIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-copy.png"));
        ImageIcon pasteIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-paste.png"));
        ImageIcon deleteIcon = new ImageIcon(
                ToolBar.class.getResource("images/icon-delete.png"));

        Action undoAction = new AbstractAction("Undo", undoIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Undo action");
            }
        };
        Action redoAction = new AbstractAction("Redo", redoIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Redo Action");
            }
        };
        Action copyAction = new AbstractAction("Copy", copyIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Copy");
            }
        };
        Action pasteAction = new AbstractAction("Paste", pasteIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Paste");
            }
        };
        Action deleteAction = new AbstractAction("Delete", deleteIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete");
            }
        };

        this.add(Box.createHorizontalGlue());
        this.add(undoAction);
        this.add(redoAction);
        this.add(copyAction);
        this.add(pasteAction);
        this.add(deleteAction);
        this.setBorder(BorderFactory.createEmptyBorder());

        //this.setRollover(true);
        /*
         for (int i = 0; i < buttonLabels.length; ++i) {
         icons[i] = new ImageIcon(iconFiles[i]);
         buttons[i] = new JButton(icons[i]);
         buttons[i].setToolTipText(buttonLabels[i]);
         if (i == 3) {
         this.addSeparator();
         }
         this.add(buttons[i]);*/
        this.setFloatable(false);
    }
}
