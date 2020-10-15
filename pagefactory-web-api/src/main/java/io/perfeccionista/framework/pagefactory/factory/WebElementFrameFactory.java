package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.List;

import static io.perfeccionista.framework.utils.WebElementUtils.getWebChildElementMethods;
import static java.util.stream.Collectors.toList;

public class WebElementFrameFactory {

    private WebElementFrameFactory() {
    }

    public static @NotNull <T extends WebBlock> T createWebBlockFrame(@NotNull Class<T> webMappedBlockClass) {
        T webMappedBlockFrame = WebElementFrameInitializer.initWebBlockFrame(webMappedBlockClass);
        List<Method> webChildElementMethods = getWebChildElementMethods(webMappedBlockClass);
        WebElementRegistry webElementRegistry = createWebChildElementFrameRegistry(webChildElementMethods);
        return WebElementFrameDecorator.decorateWebMappedBlockFrameInstance(webMappedBlockFrame, webElementRegistry, webMappedBlockClass);
    }

    protected static @NotNull WebElementRegistry createWebChildElementFrameRegistry(@NotNull List<Method> parentMethods) {
        List<WebChildElement> childElements = parentMethods.stream()
                .map(childElementMethod -> {
                    //noinspection unchecked because all methods filtered by returnType
                    Class<? extends WebChildElement> childElementType = (Class<? extends WebChildElement>) childElementMethod.getReturnType();
                    if (WebBlock.class.isAssignableFrom(childElementType)) {
                        //noinspection unchecked because webChildElementType already checked
                        Class<? extends WebBlock> webBlockType = (Class<? extends WebBlock>) childElementType;
                        WebBlock webBlockFrameInstance = WebElementFrameInitializer.initWebBlockFrame(webBlockType);
                        List<Method> childElementMethods = getWebChildElementMethods(webBlockType);
                        WebElementRegistry elementRegistry = createWebChildElementFrameRegistry(childElementMethods);
                        return WebElementFrameDecorator.decorateWebBlockFrameInstance(webBlockFrameInstance, elementRegistry, webBlockType, childElementMethod);
                    } else {
                        WebChildElement childElementFrameInstance = WebElementFrameInitializer.initWebChildElementFrame(childElementType);
                        return WebElementFrameDecorator.decorateWebChildElementFrameInstance(childElementFrameInstance, childElementType, childElementMethod);
                    }
                }).collect(toList());
        return WebElementRegistry.of(childElements);
    }

}
