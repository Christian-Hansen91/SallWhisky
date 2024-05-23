package model.application;

import gui.StartVindue;

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
    private Medarbejder medarbejder;

    public String getMarknavn() {
        return marknavn;
    }

    public double getMaengde() {
        return maengde;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }

    public Maltning(double maengde, String kornsort, String marknavn, Medarbejder medarbejder) {
        Maltning.totalAntal++;
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kornsort = kornsort;
        this.marknavn = marknavn;
        this.maengde = maengde;
        this.medarbejder = medarbejder;
    }

    public Maltning(double maengde, String kornsort, String marknavn, String rygemateriale, String kommentar, Medarbejder medarbejder) {
        Maltning.totalAntal++;
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kornsort = kornsort;
        this.marknavn = marknavn;
        this.maengde = maengde;
        this.rygemateriale = rygemateriale;
        this.medarbejder = medarbejder;
        this.kommentar = kommentar;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("MALTNING #" + id + "\n" +
                "Dato: " + dato + "\n" +
                "Korntype: " + kornsort +
                ", marknavn: " + marknavn + "\n" +
                "Mængde: " + maengde + "\n");
        if (!(rygemateriale == null || rygemateriale.equals(""))) {
            s.append("Rygemateriale: " + rygemateriale + "\n");
        }
        if (!(kommentar == null || kommentar.equals(""))) {
            s.append( "Kommentar: " + kommentar + "\n");
        }
        s.append(medarbejder);

        return s.toString();
    }
}
