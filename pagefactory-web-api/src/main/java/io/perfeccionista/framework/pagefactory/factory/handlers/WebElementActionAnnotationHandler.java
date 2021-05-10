package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebElementActionAnnotationHandler {

    private WebElementActionAnnotationHandler() {
    }

    public static @NotNull EndpointHandlerRegistry createWebElementActionRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                     @NotNull Method elementMethod,
                                                                                     @NotNull WebPageFactoryPreferences configuration) {
        Map<String, Class<? extends EndpointHandler>> endpointHandlers = configuration
                .getWebElementActionConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementAction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementAction -> endpointHandlers
                        .put(webElementAction.name(), webElementAction.handler()));
        findRepeatableAnnotations(elementMethod, WebElementAction.class)
                .forEach(webElementAction -> endpointHandlers
                        .put(webElementAction.name(), webElementAction.handler()));
        return EndpointHandlerRegistry.of(endpointHandlers);
    }

    public static @NotNull EndpointHandlerRegistry createWebElementActionRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                      @NotNull WebPageFactoryPreferences configuration) {
        Map<String, Class<? extends EndpointHandler>> endpointHandlers = configuration
                .getWebElementActionConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementAction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementAction -> endpointHandlers
                        .put(webElementAction.name(), webElementAction.handler()));
        return EndpointHandlerRegistry.of(endpointHandlers);
    }

}
