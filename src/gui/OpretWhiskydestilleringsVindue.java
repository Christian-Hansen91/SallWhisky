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
    private Whiskydestillering whiskydestillering = null;
    private Medarbejder medarbejder;
    private StartVindue startVindue;

    public OpretWhiskydestilleringsVindue(String title, Stage owner, StartVindue startVindue, Medarbejder medarbejder) {
        this.startVindue = startVindue;
        this.medarbejder = medarbejder;
        this.initOwner(owner);

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

        lblHead.setTextFill(Color.BURLYWOOD);
        lblHeart.setTextFill(Color.BURLYWOOD);
        lblTail.setTextFill(Color.BURLYWOOD);
        lblAlkoholprocent.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblOverskrift.setTextFill(Color.BURLYWOOD);

        pane.add(lblOverskrift, 0, 0, 26, 1);
        pane.setHalignment(lblOverskrift, HPos.CENTER);

        pane.add(cbMaltning, 0, 1, 4, 1);
        cbMaltning.setMaxWidth(175);
        cbMaltning.getItems().setAll(Controller.getMaltninger());
        cbMaltning.setPromptText("Vælg maltning");
        cbMaltning.setVisibleRowCount(2);
        pane.add(dpStartdato, 0, 2, 2, 1);
        dpStartdato.setPromptText("Vælg startdato");
        pane.add(dpSlutdato, 0, 3, 2, 1);

        pane.add(lblHead, 0, 5);
        pane.add(txfHead, 1, 5);
        txfHead.setMaxWidth(75);
        pane.setHalignment(txfHead, HPos.RIGHT);
        pane.add(lblHeart, 0, 6);
        pane.add(txfHeart, 1, 6);
        txfHeart.setMaxWidth(75);
        pane.setHalignment(txfHeart, HPos.RIGHT);
        pane.add(lblTail, 0, 7);
        pane.add(txfTail, 1, 7);
        txfTail.setMaxWidth(75);
        pane.setHalignment(txfTail, HPos.RIGHT);

        pane.add(lblAlkoholprocent, 0, 8);
        pane.add(txfAlkoholprocent, 1, 8);
        txfAlkoholprocent.setMaxWidth(75);
        pane.setHalignment(txfAlkoholprocent, HPos.RIGHT);

        pane.add(lblKommentar, 22, 1, 2, 1);
        pane.add(txaKommentar, 22, 2, 2, 6);
        txaKommentar.setMaxWidth(150);
        txaKommentar.setMaxHeight(250);
        pane.setHalignment(txaKommentar, HPos.RIGHT);

        pane.add(btnGem, 22, 8, 2, 1);
        pane.add(btnAnnuller, 22, 8, 2, 1);
        pane.setHalignment(btnGem, HPos.CENTER);
        btnGem.setOnAction(event -> gemAction());
        pane.setHalignment(btnAnnuller, HPos.RIGHT);
        btnAnnuller.setOnAction(event -> annullerAction());
    }

    private void annullerAction() {
        this.hide();
    }

    private void gemAction() {
        LocalDate startdato = dpStartdato.getValue();
        if(startdato==null)
            StartVindue.fejlIOprettelseAlert("Husk at vælge en startdato");
        if(cbMaltning.getSelectionModel().getSelectedItem()==null)
            StartVindue.fejlIOprettelseAlert("Husk at tilføje en maltning");
        LocalDate slutdato = dpSlutdato.getValue();
        double head = 0;
        double heart = 0;
        double tail = 0;
        double alkoholprocent = 0;
        try {
            head = Double.parseDouble(txfHead.getText().trim());
            heart = Double.parseDouble(txfHeart.getText().trim());
            tail = Double.parseDouble(txfTail.getText().trim());
            alkoholprocent = Double.parseDouble(txfAlkoholprocent.getText().trim());
        } catch (NumberFormatException e) {
            StartVindue.kommafejlAlert();
        }
        Maltning maltning = cbMaltning.getSelectionModel().getSelectedItem();
        String kommentar = txaKommentar.getText().trim();
        medarbejder = startVindue.getMedarbejder();

        if (!startdato.isAfter(slutdato) && !slutdato.isBefore(startdato) && !(head == 0) && !(heart == 0) && !(tail == 0) && !(alkoholprocent == 0)) {
            whiskydestillering = Controller.opretWhiskydestillering(maltning, startdato, slutdato, head, heart, tail, kommentar, alkoholprocent, medarbejder);

            txfHead.clear();
            txfHeart.clear();
            txfTail.clear();
            txaKommentar.clear();
            txfAlkoholprocent.clear();

            this.hide();
            StartVindue.succesIOprettelseAlert();
        } else {
            StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette whiskydestilleringen.");
            String medarbejder1 = medarbejder.getNavn().trim();
            try {
                if (!startdato.isAfter(slutdato) && !slutdato.isBefore(startdato) && !(head == 0) && !(heart == 0) && !(tail == 0) && !(alkoholprocent == 0)) {
                    whiskydestillering = Controller.opretWhiskydestillering(maltning, startdato, slutdato, head, heart, tail, kommentar, alkoholprocent, medarbejder);
                    this.hide();
                    StartVindue.succesIOprettelseAlert();
                } else {
                    StartVindue.fejlIOprettelseAlert("Der mangler noget information for at oprette whiskydestilleringen.");
                }
            } catch (NullPointerException e) {
                StartVindue.fejlIOprettelseAlert("Udfyld alle felter for at oprette en destillering");
            }
        }
    }
}
