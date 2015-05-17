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
public class FormateurTest {

    private Formateur leFormateur;
    private Module leModule;
    private HashMap<Integer, Seance> lesSeances;
    private Formation laFormation;
    
    public FormateurTest() {
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
        laFormation = new Formation(1, "L3 2/3 A", 40, null, null);
        leFormateur = new Formateur(1, "Dujardin", "Jean", "JD", "0102030405", "Jean.Dujardin@u-pec.fr", lesSeances);
        leModule = new Module(1, "Base de données", "BDD", "Black", 10, laFormation, lesSeances);
        Date dateSeanceUn = new Date();
        Seance seanceUn = new Seance(1, 1, dateSeanceUn, leModule, leFormateur);
        Date dateSeanceDeux = new Date();
        Seance seanceDeux = new Seance(1, 2, dateSeanceDeux, leModule, leFormateur);
        lesSeances.put(1, seanceUn);
        lesSeances.put(2, seanceDeux);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Formateur.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = leFormateur.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Formateur.
     */
    @Test
    public void testSetId() {
        int expResult = 2;
        leFormateur.setId(expResult);
        int result = leFormateur.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNom method, of class Formateur.
     */
    @Test
    public void testGetNom() {
        String expResult = "Dujardin";
        String result = leFormateur.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNom method, of class Formateur.
     */
    @Test
    public void testSetNom() {
        String expResult = "LaFontaine";
        leFormateur.setNom(expResult);
        String result = leFormateur.getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrenom method, of class Formateur.
     */
    @Test
    public void testGetPrenom() {
        String expResult = "Jean";
        String result = leFormateur.getPrenom();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrenom method, of class Formateur.
     */
    @Test
    public void testSetPrenom() {
        String expResult = "Pedro";
        leFormateur.setPrenom(expResult);
        String result = leFormateur.getPrenom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInitiales method, of class Formateur.
     */
    @Test
    public void testGetInitiales() {
        String expResult = "JD";
        String result = leFormateur.getInitiales();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInitiales method, of class Formateur.
     */
    @Test
    public void testSetInitiales() {
        String expResult = "PL";
        leFormateur.setInitiales(expResult);
        String result = leFormateur.getInitiales();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTelephone method, of class Formateur.
     */
    @Test
    public void testGetTelephone() {
        String expResult = "0102030405";
        String result = leFormateur.getTelephone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTelephone method, of class Formateur.
     */
    @Test
    public void testSetTelephone() {
        String expResult = "0101010101";
        leFormateur.setTelephone(expResult);
        String result = leFormateur.getTelephone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Formateur.
     */
    @Test
    public void testGetEmail() {
        String expResult = "Jean.Dujardin@u-pec.fr";
        String result = leFormateur.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Formateur.
     */
    @Test
    public void testSetEmail() {
        String expResult = "JDujardin@u-pec.fr";
        leFormateur.setEmail(expResult);
        String result = leFormateur.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListeSceances method, of class Formateur.
     */
    @Test
    public void testGetListeSeances() {
        HashMap<Integer, Seance> result = leFormateur.getListeSceances();
        HashMap<Integer, Seance> expResult = lesSeances;
        assertSame(expResult, result);
    }

    /**
     * Test of setListeSceances method, of class Formateur.
     */
    @Test
    public void testSetListeSceances() {
        HashMap<Integer, Seance> lesNouvllesSeances = new HashMap<Integer, Seance>();
        leModule = new Module(1, "Base de données", "BDD", "Black", 10,laFormation ,lesSeances);
        Date dateSeanceTrois = new Date();
        Seance seanceTrois = new Seance(1, 1, dateSeanceTrois, leModule, leFormateur);
        Date dateSeanceQuatre = new Date();
        Seance seanceQuatre = new Seance(1, 2, dateSeanceQuatre, leModule, leFormateur);
        lesNouvllesSeances.put(1, seanceTrois);
        lesNouvllesSeances.put(2, seanceQuatre);
        leFormateur.setListeSceances(lesNouvllesSeances);
        HashMap<Integer, Seance> result = leFormateur.getListeSceances();
        HashMap<Integer, Seance> expResult = lesNouvllesSeances;
        assertSame(expResult, result);
    }

    /**
     * Test of getListeSceances method, of class Formateur.
     */
    @Test
    public void testGetListeSceances() {
        HashMap<Integer, Seance> expResult = lesSeances;
        HashMap<Integer, Seance> result = leFormateur.getListeSceances();
        assertSame(expResult, result);
    }

    /**
     * Test of addSeance method, of class Formateur.
     */
    @Test
    public void testAddSeance() {
        boolean presenceSeance = false;
        Date dateSeanceTrois = new Date();
        Seance nouvelleSeance = new Seance(1, 1, dateSeanceTrois, leModule, leFormateur);
        leFormateur.addSeance(Integer.SIZE, nouvelleSeance);
        for(int lesCles : leFormateur.getListeSceances().keySet()){
            Seance uneSeance = leFormateur.getListeSceances().get(lesCles);
            if (uneSeance.equals(nouvelleSeance)){
                presenceSeance = true;
            }
        }
        if (presenceSeance == false) {
            fail("La séance n\'a pas été ajoutée !");
        }
    }

    /**
     * Test of toString method, of class Formateur.
     */
    @Test
    public void testToString() {
        String expResult = "Jean Dujardin";
        String result = leFormateur.toString();
        assertEquals(expResult, result);
    }

}
