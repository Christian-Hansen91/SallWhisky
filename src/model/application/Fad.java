package model.application;

import java.time.LocalDate;

public class Fad implements Lagerenhed{
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
    private Medarbejder medarbejder;

    public Fad(LocalDate indkoebsdato, String fadtype, int kapacitet, String ophavsland, String leverandoer, String historik, Medarbejder medarbejder) {
        totalAntal++;
        this.id = totalAntal;
        this.indkoebsdato = indkoebsdato;
        this.fadtype = fadtype;
        this.kapacitet = kapacitet;
        this.ophavsland = ophavsland;
        this.leverandoer = leverandoer;
        this.historik = historik;
        this.medarbejder = medarbejder;
    }

    public void saetDestillat(Destillat destillat) {
        this.destillat = this.destillat;
    }

    public boolean tjekPlads(double liter) {
        return hentOpbrugtKapacitet() + liter <= kapacitet;
    }
    public Destillat getDestillat() {
        return destillat;
    }

    public double hentOpbrugtKapacitet() {
        return destillat.hentTotalMaengde();
    }
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
                "Historik: " + historik + "\n" +
                "Medarbejder: " + medarbejder;
    }

    @Override
    public void tilfoejLager(Lager lager, int reol, int hylde) {
        if (!lager.equals(this.lager)) {
            this.lager = lager;
            lager.addLagerenhedAt(reol, hylde, this);
        }
    }
}
