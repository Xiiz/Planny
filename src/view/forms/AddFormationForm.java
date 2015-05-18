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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.Formation;
import model.Planning;
import net.miginfocom.swing.MigLayout;
import sun.applet.Main;

/**
 *
 * @author Amine
 */
public class AddFormationForm extends JFrame {

    JTextField nomFormationField, dureeField;
    JComboBox anneePlanningCombo;
    private PlannyController controller;

    public AddFormationForm(PlannyController controller) {
        super("Planny | Ajouter une Formation");
        this.controller = controller;
        try {
            setIconImage(ImageIO.read(AddFormationForm.class.getResource("/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new MigLayout("center"));

        JLabel title = new JLabel("Ajouter une formation");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        this.add(title, "dock north");

        nomFormationField = new JTextField();
        nomFormationField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Nom"), "alignx trailing");
        this.add(nomFormationField, "wrap");

        anneePlanningCombo = new JComboBox(controller.getAnneesPlannings().toArray());
        anneePlanningCombo.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Annee Planning"), "alignx trailing");
        this.add(anneePlanningCombo, "wrap");

        dureeField = new JTextField();
        dureeField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Durée Séance (min)"), "alignx trailing");
        this.add(dureeField, "wrap");

        JButton buttonAjouter = new JButton("Ajouter");
        this.add(buttonAjouter, "skip, split2, growx");
        buttonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Planning planning = controller.getPlanning(anneePlanningCombo.getSelectedItem().toString());
                Formation formation = new Formation(controller.getNextFormationId(), nomFormationField.getText(), Integer.parseInt(dureeField.getText()), planning, null);

                controller.addFormation(formation, planning);
                controller.updateFormationInfos(controller.getSelectedDate());
                dispose();
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
