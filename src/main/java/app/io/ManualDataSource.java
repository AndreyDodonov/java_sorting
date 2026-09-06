package app.io;

/**
 * TODO: ручной ввод через Scanner. При невалидных данных — сразу сообщать об
 * ошибке и просить ввести заново, НЕ пропускать запись молча в отличие от
 * чтения из файла или рандомной генерации (см. DataSource).
 */
public class ManualDataSource implements DataSource {
    @Override
    public LoadResult load(int count) {
        throw new UnsupportedOperationException("Реализовать ручной ввод");
    }
}
