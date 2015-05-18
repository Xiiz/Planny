package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Formation;
import model.Planning;

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
                    + "	DUREESEANCE INT NULL,"
                    + "	PRIMARY KEY (IDFORMATION),"
                    + "	FOREIGN KEY (IDPLANNING) REFERENCES PLANNING (IDPLANNING)"
                    + " );"
                    + "CREATE INDEX IF NOT EXISTS I_FK_FORMATION_PLANNING ON FORMATION (IDPLANNING ASC);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Table Formation initialisée");
    }

    public static HashMap<Integer, Formation> getFormations(Planning planning, Connection c) {
        try {
            HashMap<Integer, Formation> formations = new HashMap();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *"
                    + " FROM FORMATION"
                    + " WHERE IDPLANNING = " + planning.getId() + ";");
            while (rs.next()) {
                Formation formation = new Formation();
                formation.setId(rs.getInt("idFormation"));
                formation.setNom(rs.getString("nom"));
                formation.setDureeSceance(rs.getInt("dureeSeance"));
                formation.setPlanning(planning);
                formation.setListeModules(ModuleProvider.getModules(formation, c));

                formations.put(rs.getInt("idFormation"), formation);
            }
            rs.close();
            stmt.close();

            return formations;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void insertFormation(Connection c, Formation formation) {
        try {
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();

            String sql = "INSERT INTO FORMATION (IDFORMATION,IDPLANNING,NOM,DUREESEANCE) "
                    + "VALUES (" + formation.getId() + ", "
                    + formation.getPlanning().getId() + ", "
                    + "'" + formation.getNom() + "', "
                    + formation.getDureeSceance() + ");";
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
