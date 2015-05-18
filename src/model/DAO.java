package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.provider.FormateurProvider;
import model.provider.FormationProvider;
import model.provider.ModuleProvider;
import model.provider.PlanningProvider;
import model.provider.SeanceProvider;

/**
 * Classe d'accès aux données de la base de données SQLite
 *
 * @author Yassine Doghri
 */
public class DAO {

    public static void initDatabase() {
        Connection c = Connect.get();
        FormateurProvider.createTable(c);
        FormationProvider.createTable(c);
        ModuleProvider.createTable(c);
        PlanningProvider.createTable(c);
        SeanceProvider.createTable(c);
    }

    public static HashMap<Integer, Planning> initPlannings() {
        Connection c = Connect.get();
        return PlanningProvider.getAll(c);
    }

    public static void insertData(String tableName, String fieldNames, String values) throws Exception {

        String[] fn = fieldNames.split(",");
        String[] v = values.split(",");

        if (fn.length != v.length) {
            throw new Exception("Le nombre de champs est différent de celui des données");
        }

        try {
            Connection c = Connect.get();
            Statement stmt = c.createStatement();
            String sql = "INSERT INTO " + tableName + " (" + fieldNames + ") "
                    + "VALUES (" + values + ");";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Records created successfully");
    }

    public static void updateData() {

    }

    public static HashMap<Integer, Formateur> getAllFormateurs() {
        Connection c = Connect.get();
        return FormateurProvider.getAllFormateurs(c);
    }

    public static HashMap<Integer, Module> getAllModules() {
        Connection c = Connect.get();
        return ModuleProvider.getAllModules(c);
    }

    public static void addSeance(Seance seance) {
        Connection c = Connect.get();
        SeanceProvider.insertSeance(c, seance);
    }

    public static void addFormation(Formation formation) {
        Connection c = Connect.get();
        FormationProvider.insertFormation(c, formation);
    }

    public static Formateur getFormateur(String prenom, String nom) {
        Connection c = Connect.get();
        return FormateurProvider.getFormateur(c, prenom, nom);
    }

    public static void addModule(Module module) {
        Connection c = Connect.get();
        ModuleProvider.insertModule(c, module);
    }

    public static void addFormateur(Formateur formateur) {
        Connection c = Connect.get();
        FormateurProvider.insertFormateur(c, formateur);
    }

    public static void updateSeance(Seance seance) {
        Connection c = Connect.get();
        SeanceProvider.updateSeance(c, seance);
    }
}
