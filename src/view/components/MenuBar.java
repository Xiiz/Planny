package view.components;

import controller.PlannyController;
import view.forms.AddFormationForm;
import view.forms.AddFormateurForm;
import view.forms.AddModuleForm;
import view.forms.AddSeanceForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import view.forms.ExportHtmlForm;

/**
 *
 * @author Amine
 */
public class MenuBar extends JMenuBar {

    private final JMenu file, edit, element, help, exportPlanning;
    private final JMenuItem newPlanning, openPlanning,
            html, exit, undo, redo, copy, paste,
            addFormation, addFormateur, addModule, addSeance, about;

    /**
     *
     */
    public MenuBar(PlannyController controller) {
        // create menus
        file = new JMenu("Fichier");
        exportPlanning = new JMenu("Exporter planning");
        edit = new JMenu("Editer");
        element = new JMenu("Ajouter");
        help = new JMenu("Aide");

        // create menu items
        newPlanning = new JMenuItem("Nouveau planning...");
        openPlanning = new JMenuItem("Ouvrir planning...");
        html = new JMenuItem("HTML...");
        exit = new JMenuItem("Fermer");

        undo = new JMenuItem("Annuler");
        redo = new JMenuItem("Repeter");
        copy = new JMenuItem("Copier");
        paste = new JMenuItem("Coller");

        addFormation = new JMenuItem("Formation...");
        addFormateur = new JMenuItem("Formateur...");
        addModule = new JMenuItem("Module...");
        addSeance = new JMenuItem("Seance...");

        addFormation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                AddFormationForm formationForm = new AddFormationForm(controller);
                formationForm.setVisible(true);

            }
        });
        addFormateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                AddFormateurForm formateurForm = new AddFormateurForm(controller);
                formateurForm.setVisible(true);

            }
        });
        addModule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                AddModuleForm moduleForm = new AddModuleForm(controller);
                moduleForm.setVisible(true);

            }
        });
        addSeance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                AddSeanceForm seanceForm = new AddSeanceForm(controller);
                seanceForm.setVisible(true);

            }
        });
        html.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ExportHtmlForm YearCalendarForm = new ExportHtmlForm(controller);
                YearCalendarForm.setVisible(true);
            }
        });

        about = new JMenuItem("A propos");

        // add menu items to menus
        file.add(newPlanning);
        file.add(openPlanning);
        file.add(new JSeparator());
        file.add(exportPlanning);
        exportPlanning.add(html);
        file.add(new JSeparator());
        file.add(exit);

        edit.add(undo);
        edit.add(redo);
        edit.add(copy);
        edit.add(paste);

        element.add(addFormation);
        element.add(addFormateur);
        element.add(addModule);
        element.add(addSeance);

        help.add(about);

        // adding the menus to the Menu bar
        this.add(file);
        this.add(edit);
        this.add(element);
        this.add(help);
    }
}
