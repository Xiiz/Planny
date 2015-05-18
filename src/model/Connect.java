package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connexion à la base de données SQLite
 *
 * @author Yassine Doghri
 */
public class Connect {

    private static Connection c = null;

    private static Connection open() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:planny.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    /**
     * Retourne la connection à la bdd en cours ou la crée
     *
     * @return
     */
    public static Connection get() {
        if (c == null) {
            c = Connect.open();
        }
        return c;
    }

    /**
     * Ferme la bdd
     */
    public static void close() {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
        }
    }

}
