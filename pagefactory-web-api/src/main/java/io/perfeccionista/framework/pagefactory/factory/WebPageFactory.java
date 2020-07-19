package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.exceptions.TableColumnLocatorNotDeclaredException;
import io.perfeccionista.framework.exceptions.TableColumnNotDeclaredException;
import io.perfeccionista.framework.pagefactory.WebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.TABLE_COLUMN_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.TABLE_COLUMN_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.utils.ReflectionUtils.getInheritedInterfaces;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;
import static java.util.stream.Collectors.toList;
import static org.junit.platform.commons.util.ReflectionUtils.findMethods;

public class WebPageFactory {

    protected final WebElementsConfiguration configuration;
    protected final WebElementInitializer initializer;
    protected final WebElementDecorator decorator;

    public WebPageFactory(WebElementsConfiguration configuration) {
        this.configuration = configuration;
        this.initializer = new WebElementInitializer(configuration);
        this.decorator = new WebElementDecorator(configuration);
    }

    public WebPage createWebPage(Class<? extends WebPage> webPageClass) {
        WebPage webPageInstance = initializer.initWebPageInstance(webPageClass);
        List<Method> webChildElementMethods = getWebChildElementMethods(webPageClass);
        WebElementRegistry webPageElementRegistry = createWebChildElements(webPageInstance, webChildElementMethods);
        return decorator.decorateWebPageInstance(webPageInstance, webPageElementRegistry);
    }

    public Map<Integer, WebMappedBlock> createWebListBlocks(WebList webList, WebFilterResult filter) {
        String hash = filter.getHash();
        Set<Integer> indexes = filter.getIndexes();
        WebLocatorHolder liLocatorHolder = webList.getLocator(LI)
                .orElseThrow(() ->
                        new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI))
                                .addAttachmentEntry(JsonAttachmentEntry.of("Element", webList.toJson())));
        Class<? extends WebMappedBlock> mappedBlockClass = readField("mappedBlockClass", webList);
        Map<Integer, WebMappedBlock> webMappedBlocks = new HashMap<>();
        for (int index : indexes) {
            WebMappedBlock webMappedBlockInstance = initializer.initWebMappedBlock(mappedBlockClass);
            List<Method> childElementMethods = getWebChildElementMethods(mappedBlockClass);
            WebElementRegistry elementRegistry = createWebChildElements(webMappedBlockInstance, childElementMethods);
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            parentLocators.add(liLocatorHolder.clone().setSingle(true).setIndex(index));
            WebParentInfo<WebList> parentInfo = WebParentInfo.of(webList, hash, parentLocators);
            webMappedBlocks.put(index, decorator.decorateWebMappedBlockInstance(webList, webMappedBlockInstance, elementRegistry, parentInfo));
        }
        return webMappedBlocks;
    }

    public Map<Integer, WebMappedBlock> createWebTableCells(WebTable webTable, String columnName, WebFilterResult filterResult) {
        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();
        WebLocatorHolder tableRowLocator = webTable.getLocator(TBODY_ROW)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(TBODY_ROW))
                        .addAttachmentEntry(JsonAttachmentEntry.of("Element", webTable.toJson())));
        Map<String, TableColumnHolder> tableColumnHolders = readField("tableColumnHolders", webTable);
        TableColumnHolder tableColumnHolder = Optional.ofNullable(tableColumnHolders.get(columnName))
                .orElseThrow(() -> new TableColumnNotDeclaredException(
                        TABLE_COLUMN_NOT_DECLARED.getMessage(columnName, webTable.getElementIdentifier().getLastUsedName())));
        WebLocatorHolder tableColumnLocator = tableColumnHolder.getBodyLocator()
                .orElseThrow(() -> new TableColumnLocatorNotDeclaredException(
                        TABLE_COLUMN_LOCATOR_NOT_DECLARED.getMessage(columnName, webTable.getElementIdentifier().getLastUsedName())));
        Class<? extends WebMappedBlock> mappedCellClass = tableColumnHolder.getBodyClass();
        Map<Integer, WebMappedBlock> webMappedCells = new HashMap<>();
        for (int index : indexes) {
            WebMappedBlock webMappedBlockInstance = initializer.initWebMappedBlock(mappedCellClass);
            List<Method> childElementMethods = getWebChildElementMethods(mappedCellClass);
            WebElementRegistry elementRegistry = createWebChildElements(webMappedBlockInstance, childElementMethods);
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            parentLocators.add(tableRowLocator.clone().setSingle(true).setIndex(index));
            parentLocators.add(tableColumnLocator.clone());
            WebParentInfo<WebTable> parentInfo = WebParentInfo.of(webTable, hash, parentLocators);
            webMappedCells.put(index, decorator.decorateWebMappedBlockInstance(webTable, webMappedBlockInstance, elementRegistry, parentInfo));
        }
        return webMappedCells;
    }

    protected WebElementRegistry createWebChildElements(WebParentElement parent, List<Method> elementMethods) {
        List<WebChildElement> webChildElements = elementMethods.stream()
                .map(webChildElementMethod -> {
                    //noinspection unchecked because all methods filtered by returnType
                    Class<? extends WebChildElement> webChildElementType = (Class<? extends WebChildElement>) webChildElementMethod.getReturnType();
                    if (WebBlock.class.isAssignableFrom(webChildElementType)) {
                        //noinspection unchecked because webChildElementType already checked
                        Class<? extends WebBlock> webBlockType = (Class<? extends WebBlock>) webChildElementType;
                        WebBlock webBlockInstance = initializer.initWebBlock(webBlockType);
                        List<Method> childElementMethods = getWebChildElementMethods(webBlockType);
                        WebElementRegistry elementRegistry = createWebChildElements(webBlockInstance, childElementMethods);
                        return decorator.decorateWebBlockInstance(parent, webBlockInstance, webChildElementMethod, elementRegistry);
                    } else {
                        WebChildElement webChildElementInstance = initializer.initWebChildElement(webChildElementType);
                        return decorator.decorateWebChildElementInstance(parent, webChildElementInstance, webChildElementMethod);
                    }
                }).collect(toList());
        return WebElementRegistry.of(webChildElements);
    }

    protected static List<Method> getWebChildElementMethods(Class<? extends WebParentElement> processedClass) {
        Predicate<Method> methodPredicate = method -> WebChildElement.class.isAssignableFrom(method.getReturnType()) && !method.isDefault();
        Set<Method> methods = new HashSet<>();
        Set<Class<? extends WebParentElement>> inheritedInterfaces = getInheritedInterfaces(WebParentElement.class, processedClass);
        for (Class<? extends WebParentElement> inheritedInterface : inheritedInterfaces) {
            methods.addAll(findMethods(inheritedInterface, methodPredicate));
        }
        return new ArrayList<>(methods);
    }

}
