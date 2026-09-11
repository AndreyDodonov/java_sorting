package app.io;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BusFileDtoTest {

    private final Gson gson = new Gson();

    @Test
    void shouldDeserializeJsonToDto() {
        String json = """
            {
                "routeNumber": 15,
                "regNumber": "A123BC",
                "model": "PAZ",
                "mileage": 120000,
                "note": "После ТО",
                "operational": true
            }
            """;

        BusFileDto dto = gson.fromJson(json, BusFileDto.class);

        assertEquals(15, dto.routeNumber());
        assertEquals("A123BC", dto.regNumber());
        assertEquals("PAZ", dto.model());
        assertEquals(120000, dto.mileage());
        assertEquals("После ТО", dto.note());
        assertTrue(dto.operational());
    }

    @Test
    void shouldSerializeDtoToJson() {
        BusFileDto dto = new BusFileDto(
            15,
            "A123BC",
            "PAZ",
            120000,
            "После ТО",
            true
        );

        String json = gson.toJson(dto);

        assertTrue(json.contains("\"routeNumber\":15"));
        assertTrue(json.contains("\"regNumber\":\"A123BC\""));
        assertTrue(json.contains("\"model\":\"PAZ\""));
        assertTrue(json.contains("\"mileage\":120000"));
        assertTrue(json.contains("\"note\":\"После ТО\""));
        assertTrue(json.contains("\"operational\":true"));
    }
}
