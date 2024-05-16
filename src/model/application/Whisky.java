package model.application;

import java.time.LocalDate;

public class Whisky implements Lagerenhed{
    private LocalDate dato;
    private int nr;
    private static int totalAntal;
    private String navn;
    private String beskrivelse;
    private double flaskeStr;
    private double vandTilfoejet;
    private String whiskyBetegnelse;
    private double alkoholprocent;
    private Lager lager;

    public Whisky(LocalDate dato, String navn, String beskrivelse, double flaskeStr,
                  double vandTilfoejet, double alkoholprocent) {
        this.totalAntal++;
        this.nr=totalAntal;
        this.dato = dato;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.flaskeStr = flaskeStr;
        this.vandTilfoejet = vandTilfoejet;
        this.alkoholprocent = alkoholprocent;
    }

    public LocalDate getDato() {
        return dato;
    }

    public int getNr() {
        return nr;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getFlaskeStr() {
        return flaskeStr;
    }

    public double getVandTilfoejet() {
        return vandTilfoejet;
    }

    public String getWhiskyBetegnelse() {
        return whiskyBetegnelse;
    }

    public double getAlkoholprocent() {
        return alkoholprocent;
    }
}
