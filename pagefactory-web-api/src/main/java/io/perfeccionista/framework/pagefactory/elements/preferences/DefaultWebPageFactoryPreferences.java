package io.perfeccionista.framework.pagefactory.elements.preferences;

import io.perfeccionista.framework.pagefactory.elements.MappedWebBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebPageImpl;
import io.perfeccionista.framework.pagefactory.elements.WebTableRowImpl;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.perfeccionista.framework.utils.ReflectionUtils.Order.DESC;
import static io.perfeccionista.framework.utils.ReflectionUtils.getInheritedInterfaces;

public class DefaultWebPageFactoryPreferences implements WebPageFactoryPreferences {

    protected Class<? extends WebPageImpl> webPageImplementationClass = WebPageImpl.class;
    protected Class<? extends WebBlockImpl> webBlockImplementationClass = WebBlockImpl.class;
    protected Class<? extends WebBlockImpl> mappedWebBlockImplementationClass = MappedWebBlockImpl.class;
    protected Class<? extends WebTableRowImpl> webTableRowImplementationClass = WebTableRowImpl.class;

    protected Map<Class<? extends WebChildElement>, Class<? extends WebChildElement>> webElementImplementations = new HashMap<>();
    protected Map<Class<? extends WebChildElement>, Class<? extends WebBlock>> webMappedBlocks = new HashMap<>();

    protected Map<Class<? extends WebChildElementBase>, WebElementActionConfiguration> webElementActionConfigurations = new HashMap<>();
    protected Map<Class<? extends WebChildElementBase>, WebElementInteractionConfiguration> webElementInteractionConfigurations = new HashMap<>();
    protected Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> webElementPropertyConfigurations = new HashMap<>();
    protected Map<Class<? extends WebChildElementBase>, WebLocatorConfiguration> webLocatorConfigurations = new HashMap<>();

    @Override
    public @NotNull Class<? extends WebPageImpl> getWebPageImplementation() {
        return webPageImplementationClass;
    }

    @Override
    public WebPageFactoryPreferences setWebPageImplementation(@NotNull Class<? extends WebPageImpl> webPageImplementationClass) {
        this.webPageImplementationClass = webPageImplementationClass;
        return this;
    }

    @Override
    public @NotNull Class<? extends WebBlockImpl> getWebBlockImplementation() {
        return webBlockImplementationClass;
    }

    @Override
    public WebPageFactoryPreferences setWebBlockImplementation(@NotNull Class<? extends WebBlockImpl> webBlockImplementation) {
        this.webBlockImplementationClass = webBlockImplementation;
        return this;
    }

    @Override
    public @NotNull Class<? extends WebBlockImpl> getMappedWebBlockImplementation() {
        return mappedWebBlockImplementationClass;
    }

    @Override
    public WebPageFactoryPreferences setMappedWebBlockImplementation(@NotNull Class<? extends WebBlockImpl> mappedWebBlockImplementation) {
        this.mappedWebBlockImplementationClass = mappedWebBlockImplementation;
        return this;
    }

    @Override
    public @NotNull Class<? extends WebTableRowImpl> getWebTableRowImplementation() {
        return webTableRowImplementationClass;
    }

    @Override
    public WebPageFactoryPreferences setWebTableRowImplementation(@NotNull Class<? extends WebTableRowImpl> webTableRowImplementation) {
        this.webTableRowImplementationClass = webTableRowImplementation;
        return this;
    }

    @Override
    public @Nullable Class<? extends WebChildElement> getWebElementImplementation(@NotNull Class<? extends WebChildElement> webElementType) {
        return webElementImplementations.get(webElementType);
    }

    @Override
    public WebPageFactoryPreferences setWebElementImplementations(@NotNull Map<Class<? extends WebChildElement>, Class<? extends WebChildElement>> webElementImplementations) {
        this.webElementImplementations = webElementImplementations;
        return this;
    }

    @Override
    public @Nullable Class<? extends WebBlock> getWebMappedBlock(@NotNull Class<? extends WebChildElement> webElementType) {
        Class<? extends WebBlock> webMappedBlock = webMappedBlocks.get(webElementType);
        if (Objects.nonNull(webMappedBlock)) {
            return webMappedBlock;
        }
        for (Class<? extends WebChildElement> inheritedInterface : getInheritedInterfaces(WebChildElement.class, webElementType, DESC)) {
            webMappedBlock = webMappedBlocks.get(inheritedInterface);
            if (Objects.nonNull(webMappedBlock)) {
                break;
            }
        }
        return webMappedBlock;
    }

