package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsSize;
import io.perfeccionista.framework.pagefactory.itemextractor.js.JsWebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

// Сделать реализацию через массив блоков в которых один радио-баттон и индекс хранить в нем
@Locator(component = RADIO, xpath = ".//input", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebRadioGroupImpl extends AbstractWebChildElement implements WebRadioGroup {

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor) {
        return OperationResult.execute(() -> extractor.extractMultipleValues(this, Set.of()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor, JsRadioButtonFilter filter) {
        return OperationResult.execute(() -> {
            MultipleResult<Integer> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result.getItems().keySet());
        });
    }

    @Override
    public OperationResult<Void> scrollToElement(JsRadioButtonFilter filter) {
        return getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public OperationResult<Integer> size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, RADIO);
    }

}
