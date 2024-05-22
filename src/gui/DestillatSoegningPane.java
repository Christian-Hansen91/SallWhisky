package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.application.Destillat;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DestillatSoegningPane extends SoegningPane {
    private ListView<Destillat> lvResultater;
    private TextField txfId, txfKommentar;
    private Label lblFejl;
    private CheckBox chkTreAar;

    DestillatSoegningPane() {
        super();
    }

    @Override
    void initContent(GridPane gridPane) {
        Label lblOverskrift = new Label("Indtast de parametre, du vil søge på:");
        Label lblId = new Label("ID: ");
        Label lblKommentar = new Label("Kommentar: ");
        Label lblResultater = new Label("Resultater");
        lblOverskrift.setTextFill(Color.BURLYWOOD);
        lblId.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
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

        Button btnAlle = new Button("Vis alle");
        add(btnAlle, 1, 12);
        btnAlle.setOnAction(e -> findAlle());
        setHalignment(btnAlle, HPos.RIGHT);

        chkTreAar = new CheckBox("Lagret mindst tre år:");
        chkTreAar.setTextFill(Color.BURLYWOOD);
        add(chkTreAar, 0, 11, 2, 1);
        chkTreAar.setOnAction(e -> soegningAction());
    }

    private void findAlle() {
        lvResultater.getItems().clear();
        lvResultater.getItems().setAll(Controller.getDestillater());
    }

    private void soegningAction() {
        lvResultater.getItems().clear();
        Set<Destillat> destillatSet = new HashSet<Destillat>();
        destillatSet.addAll(Controller.soegDestillatKommentar(txfKommentar.getText()));
        destillatSet.addAll(Controller.soegDestillatId(saniterInputId()));
        ArrayList<Destillat> destillater = new ArrayList<>(destillatSet);
        lvResultater.getItems().addAll(destillater);
        if (chkTreAar.isSelected()) {
            List<Destillat> temp = lvResultater.getItems();
            System.out.println(temp.size());
            lvResultater.getItems().setAll(Controller.fjernUnderTre(temp));
        }
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
