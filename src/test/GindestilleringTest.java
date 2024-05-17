package test;

import model.application.Gindestillering;
import model.application.Ingrediens;
import model.application.Ingrediensmaengde;
import model.application.Medarbejder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GindestilleringTest {
    private Ingrediensmaengde ingrediensmaengde;
    private Gindestillering gindestillering;
    private double maengde;

    @Test
    void gindestilleringConstructor() { //tester enebær-constructor og indeholderIngrediens
        Medarbejder medarbejder = new Medarbejder("christian", 1234);
        gindestillering = new Gindestillering(LocalDate.now(), LocalDate.now().plusDays(3), 50, 50, 1000, 200, medarbejder);
        boolean indeholderEnebaer = false;
        indeholderEnebaer = gindestillering.ginIndeholderIngrediens(Ingrediens.ENEBAER);
        assertTrue(indeholderEnebaer);
    }

    @Test
    void tilfoejIngrediensmaengde() {
        Medarbejder medarbejder = new Medarbejder("christian", 1234);
        gindestillering = new Gindestillering(LocalDate.now(), LocalDate.now().plusDays(3), 50, 50, 1000, 200, medarbejder);
        gindestillering.tilfoejIngrediensmaengde(Ingrediens.ENEBAER,500);
        double result = gindestillering.maengdeAfIngrediens(Ingrediens.ENEBAER);
        assertEquals(result, 700);

        gindestillering.tilfoejIngrediensmaengde(Ingrediens.APPELSIN,200);
        result = gindestillering.maengdeAfIngrediens(Ingrediens.APPELSIN);
        assertEquals(result, 200);
    }
}