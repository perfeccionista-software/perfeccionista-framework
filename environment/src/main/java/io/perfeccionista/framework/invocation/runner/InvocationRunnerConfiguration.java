package io.perfeccionista.framework.invocation.runner;

import org.jetbrains.annotations.NotNull;

/**
 * Конфигурация позволяет соотносить класс враппера с используемой внутри реализацией обработчик повторяющихся действий
 * Пользователь может написать свой враппер и сопоставить его класс с используемой имплементацией.
 */
public interface InvocationRunnerConfiguration {

    /**
     * Этот метод не должен возвращать null
     * Для всех тестов используется дефолтная пустая обертка, которая возвращается этим методом.
     * Эта обертка используется для оборачивания вызова всего тела метода через интерфейс InvocationInterceptor
     *
     * if (invocationWrapper == MyClass.class) {
     *     return MyClassActionRunner.class;
     * }
     * return EmptyActionRunner.class;
     */
    @NotNull Class<? extends InvocationRunner> getInvocationRunnerImplementation(@NotNull Class<?> invocationWrapper);

}
