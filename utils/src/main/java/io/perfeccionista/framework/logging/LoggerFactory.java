package io.perfeccionista.framework.logging;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public final class LoggerFactory {

    private static final ThreadLocal<Class<? extends Logger>> threadLocalLoggerClass = new ThreadLocal<>();

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
        Optional<? extends Class<? extends Logger>> optionalLoggerClass = Optional.ofNullable(LoggerFactory.threadLocalLoggerClass.get());
        if (optionalLoggerClass.isPresent()) {
            Class<? extends Logger> loggerClass = optionalLoggerClass.get();
            Logger logger = newInstance(loggerClass, clazz.getCanonicalName());
            if (logger instanceof ListenableLogger) {
                ((ListenableLogger) logger).addListeners(listeners);
            }
            return logger;
        }
        return new JulDelegatingLogger(clazz.getName());
    }

    public static void setLogger(@NotNull Class<? extends Logger> loggerClass) {
        threadLocalLoggerClass.set(loggerClass);
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
