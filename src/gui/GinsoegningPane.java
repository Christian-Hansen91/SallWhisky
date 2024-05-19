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
import model.application.Destillat;
import model.application.Gindestillering;

public class GinsoegningPane extends SoegningPane{
    private ListView<Gindestillering> lvResultater;
    private TextField txfId, txfKommentar, txfFad;
    private Label lblFejl;
    GinsoegningPane() {
        super();
    }
    @Override
    void initContent(GridPane gridPane) {
        Label lblOverskrift = new Label("Indtast de parametre, du vil søge på:");
        Label lblId = new Label("ID: ");
        Label lblKommentar = new Label("Kommentar: ");
        Label lblFad = new Label("Fad: ");
        Label lblResultater = new Label("Resultater");
        lblOverskrift.setTextFill(Color.BURLYWOOD);
        lblId.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblFad.setTextFill(Color.BURLYWOOD);
        lblResultater.setTextFill(Color.BURLYWOOD);
        add(new Label("                           "),2, 0, 2, 1);

        add(lblOverskrift, 0, 0, 2, 2);
        setValignment(lblOverskrift, VPos.CENTER);
        add(lblId, 0, 3);
        txfId = new TextField();
        add(txfId, 1, 3);
        add(lblKommentar, 0, 4);
        txfKommentar = new TextField();
        add(txfKommentar, 1, 4);


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
        btnSoeg.setOnAction(e -> soegningAction());
    }

    private void soegningAction() {
        lvResultater.getItems().setAll(Controller.getGindestilleringer());
    }
}
