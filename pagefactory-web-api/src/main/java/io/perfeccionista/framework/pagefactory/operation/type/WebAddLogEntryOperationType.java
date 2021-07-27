package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.logging.Level;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.ADD_LOG_ENTRY_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebAddLogEntryOperationType implements WebElementOperationType<Void> {

    private final WebPage page;
    private final Level logLevel;
    private final String message;

    private final InvocationInfo invocationInfo;

    private WebAddLogEntryOperationType(WebPage page, Level logLevel, String message) {
        this.page = page;
        this.logLevel = logLevel;
        this.message = message;
        var pageName = page.getPageIdentifier().getLastUsedName();
        this.invocationInfo = actionInvocation(ADD_LOG_ENTRY_METHOD, pageName, logLevel.getName(), message);
    }

    public static WebAddLogEntryOperationType of(@NotNull WebPage page, @NotNull Level logLevel, @NotNull String message) {
        return new WebAddLogEntryOperationType(page, logLevel, message);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Void> getEndpointHandler() {
        Class<? extends EndpointHandler<Void>> endpointHandlerClass = page.getEndpointHandler(ADD_LOG_ENTRY_METHOD, Void.class);
        Constructor<? extends EndpointHandler<Void>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, logLevel, message);
    }

}
