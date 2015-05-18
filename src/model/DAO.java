package model;

import java.sql.Connection;
import java.util.HashMap;
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

    /**
     * Initialise les tables de la base de données
     */
    public static void initDatabase() {
        Connection c = Connect.get();
        FormateurProvider.createTable(c);
        FormationProvider.createTable(c);
        ModuleProvider.createTable(c);
        PlanningProvider.createTable(c);
        SeanceProvider.createTable(c);
    }

    /**
     * Parcours la bdd pour initialiser les objets du modèle et retourne les
     * plannings
     *
     * @return liste des plannings
     */
    public static HashMap<Integer, Planning> initPlannings() {
        Connection c = Connect.get();
        return PlanningProvider.getAll(c);
    }

    /**
     * Retourne tous les formateurs de la base de données
     *
     * @return liste des formateurs
     */
    public static HashMap<Integer, Formateur> getAllFormateurs() {
        Connection c = Connect.get();
        return FormateurProvider.getAllFormateurs(c);
    }

    /**
     * Retourne tous les modules de la base de données
     *
     * @return liste de modules
     */
    public static HashMap<Integer, Module> getAllModules() {
        Connection c = Connect.get();
        return ModuleProvider.getAllModules(c);
    }

    /**
     * Ajoute une séance donnée à la base de données
     *
     * @param seance
     */
    public static void addSeance(Seance seance) {
        Connection c = Connect.get();
        SeanceProvider.insertSeance(c, seance);
    }

    /**
     * Ajoute une formation donnée à la bdd
     *
     * @param formation
     */
    public static void addFormation(Formation formation) {
        Connection c = Connect.get();
        FormationProvider.insertFormation(c, formation);
    }

    /**
     * Retourne un fomateur trouvé par le prenom et nom donné
     *
     * @param prenom
     * @param nom
     * @return Formateur
     */
    public static Formateur getFormateur(String prenom, String nom) {
        Connection c = Connect.get();
        return FormateurProvider.getFormateur(c, prenom, nom);
    }

    /**
     * Ajoute un Module donné à la bdd
     *
     * @param module
     */
    public static void addModule(Module module) {
        Connection c = Connect.get();
        ModuleProvider.insertModule(c, module);
    }

    /**
     * Ajoute un formateur donné à la base de données
     *
     * @param formateur
     */
    public static void addFormateur(Formateur formateur) {
        Connection c = Connect.get();
        FormateurProvider.insertFormateur(c, formateur);
    }

    /**
     * Met à jours une séance donnée
     *
     * @param seance
     */
    public static void updateSeance(Seance seance) {
        Connection c = Connect.get();
        SeanceProvider.updateSeance(c, seance);
    }

    /**
     * Ajoute un planning donné à la bdd
     *
     * @param planning
     */
    public static void addPlanning(Planning planning) {
        Connection c = Connect.get();
        PlanningProvider.insertPlanning(c, planning);
    }
}
