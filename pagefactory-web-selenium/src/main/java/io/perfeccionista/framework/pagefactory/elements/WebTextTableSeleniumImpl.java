package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetColor;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetDimensions;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsDisplayed;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsInFocus;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetIsPresent;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLocation;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetPropertyValue;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetScreenshot;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetSize;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollTo;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollToTableRowElement;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveDimensions;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveLocation;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHavePropertyNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHavePropertyValue;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldHaveSize;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldLooksLike;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveDimensions;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHaveLocation;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHavePropertyNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotHavePropertyValue;
import io.perfeccionista.framework.pagefactory.elements.asserts.AssertShouldNotLooksLike;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBePresent;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBePresent;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilderSeleniumImpl;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_ALL_ROWS_FILTER;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_FOOTER_FILTER;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_HEADER_FILTER;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.HOVER_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_COMPONENT_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_IN_FOCUS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_LOOKS_LIKE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SIZE_METHOD;


// WebTextTable
@WebElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)
@WebElementAction(name = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToTableRowElement.class)
@WebElementAction(name = SIZE_METHOD, implementation = JsGetSize.class)
@WebElementAction(name = SHOULD_HAVE_SIZE_METHOD, implementation = AssertShouldHaveSize.class)
// WebChildElement
@WebElementAction(name = COMPONENT_SHOULD_BE_DISPLAYED_METHOD, implementation = JsAssertShouldBeDisplayed.class)
@WebElementAction(name = COMPONENT_SHOULD_BE_PRESENT_METHOD, implementation = JsAssertShouldBePresent.class)
@WebElementAction(name = COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD, implementation = JsAssertShouldNotBeDisplayed.class)
@WebElementAction(name = COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD, implementation = JsAssertShouldNotBePresent.class)
@WebElementAction(name = GET_COLOR_METHOD, implementation = JsGetColor.class)
@WebElementAction(name = GET_DIMENSIONS_METHOD, implementation = JsGetDimensions.class)
@WebElementAction(name = GET_LOCATION_METHOD, implementation = JsGetLocation.class)
@WebElementAction(name = GET_PROPERTY_VALUE_METHOD, implementation = JsGetPropertyValue.class)
@WebElementAction(name = GET_SCREENSHOT_METHOD, implementation = JsGetScreenshot.class)
@WebElementAction(name = HOVER_TO_METHOD, implementation = SeleniumHoverTo.class)
@WebElementAction(name = IS_COMPONENT_DISPLAYED_METHOD, implementation = JsGetIsDisplayed.class)
@WebElementAction(name = IS_COMPONENT_PRESENT_METHOD, implementation = JsGetIsPresent.class)
@WebElementAction(name = IS_DISPLAYED_METHOD, implementation = JsGetIsDisplayed.class)
@WebElementAction(name = IS_IN_FOCUS_METHOD, implementation = JsGetIsInFocus.class)
@WebElementAction(name = IS_PRESENT_METHOD, implementation = JsGetIsPresent.class)
@WebElementAction(name = SCROLL_TO_METHOD, implementation = JsScrollTo.class)
@WebElementAction(name = SHOULD_BE_DISPLAYED_METHOD, implementation = JsAssertShouldBeDisplayed.class)
@WebElementAction(name = SHOULD_BE_IN_FOCUS_METHOD, implementation = AssertShouldBeInFocus.class)
@WebElementAction(name = SHOULD_BE_PRESENT_METHOD, implementation = JsAssertShouldBePresent.class)
@WebElementAction(name = SHOULD_HAVE_COLOR_METHOD, implementation = AssertShouldHaveColor.class)
@WebElementAction(name = SHOULD_HAVE_DIMENSIONS_METHOD, implementation = AssertShouldHaveDimensions.class)
@WebElementAction(name = SHOULD_HAVE_LOCATION_METHOD, implementation = AssertShouldHaveLocation.class)
@WebElementAction(name = SHOULD_HAVE_PROPERTY_NUMBER_METHOD, implementation = AssertShouldHavePropertyNumber.class)
@WebElementAction(name = SHOULD_HAVE_PROPERTY_VALUE_METHOD, implementation = AssertShouldHavePropertyValue.class)
@WebElementAction(name = SHOULD_LOOKS_LIKE_METHOD, implementation = AssertShouldLooksLike.class)
@WebElementAction(name = SHOULD_NOT_BE_DISPLAYED_METHOD, implementation = JsAssertShouldNotBeDisplayed.class)
@WebElementAction(name = SHOULD_NOT_BE_IN_FOCUS_METHOD, implementation = AssertShouldNotBeInFocus.class)
@WebElementAction(name = SHOULD_NOT_BE_PRESENT_METHOD, implementation = JsAssertShouldNotBePresent.class)
@WebElementAction(name = SHOULD_NOT_HAVE_COLOR_METHOD, implementation = AssertShouldNotHaveColor.class)
@WebElementAction(name = SHOULD_NOT_HAVE_DIMENSIONS_METHOD, implementation = AssertShouldNotHaveDimensions.class)
@WebElementAction(name = SHOULD_NOT_HAVE_LOCATION_METHOD, implementation = AssertShouldNotHaveLocation.class)
@WebElementAction(name = SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, implementation = AssertShouldNotHavePropertyNumber.class)
@WebElementAction(name = SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, implementation = AssertShouldNotHavePropertyValue.class)
@WebElementAction(name = SHOULD_NOT_LOOKS_LIKE_METHOD, implementation = AssertShouldNotLooksLike.class)
public class WebTextTableSeleniumImpl extends AbstractWebChildElement implements WebTextTable {

