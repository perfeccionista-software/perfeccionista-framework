package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

// TODO: Сделать встроенную проверку того, что это изображение (по тегу src)
// TODO: Добавить метод saveToFile(Path file); который сохранит изображение в указанный файл.
public interface WebImage extends WebChildElement,
        ClickAvailable {

    // Actions

    @Override
    WebImage executeAction(String name, Object... args);

    @Override
    WebImage executeInteraction(String name, WebChildElement other, Object... args);

    // Asserts

    @Override
    WebImage should(WebAssertCondition assertCondition);

    @Override
    WebImage should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Click

    @Override
    WebImage click();

    // Get Color

    @Override
    WebImage componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @Override
    WebImage componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

    // Get Dimensions

    @Override
    WebImage componentShouldHaveDimensions(String componentName, Dimensions expectedDimensions);

    @Override
    WebImage componentShouldNotHaveDimensions(String componentName, Dimensions expectedDimensions);

    // Get Location

    @Override
    WebImage componentShouldHaveLocation(String componentName, Location expectedLocation);

    @Override
    WebImage componentShouldNotHaveLocation(String componentName, Location expectedLocation);

    // Get Screenshot

    @Override
    WebImage componentShouldLooksLike(String componentName, Screenshot expectedScreenshot);

    @Override
    WebImage componentShouldNotLooksLike(String componentName, Screenshot expectedScreenshot);

    // HoverTo

    @Override
    WebImage hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebImage shouldBeDisplayed();

    @Override
    WebImage shouldNotBeDisplayed();

    // IsInFocus

    @Override
    WebImage shouldBeInFocus();

    @Override
    WebImage shouldNotBeInFocus();

    // IsPresent

    @Override
    WebImage shouldBePresent();

    @Override
    WebImage shouldNotBePresent();

    // ScrollTo

    @Override
    WebImage scrollTo();

    // WebComponents

    @Override
    WebImage componentShouldBePresent(String componentName);

    @Override
    WebImage componentShouldNotBePresent(String componentName);

    @Override
    WebImage componentShouldBeDisplayed(String componentName);

    @Override
    WebImage componentShouldNotBeDisplayed(String componentName);

    // WebProperties

    @Override
    WebImage shouldHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebImage shouldHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

    @Override
    WebImage shouldNotHavePropertyValue(String propertyName, StringValue expectedValue);

    @Override
    WebImage shouldNotHavePropertyValue(String propertyName, NumberValue<?> expectedValue);

}
