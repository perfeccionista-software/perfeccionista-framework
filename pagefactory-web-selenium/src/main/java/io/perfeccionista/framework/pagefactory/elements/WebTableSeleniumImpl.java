package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollToTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.actions.JsSize;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterSeleniumImpl;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_ELEMENT_METHOD;

@WebLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@WebLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@WebLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
@ElementAction(name = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToTableRowElement.class)
@ElementAction(name = SIZE_METHOD, implementation = JsSize.class)
public abstract class WebTableSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebTable {

    @Override
    public  <V> SingleResult<V> extractHeader(WebTableCellValueExtractor<V> extractor) {
        return new WebTableFilterSeleniumImpl().filter(this)
                .extractHeader(extractor);
    }

    @Override
    public  <V> MultipleResult<V> extractAllRows(WebTableCellValueExtractor<V> extractor) {
        return new WebTableFilterSeleniumImpl().filter(this)
                .extractAllRows(extractor);
    }

    @Override
    public  <V> SingleResult<V> extractFooter(WebTableCellValueExtractor<V> extractor) {
        return new WebTableFilterSeleniumImpl().filter(this)
                .extractFooter(extractor);
    }

//    protected Map<String, WebColumnMapper> columnMappers;
//
//    @Override
//    public <V> V getHeaderValue(WebTableCellValueExtractor<V> extractor) {
//        return extractor.extractSingleHeaderValue(this).get();
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebTableCellValueExtractor<V> extractor) {
//        return extractor.extractMultipleValues(this, MultipleResult.empty());
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebTableCellValueExtractor<V> extractor, WebTableFilter filter) {
//        MultipleResult<Integer> result = filter.multipleResult(this);
//        return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
//    }
//
//    @Override
//    public <V> V getFooterValue(WebTableCellValueExtractor<V> extractor) {
//        return extractor.extractSingleFooterValue(this).get();
//    }
//
//
//    @Override
//    public void scrollToElement(WebTableFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//
//    @Override
//    public int size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, TBODY_ROW);
//    }
//
//    @Override
//    public Optional<WebColumnMapper> getColumnMapper(String columnName) {
//        return Optional.ofNullable(columnMappers.get(columnName));
//    }

}
