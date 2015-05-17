/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lasalmonie Pierre
 */
public class ModuleTest {

    private Module leModule;
    private HashMap<Integer, Seance> lesSeances;
    private Seance laSeance;
    private Formation laFormation;

    public ModuleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            lesSeances = new HashMap<Integer, Seance>();
            laSeance = new Seance(1, 1, null, leModule, null);
            laFormation = new Formation();
            leModule = new Module(1, "Base de données", "BDD", "Black", 10,laFormation,lesSeances);          
            leModule.addSeance(1, laSeance);
        } catch (Exception ex) {
            Logger.getLogger(ModuleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Module.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = leModule.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Module.
     */
    @Test
    public void testSetId() {
        int expResult = 4;
        leModule.setId(expResult);
        int result = leModule.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNom method, of class Module.
     */
    @Test
    public void testGetNom() {
        String expResult = "Base de données";
        String result = leModule.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNom method, of class Module.
     */
    @Test
    public void testSetNom() {
        String expResult = "JAVA IHM";
        leModule.setNom(expResult);
        String result = leModule.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAbbr method, of class Module.
     */
    @Test
    public void testGetAbbr() {
        String expResult = "BDD";
        String result = leModule.getAbbr();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAbbr method, of class Module.
     */
    @Test
    public void testSetAbbr() {
        String expResult = "IHM";
        leModule.setAbbr(expResult);
        String result = leModule.getAbbr();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCouleur method, of class Module.
     */
    @Test
    public void testGetCouleur() {
        String expResult = "Black";
        String result = leModule.getCouleur();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCouleur method, of class Module.
     */
    @Test
    public void testSetCouleur() {
        String expResult = "White";
        leModule.setCouleur(expResult);
        String result = leModule.getCouleur();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNbSeances method, of class Module.
     */
    @Test
    public void testGetNbSeances() {
        int expResult = 10;
        int result = leModule.getNbSeances();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNbSeances method, of class Module.
     */
    @Test
    public void testSetNbSeances() {
        int expResult = 25;
        leModule.setNbSeances(expResult);
        int result = leModule.getNbSeances();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListeSeances method, of class Module.
     */
    @Test
    public void testGetListeSeances() {
        HashMap<Integer, Seance> expResult = lesSeances;
        HashMap<Integer, Seance> result = leModule.getListeSeances();
        assertSame(expResult, result);
    }

    /**
     * Test of getSeance method, of class Module.
     */
    @Test
    public void testGetSeance() {
        Seance expResult = laSeance;
        Seance result = leModule.getSeance(1);
        assertSame(expResult, result);
    }

    /**
     * Test of setListeSeances method, of class Module.
     */
    @Test
    public void testSetListeSeances() {
        HashMap<Integer, Seance> expResult = new HashMap<Integer, Seance>();
        Seance seanceUn = new Seance(1, 1, null, leModule, null);
        Seance seanceDeux = new Seance(2, 3, null, leModule, null);
        expResult.put(1, seanceUn);
        expResult.put(2, seanceDeux);
        leModule.setListeSeances(expResult);
        HashMap<Integer, Seance> result = leModule.getListeSeances();
        assertSame(expResult, result);
    }

    /**
     * Test of addSeance method, of class Module.
     */
    @Test
    public void testAddSeance() {
        try {
            Seance expResult = new Seance(1, 1, null, leModule, null);
            leModule.addSeance(1, expResult);
            Seance result = leModule.getSeance(1);
            assertSame(expResult, result);
        } catch (Exception ex) {
            Logger.getLogger(ModuleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getFormation method, of class Module.
     */
    @Test
    public void testGetFormation() {
        Formation expResult = laFormation;
        Formation result = leModule.getFormation();
        assertSame(expResult, result);
    }

    /**
     * Test of setFormation method, of class Module.
     */
    @Test
    public void testSetFormation() {
        Formation expResult = new Formation();
        leModule.setFormation(expResult);
        Formation result = leModule.getFormation();
        assertSame(expResult, result);

    }

    /**
     * Test of toString method, of class Module.
     */
    @Test
    public void testToString() {
        String expResult = "Base de données";
        String result = leModule.toString();
        assertEquals(expResult, result);
    }

}
