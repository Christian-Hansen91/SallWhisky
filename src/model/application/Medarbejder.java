package model.application;

public class Medarbejder {
    private String navn;
    private int id;
    private int tlfNr;

    public Medarbejder(String navn, int id, int tlfNr) {
        this.navn = navn;
        this.id = id;
        this.tlfNr = tlfNr;
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
        return "Navn: " + navn + ", ID: " + id + ", Tlf.:" + tlfNr + ".";
    }
}
