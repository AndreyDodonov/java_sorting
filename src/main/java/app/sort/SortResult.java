package app.sort;

import app.model.Bus;

import java.util.List;

/**
 * Результат одного прогона сортировки и времени выполнения
 */
public record SortResult(List<Bus> sorted, long elapsedNanoSec, String algoName) {

    // в миллисекундах
    public double elapsedTimeInMS() {
        return elapsedNanoSec / 1000000.0;
    }
}
