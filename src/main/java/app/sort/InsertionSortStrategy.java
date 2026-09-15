package app.sort;

import java.util.Comparator;

import app.collection.ArrayBusCollection;
import app.collection.BusCollection;
import app.model.Bus;

public class InsertionSortStrategy implements SortStrategy {

    @Override
    public BusCollection sort(BusCollection input, Comparator<Bus> comparator) {
        BusCollection result = new ArrayBusCollection(input);
        int n = result.size();

        for (int i = 1; i < n; i++) {
            Bus key = result.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(result.get(j), key) > 0) {
                result.set(j + 1, result.get(j));
                j--;
            }
            result.set(j + 1, key);
        }
        return result;
    }

    @Override
    public String name() {
        return "Insertion Sort";
    }
}
