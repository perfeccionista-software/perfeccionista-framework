package io.perfeccionista.framework.pagefactory.browser.logs;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.logging.Level;
import java.util.stream.Stream;

public class SeleniumLogsDispatcher implements LogsDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final ExceptionMapper exceptionMapper;

    public SeleniumLogsDispatcher(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public @NotNull Stream<String> getEntries() {
        return exceptionMapper.map(() -> instance.manage()
                .logs().get(LogType.BROWSER)
                .getAll().stream()
                .map(LogEntry::toString))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult();
    }

    @Override
    public SeleniumLogsDispatcher setLogLevel(@NotNull Level level) {
        exceptionMapper.map(() -> instance.setLogLevel(level))
                .ifException(exception -> {
                    throw exception;
                });
        return this;
    }

}
