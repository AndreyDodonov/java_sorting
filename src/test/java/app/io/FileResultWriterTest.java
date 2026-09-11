package app.io;

import app.model.Bus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileResultWriterTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldAppendResultsToExistingFile() throws IOException {
        Path file = tempDir.resolve("results.jsonl");

        // Уже существующая запись в файле
        String existingJson = """
            {"routeNumber":10,"regNumber":"А111ВС77","model":"PAZ","mileage":50000,"note":"","operational":true}
            """;

        Files.writeString(file, existingJson);

        Bus bus1 = Bus.builder()
            .routeNumber(15)
            .regNumber("А123ВС77")
            .model("PAZ")
            .mileage(120000)
            .note("")
            .operational(true)
            .build();

        Bus bus2 = Bus.builder()
            .routeNumber(27)
            .regNumber("В456ОР78")
            .model("LiAZ")
            .mileage(85000)
            .note("После ТО")
            .operational(true)
            .build();

        FileResultWriter writer = new FileResultWriter();

        writer.appendResults(
            List.of(bus1, bus2),
            file.toString()
        );

        List<String> lines = Files.readAllLines(file);

        assertEquals(3, lines.size());

        assertEquals(existingJson.trim(), lines.get(0));

        assertEquals(
            "{\"routeNumber\":15,\"regNumber\":\"А123ВС77\",\"model\":\"PAZ\",\"mileage\":120000,\"note\":\"\",\"operational\":true}",
            lines.get(1)
        );

        assertEquals(
            "{\"routeNumber\":27,\"regNumber\":\"В456ОР78\",\"model\":\"LiAZ\",\"mileage\":85000,\"note\":\"После ТО\",\"operational\":true}",
            lines.get(2)
        );
    }
}
