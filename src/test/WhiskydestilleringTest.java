package test;

import model.application.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WhiskydestilleringTest {
    Maltning maltning;
    Whiskydestillering whiskyDestillering;
    Medarbejder medarbejder;
    Fad fad;
    Destillat destillat;
    VaeskeTilDestillat vaeskeTilDestillat;

    @BeforeEach
    void setup() {
        medarbejder = new Medarbejder("alice",5050);
        maltning = new Maltning(100, "Byg","Lars' Mark", medarbejder);
        whiskyDestillering = new Whiskydestillering(maltning, LocalDate.now(), LocalDate.now(),1000,50,850,150,"test",60,medarbejder);
        fad = new Fad(LocalDate.now(),"eg",70,"spanien","spanish cooperage","sherry", medarbejder);
        destillat = new Destillat(fad, medarbejder);
    }

    @Test
    void opretTapning() { //test normal oprettelse, test tapning større end mængde destillering
        double maengde = 60;
        VaeskeTilDestillat vaeskeTilDestillat = whiskyDestillering.opretVaeskeTilDestillat(maengde);
        assertEquals(0,fad.hentOpbrugtKapacitet());
        destillat.tilfoejTapning(vaeskeTilDestillat);
        assertDoesNotThrow(() -> whiskyDestillering.opretVaeskeTilDestillat(maengde));
        assertEquals(maengde,fad.hentOpbrugtKapacitet());
        assertThrows(IllegalArgumentException.class, () -> whiskyDestillering.opretVaeskeTilDestillat(10000));
        assertThrows(IllegalArgumentException.class, () -> whiskyDestillering.opretVaeskeTilDestillat(941));
        assertDoesNotThrow(() -> whiskyDestillering.opretVaeskeTilDestillat(940));
    }
    @Test
    void opretMaltning() {
        Maltning maltning = new Maltning(500, "byg","Lars", medarbejder);
        assertEquals("Lars",maltning.getMarknavn());
        assertNull(maltning.getRygemateriale());
        maltning = new Maltning(250,"også byg","Lars mark 2", "tørv", medarbejder);
        assertEquals("tørv", maltning.getRygemateriale());
    }

    @Test
    void opretDestillering() {
        whiskyDestillering = new Whiskydestillering(maltning,LocalDate.now(),LocalDate.now().plusDays(5),100,50,850,150,"testdestillering",60,medarbejder);
        assertNotNull(whiskyDestillering);
        assertEquals(whiskyDestillering,medarbejder.getWhiskydestilleringer().get(medarbejder.getWhiskydestilleringer().size() - 1));
    }
}