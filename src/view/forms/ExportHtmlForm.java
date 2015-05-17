/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.forms;

import controller.PlannyController;
import helper.ChooseFile;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.HtmlFile;
import model.Planning;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Xiiz
 */
public class ExportHtmlForm extends JFrame {

    public ExportHtmlForm(PlannyController controller) {

        ArrayList<String> listeAnnees = controller.getAnneesPlannings();
        this.setLayout(new MigLayout("center"));
        
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("fichier.html"));
        this.add(fc, "north");

        JComboBox anneeComboBox = new JComboBox(listeAnnees.toArray());
        anneeComboBox.setPreferredSize(new Dimension(200, 24));
        this.add(new JLabel("Année à sélectionner :"), "alignx trailing");
        this.add(anneeComboBox, "wrap");



        JButton buttonValider = new JButton("Valider");
        this.add(buttonValider, "skip, split2, growx");

        Object selectedValue = anneeComboBox.getSelectedItem();

        buttonValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File destination = fc.getCurrentDirectory();
                    String nomFicher = fc.getName(destination);
                    HtmlFile exportHtml = new HtmlFile(controller.getPlanning(selectedValue.toString()), fc.toString(), nomFicher);
                } catch (IOException ex) {
                    Logger.getLogger(ExportHtmlForm.class.getName()).log(Level.SEVERE, null, ex);
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
        setLocationRelativeTo(null);
        toFront();
    }
}
