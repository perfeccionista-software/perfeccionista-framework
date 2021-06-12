package io.perfeccionista.framework.pagefactory.elements.preferences;

import io.perfeccionista.framework.pagefactory.elements.MappedMobileBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileBlockImpl;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.MobilePageImpl;
import io.perfeccionista.framework.pagefactory.elements.MobileTableRowImpl;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.perfeccionista.framework.measurements.Order.DESC;
import static io.perfeccionista.framework.utils.ReflectionUtilsForClasses.findInheritedInterfaces;

public class DefaultMobilePageFactoryPreferences implements MobilePageFactoryPreferences {

    protected Class<? extends MobilePageImpl> mobilePageImplementationClass = MobilePageImpl.class;
    protected Class<? extends MobileBlockImpl> mobileBlockImplementationClass = MobileBlockImpl.class;
    protected Class<? extends MobileBlockImpl> mappedMobileBlockImplementationClass = MappedMobileBlockImpl.class;
    protected Class<? extends MobileTableRowImpl> mobileTableRowImplementationClass = MobileTableRowImpl.class;

    protected Map<Class<? extends MobileChildElement>, Class<? extends MobileChildElement>> mobileElementImplementations = new HashMap<>();
    protected Map<Class<? extends MobileChildElement>, Class<? extends MobileBlock>> mobileMappedBlocks = new HashMap<>();

    protected Map<Class<? extends MobilePage>, MobileEndpointHandlerConfiguration> mobilePageActionConfigurations = new HashMap<>();
    protected Map<Class<? extends MobileChildElementBase>, MobileEndpointHandlerConfiguration> mobileElementActionConfigurations = new HashMap<>();
    protected Map<Class<? extends MobileChildElementBase>, MobileElementPropertyConfiguration> mobileElementPropertyConfigurations = new HashMap<>();
    protected Map<Class<? extends MobileChildElementBase>, MobileElementStateConfiguration> mobileElementStateConfigurations = new HashMap<>();
    protected Map<Class<? extends MobileChildElementBase>, MobileLocatorConfiguration> mobileLocatorConfigurations = new HashMap<>();

    @Override
    public @NotNull Class<? extends MobilePageImpl> getMobilePageImplementation() {
        return mobilePageImplementationClass;
    }

    @Override
    public MobilePageFactoryPreferences setMobilePageImplementation(@NotNull Class<? extends MobilePageImpl> mobilePageImplementationClass) {
        this.mobilePageImplementationClass = mobilePageImplementationClass;
        return this;
    }

    @Override
    public @NotNull Class<? extends MobileBlockImpl> getMobileBlockImplementation() {
        return mobileBlockImplementationClass;
    }

    @Override
    public MobilePageFactoryPreferences setMobileBlockImplementation(@NotNull Class<? extends MobileBlockImpl> mobileBlockImplementation) {
        this.mobileBlockImplementationClass = mobileBlockImplementation;
        return this;
    }

    @Override
    public @NotNull Class<? extends MobileBlockImpl> getMappedMobileBlockImplementation() {
        return mappedMobileBlockImplementationClass;
    }

    @Override
    public MobilePageFactoryPreferences setMappedMobileBlockImplementation(@NotNull Class<? extends MobileBlockImpl> mappedMobileBlockImplementation) {
        this.mappedMobileBlockImplementationClass = mappedMobileBlockImplementation;
        return this;
    }

    @Override
    public @NotNull Class<? extends MobileTableRowImpl> getMobileTableRowImplementation() {
        return mobileTableRowImplementationClass;
    }

    @Override
    public MobilePageFactoryPreferences setMobileTableRowImplementation(@NotNull Class<? extends MobileTableRowImpl> mobileTableRowImplementation) {
        this.mobileTableRowImplementationClass = mobileTableRowImplementation;
        return this;
    }

    @Override
    public @Nullable Class<? extends MobileChildElement> getMobileElementImplementation(@NotNull Class<? extends MobileChildElement> mobileElementType) {
        return mobileElementImplementations.get(mobileElementType);
    }

    @Override
    public MobilePageFactoryPreferences setMobileElementImplementations(@NotNull Map<Class<? extends MobileChildElement>, Class<? extends MobileChildElement>> mobileElementImplementations) {
        this.mobileElementImplementations = mobileElementImplementations;
        return this;
    }

    @Override
    public @Nullable Class<? extends MobileBlock> getMobileMappedBlock(@NotNull Class<? extends MobileChildElement> mobileElementType) {
        Class<? extends MobileBlock> mobileMappedBlock = mobileMappedBlocks.get(mobileElementType);
        if (Objects.nonNull(mobileMappedBlock)) {
            return mobileMappedBlock;
        }
        for (Class<? extends MobileChildElement> inheritedInterface : findInheritedInterfaces(MobileChildElement.class, mobileElementType, DESC)) {
            mobileMappedBlock = mobileMappedBlocks.get(inheritedInterface);
            if (Objects.nonNull(mobileMappedBlock)) {
                break;
            }
        }
        return mobileMappedBlock;
    }

    @Override
    public MobilePageFactoryPreferences setMobileMappedBlocks(@NotNull Map<Class<? extends MobileChildElement>, Class<? extends MobileBlock>> mobileMappedBlocks) {
        this.mobileMappedBlocks = mobileMappedBlocks;
        return this;
    }

