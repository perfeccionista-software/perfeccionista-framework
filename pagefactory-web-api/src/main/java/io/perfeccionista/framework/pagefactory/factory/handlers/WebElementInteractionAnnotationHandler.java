package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteraction;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionImplementation;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionRegistry;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebElementInteractionAnnotationHandler {

    private WebElementInteractionAnnotationHandler() {
    }

    public static @NotNull WebElementInteractionRegistry createWebElementInteractionRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                                @NotNull Method elementMethod,
                                                                                                @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementInteractionImplementation> webElementInteractions = configuration
                .getWebElementInteractionConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementInteraction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementInteraction -> webElementInteractions
                        .put(webElementInteraction.name(), newInstance(webElementInteraction.implementation())));
        findRepeatableAnnotations(elementMethod, WebElementInteraction.class)
                .forEach(webElementInteraction -> webElementInteractions
                        .put(webElementInteraction.name(), newInstance(webElementInteraction.implementation())));
        return WebElementInteractionRegistry.of(webElementInteractions);
    }

    public static @NotNull WebElementInteractionRegistry createWebElementInteractionRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                                @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementInteractionImplementation> webElementInteractions = configuration
                .getWebElementInteractionConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementInteraction.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementInteraction -> webElementInteractions
                        .put(webElementInteraction.name(), newInstance(webElementInteraction.implementation())));
        return WebElementInteractionRegistry.of(webElementInteractions);
    }

}
