package app.collection;

import app.model.Bus;
import app.validation.ValidationException;

import java.util.ArrayList;
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
        int newCapacity = elements.length + (elements.length >> 1);
        Bus[] newElements = new Bus[newCapacity];
        for (int i = 0; i < size; ++i) {
            newElements[i] = elements[i];
        }
        return newElements;
    }

    @Override
    public void add(Bus bus) {
        for (int i = 0; i < size; i++) {
            if (elements[i].getRegNumber().equals(bus.getRegNumber())) {
                String message = "regNumber " + bus.getRegNumber() + " уже существует.";
                throw new ValidationException(message);
            }
        }
        if (size == elements.length) {
            elements = grow();
        }
        elements[size] = bus;
        size += 1;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Bus> toList() {
        List<Bus> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(elements[i]);
        }
        return list;
    }
}
