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

public class OpretFadVindue extends Stage {
    private Label lblFadtype = new Label("Fadtype: ");
    private TextField txfFadetype = new TextField();
    private Label lblKapacitet = new Label("Kapacitet: ");
    private ComboBox<String> cbKapacitet = new ComboBox<>();
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


        pane.add(lblFadtype, 0, 1);
        pane.add(txfFadetype, 2, 1, 3, 1);
        txfFadetype.setMaxWidth(150);
        pane.setHalignment(txfFadetype, HPos.RIGHT);
        pane.add(lblKapacitet, 0, 2);
        pane.add(cbKapacitet, 2, 2, 3, 1);
        cbKapacitet.setMaxWidth(150);
        cbKapacitet.setValue("Vælg kapacitet");
        cbKapacitet.getItems().addAll("30L", "60L", "90L", "120L", "150L", "200L");
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

        pane.add(lblLagerplads, 24, 1, 3, 1);
        pane.add(cbLager, 24, 2, 3, 1);
        cbLager.setMinWidth(175);
        cbLager.getItems().addAll(Controller.getLagre());

        pane.add(btnGem, 24, 7, 2, 1);
        pane.setHalignment(btnGem, HPos.RIGHT);
        btnGem.setOnAction(event -> gemAction());
        pane.add(btnAnnuller, 26, 7);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);

    }

    private void gemAction() {
        String fadtype = txfFadetype.getText().trim();
        int kapacitet = Integer.parseInt((cbKapacitet.getSelectionModel().getSelectedItem()));
        String ophavsland = txfOphavsland.getText().trim();
        String leverandoer = txfLeverandoer.getText().trim();
        LocalDate indkoebsdato = dpIndkoebsdato.getValue();
        String historik = txaHistorik.getText().trim();
        Lager lager1 = cbLager.getValue();

        if (!fadtype.isEmpty()&& !ophavsland.isEmpty()&& !leverandoer.isEmpty()) {
            fad = Controller.opretFad(indkoebsdato, fadtype, kapacitet, ophavsland, leverandoer, historik);

            txfFadetype.clear();
            txfOphavsland.clear();
            txfLeverandoer.clear();
            txaHistorik.clear();
            Controller.addFad(fad);
            this.hide();
            StartVindue.succesIOprettelseAlert();
        } else {
            StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette fadet.");
        }

        if(!(lager1.equals(null))) {
            //HJÆLP
        }
    }
}
