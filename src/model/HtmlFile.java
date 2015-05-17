/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Xiiz
 */
public class HtmlFile {

       
    public HtmlFile(Planning lePlanning,String destination,String name) throws IOException {
        
        
        File f = new File(destination+"\\"+name+".html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write("<html>");
        bw.write("<head>");
        bw.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        bw.write("<body>");
        bw.write("<h1>Voici le planning pour l'année" + lePlanning.getAnneePlanning() + "</h1>");
        bw.write("   ");
        for (int clesFormation : lePlanning.getListeFormations().keySet()) {
            Formation laFormation = lePlanning.getFormation(clesFormation);
            bw.write("<p>Voici le planning la Formation : " + laFormation.toString() + " qui se déroule en : " + laFormation.getDureeSceance() + "</p>");
            bw.write("<p>Celle-ci dispose est composée des modules suivant : </br>");
            for (int cleModule : laFormation.getListeModules().keySet()) {
                Module unModule = laFormation.getModule(cleModule);
                bw.write(unModule.toString() + ", nombre de séance :" + unModule.getNbSeances() + ", de couleur : " + unModule.getCouleur());
            }
        }
        bw.write("</body>");
        bw.write("</html>");

        bw.close();

        Desktop.getDesktop().browse(f.toURI());
    }
}


