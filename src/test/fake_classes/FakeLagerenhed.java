package test.fake_classes;

import model.application.Lager;
import model.application.Lagerenhed;

public class FakeLagerenhed implements Lagerenhed {
    private Lager lager;

    public Lager getLager() {
        return lager;
    }

    @Override
    public void tilfoejLager(Lager lager, int reol, int hylde) {
        this.lager = lager;
    }
}
