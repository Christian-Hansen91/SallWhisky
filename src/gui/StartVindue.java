package gui;

import controller.Controller;
import javafx.application.Application;
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
import model.application.Medarbejder;

public class StartVindue extends Application {
    private OpretMedarbejderVindue opretMedarbejderVindue;
    private LogIndVindue logIndVindue;
    private OpretMaltningVindue opretMaltningVindue;
    private OpretLagerVindue opretLagerVindue;
    private OpretFadVindue opretFadVindue;
    private OpretDestillatVindue opretDestillatVindue;
    private OpretWhiskydestilleringsVindue opretWhiskydestilleringsVindue;
    private OpretWhiskyVindue opretWhiskyVindue;
    private SoegningsVindue soegningsVindue;
    private Button btnOpretMaltning = new Button("Opret maltning");
    private Button btnOpretWhiskyDestillering = new Button("Opret whiskydestillering");
    private Button btnOpretGinDestillering = new Button("Opret gindestillering");
    private Button btnOpretDestilleringsTapning = new Button("Opret destillat (til fad)");
    private Button btnOpretOmhaeldning = new Button("Opret omhældning");
    private Button btnOpretWhiskyTapning = new Button("Opret whisky (tapning)");
    private Button btnOpretLager = new Button("Opret lager");
    private Button btnOpretFad = new Button("Opret fad");
    private Button btnOpretMedarbejder = new Button("Opret medarbejder");
    private Button btnLogInd = new Button("Log ind");
    private TextField txfMedarbejder = new TextField();
    private Medarbejder medarbejder = null;
    private Label lblMedarbejder = new Label("Medarbejder: ");
    private Stage stage;
    private Button btnSoeg = new Button("Søg");

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Sall Whisky Distillery");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setPrefHeight(315);
        pane.setPrefWidth(600);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setStyle("-fx-background-image: url('https://migogaarhus.dk/wp-content/uploads/2021/01/Sall-whisky.jpg')");

        Label lblVelkommen = new Label("     Velkommen til Sall Whisky Distillery    ");
        pane.setHalignment(lblVelkommen, HPos.CENTER);
        lblVelkommen.setTextFill(Color.BURLYWOOD);
        pane.add(lblVelkommen, 2, 0, 2, 1);

        pane.add(lblMedarbejder, 0, 1, 2, 1);
        lblMedarbejder.setTextFill(Color.BURLYWOOD);
        pane.add(btnSoeg, 5, 1);
        pane.setHalignment(btnSoeg, HPos.RIGHT);

        pane.add(btnLogInd, 0, 0, 2, 1);
        pane.setHalignment(btnLogInd, HPos.LEFT);
        pane.add(btnOpretMedarbejder, 4, 0, 2, 1);
        pane.setHalignment(btnOpretMedarbejder, HPos.RIGHT);

        btnOpretWhiskyDestillering.setMinWidth(160);
        btnOpretGinDestillering.setMinWidth(160);
        btnOpretMaltning.setMinWidth(160);
        btnOpretLager.setMinWidth(160);
        btnOpretFad.setMinWidth(160);
        btnOpretDestilleringsTapning.setMinWidth(160);
        btnOpretWhiskyTapning.setMinWidth(160);
        btnOpretOmhaeldning.setMinWidth(160);

        pane.add(btnOpretWhiskyDestillering, 0, 4, 2, 1);
        pane.setHalignment(btnOpretWhiskyDestillering, HPos.LEFT);
        pane.add(btnOpretGinDestillering, 4, 4, 2, 1);
        pane.setHalignment(btnOpretGinDestillering, HPos.RIGHT);
        pane.add(btnOpretMaltning, 0, 5, 2, 1);
        pane.setHalignment(btnOpretMaltning, HPos.LEFT);
        pane.add(btnOpretFad, 4, 5, 2, 1);
        pane.setHalignment(btnOpretFad, HPos.RIGHT);

        pane.add(btnOpretLager, 0, 6, 2, 1);
        pane.setHalignment(btnOpretLager, HPos.LEFT);
        pane.add(btnOpretOmhaeldning, 4, 6, 2, 1);
        pane.setHalignment(btnOpretOmhaeldning, HPos.RIGHT);
        pane.add(btnOpretDestilleringsTapning, 0, 7, 2, 1);
        pane.setHalignment(btnOpretDestilleringsTapning, HPos.LEFT);
        pane.add(btnOpretWhiskyTapning, 4, 7, 2, 1);
        pane.setHalignment(btnOpretWhiskyTapning, HPos.RIGHT);

