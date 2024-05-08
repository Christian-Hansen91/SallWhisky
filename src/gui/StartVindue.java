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
    private Button btnOpretDestilleringsTapning = new Button("Opret destilleringstapning");
    private Button btnOpretOmhaeldning = new Button("Opret omhældning");
    private Button btnOpretWhiskyTapning = new Button("Opret whiskytapning");
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
        Label lblMedarbejder = new Label("Medarbejder: Snævar");
        pane.add(lblMedarbejder, 0, 0, 32, 1);
        lblMedarbejder.setTextFill(Color.BURLYWOOD);
        pane.add(lblVelkommen, 1, 0, 32, 1);
        btnOpretWhiskyDestillering.setMinWidth(160);
        btnOpretGinDestillering.setMinWidth(160);
        btnOpretMaltning.setMinWidth(160);
        btnOpretLager.setMinWidth(160);
        btnOpretFad.setMinWidth(160);
        btnOpretDestilleringsTapning.setMinWidth(160);
        btnOpretWhiskyTapning.setMinWidth(160);
        btnOpretOmhaeldning.setMinWidth(160);

        pane.add(btnOpretWhiskyDestillering, 0, 4, 2, 1);
        pane.setHalignment(btnOpretWhiskyDestillering, HPos.CENTER);
        pane.add(btnOpretGinDestillering, 29, 4, 2, 1);
        pane.setHalignment(btnOpretGinDestillering, HPos.CENTER);
        pane.add(btnOpretMaltning, 0, 5, 2, 1);
        pane.setHalignment(btnOpretMaltning, HPos.CENTER);
        pane.add(btnOpretFad, 29, 5, 2, 1);
        pane.setHalignment(btnOpretFad, HPos.CENTER);
        pane.add(btnOpretLager, 0, 6, 2, 1);
        pane.setHalignment(btnOpretLager, HPos.CENTER);
        pane.add(btnOpretOmhaeldning, 29, 6, 2, 1);
        pane.setHalignment(btnOpretOmhaeldning, HPos.CENTER);
        pane.add(btnOpretDestilleringsTapning, 0, 7, 2, 1);
        pane.setHalignment(btnOpretDestilleringsTapning, HPos.CENTER);
        pane.add(btnOpretWhiskyTapning, 29, 7, 2, 1);
        pane.setHalignment(btnOpretWhiskyTapning, HPos.CENTER);


        btnOpretDestilleringsTapning.setOnAction(event -> opretTapningAction());


    }

    private void opretTapningAction() {
        opretDestilleringsTapningsVindue.showAndWait();
    }

}