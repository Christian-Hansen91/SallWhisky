package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private Button btnOpretMaltning = new Button("Opret maltning");
    private Button btnOpretWhiskyDestillering = new Button("Opret whiskydestillering");
    private Button btnOpretGinDestillering = new Button("Opret gindestillering");
    private Button btnOpretTapning = new Button("Opret tapning");
    private Button btnOpretLager = new Button("Opret lager");
    private Button btnOpretFad = new Button("Opret fad");


    public void start(Stage stage) throws Exception {

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
        pane.setHgap(10);
        pane.setVgap(10);



    }

}