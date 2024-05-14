package model.application;

import java.util.ArrayList;
import java.util.List;

public class Lager {
    private String navn;
    private Lagerenhed[][] reolliste;
    private static List<Fad> fade = new ArrayList<>();

    public Lager(String navn, Lagerenhed[][] reolliste) {
        this.navn = navn;
        this.reolliste = reolliste;
    }

    @Override
    public String toString() {
        return navn;
    }

    public void addReol(int antalHylder) {
        Lagerenhed[][] newArray = new Lagerenhed[reolliste.length+1][];
        for (int i = 0; i < reolliste.length; i++) {
            newArray[i] = reolliste[i];
        }
        reolliste = newArray;
        reolliste[reolliste.length-1] = new Lagerenhed[antalHylder];
    }

    public static void tilfoejFad(Fad fad) {
        if (!fade.contains(fad)) {
            fade.add(fad);
        }
    }

    public void addLagerenhedAt(int row, int column, Lagerenhed lagerenhed) {
        reolliste[row][column] = lagerenhed;
    }

    public Lagerenhed getLagerenhedAt(int row, int column) {
        return reolliste[row][column];
    }
}