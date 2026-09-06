package app.sort;

import app.model.Bus;

import java.util.Comparator;
import java.util.List;

/**
 * контекст паттерна стратегия. Подсовываем имя сортировки и одинаково везде
 * получаем замер времени. В общем что угодно делаем одинаково, получая
 * результат, поменяв только название стратегии
 */
public class SortContext {

    private SortStrategy strategy;

    public SortContext(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public SortResult executeSort(List<Bus> input, Comparator<Bus> comparator) {
        long start = System.nanoTime();
        List<Bus> sorted = strategy.sort(input, comparator);
        long elapsed = System.nanoTime() - start;
        return new SortResult(sorted, elapsed, strategy.name());
    }
}
