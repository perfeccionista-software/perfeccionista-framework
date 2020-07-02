package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementIdentifierHandler.extractNames;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createWebLocatorRegistryFor;
import static io.perfeccionista.framework.utils.ReflectionUtils.writeField;

public class WebElementMockDecorator {

    private WebElementMockDecorator() {
    }

    public static <T extends WebMappedBlock> T decorateWebMappedBlockMockInstance(T webMappedBlockMock,
                                                                                  WebElementRegistry elementRegistry) {
        writeField("parentMock", webMappedBlockMock, null);
        writeField("elementRegistry", webMappedBlockMock, elementRegistry);
        return webMappedBlockMock;
    }

    public static WebBlock decorateWebBlockMockInstance(WebParentElement parent,
                                                        WebBlock webBlockInstance,
                                                        Method webChildElementMethod,
                                                        WebElementRegistry elementRegistry) {
        decorateWebChildElementMockInstance(parent, webBlockInstance, webChildElementMethod);
        writeField("elementRegistry", webBlockInstance, elementRegistry);
        return webBlockInstance;
    }

    public static WebChildElement decorateWebChildElementMockInstance(WebParentElement parent,
                                                                      WebChildElement webChildElementInstance,
                                                                      Method webChildElementMethod) {
        writeField("parentMock", webChildElementInstance, parent);
        writeField("locatorRegistry", webChildElementInstance, createWebLocatorRegistryFor(webChildElementInstance, webChildElementMethod));
        WebElementIdentifier identifier = WebElementIdentifier.of(extractNames(webChildElementInstance, webChildElementMethod), webChildElementMethod);
        writeField("elementIdentifier", webChildElementInstance, identifier);
        return webChildElementInstance;
    }

}
