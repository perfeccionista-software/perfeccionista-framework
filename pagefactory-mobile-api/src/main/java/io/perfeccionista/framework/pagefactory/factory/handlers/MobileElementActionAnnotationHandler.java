package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementAction;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;

import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public class MobileElementActionAnnotationHandler {

    private MobileElementActionAnnotationHandler() {
    }

    public static @NotNull EndpointHandlerRegistry createMobileElementActionRegistryFor(@NotNull MobileChildElement mobileChildElement,
                                                                                        @NotNull Method elementMethod,
                                                                                        @NotNull MobilePageFactoryPreferences configuration) {
        Map<String, Class<? extends EndpointHandler>> mobileElementActions = configuration
                .getMobileElementActionConfiguration(mobileChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(MobileElementAction.class, MobileChildElement.class, mobileChildElement.getClass())
                .forEach(mobileElementAction -> mobileElementActions
                        .put(mobileElementAction.name(), mobileElementAction.handler()));
        findRepeatableAnnotations(elementMethod, MobileElementAction.class)
                .forEach(mobileElementAction -> mobileElementActions
                        .put(mobileElementAction.name(), mobileElementAction.handler()));
        return EndpointHandlerRegistry.of(mobileElementActions);
    }

    public static @NotNull EndpointHandlerRegistry createMobileElementActionRegistryFor(@NotNull MobileChildElement mobileChildElement,
                                                                                        @NotNull MobilePageFactoryPreferences configuration) {
        Map<String, Class<? extends EndpointHandler>> mobileElementActions = configuration
                .getMobileElementActionConfiguration(mobileChildElement.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(MobileElementAction.class, MobileChildElement.class, mobileChildElement.getClass())
                .forEach(mobileElementAction -> mobileElementActions
                        .put(mobileElementAction.name(), mobileElementAction.handler()));
        return EndpointHandlerRegistry.of(mobileElementActions);
    }

    public static @NotNull EndpointHandlerRegistry createMobileElementActionRegistryFor(@NotNull MobilePage mobilePage,
                                                                                        @NotNull MobilePageFactoryPreferences configuration) {
        Map<String, Class<? extends EndpointHandler>> mobileElementActions = configuration
                .getMobilePageActionConfiguration(mobilePage.getClass())
                .asMap();
        findAllRepeatableAnnotationsInHierarchy(MobileElementAction.class, MobilePage.class, mobilePage.getClass())
                .forEach(mobileElementAction -> mobileElementActions
                        .put(mobileElementAction.name(), mobileElementAction.handler()));
        return EndpointHandlerRegistry.of(mobileElementActions);
    }

}
