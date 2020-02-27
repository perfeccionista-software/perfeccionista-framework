package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.mapping.WebColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsStringTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

public interface WebSimpleTable extends WebChildElement,
        ScrollToElementAvailable<JsStringTableRowFilter>, SizeAvailable {

    Optional<WebColumnMapper> getColumnMapper(String columnName);

    OperationResult<String> getHeaderValue(String columnName);

    OperationResult<MultipleResult<String>> getValues(String columnName);

    OperationResult<MultipleResult<String>> getValues(String columnName, JsStringTableRowFilter filter);

    OperationResult<String> getFooterValue(String columnName);

}
