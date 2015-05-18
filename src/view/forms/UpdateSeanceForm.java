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
import model.DAO;
import model.Formateur;
import model.Module;
import model.Seance;
import net.miginfocom.swing.MigLayout;

/**
 * Frame du formulaire de mise à jour d'une seance
 *
 * @author Yassine Doghri
 */
public class UpdateSeanceForm extends JFrame {

    private JDateChooser dateChooser;
    private JTextField numSeanceField;
    private JComboBox comboModules;
    private JComboBox comboFormateurs;
    private JComboBox comboTime;

    /**
     * Frame qui permet la modification des attributs d'une séance.
     *
     * @param controller
     * @param seance
     */
    public UpdateSeanceForm(PlannyController controller, Seance seance) {
        super("Planny | Modifier une séance");
        try {
            setIconImage(ImageIO.read(UpdateSeanceForm.class.getResource("/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.setLayout(new MigLayout("center"));

        JLabel title = new JLabel("Modifier une seance");
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
        dateChooser.setDate(seance.getDateSeance());
        this.add(new JLabel("Heure"), "alignx trailing");
        this.add(comboTime, "wrap");
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        int heure = Integer.parseInt(sdf.format(seance.getDateSeance()));
        if (heure < 12) {
            comboTime.setSelectedIndex(0);
        } else {
            comboTime.setSelectedIndex(1);
        }
        this.add(new JLabel("Modules"), "alignx trailing");
        this.add(comboModules, "wrap");
        comboModules.setSelectedItem(seance.getModule().toString());
        this.add(new JLabel("Numéro Séance"), "alignx trailing");
        this.add(numSeanceField, "wrap");
        numSeanceField.setText(Integer.toString(seance.getNumSeance()));
        this.add(new JLabel("Formateurs"), "alignx trailing");
        this.add(comboFormateurs, "wrap");
        comboFormateurs.setSelectedItem(seance.getFormateur().toString());

        JButton buttonAjouter = new JButton("Modifier");
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

                    Seance seance2 = new Seance(seance.getId(), Integer.parseInt(numSeanceField.getText()), dateSeance2, null, null);

                    Module module = seance.getModule();
                    Formateur formateur = seance.getFormateur();
                    // check if module and formateur changed
                    if (!comboFormateurs.getSelectedItem().toString().equals(seance.getFormateur().getPrenom() + " " + seance.getFormateur().getNom())) {
                        module = controller.getModule(comboModules.getSelectedItem().toString(), dateChooser.getDate());
                    }
                    if (!comboModules.getSelectedItem().toString().equals(seance.getModule().getNom())) {
                        formateur = controller.getFormateur(comboFormateurs.getSelectedItem().toString(), CalendarHelper.getPlanningYear(dateChooser.getDate()));
                    }

                    module.removeSeance(seance.getId());
                    formateur.removeSeance(seance.getId());

                    seance2.setModule(module);
                    seance2.setFormateur(formateur);

                    try {
                        module.addSeance(seance.getId(), seance2);
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateSeanceForm.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null,
                                "Le nombre de séances pour le module est dépassé !",
                                "A plain message",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                    formateur.addSeance(seance.getId(), seance2);

                    DAO.updateSeance(seance2);
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
