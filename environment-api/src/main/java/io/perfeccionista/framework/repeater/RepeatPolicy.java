package io.perfeccionista.framework.repeater;

import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.EnvironmentConfiguration;

import java.util.Optional;

/**
 * Политика перезапусков тестов, использующаяся по умолчанию на всем проекте
 * Определяет правила перезапусков тестов.
 *
 * Должен иметь конструктор по умолчанию
 *
 * @see EnvironmentConfiguration
 * @see Environment
 * @see RepeatCondition
 * @see TestRepeatedOnCondition
 */
public interface RepeatPolicy {

    /**
     * @return режим работы перезапусков для всех тестов, используемый по умолчанию
     * @see RepeatCondition
     */
    @NotNull
    default RepeatMode getRepeatMode() {
        return RepeatMode.NO_REPEAT;
    }

    /**
     * @return Условие, которое, в совокупности с режимом перезапуска,
     * определяет необходимость последующего запуска теста.
     * Если {@link RepeatMode} установлен в {@link RepeatMode#NO_REPEAT},
     * то можно возвращать {@link Optional#empty()},
     * в противном случае, какой-либо {@link RepeatCondition} должен возвращаться.
     * @see RepeatMode
     */
    @NotNull RepeatCondition getRepeatCondition();

    /**
     * @return Минимальное количество попыток запуска теста
     */
    default int minAttempt() {
        return 1;
    }

    /**
     * @return Максимальное количество попыток запуска тестов
     */
    default int maxAttempt() {
        return 3;
    }

}
