package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementProperty;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createOptionalWebLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebElementPropertyAnnotationHandler {

    private WebElementPropertyAnnotationHandler() {
    }

    public static @NotNull WebElementPropertyRegistry createWebElementPropertyRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                          @NotNull Method elementMethod,
                                                                                          @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementPropertyHolder> webElementProperties = configuration
                .getWebElementPropertyConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementProperty.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementProperty -> webElementProperties
                        .put(webElementProperty.name(), createWebElementPropertyHolder(webElementProperty)));
        findRepeatableAnnotations(elementMethod, WebElementProperty.class)
                .forEach(webElementProperty -> webElementProperties
                        .put(webElementProperty.name(), createWebElementPropertyHolder(webElementProperty)));
        return WebElementPropertyRegistry.of(webElementProperties);
    }

    public static @NotNull WebElementPropertyRegistry createWebElementPropertyRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                          @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementPropertyHolder> webElementProperties = configuration
                .getWebElementPropertyConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementProperty.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementProperty -> webElementProperties
                        .put(webElementProperty.name(), createWebElementPropertyHolder(webElementProperty)));
        return WebElementPropertyRegistry.of(webElementProperties);
    }

    protected static @NotNull WebElementPropertyHolder createWebElementPropertyHolder(@NotNull WebElementProperty webElementProperty) {
        Optional<WebLocatorHolder> optionalWebLocatorHolder = createOptionalWebLocatorHolder(webElementProperty.webLocator());
        return WebElementPropertyHolder.of(
                webElementProperty.name(),
                optionalWebLocatorHolder.orElse(null),
                newInstance(webElementProperty.extractor())
        );
    }

}
