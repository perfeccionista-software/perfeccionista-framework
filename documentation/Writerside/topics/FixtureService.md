# FixtureService

Что такое фикстура?
Это некоторое предусловие, необходимое для выполнения теста. Это может быть:
 - создание каких-либо данных, которые используются в тесте
 - изменение какого-либо параметра системы
 - настройка заглушки
 - и т.п.
При выполнении фикстуры в начале теста, зачастую, ее необходимо откатить после выполнения теста.

При работе с фикстурами на проектах возникает множество задач, решение которых
приводит к увеличению кода в тестовых классах и к проблемам стабильности выполнения тестов.
Какие проблемы приходится решать:
- Найти нужную фикстуру (актуально для больших проектов)
- Организовать хранилище выполненных фикстур и объектов, созданных при выполнении этой фикстуры
- Откатить фикстуры после выполнения тестов в порядке, обратном тому, что был при создании
- Организовать обработку исключений, которые могут возникнуть при выполнении и откате фикстур
- Организовать добавление информации о выполненных и откаченных фикстурах в отчеты

Чтобы этот функционал не загромождал тесты и гибко настраивался, он был вынесен в `FixtureService`.

How to work with FixtureService
-------------------------------
Выможете напрямую работать с FixtureService
```Java
class FixtureTest extends TestWithEnvironment {

    private User user;

    @BeforeEach
    void beforeEach(FixtureService fixtureService) {
        FixtureSetUpResult<User> userFixtureResult = fixtureService.executeFixture("Создание пользователя");
        // Обертка FixtureSetUpResult позволяет провести обработку результата выполнения фикстуры,
        // который может быть как успешным, так и неуспешным
        user = userFixtureResult.getNotNullResult();
    }

    @Test
    void fixtureServiceInitializationTest(Environment environment, FixtureService fixtureService) {
        // Для создания фикстуры часто требуются параметры
        FixtureSetUpResult<Account> userAccountFixtureResult = fixtureService
                .executeFixture("Создание счета", FixtureParameters.createWithParameter("User", user));
        Account userAccount = userAccountFixtureResult.getNotNullResult();
    }

}
```

Но удобнее вынести логику создания фикстур в отдельный класс и испоьзовать его в тестах
```Java
class Fixtures {

    public static User user() {
        FixtureSetUpResult<User> userFixtureResult = fixtureService().executeFixture(new UserFixture());
        return userFixtureResult.getNotNullResult();
    }

    public static Account userAccount(User user) {
        FixtureParameters fixtureParameters = FixtureParameters.createWithParameter("User", user);
        FixtureSetUpResult<Account> userAccountFixtureResult = fixtureService()
                .executeFixture(new UserAccount().withParameters(fixtureParameters));
        return userAccountFixtureResult.getNotNullResult();
    }
    
    private static FixtureService fixtureService() {
        return Environment.getForCurrentThread().getService(FixtureService.class);
    }

}
```

Тогда код тестов будет выглядеть более коротким и читаемым
```Java
class FixtureTest extends TestWithEnvironment {

    @Test
    void testWithFixtures() {
        User user = Fixtures.user();
        Account userAccount = Fixtures.userAccount(user);
    }

}
```

FixtureService lifecycle
------------------------
- Создание объекта. Имплементация `FixtureService` должна иметь конструктор по умолчанию.
- Инициализация конфигурации сервиса. У `FixtureService` есть конфигурация по умолчанию,
  поэтому, если в `EnvironmentConfiguration` не задана конфигурация для этого сервиса, то
  будет использоваться `DefaultFixtureServiceConfiguration`. Через конфигурацию можно указать
  пакеты, в которых будет произведен поиск классов фикстур и если это именованная фикстура,
  то она будет доступна для выполнения через ее имя (аннотация `@Name` над классом).
  Подробнее о настройках этой конфигурации ниже.
- Выполнение фикстур. При выполнении фикстур, эти объекты записываются в очередь, которая
  находится внутри сервиса. Соответственно, экземпляр сервиса знает какие фикстуры были
  выполнены в рамках теста и в каком порядке. Для того чтобы эта модель работала, необходимо
  выполнять фикстуры через сервис:
  ```Java
  fixtureService.executeFixture(Fixture<S, T> fixture);
  fixtureService.executeFixture(String fixtureName);
  fixtureService.executeFixture(String fixtureName, FixtureParameters fixtureParameters);
  fixtureService.executeFixture(Class<? extends Fixture<S, T>> fixtureClass);
  fixtureService.executeFixture(Class<? extends ParametrizedFixture<S, T, P>> fixtureClass, P fixtureParameters);
  ```
  При выполнении этого метода на объекте фикстуры вызываются поочередно методы:
  `setUp()` - непосредственное выполнение фикстуры, которое возвращает `FixtureSetUpResult<T>`.
  `process()` - обработка результата `FixtureSetUpResult<T>`, который может содержать как
  результат работы фикстуры (например, созданную модель или установленное значение),
  так и ошибку выполнения.
- Откат фикстур. После завершения теста, при вызове хука `afterEach()`, происходит
  откат фикстур в порядке обратном созданию. При этом, на объекте фикстуры вызываются поочередно методы:
  `tearDown()` - непосредственное выполнение отката фикстуры, которое возвращает `FixtureTearDownResult<T>`.
  `process()` - обработка результата `FixtureTearDownResult<T>`, который может содержать как
  результат работы jnrfnf фикстуры (например, возвращенное значение), так и ошибку выполнения.

