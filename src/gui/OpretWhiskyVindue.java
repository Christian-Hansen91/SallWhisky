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
import model.application.Destillat;
import model.application.Lager;
import model.application.VaeskeTilWhisky;
import model.application.Whisky;

import java.time.LocalDate;

public class OpretWhiskyVindue extends Stage {
    private DatePicker dpDato = new DatePicker(LocalDate.now());
    private ComboBox<Destillat> cbDestillater = new ComboBox<>();
    private Label lblBeskrivelse = new Label("Beskrivelse: ");
    private TextField txfBeskrivelse = new TextField();
    private Label lblNavn = new Label("Navn: ");
    private TextField txfNavn = new TextField();
    private ListView lvVaeskeTilWhisky = new ListView();
    private Button btnTilfoejVaeskeTilWhisky = new Button("Tilføj væske til whisky");
    private Label lblOpretWhisky = new Label("Opret whisky");
    private Button btnOpretWhisky = new Button("Opret whisky");
    private Button btnToemDestillat = new Button("Tøm destillat");
    private Label lblTilfoejTilFad = new Label("Tilføj til fad:");
    private Label lblMaengdeILiter = new Label("Mængde (L): ");
    private TextField txfMaengdeILiter = new TextField();
    private ComboBox<String> cbFlaskestr = new ComboBox();
    private Label lblVandTilfoejet = new Label("Vand tilføjet (L): ");
    private TextField txfVandTilfoejet = new TextField();
    private Label lblAlcoholprocent = new Label("Alcoholprocent: ");
    private TextField txfAlkoholprocent = new TextField();
    private boolean toemDestilat = false;
    private TextField txfToemDestillat = new TextField();
    private ObservableList<VaeskeTilWhisky> listVaeskeTilWhiskyAdded = FXCollections.observableArrayList();
    private ObservableList<Destillat> listDestillater = FXCollections.observableArrayList();
    private TextField txfantalFlasker = new TextField();
    private ListView<String> lvLedigeLagerPladser = new ListView<>();
    private ComboBox<Lager> cbLager = new ComboBox<>();
    private ObservableList<Lager> listLager = FXCollections.observableArrayList();
    private Label lblAntalFlasker = new Label("Antal flasker: ");
    private Label lbl1 = new Label("Vælg destillat(er)");
    private Label lbl2 = new Label("Lav din whisky");
    private Label lbl3 = new Label("Gem din whisky");
    private Lager valgtLager;
    private int reol, hylde;
    private Exception manglendeOplysningerException = new Exception("Et eller flere felter er ikke udfyldt");

