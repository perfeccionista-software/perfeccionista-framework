package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.name.MappedMobileBlockIdentifier;
import io.perfeccionista.framework.name.MobileChildElementIdentifier;
import io.perfeccionista.framework.name.MobileElementIdentifier;
import io.perfeccionista.framework.name.MobilePageIdentifier;
import io.perfeccionista.framework.pagefactory.dispatcher.DeviceType;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceService;
import io.perfeccionista.framework.pagefactory.elements.DefaultMobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.elements.actions.base.EndpointHandlerRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileListFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileTableFrame;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileElementActionAnnotationHandler.createMobileElementActionRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileElementNameHandler.extractNames;
import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileElementPropertyAnnotationHandler.createMobileElementPropertyRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileElementStateAnnotationHandler.createMobileElementStateRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileLocatorAnnotationHandler.createMobileLocatorRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedMobileBlockAnnotationHandler.createMobileListFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedMobileTableColumnAnnotationHandler.createMobileTableFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedMobileTextBlockAnnotationHandler.createMobileTextListFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedMobileTextTableColumnAnnotationHandler.createMobileTextTableFrame;
import static io.perfeccionista.framework.utils.ReflectionUtils.writeField;

public class MobileElementDecorator {

    public static final String ELEMENT_IDENTIFIER_FIELD = "elementIdentifier";
    public static final String PAGE_IDENTIFIER_FIELD = "pageIdentifier";

    public static final String PARENT_HOLDER_FIELD = "parentHolder";
    public static final String ACTION_REGISTRY_FIELD = "actionRegistry";
    public static final String ELEMENT_REGISTRY_FIELD = "elementRegistry";
    public static final String LOCATOR_REGISTRY_FIELD = "locatorRegistry";
    public static final String PROPERTY_REGISTRY_FIELD = "propertyRegistry";
    public static final String STATE_REGISTRY_FIELD = "stateRegistry";

    public static final String WEB_LIST_FRAME_FIELD = "mobileListFrame";
    public static final String WEB_TEXT_LIST_FRAME_FIELD = "mobileTextListFrame";
    public static final String WEB_TABLE_FRAME = "mobileTableFrame";
    public static final String WEB_TEXT_TABLE_FRAME = "mobileTextTableFrame";

    protected final MobilePageFactoryPreferences configuration;
    protected final MobilePageFactory mobilePageFactory;

    public MobileElementDecorator(@NotNull MobilePageFactoryPreferences configuration,
                                  @NotNull MobilePageFactory mobilePageFactory) {
        this.configuration = configuration;
        this.mobilePageFactory = mobilePageFactory;
    }

    public @NotNull <T extends MobileBlock> T decorateMappedMobileBlockInstance(@NotNull T mobileMappedBlockInstance,
                                                                                @NotNull Class<? extends T> mobileMappedBlockClass,
                                                                                @NotNull MobileElementRegistry mobileElementRegistry,
                                                                                @NotNull MobileParentHolder mobileParentHolder) {
        writeField(PARENT_HOLDER_FIELD, mobileMappedBlockInstance, mobileParentHolder);
        writeField(ELEMENT_REGISTRY_FIELD, mobileMappedBlockInstance, mobileElementRegistry);
        EndpointHandlerRegistry actionRegistry = createMobileElementActionRegistryFor(mobileMappedBlockInstance, configuration);
        writeField(ACTION_REGISTRY_FIELD, mobileMappedBlockInstance, actionRegistry);
        MobileLocatorRegistry locatorRegistry = createMobileLocatorRegistryFor(getDeviceType(), mobileMappedBlockInstance, configuration);
        writeField(LOCATOR_REGISTRY_FIELD, mobileMappedBlockInstance, locatorRegistry);
        MobileElementPropertyRegistry propertyRegistry = createMobileElementPropertyRegistryFor(getDeviceType(), mobileMappedBlockInstance, configuration);
        writeField(PROPERTY_REGISTRY_FIELD, mobileMappedBlockInstance, propertyRegistry);
        MobileElementStateRegistry stateRegistry = createMobileElementStateRegistryFor(getDeviceType(), mobileMappedBlockInstance, configuration);
        writeField(STATE_REGISTRY_FIELD, mobileMappedBlockInstance, stateRegistry);
        MobileElementIdentifier elementIdentifier = MappedMobileBlockIdentifier.of(mobileMappedBlockClass);
        writeField(ELEMENT_IDENTIFIER_FIELD, mobileMappedBlockInstance, elementIdentifier);
        return mobileMappedBlockInstance;
    }

    public @NotNull MobilePage decorateMobilePageInstance(@NotNull MobilePage mobilePageInstance,
                                                          @NotNull MobileElementRegistry mobileElementRegistry) {
        writeField(ELEMENT_REGISTRY_FIELD, mobilePageInstance, mobileElementRegistry);
        EndpointHandlerRegistry actionRegistry = createMobileElementActionRegistryFor(mobilePageInstance, configuration);
        writeField(ACTION_REGISTRY_FIELD, mobilePageInstance, actionRegistry);
        MobilePageIdentifier pageIdentifier = MobilePageIdentifier.of(mobilePageInstance.getClass());
        writeField(PAGE_IDENTIFIER_FIELD, mobilePageInstance, pageIdentifier);
        MobileLocatorRegistry locatorRegistry = createMobileLocatorRegistryFor(getDeviceType(), mobilePageInstance.getClass());
        writeField(LOCATOR_REGISTRY_FIELD, mobilePageInstance, locatorRegistry);
        return mobilePageInstance;
    }

