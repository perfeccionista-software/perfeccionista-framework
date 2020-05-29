package io.perfeccionista.framework.pagefactory.browser.executor;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.jsfunction.base.LaunchOperationExecuting;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.utils.JsonUtils.toPrettyJson;

// TODO: Если не стоит флажок отладки, то минифицируем js. Или можно минифицировать скрипты в момент билда
public class SeleniumOperationExecutor implements OperationExecutor {
    private static final Logger log = LoggerFactory.getLogger(SeleniumOperationExecutor.class);

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final ExceptionMapper exceptionMapper;
    protected final SeleniumJsRepository jsRepository;

    protected boolean traceSearch = false;

    public SeleniumOperationExecutor(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
        this.jsRepository = new SeleniumJsRepository(instance);
    }

    public SeleniumOperationExecutor setTraceSearch(boolean traceSearch) {
        this.traceSearch = traceSearch;
        return this;
    }

    @Override
    public <T> JsOperationResult<T> executeOperation(JsOperation<T> operation) {
        jsRepository.prepareOperation(operation);
        if (this.traceSearch) {
            operation.setTraceSearch(true);
        }

        ScriptExecutionResult<T> result = ScriptExecutionResult.of(
                instance.executeScript(new LaunchOperationExecuting().getScript(), toPrettyJson(operation.toJson())),
                operation.getEndpointJsFunction().getConverter()
        );

        if (traceSearch && result.withLogs()) {
            log.info(result::getLogs);
        }
        if (result.withException()) {
            PerfeccionistaException exception = result.getException(exceptionMapper);
            if (result.withLogs()) {
                exception.addAttachmentEntry(JsonAttachmentEntry.of("JavaScript logs", result.getLogsNode()));
            }
            throw exception;
        }

        return result.getOperationResult();
    }

}
