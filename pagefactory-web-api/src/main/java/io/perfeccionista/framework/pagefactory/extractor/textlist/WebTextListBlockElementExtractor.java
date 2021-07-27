package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WebTextListBlockElementExtractor implements WebTextListBlockValueExtractor<WebLink> {

    @Override
    public Map<Integer, WebLink> extractValues(@NotNull WebTextBlockFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        WebTextList element = filter.getElement();
        WebLink elementToExtractFrame = element.getBlockFrame().getMappedBlockFrame().textLink();

        Map<Integer, WebLink> extractedElements = new HashMap<>();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        webPageFactory.createWebTextListBlocks(element, filterResult)
                .forEach((index, webMappedBlock) -> {
                    WebLink elementToExtract = webMappedBlock.getElementRegistry()
                            .getRequiredElementByMethod(elementToExtractFrame.getElementIdentifier().getElementMethod(), WebLink.class);
                    extractedElements.put(index, elementToExtract);
                });
        return extractedElements;
    }

}
