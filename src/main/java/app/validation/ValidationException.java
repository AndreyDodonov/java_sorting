package app.validation;

/**
 * Бросается при невалидных бизнес-данных
 * Ловится отдельно от прочих RuntimeException,
 * что бы Main мог показать понятную ошибку ввода,
 * а не падать целиком
 */

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
