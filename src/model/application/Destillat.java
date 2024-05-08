package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private static int totalAntal;
    private int id;
    private List<Tapning> tapninger = new ArrayList<>();
    private LocalDate dato;
    private Fad fad;
    private String kommentar;

    public Destillat(Fad fad) {
        this.fad = fad;
        fad.tilfoejDestillat(this);
        this.dato = LocalDate.now();
        this.kommentar = kommentar;
        Destillat.totalAntal++;
        this.id = totalAntal;
    }
    public void saetKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
    public void tilfoejTapning(Tapning tapning) {
        if (!tapninger.contains(tapning)) {
            tapninger.add(tapning);
        }
    }
    public boolean pladsIToenden
    public double hentTotalMaengde() {
        double liter = 0;
        for (Tapning tapning : tapninger) {
            liter += tapning.getMaengde();
        }
        return liter;
    }

}
