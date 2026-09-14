package app.sort;

import java.util.Comparator;

import app.collection.ArrayBusCollection;
import app.collection.BusCollection;
import app.model.Bus;

/**
 * пример реализации Как образец паттерна: копируем вход, сравниваем только
 * через переданный comparator, ничего из java.util.Collections/Arrays не
 * используем. Возможно не эффективный вариант, накидал для смоук теста через
 * main и продемонстрировать контракт, как это всё общается между собой
 */
public class BubbleSortStrategy implements SortStrategy {

    @Override
    public BusCollection sort(BusCollection input, Comparator<Bus> comparator) {
        BusCollection result = new ArrayBusCollection(input);
        int n = result.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(result.get(j), result.get(j + 1)) > 0) {
                    swap(result, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return result;
    }

    @Override
    public String name() {
        return "Bubble Sort";
    }
}
