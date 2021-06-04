package io.perfeccionista.framework.logging;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class JulDelegatingLogger implements ListenableLogger {
    private static final String FQCN = JulDelegatingLogger.class.getName();

    private final String name;
    private final java.util.logging.Logger julLogger;

    private final Set<LogRecordListener> listeners = ConcurrentHashMap.newKeySet();

    protected JulDelegatingLogger(String name) {
        this.name = name;
        this.julLogger = java.util.logging.Logger.getLogger(this.name);
        this.julLogger.addHandler(new ConsoleHandler());
        this.julLogger.setLevel(Level.INFO);
    }

    @Override
    public void error(Supplier<String> messageSupplier) {
        log(Level.SEVERE, null, messageSupplier);
    }

    @Override
    public void error(Supplier<String> messageSupplier, Throwable throwable) {
        log(Level.SEVERE, throwable, messageSupplier);
    }

    @Override
    public void warn(Supplier<String> messageSupplier) {
        log(Level.WARNING, null, messageSupplier);
    }

    @Override
    public void warn(Supplier<String> messageSupplier, Throwable throwable) {
        log(Level.WARNING, throwable, messageSupplier);
    }

    @Override
    public void info(Supplier<String> messageSupplier) {
        log(Level.INFO, null, messageSupplier);
    }

    @Override
    public void info(Supplier<String> messageSupplier, Throwable throwable) {
        log(Level.INFO, throwable, messageSupplier);
    }

    @Override
    public void config(Supplier<String> messageSupplier) {
        log(Level.CONFIG, null, messageSupplier);
    }

    @Override
    public void config(Supplier<String> messageSupplier, Throwable throwable) {
        log(Level.CONFIG, throwable, messageSupplier);
    }

    @Override
    public void debug(Supplier<String> messageSupplier) {
        log(Level.FINE, null, messageSupplier);
    }

    @Override
    public void debug(Supplier<String> messageSupplier, Throwable throwable) {
        log(Level.FINE, throwable, messageSupplier);
    }

    @Override
    public void trace(Supplier<String> messageSupplier) {
        log(Level.FINER, null, messageSupplier);
    }

    @Override
    public void trace(Supplier<String> messageSupplier, Throwable throwable) {
        log(Level.FINER, throwable, messageSupplier);
    }

    @Override
    public Logger addListeners(Set<LogRecordListener> listeners) {
        this.listeners.addAll(listeners);
        return this;
    }

    private void log(Level level, Throwable throwable, Supplier<String> messageSupplier) {
        boolean loggable = this.julLogger.isLoggable(level);
        if (loggable || !listeners.isEmpty()) {
            LogRecord logRecord = createLogRecord(level, throwable, nullSafeGet(messageSupplier));
            if (loggable) {
                this.julLogger.log(logRecord);
            }
            listeners.forEach(listener -> listener.logRecordSubmitted(logRecord));
        }
    }

    private LogRecord createLogRecord(Level level, Throwable throwable, String message) {
        String sourceClassName = null;
        String sourceMethodName = null;
        boolean found = false;
        for (StackTraceElement element : new Throwable().getStackTrace()) {
            String className = element.getClassName();
            if (FQCN.equals(className)) {
                found = true;
            }
            else if (found) {
                sourceClassName = className;
                sourceMethodName = element.getMethodName();
                break;
            }
        }

        LogRecord logRecord = new LogRecord(level, message);
        logRecord.setLoggerName(this.name);
        logRecord.setThrown(throwable);
        logRecord.setSourceClassName(sourceClassName);
        logRecord.setSourceMethodName(sourceMethodName);
        logRecord.setResourceBundleName(this.julLogger.getResourceBundleName());
        logRecord.setResourceBundle(this.julLogger.getResourceBundle());

        return logRecord;
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return (messageSupplier != null ? messageSupplier.get() : null);
    }

}
