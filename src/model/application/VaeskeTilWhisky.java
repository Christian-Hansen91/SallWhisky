package model.application;

public class VaeskeTilWhisky {
    private Destillat destillat;
    private double maengde;

    public VaeskeTilWhisky(Destillat destillat, double maengde) {
        this.destillat = destillat;
        this.maengde = maengde;
    }

    public double getMaengde() {
        return maengde;
    }

    @Override
    public String toString() {
        return "VaeskeTilWhisky{" +
                "destillat=" + destillat.getId() +
                ", maengde=" + maengde +
                '}';
    }
}

