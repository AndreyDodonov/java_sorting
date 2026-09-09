package app.collection;

import app.model.Bus;
import app.validation.ValidationException;

import java.util.List;

/**
 *
 * это просто заготовка (Bus[10]), реализация add() и ресайза при переполнении
 * не написана. НЕЛЬЗЯ заменять elements на java.util.ArrayList — это и есть
 * задача Доп.3, писать свою логику ресайза массива вручную.
 *
 */
public class ArrayBusCollection implements BusCollection {
    private static final int DEFAULT_CAPACITY = 10;
    private Bus[] elements;
    private int size = 0;

    ArrayBusCollection() {
        this.elements = new Bus[DEFAULT_CAPACITY];
    }

    private Bus[] grow() {
        int newCapacity = elements.length + elements.length >> 1;
        Bus[] newElements = new Bus[newCapacity];
        for (int i = 0; i < size; ++i) {
            newElements[i] = elements[i];
        }
        return newElements;
    }

    @Override
    public void add(Bus bus) {

        for (Bus element : elements) {
            if (element.getRegNumber() == bus.getRegNumber()) {
                throw ValidationException("regNumber " + bus.getRegNumber() + " already exist");
            }
            if (size == elements.length) {
                elements = grow();
            }
        }
        elements[size] = bus;
        size += 1;

        // проверить уникальность regNumber среди elements[0..size-1],
        // при дубликате — throw new ValidationException(...), специальный класс для
        // ошибок валидации
        // если сейчас в main генерировать автобусы, то дубли по госномерам попадают в
        // финальный список, а должны
        // отсекаться как раз здесь
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Bus> toList() {
        List<Bus> list = new List<>();
        for (Bus element : elements) {
            list.add(element);
        }
        return list;
    }
}
