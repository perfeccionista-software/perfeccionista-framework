package io.perfeccionista.framework.pagefactory.elements.impl;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.MobileColumnMapper;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSize;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumSwipeToTableRowElement;
import io.perfeccionista.framework.pagefactory.extractor.AppiumTableRowValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.AppiumTableRowFilter;
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
@ElementMethod(type = SWIPE_TO_ELEMENT_METHOD, implementation = AppiumSwipeToTableRowElement.class)
@ElementMethod(type = SIZE_METHOD, implementation = AppiumSize.class)
public abstract class MobileTableImpl extends AbstractMobileChildElement implements MobileTable {

    protected Map<String, MobileColumnMapper> columnMappers;

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(AppiumTableRowValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractMultipleValues(this, MultipleResult.empty()));
    }

    @Override
    public <V> OperationResult<MultipleResult<V>> getValues(AppiumTableRowValueExtractor<V> extractor, AppiumTableRowFilter filter) {
        return OperationResult.of(() -> {
            MultipleResult<MobileElement> result = filter.multipleResult(this);
            return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
        });
    }

    @Override
    public <V> OperationResult<V> getHeaderValue(AppiumTableRowValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractSingleHeaderValue(this).get());
    }

    @Override
    public <V> OperationResult<V> getFooterValue(AppiumTableRowValueExtractor<V> extractor) {
        return OperationResult.of(() -> extractor.extractSingleFooterValue(this).get());
    }

//    @Override
//    public OperationResult<Void> swipeToElement(AppiumTableRowFilter filter) {
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
