package model.application;

import java.time.LocalDate;

public class Tapning {
    private Destillering destillering;
    private Destillat destillat;
    private LocalDate dato;
    private double maengde;
    private String kommentar;

    Tapning(double maengde, Destillering destillering, Destillat destillat, String kommentar) {
        this.maengde = maengde;
        this.destillat = destillat;
        destillat.tilfoejTapning(this);
        this.destillering = destillering;
        this.dato = LocalDate.now();
        this.kommentar = kommentar;
    }
    public double getMaengde() {
        return maengde;
    }

    @Override
    public String toString() {
        return "Tapning: " + "\n" +
                "Destillering: " + destillering + "\n" +
                "Dato: " + dato + "\n" +
                "Mængde: " + maengde + "\n" +
                "Kommentar: " + kommentar + "\n";
    }
}
