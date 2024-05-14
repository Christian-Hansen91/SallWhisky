package model.application;

import storage.Storage;

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
    private List<Tapning> tapninger;
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

    public Destillat opretDestillat(Fad fad) {
        Destillat destillat = new Destillat(fad);
        Fad.tilfoejDestillat(destillat);
        return destillat;
    }

    public static int getTotalAntal() {
        return totalAntal;
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
