package io.perfeccionista.framework.pagefactory.elements.preferences;

import io.perfeccionista.framework.pagefactory.elements.MappedWebBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
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

import static io.perfeccionista.framework.measurements.Order.DESC;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findInheritedInterfaces;

public class DefaultWebPageFactoryPreferences implements WebPageFactoryPreferences {

    protected Class<? extends WebPageImpl> webPageImplementationClass = WebPageImpl.class;
    protected Class<? extends WebBlockImpl> webBlockImplementationClass = WebBlockImpl.class;
    protected Class<? extends WebBlockImpl> mappedWebBlockImplementationClass = MappedWebBlockImpl.class;
    protected Class<? extends WebTableRowImpl> webTableRowImplementationClass = WebTableRowImpl.class;

    protected Map<Class<? extends WebChildElement>, Class<? extends WebChildElement>> webElementImplementations = new HashMap<>();
    protected Map<Class<? extends WebChildElement>, Class<? extends WebBlock>> webMappedBlocks = new HashMap<>();

    protected Map<Class<? extends WebPage>, WebEndpointHandlerConfiguration> webPageActionConfigurations = new HashMap<>();
    protected Map<Class<? extends WebChildElementBase>, WebEndpointHandlerConfiguration> webElementActionConfigurations = new HashMap<>();
    protected Map<Class<? extends WebChildElementBase>, WebElementPropertyConfiguration> webElementPropertyConfigurations = new HashMap<>();
    protected Map<Class<? extends WebChildElementBase>, WebElementStateConfiguration> webElementStateConfigurations = new HashMap<>();
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
        for (Class<? extends WebChildElement> inheritedInterface : findInheritedInterfaces(WebChildElement.class, webElementType, DESC)) {
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
    public @NotNull WebEndpointHandlerConfiguration getWebPageActionConfiguration(@NotNull Class<? extends WebPage> webPageImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        WebEndpointHandlerConfiguration cachedMobilePageActionConfiguration = webPageActionConfigurations.get(webPageImplementation);
        if (null != cachedMobilePageActionConfiguration) {
            return cachedMobilePageActionConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        WebEndpointHandlerConfiguration webPageActionConfiguration = WebEndpointHandlerConfiguration.builder();
        Deque<Class<? extends WebPage>> elementInterfaces = findInheritedInterfaces(WebPage.class, webPageImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                webPageActionConfiguration.setFromIfNotPresent(getWebPageActionConfiguration(inheritedInterface)));

        webPageActionConfigurations.put(webPageImplementation, webPageActionConfiguration);
        return webPageActionConfiguration;
    }

    @Override
    public WebPageFactoryPreferences setWebPageActionConfigurations(@NotNull Map<Class<? extends WebPage>, WebEndpointHandlerConfiguration> webPageActionConfigurations) {
        this.webPageActionConfigurations = webPageActionConfigurations;
        return this;
    }

    @Override
    public @NotNull WebEndpointHandlerConfiguration getWebElementActionConfiguration(@NotNull Class<? extends WebChildElementBase> webElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        WebEndpointHandlerConfiguration cachedWebElementActionConfiguration = webElementActionConfigurations.get(webElementImplementation);
        if (null != cachedWebElementActionConfiguration) {
            return cachedWebElementActionConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        WebEndpointHandlerConfiguration webElementActionConfiguration = WebEndpointHandlerConfiguration.builder();
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = findInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                webElementActionConfiguration.setFromIfNotPresent(getWebElementActionConfiguration(inheritedInterface)));

        webElementActionConfigurations.put(webElementImplementation, webElementActionConfiguration);
        return webElementActionConfiguration;
    }

    @Override
    public WebPageFactoryPreferences setWebElementActionConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebEndpointHandlerConfiguration> webElementActionConfigurations) {
        this.webElementActionConfigurations = webElementActionConfigurations;
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
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = findInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
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
    public @NotNull WebElementStateConfiguration getWebElementStateConfiguration(@NotNull Class<? extends WebChildElementBase> webElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        WebElementStateConfiguration cachedWebElementStateConfiguration = webElementStateConfigurations.get(webElementImplementation);
        if (null != cachedWebElementStateConfiguration) {
            return cachedWebElementStateConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        WebElementStateConfiguration webElementStateConfiguration = WebElementStateConfiguration.builder();
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = findInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                webElementStateConfiguration.setFromIfNotPresent(getWebElementStateConfiguration(inheritedInterface)));

        webElementStateConfigurations.put(webElementImplementation, webElementStateConfiguration);
        return webElementStateConfiguration;
    }

    @Override
    public WebPageFactoryPreferences setWebElementStateConfigurations(@NotNull Map<Class<? extends WebChildElementBase>, WebElementStateConfiguration> webElementStateConfigurations) {
        this.webElementStateConfigurations = webElementStateConfigurations;
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
        Deque<Class<? extends WebChildElementBase>> elementInterfaces = findInheritedInterfaces(WebChildElementBase.class, webElementImplementation, DESC);
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
