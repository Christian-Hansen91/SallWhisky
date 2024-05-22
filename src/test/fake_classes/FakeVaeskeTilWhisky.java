package test.fake_classes;

public class FakeVaeskeTilWhisky implements VaeskeWhiskyInterface{
    private double maengde;

    public FakeVaeskeTilWhisky(double maengde) {
        this.maengde = maengde;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    @Override
    public double getMaengde() {
        return maengde;
    }
}
