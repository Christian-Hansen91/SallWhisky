package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private static int totalAntal;
    private int id;
    private LocalDate dato;
    private String kommentar;
    private List<VaeskeTilDestillat> tapninger = new ArrayList<>();
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
    public void tilfoejTapning(VaeskeTilDestillat vaeskeTilDestillat) {
        if (!tapninger.contains(vaeskeTilDestillat)) {
            tapninger.add(vaeskeTilDestillat);
        }
    }
    public double hentTotalMaengde() {
        double liter = 0;
        for (VaeskeTilDestillat vaeskeTilDestillat : tapninger) {
            liter += vaeskeTilDestillat.getMaengde();
        }
        return liter;
    }

    public Fad getFad() {
        return fad;
    }
}
