package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.LocalDate;

public class OpretWhiskydestilleringsVindue extends Stage {
    private Label lblNewMakeNr = new Label("New Make nr.:");
    private TextField txfNewMakeNr = new TextField();
    private DatePicker startdato = new DatePicker();
    private DatePicker slutdato = new DatePicker(LocalDate.now());
    private Label lblVaeskeMaengde = new Label("Væskemængde: ");
    private TextField txfVaeskeMaengde = new TextField();
    private Label lblHead = new Label("Head: ");
    private TextField txfHead = new TextField();
    private Label lblHeart = new Label("Heart: ");
    private TextField txfHeart = new TextField();
    private Label lblTail = new Label("Tail: ");
    private TextField txfTail = new TextField();
    private Label lblAlkoholprocent = new Label("Alkoholprocent: ");
    private TextField txfAlkoholprocent = new TextField();
    private Label lblKommentar = new Label("Kommentar: ");
    private TextArea txaKommentar = new TextArea();
    private ComboBox<String> cbMaltning = new ComboBox<>();
    private Label lblOverskrift = new Label("Opret whiskydestillering");
    private Button btnGem = new Button("Gem");
    private Button btnAnnuller = new Button("Anuller");

    public OpretWhiskydestilleringsVindue(String title, Stage owner, StartVindue startVindue) {
        this.initOwner(owner);

        setTitle("Opret Whiskydestillering");
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

        lblNewMakeNr.setTextFill(Color.BURLYWOOD);
        lblVaeskeMaengde.setTextFill(Color.BURLYWOOD);
        lblHead.setTextFill(Color.BURLYWOOD);
        lblHeart.setTextFill(Color.BURLYWOOD);
        lblTail.setTextFill(Color.BURLYWOOD);
        lblVaeskeMaengde.setTextFill(Color.BURLYWOOD);
        lblAlkoholprocent.setTextFill(Color.BURLYWOOD);
        lblKommentar.setTextFill(Color.BURLYWOOD);
        lblOverskrift.setTextFill(Color.BURLYWOOD);

        pane.add(lblOverskrift, 0, 0, 26, 1);
        pane.setHalignment(lblOverskrift, HPos.CENTER);
        pane.add(lblNewMakeNr, 0, 1);
        pane.add(txfNewMakeNr, 1, 1);
        txfNewMakeNr.setMaxWidth(75);
        pane.setHalignment(txfNewMakeNr, HPos.RIGHT);

        pane.add(startdato, 0, 2, 2, 1);
        pane.add(slutdato, 0, 3, 2, 1);

        pane.add(lblVaeskeMaengde, 0, 4);
        pane.add(txfVaeskeMaengde, 1, 4);
        txfVaeskeMaengde.setMaxWidth(75);
        pane.setHalignment(txfVaeskeMaengde, HPos.RIGHT);
        pane.add(lblHead, 0, 5);
        pane.add(txfHead, 1, 5);
        txfHead.setMaxWidth(75);
        pane.setHalignment(txfHead, HPos.RIGHT);
        pane.add(lblHeart, 0, 6);
        pane.add(txfHeart, 1, 6);
        txfHeart.setMaxWidth(75);
        pane.setHalignment(txfHeart, HPos.RIGHT);
        pane.add(lblTail, 0, 7);
        pane.add(txfTail, 1, 7);
        txfTail.setMaxWidth(75);
        pane.setHalignment(txfTail, HPos.RIGHT);

        pane.add(lblAlkoholprocent, 0, 8);
        pane.add(txfAlkoholprocent, 1, 8);
        txfAlkoholprocent.setMaxWidth(75);
        pane.setHalignment(txfAlkoholprocent, HPos.RIGHT);

        pane.add(lblKommentar, 22, 1, 2, 1);
        pane.add(txaKommentar, 22, 2, 2, 4);
        txaKommentar.setMaxWidth(150);
        txaKommentar.setMaxHeight(150);
        pane.setHalignment(txaKommentar, HPos.RIGHT);

        pane.add(btnGem, 22, 8);
        pane.add(btnAnnuller, 23, 8);
        pane.setHalignment(btnGem, HPos.RIGHT);
        pane.setHalignment(btnAnnuller, HPos.RIGHT);


    }
}
