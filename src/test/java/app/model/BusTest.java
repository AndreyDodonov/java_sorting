package app.model;

import app.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BusTest {

    @Test
    void builderAllCorrectData() {
        Bus bus = Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(1000).build();
        Assertions.assertEquals("Liaz", bus.getModel());
    }

    @Test
    void builderIncorrectMileage() {
        Assertions.assertThrows(ValidationException.class,
                () -> Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(-10).build());
    }

    @Test
    void builderIncorrectRouteNumber() {
        Assertions.assertThrows(ValidationException.class,
                () -> Bus.builder().regNumber("A123").model("Liaz").routeNumber(-12).mileage(10).build());
    }

}
