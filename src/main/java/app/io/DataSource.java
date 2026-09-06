package app.io;

import app.model.Bus;

import java.util.List;

/**
 * Владелец модуля: впиши своё имя (акк гитхаба) (feature/io).
 *
 * <p>
 * Поведение при невалидной записи различается по реализациям:
 * <ul>
 * <li>{@link FileDataSource}, {@link RandomDataSource} — невалидная запись
 * пропускается, накапливается в {@link LoadResult#rejectedWarnings()} и
 * возвращается вместе с успешно загруженными записями. Остальные записи
 * обрабатываются дальше — чтобы из-за пары кривых строк не рушить весь
 * флоу.</li>
 * <li>{@link ManualDataSource} — при невалидном вводе пользователю сразу
 * сообщается об ошибке и предлагается ввести запись заново, без пропуска;
 * {@link LoadResult#rejectedWarnings()} в этом случае обычно остаётся
 * пустым.</li>
 * </ul>
 */
public interface DataSource {
    LoadResult load(int count);

    /**
     * @param loaded
     *            массив записей (автобусов) успешно загруженных
     * @param rejectedWarnings
     *            причины, по которым отдельные записи были отброшены (пустой
     *            список, если отбрасывать было нечего)
     */
    record LoadResult(List<Bus> loaded, List<String> rejectedWarnings) {

    }
}
