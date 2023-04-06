package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetScreenshotAvailable;
import io.perfeccionista.framework.pagefactory.elements.options.GetScreenshotOptions;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebGetScreenshotOperationType implements WebElementOperationType<Screenshot> {

    private static final String ACTION_NAME = GET_SCREENSHOT_METHOD;

    private final WebGetScreenshotAvailable element;
    private final GetScreenshotOptions screenshotOptions;

    private final InvocationInfo invocationInfo;

    private WebGetScreenshotOperationType(WebGetScreenshotAvailable element, GetScreenshotOptions screenshotOptions) {
        this.element = element;
        this.screenshotOptions = screenshotOptions;
        String elementName = element.getElementIdentifier().getLastUsedName();
        this.invocationInfo = getterInvocation(ACTION_NAME, elementName, screenshotOptions.toString());
    }

    public static WebGetScreenshotOperationType of(@NotNull WebGetScreenshotAvailable element, @NotNull GetScreenshotOptions screenshotOptions) {
        return new WebGetScreenshotOperationType(element, screenshotOptions);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return this.invocationInfo;
    }

    @Override
    public @NotNull EndpointHandler<Screenshot> getEndpointHandler() {
        Class<? extends EndpointHandler<Screenshot>> endpointHandlerClass = element.getEndpointHandler(ACTION_NAME, Screenshot.class);
        Constructor<? extends EndpointHandler<Screenshot>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, screenshotOptions);
    }

}
