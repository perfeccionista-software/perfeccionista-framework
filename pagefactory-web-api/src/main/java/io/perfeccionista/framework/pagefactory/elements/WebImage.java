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
import org.jetbrains.annotations.NotNull;

// TODO: Сделать встроенную проверку того, что это изображение (по тегу src)
// TODO: Добавить метод saveToFile(Path file); который сохранит изображение в указанный файл.
public interface WebImage extends WebChildElement,
        ClickAvailable {

    // Actions

    @Override
    WebImage executeAction(@NotNull String name, Object... args);

    @Override
    WebImage executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

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
    WebImage componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebImage componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebImage componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebImage componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Location

    @Override
    WebImage componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebImage componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebImage componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebImage componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

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
    WebImage componentShouldBePresent(@NotNull String componentName);

    @Override
    WebImage componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebImage componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebImage componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebImage shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebImage shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebImage shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebImage shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
