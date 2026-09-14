package app.sort;

import java.util.Comparator;

import app.collection.ArrayBusCollection;
import app.collection.BusCollection;
import app.model.Bus;

public class QuickSortStrategy implements SortStrategy {

    private int partition(BusCollection list, int low, int high, Comparator<Bus> comparator) {
        Bus pivotPoint = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivotPoint) < 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);

        return i + 1;
    }

    private void quickSort(BusCollection list, int low, int high, Comparator<Bus> comparator) {
        if (low < high) {
            int partitionIndex = partition(list, low, high, comparator);
            quickSort(list, low, partitionIndex - 1, comparator);
            quickSort(list, partitionIndex + 1, high, comparator);
        }
    }

    @Override
    public BusCollection sort(BusCollection input, Comparator<Bus> comparator) {
        BusCollection result = new ArrayBusCollection(input);
        int n = result.size();
        quickSort(result, 0, n - 1, comparator);

        return result;
    }

    @Override
    public String name() {
        return "Quick Sort";
    }
}
