package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.SimpleTable;
import io.perfeccionista.framework.pagefactory.elements.mapping.ColumnMapper;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsStringTableRowFilter;

import java.util.Optional;

public interface WebSimpleTable extends SimpleTable<JsStringTableRowFilter>, WebChildElement {

    Optional<ColumnMapper> getColumnMapper(String columnName);

}
