package app.concurrency;

import app.model.Bus;
import app.model.BusField;

import java.util.List;

/**
 * Владелец модуля: впиши своё имя/ник на гитхабе, Доп. задание 4 —
 * многопоточный подсчёт количества вхождений заданного значения поля в
 * коллекции. Данные разбиваются на чанки, каждый чанк считает свой поток,
 * результаты суммируются.
 */
public interface OccurenceCounter {
    long count(List<Bus> data, BusField field, Object targetValue, int threadCount) throws InterruptedException;
}