    @Override
    public WebPageFactoryPreferences setWebMappedBlocks(@NotNull Map<Class<? extends WebChildElement>, Class<? extends WebBlock>> webMappedBlocks) {
        this.webMappedBlocks = webMappedBlocks;
        return this;
    }

    @Override
    public @NotNull WebElementActionConfiguration getWebElementActionConfiguration(@NotNull Class<? extends WebChildElementBase> webElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        WebElementActionConfiguration cachedWebElementActionConfiguration = webElementActionConfigurations.get(webElementImplementation);
        if (null != cachedWebElementActionConfiguration) {
            return cachedWebElementActionConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        WebElementActionConfiguration webElementActionConfiguration = WebElementActionConfiguration.builder();
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = getInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                webElementActionConfiguration.setFromIfNotPresent(getWebElementActionConfiguration(inheritedInterface)));

        webElementActionConfigurations.put(webElementImplementation, webElementActionConfiguration);
        return webElementActionConfiguration;
    }

    @Override
    public WebPageFactoryPreferences setWebElementActionConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebElementActionConfiguration> webElementActionConfigurations) {
        this.webElementActionConfigurations = webElementActionConfigurations;
        return this;
    }

    @Override
    public @NotNull WebElementInteractionConfiguration getWebElementInteractionConfiguration(@NotNull Class<? extends WebChildElementBase> webElementImplementation) {
        // Проверяем, задана ли конфигурация для элемента
        WebElementInteractionConfiguration cachedWebElementInteractionConfiguration = webElementInteractionConfigurations.get(webElementImplementation);
        if (null != cachedWebElementInteractionConfiguration) {
            return cachedWebElementInteractionConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        WebElementInteractionConfiguration webElementInteractionConfiguration = WebElementInteractionConfiguration.builder();
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = getInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                webElementInteractionConfiguration.setFromIfNotPresent(getWebElementInteractionConfiguration(inheritedInterface)));

        webElementInteractionConfigurations.put(webElementImplementation, webElementInteractionConfiguration);
        return webElementInteractionConfiguration;
    }

    @Override
    public WebPageFactoryPreferences setWebElementInteractionConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebElementInteractionConfiguration> webElementInteractionConfigurations) {
        this.webElementInteractionConfigurations = webElementInteractionConfigurations;
        return this;
    }

    @Override
    public @NotNull WebElementPropertyConfiguration getWebElementPropertyConfiguration(@NotNull Class<? extends WebChildElementBase> webElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        WebElementPropertyConfiguration cachedWebElementPropertyConfiguration = webElementPropertyConfigurations.get(webElementImplementation);
        if (null != cachedWebElementPropertyConfiguration) {
            return cachedWebElementPropertyConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        WebElementPropertyConfiguration webElementPropertyConfiguration = WebElementPropertyConfiguration.builder();
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = getInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                webElementPropertyConfiguration.setFromIfNotPresent(getWebElementPropertyConfiguration(inheritedInterface)));

        webElementPropertyConfigurations.put(webElementImplementation, webElementPropertyConfiguration);
        return webElementPropertyConfiguration;
    }

    @Override
    public WebPageFactoryPreferences setWebElementPropertyConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> webElementPropertyConfigurations) {
        this.webElementPropertyConfigurations = webElementPropertyConfigurations;
        return this;
    }

    @Override
    public @NotNull WebLocatorConfiguration getWebLocatorConfiguration(@NotNull Class<? extends WebChildElementBase> webElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        WebLocatorConfiguration cachedWebLocatorConfiguration = webLocatorConfigurations.get(webElementImplementation);
        if (null != cachedWebLocatorConfiguration) {
            return cachedWebLocatorConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        WebLocatorConfiguration webLocatorConfiguration = WebLocatorConfiguration.builder();
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = getInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                webLocatorConfiguration.setFromIfNotPresent(getWebLocatorConfiguration(inheritedInterface)));

        webLocatorConfigurations.put(webElementImplementation, webLocatorConfiguration);
        return webLocatorConfiguration;
    }

    @Override
    public WebPageFactoryPreferences setWebLocatorConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebLocatorConfiguration> webLocatorConfigurations) {
        this.webLocatorConfigurations = webLocatorConfigurations;
        return this;
    }

}
