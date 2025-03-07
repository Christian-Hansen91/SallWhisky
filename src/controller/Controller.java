package controller;

import model.application.*;
import storage.Storage;
import test.fake_classes.VaeskeInterface;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static Medarbejder opretMedarbejder(String navn, int tlfNr) {
        Medarbejder medarbejder = new Medarbejder(navn, tlfNr);
        Storage.addMedarbejder(medarbejder);
        return medarbejder;
    }

    public static Maltning opretMaltning(double maengde, String korntype, String marknavn, Medarbejder medarbejder) {
        Maltning maltning = new Maltning(maengde, korntype, marknavn, medarbejder);
        Storage.addMaltning(maltning);
        return maltning;
    }

    public static Maltning opretMaltning(double maengde, String korntype, String marknavn, String rygemateriale, String kommentar, Medarbejder medarbejder) {
        Maltning maltning = new Maltning(maengde, korntype, marknavn, rygemateriale, kommentar, medarbejder);
        Storage.addMaltning(maltning);
        return maltning;
    }

    public static Fad opretFad(LocalDate indkoebsdato, String fadtype, int kapacitet, String ophavsland, String leverandoer, String historik, Medarbejder medarbejder) {
        Fad fad = new Fad(indkoebsdato, fadtype, kapacitet, ophavsland, leverandoer, historik, medarbejder);
        Storage.addFad(fad);
        return fad;
    }

    public static Whiskydestillering opretWhiskydestillering(Maltning maltning, LocalDate startdato, LocalDate slutdato,
                                                             double head, double heart, double tail, String kommentar, double alkoholprocent, Medarbejder medarbejder) {
        Whiskydestillering whiskydestillering = new Whiskydestillering(maltning, startdato, slutdato, head, heart, tail, kommentar, alkoholprocent, medarbejder);
        Storage.addWhiskydestillering(whiskydestillering);
        return whiskydestillering;
    }

    public static Whisky opretWhisky(LocalDate dato, String navn, String beskrivelse, double flaskeStr,
                                     double vandTilfoejet, double alkoholprocent, Medarbejder medarbejder, String whiskyBetegnelse, VaeskeTilWhisky vaeskeTilWhisky) {
        Whisky whisky = new Whisky(dato, navn, beskrivelse, flaskeStr, vandTilfoejet, alkoholprocent, medarbejder, whiskyBetegnelse, vaeskeTilWhisky);
        Storage.addWhisky(whisky);
        return whisky;
    }

    public static Gindestillering opretGindestillering(LocalDate startdato, LocalDate slutdato,
                                                       double vandTilfoejet, double alkoholprocent, double liter, double maengdeEnebaer, Medarbejder medarbejder) {
        Gindestillering gindestillering = new Gindestillering(startdato, slutdato, vandTilfoejet, alkoholprocent, liter, maengdeEnebaer, medarbejder);
        return gindestillering;
    }
    public static void tilfoejGindestillgeringTilStorage(Gindestillering gindestillering) {
        Storage.addGindestillering(gindestillering);
    }

    public static Lager opretLager(String navn, Lagerenhed[][] reolliste, Medarbejder medarbejder) {
        Lager lager = new Lager(navn, reolliste, medarbejder);
        Storage.addLager(lager);
        return lager;
    }

    public static Destillat opretDestillat(Fad fad, Medarbejder medarbejder) {
        Destillat destillat = new Destillat(fad, medarbejder);
        return destillat;
    }

    public static void tilfoejDestillatTilSTorage(Destillat destillat) {
        Storage.addDestillat(destillat);
    }

    public static void tilfoejIngrediensmaengde(Ingrediens ingrediens, double maengde, Gindestillering gindestillering) {
        gindestillering.tilfoejIngrediensmaengde(ingrediens, maengde);
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
        return Storage.getDestillater();
    }

    public static ArrayList<Destillat> getModneDestillater() {
        ArrayList<Destillat> destillater = new ArrayList<>();
        for (Destillat destillat : Storage.getDestillater()) {
            if (destillat.getDato().isBefore(LocalDate.now().minusYears(3))) {
                destillater.add(destillat);
            }
        }
        return destillater;
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
        ArrayList<Whisky> whiskyer = new ArrayList<>();
        if (nr != 0) {
            for (Whisky whisky : Storage.getWhiskyer()) {
                if (whisky.getNr() == nr) {
                    whiskyer.add(whisky);
                }
            }
        }
        return whiskyer;
    }

    public static List<Destillat> soegDestillatKommentar(String kommentar) {
        ArrayList<Destillat> destillater = new ArrayList<>();
        if (!kommentar.isEmpty()) {
            for (Destillat destillat : Storage.getDestillater()) {
                if (destillat.getKommentar().contains(kommentar)) {
                    destillater.add(destillat);
                }
            }
        }
        return destillater;
    }

    public static List<Destillat> soegDestillatId(int id) {
        ArrayList<Destillat> destillater = new ArrayList<>();
        if (id != 0) {
            for (Destillat destillat : Storage.getDestillater()) {
                if (destillat.getId() == id) {
                    destillater.add(destillat);
                }
            }
        }
        return destillater;
    }

    public static List<Destillat> fjernUnderTre(List<Destillat> alleDestillat) {
        ArrayList<Destillat> modneDestillater = new ArrayList<>();
        for (Destillat destillat : alleDestillat) {
            if (ChronoUnit.YEARS.between(destillat.getDato(), LocalDate.now()) >= 3) {
                modneDestillater.add(destillat);
            }
        }

        return modneDestillater;
    }

    public static boolean lagerpladsLedig(Lager lager, int reol, int hylde) {
        return lager.lagerpladsLedig(reol, hylde);
    }

    public static ArrayList<Fad> soegFadId(int id) {
        ArrayList<Fad> alleFade = Storage.getFade();
        ArrayList<Fad> fade = new ArrayList<>();

        if (id != 0) {
            for (Fad fad : alleFade) {
                if (fad.getId() == id) {
                    fade.add(fad);
                }
            }
        }
        return fade;
    }

    public static ArrayList<Fad> soegFadtype(String fadtype) {
        ArrayList<Fad> fade = new ArrayList<>();
        if (!fadtype.isEmpty()) {
            for (Fad fad : Storage.getFade()) {
                if (fad.getFadtype().contains(fadtype)) {
                    fade.add(fad);
                }
            }
        }
        return fade;
    }

    public static List<Gindestillering> soegGinid(int id) {
        ArrayList<Gindestillering> gindestilleringer = new ArrayList<>();
        for (Gindestillering gindestillering : Storage.getGindestilleringer()) {
            if (gindestillering.getGinNr() == id) {
                gindestilleringer.add(gindestillering);
            }
        }

        return gindestilleringer;
    }

    public static List<Gindestillering> soegGiningrediens(String ingrediens) {
        ArrayList<Gindestillering> gindestilleringer = new ArrayList<>();
        if (!ingrediens.isEmpty()) {
            for (Gindestillering gindestillering : Storage.getGindestilleringer()) {
                for (Ingrediensmaengde ingrediensmaengde : gindestillering.hentIngredienser()) {
                    if (ingrediensmaengde.hentIngrediens().toString().equals(ingrediens.toUpperCase())) {
                        gindestilleringer.add(gindestillering);
                    }
                }
            }
        }
        return gindestilleringer;
    }

    public static String skabVaeskeoversigt(Destillat destillat, Medarbejder medarbejder) {
        StringBuilder sb = new StringBuilder();

        sb.append("Destillat lavet af " + medarbejder.getNavn() + ":\n");
        for (VaeskeInterface vaeskeTilDestillat : destillat.getVaeskerTilDestillat()) {
            sb.append("\t" + vaeskeTilDestillat + "\n");
        }
        sb.append("---------\n");
        if (!destillat.getKommentar().isEmpty()) {
            sb.append("Kommentarer:\n");
            for (String s : destillat.getKommentar()) {
                sb.append("\t" + s + "\n");
            }
        }

        return sb.toString();
    }

    public static boolean tjekKapacitetFad(Fad fad, double maengde) {
        return fad.getKapacitet() > maengde;
    }
    public static ArrayList<Fad> getTommeFade() {
        ArrayList<Fad> tommeFade = new ArrayList<>();
        for (int i = 0; i < Storage.getFade().size(); i++) {
            if (Storage.getFade().get(i).getDestillat() == null) {
                tommeFade.add(Storage.getFade().get(i));
            }
        }
        return tommeFade;
    }

    public static ArrayList<Whiskydestillering> getWhiskydestilleringerMedTilgængeligVæske() {
        ArrayList<Whiskydestillering> WhiskydestilleringerMedTilgængeligVæske = new ArrayList<>();
        for (int i = 0; i < Storage.getWhiskydestilleringer().size(); i++) {
            if (!(Storage.getWhiskydestilleringer().get(i).hentTilgaengeligVaeske() == 0)) {
                WhiskydestilleringerMedTilgængeligVæske.add(Storage.getWhiskydestilleringer().get(i));
            }
        }
        return WhiskydestilleringerMedTilgængeligVæske;
    }

    public static void tilfoejLagerenhedTilLager(int reol, int hylde, Lagerenhed lagerenhed, Lager lager) {
        lagerenhed.tilfoejLager(lager, reol, hylde);
    }

    public static Flaskekasse opretFlasker(int antal, Whisky whisky) {
        Flaskekasse flaskekasse = new Flaskekasse(whisky);
        flaskekasse.opretFlasker(antal);
        return flaskekasse;
    }
}

