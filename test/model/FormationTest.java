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
 * @author Lasalmonie Pierre
 */
public class FormationTest {

    private Formation laFormation;
    private Module moduleFra;
    private HashMap<Integer, Module> listeModules;
    private Planning lePlanning;

    public FormationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lePlanning = new Planning();
        HashMap<Integer, Seance> lesSeances = new HashMap<Integer, Seance>();
        Module moduleBdd = new Module(1, "Base de données", "BDD", "Black", 10, laFormation, lesSeances);
        moduleFra = new Module(1, "Français", "FRA", "Blue", 5, laFormation, lesSeances);
        listeModules = new HashMap<Integer, Module>();
        laFormation = new Formation(1, "L3 2/3 A", 40, lePlanning, listeModules);
        laFormation.addModule(1, moduleBdd);
        laFormation.addModule(2, moduleFra);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Formation.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = laFormation.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Formation.
     */
    @Test
    public void testSetId() {
        int expResult = 3;
        laFormation.setId(expResult);
        int result = laFormation.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNom method, of class Formation.
     */
    @Test
    public void testGetNom() {
        String expResult = "L3 2/3 A";
        String result = laFormation.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNom method, of class Formation.
     */
    @Test
    public void testSetNom() {
        String expResult = "Master 1";
        laFormation.setNom(expResult);
        String result = laFormation.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDureeSceance method, of class Formation.
     */
    @Test
    public void testGetDureeSceance() {
        int expResult = 40;
        int result = laFormation.getDureeSceance();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDureeSceance method, of class Formation.
     */
    @Test
    public void testSetDureeSceance() {
        int expResult = 45;
        laFormation.setDureeSceance(expResult);
        int result = laFormation.getDureeSceance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListeModules method, of class Formation.
     */
    @Test
    public void testGetListeModules() {
        HashMap<Integer, Module> expResult = listeModules;
        HashMap<Integer, Module> result = laFormation.getListeModules();
        assertSame(expResult, result);
    }

    /**
     * Test of getModule method, of class Formation.
     */
    @Test
    public void testGetModule() {
        Module expResult = moduleFra;
        Module result = laFormation.getModule(2);
        assertSame(expResult, result);
    }

    /**
     * Test of setListeModules method, of class Formation.
     */
    @Test
    public void testSetListeModules() {
        HashMap<Integer, Module> listeNouveauxModules = new HashMap<Integer, Module>();
        HashMap<Integer, Seance> lesNouvellesSeances = new HashMap<Integer, Seance>();
        Module moduleEng = new Module(1, "Anglais", "ENG", "Yellow", 5, laFormation, lesNouvellesSeances);
        Module moduleMarketing = new Module(1, "Marketing", "Mark", "Orange", 10, laFormation, lesNouvellesSeances);
        listeNouveauxModules.put(Integer.SIZE, moduleEng);
        listeNouveauxModules.put(Integer.SIZE, moduleMarketing);
        laFormation.setListeModules(listeNouveauxModules);
        HashMap<Integer, Module> expResult = listeNouveauxModules;
        HashMap<Integer, Module> result = laFormation.getListeModules();
        assertSame(expResult, result);
    }

    /**
     * Test of addModule method, of class Formation.
     */
    @Test
    public void testAddModule() {
        HashMap<Integer, Seance> lesSeances = new HashMap<Integer, Seance>();
        Module moduleEng = new Module(1, "Anglais", "ENG", "Yellow", 5, laFormation, lesSeances);
        laFormation.addModule(3, moduleEng);
        Module expResult = moduleEng;
        Module result = laFormation.getModule(3);
        assertSame(expResult, result);
    }

    /**
     * Test of getPlanning method, of class Formation.
     */
    @Test
    public void testGetPlanning() {
        Planning expResult = lePlanning;
        Planning result = laFormation.getPlanning();
        assertSame(expResult, result);
    }

    /**
     * Test of setPlanning method, of class Formation.
     */
    @Test
    public void testSetPlanning() {
        Planning expResult = new Planning();
        laFormation.setPlanning(expResult);
        Planning result = laFormation.getPlanning();
        assertSame(expResult, result);
    }

}
