package io.perfeccionista.framework.pagefactory.factory;

import io.perfeccionista.framework.pagefactory.elements.DefaultMobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.elements.MobileTableRow;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentHolder;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentHolderForChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentHolderForIsolatedStructuralElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentHolderForStructuralElement;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.preferences.MobilePageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_FOOTER;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;
import static io.perfeccionista.framework.utils.MobileElementUtils.getMobileChildElementMethods;
import static java.util.stream.Collectors.toList;

public class MobilePageFactory {

    protected final MobilePageFactoryPreferences configuration;
    protected final MobileElementInitializer initializer;
    protected final MobileElementDecorator decorator;

    public MobilePageFactory(@NotNull MobilePageFactoryPreferences configuration) {
        this.configuration = configuration;
        this.initializer = new MobileElementInitializer(configuration);
        this.decorator = new MobileElementDecorator(configuration, this);
    }

    public @NotNull MobilePage createMobilePage(@NotNull Class<? extends MobilePage> mobilePageClass) {
        MobilePage mobilePageInstance = initializer.initMobilePageInstance(mobilePageClass);
        List<Method> mobileChildElementMethods = getMobileChildElementMethods(mobilePageClass);
        MobileElementRegistry mobilePageElementRegistry = createMobileChildElementRegistry(mobilePageInstance, mobileChildElementMethods);
        return decorator.decorateMobilePageInstance(mobilePageInstance, mobilePageElementRegistry);
    }

    public MobileChildElement createMobileChildElement(@NotNull MobileParentElement parent,
                                                       @NotNull Method mobileChildElementMethod) {
        //noinspection unchecked
        Class<? extends MobileChildElement> childElementType = (Class<? extends MobileChildElement>) mobileChildElementMethod.getReturnType();
        MobileChildElement childElementInstance = initializer.initMobileChildElement(childElementType);
        return decorator.decorateMobileChildElementInstance(childElementInstance, MobileParentHolderForChildElement.of(parent), mobileChildElementMethod);
    }

