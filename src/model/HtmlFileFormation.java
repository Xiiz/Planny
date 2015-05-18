package model;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Lasalmonie Pierre
 */
public class HtmlFileFormation {

    /**
     * Génère un fichier HTML affichant le planning d'une année donnée
     * 
     * @param lePlanning
     * @param destination
     * @param name
     * @throws IOException
     */
    public HtmlFileFormation(Planning lePlanning, String destination, String name) throws IOException {
        ArrayList<Seance> listeSeance = new ArrayList<>();
        File f = new File(name + ".html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html>");
        bw.write("<head>");
        bw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        bw.write("<body style=\"width : 100%; height : 100%;\" background-color:grey;>");
        bw.write("<div id=\"info\" style=\"width : 80%; height : 80%; margin:0 auto\">");
        bw.write("<h1>Voici le planning pour l'année " + lePlanning.getAnneePlanning() + "</h1>");

        for (int clesFormation : lePlanning.getListeFormations().keySet()) {
            Formation laFormation = lePlanning.getFormation(clesFormation);
            bw.write("<p>Voici le planning la Formation : " + laFormation.getNom() + " qui se déroule en : " + laFormation.getHeureTotalFormation() + " heures.</p>");
            bw.write("<p>Celle-ci est composée des modules suivants : </br>");
            for (int cleModule : laFormation.getListeModules().keySet()) {
                Module unModule = laFormation.getModule(cleModule);
                bw.write(unModule.detailModule());
                for (HashMap.Entry<Integer, Seance> uneSeance : unModule.getListeSeances().entrySet()) {
                    Seance laSeance = uneSeance.getValue();
                    bw.write(laSeance.getInfoSeance());
                }
                bw.write("nombre de séance : " + unModule.getListeSeances().size() + "<br />");
                bw.write("<br />");
            }
            bw.write("</p>");
            bw.write("<br />");
        }
        bw.write("</body>");
        bw.write("</html>");

        bw.close();

        Desktop.getDesktop().browse(f.toURI());
    }
}
//}
