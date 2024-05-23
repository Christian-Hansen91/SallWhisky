package model.application;

import test.fake_classes.VaeskeInterface;
import test.fake_classes.VaeskeWhiskyInterface;

import java.util.ArrayList;

public class VaeskeTilWhisky implements VaeskeWhiskyInterface {
    private Destillat destillat;
    private double maengde;

    public VaeskeTilWhisky(Destillat destillat, double maengde) {
        tjekMaengde(destillat, maengde);
        this.destillat = destillat;
        this.maengde = maengde;
    }

    private void tjekMaengde(Destillat destillat, double maengde) {
        if (destillat.hentTotalMaengde() < maengde)
            throw new IllegalArgumentException("Destillatet indeholder " + destillat.hentTotalMaengde() + " liter, og du prøver at tappe " + maengde + " liter");
    }

    public double getMaengde() {
        return maengde;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Destillat:\n";
        s += "\tID: " + destillat.getId() +"\n";
        s += "\tMængde: " + maengde;
        for (String string : destillat.getKommentar()) {
            s+= "\n\t"+string;
        }
        return s;
    }

    public Destillat getDestillat() {
        return destillat;
    }
}

