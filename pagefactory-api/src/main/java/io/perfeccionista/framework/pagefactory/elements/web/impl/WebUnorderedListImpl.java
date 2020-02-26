package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.web.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsSize;
import io.perfeccionista.framework.pagefactory.itemextractor.JsWebBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@Locator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebUnorderedListImpl extends AbstractWebChildElement implements WebUnorderedList {

    protected Class<? extends WebBlock> blockMapper;

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebBlockValueExtractor<V> extractor) {
        return OperationResult.execute(() -> extractor.extractMultipleValues(this, Set.of()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebBlockValueExtractor<V> extractor, JsBlockFilter filter) {
        return OperationResult.execute(() -> {
            MultipleResult<Integer> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result.getItems().keySet());
        });
    }

    @Override
    public OperationResult<Void> scrollToElement(JsBlockFilter filter) {
        return getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public OperationResult<Integer> size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
    }

    protected Class<? extends WebBlock> getBlockMapper() {
        return blockMapper;
    }

}
