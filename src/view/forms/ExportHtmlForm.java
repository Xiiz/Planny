/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import controller.PlannyController;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.HtmlFileFormateur;
import model.HtmlFileFormation;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Xiiz
 */
public class ExportHtmlForm extends JFrame {

    private String choixExport;
    private JComboBox formateurComboBox;
    private Object selectedFormateur;

    /**
     *Frame qui permet l'export en HTML des plannings
     * @param controller
     */
    public ExportHtmlForm(PlannyController controller) {
        super("Planny | Exportation HTML");
        try {
            setIconImage(ImageIO.read(ExportHtmlForm.class.getResource("/planny-icon.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        choixExport = "Formation";
        ArrayList<String> listeAnnees = controller.getAnneesPlannings();
        ArrayList<String> listeFormateur = controller.getAllFormateurs();
        this.setLayout(new MigLayout("center"));

        JPanel choixComboExport = new JPanel();
        this.add(choixComboExport);

        JComboBox anneeComboBox = new JComboBox(listeAnnees.toArray());
        anneeComboBox.setPreferredSize(new Dimension(200, 24));
        choixComboExport.add(new JLabel("Année à sélectionner :"), "alignx trailing");
        choixComboExport.add(anneeComboBox, "wrap");
        
 
        String[] choice = {"Formation", "Formateur"};
        JComboBox choiceExport = new JComboBox(choice);
        choixComboExport.add(new JLabel("Que voulez vous exporter ?"), "alignx trailing");
        choixComboExport.add(choiceExport, "width :100:,grow,push");

        Object selectedYear = anneeComboBox.getSelectedItem();

        JFileChooser fc = new JFileChooser();
        fc.setControlButtonsAreShown(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML File", "html");
        fc.setFileFilter(filter);
        fc.setSelectedFile(new File("planning-" + anneeComboBox.getSelectedItem().toString() + ".html"));
        this.add(fc, "north");

        choiceExport.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                choixExport = e.getItem().toString();
                if (choixExport.equals("Formateur")) {
                    formateurComboBox = new JComboBox(listeFormateur.toArray());
                    formateurComboBox.setPreferredSize(new Dimension(200, 24));
                    choiceExport.getParent().add(new JLabel("Formateur à sélectionner :"));
                    choiceExport.getParent().add(formateurComboBox);
                    choiceExport.getParent().repaint();
                    choiceExport.getParent().validate();
                }
            }
        });
        JPanel boutonSouth = new JPanel();
        boutonSouth.setLayout(new FlowLayout());
        this.add(boutonSouth, "dock south");

        JButton buttonValider = new JButton("Valider");
        boutonSouth.add(buttonValider);

        JButton buttonAnnuler = new JButton("Annuler");
        boutonSouth.add(buttonAnnuler);

        buttonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }
        );

        buttonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (choixExport.equals("Formateur")) {
                        selectedFormateur = formateurComboBox.getSelectedItem();
                        File destination = fc.getCurrentDirectory();
                        String nomFicher = fc.getName(destination);
                        HtmlFileFormateur exportHtml = new HtmlFileFormateur(controller.getPlanning(selectedYear.toString()), controller.getFormateur(selectedFormateur.toString(), selectedYear.toString()), destination.toString(), nomFicher);
                    } else if (choixExport.equals("Formation")) {
                        File destination = fc.getCurrentDirectory();
                        String nomFicher = fc.getName(destination);
                        HtmlFileFormation exportHtml = new HtmlFileFormation(controller.getPlanning(selectedYear.toString()), destination.toString(), nomFicher);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ExportHtmlForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLocationRelativeTo(null);
        toFront();
    }
}
