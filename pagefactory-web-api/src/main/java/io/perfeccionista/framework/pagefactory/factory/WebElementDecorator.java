package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.name.MappedWebBlockIdentifier;
import io.perfeccionista.framework.name.WebChildElementIdentifier;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebRadioButtonBlock;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionRegistry;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebRadioGroupFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolder;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedWebBlockAnnotationHandler.createWebListFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedWebRadioButtonBlockAnnotationHandler.createWebRadioGroupFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedWebTableColumnAnnotationHandler.createWebTableFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedWebTextBlockAnnotationHandler.createWebTextListFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseMappedWebTextTableColumnAnnotationHandler.createWebTextTableFrame;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementActionAnnotationHandler.createWebElementActionRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementNameHandler.extractNames;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementInteractionAnnotationHandler.createWebElementInteractionRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementPropertyAnnotationHandler.createWebElementPropertyRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createWebLocatorRegistryFor;
import static io.perfeccionista.framework.utils.ReflectionUtils.*;

// TODO: Добавить возможность принудительно переопределять в конфигурации методы для конкретных типов элементов
// TODO: Добавить возможность переопределять конфигурации элементов над родительским классом (страницей или блоком)
public class WebElementDecorator {

    public static final String ELEMENT_IDENTIFIER_FIELD = "elementIdentifier";
    public static final String PAGE_IDENTIFIER_FIELD = "pageIdentifier";

    public static final String PARENT_HOLDER_FIELD = "parentHolder";
    public static final String ACTION_REGISTRY_FIELD = "actionRegistry";
    public static final String ELEMENT_REGISTRY_FIELD = "elementRegistry";
    public static final String LOCATOR_REGISTRY_FIELD = "locatorRegistry";
    public static final String PROPERTY_REGISTRY_FIELD = "propertyRegistry";
    public static final String INTERACTION_REGISTRY_FIELD = "interactionRegistry";

    public static final String WEB_RADIO_GROUP_FRAME_FIELD = "webRadioGroupFrame";
    public static final String WEB_LIST_FRAME_FIELD = "webListFrame";
    public static final String WEB_TEXT_LIST_FRAME_FIELD = "webTextListFrame";
    public static final String WEB_TABLE_FRAME = "webTableFrame";
    public static final String WEB_TEXT_TABLE_FRAME = "webTextTableFrame";

    protected final WebPageFactoryPreferences configuration;
    protected final WebPageFactory webPageFactory;

    public WebElementDecorator(@NotNull WebPageFactoryPreferences configuration, @NotNull WebPageFactory webPageFactory) {
        this.configuration = configuration;
        this.webPageFactory = webPageFactory;
    }

    public @NotNull <T extends WebBlock> T decorateMappedWebBlockInstance(@NotNull T webMappedBlockInstance,
                                                                          @NotNull Class<? extends T> webMappedBlockClass,
                                                                          @NotNull WebElementRegistry webElementRegistry,
                                                                          @NotNull WebParentHolder webParentHolder) {
        writeField(PARENT_HOLDER_FIELD, webMappedBlockInstance, webParentHolder);
        writeField(ELEMENT_REGISTRY_FIELD, webMappedBlockInstance, webElementRegistry);
        WebElementActionRegistry actionRegistry = createWebElementActionRegistryFor(webMappedBlockInstance, configuration);
        writeField(ACTION_REGISTRY_FIELD, webMappedBlockInstance, actionRegistry);
        WebLocatorRegistry locatorRegistry = createWebLocatorRegistryFor(webMappedBlockInstance, configuration);
        writeField(LOCATOR_REGISTRY_FIELD, webMappedBlockInstance, locatorRegistry);
        WebElementPropertyRegistry propertyRegistry = createWebElementPropertyRegistryFor(webMappedBlockInstance, configuration);
        writeField(PROPERTY_REGISTRY_FIELD, webMappedBlockInstance, propertyRegistry);
        WebElementIdentifier elementIdentifier = MappedWebBlockIdentifier.of(webMappedBlockClass);
        writeField(ELEMENT_IDENTIFIER_FIELD, webMappedBlockInstance, elementIdentifier);
        WebElementInteractionRegistry interactionRegistry = createWebElementInteractionRegistryFor(webMappedBlockInstance, configuration);
        writeField(INTERACTION_REGISTRY_FIELD, webMappedBlockInstance, interactionRegistry);
        return webMappedBlockInstance;
    }

    public @NotNull WebPage decorateWebPageInstance(@NotNull WebPage webPageInstance,
                                                    @NotNull WebElementRegistry webElementRegistry) {
        writeField(ELEMENT_REGISTRY_FIELD, webPageInstance, webElementRegistry);
        WebPageIdentifier pageIdentifier = WebPageIdentifier.of(webPageInstance.getClass());
        writeField(PAGE_IDENTIFIER_FIELD, webPageInstance, pageIdentifier);
        WebLocatorRegistry locatorRegistry = createWebLocatorRegistryFor(webPageInstance.getClass());
        writeField(LOCATOR_REGISTRY_FIELD, webPageInstance, locatorRegistry);
        return webPageInstance;
    }

