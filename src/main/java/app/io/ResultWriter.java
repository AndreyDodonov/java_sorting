package app.io;

import app.collection.BusCollection;

import java.io.IOException;

/**
 * Владелец модуля: впиши имя/ник на гитхабе Доп. задание 2 запись результатов
 * сортировки в файл в режиме добавления (append), а не перезаписи: при
 * повторном вызове новые записи дописываются в конец файла, старое содержимое
 * не стирается. Здесь IOException, а не ValidationException, так как тут ошибки
 * уже не от бизнес логики идут
 */
public interface ResultWriter {

    /**
     * @param sorted
     *            уже отсортированные данные для записи
     * @param filePath
     *            путь к файлу; если файла не существует — создаётся
     * @throws IOException
     *             при ошибке доступа к файлу (нет прав, диск занят и т.д.)
     */
    void appendResults(BusCollection sorted, String filePath) throws IOException;
}
