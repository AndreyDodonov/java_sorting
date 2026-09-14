package app.concurrency;

import app.model.Bus;
import app.model.BusField;
import app.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OccurenceCounterImplTest {

    @Test
    void correctData_oneOccurence() throws InterruptedException {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(1).mileage(3000).build();

        List<Bus> loaded = List.of(bus1, bus2, bus3);

        OccurenceCounter counter = new OccurenceCounterImpl();
        long result = counter.count(loaded, BusField.MILEAGE, 2000, 2);

        assertEquals(1, result);
    }

    @Test
    void correctDataModelField_twoOccurences() throws InterruptedException {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz-2").routeNumber(1).mileage(3000).build();

        List<Bus> loaded = List.of(bus1, bus2, bus3);

        OccurenceCounter counter = new OccurenceCounterImpl();
        long result = counter.count(loaded, BusField.MODEL, "Liaz", 2);

        assertEquals(2, result);
    }

    @Test
    void correctDataRegNumberField_oneOccurence() throws InterruptedException {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz-2").routeNumber(1).mileage(3000).build();

        List<Bus> loaded = List.of(bus1, bus2, bus3);

        OccurenceCounter counter = new OccurenceCounterImpl();
        long result = counter.count(loaded, BusField.REG_NUMBER, "A123", 2);

        assertEquals(1, result);
    }

    @Test
    void correctDataNoOccurences_zeroOccurence() throws InterruptedException {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(1).mileage(3000).build();

        List<Bus> loaded = List.of(bus1, bus2, bus3);

        OccurenceCounter counter = new OccurenceCounterImpl();
        long result = counter.count(loaded, BusField.MILEAGE, 2, 2);

        assertEquals(0, result);

    }

    @Test
    void correctData_manyOccurences() throws InterruptedException {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(1).mileage(3000).build();
        Bus bus4 = Bus.builder().regNumber("A126").model("Liaz").routeNumber(1).mileage(3000).build();

        List<Bus> loaded = List.of(bus1, bus2, bus3, bus4);

        OccurenceCounter counter = new OccurenceCounterImpl();
        long result = counter.count(loaded, BusField.MILEAGE, 3000, 2);

        assertEquals(2, result);

    }

    @Test
    void emptyList_zeroOccurences() throws InterruptedException {

        List<Bus> loaded = List.of();

        OccurenceCounter counter = new OccurenceCounterImpl();
        long result = counter.count(loaded, BusField.MILEAGE, 3000, 2);

        assertEquals(0, result);

    }

    @Test
    void incorrectThreadCount_throwException() throws InterruptedException {
        Bus bus1 = Bus.builder().regNumber("A123").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A124").model("Liaz").routeNumber(1).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A125").model("Liaz").routeNumber(1).mileage(3000).build();
        Bus bus4 = Bus.builder().regNumber("A126").model("Liaz").routeNumber(1).mileage(3000).build();

        List<Bus> loaded = List.of(bus1, bus2, bus3, bus4);

        OccurenceCounter counter = new OccurenceCounterImpl();

        assertThrows(ValidationException.class, () -> counter.count(loaded, BusField.MILEAGE, 3000, -2));

    }
}
