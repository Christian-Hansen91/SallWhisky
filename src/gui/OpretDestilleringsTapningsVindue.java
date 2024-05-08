package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

public class OpretDestilleringsTapningsVindue extends Stage {
    private Label lblId = new Label("ID: ");
    private DatePicker dato = new DatePicker(LocalDate.now());
    private ListView lvwTapninger = new ListView<>();
    private ListView lvwFade = new ListView<>();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextField txfKommentar = new TextField();
    private Label lblDestilleringsTapning = new Label("Destilleringstapning");
    private TextField txfDestilleringsTapning = new TextField();
    private Button btnTilfoej = new Button("Tilføj");

    public OpretDestilleringsTapningsVindue(String title, Stage owner) {
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
        lblDestilleringsTapning.setTextFill(Color.BURLYWOOD);
        pane.add(lblId, 0, 1);
        pane.add(dato, 0, 2);
        pane.add(lvwTapninger, 0, 3);
        lvwTapninger.setMaxWidth(200);
        pane.add(lvwFade, 0, 4);
        lvwFade.setMaxWidth(200);
        pane.add(lblKommentar, 0, 5);
        pane.add(txfKommentar, 0, 6);
        txfKommentar.setMaxWidth(200);
        pane.add(btnTilfoej, 0, 7);
        pane.setHalignment(btnTilfoej, HPos.RIGHT);

        pane.add(lblDestilleringsTapning, 15, 1, 2, 1);
        pane.setHalignment(lblDestilleringsTapning, HPos.CENTER);
        pane.add(txfDestilleringsTapning, 15, 2, 2, 6);
        pane.setHalignment(txfDestilleringsTapning, HPos.CENTER);
        txfDestilleringsTapning.setMinWidth(200);
        txfDestilleringsTapning.setMinHeight(215);
    }
}
