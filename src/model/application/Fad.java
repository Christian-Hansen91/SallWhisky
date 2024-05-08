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

    public Fad(int id, int kapacitet, String ophavsland, LocalDate indkoebsdato, String historik) {
        this.id = id;
        this.kapacitet = kapacitet;
        this.ophavsland = ophavsland;
        this.indkoebsdato = indkoebsdato;
        this.historik = historik;
    }

    public void tilfoejDestillat(Destillat destillat) {
        if (!destillater.contains(destillat)) {
            destillater.add(destillat);
        }
    }

    public boolean tjekPlads(double liter) {
        double nuvaerendeIndhold = 0;
        for (Destillat destillat : destillater) {
            nuvaerendeIndhold += destillat.hentTotalMaengde();
        }
        return nuvaerendeIndhold + liter <= kapacitet;
    }
    public double hentOpbrugtKapacitet() {
        double total = 0;
        for (Destillat destillat : destillater) {
            total += destillat.hentTotalMaengde();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Fad, ID: " + id + "\n" +
                "Kapacitet: " + kapacitet + "\n" +
                "Lagerplads: " + lagerplads + "\n" +
                "Ophavsland: " + ophavsland + "\n" +
                "Indkøbsdato: " + indkoebsdato + "\n" +
                "Historik: " + historik + "\n" +
                "Destillater: " + destillater + "\n";
    }
}
