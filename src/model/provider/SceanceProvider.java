package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Sceance;

/**
 *
 * @author Yassine Doghri
 */
public class SceanceProvider {

    public static void createTable(Connection c) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SCEANCE ("
                    + "	IDSCEANCE INT NOT NULL,"
                    + "	IDFORMATEUR INT NOT NULL,"
                    + "	IDMODULE INT NOT NULL,"
                    + "	NUMSCEANCE INT NULL,"
                    + "	DATESCEANCE DATE NULL,"
                    + "	PRIMARY KEY (IDSCEANCE),"
                    + "	FOREIGN KEY (IDFORMATEUR) REFERENCES FORMATEUR (IDFORMATEUR),"
                    + "	FOREIGN KEY (IDMODULE) REFERENCES MODULE (IDMODULE)"
                    + " );"
                    + "CREATE  INDEX I_FK_SCEANCE_FORMATEUR ON SCEANCE (IDFORMATEUR ASC);"
                    + "CREATE  INDEX I_FK_SCEANCE_MODULE ON SCEANCE (IDMODULE ASC);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SceanceProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("La table Sceance créé");
    }

    public static ArrayList<Sceance> getAll(Connection c) {
        try {
            ArrayList<Sceance> sceances = new ArrayList();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCEANCE;");

            while (rs.next()) {
                Sceance sceance = new Sceance();
                sceance.setId(rs.getInt("id"));
                sceance.setNumSceance(rs.getInt("numSceance"));
                sceance.setDateSceance(rs.getDate("dateSceance"));

                sceances.add(sceance);
            }
            return sceances;
        } catch (SQLException ex) {
            Logger.getLogger(SceanceProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static HashMap<Integer, Sceance> getSceancesFormateur(int idFormateur, Connection c) {
        try {
            HashMap<Integer, Sceance> sceances = new HashMap();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM SCEANCE"
                    + "WHERE IDFORMATEUR = " + idFormateur + ";");
            while (rs.next()) {
                Sceance sceance = new Sceance();
                sceance.setId(rs.getInt("id"));
                sceance.setNumSceance(rs.getInt("numSceance"));
                sceance.setDateSceance(rs.getDate("dateSceance"));

                sceances.put(rs.getInt("id"), sceance);
            }
            rs.close();
            stmt.close();

            return sceances;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static HashMap<Integer, Sceance> getSceancesModule(int idModule, Connection c) {
        try {
            HashMap<Integer, Sceance> sceances = new HashMap();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM SCEANCE"
                    + "WHERE IDMODULE = " + idModule + ";");
            while (rs.next()) {
                Sceance sceance = new Sceance();
                sceance.setId(rs.getInt("id"));
                sceance.setNumSceance(rs.getInt("numSceance"));
                sceance.setDateSceance(rs.getDate("dateSceance"));

                sceances.put(rs.getInt("id"), sceance);
            }
            rs.close();
            stmt.close();

            return sceances;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
