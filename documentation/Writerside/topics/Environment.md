# Environment

Класс `Environment` является входной точкой ко всему конфигурируемому тестовому окружению.

Для каждого теста должен создаваться отдельный экземпляр `Environment`.
Это гарантирует чистоту окружения теста.

> Оптимизации, связанные с использованием одного экземпляра на разные тесты или потоки
> используйте на свой страх и риск.
{style="warning"}

Environment usage
--------------------------
> Оба примера подходят для параллельного запуска
{style="note"}

Если для каждого теста создается отдельный экземпляр тестового класса.
```java
public class EnvironmentTest {

    private final Environment environment = new Environment(DefaultEnvironmentConfiguration.class);

    @BeforeEach
    void beforeEach() {
        environment.init();
    }

    @AfterEach
    void afterEach() {
        environment.shutdown();
    }

    @Test
    void environmentTestOne() {
        ProjectFixtures fixtures = new ProjectFixtures(environment);
        User user = fixtures.createRandomUser();
        UserAccount account = fixtures.createDefaultUserAccount(user);
    }
}
```

Если все тесты в тестовом классе выполняются на одном экземпляре тестового класса,
то `Environment` необходимо изолировать на уровне потока.
```java
public class EnvironmentTest {

    @BeforeEach
    void beforeEach() {
        Environment.createForCurrentThread(DefaultEnvironmentConfiguration.class)
                .init();
    }

    @AfterEach
    void afterEach() {
        Environment.finalizeForCurrentThread();
    }

    @Test
    void environmentTestOne() {
        Environment environment = Environment.getForCurrentThread();
        ProjectFixtures fixtures = new ProjectFixtures(environment);
        User user = fixtures.createRandomUser();
        UserAccount account = fixtures.createDefaultUserAccount(user);
    }

}
```

> Если вы используете не проекте JUnit5, смотрите раздел JUnit5 Extension
{style="note"}

`Environment` после инициализации содержит в себе набор сконфигурированных сервисов.
Набор и конфигурации каждого из сервисов берутся из конфигурации окружения `EnvironmentConfiguration`,
которая необходима для создания экземпляра `Environment`.

Environment lifecycle
--------------------------

`Environment` имеет несколько фаз жизненного цикла:

- создание экземпляра `Environment` на основе переданной конфигурации.
  ```Java
  Environment environment = new Environment(DefaultEnvironmentConfiguration.class);
  ```
- привязка экземпляра `Environment` к текущему потоку.
  Для создания экземпляра `Environment`, как локального объекта внутри тестового метода этот шаг является опциональным.
  ```Java
  Environment.setForCurrentThread(environment);
  ```
- инициализация конфигурации.
  ```Java
  environment.init();
  ```
  На этом этапе происходит создание и конфигурирование всех экземпляров сервисов, указанных в конфигурации.
  При инициализации сервисов им доступен созданный экземпляр `Environment` и находящиеся в нем `RelatedObjects` (см. документацию ниже).
  Так же на этом этапе происходит вызов метода
  ```Java
  Service.beforeTest()
    ```
  для каждого зарегистрированного сервиса. Если вы используете сервис, который хранит
  объекты в `static` или `ThreadLocal` полях, то в этот момент их нужно приводить в готовность
  (например, вытаскивать из общего пула объект сессии, который будет использоваться в тесте).
- использование экземпляра `Environment` в тесте
- финализация конфигурации.
  ```Java
  environment.shutdown();
  ```
  На этом этапе происходит завершение всех экземпляров сервисов, указанных в конфигурации.
  Здесь происходит вызов метода
  
  ```Java
  Service.afterTest()
  ```
  на каждом зарегистрированном сервисе.
  Если вы используете сервис, который хранит
  объекты в `static` или `ThreadLocal` полях, то в этот момент их нужно завершать
  (например, возвращать объект сессии в пул).
- отвязывание экземпляра `Environment` от текущего потока.
  
Environment methods
-------------------
### Static methods:

- ```java
  Environment.createForCurrentThread(configurationClass, testName);
  ```
  Создание экземпляра Environment на основе класса конфигурации и имени теста и привязка его к текущему потоку.
  В рамках этого метода НЕ выполняется инициализация окружения.
  Если имя теста задано, то оно выводится в заголовке Environment при выводе в лог конфигурации.
