[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=AndreyDodonov_java_sorting&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=AndreyDodonov_java_sorting)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=AndreyDodonov_java_sorting&metric=coverage)](https://sonarcloud.io/summary/new_code?id=AndreyDodonov_java_sorting)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=AndreyDodonov_java_sorting&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=AndreyDodonov_java_sorting)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=AndreyDodonov_java_sorting&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=AndreyDodonov_java_sorting)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=AndreyDodonov_java_sorting&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=AndreyDodonov_java_sorting)
# Учебный проект: Консольная сортировка (Класс Bus)


Программа для сортировки кастомных объектов с использованием паттерна «Стратегия».

## 👥 Команда проекта

* **Тимлид (Модель + Меню):** Андрей (@AndreyDodonov)
* **Стратегии сортировки:** Радмир (@Radmirs)
* **Кастомная коллекция:** Иван (@Isin314)
* **Ввод/Вывод (I/O):** Дмитрий (@UskorevD)
* **Многопоточность:** Денис (@dencharski )

---

## 🛠 Технические требования и Code Style

* **Java Version:** 21
* **Сборщик:** Maven
* **Именование веток:**
    * `feature/main-integration` — тимлид: финальная сборка Main, интеграция модулей
    * `feature/sorting-strategies` — оставшиеся 3 алгоритма сортировки (Strategy)
    * `feature/custom-collection` — кастомная коллекция (Доп.3)
    * `feature/io` — файл/рандом/ручной ввод, запись результатов (Доп.2)
    * `feature/concurrency` — многопоточный подсчёт вхождений (Доп.4)
* **Конвенции кода:**
    * CamelCase для классов (`BusStrategy`), lowerCamelCase для переменных (`busCount`).
    * Все методы и переменные на английском языке.
    * **Запрет:** Использовать встроенные `Arrays.sort()`, `Collections.sort()` и стандартные структуры из `java.util.*`
      там, где требуется кастомная реализация.
* **Форматирование:**
    * Кодировка - только UTF-8 (обычно используется по умолчанию)
    * В корень проекта добавлен .editorconfig, что бы у всех было
      одинаковое форматирование. Перед коммитом не забываем делать автоформатирование (Ctrl + Alt + L в IDEA).

---

## 🚀 Workflow для команды (Как вносить изменения)

1. Сделать `git pull origin main`, чтобы забрать свежие изменения.
2. Создать свою ветку: `git checkout -b feature/your-feature-name`.
3. Закоммитить код и запушить ветку: `git push origin feature/your-feature-name`.
4. Создать **Pull Request** на GitHub в ветку `main`.
5. Дождаться ревью от Тимлида и мёрджа.

---

## 📦 Как запустить проект

1. Клонировать репозиторий:
   `git clone https://github.com/username/project.git`
2. Скомпилировать и запустить через Maven / IDE:
   `mvn clean package`
   `java -jar target/app.jar`
