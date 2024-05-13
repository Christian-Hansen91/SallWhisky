package model.application;

public class Medarbejder {
    private String navn;
    private int id;
    private int tlfNr;
    private static int totalAntal;

    public Medarbejder(String navn, int tlfNr) {
        Medarbejder.totalAntal++;
        this.id = totalAntal;
        this.navn = navn;
        this.tlfNr = tlfNr;
    }

    public static int getTotalAntal() {
        return totalAntal;
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
