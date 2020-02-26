package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.locators.Locator;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebSimpleUnorderedList;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsScrollToStringBlockElement;
import io.perfeccionista.framework.pagefactory.elements.web.methods.JsSize;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsStringBlockFilter;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

@Locator(component = LI, xpath = ".//li", single = false)
@ElementMethod(type = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToStringBlockElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = JsSize.class)
public class WebSimpleUnorderedListImpl extends AbstractWebChildElement implements WebSimpleUnorderedList {

    @Override
    public OperationResult<MultipleResult<String>> getValues() {
        JsOperation<MultipleResult<String>> operation = JsOperation.multiple(getLocatorChainTo(LI), GetText.class);
        return OperationResult.execute(() -> getDriverInstance().getDriverOperationExecutor().executeOperation(operation));
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(JsStringBlockFilter filter) {
        return OperationResult.execute(() -> filter.multipleResult(this));
    }

    @Override
    public OperationResult<Void> scrollToElement(JsStringBlockFilter filter) {
        return getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public OperationResult<Integer> size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
    }

}
