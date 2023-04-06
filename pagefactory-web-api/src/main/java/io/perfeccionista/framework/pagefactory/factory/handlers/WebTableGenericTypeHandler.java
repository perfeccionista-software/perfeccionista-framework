package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebNode;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findGenericInterface;

public class WebTableGenericTypeHandler {

    private WebTableGenericTypeHandler() {
    }

    public static <H extends WebBlock<?>, T extends WebBlock<?>> @NotNull WebTableFrame<WebBlock<?>, WebBlock<?>> createWebTableFrame(@NotNull WebTable<?, T> webTable,
                                                                                                                                      @NotNull Method elementMethod,
                                                                                                                                      @NotNull WebPageFactory webPageFactory,
                                                                                                                                      @NotNull WebPageFactoryPreferences configuration) {

        Class<? extends WebChildElement> webChildElementType = webTable.getElementIdentifier().getElementType();

        Class<? extends WebBlock<?>> webMappedHeaderClass = configuration.getWebMappedBlock(webChildElementType);
        Class<? extends WebBlock<?>> webMappedBlockClass = configuration.getWebMappedBlock(webChildElementType);

        if (elementMethod.getReturnType().equals(WebTable.class)) {
            Type genericReturnType = elementMethod.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType) {
                webMappedHeaderClass = (Class<? extends WebBlock<?>>) ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];
                webMappedBlockClass = (Class<? extends WebBlock<?>>) ((ParameterizedType) genericReturnType).getActualTypeArguments()[1];
            } else {
                webMappedHeaderClass = WebNode.class;
                webMappedBlockClass = WebNode.class;
            }
        } else {
            Optional<Type> optionalGenericListInterface = findGenericInterface(elementMethod.getReturnType(), WebTable.class);
            if (optionalGenericListInterface.isPresent()) {
                Type genericListInterface = optionalGenericListInterface.get();
                if (genericListInterface instanceof ParameterizedType) {
                    webMappedHeaderClass = (Class<? extends WebBlock<?>>) ((ParameterizedType) genericListInterface).getActualTypeArguments()[0];
                    webMappedBlockClass = (Class<? extends WebBlock<?>>) ((ParameterizedType) genericListInterface).getActualTypeArguments()[1];
                } else {
                    webMappedHeaderClass = WebNode.class;
                    webMappedBlockClass = WebNode.class;
                }
            }
        }

        WebBlock<?> webTableHeader;
        WebBlock<?> webTableBlock;

        if (Objects.nonNull(webMappedHeaderClass)) {
            webTableHeader = webPageFactory.createWebTableHeader(webTable, webMappedHeaderClass);
        } else {
            webTableHeader = webPageFactory.createWebTableHeader(webTable, WebBlock.class);
        }

        if (Objects.nonNull(webMappedBlockClass)) {
            webTableBlock = webPageFactory.createMappedWebBlock(webTable, webMappedBlockClass);
        } else {
            webTableBlock = webPageFactory.createMappedWebBlock(webTable, WebBlock.class);
        }

        return new WebTableFrame<>(webTableHeader, webTableBlock);
    }

}
