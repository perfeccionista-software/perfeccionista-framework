package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.dispatcher.DeviceType;
import io.perfeccionista.framework.pagefactory.elements.DefaultMobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileTableFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedMobileTextTableColumn;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.ANDROID;
import static io.perfeccionista.framework.pagefactory.dispatcher.DeviceType.IOS;
import static io.perfeccionista.framework.pagefactory.factory.handlers.MobileLocatorAnnotationHandler.createOptionalMobileLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;

public class UseMappedMobileTextTableColumnAnnotationHandler {

    private UseMappedMobileTextTableColumnAnnotationHandler() {
    }

    public static @NotNull MobileTableFrame<DefaultMobileTextBlock> createMobileTextTableFrame(@NotNull DeviceType deviceType,
                                                                                               @NotNull MobileTextTable mobileTextTable,
                                                                                               @NotNull Method elementMethod,
                                                                                               @NotNull MobilePageFactory mobilePageFactory) {
        Map<String, MobileLocatorHolder> headerLocators = new HashMap<>();
        Map<String, MobileLocatorHolder> bodyLocators = new HashMap<>();
        Map<String, MobileLocatorHolder> footerLocators = new HashMap<>();
        Map<String, DefaultMobileTextBlock> headers = new HashMap<>();
        Map<String, DefaultMobileTextBlock> body = new HashMap<>();
        Map<String, DefaultMobileTextBlock> footers = new HashMap<>();
        Set<String> processedColumns = new HashSet<>();

        findRepeatableAnnotations(elementMethod, UseMappedMobileTextTableColumn.class)
                .forEach(mobileMappedTableColumn -> {
                    String processedColumnName = mobileMappedTableColumn.name();
                    if (!processedColumns.contains(processedColumnName)) {

                        MobileLocatorHolder headerLocator = null;
                        MobileLocatorHolder bodyLocator = null;
                        MobileLocatorHolder footerLocator = null;

                        if (ANDROID == deviceType) {
                            headerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.androidHeaderLocator())
                                    .orElse(null);
                            bodyLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.androidBodyLocator())
                                    .orElse(null);
                            footerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.androidFooterLocator())
                                    .orElse(null);
                        } else if (IOS == deviceType) {
                            headerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.iosHeaderLocator())
                                    .orElse(null);
                            bodyLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.iosBodyLocator())
                                    .orElse(null);
                            footerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.iosFooterLocator())
                                    .orElse(null);
                        }

                        headerLocators.put(processedColumnName, headerLocator);
                        bodyLocators.put(processedColumnName, bodyLocator);
                        footerLocators.put(processedColumnName, footerLocator);

                        if (Objects.nonNull(headerLocator)) {
                            DefaultMobileTextBlock headerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTextTable, mobileMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            DefaultMobileTextBlock bodyColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTextTable, mobileMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            DefaultMobileTextBlock footerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTextTable, mobileMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        findAllRepeatableAnnotationsInHierarchy(UseMappedMobileTextTableColumn.class, MobileChildElement.class, mobileTextTable.getClass())
                .forEach(mobileMappedTableColumn -> {
                    String processedColumnName = mobileMappedTableColumn.name();
                    if (!processedColumns.contains(processedColumnName)) {

                        MobileLocatorHolder headerLocator = null;
                        MobileLocatorHolder bodyLocator = null;
                        MobileLocatorHolder footerLocator = null;

                        if (ANDROID == deviceType) {
                            headerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.androidHeaderLocator())
                                    .orElse(null);
                            bodyLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.androidBodyLocator())
                                    .orElse(null);
                            footerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.androidFooterLocator())
                                    .orElse(null);
                        } else if (IOS == deviceType) {
                            headerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.iosHeaderLocator())
                                    .orElse(null);
                            bodyLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.iosBodyLocator())
                                    .orElse(null);
                            footerLocator = createOptionalMobileLocatorHolder(mobileMappedTableColumn.iosFooterLocator())
                                    .orElse(null);
                        }

                        headerLocators.put(processedColumnName, headerLocator);
                        bodyLocators.put(processedColumnName, bodyLocator);
                        footerLocators.put(processedColumnName, footerLocator);

                        if (Objects.nonNull(headerLocator)) {
                            DefaultMobileTextBlock headerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTextTable, mobileMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            DefaultMobileTextBlock bodyColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTextTable, mobileMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            DefaultMobileTextBlock footerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTextTable, mobileMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        return new MobileTableFrame<>(mobileTextTable, processedColumns, headerLocators, bodyLocators, footerLocators, headers, body, footers);
    }

}
