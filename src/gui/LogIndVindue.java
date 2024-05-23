package gui;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.application.Medarbejder;

public class LogIndVindue extends Stage {
    private ComboBox<Medarbejder> cbVaelgMedarbejder = new ComboBox<>();
    private Label lblvaelg = new Label("Vælg en medarbejder på listen for at logge ind: ");
    private TextField txfId = new TextField();
    private Button btnLogInd = new Button("Log ind");
    private Button btnAnnuller = new Button("Anuller");
    private StartVindue startVindue = null;
    private Medarbejder medarbejder = null;

    public LogIndVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);
        this.startVindue = startVindue;

        setTitle("Log ind");
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

        lblvaelg.setTextFill(Color.BURLYWOOD);


        pane.add(lblvaelg, 0, 2, 15, 1);

        pane.add(cbVaelgMedarbejder, 0, 3, 15, 1);
        cbVaelgMedarbejder.setMaxWidth(160);
        cbVaelgMedarbejder.getItems().setAll(Controller.getMedarbejdere());

        pane.add(btnLogInd, 29, 16);
        pane.add(btnAnnuller, 30, 16);
        pane.setHalignment(btnLogInd, HPos.RIGHT);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);
        btnAnnuller.setOnAction(e -> annullerAction());

        btnLogInd.setOnAction(event -> logIndAction());
    }

    private void logIndAction() {
        medarbejder = cbVaelgMedarbejder.getSelectionModel().getSelectedItem();

        if (!cbVaelgMedarbejder.getSelectionModel().isEmpty()) {
            cbVaelgMedarbejder.getSelectionModel().clearSelection();
            startVindue.setMedarbejder(medarbejder);
            this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl i log ind");
            alert.setHeaderText("Manglende information");
            alert.setContentText("Vælg en medarbejder på listen for at logge ind.");
            alert.show();
        }
    }
    private void annullerAction() {
        this.hide();
    }
}
