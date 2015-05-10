/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    + "   PRIMARY KEY (IDMODULE),"
                    + "   FOREIGN KEY (IDFORMATION) REFERENCES FORMATION (IDFORMATION)"
                    + " );"
                    + "CREATE INDEX I_FK_MODULE_FORMATION ON MODULE (IDFORMATION ASC);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModuleProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("La table Module créé");
    }

    public static ArrayList<Module> getAll(Connection c) {
        try {
            ArrayList<Module> modules = new ArrayList();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MODULE;");

            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setNom(rs.getString("nom"));
                module.setAbbr(rs.getString("abbr"));
                module.setCouleur(rs.getString("couleur"));
                module.setListeSceances(SceanceProvider.getSceancesModule(rs.getInt("id"), c));

                modules.add(module);
            }
            return modules;
        } catch (SQLException ex) {
            Logger.getLogger(ModuleProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static HashMap<Integer, Module> getModules(int idFormation, Connection c) {
        try {
            HashMap<Integer, Module> modules = new HashMap();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * "
                    + "FROM MODULE"
                    + "WHERE IDFORMATION = " + idFormation + ";");
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setNom(rs.getString("nom"));
                module.setAbbr(rs.getString("abbr"));
                module.setCouleur(rs.getString("couleur"));

                modules.put(rs.getInt("id"), module);
            }
            rs.close();
            stmt.close();

            return modules;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
