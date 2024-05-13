package controller;

import model.application.Destillering;
import model.application.Fad;
import model.application.Maltning;
import model.application.Medarbejder;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    public static Maltning opretMaltning(double maengde, String korntype, String marknavn) {
        Maltning maltning = new Maltning(maengde, korntype, marknavn);
        Storage.addMaltning(maltning);
        return maltning;
    }
    public static Maltning opretMaltning(double maengde, String korntype, String marknavn, String rygemateriale) {
        Maltning maltning = new Maltning(maengde, korntype, marknavn, rygemateriale);
        Storage.addMaltning(maltning);
        return maltning;
    }

    public static Fad opretFad(int id, LocalDate indkoebsdato, String fadtype, int kapacitet, String ophavsland, String leverandoer, String historik) {
        Fad fad = new Fad(id, indkoebsdato, fadtype, kapacitet, ophavsland, leverandoer, historik);
        Storage.addFad(fad);
        return fad;
    }

    public static Destillering opretDestillering(Maltning maltning, LocalDate startdato, LocalDate slutdato, double maengdeVand,
                                                 double head, double heart, double tail, String kommentar, double alkoholprocent, Medarbejder medarbejder) {
        Destillering destillering = new Destillering(maltning, startdato, slutdato, maengdeVand, head, heart, tail, kommentar, alkoholprocent, medarbejder);
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
