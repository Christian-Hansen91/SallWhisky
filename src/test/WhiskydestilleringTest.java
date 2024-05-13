package test;

import model.application.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhiskydestilleringTest {
    Maltning maltning;
    Whiskydestillering whiskydestillering;
    Fad fad;
    Destillat destillat;
    Tapning tapning;

    /*@BeforeEach
    void setup() {
        maltning = new Maltning(100, "Byg");
        destillering = new Destillering(maltning);
        fad = new Fad(60, "sherry");
        destillat = new Destillat(fad);
    }*/
    @Test
    void opretTapning() {
        double result = 60;
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertDoesNotThrow(() -> whiskydestillering.opretTapning(destillat, result,"test project"));
        assertEquals(result,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningOverfyldt() {
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertThrows(IllegalArgumentException.class, () -> whiskydestillering.opretTapning(destillat,100,"Music Project"));
        assertEquals(0,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningNytDestillatOverfyldt() {
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertThrows(IllegalArgumentException.class, () -> whiskydestillering.opretTapningNytDestillat(fad, 100));
        assertEquals(0,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningNytDestillat() {
        double result = 60;
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertDoesNotThrow(() -> whiskydestillering.opretTapningNytDestillat(fad, result));
        assertEquals(result,fad.hentOpbrugtKapacitet());
    }
}