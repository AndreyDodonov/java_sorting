package app.collection;

import app.model.Bus;
import app.validation.ValidationException;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Владелец модуля: isin314(feature/custom-collection)
 * <p>
 * НЕЛЬЗЯ оборачивать java.util.ArrayList/LinkedList внутри (по условию задачи)
 * <p>
 * ValidationException из add() если автобус с таким же рег номером (regNumber)
 * уже есть в коллекции. У нас же не может быть автобусов с одинаковыми
 * госномерами, поэтому это ошибка валидации
 * <p>
 * может быть ещё что-то надо будет, но пока я вижу нужду в этих трёх методах
 */

public interface BusCollection extends Iterable<Bus> {

    void add(Bus bus);

    void set(int index, Bus bus);

    Bus get(int index);

    boolean isEmpty();

    int size();

    Bus[] subList(int from, int to);

    default Stream<Bus> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    static BusCollection of(Bus... buses) {
        ArrayBusCollection array = new ArrayBusCollection();
        for (Bus bus : buses) {
            array.add(bus);
        }
        return array;
    }

    List<Bus> toList();

    static BusCollection fromStream(Stream<Bus> stream) {
        BusCollection collection = new ArrayBusCollection();
        stream.forEach(bus -> {
            try {
                collection.add(bus);
            } catch (ValidationException e) {
                // скипаем невалидные данные, обработаются с накоплением в другом месте
            }
        });
        return collection;
    }
}
