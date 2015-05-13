/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xiiz
 */
public class PlanningTest {

    private Planning lePlanning;
    private HashMap<Integer, Formation> listeFormations;
    private Formation laFormation;

    public PlanningTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        listeFormations = new HashMap<Integer, Formation>();
        lePlanning = new Planning(1, "2015", listeFormations);
        Module moduleFra = new Module(1, "Français", "FRA", "Blue", 5, null);
        HashMap<Integer, Module> listeModules = new HashMap<Integer, Module>();
        laFormation = new Formation(1, "L3 2/3 A", 40, listeModules);
        laFormation.addModule(1, moduleFra);
        lePlanning.addFormation(1, laFormation);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Planning.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = lePlanning.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Planning.
     */
    @Test
    public void testSetId() {
        int expResult = 1;
        lePlanning.setId(expResult);
        int result = lePlanning.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnneePlanning method, of class Planning.
     */
    @Test
    public void testGetAnneePlanning() {
        String expResult = "2015";
        String result = lePlanning.getAnneePlanning();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnneePlanning method, of class Planning.
     */
    @Test
    public void testSetAnneePlanning() {
        String expResult = "2017";
        lePlanning.setAnneePlanning(expResult);
        String result = lePlanning.getAnneePlanning();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListeFormations method, of class Planning.
     */
    @Test
    public void testGetListeFormations() {
        HashMap<Integer, Formation> expResult = listeFormations;
        HashMap<Integer, Formation> result = lePlanning.getListeFormations();
        assertSame(expResult, result);
    }

    /**
     * Test of getFormation method, of class Planning.
     */
    @Test
    public void testGetFormation() {
        Formation expResult = laFormation;
        Formation result = lePlanning.getFormation(1);
        assertSame(expResult, result);
    }

    /**
     * Test of setListeFormations method, of class Planning.
     */
    @Test
    public void testSetListeFormations() {
        HashMap<Integer, Formation> expResult = new HashMap<Integer, Formation>();
        Formation formationUn = new Formation(1, "M1", 40, null);
        Formation formationDeux = new Formation(2, "M2", 30, null);
        expResult.put(1, formationUn);
        expResult.put(2, formationDeux);
        lePlanning.setListeFormations(expResult);
        HashMap<Integer, Formation> result = lePlanning.getListeFormations();
        assertSame(expResult, result);
    }

    /**
     * Test of addFormation method, of class Planning.
     */
    @Test
    public void testAddFormation() {
        Formation expResult = new Formation(2, "M1", 40, null);
        lePlanning.addFormation(2, expResult);
        Formation result = lePlanning.getFormation(2);
        assertSame(expResult, result);
    }

    /**
     * Test of removeFormation method, of class Planning.
     */
    @Test
    public void testRemoveFormation() {
        Formation expResult = new Formation(2, "M1", 40, null);
        lePlanning.addFormation(2, expResult);
        lePlanning.removeFormation(2);
        for (int lesCles : lePlanning.getListeFormations().keySet()) {
            Formation result = lePlanning.getFormation(lesCles);
            if (expResult.equals(result)) {
                fail("La formation n'a pas été supprimée");
            }
        }
    }

}
