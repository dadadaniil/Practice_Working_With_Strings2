import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class RunnerTest {

    @Test
    public void testFirstScenario() {
        StringBuilder result = new StringBuilder();
        String path = String.format(Main.NAME_OF_FILE, 1);
        ResourceBundle rb = ResourceBundle.getBundle(path, Locale.ENGLISH);

        int errorLines = Main.searchForCorrectValues(result, rb);
        assertEquals(3, errorLines);
        String expectedIn1 = Main.SUM_TEXT + "8.24";
        assertEquals(expectedIn1, result.toString());
    }

    @Test
    public void testSecondScenario() {
        StringBuilder result = new StringBuilder();
        String path = String.format(Main.NAME_OF_FILE, 2);
        ResourceBundle rb = ResourceBundle.getBundle(path, Locale.ENGLISH);

        int errorLines = Main.searchForCorrectValues(result, rb);
        assertEquals(9, errorLines);
        String expectedIn1 = Main.SUM_TEXT + "30.242";
        assertEquals(expectedIn1, result.toString());
    }

    @Test
    public void testThirdScenario() {
        StringBuilder result = new StringBuilder();
        String path = String.format(Main.NAME_OF_FILE, 3);
        ResourceBundle rb = ResourceBundle.getBundle(path, Locale.ENGLISH);

        int errorLines = Main.searchForCorrectValues(result, rb);
        assertEquals(0, errorLines);
        String expectedIn1 = Main.SUM_TEXT + "1.9";
        assertEquals(expectedIn1, result.toString());
    }

    @Test
    public void testWrongName() {
        assertThrows(MissingResourceException.class, () -> {
            StringBuilder result = new StringBuilder();
            String path = String.format(Main.NAME_OF_FILE, 7);
            ResourceBundle rb = ResourceBundle.getBundle(path, Locale.ENGLISH);
            Main.searchForCorrectValues(result, rb);
        });
    }
}