package app.io;

import app.model.Bus;
import app.validation.ValidationException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Чтение и построчная валидация данных из файла. Невалидные строки —
 * пропускаем и копим в сводку (см. DataSource).
 * Пустые строки игнорируются.
 * Считаем именно успешно загруженные автобусы.
 */
public class FileDataSource implements DataSource {

    private final String path;
    private final Gson gson;

    public FileDataSource(String path) {
        this.path = path;
        this.gson = new Gson();
    }

    @Override
    public LoadResult load(int count) {
        List<Bus> loaded = new ArrayList<>();
        List<String> rejectedWarnings = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            String line;

            while (loaded.size() < count && (line = reader.readLine()) != null) {
                try {
                    if (line.isBlank()) {
                        continue;
                    }

                    BusFileDto dto = gson.fromJson(line, BusFileDto.class);

                    Bus bus = Bus.builder()
                        .routeNumber(dto.routeNumber())
                        .regNumber(dto.regNumber())
                        .model(dto.model())
                        .mileage(dto.mileage())
                        .note(dto.note())
                        .operational(dto.operational())
                        .build();

                    loaded.add(bus);
                } catch (JsonSyntaxException | ValidationException e) {
                    rejectedWarnings.add(
                        "Строка: " + line + " не валидна. Причина: " + e.getMessage()
                    );
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(
                "Не удалось прочитать файл: " + path, e
            );
        }

        return new LoadResult(loaded, rejectedWarnings);
    }
}