DefaultFixtureServiceConfiguration
----------------------------------
Для инициализации `FixtureService` можно использовать базовый класс конфигурации.
Его реализация позволяет регулировать набор фикстур, которые будут использоваться в `FixtureService`.
Сканирование классов фикстур выполняется по списку пакетов, который можно задать несколькими способами:
- если среди переменных окружения задано свойство `perfeccionista.fixtures.scanPackages`, то
  список пакетов для сканирования классов фикстур, разделенных `,` выбирается оттуда.
- если такого свойства среди переменных окружения нет, то выполняется поиск конфигурационного файла
  `perfeccionista.properties`. Если файл есть и в нем задано свойство `perfeccionista.fixtures.scanPackages`,
  то список пакетов для сканирования сервисов, разделенных `,` выбирается оттуда.
- если такого файла или свойства в этом файле нет, то сканирования не происходит

Fixtures
--------
Как выглядит интерфейс фикстуры?
```Java
public interface Fixture<S, T> {

    @NotNull FixtureSetUpResult<S> setUp();

    @NotNull FixtureTearDownResult<T> tearDown();

}
```
Где:
- `<S>` - тип возвращаемого значения для выполнения фикстуры
- `<T>` - тип возвращаемого значения для выполнения отката фикстуры

В интерфейсе фикстуры есть два метода:
- `setUp()`, внутри которого выполняется сама логика предусловия.
  Этот метод возвращает `FixtureSetUpResult`, который может содержать как
  объект выполнения фикстуры (созданную сущность, актуальное значение изменяемого параметра и тп),
  так и ошибку выполнения фикстуры.
  Как обработать этот результат пользователь должен решить сам. Если выполнение фикстуры критично
  для теста, то полученный Exception нужно пробросить дальше, если же фикстура не критична для
  выполнения теста, то ошибку можно обработать (например, записать в лог) и продолжить тест.
- `tearDown`, внутри которого происходит откат системы к первоначальному состоянию.
  Этот метод возвращает `FixtureTearDownResult`, который может содержать как
  объект выполнения отката фикстуры (true/false, возвращенное значение изменяемого параметра и тп),
  так и ошибку выполнения отката фикстуры.
  Как обработать этот результат пользователь должен решить сам. Если выполнение отката фикстуры критично
  для последующих тестов, то полученный Exception нужно пробросить дальше,
  если же откат фикстуры не критичен для выполнения теста, то ошибку можно обработать
  (например, записать в лог) и продолжить тесты.

Ниже приведен пример реализации фикстуры:
```Java
@Name("Test fixture name")
public class TestFixtureOne implements Fixture<Integer, Boolean> {

    @Override
    public @NotNull FixtureSetUpResult<Integer> setUp() {
        return FixtureSetUpResult.of(777);
    }

    @Override
    public @NotNull FixtureTearDownResult<Boolean> tearDown() {
        return FixtureTearDownResult.of(true);
    }

}
```

`@Name("Test fixture name")` - имя фикстуры по которому она будет доступна через `FixtureService`,
после выполнения сканирования пакета, содержащего класс этой фикстуры.

Work with FixtureSetUpResult
----------------------------

Логика выполнения фикстуры разделена на 3 этапа:
- Выполнение кода фикстуры и получение результата. Важно, чтобы результат формировался,
  даже если это результат с `Exception`.
- Обработка результата с помощью общего `FixtureResultProcessor`. Базовая имплементация этого интерфейса
  не производит никакой обработки, но с ее помощью можно, например, выводить результаты выполнения фикстуры в лог
  или прикладывать к Allure-отчету.
- Обработка исключения при выполнении фикстуры с помощью персонального обработчика исключений. По умолчанию,
  при получении `Exception`, он пробрасывается выше. Однако, если вам необходимо написать
  какую-то более сложную логику (например, какие-то конкретные `Exception` не являются критичными и их нужно заглушить),
  то можно это сделать следующим образом:
  ```Java
  @Override
  public @NotNull FixtureSetUpResult<Integer> setUp() {
      // здесь будет какая-то логика создания фикстуры при которой выбрасывается exception
      return FixtureSetUpResult.exception(myCustomException)
              .setExceptionProcessor(runtimeException -> {
                  if (CastUtils.isSubtypeOf(runtimeException, MyCustomException.class)) {
                      log.info(runtimeException.toString());
                  } else {
                      throw runtimeException;
                  }
              });
  }

  ```

Work with FixtureTearDownResult
-------------------------------
Логика выполнения отката фикстуры также разделена на 3 этапа:
- Выполнение кода отката фикстуры и получение результата. Важно, чтобы результат формировался,
  даже если это результат с `Exception`.
- Обработка результата с помощью общего `FixtureResultProcessor`. Базовая имплементация этого интерфейса
  не производит никакой обработки, но с ее помощью можно, например, выводить результаты выполнения фикстуры в лог
  или прикладывать к Allure-отчету.
- Обработка результата или исключения при выполнении отката фикстуры с помощью персонального обработчика.
  По умолчанию, при получении `Exception`, он пробрасывается выше, а результат выполнения отката фикстуры игнорируется.
  Однако, если вам необходимо написать какую-то более сложную логику обработки результата или Exception,
  то можно это сделать следующим образом:
  ```Java
  @Override
  public @NotNull FixtureTearDownResult<String> tearDown() {
      // здесь будет какая-то логика отката фикстуры
      return FixtureTearDownResult.of("custom parameter value")
              .setResultProcessor((param, runtimeException) -> {
                  if (Objects.nonNull(exception) {
                      throw runtimeException;
                  } else {
                      Allure.attachment("Восстановленное значение параметра", param);
                  }
              });
  }
  ```
  