package model.application;

import java.time.LocalDate;

public class Maltning {
    private LocalDate dato;
    private String korntype;
    private String marknavn;
    private double maengde;
    private static int totalAntal;
    private int id;
    private String rygemateriale;
    private String kommentar;

    public Maltning(double maengde, String korntype) {
        this.maengde = maengde;
        this.korntype = korntype;
        this.dato = LocalDate.now();
        Maltning.totalAntal++;
        this.id = totalAntal;
    }
    public Maltning(double maengde, String korntype, String rygemateriale) {
        this.maengde = maengde;
        this.korntype = korntype;
        this.dato = LocalDate.now();
        Maltning.totalAntal++;
        this.id = totalAntal;
        this.rygemateriale = rygemateriale;
    }

    @Override
    public String toString() {
        return "Maltning: " + "\n" +
                "ID: " + id + "\n" +
                "Dato: " + dato + "\n" +
                "Korntype: " + korntype +
                ", marknavn: " + marknavn + "\n" +
                "Mængde=" + maengde + "\n" +
                "Rygemateriale: " + rygemateriale + "\n" +
                "Kommentar: " + kommentar + "\n";
    }
}