    public @NotNull WebBlock decorateWebBlockInstance(@NotNull WebBlock webBlockInstance,
                                                      @NotNull WebElementRegistry webElementRegistry,
                                                      @NotNull WebParentHolder webParentHolder,
                                                      @NotNull Method webBlockMethod) {
        decorateWebChildElementInstance(webBlockInstance, webParentHolder, webBlockMethod);
        writeField(ELEMENT_REGISTRY_FIELD, webBlockInstance, webElementRegistry);
        return webBlockInstance;
    }

    public @NotNull WebChildElement decorateWebChildElementInstance(@NotNull WebChildElement webChildElementInstance,
                                                                    @NotNull WebParentHolder webParentHolder,
                                                                    @NotNull Method webChildElementMethod) {
        writeField(PARENT_HOLDER_FIELD, webChildElementInstance, webParentHolder);
        WebElementActionRegistry actionRegistry = createWebElementActionRegistryFor(webChildElementInstance, webChildElementMethod, configuration);
        writeField(ACTION_REGISTRY_FIELD, webChildElementInstance, actionRegistry);
        WebLocatorRegistry locatorRegistry = createWebLocatorRegistryFor(webChildElementInstance, webChildElementMethod, configuration);
        writeField(LOCATOR_REGISTRY_FIELD, webChildElementInstance, locatorRegistry);
        WebElementPropertyRegistry propertyRegistry = createWebElementPropertyRegistryFor(webChildElementInstance, webChildElementMethod, configuration);
        writeField(PROPERTY_REGISTRY_FIELD, webChildElementInstance, propertyRegistry);
        WebElementIdentifier elementIdentifier = WebChildElementIdentifier.of(extractNames(webChildElementInstance, webChildElementMethod), webChildElementMethod);
        writeField(ELEMENT_IDENTIFIER_FIELD, webChildElementInstance, elementIdentifier);
        WebElementInteractionRegistry interactionRegistry = createWebElementInteractionRegistryFor(webChildElementInstance, webChildElementMethod, configuration);
        writeField(INTERACTION_REGISTRY_FIELD, webChildElementInstance, interactionRegistry);
        if (webChildElementInstance instanceof WebRadioGroup) {
            decorateWebRadioGroupInstance((WebRadioGroup) webChildElementInstance, webChildElementMethod);
        }
        if (webChildElementInstance instanceof WebList) {
            decorateWebListInstance((WebList) webChildElementInstance, webChildElementMethod);
        }
        if (webChildElementInstance instanceof WebTextList) {
            decorateWebTextListInstance((WebTextList) webChildElementInstance, webChildElementMethod);
        }
        if (webChildElementInstance instanceof WebTable) {
            decorateWebTableInstance((WebTable) webChildElementInstance, webChildElementMethod);
        }
        if (webChildElementInstance instanceof WebTextTable) {
            decorateWebTextTableInstance((WebTextTable) webChildElementInstance, webChildElementMethod);
        }
        return webChildElementInstance;
    }

    public void decorateWebRadioGroupInstance(@NotNull WebRadioGroup webRadioGroupInstance, @NotNull Method webChildElementMethod) {
        WebRadioGroupFrame<DefaultWebRadioButtonBlock> webRadioGroupFrame =
                createWebRadioGroupFrame(webRadioGroupInstance, webChildElementMethod, webPageFactory, configuration);
        writeField(WEB_RADIO_GROUP_FRAME_FIELD, webRadioGroupInstance, webRadioGroupFrame);
    }

    public void decorateWebListInstance(@NotNull WebList webListInstance, @NotNull Method webChildElementMethod) {
        WebListFrame<WebBlock> webListFrame =
                createWebListFrame(webListInstance, webChildElementMethod, webPageFactory, configuration);
        writeField(WEB_LIST_FRAME_FIELD, webListInstance, webListFrame);
    }

    public void decorateWebTextListInstance(@NotNull WebTextList webTextListInstance, @NotNull Method webChildElementMethod) {
        WebListFrame<DefaultWebTextBlock> webTextListFrame =
                createWebTextListFrame(webTextListInstance, webChildElementMethod, webPageFactory, configuration);
        writeField(WEB_TEXT_LIST_FRAME_FIELD, webTextListInstance, webTextListFrame);
    }

    public void decorateWebTableInstance(@NotNull WebTable webTableInstance, @NotNull Method webChildElementMethod) {
        WebTableFrame<WebBlock> webTableRegistry =
                createWebTableFrame(webTableInstance, webChildElementMethod, webPageFactory);
        writeField(WEB_TABLE_FRAME, webTableInstance, webTableRegistry);
    }

    public void decorateWebTextTableInstance(@NotNull WebTextTable webTextTableInstance, @NotNull Method webChildElementMethod) {
        WebTableFrame<DefaultWebTextBlock> webTableRegistry =
                createWebTextTableFrame(webTextTableInstance, webChildElementMethod, webPageFactory);
        writeField(WEB_TEXT_TABLE_FRAME, webTextTableInstance, webTableRegistry);
    }

}
