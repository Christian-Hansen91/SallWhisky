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

public class OpretGindestilleringVindue extends Stage implements LagerenhedsVindue {
    private DatePicker dpStartdato = new DatePicker();
    private DatePicker dpSlutdato = new DatePicker(LocalDate.now());
    private TextField txfVandtilfoejet = new TextField();
    private TextField txfAlkoholprocent = new TextField();
    private TextField txfLiter = new TextField();
    private TextField txfEnebaer = new TextField();
    private TextArea txaKommentar = new TextArea();
    private Label lblOverskrift = new Label("Opret Gindestillering");
    private Button btnGem = new Button("Gem");
    private Button btnAnnuller = new Button("Anuller");
    private Gindestillering gindestillering = null;
    private Medarbejder medarbejder;
    private StartVindue startVindue;
    private Button btnLagerplads = new Button("Lagerplads");
    private Lager lager = null;
    private int reol, hylde;
    private Label lblValgtLager = new Label("");
    private Label lblTilfoejIngrediens = new Label("Tilføj ingredienser:");
    private TextField txfIngrediensMaengde = new TextField();
    private ListView<Ingrediensmaengde> lvIngrediensmaengder;
    private ComboBox<Ingrediens> cbIngredienser;
    private Button btnTilfoejIngrediens, btnOpret;


    public OpretGindestilleringVindue(String title, Stage owner, StartVindue startVindue, Medarbejder medarbejder) {
        this.startVindue = startVindue;
        this.medarbejder = medarbejder;
        this.initOwner(owner);

        setTitle("Opret Gindestillering");
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

        lblOverskrift.setTextFill(Color.BURLYWOOD);
        lblTilfoejIngrediens.setTextFill(Color.BURLYWOOD);
        lblValgtLager.setTextFill(Color.BURLYWOOD);

        pane.add(lblOverskrift, 0, 0, 6, 1);
        pane.setHalignment(lblOverskrift, HPos.CENTER);

        pane.add(dpStartdato, 0, 2, 2, 1);
        dpStartdato.setPromptText("Startdato");
        dpStartdato.setPrefWidth(175);
        dpSlutdato.setPrefWidth(175);
        pane.add(dpSlutdato, 0, 3, 2, 1);
        dpSlutdato.setPromptText("Slutdato");

        dpStartdato.getEditor().setDisable(true);
        dpSlutdato.getEditor().setDisable(true);



        pane.add(txaKommentar, 0, 4, 2, 2);
        txaKommentar.setPromptText("Tilføj kommentar:");
        txaKommentar.setPrefWidth(175);
        txaKommentar.setMaxHeight(60);
        pane.setHalignment(txaKommentar, HPos.LEFT);

        pane.add(txfAlkoholprocent, 0, 6);
        txfAlkoholprocent.setMaxWidth(85);
        txfAlkoholprocent.setPromptText("Alkoholprocent:");
        pane.setHalignment(txfAlkoholprocent, HPos.RIGHT);
        pane.add(txfVandtilfoejet, 1, 6);
        txfVandtilfoejet.setPromptText("Vand tilføjet:");
        txfVandtilfoejet.setMaxWidth(85);
        pane.setHalignment(txfVandtilfoejet, HPos.RIGHT);

        pane.add(txfEnebaer, 2, 1, 2, 1);
        txfEnebaer.setPromptText("Enebær (g):");
        pane.setHalignment(txfEnebaer, HPos.RIGHT);
        btnOpret = new Button("Opret");
        pane.add(btnOpret, 3, 2);
        btnOpret.setPrefWidth(85);
        pane.setHalignment(btnOpret, HPos.RIGHT);
        btnOpret.setOnAction(e -> opretGindestillering());
        pane.add(txfLiter, 2, 2);
        txfLiter.setPromptText("Liter i alt:");
        pane.setHalignment(txfLiter, HPos.LEFT);
        txfLiter.setPrefWidth(85);

        pane.add(lblTilfoejIngrediens, 2, 3, 2, 1);
        pane.setHalignment(lblTilfoejIngrediens, HPos.CENTER);
        cbIngredienser = new ComboBox<>();
        cbIngredienser.setPromptText("Vælg ingrediens");
        pane.setHalignment(cbIngredienser, HPos.RIGHT);
        cbIngredienser.setPrefWidth(175);
        pane.add(cbIngredienser, 2, 4, 2, 1);
        cbIngredienser.getItems().setAll(Ingrediens.values());
        cbIngredienser.setDisable(true);

        pane.add(txfIngrediensMaengde, 2, 5, 2, 1);
        txfIngrediensMaengde.setPromptText("Mængde (g):");
        txfIngrediensMaengde.setDisable(true);
        txfIngrediensMaengde.setPrefWidth(175);
        pane.setHalignment(txfIngrediensMaengde, HPos.RIGHT);

        btnTilfoejIngrediens = new Button("Tilføj ingrediens");
        pane.add(btnTilfoejIngrediens, 2, 6, 2, 1);
        btnTilfoejIngrediens.setPrefWidth(175);
        pane.setHalignment(btnTilfoejIngrediens, HPos.RIGHT);

        btnTilfoejIngrediens.setOnAction(e -> tilfoejIngrediens());
        btnTilfoejIngrediens.setDisable(true);

        lvIngrediensmaengder = new ListView<>();
        pane.add(lvIngrediensmaengder, 4, 1, 2, 4);
        lvIngrediensmaengder.setMinWidth(175);

        pane.add(btnLagerplads, 4, 5);
        btnLagerplads.setPrefWidth(85);
        pane.setHalignment(btnLagerplads, HPos.LEFT);
        btnLagerplads.setDisable(true);
        pane.add(lblValgtLager, 5, 5);
        lblValgtLager.setMaxWidth(85);
        pane.setHalignment(lblValgtLager, HPos.RIGHT);

        pane.add(btnGem, 5, 6);
        pane.add(btnAnnuller, 4, 6);
        pane.setHalignment(btnGem, HPos.RIGHT);
        btnGem.setPrefWidth(85);
        btnAnnuller.setPrefWidth(85);
        pane.setHalignment(btnAnnuller, HPos.LEFT);
        btnGem.setDisable(true);
        btnGem.setOnAction(event -> gemAction());
        btnAnnuller.setOnAction(event -> annullerAction());
        btnLagerplads.setOnAction(event -> vaelgLagerAction());
    }

