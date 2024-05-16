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
import model.application.Maltning;
import model.application.Medarbejder;
import model.application.Whiskydestillering;
import storage.Storage;

import java.time.LocalDate;
import java.util.List;

public class OpretWhiskydestilleringsVindue extends Stage {
    private DatePicker dpStartdato = new DatePicker();
    private DatePicker dpSlutdato = new DatePicker(LocalDate.now());
    private Label lblVaeskeMaengde = new Label("Væskemængde: ");
    private TextField txfVaeskeMaengde = new TextField();
    private Label lblHead = new Label("Head: ");
    private TextField txfHead = new TextField();
    private Label lblHeart = new Label("Heart: ");
    private TextField txfHeart = new TextField();
    private Label lblTail = new Label("Tail: ");
    private TextField txfTail = new TextField();
    private Label lblAlkoholprocent = new Label("Alkoholprocent: ");
    private TextField txfAlkoholprocent = new TextField();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextArea txaKommentar = new TextArea();
    private ComboBox<Maltning> cbMaltning = new ComboBox<>();
    private Label lblOverskrift = new Label("Opret whiskydestillering");
    private Button btnGem = new Button("Gem");
    private Button btnAnnuller = new Button("Anuller");
    private Maltning maltning = null;
    private Whiskydestillering whiskydestillering = null;
    private Medarbejder medarbejder;

    public OpretWhiskydestilleringsVindue(String title, Stage owner, StartVindue startVindue, Medarbejder medarbejder) {
        this.initOwner(owner);

        this.medarbejder = medarbejder;
        setTitle("Opret Whiskydestillering");
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

        lblVaeskeMaengde.setTextFill(Color.BURLYWOOD);
        lblHead.setTextFill(Color.BURLYWOOD);
        lblHeart.setTextFill(Color.BURLYWOOD);
        lblTail.setTextFill(Color.BURLYWOOD);
        lblVaeskeMaengde.setTextFill(Color.BURLYWOOD);
        lblAlkoholprocent.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblOverskrift.setTextFill(Color.BURLYWOOD);

        pane.add(lblOverskrift, 0, 0, 26, 1);
        pane.setHalignment(lblOverskrift, HPos.CENTER);

        pane.add(cbMaltning, 0, 1, 4, 1);
        cbMaltning.setMaxWidth(175);
        cbMaltning.getItems().setAll(Storage.getMaltninger());

        pane.add(dpStartdato, 0, 3, 2, 1);
        pane.add(dpSlutdato, 0, 4, 2, 1);

        pane.add(lblVaeskeMaengde, 0, 5);
        pane.add(txfVaeskeMaengde, 1, 5);
        txfVaeskeMaengde.setMaxWidth(75);
        pane.setHalignment(txfVaeskeMaengde, HPos.RIGHT);
        pane.add(lblHead, 0, 6);
        pane.add(txfHead, 1, 6);
        txfHead.setMaxWidth(75);
        pane.setHalignment(txfHead, HPos.RIGHT);
        pane.add(lblHeart, 0, 7);
        pane.add(txfHeart, 1, 7);
        txfHeart.setMaxWidth(75);
        pane.setHalignment(txfHeart, HPos.RIGHT);
        pane.add(lblTail, 0, 8);
        pane.add(txfTail, 1, 8);
        txfTail.setMaxWidth(75);
        pane.setHalignment(txfTail, HPos.RIGHT);

        pane.add(lblAlkoholprocent, 0, 9);
        pane.add(txfAlkoholprocent, 1, 9);
        txfAlkoholprocent.setMaxWidth(75);
        pane.setHalignment(txfAlkoholprocent, HPos.RIGHT);

        pane.add(lblKommentar, 22, 1, 2, 1);
        pane.add(txaKommentar, 22, 2, 2, 7);
        txaKommentar.setMaxWidth(150);
        txaKommentar.setMaxHeight(250);
        pane.setHalignment(txaKommentar, HPos.RIGHT);

        pane.add(btnGem, 22, 9, 2, 1);
        pane.add(btnAnnuller, 22, 9, 2, 1);
        pane.setHalignment(btnGem, HPos.CENTER);
        btnGem.setOnAction(event -> gemAction());
        pane.setHalignment(btnAnnuller, HPos.RIGHT);
    }

    private void gemAction() {
        LocalDate startdato = dpStartdato.getValue();
        LocalDate slutdato = dpSlutdato.getValue();
        int maengdeVaeske = Integer.parseInt(txfVaeskeMaengde.getText().trim());
        double head = Double.parseDouble(txfHead.getText().trim());
        double heart = Double.parseDouble(txfHeart.getText().trim());
        double tail = Double.parseDouble(txfTail.getText().trim());
        double alkoholprocent = Double.parseDouble(txfAlkoholprocent.getText().trim());
        Maltning maltning = cbMaltning.getSelectionModel().getSelectedItem();
        String kommentar = txaKommentar.getText().trim();
        String medarbejder1 = medarbejder.getNavn().trim();

        if (!startdato.isAfter(slutdato) && !slutdato.isBefore(startdato) && !(maengdeVaeske == 0) && !(head == 0) && !(heart == 0) && !(tail == 0)&& !(alkoholprocent == 0)) {
            whiskydestillering = Controller.opretWhiskydestillering(maltning, startdato, slutdato, maengdeVaeske, head, heart, tail, kommentar, alkoholprocent, medarbejder);

            txfHead.clear();
            txfHeart.clear();
            txfTail.clear();
            txfVaeskeMaengde.clear();
            txaKommentar.clear();
            txfAlkoholprocent.clear();

            Controller.addWhiskydestillering(whiskydestillering);
            this.hide();
            StartVindue.succesIOprettelseAlert();
        } else {
            StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette whiskydestilleringen.");
        }
    }
}