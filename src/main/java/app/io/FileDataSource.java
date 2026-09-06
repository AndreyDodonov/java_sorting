package app.io;

/**
 * TODO: чтение и построчная валидация данных из файла.
 * Невалидные строки — пропускать и копить в сводку (см. DataSource).
 */
public class FileDataSource implements DataSource {

    private final String path;

    public FileDataSource(String path) {
        this.path = path;
    }

    @Override
    public LoadResult load(int count) {
        throw new UnsupportedOperationException("Реализовать чтение из файла: " + path);
    }
}
