package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.application.Destillat;
import model.application.Fad;
import model.application.Tapning;
import model.application.Whiskydestillering;

import java.time.LocalDate;

public class OpretWhiskyTapningsVindue extends Stage {
    private DatePicker dato = new DatePicker(LocalDate.now());
    private ComboBox<Destillat> cbDestilleringer = new ComboBox<>();
    private ComboBox<Whiskydestillering> cbFade = new ComboBox<>();
    private Label lblBeskrivelse = new Label("Beskrivelse: ");
    private TextField txfBeskrivelse = new TextField();
    private Label lblNavn = new Label("Navn: ");
    private TextField txfNavn = new TextField();
    private Label lblDestillat = new Label("Destillat");
    private TextArea txaDestillat = new TextArea();
    private Button btnOpretTapning = new Button("Opret tapning til whisky");
    private Label lblTapning = new Label("Tapning ");
    private Button btnOpretDestillat = new Button("Opret destillat");
    private Button btnToemDestillat = new Button("Tøm destillat");

    private Label lblTilfoejTilFad = new Label("Tilføj til fad:");
    private Label lblMaengdeILiter = new Label("Mængde (L): ");
    private TextField txfMaengdeILiter = new TextField();
    private Destillat destillat = null;
    private Tapning tapning = null;
    private Whiskydestillering whiskydestillering = null;
    private ComboBox<String> cbKapacitet = new ComboBox();
    private Label lblVandTilfoejet = new Label("Vand tilføjet (L): ");
    private TextField txfVandTilfoejet = new TextField();
    private Label lblAlcoholprocent = new Label("Alcoholprocent: ");
    private TextField txfAlcoholprocent = new TextField();
    private boolean toemDestilat = false;



    public OpretWhiskyTapningsVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);

        setTitle("Opret whiskytapning");
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

        lblBeskrivelse.setTextFill(Color.BURLYWOOD);
        lblDestillat.setTextFill(Color.BURLYWOOD);
        lblTapning.setTextFill(Color.BURLYWOOD);
        lblTilfoejTilFad.setTextFill(Color.BURLYWOOD);
        lblMaengdeILiter.setTextFill(Color.BURLYWOOD);
        lblNavn.setTextFill(Color.BURLYWOOD);
        lblAlcoholprocent.setTextFill(Color.BURLYWOOD);
        lblVandTilfoejet.setTextFill(Color.BURLYWOOD);

        pane.add(lblTapning, 1, 1);
        pane.setHalignment(lblTapning, HPos.CENTER);

        pane.add(dato, 1, 2);

        pane.add(cbDestilleringer, 0, 1, 2, 1);
        cbDestilleringer.setMaxWidth(200);
        cbDestilleringer.getItems().addAll(Controller.getDestillater());


        pane.add(lblMaengdeILiter, 0, 2);
        pane.add(txfMaengdeILiter, 0, 3);
        pane.add(txaDestillat,0,4);
        pane.add(lblBeskrivelse, 1, 3);
        pane.add(txfBeskrivelse, 1, 4);
        pane.add(lblNavn, 1, 5);
        pane.add(txfNavn, 1, 6);
        pane.add(lblAlcoholprocent, 1, 7);
        pane.add(txfAlcoholprocent, 1, 8);
        pane.add(lblVandTilfoejet, 1, 9);
        pane.add(txfVandTilfoejet, 1, 10);

        pane.add(btnOpretTapning, 0, 6);
        pane.setHalignment(btnOpretTapning, HPos.RIGHT);
        btnOpretTapning.setOnAction(event -> opretTapningTilWhiskyAction());
        pane.add(btnToemDestillat, 0, 7);
        pane.setHalignment(btnToemDestillat, HPos.RIGHT);
        btnToemDestillat.setOnAction(event -> setToemDestilat());


        pane.add(cbKapacitet, 15, 7, 3, 1);
        cbKapacitet.setMaxWidth(150);
        cbKapacitet.setValue("Vælg flasketype");
        cbKapacitet.getItems().addAll("1", "2", "3");
        pane.setHalignment(cbKapacitet, HPos.RIGHT);

        pane.add(btnOpretDestillat, 15, 10, 2, 1);
        pane.setHalignment(btnOpretDestillat, HPos.RIGHT);
        btnOpretDestillat.setOnAction(event -> gemDestillatAction());
    }

    private void setToemDestilat() {
        this.toemDestilat = true;
        //TODO indikater for at dette er valgt
    }

    private double antalFlakserForAtTappe() {
        System.out.println(0.7/0.7);
        double antalFlasker = 0;
        double meangdeLiter = Integer.parseInt(txfMaengdeILiter.getText());
        double kapacitet = Integer.parseInt((cbKapacitet.getSelectionModel().getSelectedItem())) - Integer.parseInt(txfVandTilfoejet.getText());
        antalFlasker =  meangdeLiter / kapacitet;
        System.out.println(antalFlasker);
        antalFlasker =  meangdeLiter % kapacitet;
        System.out.println(antalFlasker);
        return antalFlasker;
    }

    private void opretTapningTilWhiskyAction() {
        double meangdeWhisky = Double.parseDouble(txfMaengdeILiter.getText());
        Destillat destillat = cbDestilleringer.getValue();
        //tapning = destillat.opretTapning(destillat, Double.parseDouble(maengde), kommentar);
        //todo her skal oprettes en tapning
        destillat.tilfoejTapning(tapning);
        txaDestillat.setText(tapning.toString());
        if(toemDestilat==true){
            //todo set angelshare på destilat
        }

    }

    private void gemDestillatAction() {
        String kommentar = txfBeskrivelse.getText().trim();
        Whiskydestillering whiskydestillering1 = cbFade.getSelectionModel().getSelectedItem();

        //destillat = whiskydestillering.opretDestillat(whiskydestillering1);
    }
}
