package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.application.Fad;
import model.application.Lager;

import java.time.LocalDate;

public class OpretFadVindue extends Stage implements LagerenhedsVindue {
    private Label lblFadtype = new Label("Fadtype: ");
    private TextField txfFadetype = new TextField();
    private Label lblKapacitet = new Label("Kapacitet (L): ");
    private ComboBox<Integer> cbKapacitet = new ComboBox<>();
    private Label lblLagerplads = new Label("Vælg en lagerplads");
    private ComboBox<Lager> cbLager = new ComboBox<>();
    private Lager lager = null;
    private Label lblOphavsland = new Label("Ophavsland: ");
    private TextField txfOphavsland = new TextField();
    private Label lblIndkoebsdato = new Label("Indkøbsdato: ");
    private DatePicker dpIndkoebsdato = new DatePicker();
    private Label lblLevarandoer = new Label("Leverandør: ");
    private TextField txfLeverandoer = new TextField();
    private Label lblHistorik = new Label("Historik: ");
    private TextArea txaHistorik = new TextArea();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextArea txaKommentar = new TextArea();
    private Button btnGem = new Button("Gem");
    private Button btnAnnuller = new Button("Annuller");
    private Fad fad = null;
    private int reol, hylde;
    private Label lblLager = new Label("");

    public OpretFadVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);
        setTitle("Opret fad");
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

        lblFadtype.setTextFill(Color.BURLYWOOD);
        lblOphavsland.setTextFill(Color.BURLYWOOD);
        lblLevarandoer.setTextFill(Color.BURLYWOOD);
        lblHistorik.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblKapacitet.setTextFill(Color.BURLYWOOD);
        lblIndkoebsdato.setTextFill(Color.BURLYWOOD);
        lblLagerplads.setTextFill(Color.BURLYWOOD);
        lblLager.setTextFill(Color.BURLYWOOD);

        pane.add(lblFadtype, 0, 1);
        pane.add(txfFadetype, 2, 1, 3, 1);
        txfFadetype.setMaxWidth(150);
        pane.setHalignment(txfFadetype, HPos.RIGHT);
        pane.add(lblKapacitet, 0, 2);
        pane.add(cbKapacitet, 2, 2, 3, 1);
        cbKapacitet.setMaxWidth(150);
        cbKapacitet.getItems().addAll(30, 60, 90, 120, 150, 200);
        pane.setHalignment(cbKapacitet, HPos.RIGHT);
        pane.add(lblIndkoebsdato, 0, 3);
        pane.add(dpIndkoebsdato, 2, 3, 3, 1);
        dpIndkoebsdato.setMaxWidth(150);
        pane.setHalignment(dpIndkoebsdato, HPos.RIGHT);
        pane.add(lblLevarandoer, 0, 4);
        pane.add(txfLeverandoer, 2, 4, 3, 1);
        txfLeverandoer.setMaxWidth(150);
        pane.setHalignment(txfLeverandoer, HPos.RIGHT);
        pane.add(lblOphavsland, 0, 5);
        pane.add(txfOphavsland, 2, 5, 3, 1);
        txfOphavsland.setMaxWidth(150);
        pane.setHalignment(txfOphavsland, HPos.RIGHT);
        pane.add(lblHistorik, 0, 6);
        pane.add(txaHistorik, 2, 6, 3, 1);
        txaHistorik.setMaxWidth(150);
        pane.setHalignment(txaHistorik, HPos.RIGHT);
        pane.add(lblKommentar, 0, 7);
        pane.add(txaKommentar, 2, 7, 3, 1);
        txaKommentar.setMaxWidth(150);
        pane.setHalignment(txaKommentar, HPos.RIGHT);
        Button btnVaelgLager = new Button("Vælg lager");
        btnVaelgLager.setOnAction(e -> vaelgLager());
        pane.add(lblLagerplads, 24, 1, 3, 1);
        pane.add(btnVaelgLager, 24, 2, 3, 1);

        pane.add(lblLager,24,3,3,1);
        pane.add(btnGem, 24, 7, 2, 1);
        pane.setHalignment(btnGem, HPos.RIGHT);
        btnGem.setOnAction(event -> gemAction());
        pane.add(btnAnnuller, 26, 7);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);
    }
    private void vaelgLager() {
        LagerVindue lagerVindue = new LagerVindue(this);
        lagerVindue.showAndWait();
        opdaterLagerLabels();
    }

    private void opdaterLagerLabels() {
        if (lager != null) {
            lblLager.setText("Lager: " + lager.getNavn() + ", reol " + reol + " hylde " + hylde);
        }
    }

    private void gemAction() {
        String fadtype = txfFadetype.getText().trim();
        int kapacitet = (cbKapacitet.getSelectionModel().getSelectedItem());
        String ophavsland = txfOphavsland.getText().trim();
        String leverandoer = txfLeverandoer.getText().trim();
        LocalDate indkoebsdato = dpIndkoebsdato.getValue();
        String historik = txaHistorik.getText().trim();

        if (!fadtype.isEmpty()&& !ophavsland.isEmpty()&& !leverandoer.isEmpty()) {
            fad = Controller.opretFad(indkoebsdato, fadtype, kapacitet, ophavsland, leverandoer, historik);
            txfFadetype.clear();
            txfOphavsland.clear();
            txfLeverandoer.clear();
            txaHistorik.clear();
            this.hide();
            StartVindue.succesIOprettelseAlert();
        } else {
            StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette fadet.");
        }
    }
    @Override
    public void setValgtReolHylde(Lager lager, int reol, int hylde) {
        this.lager = lager;
        this.reol = reol;
        this.hylde = hylde;
    }
}