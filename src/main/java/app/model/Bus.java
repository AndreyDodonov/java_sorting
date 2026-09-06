package app.model;

import app.validation.ValidationException;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Модель автобуса.
 * <ul>
 *  <li>Иммутабельная — единственный способ создать объект: Bus.builder()...build().</li>
 *  <li>id генерируется автоматически (AtomicLong), не задаётся снаружи.</li>
 *  <li>Уникальность regNumber в рамках коллекции проверяется НЕ здесь, а в BusCollection.add() </li>
 *   Builder валидирует только форму одного объекта, не знает о других уже созданных Bus.
 * </ul>
 */
public final class Bus {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);
    private final long id;
    private final int routeNumber;
    private final String regNumber;
    private final String model;
    private final int mileage;
    private final String note;
    private final boolean operational;

    private Bus(Builder b) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.model = b.model;
        this.routeNumber = b.routeNumber;
        this.regNumber = b.regNumber;
        this.mileage = b.mileage;
        this.note = (b.note == null) ? "" : b.note;
        this.operational = b.operational;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Bus{id=%d, regNumber='%s', routeNumber=%d, model='%s', mileage=%d, operational=%b, note='%s'}"
            .formatted(id, regNumber, routeNumber, model, mileage, operational, note);
    }

    public long getId() {
        return id;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public String getNote() {
        return note;
    }

    public boolean isOperational() {
        return operational;
    }

    public static final class Builder {

        private int routeNumber;
        private String regNumber;
        private String model;
        private int mileage;
        private String note;
        private boolean operational;

        private Builder() {

        }

        public Builder routeNumber(int routeNumber) {
            this.routeNumber = routeNumber;
            return this;
        }

        public Builder regNumber(String regNumber) {
            this.regNumber = regNumber;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder operational(boolean operational) {
            this.operational = operational;
            return this;
        }

        public Bus build() {
            requireNonBlank(regNumber, "regNumber");
            requireNonBlank(model, "model");
            if (mileage < 0) {
                throw new ValidationException("Пробег не может быть ниже 0: " + mileage);
            }
            if (routeNumber <= 0) {
                throw new ValidationException("Номер маршрута не может быть ниже 1: " + routeNumber);
            }
            if (note != null && note.length() > 200) {
                throw new ValidationException("Примечание не может быть длиннее 200 символов, а сейчас: " + note.length());
            }

            return new Bus(this);
        }

        private void requireNonBlank(String value, String field) {
            if (value == null || value.isBlank()) {
                throw new ValidationException("Обязательное поле: " + field);
            }
        }

    }

}
