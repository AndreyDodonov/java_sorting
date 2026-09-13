package app;

import app.model.Bus;
import app.model.BusField;
import app.sort.BubbleSortStrategy;
import app.sort.SortResult;
import app.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void sortData_withValidData_returnsSortedListOk() {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(1).mileage(3000).build();

        List<Bus> loaded = List.of(bus1, bus2, bus3);
        SortResult result = MenuHandler.sortData(new BubbleSortStrategy(), BusField.MILEAGE, loaded);

        List<Bus> sorted = result.sorted();

        assertTrue(sorted.get(0).getMileage() <= sorted.get(1).getMileage());
        assertTrue(sorted.get(1).getMileage() <= sorted.get(2).getMileage());
    }

    @Test
    void userInput_withInvalidFieldChoice_throwsValidationException() {
        Scanner scanner = new Scanner("1\n5\n9\n");

        assertThrows(ValidationException.class, () -> MenuHandler.userInput(scanner));
    }

    // хэппи вей для рандома. Если порядок ввода поменяется - тест сломается!
    // в целом рандом тестить такое себе )
    @Test
    void userInput_withValidRandomFlow_returnsDataWithoutExceptions() {
        Scanner scanner = new Scanner("1\n5\n3\n1\n");

        MenuHandler.UserInput input = MenuHandler.userInput(scanner);

        assertEquals(5, input.loaded().size());
        assertEquals(BusField.MILEAGE, input.field());
        assertInstanceOf(BubbleSortStrategy.class, input.strategy());
    }

    // Если порядок ввода поменяется - тест сломается!
    @Test
    void userInput_withInvalidAlgorithmChoice_throwsValidationException() {
        Scanner scanner = new Scanner("1\n5\n3\n9\n");

        assertThrows(ValidationException.class, () -> MenuHandler.userInput(scanner));
    }

    // Если порядок ввода поменяется - тест сломается!
    @Test
    void userInput_withInvalidSourceChoice_throwsValidationException() {
        Scanner scanner = new Scanner("9\n");

        assertThrows(ValidationException.class, () -> MenuHandler.userInput(scanner));
    }




}
