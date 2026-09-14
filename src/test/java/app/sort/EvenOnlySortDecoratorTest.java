package app.sort;

import app.collection.BusCollection;
import app.model.Bus;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EvenOnlySortDecoratorTest {

    @Test
    void correctData_okSort() {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(1).mileage(3000).build();
        Bus bus4 = Bus.builder().regNumber("A126").model("Liaz").routeNumber(1).mileage(4000).build();

        BusCollection loaded = BusCollection.of(bus1, bus2, bus3, bus4);

        EvenOnlySortDecorator decorator = new EvenOnlySortDecorator(new BubbleSortStrategy());
        Comparator<Bus> comparator = BusComparators.byField(app.model.BusField.MILEAGE);

        BusCollection result = decorator.sort(loaded, comparator);

        // нечётные остались на своих исходных позициях
        assertEquals("A123", result.get(0).getRegNumber());
        assertEquals("A125", result.get(2).getRegNumber());

        // чётные отсортированы между собой по возрастанию: было 4000, 2000 -> стало
        // 2000, 4000
        assertEquals(2000, result.get(1).getMileage());
        assertEquals(4000, result.get(3).getMileage());

    }
}
