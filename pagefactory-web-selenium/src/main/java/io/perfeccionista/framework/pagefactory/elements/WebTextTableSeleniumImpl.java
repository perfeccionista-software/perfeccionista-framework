package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollToTextTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.actions.JsSize;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterSeleniumImpl;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_ELEMENT_METHOD;

@WebLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@WebLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@WebLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
@ElementAction(name = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToTextTableRowElement.class)
@ElementAction(name = SIZE_METHOD, implementation = JsSize.class)
public abstract class WebTextTableSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebTextTable {

    @Override
    public SingleResult<String> extractHeader(String columnName) {
        return new WebTextTableFilterSeleniumImpl().filter(this)
                .extractHeader(columnName);
    }

    @Override
    public MultipleResult<String> extractAllRows(String columnName) {
        return new WebTextTableFilterSeleniumImpl().filter(this)
                .extractAllRows(columnName);
    }

    @Override
    public SingleResult<String> extractFooter(String columnName) {
        return new WebTextTableFilterSeleniumImpl().filter(this)
                .extractFooter(columnName);
    }

//
//    protected Map<String, WebColumnMapper> columnMappers;
//
//    @Override
//    public String getHeaderValue(String columnName) {
//        return new WebSimpleTableRowValueExtractor(columnName).extractSingleHeaderValue(this).get();
//    }
//
//    @Override
//    public MultipleResult<String> getValues(String columnName) {
//        return new WebSimpleTableRowValueExtractor(columnName).extractMultipleValues(this, MultipleResult.empty());
//    }
//
//    @Override
//    public MultipleResult<String> getValues(String columnName, WebStringTableRowFilter filter) {
//        MultipleResult<Integer> result = filter.multipleResult(this);
//        return new WebSimpleTableRowValueExtractor(columnName).setHash(result.getElementHash())
//                .extractMultipleValues(this, result);
//    }
//
//    @Override
//    public String getFooterValue(String columnName) {
//        return new WebSimpleTableRowValueExtractor(columnName).extractSingleFooterValue(this).get();
//    }
//
//    @Override
//    public void scrollToElement(WebStringTableRowFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
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
