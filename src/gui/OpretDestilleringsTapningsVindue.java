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
import model.application.Whiskydestillering;
import model.application.Fad;
import model.application.Tapning;
import storage.Storage;

import java.time.LocalDate;
import java.util.List;

public class OpretDestilleringsTapningsVindue extends Stage {
    private Label lblId = new Label("ID: ");
    private DatePicker dato = new DatePicker(LocalDate.now());
    private ComboBox<Whiskydestillering> cbDestilleringer = new ComboBox<>();
    private ComboBox<Fad> cbFade = new ComboBox<>();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextField txfKommentar = new TextField();
    private Label lblDestillat = new Label("Destillat");
    private TextArea txaDestillat = new TextArea();
    private Button btnOpretTapning = new Button("Opret tapning til destillat");
    private Label lblTapning = new Label("Tapning ");
    private Button btnOpretDestillat = new Button("Opret destillat");
    private Label lblTilfoejTilFad = new Label("Tilføj til fad:");
    private Label lblMaengdeILiter = new Label("Mængde (L): ");
    private TextField txfMaengdeILiter = new TextField();
    private TextField txfId = new TextField();
    private Destillat destillat = null;
    private Tapning tapning = null;
    private Whiskydestillering whiskydestillering = null;

    public OpretDestilleringsTapningsVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);

        setTitle("Opret destilleringstapning");
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

        lblId.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblDestillat.setTextFill(Color.BURLYWOOD);
        lblTapning.setTextFill(Color.BURLYWOOD);
        lblTilfoejTilFad.setTextFill(Color.BURLYWOOD);
        lblMaengdeILiter.setTextFill(Color.BURLYWOOD);

        pane.add(lblId, 15, 2, 2, 1);
        pane.add(txfId, 15, 2, 2, 1);
        txfId.setMaxWidth(175);
        pane.setHalignment(txfId, HPos.RIGHT);

        pane.add(lblTapning, 0, 1);
        pane.setHalignment(lblTapning, HPos.CENTER);

        pane.add(dato, 0, 2);

        pane.add(cbDestilleringer, 0, 3, 2, 1);
        cbDestilleringer.setMaxWidth(200);
        cbDestilleringer.getItems().addAll(Controller.getWhiskydestilleringer());

        pane.add(lblMaengdeILiter, 0, 4);
        pane.add(txfMaengdeILiter, 0, 5);
        pane.add(lblKommentar, 0, 6);
        pane.add(txfKommentar, 0, 7);
        txfKommentar.setMaxWidth(200);

        pane.add(btnOpretTapning, 0, 10);
        pane.setHalignment(btnOpretTapning, HPos.RIGHT);
        btnOpretTapning.setOnAction(event -> opretTapningTilDestillatAction());

        pane.add(lblDestillat, 15, 1, 2, 1);
        pane.setHalignment(lblDestillat, HPos.CENTER);
        pane.add(txaDestillat, 15, 3, 2, 5);
        pane.setHalignment(txaDestillat, HPos.CENTER);
        txaDestillat.setMaxWidth(200);
        txaDestillat.setMinHeight(100);

        pane.add(lblTilfoejTilFad, 15, 8, 2, 1);
        pane.setHalignment(lblTilfoejTilFad, HPos.RIGHT);
        pane.add(cbFade, 15, 9, 2, 1);
        cbFade.setMaxWidth(200);
        cbFade.getItems().addAll(Controller.getFade());

        pane.add(btnOpretDestillat, 15, 10, 2, 1);
        pane.setHalignment(btnOpretDestillat, HPos.RIGHT);
        btnOpretDestillat.setOnAction(event -> gemDestillatAction());
    }

    private void opretTapningTilDestillatAction() {
        LocalDate dagsdato = dato.getValue();
        String maengde = txfMaengdeILiter.getText().trim();
        String kommentar = txfKommentar.getText().trim();

        tapning = whiskydestillering.opretTapning(destillat, Double.parseDouble(maengde), kommentar);
        destillat.tilfoejTapning(tapning);

        txaDestillat.setText(tapning.toString());
    }

    private void gemDestillatAction() {
        LocalDate dato1 = dato.getValue();
        String kommentar = txfKommentar.getText().trim();
        Fad fad = cbFade.getSelectionModel().getSelectedItem();

        destillat = whiskydestillering.opretDestillat(fad);
    }
}
