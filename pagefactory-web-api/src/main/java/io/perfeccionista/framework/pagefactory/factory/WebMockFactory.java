package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

import java.lang.reflect.Method;
import java.util.List;

import static io.perfeccionista.framework.pagefactory.factory.WebPageFactory.getWebChildElementMethods;
import static java.util.stream.Collectors.toList;

public class WebMockFactory {

    private WebMockFactory() {
    }

    public static <T extends WebMappedBlock> T createWebMappedBlockMock(Class<T> webMappedBlockClass) {
        T webMappedBlockMock = WebElementMockInitializer.initWebMappedBlockMock(webMappedBlockClass);
        List<Method> webChildElementMethods = getWebChildElementMethods(webMappedBlockClass);
        WebElementRegistry webElementRegistry = createWebChildElementMocks(webMappedBlockMock, webChildElementMethods);
        return WebElementMockDecorator.decorateWebMappedBlockMockInstance(webMappedBlockMock, webElementRegistry);
    }

    protected static WebElementRegistry createWebChildElementMocks(WebParentElement parent, List<Method> elementMethods) {
        List<WebChildElement> webChildElements = elementMethods.stream()
                .map(webChildElementMethod -> {
                    //noinspection unchecked because all methods filtered by returnType
                    Class<? extends WebChildElement> webChildElementType = (Class<? extends WebChildElement>) webChildElementMethod.getReturnType();
                    if (WebBlock.class.isAssignableFrom(webChildElementType)) {
                        //noinspection unchecked because webChildElementType already checked
                        Class<? extends WebBlock> webBlockType = (Class<? extends WebBlock>) webChildElementType;
                        WebBlock webBlockInstance = WebElementMockInitializer.initWebBlockMock(webBlockType);
                        List<Method> childElementMethods = getWebChildElementMethods(webBlockType);
                        WebElementRegistry elementRegistry = createWebChildElementMocks(webBlockInstance, childElementMethods);
                        return WebElementMockDecorator.decorateWebBlockMockInstance(parent, webBlockInstance, webBlockType, webChildElementMethod, elementRegistry);
                    } else {
                        WebChildElement webChildElementInstance = WebElementMockInitializer.initWebChildElementMock(webChildElementType);
                        return WebElementMockDecorator.decorateWebChildElementMockInstance(parent, webChildElementInstance, webChildElementType, webChildElementMethod);
                    }
                }).collect(toList());
        return WebElementRegistry.of(webChildElements);
    }

}
