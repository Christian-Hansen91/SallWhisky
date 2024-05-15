package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private static int totalAntal;
    private int id;
    private LocalDate dato;
    private String kommentar;
    private List<Tapning> tapninger = new ArrayList<>();
    private Fad fad;


    public Destillat(Fad fad) {
        Destillat.totalAntal++;
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kommentar = kommentar;
        this.fad = fad;
        fad.saetDestillat(this);
    }
    public void saetKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
    public void tilfoejTapning(Tapning tapning) {
        if (!tapninger.contains(tapning)) {
            tapninger.add(tapning);
        }
    }

    public double hentTotalMaengde() {
        double liter = 0;
        for (Tapning tapning : tapninger) {
            liter += tapning.getMaengde();
        }
        return liter;
    }

    public Fad getFad() {
        return fad;
    }
}
