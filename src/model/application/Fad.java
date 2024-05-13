package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fad {
    private static int totalAntal;
    private int id;
    private LocalDate indkoebsdato;
    private String fadtype;
    private int kapacitet;
    private String ophavsland;
    private String leverandoer;
    private String historik;
    private Lager lager;
    private List<Destillat> destillater = new ArrayList<>();

    public Fad(LocalDate indkoebsdato, String fadtype, int kapacitet, String ophavsland, String leverandoer, String historik) {
        this.id = id;
        this.indkoebsdato = indkoebsdato;
        this.fadtype = fadtype;
        this.kapacitet = kapacitet;
        this.ophavsland = ophavsland;
        this.leverandoer = leverandoer;
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
                "Indkøbsdato: " + indkoebsdato + "\n" +
                "Fadtype: " + fadtype + "\n" +
                "Kapacitet: " + kapacitet + "\n" +
                "Ophavsland: " + ophavsland +
                ", leverandør: " + leverandoer + "\n" +
                "Historik: " + historik + "\n" +
                "Destillater: " + destillater + "\n";
    }
}
