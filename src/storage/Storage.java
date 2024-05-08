package storage;

import model.application.Destillering;
import model.application.Fad;
import model.application.Maltning;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Fad> fade = new ArrayList<>();
    private static ArrayList<Destillering> destilleringer = new ArrayList<>();
    private static ArrayList<Maltning> maltninger = new ArrayList<>();


    public static ArrayList<Fad> getFade() {
        return new ArrayList<>(fade);
    }
    public static ArrayList<Destillering> getDestilleringer() {
        return new ArrayList<>(destilleringer);
    }
    public static ArrayList<Maltning> getMaltninger() {
        return new ArrayList<>(maltninger);
    }


    public static void addFad(Fad fad) {
        fade.add(fad);
    }
    public static void addDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }
    public static void addMaltning(Maltning maltning) {
        maltninger.add(maltning);
    }
}
