package app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainIntegrationTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void main_fullSortFlow_printsResultAndExits() {
        String simulatedInput = "1\n1\n5\n3\n1\n1\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Main.main(new String[]{});

        String printed = output.toString();
        assertTrue(printed.contains("работа завершена!"));
    }

    @Test
    void main_occurrenceFlow_printsCountAndExits() {
        String simulatedInput = "2\n1\n10\n3\n5000\n2\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Main.main(new String[]{});

        String printed = output.toString();
        assertTrue(printed.contains("Найдено вхождений:"));
    }

    @Test
    void main_invalidMenuChoice_printsErrorAndContinues() {
        String simulatedInput = "9\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Main.main(new String[]{});

        String printed = output.toString();
        assertTrue(printed.contains("такого пункта меню нет"));
    }

    @Test
    void main_sortFlowWithInvalidNumber_printsErrorAndReturnsToMenu() {
        String simulatedInput = "1\nabc\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Main.main(new String[]{});

        String printed = output.toString();
        assertTrue(printed.contains("Нужно вводить положительное число"));
    }

}
