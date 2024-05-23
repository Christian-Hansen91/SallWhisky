package model.application;

import java.util.ArrayList;
import java.util.List;

public class Flaskekasse implements Lagerenhed {
    private Whisky whisky;
    private Lager lager;
    private List<Integer> flaskenumre = new ArrayList<>();

    public Flaskekasse(Whisky whisky) {
        this.whisky = whisky;
    }
    public void opretFlasker(int antal) {
        for (int i = 1; i < antal; i++) {
            flaskenumre.add(i);
        }
    }
    public void tilfoejLager(Lager lager, int reol, int hylde) {
        if (!lager.equals(this.lager)) {
            this.lager = lager;
            lager.addLagerenhedAt(reol, hylde, this);
        }
    }
}
