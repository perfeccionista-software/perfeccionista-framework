# JUnit5 Extension

Если вы используете в своем проекте `JUnit5`, то лучше воспользоваться расширением `PerfeccionistaExtension`,
которое имеет большое количество улучшений, упрощающих работу с `Environment` в тестах.

How to start
------------
<tabs>
    <tab title="Gradle (kts)">
        <code-block lang="kotlin">testImplementation("io.perfeccionista.framework:environment-junit5:0.4.0-Beta")</code-block>
    </tab>
    <tab title="Gradle">
        <code-block lang="groovy">testImplementation 'io.perfeccionista.framework:environment-junit5:0.4.0-Beta'</code-block>
    </tab>
    <tab title="Maven">
        <code-block lang="xml"><![CDATA[
            <dependency>
                <group>io.perfeccionista.framework</group>
                <artifactId>environment-junit5</artifactId>
                <version>0.4.0-Beta</version>
                <scope>test</scope>
            </dependency>]]>
        </code-block>
    </tab>
</tabs>

JUnit5 Extension features
-------------------------

### Hidden Environment initialization and Service hooks
При использовании PerfeccionistaExtension, вам не нужно беспокоиться о создании экземпляра Environment,
о выполнении его инициализации и завершении - все это PerfeccionistaExtension делает сам.

Как это работает?
```Java
@ExtendWith(PerfeccionistaExtension.class)
@SetEnvironmentConfiguration(TestEnvironmentParameterResolverConfiguration.class)
public class PerfeccionistaExtensionTest {

    @Test
    @SetEnvironmentConfiguration(HigherPriorityConfiguration.class)
    void environmentWithOverridedConfigurationTest(Environment environment) {
        assertNotNull(environment, () -> "Provided environment argument is null");
    }

}
```
PerfeccionistaExtension можно установить:
- `над тестовым методом` - Extension применится только к тестовому методу
- `над тестовым классом` - Extension применится ко всем тестам в этом классе
- `над классом от которого наследуются другие тестовые классы` - Extension применится ко всем тестовым классам,
  которые наследуются от этого класса

Extension будет самостоятельно создавать экземпляр `Environment` и сохранять его для заданного потока.
Кроме того, `PerfeccionistaExtension` выполняет самостоятельно все методы жизненного цикла `Environment`: 
инициализация и финализация. Соответственно, для всех сервисов будут выполняться методы `Service.beforeTest()`
и `Service.afterTest()` в моменты, соответствующие JUnit5-хукам `beforeEach()` и `afterEach()`.

### Overriding EnvironmentConfiguration
`PerfeccionistaExtension` определяет актуальную конфигурацию через `EnvironmentConfigurationResolver`,
однако, позволяет переопределить ее на уровне:
- тестового метода
- тестового класса
- абстрактного класса от которого наследуются другие тестовые классы

Полный алгоритм для каждого теста выглядит следующим образом:
- ищет аннотацию `@SetEnvironmentConfiguration` над тестовым методом.
  Если находит, то берет оттуда класс конфигурации и возвращает экземпляр этой конфигурации.
- ищет аннотацию `@SetEnvironmentConfiguration` над тестовым классом.
  Если находит, то берет оттуда класс конфигурации и возвращает экземпляр этой конфигурации.
- ищет аннотацию `@SetEnvironmentConfiguration` над классами от которых наследуется текущий тестовый класс.
  Если находит, то берет оттуда класс конфигурации и возвращает экземпляр этой конфигурации.
- ищет свойство `perfeccionista.environment` среди переменных окружения.
  Если находит, то берет оттуда класс конфигурации и возвращает экземпляр этой конфигурации.
- если среди системных переменных такого свойства нет, то смотрит файл `perfeccionista.properties` и
  ищет это свойство в нем. Если находит, то возвращает экземпляр указанной конфигурации.
- если этого файла нет или в нем не задано это свойство,
  то возвращает экземпляр базовой конфигурации: `DefaultEnvironmentConfiguration`

