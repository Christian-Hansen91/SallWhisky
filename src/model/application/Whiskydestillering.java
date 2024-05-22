package model.application;

import java.time.LocalDate;
import java.util.*;

public class Whiskydestillering {
    private static int totalAntal;
    private int newMakeNr;
    private LocalDate startdato;
    private LocalDate slutdato;
    private double maengdeVaeske;
    private double head;
    private double heart;
    private double tail;
    private double alkoholprocent;
    private String kommentar;
    private List<VaeskeTilDestillat> VaeskerTilDestillat = new ArrayList<>();
    private Maltning maltning;
    private Medarbejder medarbejder;

    public Whiskydestillering(Maltning maltning, LocalDate startdato, LocalDate slutdato, double maengdeVaeske,
                              double head, double heart, double tail, String kommentar, double alkoholprocent, Medarbejder medarbejder) {
        Whiskydestillering.totalAntal++;
        this.newMakeNr = totalAntal;
        this.maltning = maltning;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.maengdeVaeske = maengdeVaeske;
        this.head = head;
        this.heart = heart;
        this.tail = tail;
        this.kommentar = kommentar;
        this.alkoholprocent = alkoholprocent;
        this.medarbejder = medarbejder;
        medarbejder.tilfoejDestillering(this);
    }

    public VaeskeTilDestillat opretVaeskeTilDestillat(double maengde) {
        if (tjekNokMaengde(maengde)) {
            VaeskeTilDestillat vaeskeTilDestillat = new VaeskeTilDestillat(maengde, this);
            return vaeskeTilDestillat;
        } else {
            throw new IllegalArgumentException("Der er ikke nok destillat til at lave denne tapning");
        }
    }
    public int getNewMakeNr() {
        return newMakeNr;
    }

    private boolean tjekNokMaengde(double liter) {
        double maengde = 0;
        for (VaeskeTilDestillat vaeskeTilDestillat : VaeskerTilDestillat) {
            maengde += vaeskeTilDestillat.getMaengde();
        }
        return maengde + liter <= heart;
    }

    public double hentTilgaengeligVaeske() {
        double vaeskeTilbage = heart;
        for (VaeskeTilDestillat vaeskeTilDestillat : VaeskerTilDestillat) {
            vaeskeTilbage -= vaeskeTilDestillat.getMaengde();
        }
        return vaeskeTilbage;
    }

    @Override
    public String toString() {
        return "Destillering, ID: " + newMakeNr + "\n" +
                "Startdato: " + startdato +
                ", slutdato: " + slutdato + "\n" +
                "Tilgængelig væske (L): " + hentTilgaengeligVaeske() + "\n" +
                "Alkoholprocent: " + alkoholprocent + "\n" +
                "Kommentar: " + kommentar + ".\n\n" +
                "Malt: " + maltning + "\n" +
                "Medarbejder: " + medarbejder;
    }

    public void tilfoejVaeskemaengde(VaeskeTilDestillat vaeskeTilDestillat) {
        if (!VaeskerTilDestillat.contains(vaeskeTilDestillat)) {
            VaeskerTilDestillat.add(vaeskeTilDestillat);
        }
    }

    public void fjernVaeske(VaeskeTilDestillat vaeskeTilDestillat) {
        if (VaeskerTilDestillat.contains(vaeskeTilDestillat)) {
            VaeskerTilDestillat.remove(vaeskeTilDestillat);
            vaeskeTilDestillat.fjernVaeske();
        }
    }
}