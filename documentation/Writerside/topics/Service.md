# Service

`Service` - это просто структурная единица окружения теста. Это обертка над некоторым функционалом,
необходимым в тесте, которая:
- может быть инициализирована без конфигурации
- может быть инициализирована с конфигурацией
- знает когда начинается тест
- знает когда заканчивается тест
- знает про текущий экземпляр `Environment` и имеет к нему доступ

Интерфейс Service:
```Java
public interface Service {

    void init(@NotNull Environment environment);

    void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration);

    default void beforeTest() {
        // do nothing
    }

    default void afterTest() {
        // do nothing
    }

}
```
Creating Service implementation
-------------------------------
> Реализация сервиса должна иметь Default constructor
{style="warning"}

Для того чтобы создать свою реализацию сервиса, необходимо реализовать 2 обязательных метода и 2 опциональных.
- ```Java
  void init(@NotNull Environment environment);
  ```
  Обязательный метод. Вызывается, если в конфигурации `Environment` НЕ указана конфигурация для этого сервиса,
  и сервис, при этом, не имеет конфигурации по-умолчанию. Если сервис не предполагает использование конфигурации,
  то можно оставить тело метода пустым.
- ```Java
  void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration);
  ```
  Обязательный метод. Вызывается, если в конфигурации `Environment` указана конфигурация для этого сервиса,
  или сервис содержит конфигурацию по-умолчанию. В данном методе реализуется применение настроек из конфигурации
  к экземпляру сервиса. 
- ```Java
  default void beforeTest() {}
  ```
  Опциональный метод. Вызывается перед выполнением теста для которого создан экземпляр `Environment`.
  В этом методе необходимо подготавливать экземпляр сервиса, если это необходимо.
  Например, если сервис используется для предоставления сессии, которая будет использоваться в тесте,
  то в этом методе можно резервировать сессию из общего пула.
- ```Java
  default void afterTest() {}
  ```
  Опциональный метод. Вызывается после выполнения теста для которого создан экземпляр `Environment`.
  В этом методе необходимо финализировать экземпляр сервиса, если это необходимо.
  Например, если сервис используется для предоставления сессии, которая будет использоваться в тесте,
  то в этом методе можно возвращать сессию в общий пул. Или, например, записывать в отчет собранную статистику,
  если сервис использовался для ее сбора.

Пример логики организации сервиса
```Java
public class SessionService implements Service {

    private static final ThreadSafeSessionStorage sessionStorage = new ThreadSafeSessionStorage();

    private Environment environment = null;
    private ServiceConfiguration configuration = null;

    private Session activeSession = null;

    public Session getTestSession() {
        // Вызов этого метода в тесте всегда происходит между beforeTest() и afterTest()
        return activeSession;
    }

    @Override
    public void init(@NotNull Environment environment) {
        this.environment = environment;
    }

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    @Override
    public void beforeTest() {
        String testSessionType = environment.getRelatedObjects().get("Test Session Type").toString();
        activeSession = sessionStorage.getFreeSession(testSessionType);
    }

    @Override
    public void afterTest() {
        sessionStorage.returnSession(activeSession);
        activeSession = null;
    }
    
}
```

ServiceConfiguration
--------------------
Конфигурация сервиса хранит настройки, которые могут менять параметры сервиса для разных тестов.
Конфигурация полностью реализуется автором сервиса.
> Конфигурация является необязательным элементом сервиса. При ее отсутствии, после создания экземпляра
> сервиса на нем вызывается метод `init(@NotNull Environment environment)`.
{style="note"}

Через ServiceConfiguration можно задавать класс-имплементацию сервиса.
Например, вы уже написали несколько сотен тестов, которые используют ValueService:
он передается в параметрах методов и т.п. В какой-то момент вам требуется изменить
его имплементацию, например, добавить какой-то функционал.
Для этого достаточно расширить класс 



Default ServiceConfiguration
----------------------------
Если сервис предполагает использование конфигурации, то возможно задать класс конфигурации, которая будет
использоваться по умолчанию. Это делается через аннотацию `DefaultServiceConfiguration`.
```Java
@DefaultServiceConfiguration(MySessionServiceConfiguration.class)
public class SessionService implements Service {
    ...
}
```
Данная конфигурация будет использована в том случае, если не задана другая конфигурация на
уровне `EnvironmentConfiguration`.