package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementState;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateExtractor;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createOptionalWebLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class WebElementStateAnnotationHandler {

    private WebElementStateAnnotationHandler() {
    }

    public static @NotNull WebElementStateRegistry createWebElementStateRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                    @NotNull Method elementMethod,
                                                                                    @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementStateHolder> webElementStates = configuration
                .getWebElementStateConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementState.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementState -> webElementStates
                        .put(webElementState.name(), createWebElementStateHolder(webElementState)));
        findRepeatableAnnotations(elementMethod, WebElementState.class)
                .forEach(webElementState -> webElementStates
                        .put(webElementState.name(), createWebElementStateHolder(webElementState)));
        return WebElementStateRegistry.of(webElementStates);
    }

    public static @NotNull WebElementStateRegistry createWebElementStateRegistryFor(@NotNull WebChildElement webChildElement,
                                                                                    @NotNull WebPageFactoryPreferences configuration) {
        Map<String, WebElementStateHolder> webElementStates = configuration
                .getWebElementStateConfiguration(webChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(WebElementState.class, WebChildElement.class, webChildElement.getClass())
                .forEach(webElementState -> webElementStates
                        .put(webElementState.name(), createWebElementStateHolder(webElementState)));
        return WebElementStateRegistry.of(webElementStates);
    }

    protected static @NotNull WebElementStateHolder createWebElementStateHolder(@NotNull WebElementState webElementState) {
        Optional<WebLocatorHolder> optionalWebLocatorHolder = createOptionalWebLocatorHolder(webElementState.locator());
        List<String> args = new ArrayList<>();
        args.addAll(Arrays.asList(webElementState.params()));
        Constructor<? extends WebElementStateExtractor> constructor = getDeclaredConstructor(webElementState.extractor());
        WebElementStateExtractor extractorInstance = newInstance(constructor, args);
        return WebElementStateHolder.of(webElementState.name(), optionalWebLocatorHolder.orElse(null), extractorInstance);
    }

}

