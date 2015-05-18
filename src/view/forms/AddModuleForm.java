package view.forms;

import controller.PlannyController;
import helper.CalendarHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Module;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Amine
 */
public class AddModuleForm extends JFrame {

    JTextField nomModuleField, abbreviationField, nbSeanceField, couleurField;
    JComboBox formationComboBox;

    /**
     *Création de la frame qui permet l'ajout d'un module
     * @param controller
     */
    public AddModuleForm(PlannyController controller) {
        super("Planny | Ajouter un Module");
        try {
            setIconImage(ImageIO.read(AddModuleForm.class.getResource("/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new MigLayout("center"));

        JLabel title = new JLabel("Ajouter un module");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        this.add(title, "dock north");

        nomModuleField = new JTextField();
        nomModuleField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Nom"), "alignx trailing");
        this.add(nomModuleField, "wrap");

        abbreviationField = new JTextField();
        abbreviationField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Abbreviation"), "alignx trailing");
        this.add(abbreviationField, "wrap");

        couleurField = new JTextField();
        couleurField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Couleur"), "alignx trailing");
        this.add(couleurField, "wrap");

        nbSeanceField = new JTextField();
        nbSeanceField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Nombre de seances"), "alignx trailing");
        this.add(nbSeanceField, "wrap");

        formationComboBox = new JComboBox(controller.getFormations(CalendarHelper.getPlanningYear(controller.getSelectedDate().getTime())).toArray());
        this.add(new JLabel("Formation"), "alignx trailing");
        this.add(formationComboBox, "wrap");

        JButton buttonAjouter = new JButton("Ajouter");
        this.add(buttonAjouter, "skip, split2, growx");
        buttonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Formation formation = controller.getFormation(formationComboBox.getSelectedItem().toString());
                Module module = new Module(controller.getNextModuleId(), nomModuleField.getText(), abbreviationField.getText(), couleurField.getText(), Integer.parseInt(nbSeanceField.getText()), formation, null);
                
                controller.addModule(formation, module);
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
