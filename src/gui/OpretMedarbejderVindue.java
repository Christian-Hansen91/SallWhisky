package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.application.Medarbejder;
import storage.Storage;

public class OpretMedarbejderVindue extends Stage {
    private Label lblId = new Label("ID: ");
    private TextField txfId = new TextField();
    private Label lblNavn = new Label("Navn: ");
    private TextField txfNavn = new TextField();
    private Label lblTlfNr = new Label("Tlf.nr: ");
    private TextField txfTlfNr = new TextField();
    private Button btnGem = new Button("Gem");
    private Button btnAnnuller = new Button("Anuller");
    private Medarbejder medarbejder = null;
    private StartVindue startVindue = null;


    public OpretMedarbejderVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);
        this.startVindue = startVindue;

        setTitle("Opret medarbejder");
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
        lblNavn.setTextFill(Color.BURLYWOOD);
        lblTlfNr.setTextFill(Color.BURLYWOOD);

        pane.add(lblId, 0, 2, 2, 1);
        pane.add(txfId, 2, 2, 2, 1);
        txfId.setMaxWidth(175);
        pane.setHalignment(txfId, HPos.CENTER);
        txfId.setText(Medarbejder.getTotalAntal() + "");

        pane.add(lblNavn, 0, 3, 2, 1);
        pane.add(txfNavn, 2, 3, 2, 1);
        txfNavn.setMaxWidth(175);
        pane.setHalignment(txfNavn, HPos.CENTER);

        pane.add(lblTlfNr, 0, 4, 2, 1);
        pane.add(txfTlfNr, 2, 4, 2, 1);
        txfTlfNr.setMaxWidth(175);
        pane.setHalignment(txfTlfNr, HPos.CENTER);

        pane.add(btnGem, 29, 16);
        pane.add(btnAnnuller, 30, 16);
        pane.setHalignment(btnGem, HPos.RIGHT);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);

        btnGem.setOnAction(event -> gemAction());
    }

    private void gemAction() {
        String navn = txfNavn.getText().trim();
        String tlfNr = txfTlfNr.getText().trim();

        if (!navn.isEmpty() && !tlfNr.isEmpty()) {
            medarbejder = Controller.opretMedarbejder(navn, Integer.parseInt(tlfNr));
            txfNavn.clear();
            txfTlfNr.clear();
            startVindue.setMedarbejder(medarbejder);
            this.hide();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oprettet");
            alert.setContentText("SUCCES! Medarbejderen er oprettet.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i opretning af medarbejder");
            alert.setHeaderText("Manglende information");
            alert.setContentText("Der mangler noget information for at oprette medarbejderen.");
            alert.show();
        }
    }
}
