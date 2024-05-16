package gui;

import controller.Controller;
import javafx.application.Application;
import model.application.*;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();

        Application.launch(StartVindue.class);
    }

    private static void initStorage() {
        Lager lager1 = Controller.opretLager("Lars' lager", new Lagerenhed[10][4]);
        Lager lager2 = Controller.opretLager("Container lager", new Lagerenhed[2][2]);

        Medarbejder medarbejder1 = Controller.opretMedarbejder("Snævar", 20202020);
        Medarbejder medarbejder2 = Controller.opretMedarbejder("Thomas", 45454545);
        Medarbejder medarbejder3 = Controller.opretMedarbejder("Martin", 30102040);

        Maltning maltning1 = Controller.opretMaltning(800.0, "Evergreen", "Stadsgaard", "Tørv");
        Maltning maltning2 = Controller.opretMaltning(500.0, "Stairway", "Mosevang");
        Maltning maltning3 = Controller.opretMaltning(600.0, "Irina", "Mosevang", "Tørv");
        Maltning maltning4 = Controller.opretMaltning(0.0, null, null);

        Fad fad1 = Controller.opretFad(LocalDate.of(2023, 03, 10), "EX-Bourbon", 60, "USA", "American Whisky Company", "Bourbon");
        Fad fad2 = Controller.opretFad(LocalDate.of(2023, 8, 05), "EX-Oloroso", 120, "Spanien", "Espania Whisky", "Cherry, 3. lagring");

        Whiskydestillering whiskydestillering1 = Controller.opretWhiskydestillering(maltning1, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 2), 10.0, 22.0, 100.0, 28.0, "OK", 52.5, medarbejder1);
        Whiskydestillering whiskydestillering2 = Controller.opretWhiskydestillering(maltning2, LocalDate.of(2024, 4, 16), LocalDate.of(2024, 4, 16), 30.0, 8.0, 100.0, 25.0, "OBS, tåler fortynding", 59.9, medarbejder2);
        Whiskydestillering whiskydestillering3 = Controller.opretWhiskydestillering(maltning4, null, null, 0.0, 0.0, 0.0, 0.0, "Andet. Destilleringen kommer udefra", 0.0, medarbejder3);

        Destillat destilla1 = Controller.opretDestillat(fad1);
        destilla1.tilfoejTapning(whiskydestillering1.opretVaeskeTilDestillat(100, ""));
    }
}