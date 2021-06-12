package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.dispatcher.DeviceType;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementProperty;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.ANDROID;
import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.IOS;
import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileLocatorAnnotationHandler.createOptionalMobileLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileElementPropertyAnnotationHandler {

    private MobileElementPropertyAnnotationHandler() {
    }

    public static @NotNull MobileElementPropertyRegistry createMobileElementPropertyRegistryFor(@NotNull DeviceType deviceType,
                                                                                                @NotNull MobileChildElement mobileChildElement,
                                                                                                @NotNull Method elementMethod,
                                                                                                @NotNull MobilePageFactoryPreferences configuration) {
        Map<String, MobileElementPropertyHolder> mobileElementProperties = configuration
                .getMobileElementPropertyConfiguration(mobileChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(MobileElementProperty.class, MobileChildElement.class, mobileChildElement.getClass())
                .forEach(mobileElementProperty -> mobileElementProperties
                        .put(mobileElementProperty.name(), createMobileElementPropertyHolder(deviceType, mobileElementProperty)));
        findRepeatableAnnotations(elementMethod, MobileElementProperty.class)
                .forEach(mobileElementProperty -> mobileElementProperties
                        .put(mobileElementProperty.name(), createMobileElementPropertyHolder(deviceType, mobileElementProperty)));
        return MobileElementPropertyRegistry.of(mobileElementProperties);
    }

    public static @NotNull MobileElementPropertyRegistry createMobileElementPropertyRegistryFor(@NotNull DeviceType deviceType,
                                                                                                @NotNull MobileChildElement mobileChildElement,
                                                                                                @NotNull MobilePageFactoryPreferences configuration) {
        Map<String, MobileElementPropertyHolder> mobileElementProperties = configuration
                .getMobileElementPropertyConfiguration(mobileChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(MobileElementProperty.class, MobileChildElement.class, mobileChildElement.getClass())
                .forEach(mobileElementProperty -> mobileElementProperties
                        .put(mobileElementProperty.name(), createMobileElementPropertyHolder(deviceType, mobileElementProperty)));
        return MobileElementPropertyRegistry.of(mobileElementProperties);
    }

    protected static @NotNull MobileElementPropertyHolder createMobileElementPropertyHolder(@NotNull DeviceType deviceType,
                                                                                            @NotNull MobileElementProperty mobileElementProperty) {
        Optional<MobileLocatorHolder> optionalMobileLocatorHolder = Optional.empty();
        if (ANDROID == deviceType) {
            optionalMobileLocatorHolder = createOptionalMobileLocatorHolder(mobileElementProperty.androidLocator());
        } else if (IOS == deviceType) {
            optionalMobileLocatorHolder = createOptionalMobileLocatorHolder(mobileElementProperty.iosLocator());
        }
        return MobileElementPropertyHolder.of(
                mobileElementProperty.name(),
                optionalMobileLocatorHolder.orElse(null),
                newInstance(mobileElementProperty.androidExtractor())
        );
    }

}
