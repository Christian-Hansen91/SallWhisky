package model.application;

import java.time.LocalDate;

public class Fad implements Lagerenhed {
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

    public Fad(LocalDate indkoebsdato, String fadtype, int kapacitet, String ophavsland,
               String leverandoer, String historik, Medarbejder medarbejder) {
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
    public int getId() {
        return id;
    }

    public String getFadtype() {
        return fadtype;
    }

    public void saetDestillat(Destillat destillat) {
        this.destillat = destillat;
    }


    public Destillat getDestillat() {
        return destillat;
    }

    public double hentOpbrugtKapacitet() {
        return destillat.hentTotalMaengde();
    }

    @Override
    public String toString() {
        return "FAD #" + id + "\n" +
                "Indkøbsdato: " + indkoebsdato + "\n" +
                "Fadtype: " + fadtype + "\n" +
                "Kapacitet: " + kapacitet + "\n" +
                "Ophavsland: " + ophavsland +
                ", Leverandør: " + leverandoer + "\n" +
                "Historik: " + historik + "\n" +
                medarbejder;
    }

    @Override
    public void tilfoejLager(Lager lager, int reol, int hylde) {
        if (!lager.equals(this.lager)) {
            this.lager = lager;
        }
    }

    public void fjernDestilat() {
        if (!(destillat.getKommentar().isEmpty())) {
            historik += ". På fadet har destillatet #" + destillat.getId() + " lagt, som blandt andet " + destillat.getKommentar().get(0);
        } else {
            historik += ". På fadet har destillatet #" + destillat.getId() + " lagt";
        }
        destillat = null;
        System.out.println(historik);
    }

    public double getKapacitet() {
        return kapacitet;
    }
}