    private void opdaterIngrediensLv() {
        lvIngrediensmaengder.getItems().clear();
        lvIngrediensmaengder.getItems().setAll(gindestillering.hentIngredienser());
    }

    private void vaelgLagerAction() {
        LagerVindue lagerVindue = new LagerVindue(this);
        lagerVindue.showAndWait();
        opdaterLagerLabels();
    }

    private void opdaterLagerLabels() {
        if (lager != null) {
            lblValgtLager.setText("Lager: " + lager.getNavn() + ", reol " + reol + " hylde " + hylde);
        }
    }

    private void annullerAction() {
        this.hide();
    }

    @Override
    public void setValgtReolHylde(Lager lager, int reol, int hylde) {
        this.lager = lager;
        this.reol = reol;
        this.hylde = hylde;
    }

    private void opretGindestillering() {
        if(dpStartdato.getValue()==null)
            StartVindue.fejlIOprettelseAlert("Husk at tilføje en dato");
        LocalDate startdato = dpStartdato.getValue();
        LocalDate slutdato = dpSlutdato.getValue();
        double vandTilfoejet = 0.0;
        double liter = 0.0;
        double alkoholprocent = 0.0;
        double maengdeEnebaer = 0.0;
        try {
            vandTilfoejet = Double.parseDouble(txfVandtilfoejet.getText().trim());
            liter = Double.parseDouble(txfLiter.getText().trim());
            maengdeEnebaer = Double.parseDouble(txfEnebaer.getText().trim());
            alkoholprocent = Double.parseDouble(txfAlkoholprocent.getText().trim());
        } catch (NumberFormatException e) {
            StartVindue.kommafejlAlert();
        }
        String kommentar = txaKommentar.getText().trim();

        if (!startdato.isAfter(slutdato) && !slutdato.isBefore(startdato) && !(vandTilfoejet == 0.0) && !(liter == 0.0) && !(alkoholprocent == 0.0) && !(maengdeEnebaer == 0.0)) {
            gindestillering = Controller.opretGindestillering(startdato, slutdato, vandTilfoejet, alkoholprocent, liter, maengdeEnebaer, medarbejder);
            opdaterIngrediensLv();
            flipInputtilgaengelighed();
        } else {
            StartVindue.fejlIOprettelseAlert("Udfyld alle felter før oprettelse");
        }
    }

    private void tilfoejIngrediens() {
        try {
            if (cbIngredienser.getSelectionModel().getSelectedItem() == null) {
                StartVindue.fejlIOprettelseAlert("Vælg først en ingrediens i listen");
            } else {
                gindestillering.tilfoejIngrediensmaengde(cbIngredienser.getSelectionModel().getSelectedItem(), Double.parseDouble(txfIngrediensMaengde.getText()));
                opdaterIngrediensLv();
            }
        } catch (NumberFormatException e) {
            StartVindue.kommafejlAlert();
        } catch (NullPointerException e) {
            StartVindue.fejlIOprettelseAlert("Du skal udfylde alle felter");
        }
    }

    private void gemAction() {
        if (!(lager == null)) {
            lager.addLagerenhedAt(reol, hylde, gindestillering);
            this.hide();
            StartVindue.succesIOprettelseAlert();
        } else {
            StartVindue.fejlIOprettelseAlert("Vælg en lagerplads");
        }
    }

    public void flipInputtilgaengelighed() {
        dpStartdato.setDisable(true);
        dpSlutdato.setDisable(true);
        txaKommentar.setDisable(true);
        txfAlkoholprocent.setDisable(true);
        txfLiter.setDisable(true);
        txfVandtilfoejet.setDisable(true);
        txfEnebaer.setDisable(true);
        btnOpret.setDisable(true);

        cbIngredienser.setDisable(false);
        txfIngrediensMaengde.setDisable(false);
        btnLagerplads.setDisable(false);
        btnTilfoejIngrediens.setDisable(false);
        btnGem.setDisable(false);
    }
}
