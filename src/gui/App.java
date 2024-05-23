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
        Medarbejder medarbejder1 = Controller.opretMedarbejder("Snævar", 20202020);
        Medarbejder medarbejder2 = Controller.opretMedarbejder("Thomas", 45454545);
        Medarbejder medarbejder3 = Controller.opretMedarbejder("Martin", 30102040);

        Lager lager1 = Controller.opretLager("Lars' lager", new Lagerenhed[10][4], medarbejder1);
        Lager lager2 = Controller.opretLager("Container lager", new Lagerenhed[2][2], medarbejder1);

        Maltning maltning1 = Controller.opretMaltning(800.0, "Evergreen", "Stadsgaard", "Tørv", "Lækker maltning", medarbejder1);
        Maltning maltning2 = Controller.opretMaltning(500.0, "Stairway", "Mosevang", medarbejder2);
        Maltning maltning3 = Controller.opretMaltning(600.0, "Irina", "Mosevang", "Tørv", "", medarbejder3);
        Maltning maltning4 = Controller.opretMaltning(900.0, null, null, null);

        Fad fad1 = Controller.opretFad(LocalDate.of(2023, 03, 10), "EX-Bourbon", 60, "USA", "American Whisky Company", "Bourbon", medarbejder2);
        Fad fad2 = Controller.opretFad(LocalDate.of(2023, 8, 05), "EX-Oloroso", 120, "Spanien", "Espania Whisky", "Cherry, 3. lagring", medarbejder3);
        Fad fad3 = Controller.opretFad(LocalDate.of(2023, 02, 10), "EX-Redwine", 60, "Italien", "El Winos dos Italianos", "Eg fra Uganda", medarbejder2);

        Whiskydestillering whiskydestillering1 = Controller.opretWhiskydestillering(maltning4, null, null,  90000.0, 0.0, 0.0, "Andet. Destilleringen kommer udefra", 0.0, medarbejder3);
        Whiskydestillering whiskydestillering2 = Controller.opretWhiskydestillering(maltning1, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 2), 22.0, 100.0, 28.0, "OK", 52.5, medarbejder1);
        Whiskydestillering whiskydestillering3 = Controller.opretWhiskydestillering(maltning2, LocalDate.of(2024, 4, 16), LocalDate.of(2024, 4, 16), 8.0, 100.0, 25.0, "OBS, tåler fortynding", 59.9, medarbejder2);

        Destillat destillat1 = Controller.opretDestillat(fad1, medarbejder2);
        destillat1.tilfoejVaeskeTilDestillat(whiskydestillering2.opretVaeskeTilDestillat(100));
        Destillat destillat2 = Controller.opretDestillat(fad2, medarbejder1);
        destillat2.tilfoejVaeskeTilDestillat(whiskydestillering3.opretVaeskeTilDestillat(50));
        destillat2.setDato(LocalDate.of(2018,1,2));
        lager1.addLagerenhedAt(2, 1, fad2);
        lager1.addReol(6);

        Whisky whisky1 = Controller.opretWhisky(LocalDate.of(2024, 5, 19), "Wild Whisky", "1. Edition", 0.7, 10.0, 45.9, medarbejder3, "Single cast");
        Whisky whisky2 = Controller.opretWhisky(LocalDate.of(2024, 5, 14), "Wild Whisky", "2. Edition", 0.7, 5.0, 59.9, medarbejder2, "Cast strength");

    }
}