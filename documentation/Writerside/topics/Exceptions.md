# Exceptions

В тестовых фреймворках часто приходится работать с отладочной информацией, которая
находится на разных уровнях. Проблема заключается в том, что в том месте, откуда
выбрасывается `Exception` нет всей информации, которая необходима для отладки теста и
для полноценного тестового отчета. Эту информацию необходимо добавить выше по стеку.
Однако, базовый класс `Exception` не дает такой возможности. Можно обернуть низкоуровневый
`Exception` новым с добавлением информации, но это часто не нужно. Достаточно пробросить
низкоуровневую ошибку, но добавить к ней дополнительную информацию.

Следующей проблемой является то, что некоторые ошибки, возникающие при тестировании
приложений являются ожидаемыми и при их получении не нужно прерывать тест, а нужно
повторять проверку до выполнения какого-то условия.
Например, при первой проверке наличия элемента на экране, его может не быть - это нормальная ситуация,
поскольку компоненты веб-страницы отрисовываются асинхронно и для этого нужно какое-то время.
Можно для каждого отдельного случая и исключения описывать какие типы `Exception` должны
обрабатываться, а какие прокидываться выше, но это ведет к усложнению логики.
Удобнее иметь отдельный механизм и признак по которому можно понять - какой тип у `Exception`?
Есть еще один признак, который полезно понимать при анализе причин падения тестов:
относится ли ошибка к неисправностям инфраструктуры. Мы можем не анализировать тесты,
которые, например, упали по причине неполадок сети или недоступности Selenoid, а сразу
отфильтровать такие тесты и перезапустить после устранения проблемы.

Кроме того, в процессе выполнения циклических проверок ошибка может меняться, например,
при загрузке страницы, сначала элемент может быть недоступен, потом его перекрывает другой элемент,
а после проверка падает на том, что текстовка не соответствует ожидаемой.
В этом случае, если в отчете показать всю историю изменения типов Exception во время проверок,
то это даст более полную картину того, что происходило.

Для решения этой проблемы появился интерфейс `PerfeccionistaException`.

PerfeccionistaException
-----------------------

```Java
public interface PerfeccionistaException {

    boolean isProcessed();

    PerfeccionistaException setProcessed(boolean processed);

    boolean isService();

    PerfeccionistaException setService(boolean service);

    Optional<Attachment> getAttachment();

    PerfeccionistaException setAttachment(@Nullable Attachment attachment);

    PerfeccionistaException addFirstAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry);

    PerfeccionistaException addLastAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry);

    PerfeccionistaException addSuppressedException(@NotNull Throwable throwable);

    PerfeccionistaException setAttachmentProcessor(@NotNull AttachmentProcessor processor);

    LocalDateTime getExceptionTimestamp();

    String getAttachmentDescription();

    String getLocalizedMessage();

    String getMessage();

    String stacktraceToString();

}
```
Фреймворк представляет 2 основные реализации этого интерфейса:
- `PerfeccionistaRuntimeException`
- `PerfeccionistaAssertionError`

PerfeccionistaException methods
-------------------------------

- Пара методов, которые отвечают за установку и получение признака обрабатываемого Exception.
  То есть тех Exception, при которых тест не нужно прерывать,
  а нужно продолжать пытаться выполнить проверку в рамках выставленного таймаута.
  ```Java
  boolean isProcessed()
  PerfeccionistaException setProcessed(boolean processed)
  ```
- Пара методов, которые отвечают за установку и получение признака сервисного Exception.
  То есть тех Exception, при которых тест падает из-за инфраструктурных проблем.
  ```Java
  boolean isService()
  PerfeccionistaException setService(boolean service)
  ```
- Метод возвращает объект `Attachment`, который содержит все собранные вложения, собранные
  внутри этого `Exception` на всех уровнях его обработки.
  ```Java
  Optional<Attachment> getAttachment()
  ```
- Метод позволяет установить объект `Attachment`, для этого `Exception`.
  ```Java
  PerfeccionistaException setAttachment(@Nullable Attachment attachment)
  ```
- Пара методов, которые позволяют добавить вложения в начало или в конец списка всех вложений.
  ```Java
  PerfeccionistaException addFirstAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry)
  PerfeccionistaException addLastAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry)
  ```
- Метод позволяет добавить `Exception`, который был подавлен при обработке текущего `Exception`.
  ```Java
  PerfeccionistaException addSuppressedException(@NotNull Throwable throwable)
  ```
