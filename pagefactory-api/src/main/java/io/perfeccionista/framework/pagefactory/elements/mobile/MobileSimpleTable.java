package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.perfeccionista.framework.pagefactory.elements.mapping.MobileColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SwipeToElementAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.appium.AppiumStringTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Optional;

public interface MobileSimpleTable extends MobileChildElement,
        SwipeToElementAvailable<AppiumStringTableRowFilter>, SizeAvailable {

    Optional<MobileColumnMapper> getColumnMapper(String columnName);

    OperationResult<String> getHeaderValue(String columnName);

    OperationResult<MultipleResult<String>> getValues(String columnName);

    OperationResult<MultipleResult<String>> getValues(String columnName, AppiumStringTableRowFilter filter);

    OperationResult<String> getFooterValue(String columnName);

}

