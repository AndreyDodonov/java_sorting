package app.collection;

import app.model.Bus;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusCollectionTest {

    @Test
    void allCorreectDataStream_allOk() {
        Bus bus1 = Bus.builder().regNumber("A1").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A2").model("Setra").routeNumber(2).mileage(2000).build();
        Bus bus3 = Bus.builder().regNumber("A3").model("Ikarus").routeNumber(3).mileage(3000).build();

        Stream<Bus> stream = Stream.of(bus1, bus2, bus3);

        BusCollection collection = BusCollection.fromStream(stream);

        assertEquals(3, collection.size());
    }

    @Test
    void fromStream_withDuplicate_skipsDuplicateWithoutThrowing() {
        Bus bus1 = Bus.builder().regNumber("A1").model("Liaz").routeNumber(1).mileage(1000).build();
        Bus bus2 = Bus.builder().regNumber("A1").model("Setra").routeNumber(2).mileage(2000).build();

        Stream<Bus> stream = Stream.of(bus1, bus2);

        BusCollection collection = BusCollection.fromStream(stream);

        assertEquals(1, collection.size());
    }
}
