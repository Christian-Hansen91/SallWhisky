package model.application;

public class Medarbejder {
    private String navn;
    private int id;
    private int tlfNr;
    private static int totalAntal;

    public Medarbejder(int id, String navn, int tlfNr) {
        this.id = totalAntal;
        this.navn = navn;
        this.tlfNr = tlfNr;
        Medarbejder.totalAntal++;
    }

    public String getNavn() {
        return navn;
    }

    public int getId() {
        return id;
    }

    public int getTlfNr() {
        return tlfNr;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Navn: " + navn + ", Tlf.:" + tlfNr + ".";
    }
}
