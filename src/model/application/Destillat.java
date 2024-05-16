package model.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private static int totalAntal;
    private int id;
    private LocalDate dato;
    private String kommentar;
    private List<VaeskeTilDestillat> vaeskeTilDestillater = new ArrayList<>();
    private List<VaeskeTilWhisky> vaeskeTilWhiskyer = new ArrayList<>();
    private Fad fad;
    private double angelShare = 0;

    public Destillat(Fad fad) {
        Destillat.totalAntal++;
        this.id = totalAntal;
        this.dato = LocalDate.now();
        this.kommentar = "";
        this.fad = fad;
        fad.saetDestillat(this);
    }

    public void saetKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public void tilfoejTapning(VaeskeTilDestillat vaeskeTilDestillat) {
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

    @Override
    public String toString() {
        return "Destillat{" +
                "id=" + id +
                ", dato=" + dato +
                ", kommentar='" + kommentar + '\'' +
                ", vaeskeTilDestillater=" + vaeskeTilDestillater +
                ", vaeskeTilWhiskyer=" + vaeskeTilWhiskyer +
                ", fad=" + fad +
                ", angelShare=" + angelShare +
                ", maengde=" + hentTotalMaengde() +
                '}';
    }

    public String getKommentar() {
        return kommentar;
    }

    public LocalDate getDato() {
        return dato;
    }
    public void setDato(LocalDate dato) {
        this.dato = dato;
    }
}
