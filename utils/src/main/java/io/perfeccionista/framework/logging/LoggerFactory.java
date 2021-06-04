package io.perfeccionista.framework.logging;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class LoggerFactory {

    private static final ThreadLocal<Logger> threadLocalLogger = new ThreadLocal<>();

    private LoggerFactory() {
        /* no-op */
    }

    private static final Set<LogRecordListener> listeners = ConcurrentHashMap.newKeySet();

    /**
     * Get a {@link Logger} for the specified class.
     *
     * @param clazz the class for which to get the logger; never {@code null}
     * @return the logger
     */
    public static Logger getLogger(@NotNull Class<?> clazz) {
        return Optional.ofNullable(LoggerFactory.threadLocalLogger.get())
                .orElse(new JulDelegatingLogger(clazz.getName()));
    }

    public static void setLogger(@NotNull Logger logger) {
        if (logger instanceof ListenableLogger) {
            ((ListenableLogger) logger).addListeners(listeners);
        }
        threadLocalLogger.set(logger);
    }

    /**
     * Add the supplied {@link LogRecordListener} to the set of registered
     * listeners.
     */
    public static void addListener(LogRecordListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove the supplied {@link LogRecordListener} from the set of registered
     * listeners.
     */
    public static void removeListener(LogRecordListener listener) {
        listeners.remove(listener);
    }

}
