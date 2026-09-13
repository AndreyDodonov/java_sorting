package app.model;

import app.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BusTest {

    @Test
    void builderAllCorrectData() {
        Bus bus = Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(1000).build();
        Assertions.assertEquals("Liaz", bus.getModel());
    }

    @Test
    void builderIncorrectMileage() {
        Bus.Builder builder = Bus.builder().regNumber("A123").model("Liaz").routeNumber(12).mileage(-10);

        Assertions.assertThrows(ValidationException.class, builder::build);
    }

    @Test
    void builderIncorrectRouteNumber() {
        Bus.Builder builder = Bus.builder().regNumber("A123").model("Liaz").routeNumber(-12).mileage(10);

        Assertions.assertThrows(ValidationException.class, builder::build);
    }

}
