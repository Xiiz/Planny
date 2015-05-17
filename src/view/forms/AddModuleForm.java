package view.forms;
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
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Amine
 */
public class AddModuleForm extends JFrame {

    JTextField nomModuleField, abbreviationField, nbSeanceField;
    JComboBox couleurComboBox ,formationComboBox;

    public AddModuleForm() {
        super("Planny | Ajouter un formateur");
        try {
            setIconImage(ImageIO.read(new File("src/view/components/images/planny-icon.png")));
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
        
        
        String[] colors = {"Rouge","Bleu","Vert","Jaune"};
        couleurComboBox = new JComboBox(colors);
        couleurComboBox.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Couleur"), "alignx trailing");
        this.add(couleurComboBox, "wrap");
        
        nbSeanceField = new JTextField();
        nbSeanceField.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Nombre de seances"), "alignx trailing");
        this.add(nbSeanceField, "wrap");

        formationComboBox = new JComboBox();
        this.add(new JLabel("Formation"), "alignx trailing");
        this.add(formationComboBox, "wrap");

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
                System.out.println("Button pressed");
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
