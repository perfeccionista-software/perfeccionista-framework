package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findGenericInterface;

public class UseMappedWebBlockAnnotationHandler {

    private UseMappedWebBlockAnnotationHandler() {
    }

    public static <T extends WebBlock> @NotNull WebListFrame<WebBlock> createWebListFrame(@NotNull WebList<T> webList,
                                                                                          @NotNull Method elementMethod,
                                                                                          @NotNull WebPageFactory webPageFactory,
                                                                                          @NotNull WebPageFactoryPreferences configuration) {

        Class<? extends WebChildElement> webChildElementType = webList.getElementIdentifier().getElementType();
        Class<? extends WebBlock> webMappedBlockClass = configuration.getWebMappedBlock(webChildElementType);

        if (elementMethod.getReturnType().equals(WebList.class)) {
            Type genericReturnType = elementMethod.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType) {
                webMappedBlockClass = (Class<? extends WebBlock>) ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];
            } else {
                webMappedBlockClass = WebBlock.class;
            }
        } else {
            Optional<Type> optionalGenericListInterface = findGenericInterface(elementMethod.getReturnType(), WebList.class);
            if (optionalGenericListInterface.isPresent()) {
                Type genericListInterface = optionalGenericListInterface.get();
                if (genericListInterface instanceof ParameterizedType) {
                    webMappedBlockClass = (Class<? extends WebBlock>) ((ParameterizedType) genericListInterface).getActualTypeArguments()[0];
                } else {
                    webMappedBlockClass = WebBlock.class;
                }
            }
        }

        WebBlock webListBlock = null;

        if (Objects.nonNull(webMappedBlockClass)) {
            webListBlock = webPageFactory.createMappedWebBlock(webList, webMappedBlockClass);
        }

        return new WebListFrame<>(webList, webListBlock);
    }

}
