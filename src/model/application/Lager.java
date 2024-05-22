package model.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lager {
    private static List<Fad> fade = new ArrayList<>();
    private String navn;
    private Lagerenhed[][] reolliste;
    private Medarbejder medarbejder;

    public Lager(String navn, Lagerenhed[][] reolliste, Medarbejder medarbejder) {
        this.navn = navn;
        this.reolliste = reolliste;
        this.medarbejder = medarbejder;
    }

    public void addReol(int antalHylder) {
        if (antalHylder < 1) {
            throw new IllegalArgumentException("Tilføj mindst én hylde");
        }
        Lagerenhed[][] newArray = new Lagerenhed[reolliste.length + 1][];
        for (int i = 0; i < reolliste.length; i++) {
            newArray[i] = reolliste[i];
        }
        reolliste = newArray;
        reolliste[reolliste.length - 1] = new Lagerenhed[antalHylder];
    }

    public void addLagerenhedAt(int reol, int hylde, Lagerenhed lagerenhed) {
        if (reol < 0 || hylde < 0 || reol > reolliste.length || hylde > reolliste[reol - 1].length || !lagerpladsLedig(reol, hylde)) {
            throw new IllegalArgumentException("Angivne hylde er optaget eller findes ikke");
        }
        reolliste[reol][hylde] = lagerenhed;
        lagerenhed.tilfoejLager(this, reol, hylde);
    }


    public String getNavn() {
        return navn;
    }

    public Lagerenhed[][] getReolliste() {
        Lagerenhed[][] reolliste1 = reolliste;
        return reolliste1;
    }

    public boolean lagerpladsLedig(int reol, int hylde) {
        if (reol < 0 || hylde < 0 || reol >= reolliste.length || hylde >= reolliste[reol].length) {
            throw new IllegalArgumentException("Angivne hylde er optaget eller findes ikke");
        }
        return reolliste[reol][hylde] == null;
    }

    @Override
    public String toString() {
        return "Lager: " + navn;
    }
}