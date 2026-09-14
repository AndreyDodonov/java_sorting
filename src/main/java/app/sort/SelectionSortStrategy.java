package app.sort;

import java.util.Comparator;

import app.collection.ArrayBusCollection;
import app.collection.BusCollection;
import app.model.Bus;

public class SelectionSortStrategy implements SortStrategy {

    @Override
    public BusCollection sort(BusCollection input, Comparator<Bus> comparator) {
        BusCollection result = new ArrayBusCollection(input);
        int n = result.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(result.get(j), result.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            swap(result, i, minIndex);
        }
        return result;
    }

    @Override
    public String name() {
        return "Selection Sort";
    }
}
