package io.perfeccionista.framework.pagefactory.browser.executor;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.ClassCast;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.jsfunction.base.LaunchOperationExecuting;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.utils.ReflectionUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.CANT_CAST_OBJECT;
import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

// TODO: Если не стоит флажок отладки, то минифицируем js. Или можно минифицировать скрипты в момент билда
// TODO: Добавить возможность использовать запуск без хранения скрипттов в локал сторадже (для хэдлесс)
public class SeleniumOperationExecutor implements OperationExecutor {
    private static final Logger log = LoggerFactory.getLogger(SeleniumOperationExecutor.class);

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final ExceptionMapper exceptionMapper;
    protected SeleniumJsFunctionRepository jsRepository;

    protected boolean withLogs = false;

    public SeleniumOperationExecutor(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    public SeleniumOperationExecutor withJsLogs() {
        this.withLogs = true;
        return this;
    }

    @Override
    public <T> JsOperationResult<T> executeOperation(JsOperation<T> operation) {
        if (Objects.isNull(jsRepository)) {
            jsRepository = new SeleniumJsFunctionRepository(instance);
        }
        jsRepository.prepareOperation(operation);
        if (this.withLogs) {
            operation.withLogs();
        }

        SeleniumOperationExecutionResult<T> result = SeleniumOperationExecutionResult.of(
                instance.executeScript(new LaunchOperationExecuting().getScript(), toPrettyJson(operation.toJson())),
                operation.getEndpointJsFunction().getConverter()
        );

        if (withLogs && result.withLogs()) {
            log.info(result::getLogs);
        }
        if (result.withException()) {
            return result.getUnsuccessfulOperationResult(exceptionMapper);
        }
        return result.getSuccessfulOperationResult();
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
                    if (ReflectionUtils.isSubtypeOf(result, returnType)) {
                        return (T) result;
                    }
                    throw ClassCast.exception(CANT_CAST_OBJECT.getMessage(result.getClass().getCanonicalName(), returnType.getCanonicalName()));
                })
                .ifException(exception -> {
                    throw exception;
                }).getResult();
    }

}
