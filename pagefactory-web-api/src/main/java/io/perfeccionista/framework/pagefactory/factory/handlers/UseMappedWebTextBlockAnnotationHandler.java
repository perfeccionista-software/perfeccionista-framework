package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.exceptions.MappedBlockIncorrectType;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.MAPPED_BLOCK_IMPLEMENTATION_INCORRECT_TYPE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAnnotation;
import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;

public class UseMappedWebTextBlockAnnotationHandler {

    private UseMappedWebTextBlockAnnotationHandler() {
    }

    public static @NotNull WebListFrame<DefaultWebTextBlock> createWebTextListFrame(@NotNull WebTextList webTextList,
                                                                                    @NotNull Method elementMethod,
                                                                                    @NotNull WebPageFactory webPageFactory,
                                                                                    @NotNull WebPageFactoryPreferences configuration) {
        Class<? extends WebBlock> webMappedBlockClass = configuration.getWebMappedBlock(webTextList.getClass());

        Optional<UseMappedWebBlock> optionalClassAnnotation = findFirstAnnotationInHierarchy(UseMappedWebBlock.class,
                WebChildElement.class, webTextList.getClass());
        if (optionalClassAnnotation.isPresent()) {
            webMappedBlockClass = optionalClassAnnotation.get().value();
        }

        Optional<UseMappedWebBlock> optionalMethodAnnotation = findAnnotation(elementMethod, UseMappedWebBlock.class);
        if (optionalMethodAnnotation.isPresent()) {
            webMappedBlockClass = optionalMethodAnnotation.get().value();
        }

        DefaultWebTextBlock webTextListBlock = null;

        if (Objects.nonNull(webMappedBlockClass)) {
            if (!isSubtypeOf(webMappedBlockClass, DefaultWebTextBlock.class)) {
                throw MappedBlockIncorrectType
                        .exception(MAPPED_BLOCK_IMPLEMENTATION_INCORRECT_TYPE.getMessage(DefaultWebTextBlock.class.getCanonicalName()));
            }
            webTextListBlock = (DefaultWebTextBlock) webPageFactory
                    .createMappedWebBlock(webTextList, webMappedBlockClass);
        }

        return new WebListFrame<>(webTextList, webTextListBlock);
    }

}
