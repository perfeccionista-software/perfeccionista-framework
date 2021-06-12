package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumn;
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
import static io.perfeccionista.framework.utils.AnnotationUtils.findRepeatableAnnotations;

public class UseMappedWebTableColumnAnnotationHandler {

    private UseMappedWebTableColumnAnnotationHandler() {
    }

    public static @NotNull WebTableFrame<WebBlock> createWebTableFrame(@NotNull WebTable webTable,
                                                                       @NotNull Method elementMethod,
                                                                       @NotNull WebPageFactory webPageFactory) {
        Map<String, WebLocatorHolder> headerLocators = new HashMap<>();
        Map<String, WebLocatorHolder> bodyLocators = new HashMap<>();
        Map<String, WebLocatorHolder> footerLocators = new HashMap<>();
        Map<String, WebBlock> headers = new HashMap<>();
        Map<String, WebBlock> body = new HashMap<>();
        Map<String, WebBlock> footers = new HashMap<>();
        Set<String> processedColumns = new HashSet<>();

        findRepeatableAnnotations(elementMethod, UseMappedWebTableColumn.class)
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
                            WebBlock headerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTable, webMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            WebBlock bodyColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTable, webMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            WebBlock footerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTable, webMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        findAllRepeatableAnnotationsInHierarchy(UseMappedWebTableColumn.class, WebChildElement.class, webTable.getClass())
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
                            WebBlock headerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTable, webMappedTableColumn.headerClass());
                            headers.put(processedColumnName, headerColumnBlock);
                        }
                        if (Objects.nonNull(bodyLocator)) {
                            WebBlock bodyColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTable, webMappedTableColumn.bodyClass());
                            body.put(processedColumnName, bodyColumnBlock);
                        }
                        if (Objects.nonNull(footerLocator)) {
                            WebBlock footerColumnBlock = webPageFactory
                                    .createMappedWebBlock(webTable, webMappedTableColumn.footerClass());
                            footers.put(processedColumnName, footerColumnBlock);
                        }

                        processedColumns.add(processedColumnName);
                    }
                });

        return new WebTableFrame<>(webTable, processedColumns, headerLocators, bodyLocators, footerLocators, headers, body, footers);
    }

}
