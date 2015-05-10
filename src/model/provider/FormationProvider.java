package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Formation;

/**
 *
 * @author Yassine Doghri
 */
public class FormationProvider {

    public static void createTable(Connection c) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS FORMATION ("
                    + "	IDFORMATION INT NOT NULL,"
                    + "	IDPLANNING INT NOT NULL,"
                    + "	NOM TEXT NULL,"
                    + "	DUREESCEANCE INT NULL,"
                    + "	PRIMARY KEY (IDFORMATION),"
                    + "	FOREIGN KEY (IDPLANNING) REFERENCES PLANNING (IDPLANNING)"
                    + " );"
                    + "CREATE  INDEX I_FK_FORMATION_PLANNING ON FORMATION (IDPLANNING ASC);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("La table Formation créé");
    }

    public static ArrayList<Formation> getAll(Connection c) {
        try {
            ArrayList<Formation> formations = new ArrayList();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FORMATION;");

            while (rs.next()) {
                Formation formation = new Formation();
                formation.setId(rs.getInt("id"));
                formation.setNom(rs.getString("nom"));
                formation.setDureeSceance(rs.getInt("dureeSceance"));
                formation.setListeModules(ModuleProvider.getModules(rs.getInt("id"), c));

                formations.add(formation);
            }
            return formations;
        } catch (SQLException ex) {
            Logger.getLogger(FormationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static HashMap<Integer, Formation> getFormations(int idPlanning, Connection c) {
        try {
            HashMap<Integer, Formation> formations = new HashMap();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM FORMATION"
                    + "WHERE IDPLANNING = " + idPlanning + ";");
            while (rs.next()) {
                Formation formation = new Formation();
                formation.setId(rs.getInt("id"));
                formation.setNom(rs.getString("nom"));
                formation.setDureeSceance(rs.getInt("dureeSceance"));

                formations.put(rs.getInt("id"), formation);
            }
            rs.close();
            stmt.close();

            return formations;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
