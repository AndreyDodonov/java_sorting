package app.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileDataSourceTest {
    @Test
    void shouldLoadBusesFromFile() {
        String path = "src/test/resources/valid_buses.jsonl";

        FileDataSource dataSource = new FileDataSource(path);

        DataSource.LoadResult result = dataSource.load(2);

        assertEquals(2, result.loaded().size());
        assertTrue(result.rejectedWarnings().isEmpty());

        assertEquals("A123BC", result.loaded().get(0).getRegNumber());
        assertEquals("B456CD", result.loaded().get(1).getRegNumber());
    }

    @Test
    void shouldSkipInvalidBus() {
        String path = "src/test/resources/invalid_buses.jsonl";

        FileDataSource dataSource = new FileDataSource(path);

        DataSource.LoadResult result = dataSource.load(3);

        // System.out.println("Загружено: " + result.loaded().size());
        // System.out.println("Отклонено: " + result.rejectedWarnings());

        assertEquals(2, result.loaded().size());
        assertEquals(1, result.rejectedWarnings().size());

        assertEquals("A123BC", result.loaded().get(0).getRegNumber());
        assertEquals("C789EF", result.loaded().get(1).getRegNumber());

        assertTrue(result.rejectedWarnings().get(0).contains("\"routeNumber\":-5"));
        assertTrue(result.rejectedWarnings().get(0).contains("routeNumber"));
    }

    @Test
    void shouldLoadAllAvailableBuses() {
        String path = "src/test/resources/only_two_buses.jsonl";

        FileDataSource dataSource = new FileDataSource(path);

        DataSource.LoadResult result = dataSource.load(3);

        // System.out.println("Запрошено: 3");
        // System.out.println("Загружено: " + result.loaded().size());

        assertEquals(2, result.loaded().size());
        assertTrue(result.rejectedWarnings().isEmpty());
    }
}
