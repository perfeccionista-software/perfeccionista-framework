package io.perfeccionista.framework.pagefactory.elements.preferences;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebPageImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTableRowImpl;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface WebPageFactoryPreferences {

    // GetImplementations
    @NotNull Class<? extends WebPageImpl> getWebPageImplementation();
    WebPageFactoryPreferences setWebPageImplementation(@NotNull Class<? extends WebPageImpl> webPageImplementation);

    @NotNull Class<? extends WebBlockImpl> getWebBlockImplementation();
    WebPageFactoryPreferences setWebBlockImplementation(@NotNull Class<? extends WebBlockImpl> webBlockImplementation);

    @NotNull Class<? extends WebBlockImpl> getMappedWebBlockImplementation();
    WebPageFactoryPreferences setMappedWebBlockImplementation(@NotNull Class<? extends WebBlockImpl> mappedWebBlockImplementation);

    @NotNull Class<? extends WebTableRowImpl> getWebTableRowImplementation();
    WebPageFactoryPreferences setWebTableRowImplementation(@NotNull Class<? extends WebTableRowImpl> webTableRowImplementation);

    /**
     * Имплементация может быть не задана для интерфейсов, которые наследуются от других интерфейсов только чтобы переопределить настройки элемента.
     * @param webElementType - тип элемента. Интерфейс, который возвращает метод
     */
    @Nullable Class<? extends WebChildElement> getWebElementImplementation(@NotNull Class<? extends WebChildElement> webElementType);
    WebPageFactoryPreferences setWebElementImplementations(@NotNull Map<Class<? extends WebChildElement>, Class<? extends WebChildElement>> webElementImplementations);

    // GetMappedBlocks
    @Nullable Class<? extends WebBlock> getWebMappedBlock(@NotNull Class<? extends WebChildElement> webElementType);
    WebPageFactoryPreferences setWebMappedBlocks(@NotNull Map<Class<? extends WebChildElement>, Class<? extends WebBlock>> webMappedBlocks);

    // GetActions
    @NotNull WebElementActionConfiguration getWebElementActionConfiguration(@NotNull Class<? extends WebChildElementBase> webElementImplementation);
    WebPageFactoryPreferences setWebElementActionConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebElementActionConfiguration> webElementActionConfigurations);

    // GetInteractions
    @NotNull WebElementInteractionConfiguration getWebElementInteractionConfiguration(@NotNull Class<? extends WebChildElementBase> webElementClass);
    WebPageFactoryPreferences setWebElementInteractionConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebElementInteractionConfiguration> webElementInteractionConfigurations);

    // GetWebProperties
    @NotNull WebElementPropertyConfiguration getWebElementPropertyConfiguration(@NotNull Class<? extends WebChildElementBase> webElementClass);
    WebPageFactoryPreferences setWebElementPropertyConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> webElementPropertyConfigurations);

    // GetLocators
    @NotNull WebLocatorConfiguration getWebLocatorConfiguration(@NotNull Class<? extends WebChildElementBase> webElementClass);
    WebPageFactoryPreferences setWebLocatorConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebLocatorConfiguration> webElementLocatorConfigurations);

}
