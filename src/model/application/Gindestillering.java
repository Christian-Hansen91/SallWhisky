package model.application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gindestillering implements Lagerenhed {
    private static int totalAntal;
    private int ginNr;
    private LocalDate startdato;
    private LocalDate slutdato;
    private double vandTilfoejet;
    private double alkoholprocent;
    private double liter;
    private ArrayList<Ingrediensmaengde> ingredienser = new ArrayList<>();
    private Medarbejder medarbejder;
    private Lager lager;

    public Gindestillering(LocalDate startdato, LocalDate slutdato,
                           double vandTilfoejet, double alkoholprocent, double liter, double maengdeEnebaer, Medarbejder medarbejder) {
        Gindestillering.totalAntal++;
        this.ginNr = totalAntal;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.vandTilfoejet = vandTilfoejet;
        this.alkoholprocent = alkoholprocent;
        this.liter = liter;
        tilfoejIngrediensmaengde(Ingrediens.ENEBAER, maengdeEnebaer);
        this.medarbejder = medarbejder;
    }

    public void tilfoejIngrediensmaengde(Ingrediens ingrediens, double maengde) {
        if (ginIndeholderIngrediens(ingrediens)) {
            tilfoejMaengdeTilIngrediens(ingrediens, maengde);
        } else {
            Ingrediensmaengde nyIngrediensmaengde = new Ingrediensmaengde(maengde, this, ingrediens);
            ingredienser.add(nyIngrediensmaengde);
        }
    }

    public ArrayList<Ingrediensmaengde> hentIngredienser() {
        return new ArrayList<>(ingredienser);
    }

    public boolean ginIndeholderIngrediens(Ingrediens ingrediens) {
        boolean indeholder = false;
        for (Ingrediensmaengde ingrediensmaengde : ingredienser) {
            if (ingrediensmaengde.hentIngrediens().equals(ingrediens)) {
                indeholder = true;
            }
        }

        return indeholder;
    }

    public double maengdeAfIngrediens(Ingrediens ingrediens) {
        double maengde = 0;
        for (Ingrediensmaengde ingrediensmaengde : ingredienser) {
            if (ingrediensmaengde.hentIngrediens().equals(ingrediens)) {
                maengde = ingrediensmaengde.hentMaengde();
            }
        }

        return maengde;
    }

    public void tilfoejMaengdeTilIngrediens(Ingrediens ingrediens, double maengde) {
        for (Ingrediensmaengde ingrediensmaengde : ingredienser) {
            if (ingrediensmaengde.hentIngrediens().equals(ingrediens)) {
                ingrediensmaengde.tilfoejMaengde(maengde);
            }
        }
    }

    public void tilfoejLager(Lager lager, int reol, int hylde) {
        if (!lager.equals(this.lager)) {
            this.lager = lager;
            lager.addLagerenhedAt(reol, hylde, this);
        }
    }

    public int getGinNr() {
        return ginNr;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public LocalDate getSlutdato() {
        return slutdato;
    }

    public double getVandTilfoejet() {
        return vandTilfoejet;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }

    public double getLiter() {
        return liter;
    }

    @Override
    public String toString() {
        return "Gindestillering, Nr:" + ginNr + "\n" +
                "Startdato: " + startdato + "\n" +
                "Slutdato: " + slutdato + "\n" +
                "Vand Ttlføjet: " + vandTilfoejet + "\n" +
                "Alkoholprocent: " + alkoholprocent + "\n" +
                "Liter: " + liter + "\n" +
                "Ingredienser: " + ingredienser + "\n" +
                "Lager: " + lager + "\n" +
                "Medarbejder: " + medarbejder;
    }
}
