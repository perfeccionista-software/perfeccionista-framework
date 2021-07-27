package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.exceptions.MappedBlockIncorrectType;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebRadioButtonBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebRadioButtonBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebRadioGroupFrame;
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

public class UseMappedWebRadioButtonBlockAnnotationHandler {

    private UseMappedWebRadioButtonBlockAnnotationHandler() {
    }

    public static @NotNull WebRadioGroupFrame<DefaultWebRadioButtonBlock> createWebRadioGroupFrame(@NotNull WebRadioGroup webRadioGroup,
                                                                                                   @NotNull Method elementMethod,
                                                                                                   @NotNull WebPageFactory webPageFactory,
                                                                                                   @NotNull WebPageFactoryPreferences configuration) {
        Class<? extends WebBlock> webMappedBlockClass = configuration.getWebMappedBlock(webRadioGroup.getClass());

        Optional<UseMappedWebRadioButtonBlock> optionalClassAnnotation = findFirstAnnotationInHierarchy(UseMappedWebRadioButtonBlock.class,
                WebChildElement.class,
                webRadioGroup.getClass());
        if (optionalClassAnnotation.isPresent()) {
            webMappedBlockClass = optionalClassAnnotation.get().value();
        }

        Optional<UseMappedWebRadioButtonBlock> optionalMethodAnnotation = findAnnotation(elementMethod, UseMappedWebRadioButtonBlock.class);
        if (optionalMethodAnnotation.isPresent()) {
            webMappedBlockClass = optionalMethodAnnotation.get().value();
        }

        DefaultWebRadioButtonBlock webRadioButtonBlock = null;

        if (Objects.nonNull(webMappedBlockClass)) {
            if (!isSubtypeOf(webMappedBlockClass, DefaultWebRadioButtonBlock.class)) {
                throw MappedBlockIncorrectType
                        .exception(MAPPED_BLOCK_IMPLEMENTATION_INCORRECT_TYPE.getMessage(DefaultWebRadioButtonBlock.class.getCanonicalName()));
            }
            webRadioButtonBlock = (DefaultWebRadioButtonBlock) webPageFactory.createMappedWebBlock(webRadioGroup, webMappedBlockClass);
        } else {
            webRadioButtonBlock = webPageFactory.createMappedWebBlock(webRadioGroup, DefaultWebRadioButtonBlock.class);
        }

        return new WebRadioGroupFrame<>(webRadioButtonBlock);
    }

}