    @Override
    public @NotNull MobileEndpointHandlerConfiguration getMobilePageActionConfiguration(@NotNull Class<? extends MobilePage> mobilePageImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        MobileEndpointHandlerConfiguration cachedMobilePageActionConfiguration = mobilePageActionConfigurations.get(mobilePageImplementation);
        if (null != cachedMobilePageActionConfiguration) {
            return cachedMobilePageActionConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        MobileEndpointHandlerConfiguration mobilePageActionConfiguration = MobileEndpointHandlerConfiguration.builder();
        Deque<Class<? extends MobilePage>> elementInterfaces = findInheritedInterfaces(MobilePage.class, mobilePageImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                mobilePageActionConfiguration.setFromIfNotPresent(getMobilePageActionConfiguration(inheritedInterface)));

        mobilePageActionConfigurations.put(mobilePageImplementation, mobilePageActionConfiguration);
        return mobilePageActionConfiguration;
    }

    @Override
    public MobilePageFactoryPreferences setMobilePageActionConfigurations(@NotNull Map<Class<? extends MobilePage>, MobileEndpointHandlerConfiguration> mobilePageActionConfigurations) {
        this.mobilePageActionConfigurations = mobilePageActionConfigurations;
        return this;
    }

    @Override
    public @NotNull MobileEndpointHandlerConfiguration getMobileElementActionConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        MobileEndpointHandlerConfiguration cachedMobileElementActionConfiguration = mobileElementActionConfigurations.get(mobileElementImplementation);
        if (null != cachedMobileElementActionConfiguration) {
            return cachedMobileElementActionConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        MobileEndpointHandlerConfiguration mobileElementActionConfiguration = MobileEndpointHandlerConfiguration.builder();
        Deque<Class<? extends MobileChildElementBase>> elementInterfaces = findInheritedInterfaces(MobileChildElementBase.class, mobileElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                mobileElementActionConfiguration.setFromIfNotPresent(getMobileElementActionConfiguration(inheritedInterface)));

        mobileElementActionConfigurations.put(mobileElementImplementation, mobileElementActionConfiguration);
        return mobileElementActionConfiguration;
    }

    @Override
    public MobilePageFactoryPreferences setMobileElementActionConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileEndpointHandlerConfiguration> mobileElementActionConfigurations) {
        this.mobileElementActionConfigurations = mobileElementActionConfigurations;
        return this;
    }

    @Override
    public @NotNull MobileElementPropertyConfiguration getMobileElementPropertyConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        MobileElementPropertyConfiguration cachedMobileElementPropertyConfiguration = mobileElementPropertyConfigurations.get(mobileElementImplementation);
        if (null != cachedMobileElementPropertyConfiguration) {
            return cachedMobileElementPropertyConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        MobileElementPropertyConfiguration mobileElementPropertyConfiguration = MobileElementPropertyConfiguration.builder();
        Deque<Class<? extends MobileChildElementBase>> elementInterfaces = findInheritedInterfaces(MobileChildElementBase.class, mobileElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                mobileElementPropertyConfiguration.setFromIfNotPresent(getMobileElementPropertyConfiguration(inheritedInterface)));

        mobileElementPropertyConfigurations.put(mobileElementImplementation, mobileElementPropertyConfiguration);
        return mobileElementPropertyConfiguration;
    }

    @Override
    public MobilePageFactoryPreferences setMobileElementPropertyConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileElementPropertyConfiguration> mobileElementPropertyConfigurations) {
        this.mobileElementPropertyConfigurations = mobileElementPropertyConfigurations;
        return this;
    }

    @Override
    public @NotNull MobileElementStateConfiguration getMobileElementStateConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        MobileElementStateConfiguration cachedMobileElementStateConfiguration = mobileElementStateConfigurations.get(mobileElementImplementation);
        if (null != cachedMobileElementStateConfiguration) {
            return cachedMobileElementStateConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        MobileElementStateConfiguration mobileElementStateConfiguration = MobileElementStateConfiguration.builder();
        Deque<Class<? extends MobileChildElementBase>> elementInterfaces = findInheritedInterfaces(MobileChildElementBase.class, mobileElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                mobileElementStateConfiguration.setFromIfNotPresent(getMobileElementStateConfiguration(inheritedInterface)));

        mobileElementStateConfigurations.put(mobileElementImplementation, mobileElementStateConfiguration);
        return mobileElementStateConfiguration;
    }

    @Override
    public MobilePageFactoryPreferences setMobileElementStateConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileElementStateConfiguration> mobileElementStateConfigurations) {
        this.mobileElementStateConfigurations = mobileElementStateConfigurations;
        return this;
    }

    @Override
    public @NotNull MobileLocatorConfiguration getMobileLocatorConfiguration(@NotNull Class<? extends MobileChildElementBase> mobileElementImplementation) {

        // Проверяем, задана ли конфигурация для элемента
        MobileLocatorConfiguration cachedMobileLocatorConfiguration = mobileLocatorConfigurations.get(mobileElementImplementation);
        if (null != cachedMobileLocatorConfiguration) {
            return cachedMobileLocatorConfiguration;
        }

        // Собираем конфигурацию для элемента и добавляем ее в хранилище
        MobileLocatorConfiguration mobileLocatorConfiguration = MobileLocatorConfiguration.builder();
        Deque<Class<? extends MobileChildElementBase>> elementInterfaces = findInheritedInterfaces(MobileChildElementBase.class, mobileElementImplementation, DESC);
        elementInterfaces.forEach(inheritedInterface ->
                mobileLocatorConfiguration.setFromIfNotPresent(getMobileLocatorConfiguration(inheritedInterface)));

        mobileLocatorConfigurations.put(mobileElementImplementation, mobileLocatorConfiguration);
        return mobileLocatorConfiguration;
    }

    @Override
    public MobilePageFactoryPreferences setMobileLocatorConfigurations(@NotNull Map<Class<? extends MobileChildElementBase>, MobileLocatorConfiguration> mobileLocatorConfigurations) {
        this.mobileLocatorConfigurations = mobileLocatorConfigurations;
        return this;
    }
}
