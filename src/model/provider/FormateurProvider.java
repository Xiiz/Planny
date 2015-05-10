package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Formateur;

/**
 *
 * @author Yassine Doghri
 */
public class FormateurProvider {

    public static void createTable(Connection c) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS FORMATEUR ("
                    + "   IDFORMATEUR INT NOT NULL,"
                    + "   NOM TEXT NULL,"
                    + "   PRENOM TEXT NULL,"
                    + "   INITIALES TEXT NULL,"
                    + "   TELEPHONE TEXT NULL,"
                    + "   EMAIL TEXT NULL,"
                    + "   PRIMARY KEY (IDFORMATEUR))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormateurProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("La table Formateur créé");
    }

    public static ArrayList<Formateur> getAll(Connection c) {
        try {
            ArrayList<Formateur> formateurs = new ArrayList();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FORMATEUR;");

            while (rs.next()) {
                Formateur formateur = new Formateur();
                formateur.setId(rs.getInt("id"));
                formateur.setNom(rs.getString("nom"));
                formateur.setPrenom(rs.getString("prenom"));
                formateur.setInitiales(rs.getString("initiales"));
                formateur.setTelephone(rs.getString("telephone"));
                formateur.setEmail(rs.getString("email"));
                formateur.setListeSceances(SceanceProvider.getSceancesFormateur(rs.getInt("id"), c));

                formateurs.add(formateur);
            }
            return formateurs;
        } catch (SQLException ex) {
            Logger.getLogger(FormateurProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
