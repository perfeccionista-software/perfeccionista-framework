package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.Table;
import io.perfeccionista.framework.pagefactory.elements.mapping.ColumnMapper;
import io.perfeccionista.framework.pagefactory.itemfilter.js.JsTableRowFilter;

import java.util.Optional;

public interface WebTable extends Table<JsTableRowFilter>, WebChildElement {

    Optional<ColumnMapper> getColumnMapper(String columnName);

}
