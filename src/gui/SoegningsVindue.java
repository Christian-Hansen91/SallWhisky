package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SoegningsVindue extends Stage {
    public SoegningsVindue(String title, Stage stage) {
        setTitle(title);
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);

        BorderPane pane = new BorderPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }
    public void initContent(BorderPane pane) {

        TabPane tabPane = new TabPane();
        initTabContent(tabPane);
        pane.setCenter(tabPane);
    }
    public void initTabContent(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab destillatSoegning = new Tab("Destillatsøgning");
        tabPane.getTabs().add(destillatSoegning);
        Tab whiskySoegning = new Tab("Whiskysøgning");
        tabPane.getTabs().add(whiskySoegning);

        destillatSoegning.setContent(new DestillatSoegningPane());
        WhiskySoegningPane whiskyPane = new WhiskySoegningPane();
        whiskySoegning.setContent(whiskyPane);

        Tab fadSoening = new Tab("Fadsøgning");
        tabPane.getTabs().add(fadSoening);
        FadSoegningPane fadPane = new FadSoegningPane();
        fadSoening.setContent(fadPane);

        Tab ginSoegning = new Tab("Ginsøgning");
        tabPane.getTabs().add(ginSoegning);
        GinsoegningPane ginsoegningPane = new GinsoegningPane();
        ginSoegning.setContent(ginsoegningPane);
    }
}
