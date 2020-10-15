package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WebTableCellElementExtractor<T extends WebChildElement> implements WebTableCellValueExtractor<T> {

    private TableSection section = TableSection.BODY;

    private final String columnName;
    private final String elementPath;
    private final T elementFrame;
    private final Class<T> returnType;

    public WebTableCellElementExtractor(@NotNull String columnName, @NotNull T elementFrame) {
        this.columnName = columnName;
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.returnType = null;
    }

    public WebTableCellElementExtractor(@NotNull String columnName, @NotNull String elementPath, @NotNull Class<T> returnType) {
        this.columnName = columnName;
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.returnType = returnType;
    }

    @Override
    public Map<Integer, T> extractValues(@NotNull WebTableFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        WebTable element = filter.getElement();

        Map<Integer, T> extractedElements = new HashMap<>();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        webPageFactory
                .createWebTableCells(element, columnName, section, filterResult)
                .forEach((index, webMappedBlock) -> {
                    T elementToExtract;
                    if (elementPath != null) {
                        elementToExtract = webMappedBlock.getElementRegistry()
                                .getRequiredElementByPath(elementPath, returnType);
                    } else {
                        elementToExtract = (T) webMappedBlock.getElementRegistry()
                                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod());
                    }
                    extractedElements.put(index, elementToExtract);
                });

        return extractedElements;
    }

    @Override
    public WebTableCellElementExtractor<T> fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableCellElementExtractor<T> fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
