package io.perfeccionista.framework.logging;

import java.util.Set;

public interface ListenableLogger extends Logger {

    Logger addListeners(Set<LogRecordListener> listeners);

}
