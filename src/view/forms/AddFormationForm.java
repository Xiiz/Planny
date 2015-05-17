package view.forms;

import controller.PlannyController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Amine
 */
public class AddFormationForm extends JFrame{

    JTextField nomSeanceField, dureeField;
    private PlannyController controller;

    public AddFormationForm(PlannyController controller) {
        super("Planny | Ajouter une Formation");
        this.controller = controller;
        try {
            setIconImage(ImageIO.read(new File("src/view/components/images/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new MigLayout("center"));
        
        JLabel title = new JLabel("Ajouter une formation");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        this.add(title, "dock north");
        
        
        nomSeanceField = new JTextField();
        nomSeanceField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Nom"), "alignx trailing");
        this.add(nomSeanceField, "wrap");

        dureeField = new JTextField();
        dureeField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Duree (min)"), "alignx trailing");
        this.add(dureeField, "wrap");


        JButton buttonAjouter = new JButton("Ajouter");
        this.add(buttonAjouter, "skip, split2, growx");
        buttonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
            }

        });
        JButton buttonAnnuler = new JButton("Annuler");
        this.add(buttonAnnuler, "growx");
        buttonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setLocationRelativeTo(null);
        toFront();
    }
}
