package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.exceptions.WebElementInitializationException;
import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.name.WebPageIdentifier;
import io.perfeccionista.framework.pagefactory.WebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;

import java.lang.reflect.Method;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_LIST_MAPPED_CLASS_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseWebMappedBlockAnnotationHandler.getMappedBlock;
import static io.perfeccionista.framework.pagefactory.factory.handlers.UseWebMappedTableColumnAnnotationHandler.createMappedTableColumnHolders;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementActionAnnotationHandler.createWebElementActionRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementIdentifierHandler.extractNames;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementInteractionAnnotationHandler.createWebElementInteractionRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementPropertyAnnotationHandler.createWebElementPropertyRegistryFor;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createWebLocatorRegistryFor;
import static io.perfeccionista.framework.utils.ReflectionUtils.*;

// TODO: Initializer тут не вызываем - он вызывается в методах pageFactory.
// TODO: Добавить возможность переопределять в конфигурации методы для конкретных типов элементов
// TODO: Добавить возможность переопределять конфигурации элементов над родительским классом (страницей или блоком)
public class WebElementDecorator {

    protected final WebElementsConfiguration configuration;

    public WebElementDecorator(WebElementsConfiguration configuration) {
        this.configuration = configuration;
    }

    public WebPage decorateWebPageInstance(WebPage webPageInstance,
                                           WebElementRegistry webElementRegistry) {
        writeField("parent", webPageInstance, null);
        writeField("pageIdentifier", webPageInstance, WebPageIdentifier.of(webPageInstance.getClass()));
        writeField("locatorRegistry", webPageInstance, createWebLocatorRegistryFor(webPageInstance.getClass()));
        writeField("elementRegistry", webPageInstance, webElementRegistry);
        return webPageInstance;
    }

    public WebBlock decorateWebBlockInstance(WebParentElement parent,
                                             WebBlock webBlockInstance,
                                             Method webBlockMethod,
                                             WebElementRegistry webElementRegistry) {
        decorateWebChildElementInstance(parent, webBlockInstance, webBlockMethod);
        writeField("elementRegistry", webBlockInstance, webElementRegistry);
        return webBlockInstance;
    }

    public WebMappedBlock decorateWebMappedBlockInstance(WebMappedBlock webMappedBlockInstance,
                                                         WebElementRegistry webElementRegistry,
                                                         WebParentInfo<?> parentInfo) {
        writeField("parentInfo", webMappedBlockInstance, parentInfo);
        writeField("locatorRegistry", webMappedBlockInstance, WebLocatorRegistry.empty());
        writeField("elementRegistry", webMappedBlockInstance, webElementRegistry);
        return webMappedBlockInstance;
    }

    public WebChildElement decorateWebChildElementInstance(WebParentElement parent,
                                                           WebChildElement webChildElement,
                                                           Method webChildElementMethod) {
        writeField("parent", webChildElement, parent);
        writeField("actionRegistry", webChildElement, createWebElementActionRegistryFor(webChildElement, webChildElementMethod));
        writeField("locatorRegistry", webChildElement, createWebLocatorRegistryFor(webChildElement, webChildElementMethod));
        writeField("propertyRegistry", webChildElement, createWebElementPropertyRegistryFor(webChildElement, webChildElementMethod));
        WebElementIdentifier identifier = WebElementIdentifier.of(extractNames(webChildElement, webChildElementMethod), webChildElementMethod);
        writeField("elementIdentifier", webChildElement, identifier);
        writeField("interactionRegistry", webChildElement, createWebElementInteractionRegistryFor(webChildElement, webChildElementMethod));
        if (webChildElement instanceof WebList) {
            decorateWebListInstance((WebList) webChildElement, webChildElementMethod);
        }
        if (webChildElement instanceof WebTextList) {
            decorateWebTextListInstance((WebTextList) webChildElement, webChildElementMethod);
        }
        if (webChildElement instanceof WebTable) {
            decorateWebTableInstance((WebTable) webChildElement, webChildElementMethod);
        }
        if (webChildElement instanceof WebTextTable) {
            decorateWebTextTableInstance((WebTextTable) webChildElement, webChildElementMethod);
        }
        // TODO: ParentInfo должен будет инжектиться при инициализации элемента внутри родительского элемента или экстрактора
        return webChildElement;
    }

    public void decorateWebListInstance(WebList webListInstance, Method webChildElementMethod) {
        Optional<Class<? extends WebMappedBlock>> optionalMappedBlock = getMappedBlock(webListInstance, webChildElementMethod);
        if (optionalMappedBlock.isEmpty()) {
            throw new WebElementInitializationException(WEB_LIST_MAPPED_CLASS_NOT_DECLARED.getMessage(webListInstance.getElementIdentifier().getLastUsedName()));
        }
        writeField("mappedBlockClass", webListInstance, optionalMappedBlock.get());
    }

    public void decorateWebTextListInstance(WebTextList webTextListInstance, Method webChildElementMethod) {
        Optional<Class<? extends WebMappedBlock>> optionalMappedBlock = getMappedBlock(webTextListInstance, webChildElementMethod);
        if (optionalMappedBlock.isPresent()) {
            writeField("mappedBlockClass", webTextListInstance, getMappedBlock(webTextListInstance, webChildElementMethod));
        }
    }

    public void decorateWebTableInstance(WebTable webTableInstance, Method webChildElementMethod) {
        writeField("tableColumnHolders", webTableInstance, createMappedTableColumnHolders(webTableInstance, webChildElementMethod));
    }

    public void decorateWebTextTableInstance(WebTextTable webTextTableInstance, Method webChildElementMethod) {
        writeField("tableColumnHolders", webTextTableInstance, createMappedTableColumnHolders(webTextTableInstance, webChildElementMethod));
    }

}
