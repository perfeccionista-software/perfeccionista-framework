package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.List;

import static io.perfeccionista.framework.utils.MobileElementUtils.getMobileChildElementMethods;
import static java.util.stream.Collectors.toList;

public class MobileElementFrameFactory {

    private MobileElementFrameFactory() {
    }

    public static @NotNull <T extends MobileBlock> T createMobileBlockFrame(@NotNull Class<T> webMappedBlockClass) {
        T webMappedBlockFrame = MobileElementFrameInitializer.initMobileBlockFrame(webMappedBlockClass);
        List<Method> webChildElementMethods = getMobileChildElementMethods(webMappedBlockClass);
        MobileElementRegistry webElementRegistry = createMobileChildElementFrameRegistry(webChildElementMethods);
        return MobileElementFrameDecorator.decorateMobileMappedBlockFrameInstance(webMappedBlockFrame, webElementRegistry, webMappedBlockClass);
    }

    protected static @NotNull MobileElementRegistry createMobileChildElementFrameRegistry(@NotNull List<Method> parentMethods) {
        List<MobileChildElement> childElements = parentMethods.stream()
                .map(childElementMethod -> {
                    //noinspection unchecked because all methods filtered by returnType
                    Class<? extends MobileChildElement> childElementType = (Class<? extends MobileChildElement>) childElementMethod.getReturnType();
                    if (MobileBlock.class.isAssignableFrom(childElementType)) {
                        //noinspection unchecked because webChildElementType already checked
                        Class<? extends MobileBlock> webBlockType = (Class<? extends MobileBlock>) childElementType;
                        MobileBlock webBlockFrameInstance = MobileElementFrameInitializer.initMobileBlockFrame(webBlockType);
                        List<Method> childElementMethods = getMobileChildElementMethods(webBlockType);
                        MobileElementRegistry elementRegistry = createMobileChildElementFrameRegistry(childElementMethods);
                        return MobileElementFrameDecorator.decorateMobileBlockFrameInstance(webBlockFrameInstance, elementRegistry, webBlockType, childElementMethod);
                    } else {
                        MobileChildElement childElementFrameInstance = MobileElementFrameInitializer.initMobileChildElementFrame(childElementType);
                        return MobileElementFrameDecorator.decorateMobileChildElementFrameInstance(childElementFrameInstance, childElementType, childElementMethod);
                    }
                }).collect(toList());
        return MobileElementRegistry.of(childElements);
    }

}
