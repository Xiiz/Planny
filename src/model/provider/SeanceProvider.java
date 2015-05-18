package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Module;
import model.Seance;

/**
 *
 * @author Yassine Doghri
 */
public class SeanceProvider {

    public static void createTable(Connection c) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SEANCE ("
                    + "	IDMODULE INT NOT NULL,"
                    + "	IDSEANCE INT NOT NULL,"
                    + "	IDFORMATEUR INT NOT NULL,"
                    + "	NUMSEANCE INT NULL,"
                    + "	DATESEANCE DATE NULL,"
                    + "	PRIMARY KEY (IDSEANCE),"
                    + "	FOREIGN KEY (IDMODULE) REFERENCES MODULE (IDMODULE),"
                    + "	FOREIGN KEY (IDFORMATEUR) REFERENCES FORMATEUR (IDFORMATEUR)"
                    + " );"
                    + "CREATE INDEX IF NOT EXISTS I_FK_SEANCE_FORMATEUR ON SEANCE (IDFORMATEUR ASC);"
                    + "CREATE INDEX IF NOT EXISTS I_FK_SEANCE_MODULE ON SEANCE (IDMODULE ASC);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SeanceProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Table Seance initialisée");
    }

    public static HashMap<Integer, Seance> getSeances(Module module, Connection c) {
        try {
            HashMap<Integer, Seance> seances = new HashMap();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + " FROM SEANCE"
                    + " WHERE IDMODULE = " + module.getId() + ";");
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getInt("idSeance"));
                seance.setNumSeance(rs.getInt("numSeance"));
                try {
                    seance.setDateSeance(formatter.parse(rs.getString("dateSeance")));
                } catch (ParseException ex) {
                    Logger.getLogger(SeanceProvider.class.getName()).log(Level.SEVERE, null, ex);
                }
                seance.setModule(module);
                seance.setFormateur(FormateurProvider.getFormateur(seance, c));

                seances.put(rs.getInt("idSeance"), seance);
            }
            rs.close();
            stmt.close();

            return seances;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void insertSeance(Connection c, Seance seance) {
        try {
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateSeance = f.format(seance.getDateSeance());

            String sql = "INSERT INTO SEANCE (IDMODULE,IDSEANCE,IDFORMATEUR,NUMSEANCE,DATESEANCE) "
                    + "VALUES (" + seance.getModule().getId() + ", "
                    + seance.getId() + ", "
                    + seance.getFormateur().getId() + ", "
                    + seance.getNumSeance() + ", "
                    + "'" + dateSeance + "' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void updateSeance(Connection c, Seance seance) {
        try {
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateSeance = f.format(seance.getDateSeance());

            String sql = "UPDATE SEANCE SET"
                    + " IDMODULE = " + seance.getModule().getId() + ","
                    + " IDFORMATEUR = " + seance.getFormateur().getId() + ","
                    + " NUMSEANCE = " + seance.getNumSeance() + ","
                    + " DATESEANCE = '" + dateSeance + "'"
                    + " WHERE IDSEANCE = " + seance.getId() + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records updated successfully");
    }
}
