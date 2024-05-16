package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FadSoegningPane extends SoegningPane {
    public FadSoegningPane() {
        super();
    }

    @Override
    void initContent(GridPane gridPane) {
        Label label = new Label("fadpane");
        add(label,0,0);
    }
}
