package app.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import app.model.Bus;

public class SelectionSortStrategy implements SortStrategy {

    @Override
    public List<Bus> sort(List<Bus> input, Comparator<Bus> comparator) {
        List<Bus> result = new ArrayList<>(input);
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