package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterSeleniumImpl;
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


public class WebTextTableSeleniumImpl extends AbstractWebChildElement implements WebTextTable {

    protected Map<String, TableColumnHolder> tableColumnHolders;

    @Override
    public WebTextTable setTableColumnHolders(Map<String, TableColumnHolder> tableColumnHolders) {
        this.tableColumnHolders = tableColumnHolders;
        return this;
    }

    @Override
    public Optional<TableColumnHolder> getTableColumnHolder(String columnName) {
        return Optional.ofNullable(tableColumnHolders.get(columnName));
    }

    public WebTextTableFilterResult filter(WebTextTableFilter filter) {
        return filter.filter(this);
    }

    @Override
    public SingleResult<String> extractHeader(String columnName) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_HEADER_FILTER, this, columnName),
                () -> new WebTextTableFilterSeleniumImpl().filter(this)
                        .extractHeader(columnName));
    }

    @Override
    public MultipleResult<String> extractAllRows(String columnName) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_ALL_ROWS_FILTER, this, columnName),
                () -> new WebTextTableFilterSeleniumImpl().filter(this)
                        .extractAllRows(columnName));
    }

    @Override
    public SingleResult<String> extractFooter(String columnName) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_FOOTER_FILTER, this, columnName),
                () -> new WebTextTableFilterSeleniumImpl().filter(this)
                        .extractFooter(columnName));
    }

    // Actions

    @Override
    public WebTextTable executeAction(String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextTable executeInteraction(String interactionName, WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebTextTable should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebTextTable should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }

    // Color

    @Override
    public WebTextTable componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Dimensions

    @Override
    public WebTextTable componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Location

    @Override
    public WebTextTable componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Screenshot

    @Override
    public WebTextTable componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // HoverTo

    @Override
    public WebTextTable hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebTextTable shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebTextTable shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebTextTable shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebTextTable shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebTextTable shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebTextTable shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // ScrollTo

    @Override
    public WebTextTable scrollTo() {
        super.scrollTo();
        return this;
    }

    // ScrollToElement

    @Override
    public WebTextTable scrollToElement(WebTextTableFilter filter) {
        runCheck(getEnvironment(), InvocationName.of(SCROLL_TO_ELEMENT_METHOD, this, filter),
                () -> getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter));
        return this;
    }

    // Size

    @Override
    public int size() {
        return runCheck(getEnvironment(), InvocationName.of(SIZE_METHOD, this),
                () -> getActionImplementation(SIZE_METHOD, Integer.class).execute(this, TBODY_ROW));
    }

    @Override
    public WebTextTable shouldHaveSize(NumberValue<Integer> expectedSize) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize),
                () -> getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class).execute(this, TBODY_ROW, expectedSize));
        return this;
    }

    // WebComponent

    @Override
    public WebTextTable componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebTextTable componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebTextTable shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextTable shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextTable shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextTable shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    // TODO: Implement
    @Override
    public JsonNode toJson() {
        return super.toJson();
    }

}
