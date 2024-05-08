package gui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartVindue extends Application {
    private OpretDestilleringsTapningsVindue opretDestilleringsTapningsVindue;
    private Button btnOpretMaltning = new Button("Opret maltning");
    private Button btnOpretWhiskyDestillering = new Button("Opret whiskydestillering");
    private Button btnOpretGinDestillering = new Button("Opret gindestillering");
    private Button btnOpretTapning = new Button("Opret tapning");
    private Button btnOpretLager = new Button("Opret lager");
    private Button btnOpretFad = new Button("Opret fad");


    public void start(Stage stage) throws Exception {

        stage.setTitle("Sall Whisky Distillery");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        opretDestilleringsTapningsVindue = new OpretDestilleringsTapningsVindue("Opret destilleringstapning", stage);
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setPrefHeight(315);
        pane.setPrefWidth(600);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setStyle("-fx-background-image: url('https://migogaarhus.dk/wp-content/uploads/2021/01/Sall-whisky.jpg')");

        Label lblVelkommen = new Label("Velkommen til Sall Whisky Distillery");
        pane.setHalignment(lblVelkommen, HPos.CENTER);
        lblVelkommen.setTextFill(Color.BURLYWOOD);
        pane.add(lblVelkommen, 0, 0, 32, 1);
        btnOpretWhiskyDestillering.setMinWidth(150);
        btnOpretGinDestillering.setMinWidth(150);
        btnOpretMaltning.setMinWidth(150);
        btnOpretLager.setMinWidth(150);
        btnOpretFad.setMinWidth(150);
        btnOpretTapning.setMinWidth(150);

        pane.add(btnOpretWhiskyDestillering, 0, 4, 2, 1);
        pane.setHalignment(btnOpretWhiskyDestillering, HPos.CENTER);
        pane.add(btnOpretGinDestillering, 29, 4, 2, 1);
        pane.setHalignment(btnOpretGinDestillering, HPos.CENTER);
        pane.add(btnOpretMaltning, 0, 5, 2, 1);
        pane.setHalignment(btnOpretMaltning, HPos.CENTER);
        pane.add(btnOpretLager, 29, 5, 2, 1);
        pane.setHalignment(btnOpretLager, HPos.CENTER);
        pane.add(btnOpretFad, 0, 6, 2, 1);
        pane.setHalignment(btnOpretFad, HPos.CENTER);
        pane.add(btnOpretTapning, 29, 6, 2, 1);
        pane.setHalignment(btnOpretTapning, HPos.CENTER);
        btnOpretTapning.setOnAction(event -> opretTapningAction());


    }

    private void opretTapningAction() {
        opretDestilleringsTapningsVindue.showAndWait();
    }

}