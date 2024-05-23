package model.application;

import java.util.ArrayList;

public class Medarbejder {
    private String navn;
    private int id;
    private int tlfNr;
    private static int totalAntal;
    private ArrayList<Whiskydestillering> whiskydestilleringer = new ArrayList<>();

    public Medarbejder(String navn, int tlfNr) {
        Medarbejder.totalAntal++;
        this.id = totalAntal;
        this.navn = navn;
        this.tlfNr = tlfNr;
    }


    public String getNavn() {
        return navn;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ID: " + id + ", Navn: " + navn + ", Tlf.:" + tlfNr + "\n";
    }
    public void tilfoejDestillering(Whiskydestillering whiskydestillering) {
        this.whiskydestilleringer.add(whiskydestillering);
    }

    public ArrayList<Whiskydestillering> getWhiskydestilleringer() {
        return new ArrayList<>(whiskydestilleringer);
    }
}
