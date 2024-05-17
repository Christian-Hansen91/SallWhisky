package model.application;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private static List<Fad> fade = new ArrayList<>();
    private String navn;
    private Lagerenhed[][] reolliste;

    public Lager(String navn, Lagerenhed[][] reolliste) {
        this.navn = navn;
        this.reolliste = reolliste;
    }

    public static void tilfoejFad(Fad fad) {
        if (!fade.contains(fad)) {
            fade.add(fad);
        }
    }

    @Override
    public String toString() {
        return navn;
    }

    public void addReol(int antalHylder) {
        Lagerenhed[][] newArray = new Lagerenhed[reolliste.length + 1][];
        for (int i = 0; i < reolliste.length; i++) {
            newArray[i] = reolliste[i];
        }
        reolliste = newArray;
        reolliste[reolliste.length - 1] = new Lagerenhed[antalHylder];
    }

    public void addLagerenhedAt(int reol, int hylde, Lagerenhed lagerenhed) {
        reolliste[reol][hylde] = lagerenhed;
        lagerenhed.tilfoejLager(this, reol, hylde);
    }

    public Lagerenhed getLagerenhedAt(int row, int column) {
        return reolliste[row][column];
    }

    public String getNavn() {
        return navn;
    }

    public Lagerenhed[][] getReolliste() {
        Lagerenhed[][] reolliste1 = reolliste;
        return reolliste1;
    }

    public boolean lagerpladsLedig(int reol, int hylde) {
        return reolliste[reol][hylde] == null;
    }
}