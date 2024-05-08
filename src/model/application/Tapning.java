package model.application;

import java.time.LocalDate;

public class Tapning {
    private Destillering destillering;
    private Destillat destillat;
    private LocalDate dato;
    private double maengde;

    Tapning(double maengde, Destillering destillering, Destillat destillat) {
        this.maengde = maengde;
        this.destillat = destillat;
        destillat.tilfoejTapning(this);
        this.destillering = destillering;
        this.dato = LocalDate.now();
    }
    public double getMaengde() {
        return maengde;
    }
}
