package gui;

import controller.Controller;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.application.Whisky;


public class WhiskySoegningPane extends SoegningPane {
    private ListView<Whisky> lvResultater;
    private TextField txfId, txfBeskrivelse, txfFad;
    private Label lblFejl;
    public WhiskySoegningPane() {
        super();
    }

    public void initContent(GridPane gridPane) {
        add(new Label("Indtast de parametre, du vil søge på:"), 0, 0);
        add(new Label("Id:"),0,1);
        txfId = new TextField();
        add(txfId, 1, 1);
        add(new Label("Beskrivelse:"),0,2);
        txfBeskrivelse = new TextField();
        add(txfBeskrivelse,1,2);
        add(new Label("Fad:"),0,3);
        txfFad = new TextField();
        add(txfFad,1,3);

        add(new Label("Resultater"),3,0);
        lvResultater = new ListView<>();
        add(lvResultater,3,1,4,8);
        lvResultater.setPrefWidth(250);
        lvResultater.setPrefHeight(250);
        lblFejl = new Label("");
        add(lblFejl,3,10);
        Button btnSoeg = new Button("Søg");
        add(btnSoeg,7,10);
        btnSoeg.setOnAction(e -> soegAction());

    }



    private void soegAction() {
        lvResultater.getItems().clear();
        lvResultater.getItems().addAll(Controller.soegWhiskyBeskrivelse(txfBeskrivelse.getText()));
        lvResultater.getItems().addAll(Controller.soegWhiskyId(saniterInputId()));
        //fad mv.
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
