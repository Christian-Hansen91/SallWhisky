package model.application;

public class VaeskeTilWhisky {
    private Destillat destillat;
    private double maengde;

    public VaeskeTilWhisky(Destillat destillat, double maengde) {
        tjekMaengde(destillat, maengde);
        this.destillat = destillat;
        this.maengde = maengde;
    }

    private void tjekMaengde(Destillat destillat, double maengde) {
        if(destillat.hentTotalMaengde()<maengde)
            throw new IllegalArgumentException("Destillatet indeholder " + destillat.hentTotalMaengde() + " liter, og du prøver at tappe " + maengde + " liter");
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

    public Destillat getDestillat() {
        return destillat;
    }
}

