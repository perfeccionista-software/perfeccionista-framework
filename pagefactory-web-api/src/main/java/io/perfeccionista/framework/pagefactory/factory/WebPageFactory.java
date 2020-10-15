package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.exceptions.WebElementNotFound;
import io.perfeccionista.framework.name.MappedWebBlockIdentifier;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebRadioButtonBlock;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolderForChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolderForIsolatedStructuralElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolderForStructuralElement;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentHolder;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_NOT_FOUND;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;
import static io.perfeccionista.framework.utils.WebElementUtils.getWebChildElementMethods;
import static java.util.stream.Collectors.toList;

// TODO: Сделать все элементы Clonable и при создании блоков/строк таблицы просто их клонировать и инжектить локаторы
public class WebPageFactory {

    public static final String RADIO_BUTTON_METHOD_NAME = "radioButton";

    protected final WebPageFactoryPreferences configuration;
    protected final WebElementInitializer initializer;
    protected final WebElementDecorator decorator;

    public WebPageFactory(@NotNull WebPageFactoryPreferences configuration) {
        this.configuration = configuration;
        this.initializer = new WebElementInitializer(configuration);
        this.decorator = new WebElementDecorator(configuration, this);
    }

    public @NotNull WebPage createWebPage(@NotNull Class<? extends WebPage> webPageClass) {
        WebPage webPageInstance = initializer.initWebPageInstance(webPageClass);
        List<Method> webChildElementMethods = getWebChildElementMethods(webPageClass);
        WebElementRegistry webPageElementRegistry = createWebChildElementRegistry(webPageInstance, webChildElementMethods);
        return decorator.decorateWebPageInstance(webPageInstance, webPageElementRegistry);
    }

    public WebChildElement createWebChildElement(@NotNull WebParentElement parent,
                                                 @NotNull Method webChildElementMethod) {
        //noinspection unchecked
        Class<? extends WebChildElement> childElementType = (Class<? extends WebChildElement>) webChildElementMethod.getReturnType();
        WebChildElement childElementInstance = initializer.initWebChildElement(childElementType);
        return decorator.decorateWebChildElementInstance(childElementInstance, WebParentHolderForChildElement.of(parent), webChildElementMethod);
    }

    public WebBlock createWebBlock(@NotNull WebParentElement parent,
                                   @NotNull Method webBlockMethod) {
        //noinspection unchecked
        Class<? extends WebBlock> webBlockClass = (Class<? extends WebBlock>) webBlockMethod.getReturnType();
        WebBlock webBlockInstance = initializer.initWebBlock(webBlockClass);
        List<Method> childElementMethods = getWebChildElementMethods(webBlockClass);
        WebElementRegistry elementRegistry = createWebChildElementRegistry(webBlockInstance, childElementMethods);
        return decorator.decorateWebBlockInstance(webBlockInstance, elementRegistry, WebParentHolderForChildElement.of(parent), webBlockMethod);
    }

    public <T extends WebBlock> T createMappedWebBlock(@NotNull WebChildElement parent,
                                                       @NotNull Class<T> webMappedBlockClass) {
        List<Method> childElementMethods = getWebChildElementMethods(webMappedBlockClass);
        T webMappedBlockInstance = initializer.initMappedWebBlock(webMappedBlockClass);
        WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
        WebParentHolder parentInfo = WebParentHolderForIsolatedStructuralElement.of(parent);
        return decorator.decorateMappedWebBlockInstance(webMappedBlockInstance, webMappedBlockClass, elementRegistry, parentInfo);
    }

