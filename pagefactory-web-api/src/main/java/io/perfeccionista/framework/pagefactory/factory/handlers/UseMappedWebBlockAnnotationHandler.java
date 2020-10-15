package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.exceptions.WebMappedBlockNotFound;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_MAPPED_BLOCK_NOT_FOUND;
import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

public class UseMappedWebBlockAnnotationHandler {

    private UseMappedWebBlockAnnotationHandler() {
    }

    public static @NotNull WebListFrame<WebBlock> createWebListFrame(@NotNull WebList webList,
                                                                     @NotNull Method elementMethod,
                                                                     @NotNull WebPageFactory webPageFactory,
                                                                     @NotNull WebPageFactoryPreferences configuration) {
        Class<? extends WebChildElement> webChildElementType = webList.getElementIdentifier().getElementType();
        Class<? extends WebBlock> webMappedBlockClass = configuration.getWebMappedBlock(webChildElementType);

        Optional<UseMappedWebBlock> optionalClassAnnotation =
                findFirstAnnotationInHierarchy(UseMappedWebBlock.class, WebChildElement.class, webList.getClass());
        if (optionalClassAnnotation.isPresent()) {
            webMappedBlockClass = optionalClassAnnotation.get().value();
        }

        Optional<UseMappedWebBlock> optionalMethodAnnotation =
                findAnnotation(elementMethod, UseMappedWebBlock.class);
        if (optionalMethodAnnotation.isPresent()) {
            webMappedBlockClass = optionalMethodAnnotation.get().value();
        }

        if (Objects.isNull(webMappedBlockClass)) {
            throw WebMappedBlockNotFound.exception(WEB_MAPPED_BLOCK_NOT_FOUND.getMessage(webChildElementType.getCanonicalName()));
        }

        WebBlock webListBlock = webPageFactory.createMappedWebBlock(webList, webMappedBlockClass);

        return new WebListFrame<>(webListBlock);
    }

}