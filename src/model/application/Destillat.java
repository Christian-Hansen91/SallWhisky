package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private static int totalAntal;
    private int id;
    private LocalDate dato;
    private List<String> kommentar = new ArrayList<>();
    private List<VaeskeTilDestillat> vaeskeTilDestillater = new ArrayList<>();
    private List<VaeskeTilWhisky> vaeskeTilWhiskyer = new ArrayList<>();
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
        if (!this.vaeskeTilDestillater.contains(vaeskeTilDestillat)) {
            this.vaeskeTilDestillater.add(vaeskeTilDestillat);
        }
    }

    public void saetAngelShare() {
        this.angelShare = hentTotalMaengde();
    }

    public double hentTotalMaengde() {
        double liter = 0;
        for (VaeskeTilDestillat vaeskeTilDestillat : vaeskeTilDestillater) {
            liter += vaeskeTilDestillat.getMaengde();
        }
        for (VaeskeTilWhisky vaeskeTilWhisky : vaeskeTilWhiskyer) {
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
        vaeskeTilWhiskyer.add(vaeskeTilWhisky);
        return vaeskeTilWhisky;
    }

    public int getId() {
        return id;
    }

    public List<VaeskeTilDestillat> getVaeskeTilDestillater() {
        List<VaeskeTilDestillat> vaeskeTilDestillater = this.vaeskeTilDestillater;
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
}
