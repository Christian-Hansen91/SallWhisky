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
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class OpretGindestilleringVindue extends Stage implements LagerenhedsVindue {
    private Label lblDatoer = new Label("Vælg start- og slutdato:");
    private DatePicker dpStartdato = new DatePicker();
    private DatePicker dpSlutdato = new DatePicker(LocalDate.now());
    private Label lblNavn = new Label("Giv din gin et navn:");
    private TextField txfNavn = new TextField();
    private Label lblVandTilfoejet = new Label("Vand tilføjet: ");
    private TextField txfVandtilfoejet = new TextField();
    private Label lblAlkoholprocent = new Label("Alkoholprocent: ");
    private TextField txfAlkoholprocent = new TextField();
    private Label lblLiter = new Label("Mængde (L): ");
    private TextField txfLiter = new TextField();
    private Label lblMaengdeEnebaer = new Label("Enebær (g): ");
    private TextField txfEnebaer = new TextField();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextArea txaKommentar = new TextArea();
    private Label lblOverskrift = new Label("Opret Gindestillering");
    private Button btnGem = new Button("Gem");
    private Button btnAnnuller = new Button("Anuller");
    private Gindestillering gindestillering = null;
    private Medarbejder medarbejder;
    private StartVindue startVindue;
    private Button btnVaelgLager = new Button("Lagerplads");
    private Lager lager = null;
    private int reol, hylde;
    private Label lblLager = new Label("");
    private Label lblTilfoejIngrediens = new Label("Tilføj ingredienser:");
    private Label lblIngrediensMaengde = new Label("Mængde: ");
    private TextField txfIngrediensMaengde = new TextField();
    private ArrayList<Ingrediensmaengde> ingredienser = new ArrayList<>();
    private ListView<Ingrediensmaengde> lvIngrediensmaengder;
    private ComboBox<Ingrediens> cbIngredienser;


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
        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setPrefHeight(315);
        pane.setPrefWidth(600);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setStyle("-fx-background-image: url('https://migogaarhus.dk/wp-content/uploads/2021/01/Sall-whisky.jpg')");

        lblVandTilfoejet.setTextFill(Color.BURLYWOOD);
        lblAlkoholprocent.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblOverskrift.setTextFill(Color.BURLYWOOD);
        lblLiter.setTextFill(Color.BURLYWOOD);
        lblDatoer.setTextFill(Color.BURLYWOOD);
        lblLager.setTextFill(Color.BURLYWOOD);
        lblMaengdeEnebaer.setTextFill(Color.BURLYWOOD);
        lblTilfoejIngrediens.setTextFill(Color.BURLYWOOD);
        lblIngrediensMaengde.setTextFill(Color.BURLYWOOD);
        lblNavn.setTextFill(Color.BURLYWOOD);

        pane.add(lblOverskrift, 0, 0, 8, 1);
        pane.setHalignment(lblOverskrift, HPos.CENTER);

        //pane.add(lblDatoer, 0,1,2,1);
        pane.add(dpStartdato, 0, 2, 2, 1);
        dpStartdato.setPromptText("Startdato");
        pane.add(dpSlutdato, 0, 3, 2, 1);
        dpSlutdato.setPromptText("Slutdato");

        dpStartdato.getEditor().setDisable(true);
        dpSlutdato.getEditor().setDisable(true);

        //pane.add(lblNavn, 0, 4, 2, 1);
        pane.add(txfNavn, 0, 1, 2, 1);
        txfNavn.setPromptText("Navngiv gin:");
        pane.setHalignment(txfNavn, HPos.RIGHT);

        pane.add(txaKommentar, 0, 4, 2, 2);
        txaKommentar.setPromptText("Tilføj kommentar:");
        txaKommentar.setMaxWidth(175);
        pane.setHalignment(txaKommentar, HPos.LEFT);

        //pane.add(lblAlkoholprocent, 0, 6);
        pane.add(txfAlkoholprocent, 0, 6);
        txfAlkoholprocent.setMaxWidth(75);
        txfAlkoholprocent.setPromptText("Alkoholprocent:");
        pane.setHalignment(txfAlkoholprocent, HPos.RIGHT);
        pane.add(txfVandtilfoejet, 1, 6);
        txfVandtilfoejet.setPromptText("Vand tilføjet:");
        txfVandtilfoejet.setMaxWidth(75);
        pane.setHalignment(txfVandtilfoejet, HPos.RIGHT);


        //pane.add(lblMaengdeEnebaer, 2, 1);
        pane.add(txfEnebaer, 2, 1, 2, 1);
        txfEnebaer.setPromptText("Enebær (g):");
        pane.setHalignment(txfEnebaer, HPos.RIGHT);

        pane.add(lblTilfoejIngrediens, 2, 2, 2, 1);
        cbIngredienser = new ComboBox<>();
        cbIngredienser.setPromptText("Vælg ingrediens");
        pane.setHalignment(cbIngredienser, HPos.RIGHT);
        cbIngredienser.setPrefWidth(150);
        pane.add(cbIngredienser, 2, 3, 2, 1);
        cbIngredienser.getItems().setAll(Ingrediens.values());

        //Tilføj combobox eller lignende med enum ingredienser, måske man kan skrive hvad
        // som helst og så oprettes ny enum automatisk?? Sejt
        //man kan ikke lave enum på runtime :(

        //pane.add(lblIngrediensMaengde, 2, 5);
        pane.add(txfIngrediensMaengde, 2, 4, 2, 1);
        txfIngrediensMaengde.setPromptText("Mængde (g):");
        pane.setHalignment(txfIngrediensMaengde, HPos.RIGHT);

        Button btnTilfoejIngrediens = new Button("Tilføj ingrediens");
        pane.add(btnTilfoejIngrediens, 2, 5, 2, 1);
        pane.setHalignment(btnTilfoejIngrediens, HPos.RIGHT);
        btnTilfoejIngrediens.setOnAction(e -> opdaterIngrediensLv());


        //pane.add(lblVandTilfoejet, 2, 6);
        //pane.add(lblLiter, 4,1);
        pane.add(txfLiter, 2, 6);
        txfLiter.setPromptText("Liter i alt:");
        pane.setHalignment(txfLiter, HPos.RIGHT);
        txfLiter.setMaxWidth(75);

        //pane.add(lblKommentar, 4, 2, 2, 1);

        lvIngrediensmaengder = new ListView<>();
        pane.add(lvIngrediensmaengder, 4, 1, 3, 4);


        pane.add(btnVaelgLager, 4, 5);
        pane.setHalignment(btnVaelgLager, HPos.LEFT);
        pane.add(lblLager, 5, 5, 2, 1);

        pane.add(btnGem, 6, 6);
        pane.add(btnAnnuller, 4, 6);
        pane.setHalignment(btnGem, HPos.RIGHT);
        pane.setHalignment(btnAnnuller, HPos.LEFT);

        btnGem.setOnAction(event -> gemAction());
        btnAnnuller.setOnAction(event -> annullerAction());
        btnVaelgLager.setOnAction(event -> vaelgLagerAction());
    }

    private void opdaterIngrediensLv() {
        if (this.gindestillering == null) {
            opretGindestillering();
        }
        try {
            if (cbIngredienser.getSelectionModel().getSelectedItem() == null) {
                StartVindue.fejlIOprettelseAlert("Vælg en ingrediens i listen");
            } else {
                gindestillering.tilfoejIngrediensmaengde(cbIngredienser.getSelectionModel().getSelectedItem(), Double.parseDouble(txfIngrediensMaengde.getText()));
                lvIngrediensmaengder.getItems().clear();
                lvIngrediensmaengder.getItems().setAll(gindestillering.hentIngredienser());
            }
        } catch (NumberFormatException e) {
            StartVindue.kommafejlAlert();
        } catch (NullPointerException e) {
            StartVindue.fejlIOprettelseAlert("Du skal udfylde alle felter før du kan oprette en gindestillering");
        }
    }

    private void vaelgLagerAction() {
        LagerVindue lagerVindue = new LagerVindue(this);
        lagerVindue.showAndWait();
        opdaterLagerLabels();
    }

    private void opdaterLagerLabels() {
        if (lager != null) {
            lblLager.setText("Lager: " + lager.getNavn() + ", reol " + reol + " hylde " + hylde);
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
        medarbejder = startVindue.getMedarbejder();

        if (!startdato.isAfter(slutdato) && !slutdato.isBefore(startdato) && !(vandTilfoejet == 0.0) && !(liter == 0.0) && !(alkoholprocent == 0.0) && !(maengdeEnebaer == 0.0)) {
            gindestillering = Controller.opretGindestillering(startdato, slutdato, vandTilfoejet, alkoholprocent, liter, maengdeEnebaer, medarbejder);
        } else {
            StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette gindestilleringen.");
            String medarbejder1 = medarbejder.getNavn().trim();
            try {
                if (!startdato.isAfter(slutdato) && !slutdato.isBefore(startdato) && !(vandTilfoejet == 0.0) && !(liter == 0.0) && !(alkoholprocent == 0.0) && !(maengdeEnebaer == 0.0)) {
                    gindestillering = Controller.opretGindestillering(startdato, slutdato, vandTilfoejet, alkoholprocent, liter, maengdeEnebaer, medarbejder);
                    Controller.addGindestillering(gindestillering);
                    this.hide();
                    StartVindue.succesIOprettelseAlert();
                } else {
                    StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette gindestilleringen.");
                }
            } catch (NullPointerException e) {
                StartVindue.fejlIOprettelseAlert("Udfyld alle felter for at oprette en destillering");
            }
        }
    }

    private void gemAction() {
        if (!lager.equals(null)) {
            lager.addLagerenhedAt(reol, hylde, gindestillering);
            this.hide();
            StartVindue.succesIOprettelseAlert();
        } else {
            StartVindue.fejlIOprettelseAlert("Vælg en lagerplads");
        }

    }
}
