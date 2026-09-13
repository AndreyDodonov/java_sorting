package app;

import app.sort.SortResult;
import app.validation.ValidationException;

import java.util.Scanner;

public class Main {
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("""
                    === Меню ===
                    1. Загрузка и сортировка автобусов
                    2. Подсчёт количества вхождений
                    0. Выход
                    """);
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> runSortDemo(scanner);
                case "2" -> runOccurrenceDemo(scanner);
                case "0" -> running = false;
                default -> System.out.println("такого пункта меню нет, попробуй выбрать из списка");
            }
        }
        System.out.println("работа завершена!");
        scanner.close();
    }

    @SuppressWarnings("java:S106")
    private static void runSortDemo(Scanner scanner) {
        try {

            MenuHandler.UserInput input = MenuHandler.userInput(scanner);

            SortResult result = MenuHandler.sortData(input.strategy(), input.field(), input.loaded());

            result.sorted().forEach(System.out::println);
            System.out.printf("Алгоритм: %s, время: %.3f мс%n", result.algoName(), result.elapsedTimeInMS());

        } catch (NumberFormatException e) {
            System.out.println("Нужно вводить положительное число, больше нуля");
        } catch (ValidationException e) {
            System.out.println("Ошибка валидации данных: " + e.getMessage());
        }
    }

    @SuppressWarnings("java:S106")
    private static void runOccurrenceDemo(Scanner scanner) {
        try {
            long count = MenuHandler.countOccurrences(scanner);
            System.out.println("Найдено вхождений: " + count);
        } catch (NumberFormatException e) {
            System.out.println("Нужно вводить положительное число, больше нуля");
        } catch (ValidationException e) {
            System.out.println("Ошибка валидации данных: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Подсчёт был прерван: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