    public OpretWhiskyVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);

        setTitle("Opret whisky");
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

        lbl1.setTextFill(Color.BURLYWOOD);
        lbl2.setTextFill(Color.BURLYWOOD);
        lbl3.setTextFill(Color.BURLYWOOD);
        lblBeskrivelse.setTextFill(Color.BURLYWOOD);
        lblOpretWhisky.setTextFill(Color.BURLYWOOD);
        lblTilfoejTilFad.setTextFill(Color.BURLYWOOD);
        lblMaengdeILiter.setTextFill(Color.BURLYWOOD);
        lblNavn.setTextFill(Color.BURLYWOOD);
        lblAlcoholprocent.setTextFill(Color.BURLYWOOD);
        lblVandTilfoejet.setTextFill(Color.BURLYWOOD);
        lblAntalFlasker.setTextFill(Color.BURLYWOOD);


        pane.add(lbl1, 0, 0, 2, 1);
        pane.setHalignment(lbl1, HPos.CENTER);
        pane.add(lbl2, 2, 0, 2, 1);
        pane.setHalignment(lbl2, HPos.CENTER);
        pane.add(lbl3, 4, 0, 2, 1);
        pane.setHalignment(lbl3, HPos.CENTER);

        pane.add(cbDestillater, 0, 1, 2, 3);
        cbDestillater.setMaxWidth(175);
        cbDestillater.setMinHeight(70);
        cbDestillater.getItems().addAll(Controller.getDestillater());


        pane.add(lblMaengdeILiter, 0, 4);
        pane.add(txfMaengdeILiter, 1, 4);
        txfMaengdeILiter.setMaxWidth(75);
        pane.setHalignment(txfMaengdeILiter, HPos.RIGHT);

        pane.add(btnTilfoejVaeskeTilWhisky, 0, 5, 2, 1);
        pane.setHalignment(btnTilfoejVaeskeTilWhisky, HPos.LEFT);
        btnTilfoejVaeskeTilWhisky.setOnAction(event -> opretVaeskeTilWhiskyAction());

        pane.add(btnToemDestillat, 0, 7, 2, 1);
        pane.setHalignment(btnToemDestillat, HPos.LEFT);
        btnToemDestillat.setOnAction(event -> setToemDestilat());
        pane.add(txfToemDestillat, 0, 8, 2, 1);
        pane.setHalignment(txfToemDestillat, HPos.RIGHT);
        txfToemDestillat.setMaxWidth(175);


        pane.add(dpDato, 2, 1, 2, 1);
        dpDato.setMinWidth(175);
        pane.add(lvVaeskeTilWhisky, 2, 2, 2, 3);
        lvVaeskeTilWhisky.setMaxWidth(175);
        lvVaeskeTilWhisky.setMinHeight(70);
        pane.add(lblNavn, 2, 5);
        pane.add(txfNavn, 3, 5);
        txfNavn.setMaxWidth(75);
        pane.add(lblBeskrivelse, 2, 6);
        pane.add(txfBeskrivelse, 3, 6);
        txfBeskrivelse.setMaxWidth(75);
        pane.add(lblVandTilfoejet, 2, 7);
        pane.add(txfVandTilfoejet, 3, 7);
        txfVandTilfoejet.setMaxWidth(75);
        pane.add(lblAlcoholprocent, 2, 8);
        pane.add(txfAlkoholprocent, 3, 8);
        txfAlkoholprocent.setMaxWidth(75);


        pane.add(cbFlaskestr, 4, 1, 2, 1);
        cbFlaskestr.setMinWidth(175);
        cbFlaskestr.setValue("Vælg flaskestørrelse");
        cbFlaskestr.getItems().addAll("0.5", "0.7", "1.0");
        pane.setHalignment(cbFlaskestr, HPos.RIGHT);

        pane.add(lblAntalFlasker, 4, 2);
        pane.add(txfantalFlasker, 5, 2);
        txfantalFlasker.setMaxWidth(75);
        pane.setHalignment(txfantalFlasker, HPos.RIGHT);

        pane.add(cbLager, 4, 4, 2, 1);
        cbLager.setMinWidth(175);
        pane.setHalignment(cbLager, HPos.RIGHT);
        for (int i = 0; i < Controller.getLagre().size(); i++) {
            listLager.add(Controller.getLagre().get(i));
        }
        cbLager.setItems(listLager);
        cbLager.setValue(Controller.getLagre().get(0));
        pane.add(lvLedigeLagerPladser, 4, 5, 2, 3);
        lvLedigeLagerPladser.setMaxWidth(175);
        lvLedigeLagerPladser.setMinHeight(70);
        pane.setHalignment(lvLedigeLagerPladser, HPos.RIGHT);


        //christians temp lagerknapværk
        Button btnVaelgLager = new Button("Vælg lager");
        pane.add(btnVaelgLager, 0, 10);
        btnVaelgLager.setOnAction(e -> vaelgLager());

        pane.add(btnOpretWhisky, 4, 8, 2, 1);
        pane.setHalignment(btnOpretWhisky, HPos.RIGHT);
        btnOpretWhisky.setOnAction(event -> gemWhiskyAction());
    }

    private void vaelgLager() {
        LagerVindue lagerVindue = new LagerVindue(this);
        lagerVindue.showAndWait();
    }

    private void setToemDestilat() {
        this.toemDestilat = true;
        txfToemDestillat.setText("Du tømmer destillatet");
    }

    private void antalFlaskerForAtTappe() {
        double antalFlasker = 0;
        double meangdeLiter = 0;
        for (int i = 0; i < listVaeskeTilWhiskyAdded.size(); i++) {
            meangdeLiter += listVaeskeTilWhiskyAdded.get(i).getMaengde();
        }
        meangdeLiter += Double.parseDouble(txfVandTilfoejet.getText());
        double kapacitet = Double.parseDouble((cbFlaskestr.getSelectionModel().getSelectedItem()));
        antalFlasker = meangdeLiter / kapacitet;
        txfantalFlasker.setText("" + antalFlasker);
    }

    private void opretVaeskeTilWhiskyAction() {
        double maengdeWhisky = 0;
        Destillat destillat = null;
        try {
            if (txfMaengdeILiter.getText() == "" || cbDestillater.getValue() == null)
                throw new Exception(manglendeOplysningerException);
            maengdeWhisky = Double.parseDouble(txfMaengdeILiter.getText());
            destillat = cbDestillater.getValue();

            VaeskeTilWhisky vaeskeTilWhisky = destillat.opretVaeskeTilWhisky(maengdeWhisky);
            listVaeskeTilWhiskyAdded.add(vaeskeTilWhisky);


            listDestillater.addAll(Controller.getDestillater());
            lvVaeskeTilWhisky.setItems(listVaeskeTilWhiskyAdded);
            cbDestillater.setItems(listDestillater);
            if (toemDestilat == true) {
                destillat.saetAngelShare();
                toemDestilat = false;
                txfToemDestillat.clear();
            }
        } catch (Exception e) {
            StartVindue.fejlIOprettelseAlert(e.getMessage());
        }
    }

    private void gemWhiskyAction() {
        try {
            String beskrivelse = txfBeskrivelse.getText().trim();
            LocalDate localDate = dpDato.getValue();
            String navn = txfNavn.getText().trim();
            if (navn == "" || beskrivelse == "" || txfVandTilfoejet.getText().trim() == "" || txfAlkoholprocent.getText().trim() == "" || cbFlaskestr.getValue() == "")
                throw new NullPointerException("Udfyld alle felterne");
            double vandTilfoejet = 0;
            double alkoholprocent = 0;
            double flaskeStr = Double.parseDouble((cbFlaskestr.getSelectionModel().getSelectedItem()));
            vandTilfoejet = Double.parseDouble(txfVandTilfoejet.getText().trim());
            alkoholprocent = Double.parseDouble(txfAlkoholprocent.getText().trim());
            if(alkoholprocent>100 || alkoholprocent<40)
                throw new IllegalArgumentException("Fejl i alcoholsprocent, endten for høj eller for lav");
            Whisky whisky = Controller.opretWhisky(localDate, navn, beskrivelse, flaskeStr, vandTilfoejet, alkoholprocent);
            antalFlaskerForAtTappe();
        } catch (Exception e) {
            StartVindue.fejlIOprettelseAlert(e.getMessage());
        }
    }


    public void setValgtLager(Lager lager) {
        this.valgtLager = lager;
    }

    public void setValgtReolHylde(int reol, int hylde) {
        this.reol = reol;
        this.hylde = hylde;
    }
}
