package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.dispatcher.DeviceType;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileTableFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedMobileTableColumn;
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
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public class UseMappedMobileTableColumnAnnotationHandler {

    private UseMappedMobileTableColumnAnnotationHandler() {
    }

    public static @NotNull MobileTableFrame<MobileBlock> createMobileTableFrame(@NotNull DeviceType deviceType,
                                                                                @NotNull MobileTable mobileTable,
                                                                                @NotNull Method elementMethod,
                                                                                @NotNull MobilePageFactory mobilePageFactory) {
        Map<String, MobileLocatorHolder> headerLocators = new HashMap<>();
        Map<String, MobileLocatorHolder> bodyLocators = new HashMap<>();
        Map<String, MobileLocatorHolder> footerLocators = new HashMap<>();
        Map<String, MobileBlock> headers = new HashMap<>();
        Map<String, MobileBlock> body = new HashMap<>();
        Map<String, MobileBlock> footers = new HashMap<>();
        Set<String> processedColumns = new HashSet<>();

        findRepeatableAnnotations(elementMethod, UseMappedMobileTableColumn.class)
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
                            MobileBlock headerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTable, mobileMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            MobileBlock bodyColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTable, mobileMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            MobileBlock footerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTable, mobileMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        findAllRepeatableAnnotationsInHierarchy(UseMappedMobileTableColumn.class, MobileChildElement.class, mobileTable.getClass())
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
                            MobileBlock headerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTable, mobileMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            MobileBlock bodyColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTable, mobileMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            MobileBlock footerColumnBlock = mobilePageFactory
                                    .createMappedMobileBlock(mobileTable, mobileMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        return new MobileTableFrame<>(mobileTable, processedColumns, headerLocators, bodyLocators, footerLocators, headers, body, footers);
    }

}
