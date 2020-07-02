package io.perfeccionista.framework.pagefactory.elements;

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
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollTo;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClickToBlockElement;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumHoverTo;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementAction;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldBePresent;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldHaveDimensions;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldHaveLocation;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldHavePropertyNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldHavePropertyValue;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldHaveSize;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldLooksLike;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBeDisplayed;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBeInFocus;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotBePresent;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotHaveColor;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotHaveDimensions;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotHaveLocation;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotHavePropertyNumber;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotHavePropertyValue;
import io.perfeccionista.framework.pagefactory.elements.asserts.JsAssertShouldNotLooksLike;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterSeleniumImpl;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.COMPONENT_SHOULD_NOT_BE_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_ALL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_PROPERTY_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SCREENSHOT_METHOD;
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

// WebList
@WebElementAction(name = CLICK_TO_ELEMENT_METHOD, implementation = SeleniumClickToBlockElement.class)
@WebElementAction(name = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToBlockElement.class)
@WebElementAction(name = SHOULD_HAVE_SIZE_METHOD, implementation = JsAssertShouldHaveSize.class)
@WebElementAction(name = SIZE_METHOD, implementation = JsGetSize.class)
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
@WebElementAction(name = SHOULD_BE_IN_FOCUS_METHOD, implementation = JsAssertShouldBeInFocus.class)
@WebElementAction(name = SHOULD_BE_PRESENT_METHOD, implementation = JsAssertShouldBePresent.class)
@WebElementAction(name = SHOULD_HAVE_COLOR_METHOD, implementation = JsAssertShouldHaveColor.class)
@WebElementAction(name = SHOULD_HAVE_DIMENSIONS_METHOD, implementation = JsAssertShouldHaveDimensions.class)
@WebElementAction(name = SHOULD_HAVE_LOCATION_METHOD, implementation = JsAssertShouldHaveLocation.class)
@WebElementAction(name = SHOULD_HAVE_PROPERTY_NUMBER_METHOD, implementation = JsAssertShouldHavePropertyNumber.class)
@WebElementAction(name = SHOULD_HAVE_PROPERTY_VALUE_METHOD, implementation = JsAssertShouldHavePropertyValue.class)
@WebElementAction(name = SHOULD_LOOKS_LIKE_METHOD, implementation = JsAssertShouldLooksLike.class)
@WebElementAction(name = SHOULD_NOT_BE_DISPLAYED_METHOD, implementation = JsAssertShouldNotBeDisplayed.class)
@WebElementAction(name = SHOULD_NOT_BE_IN_FOCUS_METHOD, implementation = JsAssertShouldNotBeInFocus.class)
@WebElementAction(name = SHOULD_NOT_BE_PRESENT_METHOD, implementation = JsAssertShouldNotBePresent.class)
@WebElementAction(name = SHOULD_NOT_HAVE_COLOR_METHOD, implementation = JsAssertShouldNotHaveColor.class)
@WebElementAction(name = SHOULD_NOT_HAVE_DIMENSIONS_METHOD, implementation = JsAssertShouldNotHaveDimensions.class)
@WebElementAction(name = SHOULD_NOT_HAVE_LOCATION_METHOD, implementation = JsAssertShouldNotHaveLocation.class)
@WebElementAction(name = SHOULD_NOT_HAVE_PROPERTY_NUMBER_METHOD, implementation = JsAssertShouldNotHavePropertyNumber.class)
@WebElementAction(name = SHOULD_NOT_HAVE_PROPERTY_VALUE_METHOD, implementation = JsAssertShouldNotHavePropertyValue.class)
@WebElementAction(name = SHOULD_NOT_LOOKS_LIKE_METHOD, implementation = JsAssertShouldNotLooksLike.class)
public class WebListSeleniumImpl extends AbstractWebChildElement implements WebList {

    protected Class<? extends WebMappedBlock> mappedBlockClass;

    @Override
    public WebListFilterResult filter(WebListFilter filter) {
        return filter.filter(this);
    }

    @Override
    public <V> MultipleResult<V> extractAll(WebListBlockValueExtractor<V> extractor) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_ALL_METHOD, this, extractor),
                () -> new WebListFilterSeleniumImpl()
                .filter(this)
                .extractAll(extractor));
    }

    // Actions

    @Override
    public WebList executeAction(String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    @Override
    public WebList executeInteraction(String name, WebChildElement other, Object... args) {
        super.executeInteraction(name, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebList should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebList should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }


    // ClickToElement

    @Override
    public WebList clickToElement(WebListFilter filter) {
        runCheck(getEnvironment(), InvocationName.of(CLICK_TO_ELEMENT_METHOD, this, filter),
                () -> getActionImplementation(CLICK_TO_ELEMENT_METHOD, Void.class).execute(this, filter));
        return this;
    }

    // Get Color

    @Override
    public WebList componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebList componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebList componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebList componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Location

    @Override
    public WebList componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebList componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebList componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebList componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // HoverTo

    @Override
    public WebList hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebList shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebList shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebList shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebList shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebList shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebList shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // ScrollTo

    @Override
    public WebList scrollTo() {
        super.scrollTo();
        return this;
    }

    // ScrollToElement

    @Override
    public WebList scrollToElement(WebListFilter filter) {
        runCheck(getEnvironment(), InvocationName.of(SCROLL_TO_ELEMENT_METHOD, this, filter),
                () -> getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter));
        return this;
    }

    // Size

    @Override
    public int size() {
        return runCheck(getEnvironment(), InvocationName.of(SIZE_METHOD, this, LI),
                () -> getActionImplementation(SIZE_METHOD, Integer.class).execute(this, LI));
    }

    @Override
    public WebList shouldHaveSize(NumberValue<Integer> expectedSize) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize),
                () -> getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class).execute(this, expectedSize));
        return this;
    }

    // WebComponents

    @Override
    public WebList componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebList componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebList componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebList componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebList shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebList shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebList shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebList shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
