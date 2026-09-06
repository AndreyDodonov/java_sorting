package app.io;

import app.model.Bus;
import app.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Пример реализации — по ней пишем FileDataSource и позже переписывает
 * генерацию через Stream API (Доп.3), но сам паттерн "ловим ValidationException
 * на каждой записи и копим в warnings" остаётся таким же.
 */
public class RandomDataSource implements DataSource {

    private final Random random = new Random();

    @Override
    public LoadResult load(int count) {
        List<Bus> loaded = new ArrayList<>();
        List<String> rejectedWarnings = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            try {
                Bus bus = Bus.builder()
                    .regNumber("A-" + (100 + random.nextInt(900)))
                    .routeNumber(1 + random.nextInt(50))
                    .model("Model-" + (char) ('A' + random.nextInt(5)))
                    .mileage(random.nextInt(300_000))
                    .operational(random.nextBoolean())
                    .build();
                loaded.add(bus);
            } catch (ValidationException e) {
                rejectedWarnings.add("Запись №" + i + "не валидна: " + e.getMessage());
            }
        }
        return new LoadResult(loaded, rejectedWarnings);
    }
}
