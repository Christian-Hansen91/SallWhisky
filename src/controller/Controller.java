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

    public static Fad opretFad(LocalDate indkoebsdato, String fadtype, int kapacitet, String ophavsland, String leverandoer, String historik) {
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
                                     double vandTilfoejet, double alkoholprocent, Lager lager[][]) {
        Whisky whisky = new Whisky(dato, navn, beskrivelse, flaskeStr, vandTilfoejet, alkoholprocent, lager);
        Storage.addWhisky(whisky);
        return whisky;
    }
    public static Gindestillering opretGindestillering(LocalDate startdato, LocalDate slutdato,
                                                       double vandTilfoejet, double alkoholprocent, double liter, double maengdeEnebaer) {
        Gindestillering gindestillering = new Gindestillering(startdato, slutdato, vandTilfoejet, alkoholprocent, liter, maengdeEnebaer);
        Storage.addGindestillering(gindestillering);
        return gindestillering;
    }
    public static Lager opretLager(String navn, Lagerenhed[][] reolliste){
        Lager lager = new Lager(navn, reolliste);
        Storage.addLager(lager);
        return lager;
    }
    public static Destillat opretDestillat(Fad fad){
        Destillat destillat = new Destillat(fad);
        return destillat;
    }


    public static ArrayList<Medarbejder> getMedarbejdere() {
        return Storage.getMedarbejdere();
    }

    public static ArrayList<Whisky> getWhiskyer() {
        return Storage.getWhiskyer();
    }
    public static ArrayList<Gindestillering> getGindestilleringer() {
        return Storage.getGindestilleringer();
    }
    public static ArrayList<Maltning> getMaltninger() {
        return Storage.getMaltninger();
    }

    public static ArrayList<Fad> getFade() {
        return Storage.getFade();
    }


    public static ArrayList<Whiskydestillering> getWhiskydestilleringer() {
        return Storage.getWhiskydestilleringer();
    }

    public static ArrayList<Lager> getLagre() {
        return Storage.getLagre();
    }
    public static ArrayList<Destillat> getDestillater() {
        ArrayList<Destillat> destillater = new ArrayList<>();
        for (int i = 0; i < getFade().size(); i++) {
            destillater.add(getFade().get(i).getDestillat());
        }
        return destillater;
    }
    public static void addMedarbejder(Medarbejder medarbejder) { Storage.addMedarbejder(medarbejder);
    }

    public static void addMaltning(Maltning maltning) { Storage.addMaltning(maltning);
    }

    public static void addWhiskydestillering(Whiskydestillering whiskydestillering) { Storage.addWhiskydestillering(whiskydestillering);
    }

    public static void addFad(Fad fad) { Storage.addFad(fad);
    }

    public static void addLager(Lager lager) { Storage.addLager(lager);
    }

    public static ArrayList<Whisky> soegWhiskyBeskrivelse(String beskrivelse) {
        ArrayList<Whisky> alleWhiskyer = Storage.getWhiskyer();
        ArrayList<Whisky> whiskyer = new ArrayList<>();

        if (!beskrivelse.isEmpty()) {
            for (Whisky whisky : alleWhiskyer) {
                if (whisky.getBeskrivelse().toLowerCase().contains(beskrivelse.toLowerCase())) {
                    whiskyer.add(whisky);
                }
            }
        }

        return whiskyer;
    }

    public static ArrayList<Whisky> soegWhiskyId(int nr) {
        ArrayList<Whisky> alleWhiskyer = Storage.getWhiskyer();
        ArrayList<Whisky> whiskyer = new ArrayList<>();

        if (nr != 0) {
            for (Whisky whisky : alleWhiskyer) {
                if (whisky.getNr() == nr) {
                    whiskyer.add(whisky);
                }
            }
        }

        return whiskyer;
    }

    public static ArrayList<Destillat> soegDestillatKommentar(String kommentar) {
        ArrayList<Destillat> destillater = new ArrayList<>();
        for (Destillat destillat : Storage.getDestillater()) {
            if(destillat.hentKommentar().contains(kommentar)) {
                destillater.add(destillat);
            }
        }

        return destillater;
    }
    public static ArrayList<Destillat> soegDestillatId(int id) {
        ArrayList<Destillat> destillater = new ArrayList<>();
        for (Destillat destillat : Storage.getDestillater()) {
            if (destillat.hentId() == id) {
                destillater.add(destillat);
            }
        }

        return destillater;
    }

    public ArrayList<Whisky> fjernWhiskyerUnderTre(ArrayList<Whisky> alleWhiskyer) {
        ArrayList<Whisky> whiskyer = new ArrayList<>();
        for (Whisky whisky : alleWhiskyer) {

        }

        return whiskyer;
    }
}