- ```java
  Environment.createForCurrentThread(configurationClass);
  ```
  Создание экземпляра Environment на основе класса конфигурации и привязка его к текущему потоку.
  В рамках этого метода НЕ выполняется инициализация окружения.
- ```java
  Environment.createForCurrentThread(configuration, testName);
  ```
  Создание экземпляра Environment на основе экземпляра конфигурации и имени теста и привязка его к текущему потоку.
  В рамках этого метода НЕ выполняется инициализация окружения.
  Если имя теста задано, то оно выводится в заголовке Environment при выводе в лог конфигурации.
- ```java
  Environment.createForCurrentThread(configuration);
  ```
  Создание экземпляра Environment на основе экземпляра конфигурации и привязка его к текущему потоку.
  В рамках этого метода НЕ выполняется инициализация окружения.
- ```java
  Environment.existForCurrentThread();
  ```
  Возвращает true, если к текущему потоку привязан экземпляр Environment.
- ```java
  Environment.getForCurrentThread();
  ```
  Возвращает экземпляр `Environment`, который привязан к текущему потоку или выбрасывает `Exception`.
- ```java
  Environment.setForCurrentThread(environment);
  ```
  Привязывает к текущему потоку передаваемый экземпляр `Environment`.
- ```java
  Environment.finalizeForCurrentThread();
  ```
  Завершает экземпляр `Environment` и отвязывает его от текущего потока.

### Instance methods:

- ```java
  environment.init();
  ```
  Инициализирует текущий экземпляр `Environment` из конфигурации.
  В рамках этого метода заново создаются экземпляры сервисов, заданные в `EnvironmentConfiguration`.
  Экземпляры сервисов создаются согласно заданному порядку.
  На каждом экземпляре сервиса запускается метод 
  ```Java
  Service.beforeTest();
  ```
- ```java
  environment.shutdown();
  ```
  Завершает текущий экземпляр `Environment`.
  На каждом экземпляре сервиса запускается метод
  ```Java
  Service.afterTest();
  ```
- ```java
  environment.addRelatedObject(name, object);
  ```
  Добавляет связанный объект к текущему экземпляру `Environment`. 
- ```java
  environment.getRelatedObjects();
  ```
  Возвращает `Map` со связанными объектами и их именами для текущего экземпляра `Environment`.
- ```java
  environment.register(serviceClass, service);
  ```
  Позволяет добавить в Environment экземпляр созданного сервиса.
- ```java
  environment.getService(serviceClass);
  ```
  Возвращает экземпляр сервиса, ассоциированный с заданным классом сервиса или выбрасывает `Exception`,
  если с заданным классом не ассоциирован экземпляр сервиса.
- ```java
  environment.getOptionalService(serviceClass);
  ```
  Возвращает экземпляр сервиса, обернутый в `Optional`,
  если с переданным классом ассоциирован экземпляр сервиса.
  Или возвращает пустой `Optional`, если с переданным классом ничего не ассоциировано.
- ```java
  environment.getServices();
  ```
  Возвращает все экземпляры сервисов, инициализированные в экземпляре `Environment`.
- ```java
  environment.getServiceClasses();
  ```
  Возвращает все классы сервисов с которыми ассоциированы экземпляры.
- ```java
  environment.getEnvironmentConfiguration();
  ```
  Возвращает текущую конфигурацию из которой был инициализирован `Environment`.


Environment configuration
-------------------------

Основная задача `EnvironmentConfiguration` - вернуть набор сконфигурированных экземпляров сервисов для каждого теста.

Конфигурация позволяет задать:
- набор сервисов
- конфигурацию для каждого из сервисов
- порядок инициализации сервисов
- включить/выключить отдельный сервис в конфигурации

### How to initialize Environment with configuration?

- Явно передать в конструктор `Environment` класс конфигурации или экземпляр класса.
  ```java
  new Environment(DefaultEnvironmentConfiguration.class);
  ```
  Самый простой вариант, но не гибкий, поскольку для того, чтобы изменить класс конфигурации,
  необходимо будет править код тестов, либо код самой конфигурации.
