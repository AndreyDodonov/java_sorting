package app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    void smokeTest() {
        assertTrue(true);
    }

    @Test
    void smokeTest2() {
        assertEquals(1, 1, "просто проверил, что тесты подцепились");
    }
}
