package app.io;

import app.model.Bus;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ManualDataSourceTest {
    @Test
    void shouldLoadBusFromManualInput() {
        String input = """
                15
                A123BC
                PAZ
                120000
                После ТО
                true
                """;

        Scanner scanner = new Scanner(input);
        ManualDataSource dataSource = new ManualDataSource(scanner);

        DataSource.LoadResult result = dataSource.load(1);

        assertEquals(1, result.loaded().size());

        Bus bus = result.loaded().get(0);
        assertEquals(15, bus.getRouteNumber());
        assertEquals("A123BC", bus.getRegNumber());
        assertEquals("PAZ", bus.getModel());
        assertEquals(120000, bus.getMileage());
        assertEquals("После ТО", bus.getNote());
        assertTrue(bus.isOperational());

        assertTrue(result.rejectedWarnings().isEmpty());
    }

    @Test
    void shouldHandleInvalidManualInput() {
        String input = """
                abc
                15
                A123BC
                PAZ
                120000
                После ТО
                maybe
                true

                -5
                B456CD
                LiAZ
                85000
                Исправен
                false

                27
                C456FF
                LiAZ
                85000
                После ТО
                true
                """;

        Scanner scanner = new Scanner(input);
        ManualDataSource dataSource = new ManualDataSource(scanner);

        DataSource.LoadResult result = dataSource.load(2);

        assertEquals(2, result.loaded().size());

        Bus first = result.loaded().get(0);
        assertEquals(15, first.getRouteNumber());
        assertEquals("A123BC", first.getRegNumber());

        Bus second = result.loaded().get(1);
        assertEquals(27, second.getRouteNumber());
        assertEquals("C456FF", second.getRegNumber());

        assertTrue(result.rejectedWarnings().isEmpty());
    }
}
