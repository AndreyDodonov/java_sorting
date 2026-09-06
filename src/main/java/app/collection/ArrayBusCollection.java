package app.collection;

import app.model.Bus;

import java.util.List;

/**
 *
 * это просто заготовка (Bus[10]), реализация add() и ресайза при переполнении
 * не написана. НЕЛЬЗЯ заменять elements на java.util.ArrayList — это и есть
 * задача Доп.3, писать свою логику ресайза массива вручную.
 *
 */
public class ArrayBusCollection implements BusCollection {

    private final Bus[] elements = new Bus[10]; // стартовый размер — можно менять
    private final int size = 0;

    @Override
    public void add(Bus bus) {
        // проверить уникальность regNumber среди elements[0..size-1],
        // при дубликате — throw new ValidationException(...), специальный класс для
        // ошибок валидации
        // если сейчас в main генерировать автобусы, то дубли по госномерам попадают в
        // финальный список, а должны
        // отсекаться как раз здесь
        throw new UnsupportedOperationException("TODO: реализовать add()");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Bus> toList() {
        throw new UnsupportedOperationException("TODO: реализовать toList()");
    }
}
