package app.io;

import app.model.Bus;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileResultWriter implements ResultWriter {

    private final Gson gson;

    public FileResultWriter() {
        this.gson = new Gson();
    }

    @Override
    public void appendResults(List<Bus> sorted, String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath), StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            for (Bus bus : sorted) {
                BusFileDto dto = new BusFileDto(bus.getRouteNumber(), bus.getRegNumber(), bus.getModel(),
                        bus.getMileage(), bus.getNote(), bus.isOperational());

                writer.write(gson.toJson(dto));
                writer.newLine();
            }
        }
    }
}
