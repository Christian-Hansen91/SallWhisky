package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.application.Lagerenhed;
import model.application.Medarbejder;

public class OpretLagerVindue extends Stage {
    private Label lblId = new Label("ID: ");
    private Label lblReol = new Label("Antal reoler: ");
    private Label lblHylde = new Label("Antal hylder: ");
    private Label lblNavn = new Label("Navn: ");
    private TextField txfId = new TextField();
    private TextField txtReoler = new TextField();
    private TextField txtHylder = new TextField();
    private TextField txtNavn = new TextField();
    private Button btnOpretLager = new Button("Opret lager");
    private Medarbejder medarbejder;
    private StartVindue startVindue;
    private Button btnAnnuller = new Button("Anuller");


    public OpretLagerVindue(String title, Stage owner, StartVindue startVindue) {
        this.startVindue = startVindue;
        this.initOwner(owner);

        setTitle("Opret lager");
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

        lblHylde.setTextFill(Color.BURLYWOOD);
        lblReol.setTextFill(Color.BURLYWOOD);
        lblNavn.setTextFill(Color.BURLYWOOD);

        pane.add(lblNavn, 0,2,2,1);
        pane.add(txtNavn, 2,2,2,1);
        pane.add(lblReol, 0,3,2,1);
        pane.add(txtReoler, 2,3,2,1);
        pane.add(lblHylde, 0,4,2,1);
        pane.add(txtHylder, 2,4,2,1);
        pane.add(btnOpretLager, 0, 5, 2, 1);
        btnOpretLager.setOnAction(event -> opretLager());

        txfId.setMaxWidth(175);
        pane.add(btnAnnuller,2,5);
        btnAnnuller.setOnAction(e -> annullerAction());
        pane.setHalignment(txfId, HPos.RIGHT);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);

    }
    public void opretLager(){
        try {
            String navn = txtNavn.getText();
            if(navn=="")
                throw new IllegalArgumentException("Husk at give dit lager et navn");
            int reoler = Integer.parseInt(txtReoler.getText());
            int hylder = Integer.parseInt(txtHylder.getText());
            medarbejder = startVindue.getMedarbejder();

            Controller.opretLager(navn, new Lagerenhed[reoler][hylder], medarbejder);
            this.hide();
            StartVindue.succesIOprettelseAlert();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText("Angiv antal reoler og hylder som hele tal");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
    private void annullerAction() {
        this.hide();
    }
}
