package io.perfeccionista.framework.pagefactory.browser.logs;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.stream.Stream;

// TODO: Добавить методы получения фильтрованных логов по мессаджу, датам, уровню
public interface LogsDispatcher {

    /**
     * JSON to String
     * TODO: Добавить метод, который возвращает JSON вместо стринги
     * @return
     */
    @NotNull Stream<String> getEntries();

    LogsDispatcher setLogLevel(@NotNull Level level);

}