- Метод позволяет установить кастомный обработчик для Attachment. Обработчик отвечает
  за перевод содержимого вложения в строку. Это необходимо, например, для добавления
  Attachment к Allure-отчетам или выводу в лог.
  ```Java
  PerfeccionistaException setAttachmentProcessor(@NotNull AttachmentProcessor processor)
  ```
- Метод позволяет получить время возникновения ошибки
  ```Java
  LocalDateTime getExceptionTimestamp()
  ```
- Метод позволяет получить текстовое описание всех вложений, содержащихся в этом `Exception` с
  помощью `AttachmentProcessor`.
  ```Java
  String getAttachmentDescription()
  ```

How to create new Exception?
----------------------------
Для создания нового Exception необходимо унаследоваться от одной из реализаций:
- `PerfeccionistaRuntimeException`
- `PerfeccionistaAssertionError`

Есть особенность, связанная с тем, что в зависимости от типа проверки при одном и том же Exception
тест должен падать по разному:
- как сломанный тест (Broken)
- как тест, упавший по Assert-ошибке

Первая ситуация:
Вы тестируете в своем приложении функционал, который чистит директорию. Вы пишете тест и
в этом тесте проверяете, что файл в директории удален. Если нет, то вы должны выбросить что-то типа:
`FileAlreadyExistsAssertionError`, чтобы в отчете было сразу понятно, что тестируемый функционал не отработал.

Вторая ситуация:
Вы в рамках какой-то утилиты записываете куда-то служебный файл и до этого проверяете, что такого файла там нет.
И если он там есть, то нужно выбросить эксепшн. Данная ошибка не является результатом бизнес-проверки,
поэтому там должно выбрасываться что-то типа `FileAlreadyExistsRuntimeException`.

Создавать 2 отдельных класса исключений в любом случае придется, однако, чтобы проще ориентироваться среди
этих исключений можно использовать такой подход:

```Java
public interface FileExists extends Reason {

    // Удобные методы создания экземпляров ошибок

    static FileExistsAssertionError assertionError(@NotNull String message) {
        return new FileExistsAssertionError(message);
    }

    static FileExistsAssertionError assertionError(@NotNull String message, @NotNull Throwable cause) {
        return new FileExistsAssertionError(message, cause);
    }

    static FileExistsException exception(@NotNull String message) {
        return new FileExistsException(message);
    }

    static FileExistsException exception(@NotNull String message, @NotNull Throwable cause) {
        return new FileExistsException(message, cause);
    }
    
    // Создаем классы ошибок, которые наследуются от RuntimeException и от AssertionError

    class FileExistsAssertionError extends PerfeccionistaAssertionError implements FileExists {

        FileExistsAssertionError(String message) {
            super(message);
        }

        FileExistsAssertionError(String message, Throwable cause) {
            super(message, cause);
        }

    }

    class FileExistsException extends PerfeccionistaRuntimeException implements FileExists {

        FileExistsException(String message) {
            super(message);
        }

        FileExistsException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
```
Интерфейс `Reason` - маркерный интерфейс, который не содержит каких-либо методов.
По нем мы можем понять, что перед нами какая-то причина, по которой может быть выброшен
`Exception` или `AssertionError`. По этому интерфейсу можно фильтровать ошибки на уровне общего
обработчика ошибок, например, `AllurePlugin`.

How to add an Attachment?
-------------------------
Интерфейс PerfeccionistaException поддерживает работу с вложениями, которые можно
обработать.
```Java
throw RegisterDuplicate.exception("DataSource с указанным именем уже зарегистрирован")
    .addLastAttachmentEntry(
        TextAttachmentEntry.of("First DataConverter class", processedClass.getCanonicalName()))
    .addLastAttachmentEntry(
        TextAttachmentEntry.of("Second DataConverter class", cachedDataConverterClasses.get(dataConverterName).getCanonicalName()));
```
How to change Attachment representation?
----------------------------------------
Если по какой-то причине стандартный формат преобразования вложений в строку не
подходит, то можно реализовать интерфейс `AttachmentProcessor` и применить его
перед выводом строкового представления:
```Java
catch(PerfeccionistaException exception) {
    String attachmentDescription = exception
        .setAttachmentProcessor(new CustomAttachmentProcessor())
        .getAttachmentDescription();
}
```

фикстурам
датасорсам и конвертеры
велью
таймаут
