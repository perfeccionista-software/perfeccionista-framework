package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebElementActionAnnotationHandler {

    private WebElementActionAnnotationHandler() {
    }

    public static WebElementActionRegistry createWebElementActionRegistryFor(WebChildElement webChildElement, Method elementMethod) {
        Map<String, WebElementActionImplementation<?>> webElementActions = new HashMap<>();
        findRepeatableAnnotations(elementMethod, WebElementAction.class)
                .forEach(webElementAction -> webElementActions.put(webElementAction.name(), newInstance(webElementAction.implementation())));
        findAllRepeatableAnnotationsInHierarchy(WebElementAction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementAction -> {
                    if (!webElementActions.containsKey(webElementAction.name())) {
                        webElementActions.put(webElementAction.name(), newInstance(webElementAction.implementation()));
                    }
                });
        return WebElementActionRegistry.of(webElementActions);
    }

}
