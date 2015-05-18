package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Formateur;
import model.Module;
import model.Seance;

/**
 *
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
        System.out.println("Table Formateur initialisée");
    }

    public static Formateur getFormateur(Seance seance, Connection c) {
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT f.IDFORMATEUR, NOM, PRENOM, INITIALES, TELEPHONE, EMAIL"
                    + " FROM FORMATEUR f"
                    + " JOIN SEANCE s ON f.IDFORMATEUR = s.IDFORMATEUR"
                    + " WHERE s.IDSEANCE = " + seance.getId() + ";");

            Formateur formateur = new Formateur();
            while (rs.next()) {
                formateur.setId(rs.getInt("idFormateur"));
                formateur.setNom(rs.getString("nom"));
                formateur.setPrenom(rs.getString("prenom"));
                formateur.setInitiales(rs.getString("initiales"));
                formateur.setTelephone(rs.getString("telephone"));
                formateur.setEmail(rs.getString("email"));
                formateur.addSeance(seance.getId(), seance);
            }
            return formateur;
        } catch (SQLException ex) {
            Logger.getLogger(FormateurProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static HashMap<Integer, Formateur> getAllFormateurs(Connection c) {
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FORMATEUR;");

            HashMap<Integer, Formateur> formateurs = new HashMap();
            while (rs.next()) {
                Formateur formateur = new Formateur();

                formateur.setId(rs.getInt("idFormateur"));
                formateur.setNom(rs.getString("nom"));
                formateur.setPrenom(rs.getString("prenom"));
                formateur.setInitiales(rs.getString("initiales"));
                formateur.setTelephone(rs.getString("telephone"));
                formateur.setEmail(rs.getString("email"));

                formateurs.put(rs.getInt("idFormateur"), formateur);
            }
            return formateurs;
        } catch (SQLException ex) {
            Logger.getLogger(FormateurProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Formateur getFormateur(Connection c, String prenom, String nom) {
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FORMATEUR WHERE prenom = '" + prenom + "' and nom = '" + nom + "';");

            Formateur formateur = new Formateur();
            while (rs.next()) {
                formateur.setId(rs.getInt("idFormateur"));
                formateur.setNom(rs.getString("nom"));
                formateur.setPrenom(rs.getString("prenom"));
                formateur.setInitiales(rs.getString("initiales"));
                formateur.setTelephone(rs.getString("telephone"));
                formateur.setEmail(rs.getString("email"));
            }
            return formateur;
        } catch (SQLException ex) {
            Logger.getLogger(FormateurProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void insertFormateur(Connection c, Formateur formateur) {
        try {
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();

            String sql = "INSERT INTO FORMATEUR (IDFORMATEUR,NOM,PRENOM,INITIALES,TELEPHONE,EMAIL) "
                    + "VALUES (" + formateur.getId() + ", "
                    + "'" + formateur.getNom() + "', "
                    + "'" + formateur.getPrenom() + "', "
                    + "'" + formateur.getInitiales() + "', "
                    + "'" + formateur.getTelephone() + "', "
                    + "'" + formateur.getEmail() + "' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

}