    public @NotNull MobileBlock decorateMobileBlockInstance(@NotNull MobileBlock mobileBlockInstance,
                                                      @NotNull MobileElementRegistry mobileElementRegistry,
                                                      @NotNull MobileParentHolder mobileParentHolder,
                                                      @NotNull Method mobileBlockMethod) {
        decorateMobileChildElementInstance(mobileBlockInstance, mobileParentHolder, mobileBlockMethod);
        writeField(ELEMENT_REGISTRY_FIELD, mobileBlockInstance, mobileElementRegistry);
        return mobileBlockInstance;
    }

    public @NotNull MobileChildElement decorateMobileChildElementInstance(@NotNull MobileChildElement mobileChildElementInstance,
                                                                          @NotNull MobileParentHolder mobileParentHolder,
                                                                          @NotNull Method mobileChildElementMethod) {
        writeField(PARENT_HOLDER_FIELD, mobileChildElementInstance, mobileParentHolder);
        EndpointHandlerRegistry actionRegistry = createMobileElementActionRegistryFor(mobileChildElementInstance, mobileChildElementMethod, configuration);
        writeField(ACTION_REGISTRY_FIELD, mobileChildElementInstance, actionRegistry);
        MobileLocatorRegistry locatorRegistry = createMobileLocatorRegistryFor(getDeviceType(), mobileChildElementInstance, mobileChildElementMethod, configuration);
        writeField(LOCATOR_REGISTRY_FIELD, mobileChildElementInstance, locatorRegistry);
        MobileElementPropertyRegistry propertyRegistry = createMobileElementPropertyRegistryFor(getDeviceType(), mobileChildElementInstance, mobileChildElementMethod, configuration);
        writeField(PROPERTY_REGISTRY_FIELD, mobileChildElementInstance, propertyRegistry);
        MobileElementStateRegistry stateRegistry = createMobileElementStateRegistryFor(getDeviceType(), mobileChildElementInstance, mobileChildElementMethod, configuration);
        writeField(STATE_REGISTRY_FIELD, mobileChildElementInstance, stateRegistry);
        MobileElementIdentifier elementIdentifier = MobileChildElementIdentifier.of(extractNames(mobileChildElementInstance, mobileChildElementMethod), mobileChildElementMethod);
        writeField(ELEMENT_IDENTIFIER_FIELD, mobileChildElementInstance, elementIdentifier);
        if (mobileChildElementInstance instanceof MobileList) {
            decorateMobileListInstance((MobileList) mobileChildElementInstance, mobileChildElementMethod);
        }
        if (mobileChildElementInstance instanceof MobileTextList) {
            decorateMobileTextListInstance((MobileTextList) mobileChildElementInstance, mobileChildElementMethod);
        }
        if (mobileChildElementInstance instanceof MobileTable) {
            decorateMobileTableInstance((MobileTable) mobileChildElementInstance, mobileChildElementMethod);
        }
        if (mobileChildElementInstance instanceof MobileTextTable) {
            decorateMobileTextTableInstance((MobileTextTable) mobileChildElementInstance, mobileChildElementMethod);
        }
        return mobileChildElementInstance;
    }

    public void decorateMobileListInstance(@NotNull MobileList mobileListInstance, @NotNull Method mobileChildElementMethod) {
        MobileListFrame<MobileBlock> mobileListFrame =
                createMobileListFrame(mobileListInstance, mobileChildElementMethod, mobilePageFactory, configuration);
        writeField(WEB_LIST_FRAME_FIELD, mobileListInstance, mobileListFrame);
    }

    public void decorateMobileTextListInstance(@NotNull MobileTextList mobileTextListInstance, @NotNull Method mobileChildElementMethod) {
        MobileListFrame<DefaultMobileTextBlock> mobileTextListFrame =
                createMobileTextListFrame(mobileTextListInstance, mobileChildElementMethod, mobilePageFactory, configuration);
        writeField(WEB_TEXT_LIST_FRAME_FIELD, mobileTextListInstance, mobileTextListFrame);
    }

    public void decorateMobileTableInstance(@NotNull MobileTable mobileTableInstance, @NotNull Method mobileChildElementMethod) {
        MobileTableFrame<MobileBlock> mobileTableRegistry =
                createMobileTableFrame(getDeviceType(), mobileTableInstance, mobileChildElementMethod, mobilePageFactory);
        writeField(WEB_TABLE_FRAME, mobileTableInstance, mobileTableRegistry);
    }

    public void decorateMobileTextTableInstance(@NotNull MobileTextTable mobileTextTableInstance, @NotNull Method mobileChildElementMethod) {
        MobileTableFrame<DefaultMobileTextBlock> mobileTableRegistry =
                createMobileTextTableFrame(getDeviceType(), mobileTextTableInstance, mobileChildElementMethod, mobilePageFactory);
        writeField(WEB_TEXT_TABLE_FRAME, mobileTextTableInstance, mobileTableRegistry);
    }

    protected @NotNull DeviceType getDeviceType() {
        return Environment.getCurrent().getService(MobileDeviceService.class)
                .getActiveDispatcher()
                .getDeviceType();
    }

}

