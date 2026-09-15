package app.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import app.collection.BusCollection;
import app.collection.ArrayBusCollection;
import app.model.Bus;

public class EvenOnlySortDecorator implements SortStrategy {

    private final SortStrategy strategy;

    public EvenOnlySortDecorator(SortStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public BusCollection sort(BusCollection input, Comparator<Bus> comparator) {

        BusCollection result = new ArrayBusCollection(input);
        int n = result.size();

        BusCollection evenBuses = new ArrayBusCollection();
        List<Integer> evenPositions = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (result.get(i).getMileage() % 2 == 0) {
                evenBuses.add(result.get(i));
                evenPositions.add(i);
            }
        }

        evenBuses = strategy.sort(evenBuses, comparator);

        for (int i = 0; i < evenPositions.size(); i++) {
            result.set(evenPositions.get(i), evenBuses.get(i));
        }

        return result;
    }

    @Override
    public String name() {
        return strategy.name() + " с выбором чётных";
    }

}
