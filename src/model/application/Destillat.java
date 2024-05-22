package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private static int totalAntal;
    private int id;
    private LocalDate dato;
    private List<String> kommentar = new ArrayList<>();
    private List<VaeskeTilDestillat> vaeskerTilDestillat = new ArrayList<>();
    private List<VaeskeTilWhisky> vaeskerTilWhisky = new ArrayList<>();
    private Fad fad;
    private double angelShare = 0;
    private Medarbejder medarbejder;

    public Destillat(Fad fad, Medarbejder medarbejder) {
        Destillat.totalAntal++;
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.fad = fad;
        fad.saetDestillat(this);
        this.medarbejder = medarbejder;

    }

    private void kontrollerMaengdeIFad(Fad fad) {
    }

    public void tilfoejKommentar(String kommentar) {
        this.kommentar.add(kommentar);
    }

    public void tilfoejVaeskeTilDestillat(VaeskeTilDestillat vaeskeTilDestillat) {
        if (!this.vaeskerTilDestillat.contains(vaeskeTilDestillat)) {
            this.vaeskerTilDestillat.add(vaeskeTilDestillat);
        }
    }

    public void saetAngelShare() {
        this.angelShare = hentTotalMaengde();
    }

    public double hentTotalMaengde() {
        double liter = 0;
        for (VaeskeTilDestillat vaeskeTilDestillat : vaeskerTilDestillat) {
            liter += vaeskeTilDestillat.getMaengde();
        }
        for (VaeskeTilWhisky vaeskeTilWhisky : vaeskerTilWhisky) {
            liter -= vaeskeTilWhisky.getMaengde();
        }
        liter-=angelShare;
        return liter;
    }

    public Fad getFad() {
        return fad;
    }

    public VaeskeTilWhisky opretVaeskeTilWhisky(double maengde) {
        VaeskeTilWhisky vaeskeTilWhisky = new VaeskeTilWhisky(this, maengde);
        vaeskerTilWhisky.add(vaeskeTilWhisky);
        return vaeskeTilWhisky;
    }

    public int getId() {
        return id;
    }

    public List<VaeskeTilDestillat> getVaeskerTilDestillat() {
        List<VaeskeTilDestillat> vaeskeTilDestillater = this.vaeskerTilDestillat;
        return vaeskeTilDestillater;
    }

    @Override
    public String toString() {
        return "DESTILLAT #" + id + "\n" +
                "Dato: " + dato + "\n" +
                "Blanding: " + vaeskerTilDestillat + "\n" +
                "Mængde: " + hentTotalMaengde() + "\n" +
                "Angel share: " + angelShare + "\n" +
                "Kommentar: " + kommentar + "\n" +
                fad + "\n" +
                medarbejder;
    }

    public List<String> getKommentar() {
        return new ArrayList<>(kommentar);
    }

    public LocalDate getDato() {
        return dato;
    }
    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public void fjernVaeske(int index) {
        vaeskerTilDestillat.get(index).fjernVaeske();
        vaeskerTilDestillat.remove(index);
    }
    public void fjernSindsteKommentar() {
        kommentar.remove(kommentar.size()-1);
    }
    public void tjekOmVaeskeErPaafyldt(){
        if(vaeskerTilDestillat.size()==0){
            throw new IllegalArgumentException("Der er endnu ikke tilføjet væsker til destillatet");
        }
    }
}
