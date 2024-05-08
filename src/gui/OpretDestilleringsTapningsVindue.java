package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OpretDestilleringsTapningsVindue extends Stage {
    public OpretDestilleringsTapningsVindue(String title, Stage owner) {
        this.initOwner(owner);

        setTitle("Opret destilleringstapning");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setPrefHeight(315);
        pane.setPrefWidth(600);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setStyle("-fx-background-image: url('https://migogaarhus.dk/wp-content/uploads/2021/01/Sall-whisky.jpg')");


    }
}
