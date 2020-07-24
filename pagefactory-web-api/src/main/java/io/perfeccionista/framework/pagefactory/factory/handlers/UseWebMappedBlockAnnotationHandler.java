package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

public class UseWebMappedBlockAnnotationHandler {

    private UseWebMappedBlockAnnotationHandler() {
    }

    public static Optional<Class<? extends WebMappedBlock>> getMappedBlock(WebChildElement webChildElement, Method elementMethod) {
        Optional<UseWebMappedBlock> optionalMethodAnnotation = findAnnotation(elementMethod, UseWebMappedBlock.class);
        if (optionalMethodAnnotation.isPresent()) {
            return Optional.of(optionalMethodAnnotation.get().value());
        }
        Optional<UseWebMappedBlock> optionalClassAnnotation = findFirstAnnotationInHierarchy(UseWebMappedBlock.class,
                WebChildElement.class, webChildElement.getClass());
        if (optionalClassAnnotation.isPresent()) {
            return Optional.of(optionalClassAnnotation.get().value());
        }
        return Optional.empty();
    }

}
