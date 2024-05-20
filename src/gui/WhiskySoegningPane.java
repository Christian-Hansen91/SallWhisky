package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.application.Destillat;
import model.application.Whisky;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class WhiskySoegningPane extends SoegningPane {
    private ListView<Whisky> lvResultater;
    private TextField txfId, txfBeskrivelse, txfFad;
    private Label lblFejl;

    public WhiskySoegningPane() {
        super();
    }

    public void initContent(GridPane gridPane) {
        Label lblOverskrift = new Label("Indtast de parametre, du vil søge på:");
        Label lblId = new Label("ID: ");
        Label lblBeskrivelse = new Label("Beskrivelse: ");
        Label lblFad = new Label("Fad: ");
        Label lblResultater = new Label("Resultater");
        lblOverskrift.setTextFill(Color.BURLYWOOD);
        lblId.setTextFill(Color.BURLYWOOD);
        lblBeskrivelse.setTextFill(Color.BURLYWOOD);
        lblFad.setTextFill(Color.BURLYWOOD);
        lblResultater.setTextFill(Color.BURLYWOOD);

        add(new Label("                           "),2, 0, 2, 1);

        add(lblOverskrift, 0, 0, 2, 2);
        setValignment(lblOverskrift, VPos.CENTER);
        add(lblId, 0, 3);
        txfId = new TextField();
        add(txfId, 1, 3);
        add(lblBeskrivelse, 0, 4);
        txfBeskrivelse = new TextField();
        add(txfBeskrivelse, 1, 4);
        add(lblFad, 0, 5);
        txfFad = new TextField();
        add(txfFad, 1, 5);

        add(lblResultater, 4, 0, 1, 2);
        setValignment(lblResultater, VPos.CENTER);
        lvResultater = new ListView<>();
        add(lvResultater, 4, 3, 4, 9);
        lvResultater.setPrefWidth(220);
        lvResultater.setPrefHeight(200);
        lblFejl = new Label("");
        add(lblFejl, 4, 11);

        Button btnSoeg = new Button("Søg");
        add(btnSoeg, 1, 11);
        setHalignment(btnSoeg, HPos.RIGHT);
        btnSoeg.setOnAction(e -> soegAction());
    }
    private void soegAction() {
        lvResultater.getItems().clear();
        lvResultater.getItems().addAll(Controller.soegWhiskyBeskrivelse(txfBeskrivelse.getText()));
        lvResultater.getItems().addAll(Controller.soegWhiskyId(saniterInputId()));

        lvResultater.getItems().clear();
        Set<Whisky> whiskySet = new HashSet<Whisky>();
        whiskySet.addAll(Controller.soegWhiskyBeskrivelse(txfBeskrivelse.getText()));
        whiskySet.addAll(Controller.soegWhiskyId(saniterInputId()));
        ArrayList<Whisky> whiskyer = new ArrayList<>(whiskySet);
        lvResultater.getItems().addAll(whiskyer);
    }

    private int saniterInputId() {
        int id = 0;
        if (!txfId.getText().isEmpty()) {
            try {
                id = Integer.parseInt(txfId.getText());
            } catch (NumberFormatException e) {
                lblFejl.setText("Du må kun taste heltal i id-feltet");
            }
        }
        return id;
    }
}
