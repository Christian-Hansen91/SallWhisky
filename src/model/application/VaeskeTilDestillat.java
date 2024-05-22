package model.application;

import test.fake_classes.VaeskeInterface;

import java.time.LocalDate;

public class VaeskeTilDestillat implements VaeskeInterface {
    private Whiskydestillering whiskydestillering;
    private LocalDate dato;
    private double maengde;
    private String kommentar;

    VaeskeTilDestillat(double maengde, Whiskydestillering whiskydestillering) {
        this.maengde = maengde;
        this.whiskydestillering = whiskydestillering;
        whiskydestillering.tilfoejVaeskemaengde(this);
        this.dato = LocalDate.now();
    }

    public double getMaengde() {
        return maengde;
    }

    public Whiskydestillering getWhiskydestillering() {
        return whiskydestillering;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Whiskydestillering #"+whiskydestillering.getNewMakeNr() + ":\n" +
                "\tDato: " + dato + "\n" +
                "\tMængde: " + maengde + "\n");
        return sb.toString();
    }

    public void fjernVaeske() {
        if (whiskydestillering != null) {
            whiskydestillering.fjernVaeske(this);
            whiskydestillering = null;
        }
    }
}
