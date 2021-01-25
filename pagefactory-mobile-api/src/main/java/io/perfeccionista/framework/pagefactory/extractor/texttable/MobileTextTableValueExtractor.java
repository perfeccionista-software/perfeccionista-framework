package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.extractor.MobileValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilter;

public interface MobileTextTableValueExtractor<T> extends MobileValueExtractor<MobileTextTable, MobileTextTableFilter, T> {

    MobileTextTableValueExtractor<T> fromHeader();

    MobileTextTableValueExtractor<T> fromFooter();

}

