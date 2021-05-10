package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.extractor.MobileValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilter;

public interface MobileTableValueExtractor<T> extends MobileValueExtractor<MobileTable, MobileTableFilter, T> {

    MobileTableValueExtractor<T> fromHeader();

    MobileTableValueExtractor<T> fromFooter();

}

