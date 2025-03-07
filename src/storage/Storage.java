package storage;

import model.application.*;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Medarbejder> medarbejdere = new ArrayList<>();
    private static ArrayList<Fad> fade = new ArrayList<>();
    private static ArrayList<Whiskydestillering> whiskydestilleringer = new ArrayList<>();
    private static ArrayList<Maltning> maltninger = new ArrayList<>();
    private static ArrayList<Gindestillering> gindestilleringer = new ArrayList<>();
    private static ArrayList<Whisky> whiskyer = new ArrayList<>();
    private static ArrayList<Lager> lagre = new ArrayList<>();
    private static ArrayList<Destillat> destillater = new ArrayList<>();


    public static ArrayList<Whisky> getWhiskyer() {
        return new ArrayList<>(whiskyer);
    }
    public static ArrayList<Gindestillering> getGindestilleringer() {
        return new ArrayList<>(gindestilleringer);
    }
    public static ArrayList<Medarbejder> getMedarbejdere() {
        return new ArrayList<>(medarbejdere);
    }
    public static ArrayList<Fad> getFade() {
        return new ArrayList<>(fade);
    }
    public static ArrayList<Whiskydestillering> getWhiskydestilleringer() {
        return new ArrayList<>(whiskydestilleringer);
    }
    public static ArrayList<Maltning> getMaltninger() {
        return new ArrayList<>(maltninger);
    }

    public static ArrayList<Lager> getLagre() {
        return new ArrayList<>(lagre);
    }
    public static ArrayList<Destillat> getDestillater() {
        return new ArrayList<>(destillater);
    }


    public static void addGindestillering(Gindestillering gindestillering) {
        gindestilleringer.add(gindestillering);
    }
    public static void addWhisky(Whisky whisky) {
        whiskyer.add(whisky);
    }
    public static void addMedarbejder(Medarbejder medarbejder) {
        medarbejdere.add(medarbejder);
    }
    public static void addFad(Fad fad) {
        fade.add(fad);
    }
    public static void addWhiskydestillering(Whiskydestillering whiskydestillering) {
        whiskydestilleringer.add(whiskydestillering);
    }
    public static void addMaltning(Maltning maltning) {
        maltninger.add(maltning);
    }
    public static void addLager(Lager lager) {
        lagre.add(lager);
    }
    public static void addDestillat(Destillat destillat) {
        destillater.add(destillat);
    }
}
