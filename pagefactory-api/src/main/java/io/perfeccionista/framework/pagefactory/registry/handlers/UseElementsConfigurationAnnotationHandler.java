package io.perfeccionista.framework.pagefactory.registry.handlers;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseElementsConfiguration;
import io.perfeccionista.framework.utils.ReflectionUtils;
import io.perfeccionista.framework.utils.ReflectionUtils.Order;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class UseElementsConfigurationAnnotationHandler {

    private UseElementsConfigurationAnnotationHandler() {}

    public static Optional<ElementsConfiguration> handleUseElementsConfiguration(Class<? extends Element> elementClass) {
        for (Class<? extends Element> processedClass : ReflectionUtils.getClassInheritors(Element.class, elementClass, Order.DESC)) {
            Optional<UseElementsConfiguration> optionalAnnotation = Arrays
                    .stream(processedClass.getDeclaredAnnotationsByType(UseElementsConfiguration.class))
                    .findFirst();
            if (optionalAnnotation.isPresent()) {
                ElementsConfiguration configuration = newInstance(optionalAnnotation.get().value());
                return Optional.of(configuration);
            }
        }
        return Optional.empty();
    }

}