    public Map<Integer, WebRadioButton> createWebRadioButtons(@NotNull WebRadioGroup webRadioGroup,
                                                              @NotNull WebFilterResult filterResult) {
        Map<Integer, WebRadioButton> webRadioButtons = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();
        WebLocatorHolder radioButtonLocatorHolder = webRadioGroup.getRequiredLocator(RADIO);

        Class<? extends DefaultWebRadioButtonBlock> mappedBlockClass = webRadioGroup.getWebRadioGroupFrame()
                .getMappedBlockFrame()
                .getClass();

        List<Method> childElementMethods = getWebChildElementMethods(mappedBlockClass);
        Method webRadioButtonMethod = childElementMethods.stream()
                .filter(method -> RADIO_BUTTON_METHOD_NAME.equals(method.getName()))
                .filter(method -> method.getParameterCount() == 0)
                .filter(method -> WebRadioButton.class.isAssignableFrom(method.getReturnType()))
                .findFirst()
                .orElseThrow(() -> WebElementNotFound.exception(ELEMENT_NOT_FOUND.getMessage(RADIO_BUTTON_METHOD_NAME)));

        for (int index : indexes) {
            DefaultWebRadioButtonBlock webMappedBlockInstance = initializer.initMappedWebBlock(mappedBlockClass);
            // ElementRegistry
            WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            WebLocatorHolder radioButtonBlockRootLocator = radioButtonLocatorHolder.clone().setSingle(true).setIndex(index);
            parentLocators.add(radioButtonBlockRootLocator);
            WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webRadioGroup, hash, parentLocators);
            // Decorate MappedBlock
            WebBlock decoratedWebMappedBlockInstance = decorator
                    .decorateMappedWebBlockInstance(webMappedBlockInstance, mappedBlockClass, elementRegistry, parentInfo);
            WebRadioButton webRadioButton = decoratedWebMappedBlockInstance.getElementRegistry()
                    .getRequiredElementByMethod(webRadioButtonMethod, WebRadioButton.class);
            webRadioButtons.put(index, webRadioButton);
        }

