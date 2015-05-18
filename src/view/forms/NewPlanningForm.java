package view.forms;

import controller.PlannyController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.DAO;
import model.Planning;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Yassine Doghri
 */
public class NewPlanningForm extends JFrame {

    private final JTextField planningYearField;

    public NewPlanningForm(PlannyController controller) {
        super("Planny | Ajouter une Séance");
        try {
            setIconImage(ImageIO.read(NewPlanningForm.class.getResource("/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new MigLayout("center"));

        JLabel title = new JLabel("Nouveau Planning !");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        this.add(title, "dock north");

        planningYearField = new JTextField();
        planningYearField.setPreferredSize(new Dimension(100, 24));
        this.add(new JLabel("Année Planning"), "alignx trailing");
        this.add(planningYearField, "wrap");

        JButton buttonAjouter = new JButton("Ajouter");
        this.add(buttonAjouter, "skip, split2, growx");
        buttonAjouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!planningYearField.getText().equals("")) {
                    int idPlanning = controller.getNextPlanningId();

                    Planning planning = new Planning(idPlanning, planningYearField.getText(), null);

                    controller.getPlannings().put(idPlanning, planning);
                    DAO.addPlanning(planning);
                }
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
