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
    Tapning tapning;

    @BeforeEach
    void setup() {
        maltning = new Maltning(100, "Byg","Lars' Mark");
        medarbejder = new Medarbejder("alice",5050);
        whiskyDestillering = new Whiskydestillering(maltning, LocalDate.now(), LocalDate.now(),1000,50,850,150,"test",60,medarbejder);
        fad = new Fad(LocalDate.now(),"eg",70,"spanien","spanish cooperage","sherry");
        destillat = new Destillat(fad);
    }
    @Test
    void opretTapning() {
        double result = 60;
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertDoesNotThrow(() -> whiskyDestillering.opretTapning(destillat, result,"test project"));
        assertEquals(result,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningOverfyldt() {
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertThrows(IllegalArgumentException.class, () -> whiskyDestillering.opretTapning(destillat,100,"Music Project"));
        assertEquals(0,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningNytDestillatOverfyldt() {
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertThrows(IllegalArgumentException.class, () -> whiskyDestillering.opretTapningNytDestillat(fad, 100));
        assertEquals(0,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningNytDestillat() {
        double result = 60;
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertDoesNotThrow(() -> whiskyDestillering.opretTapningNytDestillat(fad, result));
        assertEquals(result,fad.hentOpbrugtKapacitet());
    }

    @Test
    void opretMaltning() {
        Maltning maltning = new Maltning(500, "byg","Lars");
        assertNotNull(maltning);
    }
}