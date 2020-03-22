package io.perfeccionista.framework.pagefactory.elements.impl;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileSimpleTable;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSize;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSwipeToStringTableRowElement;
import io.perfeccionista.framework.pagefactory.extractor.AppiumSimpleTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumStringTableRowFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.THEAD_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SWIPE_TO_ELEMENT_METHOD;

@AndroidLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@AndroidLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@AndroidLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
@IosLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@IosLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@IosLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
@ElementMethod(type = SWIPE_TO_ELEMENT_METHOD, implementation = AppiumSwipeToStringTableRowElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = AppiumSize.class)
public abstract class MobileSimpleTableImpl extends AbstractMobileChildElement implements MobileSimpleTable {

    protected Map<String, MobileColumnMapper> columnMappers;

    @Override
    public OperationResult<String> getHeaderValue(String columnName) {
        return OperationResult.of(() -> new AppiumSimpleTableRowValueExtractor(columnName).extractSingleHeaderValue(this).get());
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(String columnName) {
        return OperationResult.of(() -> new AppiumSimpleTableRowValueExtractor(columnName).extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public OperationResult<MultipleResult<String>> getValues(String columnName, AppiumStringTableRowFilter filter) {
        MultipleResult<MobileElement> result = filter.multipleResult(this);
        return OperationResult.of(() -> new AppiumSimpleTableRowValueExtractor(columnName).setHash(result.getElementHash())
                .extractMultipleValues(this, result));
    }

    @Override
    public OperationResult<String> getFooterValue(String columnName) {
        return OperationResult.of(() -> new AppiumSimpleTableRowValueExtractor(columnName).extractSingleFooterValue(this).get());
    }

//    @Override
//    public OperationResult<Void> swipeToElement(AppiumStringTableRowFilter filter) {
//        return getMethodImplementation(SWIPE_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public OperationResult<Integer> size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, TBODY_ROW);
//    }

    @Override
    public Optional<MobileColumnMapper> getColumnMapper(String columnName) {
        return Optional.ofNullable(columnMappers.get(columnName));
    }

}
