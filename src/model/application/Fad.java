package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fad {
    private static int totalAntal;
    private int id;
    private int kapacitet;
    private String lagerplads;
    private String ophavsland;
    private LocalDate indkoebsdato;
    private String historik;
    private List<Destillat> destillater = new ArrayList<>();

    public Fad(int kapacitet, String historik) {
        this.kapacitet = kapacitet;
        this.historik = historik;
    }

    public void tilfoejDestillat(Destillat destillat) {
        if (!destillater.contains(destillat)) {
            destillater.add(destillat);
        }
    }

    public boolean tjekKapacitet(double liter) {
        double nuvaerendeIndhold = 0;
        for (Destillat destillat : destillater) {
            nuvaerendeIndhold += destillat.hentTotalMaengde();
        }
        return nuvaerendeIndhold + liter <= kapacitet;
    }
}