### Overriding ServiceConfiguration
`PerfeccionistaExtension` позволяет добавить/переопределить/заблокировать конфигурацию любого сервиса,
который используется или должен быть добавлен к `Environment` в конкретном тесте или тестовом классе.
Это может быть необходимо в том случае, когда большое количество тестов используют одну конфигурацию
`Environment`, но в конкретном запуске необходимо поменять конфигурацию конкретного сервиса
(например, для отладки).

Функционал реализован с помощью аннотации `@SetServiceConfiguration`. 
Она позволяет задать:
- конфигурацию для сервиса
- порядок инициализации (относительно других сервисов)
- включить/выключить сервис

```Java
@ExtendWith(PerfeccionistaExtension.class)
@SetEnvironmentConfiguration(TestEnvironmentParameterResolverConfiguration.class)
public class PerfeccionistaExtensionTest {

    @Test
    @SetServiceConfiguration(serviceClass = ValueService.class,
                             serviceConfigurationClass = ValueServiceConfiguration.class,
                             order = 500,
                             disabled = false)
    void environmentWithOverridedConfigurationTest(Environment environment) {
        assertNotNull(environment, () -> "Provided environment argument is null");
    }

}
```
Это множественная аннотация, поэтому можно изменить параметры сразу нескольких сервисов для теста.
Кроме того, можно переопределять настройки сервисов на разных уровнях согласно приоритету:

Полный алгоритм для каждого теста выглядит следующим образом:
- ищет аннотацию `@SetServiceConfiguration` над классами от которых наследуется текущий тестовый класс.
  Если находит, то записывает обновленную конфигурацию для сервиса.
- ищет аннотацию `@SetServiceConfiguration` над тестовым классом.
  Если находит, то берет оттуда класс конфигурации и возвращает экземпляр этой конфигурации.
- ищет аннотацию `@SetServiceConfiguration` над тестовым методом.
  Если находит, то берет оттуда класс конфигурации и возвращает экземпляр этой конфигурации.

### Environment and Service parameters resolving
`PerfeccionistaExtension` умеет определять и пробрасывать в тестовый метод экземпляр `Environment` и
экземпляры всех сервисов, созданных в рамках текущего `Environment`.
Для этого необходимо только передать необходимые сервисы или `Environment`
в параметры метода:

```Java
@ExtendWith(PerfeccionistaExtension.class)
@SetEnvironmentConfiguration(TestEnvironmentParameterResolverConfiguration.class)
public class PerfeccionistaExtension_ArgumentProviderTest {

    @Test
    void environmentArgumentProviderTest(Environment environment) {
        assertNotNull(environment, () -> "Provided environment argument is null");
    }

    @Test
    void multipleServiceArgumentProviderTest(TestService1 testService1, ValueService valueService) {
        assertAll(
                () -> assertNotNull(testService1, () -> "Provided testService1 argument is null"),
                () -> assertNotNull(valueService, () -> "Provided valueService argument is null")
        );
    }


}

```

### ExtensionContext as RelatedObject
`PerfeccionistaExtension` по умолчанию пробрасывает в `RelatedObjects` свой `ExtensionContext`,
через который во время инициализации сервисов можно получить доступ к:
- `экземпляру тестового класса`
- `тестовому методу`
- `списку тегов теста`
- `всей остальной информации, предоставляемой JUnit`

### How to change Environment implementation?
При использовании PerfeccionistaExtension, вы можете изменить имплементацию `Environment`,
которая используется в ваших тестах с помощью конфигурации:

```java
public class CustomEnvironmentConfiguration extends DefaultEnvironmentConfiguration {

    @Override
    public @NotNull Class<? extends Environment> getEnvironmentClass() {
        return MyExtendedEnvironment.class;
    }

}
```
> Класс должен наследоваться от Environment и иметь тот же самый набор конструкторов
{style="note"}