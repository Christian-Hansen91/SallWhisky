package model.application;

import test.fake_classes.VaeskeInterface;

import java.time.LocalDate;
import java.util.*;

public class Whiskydestillering {
    private static int totalAntal;
    private int newMakeNr;
    private LocalDate startdato;
    private LocalDate slutdato;
    private double head;
    private double heart;
    private double tail;
    private double alkoholprocent;
    private String kommentar;
    private List<VaeskeInterface> tapninger = new ArrayList<>();
    private Maltning maltning;
    private Medarbejder medarbejder;

    public Whiskydestillering(Maltning maltning, LocalDate startdato, LocalDate slutdato,
                              double head, double heart, double tail, String kommentar, double alkoholprocent, Medarbejder medarbejder) {
        Whiskydestillering.totalAntal++;
        this.newMakeNr = totalAntal;
        this.maltning = maltning;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.head = head;
        this.heart = heart;
        this.tail = tail;
        this.kommentar = kommentar;
        this.alkoholprocent = alkoholprocent;
        this.medarbejder = medarbejder;
        medarbejder.tilfoejDestillering(this);
    }

    public VaeskeTilDestillat opretVaeskeTilDestillat(double maengde) {
        if (maengde > 0 && tjekNokMaengde(maengde)) {
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
        for (VaeskeInterface vaeskeTilDestillat : tapninger) {
            maengde += vaeskeTilDestillat.getMaengde();
        }
        return maengde + liter <= heart;
    }

    public double hentTilgaengeligVaeske() {
        double vaeskeTilbage = heart;
        for (VaeskeInterface vaeskeTilDestillat : tapninger) {
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

    public void tilfoejVaeskemaengde(VaeskeInterface vaeskeTilDestillat) {
        if (!tapninger.contains(vaeskeTilDestillat)) {
            tapninger.add(vaeskeTilDestillat);
        }
    }

    public void fjernVaeske(VaeskeInterface vaeskeTilDestillat) {
        if (tapninger.contains(vaeskeTilDestillat)) {
            tapninger.remove(vaeskeTilDestillat);
            vaeskeTilDestillat.fjernVaeske();
        }
    }

    public List<VaeskeInterface> getTapninger() {
        return new ArrayList<>(tapninger);
    }
}