package io.perfeccionista.framework.pagefactory.elements;

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
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.EXTRACT_ALL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_BY_INDEX_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_BY_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

public class WebRadioGroupSeleniumImpl extends AbstractWebChildElement implements WebRadioGroup {

    @Override
    public @NotNull WebRadioButtonFilterResult filter(@NotNull WebRadioButtonFilter filter) {
        return filter.filter(this);
    }

    @Override
    public @NotNull <V> MultipleResult<V> extractAll(@NotNull WebRadioButtonValueExtractor<V> extractor) {
        return runCheck(getEnvironment(), InvocationName.of(EXTRACT_ALL_METHOD, this, extractor),
                () -> new WebRadioButtonFilterSeleniumImpl()
                        .filter(this)
                        .extractAll(extractor));
    }

    @Override
    public @NotNull WebRadioButton getSelected() {
        return runCheck(getEnvironment(), InvocationName.of(GET_SELECTED_METHOD, this),
                () -> new WebRadioButtonFilterSeleniumImpl().add(selected())
                        .filter(this)
                        .extractOne(element())
                        .shouldHaveNotNull().get());
    }

    @Override
    public @NotNull WebRadioButton getByLabel(@NotNull StringValue expectedLabel) {
        return runCheck(getEnvironment(), InvocationName.of(GET_BY_LABEL_METHOD, this),
                () -> with(WebConditions.containsLabel(expectedLabel))
                        .filter(this)
                        .extractOne(element())
                        .shouldHaveNotNull().get());
    }

    @Override
    public @NotNull WebRadioButton getByIndex(@NotNull NumberValue<Integer> expectedIndex) {
        return runCheck(getEnvironment(), InvocationName.of(GET_BY_INDEX_METHOD, this),
                () -> with(radioButtonIndex(expectedIndex))
                        .filter(this)
                        .extractOne(element())
                        .shouldHaveNotNull().get());
    }

    // Actions

    @Override
    public WebRadioGroup executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebRadioGroup executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
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
    public WebRadioGroup componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        super.componentShouldNotHaveColor(componentName, cssProperty, expectedColor);
        return this;
    }

    // Get Dimensions

    @Override
    public WebRadioGroup componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        super.componentShouldNotHaveDimensions(componentName, expectedDimensions);
        return this;
    }

    // Get Location

    @Override
    public WebRadioGroup componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldHaveLocation(componentName, expectedLocation);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        super.componentShouldNotHaveLocation(componentName, expectedLocation);
        return this;
    }

    // Get Screenshot

    @Override
    public WebRadioGroup componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        super.componentShouldLooksLike(componentName, expectedScreenshot);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
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
    public WebRadioGroup scrollToElement(@NotNull WebRadioButtonFilter filter) {
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
    public WebRadioGroup shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        runCheck(getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize),
                () -> {
                    int actualSize = getActionImplementation(SIZE_METHOD, Integer.class)
                            .execute(this, RADIO);
                    getActionImplementation(SHOULD_HAVE_SIZE_METHOD, Void.class)
                            .execute(this, actualSize, expectedSize);
                });
        return this;
    }

    // WebComponents

    @Override
    public WebRadioGroup componentShouldBePresent(@NotNull String componentName) {
        super.componentShouldBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotBePresent(@NotNull String componentName) {
        super.componentShouldNotBePresent(componentName);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldBeDisplayed(@NotNull String componentName) {
        super.componentShouldBeDisplayed(componentName);
        return this;
    }

    @Override
    public WebRadioGroup componentShouldNotBeDisplayed(@NotNull String componentName) {
        super.componentShouldNotBeDisplayed(componentName);
        return this;
    }

    // WebProperties

    @Override
    public WebRadioGroup shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioGroup shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioGroup shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

    @Override
    public WebRadioGroup shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue) {
        super.shouldNotHavePropertyValue(propertyName, expectedValue);
        return this;
    }

}
