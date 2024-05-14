package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OpretLagerVindue extends Stage {
    private Label lblId = new Label("ID: ");
    private TextField txfId = new TextField();


    public OpretLagerVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);

        setTitle("Opret lager");
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

        lblId.setTextFill(Color.BURLYWOOD);


        pane.add(lblId, 0, 2, 2, 1);
        pane.add(txfId, 2, 2, 2, 1);
        txfId.setMaxWidth(175);
        pane.setHalignment(txfId, HPos.RIGHT);

    }
}
