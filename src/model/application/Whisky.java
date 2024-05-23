package model.application;

import java.time.LocalDate;

public class Whisky {
    private LocalDate dato;
    private static int totalAntal;
    private int nr;
    private String navn;
    private String beskrivelse;
    private double flaskeStr;
    private double vandTilfoejet;
    private String whiskyBetegnelse;
    private double alkoholprocent;
    private Lager lager;
    private Medarbejder medarbejder;

    public Whisky(LocalDate dato, String navn, String beskrivelse, double flaskeStr,
                  double vandTilfoejet, double alkoholprocent, Medarbejder medarbejder, String whiskyBetegnelse) {
        Whisky.totalAntal++;
        this.nr = totalAntal;
        this.dato = dato;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.flaskeStr = flaskeStr;
        this.vandTilfoejet = vandTilfoejet;
        this.alkoholprocent = alkoholprocent;
        this.medarbejder = medarbejder;
        this.whiskyBetegnelse = whiskyBetegnelse;
    }

    public LocalDate getDato() {
        return dato;
    }

    public int getNr() {
        return nr;
    }

    public static int getTotalAntal() {
        return totalAntal;
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
    public int lagringsdage() {
        int dage = 0;
        LocalDate foersteDestillering;
        return dage;
    }

    @Override
    public String toString() {
        return "WHISKY: " + navn + ", #" + nr + "\n" +
                "Dato: " + dato + "\n" +
                "Beskrivelse: " + beskrivelse + "\n" +
                "Flaskestørrelse: " + flaskeStr + "\n" +
                "Vand tilføjet: " + vandTilfoejet + "\n" +
                "Betegnelse: " + whiskyBetegnelse + "\n" +
                "Alkoholprocent: " + alkoholprocent + "\n" +
                medarbejder;
    }
}
