package app.collection;

import app.model.Bus;
import app.validation.ValidationException;

import java.util.*;

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

    public ArrayBusCollection() {
        this.elements = new Bus[DEFAULT_CAPACITY];
    }

    public ArrayBusCollection(BusCollection buses) {
        this.size = buses.size();
        this.elements = new Bus[size];
        for (int i = 0; i < size; i++) {
            elements[i] = buses.get(i);
        }
    }

    public ArrayBusCollection(Bus... buses) {
        size = buses.length;
        this.elements = new Bus[size];
        for (int i = 0; i < size; i++) {
            elements[i] = buses[i];
        }
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class BusIterator implements Iterator<Bus> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Bus next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[index++];
        }
    }

    @Override
    public Iterator<Bus> iterator() {
        return new BusIterator();
    }

    @Override
    public Bus get(int index) {
        return elements[index];
    }

    @Override
    public void set(int index, Bus bus) {
        elements[index] = bus;
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
    @Override
    public Bus[] subList(int from, int to) {
        Bus[] array = new Bus[to - from];
        for (int i = from, j = 0; i < to; i++, j++) {
            array[j] = elements[i];
        }
        return array;
    }

}
