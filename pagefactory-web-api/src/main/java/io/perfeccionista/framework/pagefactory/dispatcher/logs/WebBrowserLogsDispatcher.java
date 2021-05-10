package io.perfeccionista.framework.pagefactory.dispatcher.logs;

import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.stream.Stream;

// TODO: Добавить методы получения фильтрованных логов по мессаджу, датам, уровню
public interface WebBrowserLogsDispatcher {

    /**
     * JSON to String
     * TODO: Добавить метод, который возвращает JSON вместо стринги
     * @return
     */
    @NotNull Stream<String> getEntries();

    WebBrowserLogsDispatcher setLogLevel(@NotNull Level level);

}
