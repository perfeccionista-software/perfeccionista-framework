package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.mapping.MobileColumnMapper;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.operation.OperationResult;

import java.util.Optional;

public interface MobileSimpleTable extends MobileChildElement
//        , SizeAvailable
{

    Optional<MobileColumnMapper> getColumnMapper(String columnName);

    OperationResult<String> getHeaderValue(String columnName);

    OperationResult<MultipleResult<String>> getValues(String columnName);

//    OperationResult<MultipleResult<String>> getValues(String columnName, AppiumStringTableRowFilter filter);

    OperationResult<String> getFooterValue(String columnName);

}

