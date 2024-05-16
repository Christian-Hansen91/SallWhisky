package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class OpretWhiskyTapningsVindue extends Stage {
    private DatePicker dato = new DatePicker(LocalDate.now());
    private ComboBox<Destillat> cbDestilleringer = new ComboBox<>();
    private ComboBox<Whiskydestillering> cbFade = new ComboBox<>();
    private Label lblBeskrivelse = new Label("Beskrivelse: ");
    private TextField txfBeskrivelse = new TextField();
    private Label lblNavn = new Label("Navn: ");
    private TextField txfNavn = new TextField();
    private Label lblDestillat = new Label("Destillat");
    private ListView lvVaeskeTilWhisky = new ListView();
    private Button btnOpretTapning = new Button("Opret tapning til whisky");
    private Label lblTapning = new Label("Tapning ");
    private Button btnOpretDestillat = new Button("Opret destillat");
    private Button btnToemDestillat = new Button("Tøm destillat");

    private Label lblTilfoejTilFad = new Label("Tilføj til fad:");
    private Label lblMaengdeILiter = new Label("Mængde (L): ");
    private TextField txfMaengdeILiter = new TextField();
    private Destillat destillat = null;
    private Whiskydestillering whiskydestillering = null;
    private ComboBox<String> cbKapacitet = new ComboBox();
    private Label lblVandTilfoejet = new Label("Vand tilføjet (L): ");
    private TextField txfVandTilfoejet = new TextField();
    private Label lblAlcoholprocent = new Label("Alcoholprocent: ");
    private TextField txfAlcoholprocent = new TextField();
    private boolean toemDestilat = false;
    private TextField txfToemDestilat = new TextField();
    private ObservableList<VaeskeTilWhisky> listVaeskeTilWhiskyAdded = FXCollections.observableArrayList();
    private ObservableList<Destillat> listDestillarier = FXCollections.observableArrayList();
    private TextField txfantalFlasker = new TextField();
    private ListView<String> lvLedigeLagerPladser = new ListView<>();
    private ComboBox<Lager> cbLager = new ComboBox<>();
    private ObservableList<Lager> listLager = FXCollections.observableArrayList();


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
        pane.add(lvVaeskeTilWhisky, 0, 4);
        pane.add(txfToemDestilat, 0, 8);
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


        pane.add(cbKapacitet, 2, 7, 3, 1);
        cbKapacitet.setMaxWidth(150);
        cbKapacitet.setValue("Vælg flasketype");
        cbKapacitet.getItems().addAll("0.5", "0.7", "1");
        pane.setHalignment(cbKapacitet, HPos.RIGHT);

        pane.add(btnOpretDestillat, 2, 10, 2, 1);
        pane.setHalignment(btnOpretDestillat, HPos.RIGHT);
        btnOpretDestillat.setOnAction(event -> gemDestillatAction());

        pane.add(txfantalFlasker, 2, 0);

        pane.add(cbLager, 2, 2, 3, 1);
        cbLager.setMaxWidth(150);
        for (int i = 0; i < Controller.getLagre().size(); i++) {
            listLager.add(Controller.getLagre().get(i));
        }
        cbLager.setItems(listLager);
        cbLager.setValue(Controller.getLagre().get(0));
        pane.add(lvLedigeLagerPladser, 2, 1);
        //TODO herunder skal det laves, så man får lagret fra comboboksen
        updateLvLedigeLagerpladser();
        cbLager.setOnAction(event -> updateLvLedigeLagerpladser());
    }


    private void setToemDestilat() {
        this.toemDestilat = true;
        txfToemDestilat.setText("Du tømmer destillatet");
    }

    private void antalFlakserForAtTappe() {
        double antalFlasker = 0;
        double meangdeLiter = 0;
        for (int i = 0; i < listVaeskeTilWhiskyAdded.size(); i++) {
            meangdeLiter += listVaeskeTilWhiskyAdded.get(i).getMaengde();
        }
        meangdeLiter += Double.parseDouble(txfVandTilfoejet.getText());
        double kapacitet = Double.parseDouble((cbKapacitet.getSelectionModel().getSelectedItem()));
        antalFlasker = meangdeLiter / kapacitet;
        txfantalFlasker.setText("" + antalFlasker);
    }

    private void opretTapningTilWhiskyAction() {
        double maengdeWhisky = Double.parseDouble(txfMaengdeILiter.getText());
        Destillat destillat = cbDestilleringer.getValue();
        VaeskeTilWhisky vaeskeTilWhisky = destillat.opretVaeskeTilWhisky(maengdeWhisky);
        listVaeskeTilWhiskyAdded.add(vaeskeTilWhisky);
        listDestillarier.addAll(Controller.getDestillater());
        lvVaeskeTilWhisky.setItems(listVaeskeTilWhiskyAdded);
        cbDestilleringer.setItems(listDestillarier);
        if (toemDestilat == true) {
            destillat.saetAngelShare();
            toemDestilat = false;
            txfToemDestilat.clear();
        }
    }

    private void gemDestillatAction() {
        antalFlakserForAtTappe();
        String beskrivelse = txfBeskrivelse.getText().trim();
        LocalDate datoForTapning = dato.getValue();
        String navn = txfNavn.getText().trim();
        double flaskeStr = Double.parseDouble((cbKapacitet.getSelectionModel().getSelectedItem()));
        double vandTilfoejet = Double.parseDouble(txfVandTilfoejet.getText().trim());
        double alcoholprocent = Double.parseDouble(txfAlcoholprocent.getText().trim());
        Whisky whisky = new Whisky(datoForTapning, navn, beskrivelse,flaskeStr, vandTilfoejet, alcoholprocent);
        cbLager.getValue().addLagerenhedAt(stringToInts(), whisky);

        //destillat = whiskydestillering.opretDestillat(whiskydestillering1);
    }

    private void updateLvLedigeLagerpladser() {
        ObservableList<String> listLedigeLagerPladser = FXCollections.observableArrayList();
        for (int i = 0; i < cbLager.getValue().getReolliste().length; i++) {
            for (int j = 0; j < cbLager.getValue().getReolliste()[i].length; j++) {
                if (cbLager.getValue().getReolliste()[i][j] == null)
                    listLedigeLagerPladser.add("Reol: " + (i + 1) + ", plads: " + (j + 1));
            }
        }
        lvLedigeLagerPladser.setItems(listLedigeLagerPladser);
    }

    private ArrayList<Integer> stringToInts() {
        ArrayList<Integer> plads = new ArrayList<>();
        int pladsReol = Integer.parseInt(lvLedigeLagerPladser.getSelectionModel().getSelectedItem().substring(7))-1;
        int pladsHylde = Integer.parseInt(lvLedigeLagerPladser.getSelectionModel().getSelectedItem().substring(17))-1;
        plads.add(pladsReol);
        plads.add(pladsHylde);
        return plads;
    }
    public String whiskyBetegnelse(){
        boolean sammeMalt=false;
        for (int i = 0; i <listVaeskeTilWhiskyAdded.size() ; i++) {
            //Maltning currentMaltning = listVaeskeTilWhiskyAdded.get(i).getDestillat()...
            //listVaeskeTilWhiskyAdded
        }
        String betegnelse = null;
        if(listVaeskeTilWhiskyAdded.size()==1 && Double.parseDouble(txfVandTilfoejet.getText())==0){
            betegnelse="Cask strength";
        }
        else if(listVaeskeTilWhiskyAdded.size()==1){
            betegnelse="Sinle cask";
        }
        //else if()
        //else

        return betegnelse;
    }
}
