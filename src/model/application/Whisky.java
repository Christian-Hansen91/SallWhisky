package model.application;

import java.time.LocalDate;

public class Whisky implements Lagerenhed{
    private LocalDate dato;
    private static int nr;
    private String navn;
    private String beskrivelse;
    private double flaskeStr;
    private double vandTilfoejet;
    private String whiskyBetegnelse;
    private double alkoholprocent;
    private Lager lager;

    public Whisky(LocalDate dato, String navn, String beskrivelse, double flaskeStr,
                  double vandTilfoejet, double alkoholprocent, Lager lager[][]) {
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

    public static int getNr() {
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
