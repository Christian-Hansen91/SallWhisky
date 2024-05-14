package model.application;

public class Lager {
    private String navn;
    private Lagerenhed[][] reolliste;

    public Lager(String navn, Lagerenhed[][] reolliste) {
        this.navn = navn;
        this.reolliste = reolliste;
    }

    @Override
    public String toString() {
        return "Lagernavn: " + navn;
    }

    public void addReol(int antalHylder) {
        Lagerenhed[][] newArray = new Lagerenhed[reolliste.length+1][];
        for (int i = 0; i < reolliste.length; i++) {
            newArray[i] = reolliste[i];
        }
        reolliste = newArray;
        reolliste[reolliste.length-1] = new Lagerenhed[antalHylder];
    }

    public void addLagerenhedAt(int row, int column, Lagerenhed lagerenhed) {
        reolliste[row][column] = lagerenhed;
    }

    public Lagerenhed getLagerenhedAt(int row, int column) {
        return reolliste[row][column];
    }
}
