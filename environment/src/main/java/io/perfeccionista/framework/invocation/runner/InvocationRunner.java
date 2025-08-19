package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.Environment;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * TODO: JavaDoc
 * TODO: Api version
 * TODO: Для документации и тестов создать репит-раннер
 */
@FunctionalInterface
public interface InvocationRunner {

    /**
     * Реализация данного метода дает возможнсть обернуть исполняемую логику теста
     * любой другой логикой.
     * Примерами реализаций могут быть:
     *  - циклическое исполнение логики до получения необходимого результата;
     *  - создание отчета об условиях исполнения данного метода;
     *  - выполнение некоторой логики перед/после/во время выполнения данного метода
     *  - запуск этой логики в асинхронном режиме
     *
     * @param environment экземпляр текущей Environment для доступа ко всему окружению
     * @param invocation        имя блока кода для удобного отображения логики его выполнения в отчете
     * @param supplier    блок исполняемого кода
     * @param timeout     пользовательский таймаут для его использования в циклах (при необходимости)
     * @param <T>         возвращаемый Supplier'ом тип
     * @return значение, возвращаемой Supplier'ом.
     */
    <T> T run(@NotNull Environment environment,
              @NotNull InvocationInfo invocation,
              @NotNull final Supplier<T> supplier,
              @NotNull Duration timeout);

}
