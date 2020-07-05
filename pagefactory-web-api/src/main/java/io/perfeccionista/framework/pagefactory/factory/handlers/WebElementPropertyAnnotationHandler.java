package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementProperty;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyRegistry;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createOptionalWebLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WebElementPropertyAnnotationHandler {

    private WebElementPropertyAnnotationHandler() {
    }

    public static WebElementPropertyRegistry createWebElementPropertyRegistryFor(WebChildElement webChildElement, Method elementMethod) {
        Map<String, WebElementPropertyHolder> webElementProperties = new HashMap<>();
        findRepeatableAnnotations(elementMethod, WebElementProperty.class)
                .forEach(webElementProperty -> webElementProperties.put(webElementProperty.name(),
                        createWebElementPropertyHolder(webElementProperty)));
        findAllRepeatableAnnotationsInHierarchy(WebElementProperty.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementProperty -> {
                    if (!webElementProperties.containsKey(webElementProperty.name())) {
                        webElementProperties.put(webElementProperty.name(), createWebElementPropertyHolder(webElementProperty));
                    }
                });
        return WebElementPropertyRegistry.of(webElementProperties);
    }

    protected static WebElementPropertyHolder createWebElementPropertyHolder(WebElementProperty webElementProperty) {
        Optional<WebLocatorHolder> optionalWebLocatorHolder = createOptionalWebLocatorHolder(webElementProperty.webLocator());
        return WebElementPropertyHolder.of(
                webElementProperty.name(),
                optionalWebLocatorHolder.orElse(null),
                newInstance(webElementProperty.extractor())
        );
    }

}
