package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public interface AppiumTableRowValueExtractor<V> extends AppiumValueExtractor<MobileTable, V> {

    AppiumTableRowValueExtractor<V> setHash(String hash);

    SingleResult<V> extractSingleHeaderValue(MobileTable element);

    SingleResult<V> extractSingleFooterValue(MobileTable element);

}
