package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TFOOT_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;

// TODO: Map<String, SingleResult<String>> extractHeader(Set<String> columnNames);
// TODO: Map<String, MultipleResult<String>> extractAll(Set<String> columnNames);
// TODO: Map<String, SingleResult<String>> extractFooter(Set<String> columnNames);
// TODO: Добавить TextBlockExtractor/LinkExtractor
// TODO: ClickToHeader / Cell / Footer
@WebLocator(component = THEAD_ROW, xpath = ".//thead//tr")
@WebLocator(component = TBODY_ROW, xpath = ".//tbody//tr", single = false)
@WebLocator(component = TFOOT_ROW, xpath = ".//tfoot//tr")
public interface WebTextTable extends WebChildElement,
        ScrollToElementAvailable<WebTextTableFilter>, SizeAvailable {

    WebTextTable setTableColumnHolders(Map<String, TableColumnHolder> mappedTableColumnHolders);

    Optional<TableColumnHolder> getTableColumnHolder(String columnName);

    WebTextTableFilterResult filter(WebTextTableFilter filter);

    SingleResult<String> extractHeader(String columnName);

    MultipleResult<String> extractAllRows(String columnName);

    SingleResult<String> extractFooter(String columnName);

    // Actions

    @Override
    WebTextTable executeAction(String name, Object... args);

    @Override
    WebTextTable executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebTextTable should(WebAssertCondition assertCondition);

    @Override
    WebTextTable should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Get Color

    @Override
    WebTextTable componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);
    @Override
    WebTextTable componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebTextTable componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);
    @Override
    WebTextTable componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebTextTable componentShouldHaveLocation(String componentName, Location expectedLocation);
    @Override
    WebTextTable componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebTextTable componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);
    @Override
    WebTextTable componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebTextTable hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebTextTable shouldBeDisplayed();
    @Override
    WebTextTable shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebTextTable shouldBeInFocus();
    @Override
    WebTextTable shouldNotBeInFocus();

    // IsPresent

    @Override
    WebTextTable shouldBePresent();
    @Override
    WebTextTable shouldNotBePresent();

    // ScrollTo

    @Override
    WebTextTable scrollTo();

    // ScrollToElement

    @Override
    WebTextTable scrollToElement(WebTextTableFilter filter);

    // Size

    @Override
    WebTextTable shouldHaveSize(NumberValue<Integer> expectedSize);

    // WebComponent

    @Override
    WebTextTable componentShouldBePresent(String componentName);

    @Override
    WebTextTable componentShouldNotBePresent(String componentName);

    @Override
    WebTextTable componentShouldBeDisplayed(String componentName);

    @Override
    WebTextTable componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebTextTable shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextTable shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebTextTable shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebTextTable shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
