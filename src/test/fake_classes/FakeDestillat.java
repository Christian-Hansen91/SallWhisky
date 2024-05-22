package test.fake_classes;

import model.application.Lager;
import model.application.Lagerenhed;

public class FakeDestillat implements Lagerenhed {
    private double vaeskeFraDestillering;
    private double vaeskeTilWhisky;
    private double angelShare;

    public FakeDestillat(double vaeskeFraDestillering, double vaeskeTilWhisky, double angelShare) {
        this.vaeskeFraDestillering = vaeskeFraDestillering;
        this.vaeskeTilWhisky = vaeskeTilWhisky;
        this.angelShare = angelShare;
    }

    public double getAngelShare() {
        return angelShare;
    }

    public void setAngelShare(double angelShare) {
        this.angelShare = angelShare;
    }

    public double getVaeskeFraDestillering() {
        return vaeskeFraDestillering;
    }

    public void setVaeskeFraDestillering(double vaeskeFraDestillering) {
        this.vaeskeFraDestillering = vaeskeFraDestillering;
    }

    public void setVaeskeTilWhisky(double vaeskeTilWhisky) {
        this.vaeskeTilWhisky = vaeskeTilWhisky;
    }

    public double getVaeskeTilWhisky() {
        return vaeskeTilWhisky;
    }

    @Override
    public void tilfoejLager(Lager lager, int reol, int hylde) {

    }
}
