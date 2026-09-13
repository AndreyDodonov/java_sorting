package app.collection;

import app.model.Bus;
import app.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBusCollectionTest {

    @Test
    void allDataCorrectAdd_increaseListSize() {
        Bus bus = Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(1000).build();
        ArrayBusCollection buses = new ArrayBusCollection();

        buses.add(bus);
        assertEquals(1, buses.size());
    }

    @Test
    void duplicatesUniqueData_throwValidationError() {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A123").model("Liaz2").routeNumber(122).mileage(10002).build();

        ArrayBusCollection buses = new ArrayBusCollection();
        buses.add(bus1);

        assertThrows(ValidationException.class, () -> buses.add(bus2));
    }

    @Test
    void growTestLargeArrays_arraySizeMoreThan10() {
        ArrayBusCollection buses = new ArrayBusCollection();

        for (int i = 0; i < 15; i++) {
            Bus bus = Bus.builder().regNumber("A" + i).model("Liaz").routeNumber(1).mileage(1000).build();
            buses.add(bus);
        }

        assertEquals(15, buses.size());
    }

    @Test
    void toList_returnsOnlyAddedElements_noNulls() {
        ArrayBusCollection buses = new ArrayBusCollection();

        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A223").model("Liaz").routeNumber(12).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A323").model("Liaz").routeNumber(13).mileage(3000).build();

        buses.add(bus1);
        buses.add(bus2);
        buses.add(bus3);

        List<Bus> result = buses.toList();

        assertEquals(3, result.size());
        assertFalse(result.contains(null));
    }

}
