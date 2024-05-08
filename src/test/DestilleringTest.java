package test;

import model.application.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;

class DestilleringTest {
    Maltning maltning;
    Destillering destillering;
    Fad fad;
    Destillat destillat;

    @BeforeEach
    void setup() {
        maltning = new Maltning(100, "Byg");
        destillering = new Destillering(maltning);
        fad = new Fad(60, "sherry");
        destillat = new Destillat(fad);
    }

    @Test
    void opretTapning() {
        Tapning tapning = destillering.opretTapning(destillat,100,"Music Project");

    }
}