package gui;

import controller.Controller;
import javafx.application.Application;
import model.application.Destillat;
import model.application.Destillering;
import model.application.Fad;
import model.application.Maltning;
import storage.Storage;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();

        Application.launch(StartVindue.class);
    }

    private static void initStorage() {
        Maltning maltning1 = Controller.opretMaltning(800.0, "Mosevang byg", "Tørv");
        Maltning maltning2 = Controller.opretMaltning(500.0, "Stadsgaard byg");

        Fad fad1 = Controller.opretFad(01, 1, "USA", LocalDate.of(2023, 03, 10), "Bourbon");
        Fad fad2 = Controller.opretFad(02, 2, "Spanien", LocalDate.of(2023, 8, 05), "Cherry, 3. lagring");

        Destillering destillering1 = Controller.opretDestillering(01, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 2), 10.0, 22.0, 100.0, 28.0, "OK", 59.9);
        Destillering destillering2 = Controller.opretDestillering(02, LocalDate.of(2024, 4, 16), LocalDate.of(2024, 4, 16), 30.0, 8.0, 100.0, 25.0, "OK", 59.9);

    }
}