package io.perfeccionista.framework.pagefactory.factory.handlers;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseWebMappedTableColumn;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.perfeccionista.framework.pagefactory.factory.handlers.WebLocatorAnnotationHandler.createOptionalWebLocatorHolder;
import static io.perfeccionista.framework.utils.AnnotationUtils.findAllRepeatableAnnotationsInHierarchy;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public class UseWebMappedTableColumnAnnotationHandler {

    private UseWebMappedTableColumnAnnotationHandler() {
    }

    public static Map<String, TableColumnHolder> createMappedTableColumnHolders(WebChildElement webTable, Method elementMethod) {
        Map<String, TableColumnHolder> tableColumnHolders = new HashMap<>();
        findRepeatableAnnotations(elementMethod, UseWebMappedTableColumn.class)
                .forEach(webMappedTableColumn -> {
                    if (!tableColumnHolders.containsKey(webMappedTableColumn.name())) {
                        TableColumnHolder tableColumnHolder = TableColumnHolder.of(
                                // Тут может не быть локатора
                                createOptionalWebLocatorHolder(webMappedTableColumn.headerLocator()).orElse(null),
                                webMappedTableColumn.headerClass(),
                                createOptionalWebLocatorHolder(webMappedTableColumn.bodyLocator()).orElse(null),
                                webMappedTableColumn.bodyClass(),
                                createOptionalWebLocatorHolder(webMappedTableColumn.footerLocator()).orElse(null),
                                webMappedTableColumn.footerClass());
                        tableColumnHolders.put(webMappedTableColumn.name(), tableColumnHolder);
                    }
                });
        findAllRepeatableAnnotationsInHierarchy(UseWebMappedTableColumn.class, WebChildElement.class, webTable.getClass())
                .forEach(webMappedTableColumn -> {
                    if (!tableColumnHolders.containsKey(webMappedTableColumn.name())) {
                        TableColumnHolder tableColumnHolder = TableColumnHolder.of(
                                createOptionalWebLocatorHolder(webMappedTableColumn.headerLocator()).orElse(null),
                                webMappedTableColumn.headerClass(),
                                createOptionalWebLocatorHolder(webMappedTableColumn.bodyLocator()).orElse(null),
                                webMappedTableColumn.bodyClass(),
                                createOptionalWebLocatorHolder(webMappedTableColumn.footerLocator()).orElse(null),
                                webMappedTableColumn.footerClass());
                        tableColumnHolders.put(webMappedTableColumn.name(), tableColumnHolder);
                    }
                });
        return tableColumnHolders;
    }

}
