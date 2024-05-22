package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.application.Fad;
import model.application.Whisky;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FadSoegningPane extends SoegningPane {
    private ListView<Fad> lvResultater;
    private TextField txfId, txfFadtype;
    private Label lblFejl;

    public FadSoegningPane() {
        super();
    }

    @Override
    void initContent(GridPane gridPane) {
        Label label = new Label("fadpane");
        add(label, 0, 0);

        Label lblOverskrift = new Label("Indtast de parametre, du vil søge på:");
        Label lblId = new Label("ID: ");
        Label lblFadtype = new Label("Fadtype: ");
        Label lblResultater = new Label("Resultater");
        lblOverskrift.setTextFill(Color.BURLYWOOD);
        lblId.setTextFill(Color.BURLYWOOD);
        lblFadtype.setTextFill(Color.BURLYWOOD);
        lblResultater.setTextFill(Color.BURLYWOOD);

        add(new Label("                           "), 2, 0, 2, 1);

        add(lblOverskrift, 0, 0, 2, 2);
        setValignment(lblOverskrift, VPos.CENTER);
        add(lblId, 0, 3);
        txfId = new TextField();
        add(txfId, 1, 3);
        add(lblFadtype, 0, 4);
        txfFadtype = new TextField();
        add(txfFadtype, 1, 4);

        add(lblResultater, 4, 0, 1, 2);
        setValignment(lblResultater, VPos.CENTER);
        lvResultater = new ListView<>();
        add(lvResultater, 4, 3, 4, 11);
        lvResultater.setPrefWidth(220);
        lvResultater.setPrefHeight(200);
        lblFejl = new Label("");
        add(lblFejl, 4, 11);

        Button btnSoeg = new Button("Søg");
        add(btnSoeg, 1, 11);
        setHalignment(btnSoeg, HPos.RIGHT);
        btnSoeg.setOnAction(e -> soegAction());

        Button btnAlle = new Button("Vis alle");
        add(btnAlle,1,12);
        btnAlle.setOnAction(e -> findAlle());
        setHalignment(btnAlle,HPos.RIGHT);
    }

    private void findAlle() {
        lvResultater.getItems().clear();
        lvResultater.getItems().setAll(Controller.getFade());
    }

    private void soegAction() {
        lvResultater.getItems().clear();
        Set<Fad> fadSet = new HashSet<Fad>();
        fadSet.addAll(Controller.soegFadtype(txfFadtype.getText()));
        fadSet.addAll(Controller.soegFadId(saniterInputId()));
        ArrayList<Fad> fade = new ArrayList<>(fadSet);
        lvResultater.getItems().addAll(fade);
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
