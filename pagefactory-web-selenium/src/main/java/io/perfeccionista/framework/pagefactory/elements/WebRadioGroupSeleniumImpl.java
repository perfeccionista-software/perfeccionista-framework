package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.actions.JsSize;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterSeleniumImpl;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

@WebLocator(component = RADIO, xpath = ".//input", single = false)
@ElementAction(name = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementAction(name = SIZE_METHOD, implementation = JsSize.class)
public abstract class WebRadioGroupSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebRadioGroup {

    @Override
    public  <V> MultipleResult<V> extractAll(WebRadioButtonValueExtractor<V> extractor) {
        return new WebRadioButtonFilterSeleniumImpl()
                .filter(this)
                .extractAll(extractor);
    }

    @Override
    public WebRadioButton getSelected() {
        return new WebRadioButtonFilterSeleniumImpl().add(selected())
                .filter(this)
                .extractOne(element())
                .shouldHaveNotNull().get();
    }

    @Override
    public WebRadioButton getByLabel(StringValue label) {
        return with(WebConditions.containsLabel(label))
                .filter(this)
                .extractOne(element())
                .shouldHaveNotNull().get();
    }

    @Override
    public WebRadioButton getByIndex(NumberValue<Integer> index) {
        return with(radioButtonIndex(index))
                .filter(this)
                .extractOne(element())
                .shouldHaveNotNull().get();
    }

//
//    @Override
//    public <V> MultipleResult<V> getValues(WebRadioButtonValueExtractor<V> extractor) {
//        return extractor.extractMultipleValues(this, MultipleResult.empty());
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebRadioButtonValueExtractor<V> extractor, WebRadioButtonFilter filter) {
//        MultipleResult<Integer> result = filter.multipleResult(this);
//        return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
//    }
//
//    @Override
//    public void scrollToElement(WebRadioButtonFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public int size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, RADIO);
//    }

}
