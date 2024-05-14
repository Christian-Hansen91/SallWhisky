package controller;

import model.application.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    public static Medarbejder opretMedarbejder(String navn, int tlfNr) {
        Medarbejder medarbejder = new Medarbejder(navn, tlfNr);
        Storage.addMedarbejder(medarbejder);
        return medarbejder;
    }
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
        Fad fad = new Fad(indkoebsdato, fadtype, kapacitet, ophavsland, leverandoer, historik);
        Storage.addFad(fad);
        return fad;
    }

    public static Whiskydestillering opretWhiskydestillering(Maltning maltning, LocalDate startdato, LocalDate slutdato, double maengdeVand,
                                                             double head, double heart, double tail, String kommentar, double alkoholprocent, Medarbejder medarbejder) {
        Whiskydestillering whiskydestillering = new Whiskydestillering(maltning, startdato, slutdato, maengdeVand, head, heart, tail, kommentar, alkoholprocent, medarbejder);
        Storage.addWhiskydestillering(whiskydestillering);
        return whiskydestillering;
    }
    public static Whisky opretWhisky(LocalDate dato, String navn, String beskrivelse, double flaskeStr,
                                     double vandTilfoejet, double alkoholprocent) {
        Whisky whisky = new Whisky(dato, navn, beskrivelse, flaskeStr, vandTilfoejet, alkoholprocent);
        Storage.addWhisky(whisky);
        return whisky;
    }
    public static Gindestillering opretGindestillering(LocalDate startdato, LocalDate slutdato,
                                                       double vandTilfoejet, double alkoholprocent, double liter, double maengdeEnebaer) {
        Gindestillering gindestillering = new Gindestillering(startdato, slutdato, vandTilfoejet, alkoholprocent, liter, maengdeEnebaer);
        Storage.addGindestillering(gindestillering);
        return gindestillering;
    }

    public static ArrayList<Medarbejder> getMedarbejder() {
        return Storage.getMedarbejdere();
    }
    public static ArrayList<Whisky> getWhisky() {
        return Storage.getWhiskyer();
    }
    public static ArrayList<Gindestillering> getGindestillering() {
        return Storage.getGindestilleringer();
    }
    public static ArrayList<Maltning> getMaltning() {
        return Storage.getMaltninger();
    }

    public static ArrayList<Fad> getFad() {
        return Storage.getFade();
    }

    public static ArrayList<Whiskydestillering> getWhiskydestillering() {
        return Storage.getWhiskydestilleringer();
    }


    public static void addMedarbejder(Medarbejder medarbejder) { Storage.addMedarbejder(medarbejder);
    }

}
