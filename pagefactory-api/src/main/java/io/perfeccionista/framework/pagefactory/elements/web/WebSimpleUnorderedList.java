package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsStringBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface WebSimpleUnorderedList extends WebChildElement,
        ScrollToElementAvailable<JsStringBlockFilter>, SizeAvailable {

    OperationResult<MultipleResult<String>> getValues();

    OperationResult<MultipleResult<String>> getValues(JsStringBlockFilter filter);

}