package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Formation;
import model.Module;

/**
 *
 * @author Yassine Doghri
 */
public class ModuleProvider {
    
    public static void createTable(Connection c) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `MODULE` ("
                    + "   IDMODULE INT NOT NULL,"
                    + "   IDFORMATION INT NOT NULL,"
                    + "   NOM TEXT NULL,"
                    + "   ABBR TEXT NULL,"
                    + "   COULEUR TEXT NULL,"
                    + "   NBSEANCES INT NULL,"
                    + "   PRIMARY KEY (IDMODULE),"
                    + "   FOREIGN KEY (IDFORMATION) REFERENCES FORMATION (IDFORMATION)"
                    + " );"
                    + "CREATE INDEX IF NOT EXISTS I_FK_MODULE_FORMATION ON MODULE (IDFORMATION ASC);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModuleProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Table Module initialisée");
    }
    
    public static HashMap<Integer, Module> getModules(Formation formation, Connection c) {
        try {
            HashMap<Integer, Module> modules = new HashMap();
            
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *"
                    + " FROM MODULE"
                    + " WHERE IDFORMATION = " + formation.getId() + ";");
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("idModule"));
                module.setNom(rs.getString("nom"));
                module.setAbbr(rs.getString("abbr"));
                module.setCouleur(rs.getString("couleur"));
                module.setNbSeances(rs.getInt("nbSeances"));
                module.setFormation(formation);
                module.setListeSeances(SeanceProvider.getSeances(module, c));
                
                modules.put(rs.getInt("idModule"), module);
            }
            rs.close();
            stmt.close();
            
            return modules;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static HashMap<Integer, Module> getAllModules(Connection c) {
        try {
            HashMap<Integer, Module> modules = new HashMap();
            
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MODULE;");
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("idModule"));
                module.setNom(rs.getString("nom"));
                module.setAbbr(rs.getString("abbr"));
                module.setCouleur(rs.getString("couleur"));
                module.setNbSeances(rs.getInt("nbSeances"));
                module.setListeSeances(SeanceProvider.getSeances(module, c));
                
                modules.put(rs.getInt("idModule"), module);
            }
            rs.close();
            stmt.close();
            
            return modules;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void insertModule(Connection c, Module module) {
        try {
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();
            
            String sql = "INSERT INTO MODULE (IDMODULE,IDFORMATION,NOM,ABBR,COULEUR,NBSEANCES) "
                    + "VALUES (" + module.getId() + ", "
                    + module.getFormation().getId() + ", "
                    + "'" + module.getNom() + "', "
                    + "'" + module.getAbbr() + "', "
                    + "'" + module.getCouleur() + "', "
                    + module.getNbSeances() + ");";
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
