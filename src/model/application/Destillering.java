package model.application;

import java.time.LocalDate;
import java.util.*;

public class Destillering {
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
    private List<Tapning> tapninger;
    private Maltning maltning;

    public Destillering(int newMakeNr, LocalDate startdato, LocalDate slutdato, double maengdeVaeske,
                        double head, double heart, double tail, String kommentar, double alkoholprocent) {
        this.newMakeNr = newMakeNr;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.maengdeVaeske = maengdeVaeske;
        this.head = head;
        this.heart = heart;
        this.tail = tail;
        this.kommentar = kommentar;
        this.alkoholprocent = alkoholprocent;
    }

    public Tapning opretTapning(Destillat destillat, double maengde, String kommentar) {
        if (!destillat.getFad().tjekPlads(maengde)) {
            throw new IllegalArgumentException("Der er ikke plads til den ønskede mængde i det fad");
        }
        Tapning tapning = new Tapning(maengde, this, destillat, kommentar);
        destillat.saetKommentar(kommentar);
        return tapning;
    }

    public Tapning opretTapningNytDestillat(Fad fad, double maengde) {
        if (!fad.tjekPlads(maengde)) {
            throw new IllegalArgumentException("Der er ikke plads til den ønskede mængde i det valgte fad");
        }
        Destillat destillat = new Destillat(fad);
        Tapning tapning = new Tapning(maengde, this, destillat, kommentar);
        return tapning;
    }

    @Override
    public String toString() {
        return "Destillering, ID: " + newMakeNr + "\n" +
                "Tapninger: " + tapninger + "\n" +
                "Startdato: " + startdato +
                ", slutdato: " + slutdato + "\n" +
                "Væske (L): " + maengdeVaeske + "\n" +
                "Malt: " + maltning + "\n" +
                "Head: " + head +
                ", heart: " + heart +
                ", tail: " + tail + "\n" +
                "Alkoholprocent: " + alkoholprocent + "\n" +
                "Kommentar: " + kommentar + ".\n";
    }
}
