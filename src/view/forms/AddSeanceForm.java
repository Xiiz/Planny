package view.forms;

import com.toedter.calendar.JDateChooser;
import controller.PlannyController;
import helper.CalendarHelper;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.Formateur;
import model.Module;
import model.Seance;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Yassine Doghri
 */
public class AddSeanceForm extends JFrame {

    private JDateChooser dateChooser;
    private JTextField numSeanceField;
    private JComboBox comboModules;
    private JComboBox comboFormateurs;
    private JComboBox comboTime;

    public AddSeanceForm(PlannyController controller) {
        super("Planny | Ajouter une Séance");
        try {
            setIconImage(ImageIO.read(AddSeanceForm.class.getResource("/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new MigLayout("center"));

        JLabel title = new JLabel("Ajouter une seance");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 20));
        this.add(title, "dock north");

        dateChooser = new JDateChooser();
        numSeanceField = new JTextField();
        numSeanceField.setPreferredSize(new Dimension(100, 24));
        String[] heures = {"09:00:00", "14:00:00"};
        comboTime = new JComboBox(heures);
        comboModules = new JComboBox(controller.getAllModules().toArray());
        comboFormateurs = new JComboBox(controller.getAllFormateurs().toArray());

        this.add(new JLabel("Date"), "alignx trailing");
        this.add(dateChooser, "wrap");
        this.add(new JLabel("Heure"), "alignx trailing");
        this.add(comboTime, "wrap");
        this.add(new JLabel("Modules"), "alignx trailing");
        this.add(comboModules, "wrap");
        this.add(new JLabel("Numéro Séance"), "alignx trailing");
        this.add(numSeanceField, "wrap");
        this.add(new JLabel("Formateurs"), "alignx trailing");
        this.add(comboFormateurs, "wrap");

        JButton buttonAjouter = new JButton("Ajouter");
        this.add(buttonAjouter, "skip, split2, growx");
        buttonAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calSeance = dateChooser.getCalendar();

                if ((calSeance.get(Calendar.DAY_OF_WEEK) != 1) || (calSeance.get(Calendar.DAY_OF_WEEK) != 7)) {
                    Date dateSeance = dateChooser.getDate();
                    String time = comboTime.getSelectedItem().toString();
                    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                    String dateSeanceS = f.format(dateSeance);
                    SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dateSeance2 = dateSeance;
                    try {
                        dateSeance2 = f2.parse(dateSeanceS + " " + time);
                    } catch (ParseException ex) {
                        Logger.getLogger(AddSeanceForm.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println(dateSeance2 + " " + numSeanceField.getText() + " " + comboFormateurs.getSelectedItem().toString() + " " + comboModules.getSelectedItem().toString());
                    Module module = controller.getModule(comboModules.getSelectedItem().toString(), dateChooser.getDate());
                    Formateur formateur = controller.getFormateur(comboFormateurs.getSelectedItem().toString(), CalendarHelper.getPlanningYear(dateChooser.getDate()));
                    Seance seance = new Seance(controller.getNextSeanceId(), Integer.parseInt(numSeanceField.getText()), dateSeance2, module, formateur);
                    controller.addSeance(seance, module, formateur);

                    controller.updatePlanningView(controller.getSelectedDate(), controller.getSelectedFormation());

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Les Samedis et Dimanches sont non ouvrés !",
                            "A plain message",
                            JOptionPane.PLAIN_MESSAGE);
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
