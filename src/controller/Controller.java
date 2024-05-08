package controller;

import model.application.Destillering;
import model.application.Fad;
import model.application.Maltning;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    public static Maltning opretMaltning(double maengde, String korntype) {
        Maltning maltning = new Maltning(maengde, korntype);
        Storage.addMaltning(maltning);
        return maltning;
    }
    public static Maltning opretMaltning(double maengde, String korntype, String rygemateriale) {
        Maltning maltning = new Maltning(maengde, korntype, rygemateriale);
        Storage.addMaltning(maltning);
        return maltning;
    }

    public static Fad opretFad(int id, int kapacitet, String ophavsland, LocalDate indkoebsdato, String historik) {
        Fad fad = new Fad(id, kapacitet, ophavsland, indkoebsdato, historik);
        Storage.addFad(fad);
        return fad;
    }

    public static Destillering opretDestillering(int id, LocalDate startdato, LocalDate slutdato, double maengdeVand,
                                                 double head, double heart, double tail, String kommentar, double alkoholprocent) {
        Destillering destillering = new Destillering(id, startdato, slutdato, maengdeVand, head, heart, tail, kommentar, alkoholprocent);
        Storage.addDestillering(destillering);
        return destillering;
    }

    public static ArrayList<Maltning> getMaltning() {
        return Storage.getMaltninger();
    }

    public static ArrayList<Fad> getFad() {
        return Storage.getFade();
    }

    public static ArrayList<Destillering> getDestillering() {
        return Storage.getDestilleringer();
    }


}
