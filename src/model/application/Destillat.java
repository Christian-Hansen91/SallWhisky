package model.application;

import test.fake_classes.VaeskeInterface;
import test.fake_classes.VaeskeWhiskyInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private static int totalAntal;
    private int id;
    private LocalDate dato;
    private List<String> kommentar = new ArrayList<>();
    private List<VaeskeInterface> vaeskeTilDestillater = new ArrayList<>();
    private List<VaeskeWhiskyInterface> vaeskeTilWhiskyer = new ArrayList<>();
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

    public void tilfoejVaeskeTilDestillat(VaeskeInterface vaeskeTilDestillat) {
        if (!this.vaeskeTilDestillater.contains(vaeskeTilDestillat)) {
            this.vaeskeTilDestillater.add(vaeskeTilDestillat);
        }
    }

    public void setAngelShare(double angelShare) {
        this.angelShare = angelShare;
    }

    public void saetAngelShare() {
        this.angelShare = hentTotalMaengde();
    }

    public double hentTotalMaengde() {
        double vaeskeDestillat = 0;
        double vaeskeWhisky = 0;
        for (VaeskeInterface vaeskeTilDestillat : vaeskeTilDestillater) {
            vaeskeDestillat += vaeskeTilDestillat.getMaengde();
        }
        for (VaeskeWhiskyInterface vaeskeTilWhisky : vaeskeTilWhiskyer) {
            vaeskeWhisky += vaeskeTilWhisky.getMaengde();
        }
        if (vaeskeDestillat < 0 || vaeskeWhisky < 0 || angelShare < 0
                || vaeskeDestillat - vaeskeWhisky < 0) {
            throw new IllegalArgumentException("Mængde i produkter overskrider mængde tappet til destillat");
        }
        return vaeskeDestillat - vaeskeWhisky - angelShare;
    }

    public Fad getFad() {
        return fad;
    }

    public VaeskeTilWhisky opretVaeskeTilWhisky(double maengde) {
        VaeskeTilWhisky vaeskeTilWhisky = new VaeskeTilWhisky(this, maengde);
        vaeskeTilWhiskyer.add(vaeskeTilWhisky);
        return vaeskeTilWhisky;
    }

    public int getId() {
        return id;
    }

    public List<VaeskeInterface> getVaeskeTilDestillater() {
        List<VaeskeInterface> vaeskeTilDestillater = this.vaeskeTilDestillater;
        return vaeskeTilDestillater;
    }

    @Override
    public String toString() {
        return "DESTILLAT #" + id + "\n" +
                "Dato: " + dato + "\n" +
                "Blanding: " + vaeskeTilDestillater + "\n" +
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
        vaeskeTilDestillater.get(index).fjernVaeske();
        vaeskeTilDestillater.remove(index);
    }
    public void fjernSindsteKommentar() {
        kommentar.remove(kommentar.size()-1);
    }
    public void tjekOmVaeskeErPåfyldt(){
        if(vaeskeTilDestillater.size()==0){
            throw new IllegalArgumentException("Der er endnu ikke tilføjet væsker til destillatet");
        }
    }
    public void tilfoejVaeskeTilWhisky(VaeskeWhiskyInterface whiskyMaengde) {
        this.vaeskeTilWhiskyer.add(whiskyMaengde);
    }
}
