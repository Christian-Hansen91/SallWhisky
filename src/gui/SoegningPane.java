package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class SoegningPane extends GridPane {
    public SoegningPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        GridPane gridPane = new GridPane();
        initContent(gridPane);
    }

    abstract void initContent(GridPane gridPane);

}
