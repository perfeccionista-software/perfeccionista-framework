package io.perfeccionista.framework.pagefactory.elements.preferences;

import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.MobilePageImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTableRowImpl;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface MobilePageFactoryPreferences {

    // GetImplementations
    @NotNull Class<? extends MobilePageImpl> getMobilePageImplementation();
    MobilePageFactoryPreferences setMobilePageImplementation(@NotNull Class<? extends MobilePageImpl> mobilePageImplementation);

    @NotNull Class<? extends MobileBlockImpl> getMobileBlockImplementation();
    MobilePageFactoryPreferences setMobileBlockImplementation(@NotNull Class<? extends MobileBlockImpl> mobileBlockImplementation);

    @NotNull Class<? extends MobileBlockImpl> getMappedMobileBlockImplementation();
    MobilePageFactoryPreferences setMappedMobileBlockImplementation(@NotNull Class<? extends MobileBlockImpl> mappedMobileBlockImplementation);

    @NotNull Class<? extends MobileTableRowImpl> getMobileTableRowImplementation();
    MobilePageFactoryPreferences setMobileTableRowImplementation(@NotNull Class<? extends MobileTableRowImpl> mobileTableRowImplementation);

    /**
     * Имплементация может быть не задана для интерфейсов, которые наследуются от других интерфейсов только чтобы переопределить настройки элемента.
     * @param mobileElementType - тип элемента. Интерфейс, который возвращает метод
     */
    @Nullable Class<? extends MobileChildElement> getMobileElementImplementation(@NotNull Class<? extends MobileChildElement> mobileElementType);
    MobilePageFactoryPreferences setMobileElementImplementations(@NotNull Map<Class<? extends MobileChildElement>, Class<? extends MobileChildElement>> mobileElementImplementations);

    // GetMappedBlocks
    @Nullable Class<? extends MobileBlock> getMobileMappedBlock(@NotNull Class<? extends MobileChildElement> mobileElementType);
    MobilePageFactoryPreferences setMobileMappedBlocks(@NotNull Map<Class<? extends MobileChildElement>, Class<? extends MobileBlock>> mobileMappedBlocks);

    // GetActions
    @NotNull MobileEndpointHandlerConfiguration getMobilePageActionConfiguration(@NotNull Class<? extends MobilePage> mobilePageImplementation);
    MobilePageFactoryPreferences setMobilePageActionConfigurations(@NotNull Map<Class<? extends MobilePage>, MobileEndpointHandlerConfiguration> mobilePageActionConfigurations);
    @NotNull MobileEndpointHandlerConfiguration getMobileElementActionConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementImplementation);
    MobilePageFactoryPreferences setMobileElementActionConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileEndpointHandlerConfiguration> mobileElementActionConfigurations);

    // GetMobileProperties
    @NotNull MobileElementPropertyConfiguration getMobileElementPropertyConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementClass);
    MobilePageFactoryPreferences setMobileElementPropertyConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileElementPropertyConfiguration> mobileElementPropertyConfigurations);

    // GetMobileStates
    @NotNull MobileElementStateConfiguration getMobileElementStateConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementClass);
    MobilePageFactoryPreferences setMobileElementStateConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileElementStateConfiguration> mobileElementStateConfigurations);

    // GetLocators
    @NotNull MobileLocatorConfiguration getMobileLocatorConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementClass);
    MobilePageFactoryPreferences setMobileLocatorConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileLocatorConfiguration> mobileElementLocatorConfigurations);

}
