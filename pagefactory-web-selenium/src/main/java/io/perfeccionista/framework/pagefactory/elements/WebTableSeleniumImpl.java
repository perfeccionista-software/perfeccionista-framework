package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterSeleniumImpl;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_ALL_ROWS_FILTER;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_FOOTER_FILTER;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_HEADER_FILTER;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SIZE_METHOD;


public class WebTableSeleniumImpl extends AbstractWebChildElement implements WebTable {

    protected Map<String, TableColumnHolder> tableColumnHolders;

    @Override
    public Optional<TableColumnHolder> getTableColumnHolder(String columnName) {
        return Optional.ofNullable(tableColumnHolders.get(columnName));
    }

    @Override
    public WebTableFilterResult filter(WebTableFilter filter) {
        return filter.filter(this);
    }

    @Override
    public  <V> SingleResult<V> extractHeader(WebTableCellValueExtractor<V> extractor) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_HEADER_FILTER, this, extractor),
                () -> new WebTableFilterSeleniumImpl().filter(this)
                        .extractHeader(extractor));
    }

    @Override
    public  <V> MultipleResult<V> extractAllRows(WebTableCellValueExtractor<V> extractor) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_ALL_ROWS_FILTER, this, extractor),
                () -> new WebTableFilterSeleniumImpl().filter(this)
                        .extractAllRows(extractor));
    }

    @Override
    public  <V> SingleResult<V> extractFooter(WebTableCellValueExtractor<V> extractor) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_FOOTER_FILTER, this, extractor),
                () -> new WebTableFilterSeleniumImpl().filter(this)
                        .extractFooter(extractor));
    }

    // Actions

    @Override
    public WebTable executeAction(String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTable executeInteraction(String interactionName, WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTable should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebTable should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Get Color

    @Override
    public WebTable componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebTable componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebTable componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebTable componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Location

    @Override
    public WebTable componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebTable componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebTable componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebTable componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // HoverTo

    @Override
    public WebTable hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebTable shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebTable shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebTable shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebTable shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebTable shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebTable shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // ScrollTo

    @Override
    public WebTable scrollTo() {
        super.scrollTo();
        return this;
    }

    // ScrollToElement

    @Override
    public WebTable scrollToElement(WebTableFilter filter) {
        runCheck(getEnvironment(), InvocationName.of(SCROLL_TO_ELEMENT_METHOD, this, filter),
                () -> getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter));
        return this;
    }

    // Size

    @Override
    public int size() {
        return runCheck(getEnvironment(), InvocationName.of(SIZE_METHOD, this, TBODY_ROW),
                () -> getActionImplementation(SIZE_METHOD, Integer.class).execute(this, TBODY_ROW));
    }

    @Override
    public WebTable shouldHaveSize(NumberValue<Integer> expectedSize) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize),
                () -> {
                    int actualSize = getActionImplementation(SIZE_METHOD, Integer.class)
                            .execute(this, TBODY_ROW);
                    getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class)
                            .execute(this, actualSize, expectedSize);
                });
        return this;
    }

    // WebComponents

    @Override
    public WebTable componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebTable componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebTable componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebTable componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebTable shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTable shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTable shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTable shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    // TODO: Implement
    @Override
    public JsonNode toJson() {
        return super.toJson();
    }

}
