package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.Required;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.isAnnotated;

public class RequiredAnnotationHandler {

    private RequiredAnnotationHandler() {
    }

    public static boolean getRequired(WebChildElement webChildElement, Method elementMethod) {
        if (isAnnotated(elementMethod, Required.class)) {
            return true;
        }
        return findFirstAnnotationInHierarchy(Required.class, WebChildElement.class, webChildElement.getClass())
                .isPresent();
    }

}
