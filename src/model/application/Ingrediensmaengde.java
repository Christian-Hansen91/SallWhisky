package model.application;

public class Ingrediensmaengde {
    private double maengde;
    private Gindestillering gindestillering;
    private Ingrediens ingrediens;

    public Ingrediensmaengde(double maengde, Gindestillering gindestillering, Ingrediens ingrediens) {
        this.maengde = maengde;
        this.gindestillering = gindestillering;
        this.ingrediens = ingrediens;
    }
    public void tilfoejMaengde(double maengde) {
        this.maengde += maengde;
    }
    public Ingrediens hentIngrediens() {
        return ingrediens;
    }

    public double hentMaengde() {
        return maengde;
    }
}
