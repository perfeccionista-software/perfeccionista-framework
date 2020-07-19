package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementActionAnnotationHandler.createWebElementActionRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementIdentifierHandler.extractNames;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementPropertyAnnotationHandler.createWebElementPropertyRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createWebLocatorRegistryFor;
import static io.perfeccionista.framework.utils.ReflectionUtils.writeField;

public class WebElementMockDecorator {

    // TODO: Возможно, тут кроме объекта с методом ничего не нужно вызывать.
    //  остальное создавать при инициализации мока внутри кондишена или экстрактора
    private WebElementMockDecorator() {
    }

    public static <T extends WebMappedBlock> T decorateWebMappedBlockMockInstance(T webMappedBlockMock,
                                                                                  WebElementRegistry elementRegistry) {
        writeField("parentMock", webMappedBlockMock, null);
        writeField("elementRegistry", webMappedBlockMock, elementRegistry);
        return webMappedBlockMock;
    }



    public static WebBlock decorateWebBlockMockInstance(WebParentElement parent,
                                                        WebBlock webBlock,
                                                        Class<? extends WebBlock> webBlockClass,
                                                        Method webChildElementMethod,
                                                        WebElementRegistry elementRegistry) {
        decorateWebChildElementMockInstance(parent, webBlock, webBlockClass, webChildElementMethod);
        writeField("elementRegistry", webBlock, elementRegistry);
        writeField("actionRegistry", webBlock, createWebElementActionRegistryFor(webBlock, webChildElementMethod));
        return webBlock;
    }

    public static WebChildElement decorateWebChildElementMockInstance(WebParentElement parent,
                                                                      WebChildElement webChildElement,
                                                                      Class<? extends WebChildElement> webChildElementClass,
                                                                      Method webChildElementMethod) {
        writeField("itemClass", webChildElement, webChildElementClass);
        writeField("parentMock", webChildElement, parent);
        writeField("parentMethod", webChildElement, webChildElementMethod);
        writeField("locatorRegistry", webChildElement, createWebLocatorRegistryFor(webChildElement, webChildElementMethod));
        writeField("propertyRegistry", webChildElement, createWebElementPropertyRegistryFor(webChildElement, webChildElementMethod));
        WebElementIdentifier identifier = WebElementIdentifier.of(extractNames(webChildElement, webChildElementMethod), webChildElementMethod);
        writeField("elementIdentifier", webChildElement, identifier);
        return webChildElement;
    }

}
