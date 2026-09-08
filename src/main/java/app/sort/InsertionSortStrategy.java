package app.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import app.model.Bus;

public class InsertionSortStrategy implements SortStrategy {

    @Override
    public List<Bus> sort(List<Bus> input, Comparator<Bus> comparator) {
        List<Bus> result = new ArrayList<>(input);
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