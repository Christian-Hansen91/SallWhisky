package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class SoegningPane extends GridPane {
    public SoegningPane() {
        this.setPadding(new Insets(20));
        this.setPrefHeight(315);
        this.setPrefWidth(600);
        this.setStyle("-fx-background-image: url('https://migogaarhus.dk/wp-content/uploads/2021/01/Sall-whisky.jpg')");
        this.setHgap(10);
        this.setVgap(10);
        GridPane gridPane = new GridPane();
        initContent(gridPane);
    }

    abstract void initContent(GridPane gridPane);

}
