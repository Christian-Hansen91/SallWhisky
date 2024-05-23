package test;

import model.application.Lager;
import model.application.Lagerenhed;
import model.application.Medarbejder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.fake_classes.FakeLagerenhed;

import static org.junit.jupiter.api.Assertions.*;


public class LagerTest {
    private Lager lager;

    @Test
    void testConstructorGetters() {
        String navn = "Lars' Lager";
        Lagerenhed[][] reolliste = new Lagerenhed[2][4];
        Medarbejder snaevar = new Medarbejder("Snævar", 000);

        Lager result = new Lager(navn, reolliste, snaevar);
        //TC1
        assertNotNull(result);
        //TC2
        assertEquals(result.getNavn(), navn);
        //TC3
        assertEquals(result.getReolliste(), reolliste);
    }

    @BeforeEach
    void setup() {
        lager = new Lager("Lars' lager", new Lagerenhed[2][4], new Medarbejder("Snævar", 000));
    }

    @Test
    void testAddReol() {
        int hylder = 5;
        lager.addReol(hylder);
        //TC1
        assertEquals(3, lager.getReolliste().length);
        assertEquals(5, lager.getReolliste()[2].length);
        //TC2
        assertThrows(IllegalArgumentException.class, () -> lager.addReol(-1));
        //TC3
        assertThrows(IllegalArgumentException.class, () -> lager.addReol(0));
    }

    @Test
    void testAddLagerenhedAt() {
        int reoler = 1;
        int hylder = 3;
        FakeLagerenhed lagerenhed = new FakeLagerenhed();
        //TC1
        lager.addLagerenhedAt(reoler, hylder, lagerenhed);
        lagerenhed.tilfoejLager(lager,reoler,hylder);
        assertEquals(lagerenhed, lager.getReolliste()[reoler][hylder]);
        assertEquals(lager, lagerenhed.getLager());
        //TC2
        assertThrows(IllegalArgumentException.class, () -> lager.addLagerenhedAt(reoler, hylder, lagerenhed));
        //TC3
        assertThrows(IllegalArgumentException.class, () -> lager.addLagerenhedAt(-1, 3, lagerenhed));
        //TC4
        assertThrows(IllegalArgumentException.class, () -> lager.addLagerenhedAt(1, -1, lagerenhed));
        //TC5
        assertThrows(IllegalArgumentException.class, () -> lager.addLagerenhedAt(5, 5, lagerenhed));
    }

    @Test
    void testLagerpladsLedig() {
        int reol = 1;
        int hylde = 1;
        lager.addLagerenhedAt(1,3,new FakeLagerenhed());
        //TC1
        boolean result = lager.lagerpladsLedig(reol, hylde);
        assertTrue(result);
        //TC2
        reol = 0;
        hylde = 2;
        result = lager.lagerpladsLedig(reol,hylde);
        assertTrue(result);
        //TC3
        reol = 1;
        hylde = 0;
        result = lager.lagerpladsLedig(reol,hylde);
        assertTrue(result);
        //TC4
        reol = 1;
        hylde = 3;
        result = lager.lagerpladsLedig(reol,hylde);
        assertFalse(result);
        //TC5
        assertThrows(IllegalArgumentException.class, () -> lager.lagerpladsLedig(5,2));
        //TC6
        assertThrows(IllegalArgumentException.class, () -> lager.lagerpladsLedig(1,7));
        //TC7
        assertThrows(IllegalArgumentException.class, () -> lager.lagerpladsLedig(-1,2));
        //TC8
        assertThrows(IllegalArgumentException.class, () -> lager.lagerpladsLedig(1,-1));
    }
}
