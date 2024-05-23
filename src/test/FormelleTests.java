package test;

import model.application.*;
import org.junit.jupiter.api.Test;
import test.fake_classes.FakeVaeskeFraDestillering;
import test.fake_classes.FakeVaeskeTilWhisky;
import test.fake_classes.VaeskeInterface;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FormelleTests {
    private Medarbejder medarbejder = new Medarbejder("Snævar", 00);
    private Fad fad = new Fad(LocalDate.now(), "Eg", 120, "Spanien", "Los Cooperagos", "Oprindeligt sherryfad", medarbejder);
    private Maltning maltning = new Maltning(100, "Byg","Lars' Mark", medarbejder);
    @Test
    void testOpretVaeskeTilDestillat() {
        double startMaengde = 800;
        Whiskydestillering whiskydestillering = new Whiskydestillering(maltning,LocalDate.now(),LocalDate.now(),50,startMaengde,150,"",60,medarbejder);
        double maengdeTilDestillat = 100;
        //TC1
        VaeskeInterface result = whiskydestillering.opretVaeskeTilDestillat(maengdeTilDestillat);
        assertTrue(whiskydestillering.getTapninger().contains(result));
        assertEquals(result.getWhiskydestillering(),whiskydestillering);
        assertEquals(whiskydestillering.hentTilgaengeligVaeske(),700);
        //TC2
        maengdeTilDestillat = 700;
        result = whiskydestillering.opretVaeskeTilDestillat(maengdeTilDestillat);
        assertTrue(whiskydestillering.getTapninger().contains(result));
        assertEquals(result.getWhiskydestillering(),whiskydestillering);
        assertEquals(whiskydestillering.hentTilgaengeligVaeske(),0);
        assertEquals(whiskydestillering.getTapninger().size(),2);
        //TC3
        assertThrows(IllegalArgumentException.class, () -> whiskydestillering.opretVaeskeTilDestillat(-100));
        //TC4
        assertThrows(IllegalArgumentException.class, () -> whiskydestillering.opretVaeskeTilDestillat(100));

    }
    @Test
    void testHentTotalMaengdeDestillat() {
        Destillat destillat = new Destillat(fad, medarbejder);
        FakeVaeskeFraDestillering vaeskeTilDestillat = new FakeVaeskeFraDestillering(50);
        FakeVaeskeTilWhisky vaeskeTilWhisky = new FakeVaeskeTilWhisky(40);
        destillat.tilfoejVaeskeTilDestillat(vaeskeTilDestillat);
        destillat.tilfoejVaeskeTilWhisky(vaeskeTilWhisky);
        destillat.setAngelShare(5);
        //TC1
        double result = destillat.hentTotalMaengde();
        assertEquals(result, 5);
        //TC2
        vaeskeTilWhisky.setMaengde(45);
        result = destillat.hentTotalMaengde();
        assertEquals(result,0);
        //TC3
        vaeskeTilDestillat.setMaengde(50);
        vaeskeTilWhisky.setMaengde(60);
        assertThrows(IllegalArgumentException.class,() -> destillat.hentTotalMaengde());
        //TC4
        vaeskeTilDestillat.setMaengde(50);
        vaeskeTilWhisky.setMaengde(60);
        assertThrows(IllegalArgumentException.class,() -> destillat.hentTotalMaengde());
        //TC5
        vaeskeTilDestillat.setMaengde(50);
        vaeskeTilWhisky.setMaengde(-10);
        assertThrows(IllegalArgumentException.class,() -> destillat.hentTotalMaengde());
        //TC6
        vaeskeTilDestillat.setMaengde(50);
        vaeskeTilWhisky.setMaengde(50);
        destillat.setAngelShare(-10);
        assertThrows(IllegalArgumentException.class,() -> destillat.hentTotalMaengde());
    }
}
