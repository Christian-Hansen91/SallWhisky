package model.application;

import java.time.LocalDate;

public class Gindestillering {
    private static int ginNr;
    private LocalDate startdato;
    private LocalDate slutdato;
    private double vandTilfoejet;
    private double alkoholprocent;
    private double liter;

    public Gindestillering(LocalDate startdato, LocalDate slutdato,
                           double vandTilfoejet, double alkoholprocent, double liter) {
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.vandTilfoejet = vandTilfoejet;
        this.alkoholprocent = alkoholprocent;
        this.liter = liter;
    }

    public static int getGinNr() {
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
