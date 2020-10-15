package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebElementActionAnnotationHandler {

    private WebElementActionAnnotationHandler() {
    }

    public static @NotNull WebElementActionRegistry createWebElementActionRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                      @NotNull Method elementMethod,
                                                                                      @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementActionImplementation<?>> webElementActions = configuration
                .getWebElementActionConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementAction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementAction -> webElementActions
                        .put(webElementAction.name(), newInstance(webElementAction.implementation())));
        findRepeatableAnnotations(elementMethod, WebElementAction.class)
                .forEach(webElementAction -> webElementActions
                        .put(webElementAction.name(), newInstance(webElementAction.implementation())));
        return WebElementActionRegistry.of(webElementActions);
    }

    public static @NotNull WebElementActionRegistry createWebElementActionRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                      @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementActionImplementation<?>> webElementActions = configuration
                .getWebElementActionConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementAction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementAction -> webElementActions
                        .put(webElementAction.name(), newInstance(webElementAction.implementation())));
        return WebElementActionRegistry.of(webElementActions);
    }

}
