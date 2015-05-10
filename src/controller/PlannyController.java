package controller;

import model.DAO;


/**
 *
 * @author Yassine Doghri
 */
public class PlannyController {
    
    public void startApplication() {
        DAO db = new DAO();
        db.initDatabase();
    }

}
