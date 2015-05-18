package model;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe de génération Html pour un formateur
 *
 * @author Pierre Lasalmonie
 */
public class HtmlFileFormateur {

    /**
     * Génère un Fichier HTML affichant le planning d'un formateur
     *
     * @param lePlanning
     * @param leFormateur
     * @param destination
     * @param name
     * @throws IOException
     */
    public HtmlFileFormateur(Planning lePlanning, Formateur leFormateur, String destination, String name) throws IOException {
        ArrayList<Seance> listeSeance = new ArrayList<>();
        File f = new File(name + ".html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html>");
        bw.write("<head>");
        bw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        bw.write("<body style=\"width : 100%; height : 100%;\" background-color:grey;>");
        bw.write("<div id=\"info\" style=\"width : 80%; height : 80%; margin:0 auto\">");
        bw.write("<h1>Voici le planning pour l'année " + lePlanning.getAnneePlanning() + " de " + leFormateur.getPrenom() + " " + leFormateur.getNom() + "</h1>");
        bw.write("<p>Voici le planning de toute vos séances : <br />");

        for (HashMap.Entry<Integer, Seance> uneSeance : leFormateur.getListeSceances().entrySet()) {
            Seance laSeance = uneSeance.getValue();
            Module leModule = laSeance.getModule();
            bw.write(leModule.detailModule());
            bw.write(laSeance.getInfoSeance());
            bw.write("nombre de séance : " + leFormateur.getListeSceances().size() + "<br />");
            bw.write("<br />");
            bw.write("<br />");
        }

        bw.write("</body>");
        bw.write("</html>");

        bw.close();

        Desktop.getDesktop().browse(f.toURI());
    }
}
//}
