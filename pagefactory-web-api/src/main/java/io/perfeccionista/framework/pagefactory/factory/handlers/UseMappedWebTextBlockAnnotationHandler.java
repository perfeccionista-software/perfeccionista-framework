package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.exceptions.WebMappedBlockIncorrectType;
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
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_MAPPED_BLOCK_INCORRECT_TYPE;
import static io.perfeccionista.framework.utils.AnnotationUtils.findFirstAnnotationInHierarchy;
import static io.perfeccionista.framework.utils.ReflectionUtils.isSubtypeOf;
import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

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

        if (!isSubtypeOf(webMappedBlockClass, DefaultWebTextBlock.class)) {
            throw WebMappedBlockIncorrectType
                    .exception(WEB_MAPPED_BLOCK_INCORRECT_TYPE.getMessage(DefaultWebTextBlock.class.getCanonicalName()));
        }

        DefaultWebTextBlock webTextListBlock = (DefaultWebTextBlock) webPageFactory
                .createMappedWebBlock(webTextList, webMappedBlockClass);

        return new WebListFrame<>(webTextListBlock);
    }

}
