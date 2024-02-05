# TimeoutService

TimeoutService позволяет реализовать всю работу с таймаутами в одном месте:
- задать значения необходимых таймаутов для разных срезов (от единой конфигурации
  для всех тестов до персональной конфигурации для каждого теста).
- реализовать логику установки таймаутов рядом с хранимыми значениями этих таймаутов
- выполнить установку обновленных значений таймаутов после изменения любого из значений
  таймаута в процессе выполнения теста

How to work with TimeoutService
-------------------------------
Все настройки TimeoutService берет из `TimeoutServiceConfiguration`.
В конфигурации необходимо реализовать:
- обязательный метод, который возвращает список таймаутов. Каждый таймаут привязан к типу
  таймаута. Тип таймаута - это просто класс, который работает идентификатором этого таймаута.
  ```Java
  public class SelenideTimeout implements TimeoutsType {}
  ```
- опциональный метод, в котором реализуется логика установки значений таймаута 

```Java
public class CustomTimeoutsServiceConfiguration implements TimeoutsServiceConfiguration {

    @Override
    public @NotNull Map<Class<? extends TimeoutsType>, Duration> getTimeouts() {
        return Map.of(
                SelenideTimeout.class, Duration.ofSeconds(5L)
        );
    }
    
    @Override
    public void processTimeouts(Environment environment, Map<Class<? extends TimeoutsType>, Duration> timeouts) {
        Configuration.timeout = timeouts.get(SelenideTimeout.class).toMillis();
    }

}
```

TimeoutService lifecycle
------------------------
- При инициализации сервиса используется либо конфигурация сервиса, установленная пользователем,
  либо базовая конфигурация `DefaultTimeoutsServiceConfiguration`.
- Ассоциированные с типами таймауты устанавливаются в экземпляр `TimeoutService`.
- Для заданных в конфигурации таймаутов выполняется метод `processTimeouts()`, в котором должна
  быть описана логика применения этих таймаутов к конфигурациям используемых во фреймворках.
- при изменении таймаута через `TimeoutService.setTimeout(timeoutType, duration)`, автоматически будет происходить
  вызов метода `processTimeouts()`, который должен применить обновленное значение.
