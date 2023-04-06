package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WebItemElementExtractor<R extends WebChildElement, T extends WebBlock<?>> implements WebItemValueExtractor<R, T> {

    private final String elementPath;
    private final R elementFrame;
    private final Class<R> returnType;

    public WebItemElementExtractor(@NotNull String elementPath, @NotNull Class<R> returnType) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.returnType = returnType;
    }

    public WebItemElementExtractor(@NotNull R elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.returnType = null;
    }

    @Override
    public Map<Integer, R> extractValues(@NotNull WebListFilter<T> filter) {
        FilterResult filterResult = filter.getFilterResult();
        WebList<T> element = filter.getElement();

        Map<Integer, R> extractedElements = new HashMap<>();

        WebPageFactory webPageFactory = Environment.getCurrent()
                .getService(WebPageService.class)
                .getWebPageFactory();

        webPageFactory.createWebListBlocks(element, filterResult)
                .forEach((index, webMappedBlock) -> {
                    R elementToExtract;
                    if (elementPath != null) {
                        elementToExtract = webMappedBlock.getElementRegistry()
                                .getRequiredElementByPath(elementPath, returnType);
                    } else {
                        //noinspection unchecked
                        elementToExtract = (R) webMappedBlock.getElementRegistry()
                                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod());
                    }
                    extractedElements.put(index, elementToExtract);
                });
        return extractedElements;
    }

}
