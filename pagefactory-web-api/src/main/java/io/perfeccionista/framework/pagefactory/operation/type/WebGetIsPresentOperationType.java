package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PRESENTED;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;
import static org.gradle.internal.impldep.org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;

public class WebGetIsPresentOperationType implements WebElementOperationType<Boolean> {

    private final WebIsPresentAvailable element;

    private WebGetIsPresentOperationType(WebIsPresentAvailable element) {
        this.element = element;
    }

    public static WebGetIsPresentOperationType of(@NotNull WebIsPresentAvailable element) {
        return new WebGetIsPresentOperationType(element);
    }

    @Override
    public @NotNull InvocationInfo getInvocationName() {
        return getterInvocation(IS_PRESENT_METHOD, element, PRESENTED);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_PRESENT_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor);
    }

}
