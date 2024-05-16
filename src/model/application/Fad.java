package model.application;

import java.time.LocalDate;

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
    private Destillat destillat;

    public Fad(LocalDate indkoebsdato, String fadtype, int kapacitet, String ophavsland, String leverandoer, String historik) {
        totalAntal++;
        this.id = totalAntal;
        this.indkoebsdato = indkoebsdato;
        this.fadtype = fadtype;
        this.kapacitet = kapacitet;
        this.ophavsland = ophavsland;
        this.leverandoer = leverandoer;
        this.historik = historik;
    }

    public void saetDestillat(Destillat destillat) {
        this.destillat = destillat;
    }

   /* public boolean tjekPlads(double liter) {
        double nuvaerendeIndhold = 0;
        if {
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
    }*/

    public static int getTotalAntal() {
        return totalAntal;
    }

    @Override
    public String toString() {
        return "Fad, ID: " + id + "\n" +
                "Indkøbsdato: " + indkoebsdato + "\n" +
                "Fadtype: " + fadtype + "\n" +
                "Kapacitet: " + kapacitet + "\n" +
                "Ophavsland: " + ophavsland +
                ", leverandør: " + leverandoer + "\n" +
                "Historik: " + historik + "\n";}
}
