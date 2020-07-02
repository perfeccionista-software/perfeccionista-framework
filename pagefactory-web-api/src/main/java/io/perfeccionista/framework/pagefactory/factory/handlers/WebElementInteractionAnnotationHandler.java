package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteraction;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.elements.interactions.WebElementInteractionRegistry;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebElementInteractionAnnotationHandler {

    private WebElementInteractionAnnotationHandler() {
    }

    public static WebElementInteractionRegistry createWebElementInteractionRegistryFor(WebChildElement webChildElement, Method elementMethod) {
        Map<String, WebElementInteractionImplementation> webElementInteractions = new HashMap<>();

        findRepeatableAnnotations(elementMethod, WebElementInteraction.class)
                .forEach(webElementInteraction -> webElementInteractions.put(webElementInteraction.name(),
                        newInstance(webElementInteraction.implementation())));
        findAllRepeatableAnnotationsInHierarchy(WebElementInteraction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementInteraction -> {
                    if (!webElementInteractions.containsKey(webElementInteraction.name())) {
                        webElementInteractions.put(webElementInteraction.name(), newInstance(webElementInteraction.implementation()));
                    }
                });
        return WebElementInteractionRegistry.of(webElementInteractions);
    }

}