    public MobileBlock createMobileBlock(@NotNull MobileParentElement parent,
                                         @NotNull Method mobileBlockMethod) {
        //noinspection unchecked
        Class<? extends MobileBlock> mobileBlockClass = (Class<? extends MobileBlock>) mobileBlockMethod.getReturnType();
        MobileBlock mobileBlockInstance = initializer.initMobileBlock(mobileBlockClass);
        List<Method> childElementMethods = getMobileChildElementMethods(mobileBlockClass);
        MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileBlockInstance, childElementMethods);
        return decorator.decorateMobileBlockInstance(mobileBlockInstance, elementRegistry, MobileParentHolderForChildElement.of(parent), mobileBlockMethod);
    }

    public <T extends MobileBlock> T createMappedMobileBlock(@NotNull MobileChildElement parent,
                                                       @NotNull Class<T> mobileMappedBlockClass) {
        List<Method> childElementMethods = getMobileChildElementMethods(mobileMappedBlockClass);
        T mobileMappedBlockInstance = initializer.initMappedMobileBlock(mobileMappedBlockClass);
        MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, childElementMethods);
        MobileParentHolder parentInfo = MobileParentHolderForIsolatedStructuralElement.of(parent);
        return decorator.decorateMappedMobileBlockInstance(mobileMappedBlockInstance, mobileMappedBlockClass, elementRegistry, parentInfo);
    }

    public Map<Integer, MobileBlock> createMobileListBlocks(@NotNull MobileList mobileList,
                                                       @NotNull FilterResult filterResult) {
        Map<Integer, MobileBlock> mobileMappedBlocks = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();
        MobileLocatorHolder liLocatorHolder = mobileList.getRequiredLocator(ITEM);

        //noinspection unchecked
        Class<? extends MobileBlock> mappedBlockClass = (Class<? extends MobileBlock>) mobileList.getMobileListFrame()
                .getMappedBlockFrame()
                .getElementIdentifier()
                .getElementType();

        List<Method> childElementMethods = getMobileChildElementMethods(mappedBlockClass);

        for (int index : indexes) {
            MobileBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(mappedBlockClass);
            // ElementRegistry
            MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
            MobileLocatorHolder blockRootLocator = liLocatorHolder.clone().setSingle(true).setIndex(index);
            parentLocators.add(blockRootLocator);
            MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileList, hash, parentLocators);
            // Decorate MappedBlock
            MobileBlock decoratedMobileMappedBlockInstance = decorator
                    .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, mappedBlockClass, elementRegistry, parentInfo);
            mobileMappedBlocks.put(index, decoratedMobileMappedBlockInstance);
        }

        return mobileMappedBlocks;
    }

    public Map<Integer, DefaultMobileTextBlock> createMobileTextListBlocks(@NotNull MobileTextList mobileList,
                                                                           @NotNull FilterResult filterResult) {
        Map<Integer, DefaultMobileTextBlock> mobileMappedBlocks = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();
        MobileLocatorHolder liLocatorHolder = mobileList.getRequiredLocator(ITEM);

        //noinspection unchecked
        Class<? extends DefaultMobileTextBlock> mappedBlockClass = (Class<? extends DefaultMobileTextBlock>) mobileList.getMobileTextListFrame()
                .getMappedBlockFrame()
                .getElementIdentifier()
                .getElementType();

        List<Method> childElementMethods = getMobileChildElementMethods(mappedBlockClass);

        for (int index : indexes) {
            DefaultMobileTextBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(mappedBlockClass);
            // ElementRegistry
            MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
            MobileLocatorHolder textBlockRootLocator = liLocatorHolder.clone().setSingle(true).setIndex(index);
            parentLocators.add(textBlockRootLocator);
            MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileList, hash, parentLocators);
            // Decorate MappedBlock
            DefaultMobileTextBlock decoratedMobileMappedBlockInstance = decorator
                    .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, mappedBlockClass, elementRegistry, parentInfo);
            mobileMappedBlocks.put(index, decoratedMobileMappedBlockInstance);
        }

        return mobileMappedBlocks;
    }

    public Map<Integer, MobileBlock> createMobileTableCells(@NotNull MobileTable mobileTable,
                                                      @NotNull String columnName,
                                                      @NotNull TableSection tableSection,
                                                      @NotNull FilterResult filterResult) {
        Map<Integer, MobileBlock> mobileMappedCells = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        MobileLocatorHolder tableRowLocator = mobileTable.getRequiredLocator(ITEM);
        MobileLocatorHolder tableCellLocator = mobileTable.getMobileTableFrame()
                .getRequiredBodyLocator(columnName);
        //noinspection unchecked
        Class<? extends MobileBlock> tableCellMappedBlockClass = (Class<? extends MobileBlock>) mobileTable.getMobileTableFrame()
                .getRequiredBodyMappedBlock(columnName)
                .getElementIdentifier()
                .getElementType();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = mobileTable.getRequiredLocator(TABLE_HEADER);
            tableCellLocator = mobileTable.getMobileTableFrame()
                    .getRequiredHeaderLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends MobileBlock>) mobileTable.getMobileTableFrame()
                    .getRequiredHeaderMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = mobileTable.getRequiredLocator(TABLE_FOOTER);
            tableCellLocator = mobileTable.getMobileTableFrame()
                    .getRequiredFooterLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends MobileBlock>) mobileTable.getMobileTableFrame()
                    .getRequiredFooterMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }

        List<Method> childElementMethods = getMobileChildElementMethods(tableCellMappedBlockClass);

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                MobileBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
                // ElementRegistry
                MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, childElementMethods);
                // ParentLocators
                Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                parentLocators.add(tableRowLocator.clone().setSingle(true).setIndex(index));
                MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTable, hash, parentLocators);
                // Decorate MappedBlock
                MobileBlock decoratedMobileMappedBlockInstance = decorator
                        .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                mobileMappedCells.put(index, decoratedMobileMappedBlockInstance);
            }
        } else {
            MobileBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
            // ElementRegistry
            MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
            parentLocators.add(tableRowLocator.clone());
            MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
            parentLocators.add(tableCellRootLocator);
            MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTable, hash, parentLocators);
            // Decorate MappedBlock
            MobileBlock decoratedMobileMappedBlockInstance = decorator
                    .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
            mobileMappedCells.put(-1, decoratedMobileMappedBlockInstance);
        }

        return mobileMappedCells;
    }

    public Map<Integer, DefaultMobileTextBlock> createMobileTextTableCells(@NotNull MobileTextTable mobileTextTable,
                                                                           @NotNull String columnName,
                                                                           @NotNull TableSection tableSection,
                                                                           @NotNull FilterResult filterResult) {
        Map<Integer, DefaultMobileTextBlock> mobileMappedCells = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        MobileLocatorHolder tableRowLocator = mobileTextTable.getRequiredLocator(ITEM);
        MobileLocatorHolder tableCellLocator = mobileTextTable.getMobileTextTableFrame()
                .getRequiredBodyLocator(columnName);
        //noinspection unchecked
        Class<? extends DefaultMobileTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultMobileTextBlock>) mobileTextTable.getMobileTextTableFrame()
                .getRequiredBodyMappedBlock(columnName)
                .getElementIdentifier()
                .getElementType();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = mobileTextTable.getRequiredLocator(TABLE_HEADER);
            tableCellLocator = mobileTextTable.getMobileTextTableFrame()
                    .getRequiredHeaderLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends DefaultMobileTextBlock>) mobileTextTable.getMobileTextTableFrame()
                    .getRequiredHeaderMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = mobileTextTable.getRequiredLocator(TABLE_FOOTER);
            tableCellLocator = mobileTextTable.getMobileTextTableFrame()
                    .getRequiredFooterLocator(columnName);
            //noinspection unchecked
            tableCellMappedBlockClass = (Class<? extends DefaultMobileTextBlock>) mobileTextTable.getMobileTextTableFrame()
                    .getRequiredFooterMappedBlock(columnName)
                    .getElementIdentifier()
                    .getElementType();
        }

        List<Method> childElementMethods = getMobileChildElementMethods(tableCellMappedBlockClass);

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                DefaultMobileTextBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
                // ElementRegistry
                MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, childElementMethods);
                // ParentLocators
                Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                parentLocators.add(tableRowLocator.clone().setSingle(true).setIndex(index));
                MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTextTable, hash, parentLocators);
                // Decorate MappedBlock
                DefaultMobileTextBlock decoratedMobileMappedBlockInstance = decorator
                        .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                mobileMappedCells.put(index, decoratedMobileMappedBlockInstance);
            }
        } else {
            DefaultMobileTextBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
            // ElementRegistry
            MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, childElementMethods);
            // ParentLocators
            Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
            parentLocators.add(tableRowLocator.clone());
            MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
            parentLocators.add(tableCellRootLocator);
            MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTextTable, hash, parentLocators);
            // Decorate MappedBlock
            DefaultMobileTextBlock decoratedMobileMappedBlockInstance = decorator
                    .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
            mobileMappedCells.put(-1, decoratedMobileMappedBlockInstance);
        }

        return mobileMappedCells;
    }

    public Map<Integer, MobileTableRow> createMobileTableRows(@NotNull MobileTable mobileTable,
                                                              @NotNull TableSection tableSection,
                                                              @NotNull FilterResult filterResult) {
        Map<Integer, MobileTableRow> mobileMappedRows = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        Set<String> tableColumnNames = mobileTable.getMobileTableFrame().getTableColumnNames();
        MobileLocatorHolder tableRowLocator = mobileTable.getRequiredLocator(ITEM);
        Map<String, MobileBlock> tableCellFrames = mobileTable.getMobileTableFrame().getBody();
        Map<String, MobileLocatorHolder> tableCellLocators = mobileTable.getMobileTableFrame().getBodyLocators();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = mobileTable.getRequiredLocator(TABLE_HEADER);
            tableCellFrames = mobileTable.getMobileTableFrame().getHeaders();
            tableCellLocators = mobileTable.getMobileTableFrame().getHeaderLocators();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = mobileTable.getRequiredLocator(TABLE_FOOTER);
            tableCellFrames = mobileTable.getMobileTableFrame().getFooters();
            tableCellLocators = mobileTable.getMobileTableFrame().getFooterLocators();
        }

        Map<String, List<Method>> mobileChildElementMethods = new HashMap<>();
        for (String tableColumnName : tableColumnNames) {
            //noinspection unchecked
            Class<? extends MobileBlock> tableCellMappedBlockClass = (Class<? extends MobileBlock>) tableCellFrames
                    .get(tableColumnName)
                    .getElementIdentifier()
                    .getElementType();
            List<Method> childElementMethods = getMobileChildElementMethods(tableCellMappedBlockClass);
            mobileChildElementMethods.put(tableColumnName, childElementMethods);
        }

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                MobileTableRow mobileMappedRowInstance = initializer.initMobileTableRow();

                Map<String, MobileBlock> tableCells = new HashMap<>();
                for (String tableColumnName : tableColumnNames) {
                    //noinspection unchecked
                    Class<? extends MobileBlock> tableCellMappedBlockClass = (Class<? extends MobileBlock>) tableCellFrames
                            .get(tableColumnName)
                            .getElementIdentifier()
                            .getElementType();
                    MobileLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                    MobileBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
                    // ElementRegistry
                    List<Method> methods = mobileChildElementMethods.get(tableColumnName);
                    MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, methods);
                    // ParentLocators (from tableRowInstance)
                    Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                    MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                    parentLocators.add(tableCellRootLocator);
                    MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileMappedRowInstance, parentLocators);
                    // Decorate MappedBlock
                    MobileBlock decoratedMobileMappedBlockInstance = decorator
                            .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                    tableCells.put(tableColumnName, decoratedMobileMappedBlockInstance);
                }
                // ElementRegistry
                MobileElementRegistry elementRegistry = MobileElementRegistry.of(tableCells);
                // ParentLocators (from tableRowInstance)
                Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                MobileLocatorHolder tableRowRootLocator = tableRowLocator.clone().setSingle(true).setIndex(index);
                parentLocators.add(tableRowRootLocator);
                MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTable, hash, parentLocators);
                MobileTableRow decoratedMobileMappedRowInstance = decorator
                        .decorateMappedMobileBlockInstance(mobileMappedRowInstance, MobileTableRow.class, elementRegistry, parentInfo);
                mobileMappedRows.put(index, decoratedMobileMappedRowInstance);
            }
        } else {
            MobileTableRow mobileMappedRowInstance = initializer.initMobileTableRow();

            Map<String, MobileBlock> tableCells = new HashMap<>();
            for (String tableColumnName : tableColumnNames) {
                //noinspection unchecked
                Class<? extends MobileBlock> tableCellMappedBlockClass = (Class<? extends MobileBlock>) tableCellFrames
                        .get(tableColumnName)
                        .getElementIdentifier()
                        .getElementType();
                MobileLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                MobileBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
                // ElementRegistry
                List<Method> methods = mobileChildElementMethods.get(tableColumnName);
                MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, methods);
                // ParentLocators (from tableRowInstance)
                Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileMappedRowInstance, parentLocators);
                // Decorate MappedBlock
                MobileBlock decoratedMobileMappedBlockInstance = decorator
                        .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                tableCells.put(tableColumnName, decoratedMobileMappedBlockInstance);
            }
            // ElementRegistry
            MobileElementRegistry elementRegistry = MobileElementRegistry.of(tableCells);
            // ParentLocators (from tableRowInstance)
            Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
            MobileLocatorHolder tableRowRootLocator = tableRowLocator.clone();
            parentLocators.add(tableRowRootLocator);
            MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTable, hash, parentLocators);
            MobileTableRow decoratedMobileMappedRowInstance = decorator
                    .decorateMappedMobileBlockInstance(mobileMappedRowInstance, MobileTableRow.class, elementRegistry, parentInfo);
            mobileMappedRows.put(-1, decoratedMobileMappedRowInstance);
        }

        return mobileMappedRows;
    }


    public Map<Integer, MobileBlock> createMobileTextTableRows(@NotNull MobileTextTable mobileTextTable,
                                                               @NotNull TableSection tableSection,
                                                               @NotNull FilterResult filterResult) {
        Map<Integer, MobileBlock> mobileMappedRows = new HashMap<>();

        String hash = filterResult.getHash();
        Set<Integer> indexes = filterResult.getIndexes();

        Set<String> tableColumnNames = mobileTextTable.getMobileTextTableFrame().getTableColumnNames();
        MobileLocatorHolder tableRowLocator = mobileTextTable.getRequiredLocator(ITEM);
        Map<String, DefaultMobileTextBlock> tableCellFrames = mobileTextTable.getMobileTextTableFrame().getBody();
        Map<String, MobileLocatorHolder> tableCellLocators = mobileTextTable.getMobileTextTableFrame().getBodyLocators();

        if (TableSection.HEADER == tableSection) {
            tableRowLocator = mobileTextTable.getRequiredLocator(TABLE_HEADER);
            tableCellFrames = mobileTextTable.getMobileTextTableFrame().getHeaders();
            tableCellLocators = mobileTextTable.getMobileTextTableFrame().getHeaderLocators();
        }
        if (TableSection.FOOTER == tableSection) {
            tableRowLocator = mobileTextTable.getRequiredLocator(TABLE_FOOTER);
            tableCellFrames = mobileTextTable.getMobileTextTableFrame().getFooters();
            tableCellLocators = mobileTextTable.getMobileTextTableFrame().getFooterLocators();
        }

        Map<String, List<Method>> mobileChildElementMethods = new HashMap<>();
        for (String tableColumnName : tableColumnNames) {
            //noinspection unchecked
            Class<? extends DefaultMobileTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultMobileTextBlock>) tableCellFrames
                    .get(tableColumnName)
                    .getElementIdentifier()
                    .getElementType();
            List<Method> childElementMethods = getMobileChildElementMethods(tableCellMappedBlockClass);
            mobileChildElementMethods.put(tableColumnName, childElementMethods);
        }

        if (TableSection.BODY == tableSection) {
            for (int index : indexes) {
                MobileBlock mobileMappedRowInstance = initializer.initMobileTableRow();

                Map<String, MobileBlock> tableCells = new HashMap<>();
                for (String tableColumnName : tableColumnNames) {
                    //noinspection unchecked
                    Class<? extends DefaultMobileTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultMobileTextBlock>) tableCellFrames
                            .get(tableColumnName)
                            .getElementIdentifier()
                            .getElementType();
                    MobileLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                    DefaultMobileTextBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
                    // ElementRegistry
                    List<Method> methods = mobileChildElementMethods.get(tableColumnName);
                    MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, methods);
                    // ParentLocators (from tableRowInstance)
                    Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                    MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                    parentLocators.add(tableCellRootLocator);
                    MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileMappedRowInstance, parentLocators);
                    // Decorate MappedBlock
                    DefaultMobileTextBlock decoratedMobileMappedBlockInstance = decorator
                            .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                    tableCells.put(tableColumnName, decoratedMobileMappedBlockInstance);
                }
                // ElementRegistry
                MobileElementRegistry elementRegistry = MobileElementRegistry.of(tableCells);
                // ParentLocators (from tableRowInstance)
                Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                MobileLocatorHolder tableRowRootLocator = tableRowLocator.clone().setSingle(true).setIndex(index);
                parentLocators.add(tableRowRootLocator);
                MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTextTable, hash, parentLocators);
                MobileBlock decoratedMobileMappedRowInstance = decorator
                        .decorateMappedMobileBlockInstance(mobileMappedRowInstance, DefaultMobileTextBlock.class, elementRegistry, parentInfo);
                mobileMappedRows.put(index, decoratedMobileMappedRowInstance);
            }
        } else {
            MobileBlock mobileMappedRowInstance = initializer.initMobileTableRow();

            Map<String, MobileBlock> tableCells = new HashMap<>();
            for (String tableColumnName : tableColumnNames) {
                //noinspection unchecked
                Class<? extends DefaultMobileTextBlock> tableCellMappedBlockClass = (Class<? extends DefaultMobileTextBlock>) tableCellFrames
                        .get(tableColumnName)
                        .getElementIdentifier()
                        .getElementType();
                MobileLocatorHolder tableCellLocator = tableCellLocators.get(tableColumnName);

                DefaultMobileTextBlock mobileMappedBlockInstance = initializer.initMappedMobileBlock(tableCellMappedBlockClass);
                // ElementRegistry
                List<Method> methods = mobileChildElementMethods.get(tableColumnName);
                MobileElementRegistry elementRegistry = createMobileChildElementRegistry(mobileMappedBlockInstance, methods);
                // ParentLocators (from tableRowInstance)
                Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
                MobileLocatorHolder tableCellRootLocator = tableCellLocator.clone();
                parentLocators.add(tableCellRootLocator);
                MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileMappedRowInstance, parentLocators);
                // Decorate MappedBlock
                DefaultMobileTextBlock decoratedMobileMappedBlockInstance = decorator
                        .decorateMappedMobileBlockInstance(mobileMappedBlockInstance, tableCellMappedBlockClass, elementRegistry, parentInfo);
                tableCells.put(tableColumnName, decoratedMobileMappedBlockInstance);
            }
            // ElementRegistry
            MobileElementRegistry elementRegistry = MobileElementRegistry.of(tableCells);
            // ParentLocators (from tableRowInstance)
            Deque<MobileLocatorHolder> parentLocators = new ArrayDeque<>();
            MobileLocatorHolder tableRowRootLocator = tableRowLocator.clone();
            parentLocators.add(tableRowRootLocator);
            MobileParentHolder parentInfo = MobileParentHolderForStructuralElement.of(mobileTextTable, hash, parentLocators);
            MobileBlock decoratedMobileMappedRowInstance = decorator
                    .decorateMappedMobileBlockInstance(mobileMappedRowInstance, DefaultMobileTextBlock.class, elementRegistry, parentInfo);
            mobileMappedRows.put(-1, decoratedMobileMappedRowInstance);
        }

        return mobileMappedRows;
    }

    protected @NotNull MobileElementRegistry createMobileChildElementRegistry(@NotNull MobileParentElement parent,
                                                                        @NotNull List<Method> parentMethods) {
        List<MobileChildElement> childElements = parentMethods.stream()
                .map(childElementMethod -> {
                    //noinspection unchecked because all methods filtered by returnType
                    Class<? extends MobileChildElement> childElementClass = (Class<? extends MobileChildElement>) childElementMethod.getReturnType();
                    if (MobileBlock.class.isAssignableFrom(childElementClass)) {
                        return createMobileBlock(parent, childElementMethod);
                    } else {
                        return createMobileChildElement(parent, childElementMethod);
                    }
                }).collect(toList());
        return MobileElementRegistry.of(childElements);
    }

}
