package io.perfeccionista.framework.pagefactory.browser.logs;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
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
    public Stream<String> getEntries() {
        return null;
    }

    @Override
    public LogsDispatcher setLogLevel(Level level) {
        return null;
    }

}
