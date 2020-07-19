package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.factory.proxy.mock.WebElementMock;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.WebMultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WebListBlockElementExtractor<T extends WebChildElement> implements WebListBlockValueExtractor<T> {

    private final T elementMock;
    private final String elementName;
    private final Class<T> returnType;

    public WebListBlockElementExtractor(@NotNull T elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.returnType = null;
    }

    public WebListBlockElementExtractor(@NotNull String elementName, @NotNull Class<T> returnType) {
        this.elementName = elementName;
        this.elementMock = null;
        this.returnType = returnType;
    }

    @Override
    public MultipleResult<T> extractValues(WebList element, WebListFilter filter) {
        WebFilterResult filterResult = filter.getResult();
        if (filterResult.getIndexes().isEmpty()) {
            return new WebMultipleResult<>();
        }
        Map<Integer, T> extractedElements = new HashMap<>();
        WebPageFactory webPageFactory = element.getEnvironment().getService(WebPageService.class).getWebPageFactory();
        Map<Integer, WebMappedBlock> webMappedBlocks = webPageFactory.createWebListBlocks(element, filterResult);
        // В зависимости от того, что указано при создании достаем нужные элементы или по имени или по цепочке методов.
        for (Entry<Integer, WebMappedBlock> webMappedBlockEntry : webMappedBlocks.entrySet()) {
            if (elementMock == null) {
                T elementToExtract = webMappedBlockEntry.getValue()
                        .getElementRegistry()
                        .getElementByPath(elementName, returnType)
                        // TODO: Exception
                        .orElseThrow();
                extractedElements.put(webMappedBlockEntry.getKey(), elementToExtract);
            } else {
                WebElementMock mock = (WebElementMock) elementMock;
                T elementToExtract = (T) webMappedBlockEntry.getValue()
                        .getElementRegistry()
                        .getElementByMethod(mock.getParentMethod(), mock.getItemClass())
                        // TODO: Exception
                        .orElseThrow();
                extractedElements.put(webMappedBlockEntry.getKey(), elementToExtract);
            }
        }
        return new WebMultipleResult<>(extractedElements);
    }



}
