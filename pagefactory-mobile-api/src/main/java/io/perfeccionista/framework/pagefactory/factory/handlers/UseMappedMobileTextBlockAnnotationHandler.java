package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.exceptions.MappedBlockIncorrectType;
import io.perfeccionista.framework.pagefactory.elements.DefaultMobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileListFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedMobileBlock;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.MAPPED_BLOCK_IMPLEMENTATION_INCORRECT_TYPE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAnnotation;
import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;

public class UseMappedMobileTextBlockAnnotationHandler {

    private UseMappedMobileTextBlockAnnotationHandler() {
    }

    public static @NotNull MobileListFrame<DefaultMobileTextBlock> createMobileTextListFrame(@NotNull MobileTextList mobileTextList,
                                                                                             @NotNull Method elementMethod,
                                                                                             @NotNull MobilePageFactory mobilePageFactory,
                                                                                             @NotNull MobilePageFactoryPreferences configuration) {
        Class<? extends MobileBlock> mobileMappedBlockClass = configuration.getMobileMappedBlock(mobileTextList.getClass());

        Optional<UseMappedMobileBlock> optionalClassAnnotation = findFirstAnnotationInHierarchy(UseMappedMobileBlock.class,
                MobileChildElement.class, mobileTextList.getClass());
        if (optionalClassAnnotation.isPresent()) {
            mobileMappedBlockClass = optionalClassAnnotation.get().value();
        }

        Optional<UseMappedMobileBlock> optionalMethodAnnotation = findAnnotation(elementMethod, UseMappedMobileBlock.class);
        if (optionalMethodAnnotation.isPresent()) {
            mobileMappedBlockClass = optionalMethodAnnotation.get().value();
        }

        DefaultMobileTextBlock mobileTextListBlock = null;

        if (Objects.nonNull(mobileMappedBlockClass)) {
            if (!isSubtypeOf(mobileMappedBlockClass, DefaultMobileTextBlock.class)) {
                throw MappedBlockIncorrectType
                        .exception(MAPPED_BLOCK_IMPLEMENTATION_INCORRECT_TYPE.getMessage(DefaultMobileTextBlock.class.getCanonicalName()));
            }
            mobileTextListBlock = (DefaultMobileTextBlock) mobilePageFactory
                    .createMappedMobileBlock(mobileTextList, mobileMappedBlockClass);
        }

        return new MobileListFrame<>(mobileTextList, mobileTextListBlock);
    }

}
