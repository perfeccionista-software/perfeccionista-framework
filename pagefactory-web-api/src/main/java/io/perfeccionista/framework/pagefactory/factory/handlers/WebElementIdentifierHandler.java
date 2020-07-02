package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public class WebElementIdentifierHandler {

    private WebElementIdentifierHandler() {
    }

    public static Map<String, Boolean> extractNames(WebChildElement webChildElement, Method elementMethod) {
        Map<String, Boolean> names = new HashMap<>();
        findRepeatableAnnotations(elementMethod, Name.class)
                .forEach(name -> names.put(name.value(), name.deprecated()));
        findAllRepeatableAnnotationsInHierarchy(Name.class, WebChildElement.class, webChildElement.getClass())
                .forEach(name -> {
                    if (!names.containsKey(name.value())) {
                        names.put(name.value(), name.deprecated());
                    }
                });
        return names;
    }

}
