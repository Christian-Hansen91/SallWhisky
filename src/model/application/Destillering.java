package model.application;

import java.time.LocalDate;
import java.util.*;

public class Destillering {
    private static int totalAntal;
    private int id;
    private List<Tapning> tapninger;
    private LocalDate startdato;
    private LocalDate slutdato;
    private double maengdeVand;
    private Maltning maltning;
    private double head;
    private double heart;
    private double tail;
    private String kommentar;
    private double alkoholprocent;

    public Destillering(Maltning maltning) {
        this.maltning = maltning;
    }

    public Tapning opretTapning(Destillat destillat, double maengde, String kommentar) {
        if (!destillat.getFad().tjekPlads(maengde)) {
            throw new IllegalArgumentException("Der er ikke plads til den ønskede mængde i det fad");
        }
        Tapning tapning = new Tapning(maengde, this, destillat);
        destillat.saetKommentar(kommentar);
        return tapning;
    }

    public Tapning opretTapningNytDestillat(Fad fad, double maengde) {
        if (!fad.tjekPlads(maengde)) {
            throw new IllegalArgumentException("Der er ikke plads til den ønskede mængde i det valgte fad");
        }
        Destillat destillat = new Destillat(fad);
        Tapning tapning = new Tapning(maengde, this, destillat);
        return tapning;
    }
}
