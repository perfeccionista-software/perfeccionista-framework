package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebSimpleUnorderedList;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebStringBlockFilter;
import io.perfeccionista.framework.pagefactory.js.GetText;
import io.perfeccionista.framework.pagefactory.operations.JsOperation;
import org.junit.platform.commons.util.ReflectionUtils;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;


public abstract class WebSimpleUnorderedListImpl extends AbstractWebChildElement implements WebSimpleUnorderedList {

    @Override
    public MultipleResult<String> getValues() {
        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
        JsOperation<MultipleResult<String>> operation = JsOperation.multiple(getLocatorChainTo(LI), getTextFunction);
        return getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
    }

    @Override
    public MultipleResult<String> getValues(WebStringBlockFilter filter) {
        return filter.multipleResult(this);
    }

    @Override
    public void scrollToElement(WebStringBlockFilter filter) {
        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
    }

    @Override
    public int size() {
        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
    }

}
