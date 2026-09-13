package app;

import app.collection.BusCollection;
import app.concurrency.OccurenceCounter;
import app.concurrency.OccurenceCounterImpl;
import app.io.DataSource;
import app.io.FileDataSource;
import app.io.ManualDataSource;
import app.io.RandomDataSource;
import app.model.Bus;
import app.model.BusField;
import app.sort.*;
import app.validation.ValidationException;

import java.util.List;
import java.util.Scanner;

/**
 * Обработка пользовательского ввода в Main.
 * userInput - собираем данные
 * sortData - получаем то что собрали на вводе, сортируем и выдаём SortResult для вывода в main
 */
@SuppressWarnings("java:S106")
public class MenuHandler {
    public record UserInput(SortStrategy strategy, BusField field, List<Bus> loaded) {

    }

    // обрабатываем пользовательский ввод
    public static UserInput userInput(Scanner userInputScanner) {
        List<Bus> loaded = selectSource(userInputScanner);
        BusField field = selectFieldSort(userInputScanner);
        SortStrategy strategy = selectAlgorithm(userInputScanner);
        return new UserInput(strategy, field, loaded);
    }

    //выбор источника
    private static List<Bus> selectSource(Scanner userInputScanner) {
        System.out.println("Источник данных: \n 1 - рандом \n 2 - файл \n 3 - вручную");
        int choice = Integer.parseInt(userInputScanner.nextLine().trim());
        DataSource dataSource = switch (choice) {
            case 1 -> new RandomDataSource();
            case 2 -> new FileDataSource(askFilePath(userInputScanner));
            case 3 -> new ManualDataSource(userInputScanner);
            default -> throw new ValidationException("нет такого пункта");
        };

        // можно было сделать в случае с файлом просто Integer.MAX_VALUE, но раз уж это заодно
        // тестовый стенд - пусть будет выбор максимального количества записей
        System.out.println(dataSource instanceof FileDataSource
            ? "Сколько максимум записей прочитать из файла?"
            : "Сколько автобусов сгенерировать?");
        int count = Integer.parseInt(userInputScanner.nextLine().trim());

        DataSource.LoadResult loadResult = dataSource.load(count);
        if (!loadResult.rejectedWarnings().isEmpty()) {
            System.out.println("Отклонено записей: " + loadResult.rejectedWarnings().size());
            loadResult.rejectedWarnings().forEach(System.out::println);
        }
        return loadResult.loaded();
    }

    private static String askFilePath(Scanner userInputScanner) {
        System.out.println("Введите путь к файлу (Enter — buses.json в корне проекта):");
        String input = userInputScanner.nextLine().trim();
        return input.isEmpty() ? "buses.json" : input;
    }

    //выбор поля сортировки
    private static BusField selectFieldSort(Scanner userInputScanner) {
        System.out.println("Сортировать по: \n 1 - гос номер \n 2 - модель \n 3 - пробег");
        int sortChoice = Integer.parseInt(userInputScanner.nextLine().trim());
        return switch (sortChoice) {
            case 1 -> BusField.REG_NUMBER;
            case 2 -> BusField.MODEL;
            case 3 -> BusField.MILEAGE;
            default -> throw new ValidationException("нет такого пункта");
        };
    }

    //выбор алгоритма
    private static SortStrategy selectAlgorithm(Scanner userInputScanner) {
        System.out.println(
            "Алгоритм сортировки: \n 1 - Bubble Sort \n 2 - Selection Sort \n 3 - Insertion Sort \n 4 - Quick Sort");
        int sortChoice = Integer.parseInt(userInputScanner.nextLine().trim());
        return switch (sortChoice) {
            case 1 -> new BubbleSortStrategy();
            case 2 -> new SelectionSortStrategy();
            case 3 -> new InsertionSortStrategy();
            case 4 -> new QuickSortStrategy();
            default -> throw new ValidationException("нет такого пункта");
        };

    }

    // сортируем и отдаём отсортированный результат
    public static SortResult sortData(SortStrategy strategy, BusField field, List<Bus> loaded) {
        List<Bus> data = BusCollection.fromStream(loaded.stream()).toList();
        SortContext sortContext = new SortContext(strategy);
        return sortContext.executeSort(data, BusComparators.byField(field));
    }

    public static long countOccurrences(Scanner userInputScanner) throws InterruptedException {
        List<Bus> data = selectSource(userInputScanner);
        BusField field = selectFieldSort(userInputScanner);
        Object targetValue = askTargetValue(userInputScanner, field);
        int threadCount = askThreadCount(userInputScanner);

        OccurenceCounter counter = new OccurenceCounterImpl();
        return counter.count(data, field, targetValue, threadCount);
    }

    private static Object askTargetValue(Scanner userInputScanner, BusField field) {
        System.out.println("Введи значения для поиска: ");
        String input = userInputScanner.nextLine().trim();

        return switch (field) {
            case MILEAGE -> Integer.parseInt(input);
            case REG_NUMBER -> input;
            case MODEL -> input;
        };
    }

    private static int askThreadCount(Scanner userInputScanner) {
        System.out.println("Введи количество потоков: ");
        String input = userInputScanner.nextLine().trim();
        int threadCount = Integer.parseInt(input);
        if (threadCount < 1) {
            throw new ValidationException("Число потоков не может быть меньше 1");
        }
        return threadCount;
    }


}
