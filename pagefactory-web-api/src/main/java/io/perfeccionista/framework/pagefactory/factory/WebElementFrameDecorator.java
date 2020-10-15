package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.name.WebChildElementIdentifier;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementNameHandler.extractNames;
import static io.perfeccionista.framework.utils.ReflectionUtils.writeField;

public class WebElementFrameDecorator {

    public static final String ELEMENT_IDENTIFIER_FIELD = "elementIdentifier";
    public static final String ELEMENT_REGISTRY_FIELD = "elementRegistry";
    public static final String ELEMENT_CLASS_FIELD = "elementClass";

    private WebElementFrameDecorator() {
    }

    public static @NotNull <T extends WebBlock> T decorateWebMappedBlockFrameInstance(@NotNull T webMappedBlockFrameInstance,
                                                                                      @NotNull WebElementRegistry elementRegistry,
                                                                                      @NotNull Class<T> webMappedBlockClass) {
        writeField(ELEMENT_REGISTRY_FIELD, webMappedBlockFrameInstance, elementRegistry);
        writeField(ELEMENT_CLASS_FIELD, webMappedBlockFrameInstance, webMappedBlockClass);
        return webMappedBlockFrameInstance;
    }

    public static @NotNull WebBlock decorateWebBlockFrameInstance(@NotNull WebBlock webBlockFrameInstance,
                                                                  @NotNull WebElementRegistry elementRegistry,
                                                                  @NotNull Class<? extends WebBlock> webBlockClass,
                                                                  @NotNull Method webBlockMethod) {
        decorateWebChildElementFrameInstance(webBlockFrameInstance, webBlockClass, webBlockMethod);
        writeField(ELEMENT_REGISTRY_FIELD, webBlockFrameInstance, elementRegistry);
        return webBlockFrameInstance;
    }

    public static @NotNull WebChildElement decorateWebChildElementFrameInstance(@NotNull WebChildElement webChildElementFrameInstance,
                                                                                @NotNull Class<? extends WebChildElement> webChildElementClass,
                                                                                @NotNull Method webChildElementMethod) {
        WebElementIdentifier identifier = WebChildElementIdentifier.of(extractNames(webChildElementFrameInstance, webChildElementMethod), webChildElementMethod);
        writeField(ELEMENT_IDENTIFIER_FIELD, webChildElementFrameInstance, identifier);
        writeField(ELEMENT_CLASS_FIELD, webChildElementFrameInstance, webChildElementClass);
        return webChildElementFrameInstance;
    }

}
