package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.dispatcher.DeviceType;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementState;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateExtractor;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateHolder;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.ANDROID;
import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.IOS;
import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileLocatorAnnotationHandler.createOptionalMobileLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.getDeclaredConstructor;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.newInstance;

public class MobileElementStateAnnotationHandler {

    private MobileElementStateAnnotationHandler() {
    }

    public static @NotNull MobileElementStateRegistry createMobileElementStateRegistryFor(@NotNull DeviceType deviceType,
                                                                                          @NotNull MobileChildElement mobileChildElement,
                                                                                          @NotNull Method elementMethod,
                                                                                          @NotNull MobilePageFactoryPreferences configuration) {
        Map<String, MobileElementStateHolder> mobileElementStates = configuration
                .getMobileElementStateConfiguration(mobileChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(MobileElementState.class, MobileChildElement.class, mobileChildElement.getClass())
                .forEach(mobileElementProperty -> mobileElementStates
                        .put(mobileElementProperty.name(), createMobileElementStateHolder(deviceType, mobileElementProperty)));
        findRepeatableAnnotations(elementMethod, MobileElementState.class)
                .forEach(mobileElementProperty -> mobileElementStates
                        .put(mobileElementProperty.name(), createMobileElementStateHolder(deviceType, mobileElementProperty)));
        return MobileElementStateRegistry.of(mobileElementStates);
    }

    public static @NotNull MobileElementStateRegistry createMobileElementStateRegistryFor(@NotNull DeviceType deviceType,
                                                                                          @NotNull MobileChildElement mobileChildElement,
                                                                                          @NotNull MobilePageFactoryPreferences configuration) {
        Map<String, MobileElementStateHolder> mobileElementStates = configuration
                .getMobileElementStateConfiguration(mobileChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(MobileElementState.class, MobileChildElement.class, mobileChildElement.getClass())
                .forEach(mobileElementProperty -> mobileElementStates
                        .put(mobileElementProperty.name(), createMobileElementStateHolder(deviceType, mobileElementProperty)));
        return MobileElementStateRegistry.of(mobileElementStates);
    }

    protected static @NotNull MobileElementStateHolder createMobileElementStateHolder(@NotNull DeviceType deviceType,
                                                                                      @NotNull MobileElementState mobileElementState) {
        Optional<MobileLocatorHolder> optionalMobileLocatorHolder = Optional.empty();
        List<String> args = new ArrayList<>();
        if (ANDROID == deviceType) {
            optionalMobileLocatorHolder = createOptionalMobileLocatorHolder(mobileElementState.androidLocator());
            args.addAll(Arrays.asList(mobileElementState.androidParams()));
        } else if (IOS == deviceType) {
            optionalMobileLocatorHolder = createOptionalMobileLocatorHolder(mobileElementState.iosLocator());
            args.addAll(Arrays.asList(mobileElementState.iosParams()));
        }
        Constructor<? extends MobileElementStateExtractor> constructor = getDeclaredConstructor(mobileElementState.androidExtractor());
        MobileElementStateExtractor extractorInstance = newInstance(constructor, args);
        return MobileElementStateHolder.of(mobileElementState.name(), optionalMobileLocatorHolder.orElse(null), extractorInstance);
    }

}
