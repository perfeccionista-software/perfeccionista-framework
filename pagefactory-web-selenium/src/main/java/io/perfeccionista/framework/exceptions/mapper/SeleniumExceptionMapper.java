package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.WebElementIsDisabled;
import io.perfeccionista.framework.exceptions.WebElementNotClickable;
import io.perfeccionista.framework.exceptions.WebElementNotDisplayed;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstance;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverInstanceNotAvailable;
import io.perfeccionista.framework.exceptions.SeleniumWebElementNotIntractable;
import io.perfeccionista.framework.exceptions.SeleniumStaleWebElementReference;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.exceptions.js.JsElementSearch;
import io.perfeccionista.framework.exceptions.js.JsElementState;
import io.perfeccionista.framework.exceptions.js.JsFunctionCall;
import io.perfeccionista.framework.exceptions.js.JsIncorrectSearchQuery;
import io.perfeccionista.framework.exceptions.js.JsExecution;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
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

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_IS_DISABLED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_DISPLAYED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_DRIVER_CAN_NOT_EXECUTE_OPERATION_ON_EMPTY_TAB;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_DRIVER_CONNECTION_REFUSED;
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
    protected static final Pattern LOCAL_STORAGE_PROPERTY_NOT_AVAILABLE_PATTERN = Pattern
            .compile("(Failed to read the 'localStorage' property from 'Window')+");
    protected static final Pattern ERROR_CONNECTION_REFUSED = Pattern
            .compile("(unknown error: net::ERR_CONNECTION_REFUSED)+");

    @Override
    public @NotNull<T> ExceptionMapperResult<T> map(@NotNull Supplier<T> supplier, String... exceptionMessageOptionalArgs) {
        try {
            T result = supplier.get();
            return ExceptionMapperResult.success(result);
        } catch (WebDriverException e) {
            return ExceptionMapperResult.failure(mapWebDriverException(e, exceptionMessageOptionalArgs));
        }
    }

    @Override
    public @NotNull ExceptionMapperResult<Void> map(@NotNull Runnable runnable, String... exceptionMessageOptionalArgs) {
        try {
            runnable.run();
            return ExceptionMapperResult.empty();
        } catch (WebDriverException e) {
            return ExceptionMapperResult.failure(mapWebDriverException(e, exceptionMessageOptionalArgs));
        }
    }

    public @NotNull PerfeccionistaRuntimeException mapWebDriverException(@NotNull WebDriverException e, String[] args) {
        if (e instanceof ElementClickInterceptedException) {
            return WebElementNotClickable.exception(WEB_ELEMENT_CLICK_INTERCEPTED.getMessage(getArg(args, 0)), e)
                    .setProcessed(true);
        } else if (e instanceof ElementNotVisibleException
                || e instanceof NoSuchElementException) {
            return WebElementNotDisplayed.exception(ELEMENT_NOT_DISPLAYED.getMessage(getArg(args, 0)), e)
                    .setProcessed(true);
        } else if (e instanceof ElementNotInteractableException
                || e instanceof ElementNotSelectableException) {
            return WebElementIsDisabled.exception(ELEMENT_IS_DISABLED.getMessage(getArg(args, 0)), e)
                    .setProcessed(true);
        } else if (e instanceof InvalidElementStateException
                || e instanceof MoveTargetOutOfBoundsException
                || e instanceof InvalidCoordinatesException) {
            return SeleniumWebElementNotIntractable.exception(WEB_ELEMENT_NOT_INTRACTABLE.getMessage(getArg(args, 0)), e)
                    .setProcessed(true);
        } else if (e instanceof StaleElementReferenceException) {
            return SeleniumStaleWebElementReference.exception(WEB_ELEMENT_IS_STALE.getMessage(getArg(args, 0)), e)
                    .setProcessed(true);
        } else if (e instanceof InvalidArgumentException
                || e instanceof ErrorHandler.UnknownServerException
                || e instanceof UnsupportedCommandException) {
            return SeleniumWebDriverInstance.exception(WEB_DRIVER_INTERNAL_ERROR.getMessage(), e)
                    .setService(true);
        } else if (e instanceof UnreachableBrowserException
                || e instanceof NoSuchSessionException
                || e instanceof SessionNotCreatedException
                || e instanceof ConnectionClosedException) {
            return mapSeleniumWebDriverInstanceNotAvailableException(e);
        } else {
            return mapUnclassifiedWebDriverException(e);
        }
    }

    protected @NotNull PerfeccionistaRuntimeException mapSeleniumWebDriverInstanceNotAvailableException(@NotNull WebDriverException exception) {
        String message = exception.getMessage();
        if (START_REMOTE_WEB_DRIVER_SESSION_ERROR_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception)
                    .setService(true);
        }
        if (REMOTE_BROWSER_COMMUNICATION_ERROR_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception)
                    .setService(true);
        }
        if (INVALID_SESSION_ID_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception)
                    .setService(true);
        }
        return SeleniumWebDriverInstanceNotAvailable.exception(exception.getMessage(), exception)
                .setService(true);
    }

    protected @NotNull PerfeccionistaRuntimeException mapUnclassifiedWebDriverException(WebDriverException exception) {
        String message = exception.getMessage();
        if (START_CONTAINER_ERROR_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception)
                    .setService(true);
        } else if (START_SESSION_ERROR_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception)
                    .setService(true);
        } else if (START_REMOTE_WEB_DRIVER_SESSION_ERROR_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception)
                    .setService(true);
        } else if (REMOTE_WEB_DRIVER_DOES_NOT_RESPOND_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception)
                    .setService(true);
        } else if (CHROME_FAILED_TO_START_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception)
                    .setService(true);
        } else if (NO_SUCH_DOCKER_IMAGE_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_INITIALIZATION_FAILED.getMessage(), exception)
                    .setService(true);
        } else if (PAGE_CRASH_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception)
                    .setService(true);
        } else if (UNKNOWN_WEBDRIVER_ERROR_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_NOT_AVAILABLE.getMessage(), exception)
                    .setService(true);
        } else if (LOCAL_STORAGE_PROPERTY_NOT_AVAILABLE_PATTERN.matcher(message).find()) {
            return SeleniumWebDriverInstanceNotAvailable.exception(WEB_DRIVER_CAN_NOT_EXECUTE_OPERATION_ON_EMPTY_TAB.getMessage(), exception)
                    .setService(true);
        } else if (ERROR_CONNECTION_REFUSED.matcher(message).find()) {
            return SeleniumWebDriverInstance.exception(WEB_DRIVER_CONNECTION_REFUSED.getMessage(), exception)
                    .setService(true);
        }
        return SeleniumWebDriverInstance.exception(exception.getMessage(), exception)
                .setService(true);
    }

    @Override
    public @NotNull PerfeccionistaRuntimeException createByName(@NotNull String name, @NotNull String message) {
        switch (name) {
            case "IncorrectSearchQueryError":
                return JsIncorrectSearchQuery.exception(message);
            case "ElementSearchError":
                return JsElementSearch.exception(message)
                        .setProcessed(true);
            case "ElementStateError":
                return JsElementState.exception(message);
            case "FunctionCallError":
                return JsFunctionCall.exception(message);
            default:
                return JsExecution.exception(message)
                        .setProcessed(true);
        }
    }

    private String getArg(String[] exceptionMessageOptionalArgs, int index) {
        if (exceptionMessageOptionalArgs != null && index < exceptionMessageOptionalArgs.length) {
            return exceptionMessageOptionalArgs[index];
        }
        return "empty";
    }

}
