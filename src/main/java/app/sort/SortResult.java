package app.sort;

import app.collection.BusCollection;

/**
 * Результат одного прогона сортировки и времени выполнения
 */
public record SortResult(BusCollection sorted, long elapsedNanoSec, String algoName) {

    // в миллисекундах
    public double elapsedTimeInMS() {
        return elapsedNanoSec / 1000000.0;
    }
}
