package app.sort;

import app.collection.ArrayBusCollection;
import app.collection.BusCollection;
import app.model.Bus;
import app.model.BusField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSortStrategyTest {

    @Test
    void InsertionSortTest() {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(2000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz-2").routeNumber(12).mileage(1000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(14).mileage(3000).build();
        Bus bus4 = Bus.builder().regNumber("A126").model("Liaz").routeNumber(14).mileage(500).build();

        SortContext sortContext = new SortContext(new QuickSortStrategy());
        BusCollection data = new ArrayBusCollection();

        data.add(bus1);
        data.add(bus2);
        data.add(bus3);
        data.add(bus4);

        BusField field = BusField.MILEAGE;
        SortResult sorted = sortContext.executeSort(data, BusComparators.byField(field));

        BusCollection result = sorted.sorted();

        assertTrue(result.get(0).getMileage() <= result.get(1).getMileage());
        assertTrue(result.get(1).getMileage() <= result.get(2).getMileage());
    }

}
