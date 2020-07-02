package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.exceptions.WebElementInitializationException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedBlock;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LIST_MAPPED_CLASS_NOT_DECLARED;
import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

public class UseWebMappedBlockAnnotationHandler {

    private UseWebMappedBlockAnnotationHandler() {
    }

    public static Class<? extends WebMappedBlock> getMappedBlock(WebList webChildElement, Method elementMethod) {
        Optional<UseWebMappedBlock> optionalMethodAnnotation = findAnnotation(elementMethod, UseWebMappedBlock.class);
        if (optionalMethodAnnotation.isPresent()) {
            return optionalMethodAnnotation.get().value();
        }
        Optional<UseWebMappedBlock> optionalClassAnnotation = findFirstAnnotationInHierarchy(UseWebMappedBlock.class,
                WebChildElement.class, webChildElement.getClass());
        if (optionalClassAnnotation.isPresent()) {
            return optionalClassAnnotation.get().value();
        }
        throw new WebElementInitializationException(WEB_LIST_MAPPED_CLASS_NOT_DECLARED.getMessage(webChildElement.getElementIdentifier().getLastUsedName()));
    }

}
