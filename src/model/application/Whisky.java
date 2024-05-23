package model.application;

import test.fake_classes.VaeskeInterface;

import java.time.LocalDate;
import java.util.ArrayList;

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
    private VaeskeTilWhisky vaeskeTilWhisky;

    public Whisky(LocalDate dato, String navn, String beskrivelse, double flaskeStr,
                  double vandTilfoejet, double alkoholprocent, Medarbejder medarbejder, String whiskyBetegnelse, VaeskeTilWhisky vaeskeTilWhisky) {
        Whisky.totalAntal++;
        this.nr = totalAntal;
        this.vaeskeTilWhisky = vaeskeTilWhisky;
        this.dato = dato;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.flaskeStr = flaskeStr;
        this.vandTilfoejet = vandTilfoejet;
        this.alkoholprocent = alkoholprocent;
        this.medarbejder = medarbejder;
        this.whiskyBetegnelse = whiskyBetegnelse;
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


    @Override
    public String toString() {
        ArrayList<Whiskydestillering> destilleringer = new ArrayList<>();
        for (VaeskeInterface vaeskeInterface : vaeskeTilWhisky.getDestillat().getVaeskerTilDestillat()) {
            destilleringer.add(vaeskeInterface.getWhiskydestillering());
        }
        String destilleringString = "Destilleringer:";
        for (Whiskydestillering whiskydestillering : destilleringer) {
            destilleringString+="\n\t"+whiskydestillering.toString();
        }
        ArrayList<Fad> fadliste = new ArrayList<>();
        return "WHISKY: " + navn + ", #" + nr + "\n" +
                "Dato: " + dato + "\n" +
                "Beskrivelse: " + beskrivelse + "\n" +
                "Flaskestørrelse: " + flaskeStr + "\n" +
                "Vand tilføjet: " + vandTilfoejet + "\n" +
                "Fad: " + vaeskeTilWhisky.getDestillat().getFad()+
                vaeskeTilWhisky+ "\n"+
                destilleringString+
                "Betegnelse: " + whiskyBetegnelse + "\n" +
                "Alkoholprocent: " + alkoholprocent + "\n" +
                medarbejder;
    }
}
