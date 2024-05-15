package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.application.Maltning;

import java.time.LocalDate;

public class OpretMaltningVindue extends Stage {
    private Label lblId = new Label("ID: ");
    private TextField txfId = new TextField();
    private DatePicker dpdato = new DatePicker(LocalDate.now());
    private Label lblkornsort = new Label("Kornsort: ");
    private TextField txfKornsort = new TextField();
    private Label lblMarknavn = new Label("Marknavn: ");
    private TextField txfMarknavn = new TextField();
    private Label lblMaengde = new Label("Mængde: ");
    private TextField txfMaengde = new TextField();
    private Label lblRygemateriale = new Label("Evt. rygemateriale: ");
    private TextField txfRygemateriale = new TextField();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextArea txaKommentar = new TextArea();
    private Maltning maltning;
    private Button btnGem = new Button("Gem");
    private Button btnAnnuller = new Button("Annuller");

    public OpretMaltningVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);

        setTitle("Opret maltning");
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
        lblkornsort.setTextFill(Color.BURLYWOOD);
        lblMarknavn.setTextFill(Color.BURLYWOOD);
        lblMaengde.setTextFill(Color.BURLYWOOD);
        lblRygemateriale.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);


        pane.add(lblId, 0, 2, 2, 1);
        pane.add(txfId, 2, 2, 2, 1);
        txfId.setMinWidth(110);
        pane.setHalignment(txfId, HPos.RIGHT);
        pane.add(dpdato, 0, 3, 4, 1);
        pane.add(lblkornsort, 0, 4, 2, 1);
        pane.add(txfKornsort, 2, 4, 2, 1);
        txfKornsort.setMinWidth(110);
        pane.setHalignment(txfKornsort, HPos.RIGHT);
        pane.add(lblMarknavn, 0, 5, 2, 1);
        pane.add(txfMarknavn, 2, 5, 2, 1);
        txfMarknavn.setMinWidth(110);
        pane.setHalignment(txfMarknavn, HPos.RIGHT);
        pane.add(lblMaengde, 0, 6, 2, 1);
        pane.add(txfMaengde, 2, 6, 2, 1);
        txfMaengde.setMinWidth(110);
        pane.setHalignment(txfMaengde, HPos.RIGHT);

        pane.add(lblRygemateriale, 25, 2, 2, 1);
        pane.add(txfRygemateriale, 25, 3, 2, 1);
        txfRygemateriale.setMinWidth(110);
        pane.setHalignment(txfRygemateriale, HPos.RIGHT);
        pane.add(lblKommentar, 25, 4, 2, 1);
        pane.add(txaKommentar, 25, 5, 2, 2);
        txaKommentar.setMaxWidth(175);
        txaKommentar.setMaxHeight(60);
        pane.setHalignment(txaKommentar, HPos.RIGHT);
        pane.add(btnGem, 25, 7, 2, 1);
        pane.setHalignment(btnGem, HPos.CENTER);
        btnGem.setOnAction(event -> gemAction());
        pane.add(btnAnnuller, 25, 7, 2, 1);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);
    }

    private void gemAction() {
        String kornsort = txfKornsort.getText().trim();
        String marknavn = txfMarknavn.getText().trim();
        double maengde = 0.0;
        try {
            maengde = Double.parseDouble(txfMaengde.getText().trim());
            String rygemateriale = txfRygemateriale.getText().trim();
            String kommentar = txaKommentar.getText().trim();

            if (!kornsort.isEmpty() && !marknavn.isEmpty() && !(maengde == 0.0)) {
                maltning = Controller.opretMaltning(maengde, kornsort, marknavn);

                txfKornsort.clear();
                txfMaengde.clear();
                txfMarknavn.clear();

                this.hide();
                StartVindue.succesIOprettelseAlert();
            } else {
                StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette maltningen.");
            }
        } catch (Exception e) {
            StartVindue.kommafejlAlert();
        }

    }
}
