/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.HashMap;
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
public class SeanceTest {

    private Seance laSeance;
    private Formateur leFormateur;
    private Module leModule;
    private HashMap<Integer, Seance> lesSeances;
    private Date laDate;
    private Formation laFormation;

    public SeanceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lesSeances = new HashMap<Integer, Seance>();
        leFormateur = new Formateur(1, "Dujardin", "Jean", "JD", "0102030405", "Jean.Dujardin@u-pec.fr", lesSeances);
        laFormation = new Formation();
        leModule = new Module(1, "Base de données", "BDD", "Black", 10, laFormation, lesSeances);
        laDate = new Date();
        laSeance = new Seance(1, 1, laDate, leModule, leFormateur);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Seance.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = laSeance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Seance.
     */
    @Test
    public void testSetId() {
        int expResult = 2;
        laSeance.setId(expResult);
        int result = laSeance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumSeance method, of class Seance.
     */
    @Test
    public void testGetNumSeance() {
        int expResult = 1;
        int result = laSeance.getNumSeance();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumSeance method, of class Seance.
     */
    @Test
    public void testSetNumSeance() {
        int expResult = 1;
        laSeance.setNumSeance(expResult);
        int result = laSeance.getNumSeance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDateSeance method, of class Seance.
     */
    @Test
    public void testGetDateSeance() {
        Date result = laSeance.getDateSeance();
        Date expResult = laDate;
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateSeance method, of class Seance.
     */
    @Test
    public void testSetDateSeance() {
        Date expResult = new Date(2015, 05, 10);
        laSeance.setDateSeance(expResult);
        Date result = laSeance.getDateSeance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getModule method, of class Seance.
     */
    @Test
    public void testGetModule() {
        Module result = laSeance.getModule();
        Module expResult = leModule;
        assertSame(expResult, result);
    }

    /**
     * Test of setModule method, of class Seance.
     */
    @Test
    public void testSetModule() {
        Module expResult = new Module(1, "JAVA IHM", "J.IHM", "White", 5,laFormation, lesSeances);
        laSeance.setModule(expResult);
        Module result = laSeance.getModule();
        assertSame(expResult, result);
    }

    /**
     * Test of getFormateur method, of class Seance.
     */
    @Test
    public void testGetFormateur() {
        Formateur expResult = leFormateur;
        Formateur result = laSeance.getFormateur();
        assertSame(expResult, result);
    }

    /**
     * Test of setFormateur method, of class Seance.
     */
    @Test
    public void testSetFormateur() {
        Formateur expResult = new Formateur(2, "Doe", "Jhon", "DJ", "0405060708", "Jhon.Doe@u-pec.fr", lesSeances);
        laSeance.setFormateur(expResult);
        Formateur result = laSeance.getFormateur();
        assertSame(expResult, result);
    }

}
