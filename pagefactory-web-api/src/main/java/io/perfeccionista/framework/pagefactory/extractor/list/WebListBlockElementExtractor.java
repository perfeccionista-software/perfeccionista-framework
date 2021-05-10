package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WebListBlockElementExtractor<T extends WebChildElement> implements WebListBlockValueExtractor<T> {

    private final String elementPath;
    private final T elementFrame;
    private final Class<T> returnType;

    public WebListBlockElementExtractor(@NotNull String elementPath, @NotNull Class<T> returnType) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.returnType = returnType;
    }

    public WebListBlockElementExtractor(@NotNull T elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.returnType = null;
    }

    @Override
    public Map<Integer, T> extractValues(@NotNull WebListFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        WebList element = filter.getElement();

        Map<Integer, T> extractedElements = new HashMap<>();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        webPageFactory.createWebListBlocks(element, filterResult)
                .forEach((index, webMappedBlock) -> {
                    T elementToExtract;
                    if (elementPath != null) {
                        elementToExtract = webMappedBlock.getElementRegistry()
                                .getRequiredElementByPath(elementPath, returnType);
                    } else {
                        //noinspection unchecked
                        elementToExtract = (T) webMappedBlock.getElementRegistry()
                                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod());
                    }
                    extractedElements.put(index, elementToExtract);
                });
        return extractedElements;
    }

}
