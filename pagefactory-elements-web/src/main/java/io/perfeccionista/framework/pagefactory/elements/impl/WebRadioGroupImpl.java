package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.methods.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSize;
import io.perfeccionista.framework.pagefactory.extractor.JsWebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.JsRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

// Сделать реализацию через массив блоков в которых один радио-баттон и индекс хранить в нем
@WebLocator(component = RADIO, xpath = ".//input", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebRadioGroupImpl extends AbstractWebChildElement implements WebRadioGroup {

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(JsWebRadioButtonValueExtractor<V> extractor, JsRadioButtonFilter filter) {
        return OperationResult.of(() -> {
            MultipleResult<Integer> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
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
