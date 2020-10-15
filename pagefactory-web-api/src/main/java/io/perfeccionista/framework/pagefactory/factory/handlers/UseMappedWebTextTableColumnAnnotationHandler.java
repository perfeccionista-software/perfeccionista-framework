package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTextTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createOptionalWebLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public class UseMappedWebTextTableColumnAnnotationHandler {

    private UseMappedWebTextTableColumnAnnotationHandler() {
    }

    public static @NotNull WebTableFrame<DefaultWebTextBlock> createWebTextTableFrame(@NotNull WebTextTable webTextTable,
                                                                                      @NotNull Method elementMethod,
                                                                                      @NotNull WebPageFactory webPageFactory) {
        Map<String, WebLocatorHolder> headerLocators = new HashMap<>();
        Map<String, WebLocatorHolder> bodyLocators = new HashMap<>();
        Map<String, WebLocatorHolder> footerLocators = new HashMap<>();
        Map<String, DefaultWebTextBlock> headers = new HashMap<>();
        Map<String, DefaultWebTextBlock> body = new HashMap<>();
        Map<String, DefaultWebTextBlock> footers = new HashMap<>();
        Set<String> processedColumns = new HashSet<>();

        findRepeatableAnnotations(elementMethod, UseMappedWebTextTableColumn.class)
                .forEach(webMappedTableColumn -> {
                    String processedColumnName = webMappedTableColumn.name();
                    if (!processedColumns.contains(processedColumnName)) {
                        WebLocatorHolder headerLocator = createOptionalWebLocatorHolder(webMappedTableColumn.headerLocator())
                                .orElse(null);
                        headerLocators.put(processedColumnName, headerLocator);
                        WebLocatorHolder bodyLocator = createOptionalWebLocatorHolder(webMappedTableColumn.bodyLocator())
                                .orElse(null);
                        bodyLocators.put(processedColumnName, bodyLocator);
                        WebLocatorHolder footerLocator = createOptionalWebLocatorHolder(webMappedTableColumn.footerLocator())
                                .orElse(null);
                        footerLocators.put(processedColumnName, footerLocator);

                        if (Objects.nonNull(headerLocator)) {
                            DefaultWebTextBlock headerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTextTable, webMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            DefaultWebTextBlock bodyColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTextTable, webMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            DefaultWebTextBlock footerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTextTable, webMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        findAllRepeatableAnnotationsInHierarchy(UseMappedWebTextTableColumn.class, WebChildElement.class, webTextTable.getClass())
                .forEach(webMappedTableColumn -> {
                    String processedColumnName = webMappedTableColumn.name();
                    if (!processedColumns.contains(processedColumnName)) {
                        WebLocatorHolder headerLocator = createOptionalWebLocatorHolder(webMappedTableColumn.headerLocator())
                                .orElse(null);
                        headerLocators.put(processedColumnName, headerLocator);
                        WebLocatorHolder bodyLocator = createOptionalWebLocatorHolder(webMappedTableColumn.bodyLocator())
                                .orElse(null);
                        bodyLocators.put(processedColumnName, bodyLocator);
                        WebLocatorHolder footerLocator = createOptionalWebLocatorHolder(webMappedTableColumn.footerLocator())
                                .orElse(null);
                        footerLocators.put(processedColumnName, footerLocator);

                        if (Objects.nonNull(headerLocator)) {
                            DefaultWebTextBlock headerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTextTable, webMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            DefaultWebTextBlock bodyColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTextTable, webMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            DefaultWebTextBlock footerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTextTable, webMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        return new WebTableFrame<>(webTextTable, processedColumns, headerLocators, bodyLocators, footerLocators, headers, body, footers);
    }

}
