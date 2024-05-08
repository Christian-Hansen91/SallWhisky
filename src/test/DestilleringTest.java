package test;

import model.application.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestilleringTest {
    Maltning maltning;
    Destillering destillering;
    Fad fad;
    Destillat destillat;
    Tapning tapning;

    @BeforeEach
    void setup() {
        maltning = new Maltning(100, "Byg");
        destillering = new Destillering(maltning);
        fad = new Fad(60, "sherry");
        destillat = new Destillat(fad);
    }
    @Test
    void opretTapning() {
        double result = 60;
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertDoesNotThrow(() -> destillering.opretTapning(destillat, result,"test project"));
        assertEquals(result,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningOverfyldt() {
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertThrows(IllegalArgumentException.class, () -> destillering.opretTapning(destillat,100,"Music Project"));
        assertEquals(0,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningNytDestillatOverfyldt() {
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertThrows(IllegalArgumentException.class, () -> destillering.opretTapningNytDestillat(fad, 100));
        assertEquals(0,fad.hentOpbrugtKapacitet());
    }
    @Test
    void opretTapningNytDestillat() {
        double result = 60;
        assertEquals(0,fad.hentOpbrugtKapacitet());
        assertDoesNotThrow(() -> destillering.opretTapningNytDestillat(fad, result));
        assertEquals(result,fad.hentOpbrugtKapacitet());
    }
}