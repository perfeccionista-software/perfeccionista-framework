package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.name.MobileChildElementIdentifier;
import io.perfeccionista.framework.name.MobileElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileElementNameHandler.extractNames;
import static io.perfeccionista.framework.utils.ReflectionUtilsForFields.writeField;

public class MobileElementFrameDecorator {

    public static final String ELEMENT_IDENTIFIER_FIELD = "elementIdentifier";
    public static final String ELEMENT_REGISTRY_FIELD = "elementRegistry";
    public static final String ELEMENT_CLASS_FIELD = "elementClass";

    private MobileElementFrameDecorator() {
    }

    public static @NotNull <T extends MobileBlock> T decorateMobileMappedBlockFrameInstance(@NotNull T webMappedBlockFrameInstance,
                                                                                            @NotNull MobileElementRegistry elementRegistry,
                                                                                            @NotNull Class<T> webMappedBlockClass) {
        writeField(ELEMENT_REGISTRY_FIELD, webMappedBlockFrameInstance, elementRegistry);
        writeField(ELEMENT_CLASS_FIELD, webMappedBlockFrameInstance, webMappedBlockClass);
        return webMappedBlockFrameInstance;
    }

    public static @NotNull MobileBlock decorateMobileBlockFrameInstance(@NotNull MobileBlock webBlockFrameInstance,
                                                                  @NotNull MobileElementRegistry elementRegistry,
                                                                  @NotNull Class<? extends MobileBlock> webBlockClass,
                                                                  @NotNull Method webBlockMethod) {
        decorateMobileChildElementFrameInstance(webBlockFrameInstance, webBlockClass, webBlockMethod);
        writeField(ELEMENT_REGISTRY_FIELD, webBlockFrameInstance, elementRegistry);
        return webBlockFrameInstance;
    }

    public static @NotNull MobileChildElement decorateMobileChildElementFrameInstance(@NotNull MobileChildElement webChildElementFrameInstance,
                                                                                      @NotNull Class<? extends MobileChildElement> webChildElementClass,
                                                                                      @NotNull Method webChildElementMethod) {
        MobileElementIdentifier identifier = MobileChildElementIdentifier.of(extractNames(webChildElementFrameInstance, webChildElementMethod), webChildElementMethod);
        writeField(ELEMENT_IDENTIFIER_FIELD, webChildElementFrameInstance, identifier);
        writeField(ELEMENT_CLASS_FIELD, webChildElementFrameInstance, webChildElementClass);
        return webChildElementFrameInstance;
    }

}
