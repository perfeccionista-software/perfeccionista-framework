package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;

public class MobileElementNameHandler {

    private MobileElementNameHandler() {
    }

    public static @NotNull Map<String, Boolean> extractNames(@NotNull MobileChildElement webChildElement,
                                                             @NotNull Method elementMethod) {
        Map<String, Boolean> names = new HashMap<>();
        findAllRepeatableAnnotationsInHierarchy(Name.class, MobileChildElement.class, webChildElement.getClass())
                .forEach(name -> names.put(name.value(), name.deprecated()));
        findRepeatableAnnotations(elementMethod, Name.class)
                .forEach(name -> names.put(name.value(), name.deprecated()));
        return names;
    }

}
