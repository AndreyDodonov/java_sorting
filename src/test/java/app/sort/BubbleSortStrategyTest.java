package app.sort;

import app.model.Bus;
import app.model.BusField;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BubbleSortStrategyTest {

    @Test
    void sortTest() {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(2000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz-2").routeNumber(12).mileage(1000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(14).mileage(3000).build();

        SortContext sortContext = new SortContext(new BubbleSortStrategy());
        List<Bus> data = new ArrayList<>();

        data.add(bus1);
        data.add(bus2);
        data.add(bus3);

        BusField field = BusField.MILEAGE;
        SortResult sorted = sortContext.executeSort(data, BusComparators.byField(field));

        List<Bus> result = sorted.sorted();

        assertTrue(result.get(0).getMileage() <= result.get(1).getMileage());
        assertTrue(result.get(1).getMileage() <= result.get(2).getMileage());
    }

}
