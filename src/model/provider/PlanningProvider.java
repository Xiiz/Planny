package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Planning;

/**
 *
 * @author Yassine Doghri
 */
public class PlanningProvider {

    public static void createTable(Connection c) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PLANNING ("
                    + "   IDPLANNING INT NOT NULL,"
                    + "   ANNEEPLANNING TEXT UNIQUE NULL,"
                    + "   PRIMARY KEY (IDPLANNING)"
                    + " )";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormateurProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("La table Planning créé");
    }

    public static ArrayList<Planning> getAll(Connection c) {
        try {
            ArrayList<Planning> plannings = new ArrayList();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLANNING;");
            while (rs.next()) {
                Planning planning = new Planning();
                planning.setId(rs.getInt("id"));
                planning.setAnneePlanning(rs.getString("anneePlanning"));
                planning.setListeFormations(FormationProvider.getFormations(rs.getInt("id"), c));

                plannings.add(planning);
            }
            rs.close();
            stmt.close();

            return plannings;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