        return webRadioButtons;
    }

    public Map<Integer, WebBlock> createWebListBlocks(@NotNull WebList webList,
                                                      @NotNull WebFilterResult filterResult) {
        Map<Integer, WebBlock> webMappedBlocks = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();
        WebLocatorHolder liLocatorHolder = webList.getRequiredLocator(LI);

        //noinspection unchecked
        Class<? extends WebBlock> mappedBlockClass = (Class<? extends WebBlock>) webList.getWebListFrame()
                .getMappedBlockFrame()
                .getElementIdentifier()
                .getElementType();

        List<Method> childElementMethods = getWebChildElementMethods(mappedBlockClass);

        for (int index : indexes) {
            WebBlock webMappedBlockInstance = initializer.initMappedWebBlock(mappedBlockClass);
            // ElementRegistry
            WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            WebLocatorHolder blockRootLocator = liLocatorHolder.clone().setSingle(true).setIndex(index);
            parentLocators.add(blockRootLocator);
            WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webList, hash, parentLocators);
            // Decorate MappedBlock
            WebBlock decoratedWebMappedBlockInstance = decorator
                    .decorateMappedWebBlockInstance(webMappedBlockInstance, mappedBlockClass, elementRegistry, parentInfo);
            webMappedBlocks.put(index, decoratedWebMappedBlockInstance);
        }

        return webMappedBlocks;
    }

    public Map<Integer, DefaultWebTextBlock> createWebTextListBlocks(@NotNull WebTextList webList,
                                                                     @NotNull WebFilterResult filterResult) {
        Map<Integer, DefaultWebTextBlock> webMappedBlocks = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();
        WebLocatorHolder liLocatorHolder = webList.getRequiredLocator(LI);

        //noinspection unchecked
        Class<? extends DefaultWebTextBlock> mappedBlockClass = (Class<? extends DefaultWebTextBlock>) webList.getWebTextListFrame()
                .getMappedBlockFrame()
                .getElementIdentifier()
                .getElementType();

        List<Method> childElementMethods = getWebChildElementMethods(mappedBlockClass);

        for (int index : indexes) {
            DefaultWebTextBlock webMappedBlockInstance = initializer.initMappedWebBlock(mappedBlockClass);
            // ElementRegistry
            WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            WebLocatorHolder textBlockRootLocator = liLocatorHolder.clone().setSingle(true).setIndex(index);
            parentLocators.add(textBlockRootLocator);
            WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webList, hash, parentLocators);
            // Decorate MappedBlock
            DefaultWebTextBlock decoratedWebMappedBlockInstance = decorator
                    .decorateMappedWebBlockInstance(webMappedBlockInstance, mappedBlockClass, elementRegistry, parentInfo);
            webMappedBlocks.put(index, decoratedWebMappedBlockInstance);
        }

        return webMappedBlocks;
    }

    public Map<Integer, WebBlock> createWebTableCells(@NotNull WebTable webTable,
                                                      @NotNull String columnName,
                                                      @NotNull TableSection tableSection,
                                                      @NotNull WebFilterResult filterResult) {
        Map<Integer, WebBlock> webMappedCells = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        WebLocatorHolder tableRowLocator = webTable.getRequiredLocator(TBODY_ROW);
        WebLocatorHolder tableCellLocator = webTable.getWebTableFrame()
                .getRequiredBodyLocator(columnName);
        //noinspection unchecked
        Class<? extends WebBlock> tableCellMappedBlockClass = (Class<? extends WebBlock>) webTable.getWebTableFrame()
                .getRequiredBodyMappedBlock(columnName)
                .getElementIdentifier()
                .getElementType();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = webTable.getRequiredLocator(THEAD_ROW);
            tableCellLocator = webTable.getWebTableFrame()
                    .getRequiredHeaderLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends WebBlock>) webTable.getWebTableFrame()
                    .getRequiredHeaderMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = webTable.getRequiredLocator(TFOOT_ROW);
            tableCellLocator = webTable.getWebTableFrame()
                    .getRequiredFooterLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends WebBlock>) webTable.getWebTableFrame()
                    .getRequiredFooterMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }

        List<Method> childElementMethods = getWebChildElementMethods(tableCellMappedBlockClass);

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                WebBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
                // ElementRegistry
                WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
                // ParentLocators
                Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                parentLocators.add(tableRowLocator.clone().setSingle(true).setIndex(index));
                WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTable, hash, parentLocators);
                // Decorate MappedBlock
                WebBlock decoratedWebMappedBlockInstance = decorator
                        .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                webMappedCells.put(index, decoratedWebMappedBlockInstance);
            }
        } else {
            WebBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
            // ElementRegistry
            WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            parentLocators.add(tableRowLocator.clone());
            WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
            parentLocators.add(tableCellRootLocator);
            WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTable, hash, parentLocators);
            // Decorate MappedBlock
            WebBlock decoratedWebMappedBlockInstance = decorator
                    .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
            webMappedCells.put(-1, decoratedWebMappedBlockInstance);
        }

        return webMappedCells;
    }

    public Map<Integer, DefaultWebTextBlock> createWebTextTableCells(@NotNull WebTextTable webTextTable,
                                                                     @NotNull String columnName,
                                                                     @NotNull TableSection tableSection,
                                                                     @NotNull WebFilterResult filterResult) {
        Map<Integer, DefaultWebTextBlock> webMappedCells = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        WebLocatorHolder tableRowLocator = webTextTable.getRequiredLocator(TBODY_ROW);
        WebLocatorHolder tableCellLocator = webTextTable.getWebTextTableFrame()
                .getRequiredBodyLocator(columnName);
        //noinspection unchecked
        Class<? extends DefaultWebTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultWebTextBlock>) webTextTable.getWebTextTableFrame()
                .getRequiredBodyMappedBlock(columnName)
                .getElementIdentifier()
                .getElementType();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = webTextTable.getRequiredLocator(THEAD_ROW);
            tableCellLocator = webTextTable.getWebTextTableFrame()
                    .getRequiredHeaderLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends DefaultWebTextBlock>) webTextTable.getWebTextTableFrame()
                    .getRequiredHeaderMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = webTextTable.getRequiredLocator(TFOOT_ROW);
            tableCellLocator = webTextTable.getWebTextTableFrame()
                    .getRequiredFooterLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends DefaultWebTextBlock>) webTextTable.getWebTextTableFrame()
                    .getRequiredFooterMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }

        List<Method> childElementMethods = getWebChildElementMethods(tableCellMappedBlockClass);

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                DefaultWebTextBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
                // ElementRegistry
                WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
                // ParentLocators
                Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                parentLocators.add(tableRowLocator.clone().setSingle(true).setIndex(index));
                WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTextTable, hash, parentLocators);
                // Decorate MappedBlock
                DefaultWebTextBlock decoratedWebMappedBlockInstance = decorator
                        .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                webMappedCells.put(index, decoratedWebMappedBlockInstance);
            }
        } else {
            DefaultWebTextBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
            // ElementRegistry
            WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            parentLocators.add(tableRowLocator.clone());
            WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
            parentLocators.add(tableCellRootLocator);
            WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTextTable, hash, parentLocators);
            // Decorate MappedBlock
            DefaultWebTextBlock decoratedWebMappedBlockInstance = decorator
                    .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
            webMappedCells.put(-1, decoratedWebMappedBlockInstance);
        }

        return webMappedCells;
    }

    public Map<Integer, WebBlock> createWebTableRows(@NotNull WebTable webTable,
                                                     @NotNull TableSection tableSection,
                                                     @NotNull WebFilterResult filterResult) {
        Map<Integer, WebBlock> webMappedRows = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        Set<String> tableColumnNames = webTable.getWebTableFrame().getTableColumnNames();
        WebLocatorHolder tableRowLocator = webTable.getRequiredLocator(TBODY_ROW);
        Map<String, WebBlock> tableCellFrames = webTable.getWebTableFrame().getBody();
        Map<String, WebLocatorHolder> tableCellLocators = webTable.getWebTableFrame().getBodyLocators();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = webTable.getRequiredLocator(THEAD_ROW);
            tableCellFrames = webTable.getWebTableFrame().getHeaders();
            tableCellLocators = webTable.getWebTableFrame().getHeaderLocators();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = webTable.getRequiredLocator(TFOOT_ROW);
            tableCellFrames = webTable.getWebTableFrame().getFooters();
            tableCellLocators = webTable.getWebTableFrame().getFooterLocators();
        }

        Map<String, List<Method>> webChildElementMethods = new HashMap<>();
        for (String tableColumnName : tableColumnNames) {
            //noinspection unchecked
            Class<? extends WebBlock> tableCellMappedBlockClass = (Class<? extends WebBlock>) tableCellFrames
                    .get(tableColumnName)
                    .getElementIdentifier()
                    .getElementType();
            List<Method> childElementMethods = getWebChildElementMethods(tableCellMappedBlockClass);
            webChildElementMethods.put(tableColumnName, childElementMethods);
        }

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                WebBlock webMappedRowInstance = initializer.initWebTableRow();

                Map<String, WebBlock> tableCells = new HashMap<>();
                for (String tableColumnName : tableColumnNames) {
                    //noinspection unchecked
                    Class<? extends WebBlock> tableCellMappedBlockClass = (Class<? extends WebBlock>) tableCellFrames
                            .get(tableColumnName)
                            .getElementIdentifier()
                            .getElementType();
                    WebLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                    WebBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
                    // ElementRegistry
                    List<Method> methods = webChildElementMethods.get(tableColumnName);
                    WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, methods);
                    // ParentLocators (from tableRowInstance)
                    Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                    WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                    parentLocators.add(tableCellRootLocator);
                    WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webMappedRowInstance, parentLocators);
                    // Decorate MappedBlock
                    WebBlock decoratedWebMappedBlockInstance = decorator
                            .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                    tableCells.put(tableColumnName, decoratedWebMappedBlockInstance);
                }
                // ElementRegistry
                WebElementRegistry elementRegistry = WebElementRegistry.of(tableCells);
                // ParentLocators (from tableRowInstance)
                Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                WebLocatorHolder tableRowRootLocator = tableRowLocator.clone().setSingle(true).setIndex(index);
                parentLocators.add(tableRowRootLocator);
                WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTable, hash, parentLocators);
                WebBlock decoratedWebMappedRowInstance = decorator
                        .decorateMappedWebBlockInstance(webMappedRowInstance, WebBlock.class, elementRegistry, parentInfo);
                webMappedRows.put(index, decoratedWebMappedRowInstance);
            }
        } else {
            WebBlock webMappedRowInstance = initializer.initWebTableRow();

            Map<String, WebBlock> tableCells = new HashMap<>();
            for (String tableColumnName : tableColumnNames) {
                //noinspection unchecked
                Class<? extends WebBlock> tableCellMappedBlockClass = (Class<? extends WebBlock>) tableCellFrames
                        .get(tableColumnName)
                        .getElementIdentifier()
                        .getElementType();
                WebLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                WebBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
                // ElementRegistry
                List<Method> methods = webChildElementMethods.get(tableColumnName);
                WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, methods);
                // ParentLocators (from tableRowInstance)
                Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webMappedRowInstance, parentLocators);
                // Decorate MappedBlock
                WebBlock decoratedWebMappedBlockInstance = decorator
                        .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                tableCells.put(tableColumnName, decoratedWebMappedBlockInstance);
            }
            // ElementRegistry
            WebElementRegistry elementRegistry = WebElementRegistry.of(tableCells);
            // ParentLocators (from tableRowInstance)
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            WebLocatorHolder tableRowRootLocator = tableRowLocator.clone();
            parentLocators.add(tableRowRootLocator);
            WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTable, hash, parentLocators);
            WebBlock decoratedWebMappedRowInstance = decorator
                    .decorateMappedWebBlockInstance(webMappedRowInstance, WebBlock.class, elementRegistry, parentInfo);
            webMappedRows.put(-1, decoratedWebMappedRowInstance);
        }

        return webMappedRows;
    }


    public Map<Integer, WebBlock> createWebTextTableRows(@NotNull WebTextTable webTextTable,
                                                         @NotNull TableSection tableSection,
                                                         @NotNull WebFilterResult filterResult) {
        Map<Integer, WebBlock> webMappedRows = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        Set<String> tableColumnNames = webTextTable.getWebTextTableFrame().getTableColumnNames();
        WebLocatorHolder tableRowLocator = webTextTable.getRequiredLocator(TBODY_ROW);
        Map<String, DefaultWebTextBlock> tableCellFrames = webTextTable.getWebTextTableFrame().getBody();
        Map<String, WebLocatorHolder> tableCellLocators = webTextTable.getWebTextTableFrame().getBodyLocators();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = webTextTable.getRequiredLocator(THEAD_ROW);
            tableCellFrames = webTextTable.getWebTextTableFrame().getHeaders();
            tableCellLocators = webTextTable.getWebTextTableFrame().getHeaderLocators();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = webTextTable.getRequiredLocator(TFOOT_ROW);
            tableCellFrames = webTextTable.getWebTextTableFrame().getFooters();
            tableCellLocators = webTextTable.getWebTextTableFrame().getFooterLocators();
        }

        Map<String, List<Method>> webChildElementMethods = new HashMap<>();
        for (String tableColumnName : tableColumnNames) {
            //noinspection unchecked
            Class<? extends DefaultWebTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultWebTextBlock>) tableCellFrames
                    .get(tableColumnName)
                    .getElementIdentifier()
                    .getElementType();
            List<Method> childElementMethods = getWebChildElementMethods(tableCellMappedBlockClass);
            webChildElementMethods.put(tableColumnName, childElementMethods);
        }

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                WebBlock webMappedRowInstance = initializer.initWebTableRow();

                Map<String, WebBlock> tableCells = new HashMap<>();
                for (String tableColumnName : tableColumnNames) {
                    //noinspection unchecked
                    Class<? extends DefaultWebTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultWebTextBlock>) tableCellFrames
                            .get(tableColumnName)
                            .getElementIdentifier()
                            .getElementType();
                    WebLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                    DefaultWebTextBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
                    // ElementRegistry
                    List<Method> methods = webChildElementMethods.get(tableColumnName);
                    WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, methods);
                    // ParentLocators (from tableRowInstance)
                    Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                    WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                    parentLocators.add(tableCellRootLocator);
                    WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webMappedRowInstance, parentLocators);
                    // Decorate MappedBlock
                    DefaultWebTextBlock decoratedWebMappedBlockInstance = decorator
                            .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                    tableCells.put(tableColumnName, decoratedWebMappedBlockInstance);
                }
                // ElementRegistry
                WebElementRegistry elementRegistry = WebElementRegistry.of(tableCells);
                // ParentLocators (from tableRowInstance)
                Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                WebLocatorHolder tableRowRootLocator = tableRowLocator.clone().setSingle(true).setIndex(index);
                parentLocators.add(tableRowRootLocator);
                WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTextTable, hash, parentLocators);
                WebBlock decoratedWebMappedRowInstance = decorator
                        .decorateMappedWebBlockInstance(webMappedRowInstance, DefaultWebTextBlock.class, elementRegistry, parentInfo);
                webMappedRows.put(index, decoratedWebMappedRowInstance);
            }
        } else {
            WebBlock webMappedRowInstance = initializer.initWebTableRow();

            Map<String, WebBlock> tableCells = new HashMap<>();
            for (String tableColumnName : tableColumnNames) {
                //noinspection unchecked
                Class<? extends DefaultWebTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultWebTextBlock>) tableCellFrames
                        .get(tableColumnName)
                        .getElementIdentifier()
                        .getElementType();
                WebLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                DefaultWebTextBlock webMappedBlockInstance = initializer.initMappedWebBlock(tableCellMappedBlockClass);
                // ElementRegistry
                List<Method> methods = webChildElementMethods.get(tableColumnName);
                WebElementRegistry elementRegistry = createWebChildElementRegistry(webMappedBlockInstance, methods);
                // ParentLocators (from tableRowInstance)
                Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
                WebLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webMappedRowInstance, parentLocators);
                // Decorate MappedBlock
                DefaultWebTextBlock decoratedWebMappedBlockInstance = decorator
                        .decorateMappedWebBlockInstance(webMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                tableCells.put(tableColumnName, decoratedWebMappedBlockInstance);
            }
            // ElementRegistry
            WebElementRegistry elementRegistry = WebElementRegistry.of(tableCells);
            // ParentLocators (from tableRowInstance)
            Deque<WebLocatorHolder> parentLocators = new ArrayDeque<>();
            WebLocatorHolder tableRowRootLocator = tableRowLocator.clone();
            parentLocators.add(tableRowRootLocator);
            WebParentHolder parentInfo = WebParentHolderForStructuralElement.of(webTextTable, hash, parentLocators);
            WebBlock decoratedWebMappedRowInstance = decorator
                    .decorateMappedWebBlockInstance(webMappedRowInstance, DefaultWebTextBlock.class, elementRegistry, parentInfo);
            webMappedRows.put(-1, decoratedWebMappedRowInstance);
        }

        return webMappedRows;
    }

    protected @NotNull WebElementRegistry createWebChildElementRegistry(@NotNull WebParentElement parent,
                                                                        @NotNull List<Method> parentMethods) {
        List<WebChildElement> childElements = parentMethods.stream()
                .map(childElementMethod -> {
                    //noinspection unchecked because all methods filtered by returnType
                    Class<? extends WebChildElement> childElementClass = (Class<? extends WebChildElement>) childElementMethod.getReturnType();
                    if (WebBlock.class.isAssignableFrom(childElementClass)) {
                        return createWebBlock(parent, childElementMethod);
                    } else {
                        return createWebChildElement(parent, childElementMethod);
                    }
                }).collect(toList());
        return WebElementRegistry.of(childElements);
    }

}