        btnLogInd.setOnAction(event -> logIndAction());
        btnOpretMedarbejder.setOnAction(event -> opretMedarbejderAction());
        btnOpretMaltning.setOnAction(event -> opretMaltningAction());
        btnOpretLager.setOnAction(event -> opretLagerAction());
        btnOpretFad.setOnAction(event -> opretFadAction());
        btnOpretDestilleringsTapning.setOnAction(event -> opretDestilleringsTapningAction());
        btnOpretWhiskyDestillering.setOnAction(event -> opretWhiskydestilleringsAction());
        btnSoeg.setOnAction(event -> soegningAction());
        btnOpretWhiskyTapning.setOnAction(event -> opretWhiskyTapningAction());
        opdaterKnapper();
    }

    private void soegningAction() {
        soegningsVindue = new SoegningsVindue("Søgning",stage);
        soegningsVindue.showAndWait();
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    private void txfMedarbejderAction() {
        txfMedarbejder.setText(medarbejder.getNavn());
    }

    private void logIndAction() {
        logIndVindue = new LogIndVindue("Log ind", stage, this);
        logIndVindue.showAndWait();
        opdaterKnapper();
        opdaterMedarbejderLabel();
    }

    private void opdaterKnapper() {
            boolean loggedIn = medarbejder == null;
            btnOpretFad.setDisable(loggedIn);
            btnOpretMaltning.setDisable(loggedIn);
            btnOpretDestilleringsTapning.setDisable(loggedIn);
            btnOpretOmhaeldning.setDisable(loggedIn);
            btnOpretWhiskyDestillering.setDisable(loggedIn);
            btnOpretWhiskyTapning.setDisable(loggedIn);
            btnOpretGinDestillering.setDisable(loggedIn);
            btnOpretLager.setDisable(loggedIn);
            btnSoeg.setDisable(loggedIn);
    }

    private void opretMedarbejderAction() {
        opretMedarbejderVindue = new OpretMedarbejderVindue("Opret medarbejder", stage, this);
        opretMedarbejderVindue.showAndWait();
        opdaterMedarbejderLabel();
    }

    private void opretFadAction() {
        opretFadVindue = new OpretFadVindue("Opret fad", stage,this);
        opretFadVindue.showAndWait();
    }

    private void opretMaltningAction() {
        opretMaltningVindue = new OpretMaltningVindue("Opret maltning", stage, this);
        opretMaltningVindue.showAndWait();
    }

    private void opretLagerAction() {
        opretLagerVindue = new OpretLagerVindue("Opret lager", stage, this);
        opretLagerVindue.showAndWait();
    }

    private void opretWhiskydestilleringsAction() {
        opretWhiskydestilleringsVindue = new OpretWhiskydestilleringsVindue("Opret whiskydestillering", stage, this, medarbejder);
        opretWhiskydestilleringsVindue.showAndWait();
    }

    private void opretDestilleringsTapningAction() {
        opretDestillatVindue = new OpretDestillatVindue("Opret destilleringstapning", stage, this);
        opretDestillatVindue.showAndWait();
    }
    private void opretWhiskyTapningAction() {
        opretWhiskyVindue = new OpretWhiskyVindue("Opret whiskytapning", stage, this);
        opretWhiskyVindue.showAndWait();
    }
    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }
    public void opdaterMedarbejderLabel() {
        if (!(medarbejder == null)) {
            lblMedarbejder.setText("Medarbejder: " + medarbejder.getNavn());
        }
    }

    public static void fejlIOprettelseAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl i oprettelse");
        alert.setHeaderText("Manglende information");
        alert.setContentText(text);
        alert.show();
    }

    public static void kommafejlAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl i oprettelse");
        alert.setHeaderText("Hov!");
        alert.setContentText("Kommafejl. Skriv \".\" i stedet for \",\" ved talmængder.");
        alert.show();
    }

    public static void succesIOprettelseAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oprettelse gennemført");
        alert.setHeaderText("SUCCES!");
        alert.setContentText("Din oprettelse er gennemført og gemt.");
        alert.show();
    }
}