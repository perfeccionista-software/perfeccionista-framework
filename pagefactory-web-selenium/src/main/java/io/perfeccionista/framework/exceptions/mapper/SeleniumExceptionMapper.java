package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.ElementIsDisabledException;
import io.perfeccionista.framework.exceptions.ElementNotClickableException;
import io.perfeccionista.framework.exceptions.ElementNotDisplayedException;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverException;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstanceInternalException;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstanceNotAvailableException;
import io.perfeccionista.framework.exceptions.SeleniumWebElementNotIntractableException;
import io.perfeccionista.framework.exceptions.SeleniumWebElementReferenceStaleException;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.js.ElementSearchJsException;
import io.perfeccionista.framework.exceptions.js.ElementStateJsException;
import io.perfeccionista.framework.exceptions.js.FunctionCallJsException;
import io.perfeccionista.framework.exceptions.js.IncorrectSearchQueryJsException;
import io.perfeccionista.framework.exceptions.js.JsExecutionException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.InvalidCoordinatesException;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.ConnectionClosedException;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_IS_DISABLED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_DRIVER_NOT_AVAILABLE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_DRIVER_INITIALIZATION_FAILED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_DRIVER_INTERNAL_ERROR;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_ELEMENT_CLICK_INTERCEPTED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_ELEMENT_IS_STALE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_ELEMENT_NOT_INTRACTABLE;

public class SeleniumExceptionMapper implements ExceptionMapper {
    protected static final Pattern START_REMOTE_WEB_DRIVER_SESSION_ERROR_PATTERN = Pattern
            .compile("(Could not start a new session)+");
    protected static final Pattern REMOTE_BROWSER_COMMUNICATION_ERROR_PATTERN = Pattern
            .compile("(Error communicating with the remote browser. It may have died)+");
    protected static final Pattern INVALID_SESSION_ID_PATTERN = Pattern
            .compile("(invalid session id)+");

    protected static final Pattern START_CONTAINER_ERROR_PATTERN = Pattern
            .compile("(start container: Error response from daemon: OCI runtime create failed:)+");
    protected static final Pattern START_SESSION_ERROR_PATTERN = Pattern
            .compile("(New session attempts retry count exceeded)+");
    protected static final Pattern PAGE_CRASH_PATTERN = Pattern
            .compile("(session deleted because of page crash)+");
    protected static final Pattern UNKNOWN_WEBDRIVER_ERROR_PATTERN = Pattern
            .compile("(An unknown error has occurred)+");
    protected static final Pattern CHROME_FAILED_TO_START_PATTERN = Pattern
            .compile("(Chrome failed to start)+");
    protected static final Pattern NO_SUCH_DOCKER_IMAGE_PATTERN = Pattern
            .compile("(create container: Error: No such image:)+");
    protected static final Pattern REMOTE_WEB_DRIVER_DOES_NOT_RESPOND_PATTERN = Pattern
            .compile("(wait: http(s)*://\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5} does not respond in \\d+s)+");

    @Override
    public <T> ExceptionMapperResult<T> map(Supplier<T> supplier, String... exceptionMessageOptionalArgs) {
        try {
            T result = supplier.get();
            return ExceptionMapperResult.success(result);
        } catch (WebDriverException e) {
            return ExceptionMapperResult.failure(mapWebDriverException(e, exceptionMessageOptionalArgs));
        }
    }

    @Override
    public ExceptionMapperResult<Void> map(Runnable runnable, String... exceptionMessageOptionalArgs) {
        try {
            runnable.run();
            return ExceptionMapperResult.empty();
        } catch (WebDriverException e) {
            return ExceptionMapperResult.failure(mapWebDriverException(e, exceptionMessageOptionalArgs));
        }
    }

