package app.sort;

import app.model.Bus;
import app.model.BusField;

import java.util.Comparator;

/**
 * Фабрика компараторов по полю. Задание прямо разрешает использовать Comparator
 * для кастомной сортировки — сам алгоритм сортировки (SortStrategy) при этом
 * всё равно пишется руками, без Collections.sort/Arrays.sort.
 */
public final class BusComparators {

    private BusComparators() {
    }

    public static Comparator<Bus> byField(BusField field) {
        // в свитч специально не добавил дефолт, на случай расширения енама
        return switch (field) {
            case REG_NUMBER -> Comparator.comparing(Bus::getRegNumber);
            case MODEL -> Comparator.comparing(Bus::getModel);
            case MILEAGE -> Comparator.comparingInt(Bus::getMileage);
        };
    }
}
