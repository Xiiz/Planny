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
import model.Formateur;
import net.miginfocom.swing.MigLayout;
import sun.applet.Main;

/**
 *
 * @author Amine
 */
public class AddFormateurForm extends JFrame {

    JTextField nomField, prenomField, initialesField, telephoneField, emailField;

    /**
     *Création de la Frame qui permet l'ajout d'un formateur
     * @param controller
     */
    public AddFormateurForm(PlannyController controller) {
        super("Planny | Ajouter un formateur");
        try {
            setIconImage(ImageIO.read(AddFormateurForm.class.getResource("/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new MigLayout("center"));

        JLabel title = new JLabel("Ajouter un formateur");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        this.add(title, "dock north");

        nomField = new JTextField();
        nomField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Nom"), "alignx trailing");
        this.add(nomField, "wrap");

        prenomField = new JTextField();
        prenomField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Prenom"), "alignx trailing");
        this.add(prenomField, "wrap");

        initialesField = new JTextField();
        initialesField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Initiales"), "alignx trailing");
        this.add(initialesField, "wrap");

        telephoneField = new JTextField();
        telephoneField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Telephone"), "alignx trailing");
        this.add(telephoneField, "wrap");

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Email"), "alignx trailing");
        this.add(emailField, "wrap");

        JButton buttonAjouter = new JButton("Ajouter");
        this.add(buttonAjouter, "skip, split2, growx");
        buttonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Formateur formateur = new Formateur(controller.getNextFormateurId(), nomField.getText(), prenomField.getText(), initialesField.getText(), telephoneField.getText(), emailField.getText(), null);
                controller.addFormateur(formateur);
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
