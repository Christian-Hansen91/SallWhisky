package model.application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gindestillering {
    private static int totalAntal;
    private int ginNr;
    private LocalDate startdato;
    private LocalDate slutdato;
    private double vandTilfoejet;
    private double alkoholprocent;
    private double liter;
    private ArrayList<Ingrediensmaengde> ingredienser = new ArrayList<>();

    public Gindestillering(LocalDate startdato, LocalDate slutdato,
                           double vandTilfoejet, double alkoholprocent, double liter) {
        Gindestillering.totalAntal++;
        this.ginNr = totalAntal;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.vandTilfoejet = vandTilfoejet;
        this.alkoholprocent = alkoholprocent;
        this.liter = liter;
    }

    public Ingrediensmaengde tilfoejIngrediensmaengde(Ingrediens ingrediens, double maengde) {
        Ingrediensmaengde nyIngrediensmaengde = new Ingrediensmaengde(maengde, this, ingrediens);
        ingredienser.add(nyIngrediensmaengde);
        return nyIngrediensmaengde;
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
}
