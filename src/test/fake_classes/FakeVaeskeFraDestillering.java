package test.fake_classes;

import model.application.Whiskydestillering;

public class FakeVaeskeFraDestillering implements VaeskeInterface{
    private double maengde;

    public FakeVaeskeFraDestillering(double maengde) {
        this.maengde = maengde;
    }

    @Override
    public double getMaengde() {
        return maengde;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    @Override
    public void fjernVaeske() {

    }

    @Override
    public Whiskydestillering getWhiskydestillering() {
        return null;
    }
}
