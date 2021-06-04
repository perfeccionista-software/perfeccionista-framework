package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.ClassCanNotBeCast;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.logging.Logger;
import io.perfeccionista.framework.logging.LoggerFactory;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CAST_OBJECT;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;
import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

// TODO: Если не стоит флажок отладки, то минифицируем js. Или можно минифицировать скрипты в момент билда
// TODO: Добавить возможность использовать запуск без хранения скрипттов в локал сторадже (для хэдлесс)
public class SeleniumWebBrowserOperationExecutor implements WebBrowserOperationExecutor {
    private static final Logger log = LoggerFactory.getLogger(SeleniumWebBrowserOperationExecutor.class);

    private static final String LAUNCH_OPERATION_EXECUTING =
            "return evaluate(arguments);\n" +
            "\n" +
            "function evaluate(arguments) {\n" +
            "    let executeOperation = eval(window.localStorage.getItem('perfeccionista.js.selenium.ExecuteOperation'));\n" +
            "    return executeOperation(JSON.parse(arguments[0]));\n" +
            "}";

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final WebExceptionMapper exceptionMapper;
    protected SeleniumJsFunctionRepository jsRepository;

    protected boolean withLogs = false;

    public SeleniumWebBrowserOperationExecutor(Environment environment, RemoteWebDriver instance, WebExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    public SeleniumWebBrowserOperationExecutor withJsLogs() {
        this.withLogs = true;
        return this;
    }

    @Override
    public <T> WebElementOperationResult<T> executeWebElementOperation(@NotNull WebElementOperation<T> operation) {
        if (Objects.isNull(jsRepository)) {
            jsRepository = new SeleniumJsFunctionRepository(instance);
        }
        jsRepository.prepareOperation(operation);
        if (this.withLogs) {
            operation.withLogs();
        }
        try {
            Object scriptResult = instance.executeScript(LAUNCH_OPERATION_EXECUTING, toPrettyJson(operation.toJson()));
            EndpointHandler<T> endpointHandler = operation.getOperationType().getEndpointHandler();
            SeleniumOperationExecutionResult<T> result = SeleniumOperationExecutionResult.of(scriptResult, endpointHandler);
            if (withLogs && result.withLogs()) {
                log.info(result::getLogs);
            }
            if (result.withException()) {
                return result.getUnsuccessfulOperationResult(exceptionMapper);
            }
            return result.getSuccessfulOperationResult();
        } catch (RuntimeException exception) {
            if (exception instanceof JavascriptException && exception.getMessage().contains("executeOperation is not a function")) {
                jsRepository.init();
            }
            return WebElementOperationResult.of(exceptionMapper, exception);
        }
    }

    @Override
    public Object executeScript(@NotNull String script, Object... args) {
        return exceptionMapper.map(() -> instance.executeScript(script, args))
                .ifException(exception -> {
                    throw exception;
                }).getResult();
    }

    @Override
    public <T> T executeScript(@NotNull Class<T> returnType, @NotNull String script, Object... args) {
        return exceptionMapper
                .map(() -> {
                    Object result = instance.executeScript(script, args);
                    if (isSubtypeOf(result, returnType)) {
                        return (T) result;
                    }
                    throw ClassCanNotBeCast.exception(CANT_CAST_OBJECT.getMessage(result.getClass().getCanonicalName(), returnType.getCanonicalName()));
                })
                .ifException(exception -> {
                    throw exception;
                }).getResult();
    }

}
