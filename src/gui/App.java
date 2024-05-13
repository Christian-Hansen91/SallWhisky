package gui;

import controller.Controller;
import javafx.application.Application;
import model.application.Fad;
import model.application.Maltning;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();

        Application.launch(StartVindue.class);
    }

    private static void initStorage() {
        Maltning maltning1 = Controller.opretMaltning(800.0, "Evergreen", "Stadsgaard", "Tørv");
        Maltning maltning2 = Controller.opretMaltning(500.0, "Stairway", "Mosevang");
        Maltning maltning3 = Controller.opretMaltning(600.0, "Irina", "Mosevang", "Tørv");

        Fad fad1 = Controller.opretFad(01, LocalDate.of(2023, 03, 10), "EX-Bourbon", 60, "USA", "American Whisky Company", "Bourbon");
        Fad fad2 = Controller.opretFad(02, LocalDate.of(2023, 8, 05), "EX-Oloroso", 120, "Spanien", "Espania Whisky", "Cherry, 3. lagring");

        //Destillering destillering1 = Controller.opretDestillering(maltning1, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 2), 10.0, 22.0, 100.0, 28.0, "OK", 52.5);
        //Destillering destillering2 = Controller.opretDestillering(maltning2, LocalDate.of(2024, 4, 16), LocalDate.of(2024, 4, 16), 30.0, 8.0, 100.0, 25.0, "OBS, tåler fortynding", 59.9);

    }
}