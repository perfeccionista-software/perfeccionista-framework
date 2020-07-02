package io.perfeccionista.framework.pagefactory.browser.executor;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.jsfunction.base.LaunchOperationExecuting;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

// TODO: Если не стоит флажок отладки, то минифицируем js. Или можно минифицировать скрипты в момент билда
// TODO: Добавить возможность использовать запуск без хранения скрипттов в локал сторадже (для хэдлесс)
public class SeleniumOperationExecutor implements OperationExecutor {
    private static final Logger log = LoggerFactory.getLogger(SeleniumOperationExecutor.class);

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final ExceptionMapper exceptionMapper;
    protected final SeleniumJsRepository jsRepository;

    protected boolean withLogs = false;

    public SeleniumOperationExecutor(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
        this.jsRepository = new SeleniumJsRepository(instance);
    }

    public SeleniumOperationExecutor withJsLogs() {
        this.withLogs = true;
        return this;
    }

    @Override
    public <T> JsOperationResult<T> executeOperation(JsOperation<T> operation) {
        jsRepository.prepareOperation(operation);
        if (this.withLogs) {
            operation.withLogs();
        }

        ScriptExecutionResult<T> result = ScriptExecutionResult.of(
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

}
