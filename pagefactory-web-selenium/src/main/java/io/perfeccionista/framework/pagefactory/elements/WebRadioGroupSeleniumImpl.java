package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditions;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilter;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonFilterSeleniumImpl;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_ALL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_BY_INDEX;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_BY_LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SELECTED;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

public class WebRadioGroupSeleniumImpl extends AbstractWebChildElement implements WebRadioGroup {

    @Override
    public WebRadioButtonFilterResult filter(WebRadioButtonFilter filter) {
        return filter.filter(this);
    }

    @Override
    public  <V> MultipleResult<V> extractAll(WebRadioButtonValueExtractor<V> extractor) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_ALL_METHOD, this, extractor),
                () -> new WebRadioButtonFilterSeleniumImpl()
                        .filter(this)
                        .extractAll(extractor));
    }

    @Override
    public WebRadioButton getSelected() {
        return runCheck(getEnvironment(), InvocationName.of(GET_SELECTED, this),
                () -> new WebRadioButtonFilterSeleniumImpl().add(selected())
                        .filter(this)
                        .extractOne(element())
                        .shouldHaveNotNull().get());
    }

    @Override
    public WebRadioButton getByLabel(StringValue label) {
        return runCheck(getEnvironment(), InvocationName.of(GET_BY_LABEL, this),
                () -> with(WebConditions.containsLabel(label))
                        .filter(this)
                        .extractOne(element())
                        .shouldHaveNotNull().get());
    }

    @Override
    public WebRadioButton getByIndex(NumberValue<Integer> index) {
        return runCheck(getEnvironment(), InvocationName.of(GET_BY_INDEX, this),
                () -> with(radioButtonIndex(index))
                        .filter(this)
                        .extractOne(element())
                        .shouldHaveNotNull().get());
    }

    // Actions

    @Override
    public WebRadioGroup executeAction(String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebRadioGroup executeInteraction(String interactionName, WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

    @Override
    public WebRadioGroup should(WebAssertCondition assertCondition) {
        super.should(assertCondition);
        return this;
    }

    @Override
    public WebRadioGroup should(WebAssertCondition assertCondition, InvocationName invocationName) {
        super.should(assertCondition, invocationName);
        return this;
    }


    // Get Color

    @Override
    public WebRadioGroup componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebRadioGroup componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Location

    @Override
    public WebRadioGroup componentShouldHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotHaveLocation(String componentName, Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebRadioGroup componentShouldLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot) {
        super.componentShouldNotLooksLike(componentName, expectedScreenshot);
        return this;
    }

    // HoverTo

    @Override
    public WebRadioGroup hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // IsDisplayed

    @Override
    public WebRadioGroup shouldBeDisplayed() {
        super.shouldBeDisplayed();
        return this;
    }

    @Override
    public WebRadioGroup shouldNotBeDisplayed() {
        super.shouldNotBeDisplayed();
        return this;
    }

    // IsInFocus

    @Override
    public WebRadioGroup shouldBeInFocus() {
        super.shouldBeInFocus();
        return this;
    }

    @Override
    public WebRadioGroup shouldNotBeInFocus() {
        super.shouldNotBeInFocus();
        return this;
    }

    // IsPresent

    @Override
    public WebRadioGroup shouldBePresent() {
        super.shouldBePresent();
        return this;
    }

    @Override
    public WebRadioGroup shouldNotBePresent() {
        super.shouldNotBePresent();
        return this;
    }

    // ScrollTo

    @Override
    public WebRadioGroup scrollTo() {
        super.scrollTo();
        return this;
    }

    // ScrollToElement

    @Override
    public WebRadioGroup scrollToElement(WebRadioButtonFilter filter) {
        runCheck(getEnvironment(), InvocationName.of(SCROLL_TO_ELEMENT_METHOD, this, filter),
                () -> getActionImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter));
        return this;
    }

    // Size

    @Override
    public int size() {
        return runCheck(getEnvironment(), InvocationName.of(SIZE_METHOD, this, RADIO),
                () -> getActionImplementation(SIZE_METHOD, Integer.class).execute(this, RADIO));
    }

    @Override
    public WebRadioGroup shouldHaveSize(NumberValue<Integer> expectedSize) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize),
                () -> getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class).execute(this, expectedSize));
        return this;
    }

    // WebComponents

    @Override
    public WebRadioGroup componentShouldBePresent(String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotBePresent(String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldBeDisplayed(String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotBeDisplayed(String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebRadioGroup shouldHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioGroup shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioGroup shouldNotHavePropertyValue(String propertyName, StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioGroup shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    // TODO: Implement
    @Override
    public JsonNode toJson() {
        return super.toJson();
    }

}
