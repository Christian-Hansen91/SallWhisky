package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.application.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class OpretDestillatVindue extends Stage {
    private ComboBox<Whiskydestillering> cbDestilleringer = new ComboBox<>();
    private ComboBox<Fad> cbFade = new ComboBox<>();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextField txfKommentar = new TextField();
    private Label lblDestillat = new Label("Opret destillat");
    private ListView<String> lvwDestillat = new ListView<>();
    private Button btnOpretTapning = new Button("Opret tapning til destillat");
    private Label lblTapning = new Label("Tilføj væske til destillat");
    private Button btnOpretDestillat = new Button("Opret destillat");
    private Label lblTilfoejTilFad = new Label("Tilføj til fad:");
    private Label lblMaengdeILiter = new Label("Mængde (L): ");
    private TextField txfMaengdeILiter = new TextField();
    private Destillat destillat = null;
    private VaeskeTilDestillat vaeskeTilDestillat = null;
    private Whiskydestillering whiskydestillering = null;
    private StartVindue startVindue;
    private Fad fad = null;
    private Medarbejder medarbejder;
    private ArrayList<Boolean> kommentarCounter= new ArrayList<>();
    private int kommentarCount = 0;


    public OpretDestillatVindue(String title, Stage owner, StartVindue startVindue) {
        this.startVindue = startVindue;
        medarbejder = startVindue.getMedarbejder();
        this.initOwner(owner);

        setTitle("Opret destillat (til fad)");
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

        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblDestillat.setTextFill(Color.BURLYWOOD);
        lblTapning.setTextFill(Color.BURLYWOOD);
        lblTilfoejTilFad.setTextFill(Color.BURLYWOOD);
        lblMaengdeILiter.setTextFill(Color.BURLYWOOD);

        pane.add(new Label("                                       "), 2, 0);
        pane.add(lblTapning, 0, 0);
        pane.setHalignment(lblTapning, HPos.CENTER);

        pane.add(cbDestilleringer, 0, 2, 2, 1);
        cbDestilleringer.setMaxWidth(200);
        cbDestilleringer.getItems().addAll(Controller.getWhiskydestilleringerMedTilgængeligVæske());
        cbDestilleringer.setPromptText("Vælg destillering");
        cbDestilleringer.setVisibleRowCount(2);

        pane.add(lblMaengdeILiter, 0, 3);
        pane.add(txfMaengdeILiter, 0, 4);
        pane.add(lblKommentar, 0, 5);
        pane.add(txfKommentar, 0, 6, 1, 3);
        txfKommentar.setMinWidth(200);
        txfKommentar.setMinHeight(60);
        txfMaengdeILiter.setMinWidth(200);

        pane.add(btnOpretTapning, 0, 9);
        pane.setHalignment(btnOpretTapning, HPos.RIGHT);
        btnOpretTapning.setOnAction(event -> opretVaeskeTilDestillatAction());

        pane.add(lblDestillat, 3, 0, 2, 1);
        pane.setHalignment(lblDestillat, HPos.CENTER);
        pane.add(lvwDestillat, 3, 1, 2, 5);
        pane.setHalignment(lvwDestillat, HPos.CENTER);
        lvwDestillat.setMaxWidth(200);
        lvwDestillat.setMinHeight(100);

        Button btnFjernSidste = new Button("Fjern sidste");
        btnFjernSidste.setOnAction(e -> fjernSidste());
        pane.add(btnFjernSidste,3,6);
        pane.add(lblTilfoejTilFad, 4, 6);
        pane.setHalignment(lblTilfoejTilFad, HPos.RIGHT);
        pane.add(cbFade, 3, 7, 2, 1);
        cbFade.setMaxWidth(200);
        cbFade.getItems().addAll(Controller.getTommeFade());
        cbFade.setOnAction(event -> fadValgt());
        cbFade.setVisibleRowCount(2);

        pane.add(btnOpretDestillat, 3, 9);
        pane.setHalignment(btnOpretDestillat, HPos.RIGHT);
        btnOpretDestillat.setOnAction(event -> gemDestillatAction());
        Button btnAnnuller = new Button("Annuller");
        btnAnnuller.setOnAction(e -> annullerAction());
        pane.add(btnAnnuller,4,9);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);

    }

    private void fadValgt() {
        cbFade.setDisable(true);
    }

    private void fjernSidste() {
        if (destillat != null && !destillat.getVaeskerTilDestillat().isEmpty()) {
            destillat.fjernVaeske(destillat.getVaeskerTilDestillat().size() - 1);
            if (kommentarCounter.get(kommentarCount-1) == true) {
                destillat.fjernSindsteKommentar();
            }
            kommentarCounter.remove(kommentarCount-1);
            kommentarCount--;
        }
        opdaterLvDestillat();
    }

    private void opretVaeskeTilDestillatAction() {

        if (destillat == null) {
            if (cbFade.getSelectionModel().getSelectedItem() == null) {
                StartVindue.fejlIOprettelseAlert("Vælg et passende fad fra listen");
            } else {
                fad = cbFade.getSelectionModel().getSelectedItem();
                destillat = Controller.opretDestillat(fad, medarbejder);
                opdaterLvDestillat();
            }
        }
        if (cbDestilleringer.getSelectionModel().getSelectedItem() == null) {
            StartVindue.fejlIOprettelseAlert("Vælg en whiskydestillering");
        } else if (destillat != null) {
            whiskydestillering = cbDestilleringer.getSelectionModel().getSelectedItem();
            double maengde = 0;
            try {
                maengde = Double.parseDouble(txfMaengdeILiter.getText().trim());
                vaeskeTilDestillat = whiskydestillering.opretVaeskeTilDestillat(maengde);
                destillat.tilfoejVaeskeTilDestillat(vaeskeTilDestillat);
                if (!(txfKommentar.getText().isEmpty())) {
                    destillat.tilfoejKommentar(txfKommentar.getText().trim());
                    kommentarCounter.add(true);
                    kommentarCount++;
                } else {
                    kommentarCounter.add(false);
                    kommentarCount++;
                }
            } catch (NumberFormatException e) {
                StartVindue.kommafejlAlert();
            } catch (IllegalArgumentException e) {
                StartVindue.fejlIOprettelseAlert("Der er ikke nok tilgængelig destillering");
            }

            opdaterLvDestillat();
        }
    }

    private void gemDestillatAction() {
        try {
            String kommentar = txfKommentar.getText().trim();
            fad = cbFade.getSelectionModel().getSelectedItem();
            if (Controller.tjekKapacitetFad(fad, destillat.hentTotalMaengde())) {
                Controller.tilfoejDestillatTilSTorage(destillat);
                this.hide();
                StartVindue.succesIOprettelseAlert();
            } else {
                StartVindue.fejlIOprettelseAlert("Der er ikke plads i fadet til destillatet");
            }
        } catch(Exception e){
            StartVindue.fejlIOprettelseAlert("Husk at tilføje væske til destilatet");
        }
    }

    public void opdaterLvDestillat() {
        lvwDestillat.getItems().clear();
        lvwDestillat.getItems().setAll(Controller.skabVaeskeoversigt(destillat, medarbejder));
    }
    private void annullerAction() {
        this.hide();
    }

}
