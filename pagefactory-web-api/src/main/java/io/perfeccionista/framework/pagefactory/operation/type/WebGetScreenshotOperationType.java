package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebGetScreenshotOperationType implements WebElementOperationType<Screenshot> {

    private final WebGetScreenshotAvailable element;
    private final String componentName;

    private WebGetScreenshotOperationType(WebGetScreenshotAvailable element, String componentName) {
        this.element = element;
        this.componentName = componentName;
    }

    public static WebGetScreenshotOperationType of(@NotNull WebGetScreenshotAvailable element, @NotNull String componentName) {
        return new WebGetScreenshotOperationType(element, componentName);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return getterInvocation(GET_SCREENSHOT_METHOD, element, componentName);
    }

    @Override
    public @NotNull EndpointHandler<Screenshot> getEndpointHandler() {
        Class<? extends EndpointHandler<Screenshot>> endpointHandlerClass = element.getEndpointHandler(GET_SCREENSHOT_METHOD, Screenshot.class);
        Constructor<? extends EndpointHandler<Screenshot>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
