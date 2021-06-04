package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileListFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedMobileBlock;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAnnotation;
import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;

public class UseMappedMobileBlockAnnotationHandler {

    private UseMappedMobileBlockAnnotationHandler() {
    }

    public static @NotNull MobileListFrame<MobileBlock> createMobileListFrame(@NotNull MobileList webList,
                                                                              @NotNull Method elementMethod,
                                                                              @NotNull MobilePageFactory webPageFactory,
                                                                              @NotNull MobilePageFactoryPreferences configuration) {
        Class<? extends MobileChildElement> webChildElementType = webList.getElementIdentifier().getElementType();
        Class<? extends MobileBlock> webMappedBlockClass = configuration.getMobileMappedBlock(webChildElementType);

        Optional<UseMappedMobileBlock> optionalClassAnnotation =
                findFirstAnnotationInHierarchy(UseMappedMobileBlock.class, MobileChildElement.class, webList.getClass());
        if (optionalClassAnnotation.isPresent()) {
            webMappedBlockClass = optionalClassAnnotation.get().value();
        }

        Optional<UseMappedMobileBlock> optionalMethodAnnotation =
                findAnnotation(elementMethod, UseMappedMobileBlock.class);
        if (optionalMethodAnnotation.isPresent()) {
            webMappedBlockClass = optionalMethodAnnotation.get().value();
        }

        MobileBlock webListBlock = null;

        if (Objects.nonNull(webMappedBlockClass)) {
            webListBlock = webPageFactory.createMappedMobileBlock(webList, webMappedBlockClass);
        }

        return new MobileListFrame<>(webList, webListBlock);
    }

}
