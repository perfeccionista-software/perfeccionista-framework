package io.perfeccionista.framework.logging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.stream.Stream;

public class LogRecordListener {

    // capture log records by thread to support parallel test execution
    private final ThreadLocal<List<LogRecord>> logRecords = ThreadLocal.withInitial(ArrayList::new);

    public void logRecordSubmitted(LogRecord logRecord) {
        this.logRecords.get().add(logRecord);
    }

    public Stream<LogRecord> stream() {
        return this.logRecords.get().stream();
    }

    public Stream<LogRecord> stream(@NotNull Level level) {
        return stream().filter(logRecord -> logRecord.getLevel() == level);
    }

    public Stream<LogRecord> stream(@NotNull Class<?> clazz) {
        return stream().filter(logRecord -> logRecord.getLoggerName().equals(clazz.getName()));
    }

    public Stream<LogRecord> stream(@NotNull Class<?> clazz, @NotNull Level level) {
        return stream(clazz).filter(logRecord -> logRecord.getLevel() == level);
    }

    public void clear() {
        this.logRecords.get().clear();
    }

}
