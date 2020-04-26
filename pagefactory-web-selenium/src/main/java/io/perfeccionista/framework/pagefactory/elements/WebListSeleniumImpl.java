package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.actions.JsSize;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterSeleniumImpl;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_ELEMENT_METHOD;

@WebLocator(component = LI, xpath = ".//li", single = false)
@ElementAction(name = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@ElementAction(name = SIZE_METHOD, implementation = JsSize.class)
public abstract class WebListSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebList {

    @Override
    public <V> MultipleResult<V> extractAll(WebListBlockValueExtractor<V> extractor) {
        return new WebListFilterSeleniumImpl()
                .filter(this)
                .extractAll(extractor);
    }

//    protected Class<? extends WebBlock> blockMapper;
//
//    @Override
//    public <V> SingleResult<V> getValue(WebListBlockValueExtractor<V> extractor, WebListFilter filter) {
//        SingleResult<Integer> result = filter.singleResult(this);
//        return extractor.extractSingleValue(this, result);
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebListBlockValueExtractor<V> extractor) {
//        return extractor.extractMultipleValues(this, MultipleResult.empty());
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebListBlockValueExtractor<V> extractor, WebListFilter filter) {
//        MultipleResult<Integer> result = filter.multipleResult(this);
//        return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
//    }
//
//    @Override
//    public void scrollToElement(WebListFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public int size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
//    }
//
//    @Override
//    public Class<? extends WebBlock> getBlockMapper() {
//        return blockMapper;
//    }

}