    public PerfeccionistaException mapWebDriverException(WebDriverException e, String[] args) {
        if (e instanceof ElementClickInterceptedException) {
            return new ElementNotClickableException(WEB_ELEMENT_CLICK_INTERCEPTED.getMessage(getArg(args, 0)), e);
        } else if (e instanceof ElementNotVisibleException
                || e instanceof NotFoundException) {
            return new ElementNotDisplayedException(ELEMENT_NOT_DISPLAYED.getMessage(getArg(args, 0)), e);
        } else if (e instanceof ElementNotInteractableException
                || e instanceof ElementNotSelectableException) {
            return new ElementIsDisabledException(ELEMENT_IS_DISABLED.getMessage(getArg(args, 0)), e);
        } else if (e instanceof InvalidElementStateException
                || e instanceof MoveTargetOutOfBoundsException
                || e instanceof InvalidCoordinatesException) {
            return new SeleniumWebElementNotIntractableException(WEB_ELEMENT_NOT_INTRACTABLE.getMessage(getArg(args, 0)), e);
        } else if (e instanceof StaleElementReferenceException) {
            return new SeleniumWebElementReferenceStaleException(WEB_ELEMENT_IS_STALE.getMessage(getArg(args, 0)), e);
        } else if (e instanceof InvalidArgumentException
                || e instanceof ErrorHandler.UnknownServerException
                || e instanceof UnsupportedCommandException) {
            return new SeleniumWebDriverInstanceInternalException(WEB_DRIVER_INTERNAL_ERROR.getMessage(), e);
        } else if (e instanceof UnreachableBrowserException
                || e instanceof NoSuchSessionException
                || e instanceof SessionNotCreatedException
                || e instanceof ConnectionClosedException) {
            return mapSeleniumWebDriverInstanceNotAvailableException(e);
        } else {
            return mapUnclassifiedWebDriverException(e);
        }
    }

    protected SeleniumWebDriverInstanceNotAvailableException mapSeleniumWebDriverInstanceNotAvailableException(WebDriverException exception) {
        String message = exception.getMessage();
        if (START_REMOTE_WEB_DRIVER_SESSION_ERROR_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception);
        }
        if (REMOTE_BROWSER_COMMUNICATION_ERROR_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception);
        }
        if (INVALID_SESSION_ID_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception);
        }
        return new SeleniumWebDriverInstanceNotAvailableException(exception.getMessage(), exception);
    }

    protected PerfeccionistaException mapUnclassifiedWebDriverException(WebDriverException exception) {
        String message = exception.getMessage();
        if (START_CONTAINER_ERROR_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception);
        } else if (START_SESSION_ERROR_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception);
        } else if (START_REMOTE_WEB_DRIVER_SESSION_ERROR_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception);
        } else if (REMOTE_WEB_DRIVER_DOES_NOT_RESPOND_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception);
        } else if (CHROME_FAILED_TO_START_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception);
        } else if (NO_SUCH_DOCKER_IMAGE_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception);
        } else if (PAGE_CRASH_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception);
        } else if (UNKNOWN_WEBDRIVER_ERROR_PATTERN.matcher(message).find()) {
            return new SeleniumWebDriverInstanceNotAvailableException(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception);
        }
        return new SeleniumWebDriverException(exception.getMessage(), exception);
    }

    @Override
    public PerfeccionistaException createByName(@NotNull String name, @NotNull String message) {
        if ("IncorrectSearchQueryError".equals(name)) {
            return new IncorrectSearchQueryJsException(message);
        } else if ("ElementSearchError".equals(name)) {
            return new ElementSearchJsException(message);
        } else if ("ElementStateError".equals(name)) {
            return new ElementStateJsException(message);
        } else if ("FunctionCallError".equals(name)) {
            return new FunctionCallJsException(message);
        } else {
            return new JsExecutionException(message);
        }
    }

    private String getArg(String[] exceptionMessageOptionalArgs, int index) {
        if (exceptionMessageOptionalArgs != null && index < exceptionMessageOptionalArgs.length) {
            return exceptionMessageOptionalArgs[index];
        }
        return "empty";
    }

}
