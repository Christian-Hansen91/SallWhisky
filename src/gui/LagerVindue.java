package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.application.Lager;
import model.application.Lagerenhed;

public class LagerVindue extends Stage {
    private Lager lager;
    private final String overallStyle = "-fx-font-size: 15; -fx-border-sets: -5; -fx-border-radius: 5; -fx-border-width: 2";
    private final String lagerpladsLedig = " -fx-color: green;" + overallStyle;
    private final String lagerpladsOptaget = " -fx-color: red;" + overallStyle;
    private int laengde, bredde;
    private OpretWhiskyTapningsVindue whiskyTapningsVindue;
    private ComboBox<Lager> cbLager;
    private GridPane lagerPane;
    private int reol, hylde;
    private Label lblValg = new Label("");

    public LagerVindue(OpretWhiskyTapningsVindue whiskyVindue) {
        this.whiskyTapningsVindue = whiskyVindue;
        setTitle("Vælg lager");

        setResizable(true);
        GridPane gridPane = new GridPane();

        initContent(gridPane);
        Scene scene = new Scene(gridPane);
        setScene(scene);
    }

    private void initContent(GridPane gridPane) {
        gridPane.setPrefHeight(315);
        gridPane.setPrefWidth(600);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setStyle("-fx-background-image: url('https://migogaarhus.dk/wp-content/uploads/2021/01/Sall-whisky.jpg')");
        lblValg.setTextFill(Color.BURLYWOOD);
        lagerPane = new GridPane();
        gridPane.add(lagerPane, 0, 3);
        gridPane.add(lblValg,0,2);
        cbLager = new ComboBox<>();
        for (Lager lager : Controller.getLagre()) {
            cbLager.getItems().add(lager);
        }
        cbLager.setValue(cbLager.getItems().get(0));
        opdaterValgtLager();
        opdaterLagerVindue();
        gridPane.add(cbLager, 0, 1);
        cbLager.setOnAction(e -> {
            opdaterValgtLager();
            opdaterLagerVindue();
        });

    }

    private void opdaterLagerVindue() {
        //skaber lagervinduet med knapper
        lagerPane.getChildren().clear();
        for (int i = 0; i < lager.getReolliste().length; i++) {
            for (int j = 0; j < lager.getReolliste()[i].length; j++) {
                Button btnHylde = new Button(j + "");
                lagerPane.add(btnHylde, i, j);
                btnHylde.setStyle(beregnFarvekode(i, j));
                int finalI = i;
                int finalJ = j;
                btnHylde.setOnAction(e -> vaelgReolHylde(finalI, finalJ));
            }
        }
        Button btnBekraeft = new Button("Bekræft");
        btnBekraeft.setOnAction(e -> bekraeftAction());
        lagerPane.add(btnBekraeft, laengde, bredde + 1);
    }

    private void bekraeftAction() {
        whiskyTapningsVindue.setValgtReolHylde(reol, hylde);
        close();
    }

    private void vaelgReolHylde(int reol, int hylde) {
        if (Controller.lagerpladsLedig(lager, reol, hylde)) {
            this.reol = reol;
            this.hylde = hylde;
            lblValg.setText("Du har valgt " + lager.getNavn() + ", reol " + reol + ", hylde " + hylde);
        } else {
            lblValg.setText("Den ønskede plads er optaget. Vælg en anden.");
        }
    }

    private String beregnFarvekode(int reol, int hylde) {
        String farvekode;
        if (Controller.lagerpladsLedig(lager, reol, hylde)) {
            farvekode = lagerpladsLedig;
        } else {
            farvekode = lagerpladsOptaget;
        }
        return farvekode;
    }

    private void opdaterValgtLager() {
        lblValg.setText("");
        lager = cbLager.getValue();
        laengde = lager.getReolliste().length;
        bredde = udregnBredde();
    }

    private int udregnBredde() {
        int bredde = 0;
        for (Lagerenhed[] lagerenhed : lager.getReolliste()) {
            if (lagerenhed.length > bredde) {
                bredde = lagerenhed.length;
            }
        }

        return bredde;
    }

}
