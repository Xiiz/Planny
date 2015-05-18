
import controller.PlannyController;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Yassine Doghri
 */
public class Planny {

    /**
     * Méthode principale de l'application
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initializeLookAndFeel();
        PlannyController controller = new PlannyController();
        controller.startApplication();
    }

    /**
     * Installs the Synthetica Look and Feel
     */
    public static final void initializeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(SyntheticaAluOxideLookAndFeel.class.getName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Planny.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
