package io.perfeccionista.framework.pagefactory.browser.logs;

import java.util.logging.Level;
import java.util.stream.Stream;

// TODO: Добавить методы получения фильтрованных логов по мессаджу, датам, уровню
public interface LogsDispatcher {

    /**
     * JSON to String
     * TODO: Добавить метод, который возвращает JSON вместо стринги
     * @return
     */
    Stream<String> getEntries();

    LogsDispatcher setLogLevel(Level level);

}
