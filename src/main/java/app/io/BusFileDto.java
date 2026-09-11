package app.io;

// Для переноса данных между моделью и JSON

public record BusFileDto(int routeNumber, String regNumber, String model, int mileage, String note,
        boolean operational) {
}
