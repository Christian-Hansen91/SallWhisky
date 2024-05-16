package gui;

import controller.Controller;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    private TextField txfId, txfKommentar, txfFad;
    private Label lblFejl;
    private CheckBox chkTreAar;

    DestillatSoegningPane() {
        super();
    }

    @Override
    void initContent(GridPane gridPane) {
        add(new Label("Indtast de parametre, du vil søge på:"), 0, 0);
        add(new Label("Id:"), 0, 1);
        txfId = new TextField();
        add(txfId, 1, 1);
        add(new Label("Kommentar:"), 0, 2);
        txfKommentar = new TextField();
        add(txfKommentar, 1, 2);
        add(new Label("Fad:"), 0, 3);
        txfFad = new TextField();
        add(txfFad, 1, 3);

        add(new Label("Resultater"), 3, 0);
        lvResultater = new ListView<>();
        add(lvResultater, 3, 1, 4, 8);
        lvResultater.setPrefWidth(250);
        lvResultater.setPrefHeight(250);
        lblFejl = new Label("");
        add(lblFejl, 3, 10);
        Button btnSoeg = new Button("Søg");
        add(btnSoeg, 7, 10);
        btnSoeg.setOnAction(e -> soegningAction());

        chkTreAar = new CheckBox("Lagret mindst tre år:");
        add(chkTreAar, 7, 11);
        chkTreAar.setOnAction(e -> soegningAction());
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
