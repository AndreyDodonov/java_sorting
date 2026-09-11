package app.io;

import app.model.Bus;
import app.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ручной ввод через Scanner. При невалидных данных — сразу сообщаем об
 * ошибке и просим ввести заново, НЕ пропускаем запись молча в отличие от
 * чтения из файла или рандомной генерации (см. DataSource).
 * Для каждого поля отдельно проверяется корректность формата ввода.
 * Валидация выполняется {@link Bus.Builder}.
 * При ошибке валидации текущий автобус вводится заново.
 */
public class ManualDataSource implements DataSource {

    private final Scanner scanner;

    public ManualDataSource(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public LoadResult load(int count) {
        List<Bus> loaded = new ArrayList<>();

        while (loaded.size() < count) {
            System.out.println("\nВвод автобуса №" + (loaded.size() + 1));

            Bus bus = readBus();
            loaded.add(bus);

            System.out.println("Автобус успешно добавлен.");
        }

        return new LoadResult(loaded, List.of());
    }

    private Bus readBus() {
        while (true) {
            try {
                int routeNumber = readInt("Номер маршрута: ");
                String regNumber = readString("Регистрационный номер: ");
                String model = readString("Модель: ");
                int mileage = readInt("Пробег: ");
                String note = readString("Примечание: ");
                boolean operational = readBoolean("Автобус в эксплуатации (true/false): ");

                return Bus.builder()
                    .routeNumber(routeNumber)
                    .regNumber(regNumber)
                    .model(model)
                    .mileage(mileage)
                    .note(note)
                    .operational(operational)
                    .build();

            } catch (ValidationException e) {
                System.out.println("Ошибка валидации: " + e.getMessage());
                System.out.println("Повторите ввод данный для этого автобуса.");
            }
        }
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Необходимо ввести целое число.");
            }
        }
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private boolean readBoolean(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("true")) {
                return true;
            }

            if (input.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.println("Необходимо ввести true или false.");
        }
    }
}
