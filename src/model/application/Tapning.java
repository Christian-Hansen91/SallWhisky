package model.application;

import java.time.LocalDate;

public class Tapning {
    private Whiskydestillering whiskydestillering;
    private Destillat destillat;
    private LocalDate dato;
    private double maengde;
    private String kommentar;

    Tapning(double maengde, Whiskydestillering whiskydestillering, String kommentar) {
        this.maengde = maengde;
        destillat.tilfoejTapning(this);
        this.whiskydestillering = whiskydestillering;
        this.dato = LocalDate.now();
        this.kommentar = kommentar;
    }

    public double getMaengde() {
        return maengde;
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
