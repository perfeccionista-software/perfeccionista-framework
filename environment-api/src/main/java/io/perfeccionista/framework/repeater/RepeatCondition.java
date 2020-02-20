package io.perfeccionista.framework.repeater;

import org.junit.platform.engine.TestExecutionResult;

import java.util.Deque;

/**
 * Условие, которое заключает внутри себя логику необходимую для определения
 * необходимости нового запуска или прекращения запусков тестов на основании
 * его прошлых результатов.
 * Логика работы условия зависит от выбранного режима {@link RepeatMode}
 * Реализации этого интерфейса должны иметь доступный конструктор по-умолчанию.
 * @see RepeatMode
 * @see RepeatPolicy
 * @see TestRepeatedOnCondition
 */
@FunctionalInterface
public interface RepeatCondition {

    /**
     * Метод, который инкапсулирует логику обработки результатов тестов.
     * @param testResults упорядоченный список результатов прошлых запусков.
     * @return {@link Boolean#TRUE} или {@link Boolean#FALSE} в зависимости
     * от выполнения условия.
     */
    boolean testCondition(Deque<TestExecutionResult> testResults);

}
