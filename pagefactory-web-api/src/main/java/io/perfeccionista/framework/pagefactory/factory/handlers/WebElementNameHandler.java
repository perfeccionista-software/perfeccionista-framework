package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;

public class WebElementNameHandler {

    private WebElementNameHandler() {
    }

    public static @NotNull Map<String, Boolean> extractNames(@NotNull WebChildElement webChildElement,
                                                             @NotNull Method elementMethod) {
        Map<String, Boolean> names = new LinkedHashMap<>();
        findAllRepeatableAnnotationsInHierarchy(Name.class, WebChildElement.class, webChildElement.getClass())
                .forEach(name -> names.put(name.value(), name.deprecated()));
        findRepeatableAnnotations(elementMethod, Name.class)
                .forEach(name -> names.put(name.value(), name.deprecated()));
        return names;
    }

}
