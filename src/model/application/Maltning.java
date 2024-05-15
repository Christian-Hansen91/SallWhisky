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

    public String getKornsort() {
        return kornsort;
    }

    public String getMarknavn() {
        return marknavn;
    }

    public double getMaengde() {
        return maengde;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }

    public Maltning(double maengde, String kornsort, String marknavn) {
        Maltning.totalAntal++;
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kornsort = kornsort;
        this.marknavn = marknavn;
        this.maengde = maengde;
    }
    public Maltning(double maengde, String kornsort, String marknavn, String rygemateriale) {
        Maltning.totalAntal++;
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kornsort = kornsort;
        this.marknavn = marknavn;
        this.maengde = maengde;
        this.rygemateriale = rygemateriale;
    }

    public static int getTotalAntal() {
        return totalAntal;
    }

    @Override
    public String toString() {
        return "Maltning, ID: " + id + "\n" +
                "Dato: " + dato + "\n" +
                "Korntype: " + kornsort +
                ", marknavn: " + marknavn + "\n" +
                "Mængde: " + maengde + "\n" +
                "Rygemateriale: " + rygemateriale + "\n" +
                "Kommentar: " + kommentar;
    }
}