- Использовать `EnvironmentConfigurationResolver`.
  ```java
  new Environment(EnvironmentConfigurationResolver.resolveEnvironmentConfiguration());
  ```
  Этот механизм работает следующим образом:
  - ищет свойство `perfeccionista.environment` среди переменных окружения.
    Если находит, то берет оттуда класс конфигурации и возвращает экземпляр этой конфигурации.
  - если среди системных переменных такого свойства нет, то смотрит файл `perfeccionista.properties` и
    ищет это свойство в нем. Если находит, то возвращает экземпляр указанной конфигурации.
  - если этого файла нет или в нем не задано это свойство,
    то возвращает экземпляр базовой конфигурации: `DefaultEnvironmentConfiguration`

### DefaultEnvironmentConfiguration

Для инициализации `Environment` можно использовать базовый класс конфигурации.
Его реализация позволяет регулировать набор сервисов, которые будут использоваться в `Environment`.
Сканирование сервисов для инициализации выполняется по списку пакетов, который можно задать несколькими способами:
- если среди переменных окружения задано свойство `perfeccionista.services.scanPackages`, то
  список пакетов для сканирования сервисов, разделенных `,` выбирается оттуда.
- если такого свойства среди переменных окружения нет, то выполняется поиск конфигурационного файла
  `perfeccionista.properties`. Если файл есть и в нем задано свойство `perfeccionista.services.scanPackages`,
  то список пакетов для сканирования сервисов, разделенных `,` выбирается оттуда.
- если такого файла или свойства в этом файле нет, то используется базовый набор пакетов для сканирования:
  ```text
  io.perfeccionista.framework.value
  io.perfeccionista.framework.datasource
  io.perfeccionista.framework.fixture
  ```
При сканировании экземпляр сервиса создается либо с default конфигурацией, если она указана над классом сервиса,
либо без конфигурации, если она не указана.

### How to create a custom EnvironmentConfiguration

Самый простой вариант - наследоваться от класса `DefaultEnvironmentConfiguration` и
переопределить метод `getServiceConfigurations()`.
Этот метод возвращает набор уникальных конфигураций сервисов `ConfiguredServiceHolder`,
которые регистрируются в `Environment`.
Уникальность сервиса в рамках конфигурации определяется классом по которому он регистрируется.

> Класс по которому регистрируется сервис и класс-имплементация сервиса могут отличаться,
> НО имплементация должна наследоваться от класса по которому происходит регистрация.
> Класс-имплементацию сервиса можно менять через `ServiceConfiguration`.
{style="note"}

Для каждого регистрируемого сервиса можно выбрать:
- класс по которому регистрируется сервис в конфигурации
- конфигурацию сервиса из которой он будет инициализирован и настроен
- порядковый номер, исходя из которого будет инициализирован сервис.
  По-умолчанию, каждый сервис имеет приоритет 0.
  Чем число меньше, тем раньше инициализируется сервис. Можно использовать отрицательные значения.
- признак выключения. При выставлении признака `disabled` - сервис из соответствующей конфигурации
  не инициализируется и не регистрируется.

```java
public class CustomEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Set<ConfiguredServiceHolder> getServiceConfigurations() {
        return Set.of(
                ConfiguredServiceHolder.of(ValueService.class)
                        .disable(),
                ConfiguredServiceHolder.of(TestService1.class, TestServiceConfiguration1.class)
                        .setOrder(-100),
                ConfiguredServiceHolder.of(TestService2.class, TestServiceConfiguration2.class),
                ConfiguredServiceHolder.of(TestService3.class, TestServiceConfiguration3.class)
                        .setOrder(2),
                ConfiguredServiceHolder.of(TestService4.class, TestServiceConfiguration2.class)
                        .setOrder(400)
                        .disable()
        );
    }

}
```

### How to extend DefaultEnvironmentConfiguration
Вы можете использовать логику инициализации сервисов, заложенную в DefaultEnvironmentConfiguration,
но скорректировать или дополнить ее под свои нужды.

```java
public class CustomEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Set<ConfiguredServiceHolder> getServiceConfigurations() {
        return super.getServiceConfigurations().stream()
                       .filter(configuredServiceHolder -> !Objects.equals(configuredServiceHolder.getServiceClass(), DataConverterService.class))
                       .collect(toSet());
    }

}
```

> Конфигурирование сервисов подробно описано в соответствующей главе документации.
{style="note"}