    protected Map<String, TableColumnHolder> tableColumnHolders;

    public @NotNull WebTextTableFilter filter(@NotNull WebTextTableFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull SingleResult<String> extractHeader(@NotNull String columnName) {
        // TODO: Возможно тут runCheck стоит перенести в метод extractHeader как и в методах shouldHaveSize
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_HEADER_FILTER, this, columnName),
                () -> new WebTextTableFilterBuilderSeleniumImpl().build(this)
                        .extractHeader(columnName));
    }

    @Override
    public @NotNull MultipleResult<String> extractAllRows(@NotNull String columnName) {
        // TODO: Возможно тут runCheck стоит перенести в метод extractHeader как и в методах shouldHaveSize
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_ALL_ROWS_FILTER, this, columnName),
                () -> new WebTextTableFilterBuilderSeleniumImpl().build(this)
                        .extractAllRows(columnName));
    }

    @Override
    public @NotNull SingleResult<String> extractFooter(@NotNull String columnName) {
        // TODO: Возможно тут runCheck стоит перенести в метод extractHeader как и в методах shouldHaveSize
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_FOOTER_FILTER, this, columnName),
                () -> new WebTextTableFilterBuilderSeleniumImpl().build(this)
                        .extractFooter(columnName));
    }

    // Actions

    @Override
    public WebTextTable executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebTextTable executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
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
    public WebTextTable componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Dimensions

    @Override
    public WebTextTable componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Location

    @Override
    public WebTextTable componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Screenshot

    @Override
    public WebTextTable componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
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
    public WebTextTable scrollToElement(@NotNull WebTextTableFilterBuilder filter) {
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
    public WebTextTable shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize),
                () -> {
                    int actualSize = getActionImplementation(SIZE_METHOD, Integer.class)
                            .execute(this, TBODY_ROW);
                    getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class)
                            .execute(this, actualSize, expectedSize);
                });
        return this;
    }

    // WebComponent

    @Override
    public WebTextTable componentShouldBePresent(@NotNull String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotBePresent(@NotNull String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebTextTable componentShouldBeDisplayed(@NotNull String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebTextTable componentShouldNotBeDisplayed(@NotNull String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebTextTable shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextTable shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextTable shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebTextTable shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    // TODO: Add table column description
    @Override
    public JsonNode toJson() {
        return super.toJson();
    }

}
