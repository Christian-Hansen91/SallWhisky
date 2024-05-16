package model.application;

import java.time.LocalDate;

public class VaeskeTilDestillat {
    private Whiskydestillering whiskydestillering;
    private Whisky whisky;
    private LocalDate dato;
    private double maengde;
    private String kommentar;

    VaeskeTilDestillat(double maengde, Whiskydestillering whiskydestillering) {
        this.maengde = maengde;
        this.whiskydestillering = whiskydestillering;
        this.dato = LocalDate.now();
        this.kommentar = kommentar;
    }
    public double getMaengde() {
        return maengde;
    }

    public Whiskydestillering getWhiskydestillering() {
        return whiskydestillering;
    }

    @Override
    public String toString() {
        return "Tapning: " + "\n" +
                "Destillering: " + whiskydestillering + "\n" +
                "Dato: " + dato + "\n" +
                "Mængde: " + maengde + "\n" +
                "Kommentar: " + kommentar + "\n";
    }
}
