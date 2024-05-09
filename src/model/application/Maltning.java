package model.application;

import java.time.LocalDate;

public class Maltning {
    private int id;
    private LocalDate dato;
    private String kornsort;
    private String marknavn;
    private double maengde;
    private String rygemateriale;
    private String kommentar;
    private static int totalAntal;

    public Maltning(double maengde, String kornsort, String marknavn) {
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kornsort = kornsort;
        this.marknavn = marknavn;
        this.maengde = maengde;
        Maltning.totalAntal++;

    }
    public Maltning(double maengde, String kornsort, String marknavn, String rygemateriale) {
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kornsort = kornsort;
        this.marknavn = marknavn;
        this.maengde = maengde;
        this.rygemateriale = rygemateriale;
        Maltning.totalAntal++;
    }

    @Override
    public String toString() {
        return "Maltning, ID: " + id + "\n" +
                "Dato: " + dato + "\n" +
                "Korntype: " + kornsort +
                ", marknavn: " + marknavn + "\n" +
                "Mængde=" + maengde + "\n" +
                "Rygemateriale: " + rygemateriale + "\n" +
                "Kommentar: " + kommentar + "\n";
    }
}
