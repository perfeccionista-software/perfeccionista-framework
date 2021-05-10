package io.perfeccionista.framework.pagefactory.operation.type;

import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.WebImage;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_IMAGE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static org.junit.platform.commons.util.ReflectionUtils.getDeclaredConstructor;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebIsImageOperationType implements WebElementOperationType<Boolean> {

    private final WebImage element;

    private WebIsImageOperationType(WebImage element) {
        this.element = element;
    }

    public static WebIsImageOperationType of(@NotNull WebImage element) {
        return new WebIsImageOperationType(element);
    }

    @Override
    public @NotNull InvocationName getInvocationName() {
        return getterInvocation(IS_IMAGE_METHOD, element, ROOT);
    }

    @Override
    public @NotNull EndpointHandler<Boolean> getEndpointHandler() {
        Class<? extends EndpointHandler<Boolean>> endpointHandlerClass = element.getEndpointHandler(IS_IMAGE_METHOD, Boolean.class);
        Constructor<? extends EndpointHandler<Boolean>> constructor = getDeclaredConstructor(endpointHandlerClass);
        return newInstance(constructor, element);
    }

}