Environment related objects
---------------------------
Кроме того, `Environment` может содержать набор объектов, относящихся к тесту.
Вы можете использовать эти объекты при инициализации сервисов.
Когда это может быть необходимо?
Например, когда необходимо создать экземпляр сервиса и настроить его для конкретного теста.
Одним из самых удобных способов хранения настроек являются аннотации над тестовым методом или тестовым классом.
Однако, экземпляр сервиса создается внутри объекта `Environment` из конфигурации,
которая может быть общей для всех ваших тестов и, соответственно,
не знает про размеченный тестовый метод или тестовый класс.
Проблема решается передачей в созданный объект Environment информации о:
- тестовом методе
- тестовом классе
- любой другой информации, которая может быть использована при инициализации сервисов.

> Объекты должны быть добавлены в Environment ДО вызова метода `init()`
{style="warning"}

Как это использовать?

```java
public class EnvironmentTest {

    private final Environment environment = new Environment(DefaultEnvironmentConfiguration.class);

    @BeforeEach
    void beforeEach() {
    }

    @AfterEach
    void afterEach() {
        environment.shutdown();
    }

    @Test
    @SessionType(MOBILE_SESSION)
    void environmentTestOne() {
        // Можно передать признак явно        
        environment.addRelatedObject("Session type", "Mobile session")
                // или передать тестовый класс/метод и считать оттуда аннотацию при инициализации
                .addRelatedObject("Test class", this.getClass())
                .addRelatedObject("Test method name", "environmentTestOne")
                .init();
    }

}
```

> Практический каждый тестовый фреймворк имеет механизм передачи в before/after
> методы информации о выполняемом тестовом методе.
> Используя эти механизмы вы можете полностью убрать повторяющийся код из тестов.
{style="note"}

Как инициализировать Service с учетом этих признаков, смотри в документации по Service.

> Если вы используете не проекте JUnit5, смотрите раздел JUnit5 Extension
{style="note"}

Addition
--------
### How to change Environment initialization console output?
Вы можете изменить вывод конфигурации в консоль при инициализации Environment:
```
12:40:30:331 [ForkJoinPool-1-worker-1] DEBUG Environment - Environment configuration initialization
12:40:30:352 [ForkJoinPool-1-worker-1] INFO DefaultEnvironmentAttachmentProcessor -
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Environment for test [io.perfeccionista.framework.extension.PerfeccionistaExtension_LocalEnvironment_PerMethodLifecycle_ConcurrentTest#testOne]
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    Environment class                                              io.perfeccionista.framework.Environment                                                
    Environment configuration class                                io.perfeccionista.framework.extension.configurations.TestClassLocalEnvironmentConfiguration
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Services
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    Service class                                                  Service configuration                                                                      Enabled     Order    Duration
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    io.perfeccionista.framework.extension.services.TestService1    io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration1    enabled     -100     0 ms 
    io.perfeccionista.framework.extension.services.TestService2    io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration2    enabled     0        0 ms 
    io.perfeccionista.framework.extension.services.TestService3    io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration3    enabled     2        0 ms 
    io.perfeccionista.framework.extension.services.TestService4    io.perfeccionista.framework.extension.services.configurations.TestServiceConfiguration2    disabled    400      0 ms 
    io.perfeccionista.framework.value.ValueService                 null                                                                                       disabled    0        0 ms 
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    Initialization duration                                        14 ms                                                                                  
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

12:40:30:353 [ForkJoinPool-1-worker-1] DEBUG Environment - Environment configuration initialization success
12:40:30:359 [ForkJoinPool-1-worker-1] DEBUG Environment - Environment shutdown
12:40:30:359 [ForkJoinPool-1-worker-1] DEBUG Environment - Environment shutdown success
```
Вы можете убрать его или настроить вывод в другое место, например, добавлять приложением в Allure Report.

```java
public class CustomEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull EnvironmentAttachmentProcessor getEnvironmentAttachmentProcessor() {
        return new AllureEnvironmentAttachmentProcessor();
    }
    
    static class AllureEnvironmentAttachmentProcessor implements EnvironmentAttachmentProcessor {
    
        @Override
        public void process(@NotNull TextAttachmentEntry environmentTextAttachment) {
            environmentTextAttachment.getContent()
                    .ifPresent(content -> Allure.addAttachment("EnvironmentConfiguration", "text/plain", content));
        }
        
    }

}
```