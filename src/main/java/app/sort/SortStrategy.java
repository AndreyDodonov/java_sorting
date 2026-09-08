package app.sort;

import java.util.Comparator;
import java.util.List;

import app.model.Bus;

/**
 * Владелец модуля: Радмир Сулейманов https://github.com/radmirs
 * (feature/sorting-strategies).
 *
 * <p>
 * Нужно реализовать минимум 4 алгоритма (например Bubble, Selection, Insertion,
 * Quick/Merge Sort, или другие, но эти вроде попроще) — каждый как отдельный
 * класс, реализующий этот интерфейс.
 *
 * <p>
 * Запрещено по заданию: Collections.sort/Arrays.sort или любые готовые
 * реализации сортировки внутри sort(). Разрешено: получать Comparator снаружи
 * (см. {@link BusComparators}) и использовать его для сравнения элементов
 * внутри своего алгоритма. Постараемся добавить ещё замер времени сортировки,
 * для этого добавил поле name Сортировки НЕ должны мутировать список (хотя
 * защита от этого предусмотрена). То есть надо делать защитное копирование (как
 * вариант) и возвращать новый список
 */
public interface SortStrategy {

    /**
     * @param input
     *            исходные данные — метод НЕ должен их мутировать
     * @param comparator
     *            компаратор для сравнения элементов (см. BusComparators.byField)
     * @return новый отсортированный список
     */
    List<Bus> sort(List<Bus> input, Comparator<Bus> comparator);

    /**
     * Название алгоритма — для вывода в консоли при сравнении времени выполнения.
     */
    String name();

    default void swap(List<Bus> list, int i, int j) {
        Bus tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
