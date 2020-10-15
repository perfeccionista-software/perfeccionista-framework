package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WebTextListBlockElementExtractor implements WebTextListBlockValueExtractor<WebLink> {

    @Override
    public Map<Integer, WebLink> extractValues(@NotNull WebTextListFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        WebTextList element = filter.getElement();
        WebLink elementToExtractFrame = element.getWebTextListFrame().getMappedBlockFrame().textLink();

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
