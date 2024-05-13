package model.application;

import java.util.ArrayList;

public class Lager {
    private String navn;
    private ArrayList<ArrayList<Lagerenhed>> reolliste = new ArrayList<>();

    public Lager(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return "Lagernavn: " + navn;
    }
}
