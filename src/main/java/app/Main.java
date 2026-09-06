package app;

import app.io.DataSource;
import app.io.RandomDataSource;
import app.model.Bus;
import app.model.BusField;
import app.sort.BubbleSortStrategy;
import app.sort.BusComparators;
import app.sort.SortContext;
import app.sort.SortResult;
import app.validation.ValidationException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // TODO: выбор источника данных (файл/рандом/вручную) через меню —
        // пока жёстко зашит RandomDataSource, до готовности io-модуля
        DataSource dataSource = new RandomDataSource();

        // TODO: выбор одной из 4 стратегий через меню — пока только Bubble Sort,
        // до готовности остальных 3 алгоритмов сортировки
        SortContext sortContext = new SortContext(new BubbleSortStrategy());

        boolean running = true;
        while (running) {
            // пока не готов пакет io из выбора только рандомная генерация
            System.out.println("""
                    === Меню ===
                    1. Сгенерировать и отсортировать автобусы
                    0. Выход
                    """);
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> runSortDemo(scanner, dataSource, sortContext);
                case "0" -> running = false;
                default -> System.out.println("такого пункта меню нет, попробуй выбрать из списка");
            }
        }
        System.out.println("работа завершена!");
    }

    private static void runSortDemo(Scanner scanner, DataSource dataSource, SortContext sortContext) {
        try {
            System.out.println("Сколько автобусов сгенерировать?");
            int count = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("-- генерация и сортировка ... --");
            DataSource.LoadResult loadResult = dataSource.load(count);
            if (!loadResult.rejectedWarnings().isEmpty()) {
                System.out.println("Отклонено записей: " + loadResult.rejectedWarnings().size());
                loadResult.rejectedWarnings().forEach(System.out::println);
            }
            List<Bus> data = loadResult.loaded();

            System.out.println("Сортировать по: \n 1 - гос номер \n 2 - модель \n 3 - пробег");
            int fieldChoice = Integer.parseInt(scanner.nextLine().trim());
            BusField field = switch (fieldChoice) {
                case 1 -> BusField.REG_NUMBER;
                case 2 -> BusField.MODEL;
                case 3 -> BusField.MILEAGE;
                default -> throw new ValidationException("нет такого пункта");
            };

            SortResult result = sortContext.executeSort(data, BusComparators.byField(field));
            result.sorted().forEach(System.out::println);
            System.out.printf("Алгоритм: %s, время: %.3f мс%n", result.algoName(), result.elapsedTimeInMS());

        } catch (NumberFormatException e) {
            System.out.println("Нужно вводить положительное число, больше нуля");
        } catch (ValidationException e) {
            System.out.println("Ошибка валидации данных: " + e.getMessage());
        }

    }
}
