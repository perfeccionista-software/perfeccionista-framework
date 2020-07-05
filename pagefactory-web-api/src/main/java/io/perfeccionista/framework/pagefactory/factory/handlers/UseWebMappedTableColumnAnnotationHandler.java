package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumn;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createWebLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public class UseWebMappedTableColumnAnnotationHandler {

    public static Map<String, TableColumnHolder> createMappedTableColumnHolders(WebTable webTable, Method elementMethod) {
        Map<String, TableColumnHolder> tableColumnHolders = new HashMap<>();
        findRepeatableAnnotations(elementMethod, UseWebMappedTableColumn.class)
                .forEach(webMappedTableColumn -> {
                    if (!tableColumnHolders.containsKey(webMappedTableColumn.name())) {
                        TableColumnHolder tableColumnHolder = TableColumnHolder.of(
                                createWebLocatorHolder(webMappedTableColumn.headerLocator()),
                                webMappedTableColumn.headerClass(),
                                createWebLocatorHolder(webMappedTableColumn.bodyLocator()),
                                webMappedTableColumn.bodyClass(),
                                createWebLocatorHolder(webMappedTableColumn.footerLocator()),
                                webMappedTableColumn.footerClass());
                        tableColumnHolders.put(webMappedTableColumn.name(), tableColumnHolder);
                    }
                });
        findAllRepeatableAnnotationsInHierarchy(UseWebMappedTableColumn.class, WebTable.class, webTable.getClass())
                .forEach(webMappedTableColumn -> {
                    if (!tableColumnHolders.containsKey(webMappedTableColumn.name())) {
                        TableColumnHolder tableColumnHolder = TableColumnHolder.of(
                                createWebLocatorHolder(webMappedTableColumn.headerLocator()),
                                webMappedTableColumn.headerClass(),
                                createWebLocatorHolder(webMappedTableColumn.bodyLocator()),
                                webMappedTableColumn.bodyClass(),
                                createWebLocatorHolder(webMappedTableColumn.footerLocator()),
                                webMappedTableColumn.footerClass());
                        tableColumnHolders.put(webMappedTableColumn.name(), tableColumnHolder);
                    }
                });
        return tableColumnHolders;
    }

}
