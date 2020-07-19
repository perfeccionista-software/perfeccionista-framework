package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.asserts.WebAssertCondition;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.plugin.Color;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.CLEAR;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.INPUT;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TEXT;

/**
 * Input элемент для FileInput обычно скрыт,
 * поэтому тут удобнее объявлять корневым элементом родительский div
 */
@WebLocator(component = LABEL, xpath = "self::node()//label")
@WebLocator(component = TEXT, xpath = "self::node()//input[@type = 'file']")
@WebLocator(component = INPUT, xpath = "self::node()//input[@type = 'file']")
@WebLocator(component = CLEAR, xpath = "self::node()//input[@type = 'file']")
@WebLocator(component = ENABLED, xpath = "self::node()//input[@type = 'file']")
// TODO: Метод loadFile(Path filePath) вместо sendKeys()
// Нужен ли метод submit()?
public interface WebFileInput extends WebChildElement,
        GetTextAvailable, SendKeysAvailable, ClearAvailable, IsEnabledAvailable, GetLabelAvailable {

    // Actions

    @Override
    WebFileInput executeAction(@NotNull String name, Object... args);

    @Override
    WebFileInput executeInteraction(@NotNull String name, @NotNull WebChildElement other, Object... args);

    // Asserts

    @Override
    WebFileInput should(WebAssertCondition assertCondition);

    @Override
    WebFileInput should(WebAssertCondition assertCondition, InvocationName invocationName);

    // Clear

    @Override
    WebFileInput clear();

    // Get Color

    @Override
    WebFileInput componentShouldHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    @Override
    WebFileInput componentShouldNotHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor);

    // Get Dimensions

    @Override
    WebFileInput componentShouldHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    @Override
    WebFileInput componentShouldNotHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions);

    // Get Label

    @Override
    WebFileInput shouldHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebFileInput shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @Override
    WebFileInput shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @Override
    WebFileInput shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

    // Get Location

    @Override
    WebFileInput componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @Override
    WebFileInput componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    // Get Screenshot

    @Override
    WebFileInput componentShouldLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    @Override
    WebFileInput componentShouldNotLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot);

    // GetText

    @Override
    WebFileInput shouldHaveText(@NotNull StringValue expectedValue);

    @Override
    WebFileInput shouldHaveText(@NotNull NumberValue<?> expectedValue);

    @Override
    WebFileInput shouldNotHaveText(@NotNull StringValue expectedValue);

    @Override
    WebFileInput shouldNotHaveText(@NotNull NumberValue<?> expectedValue);

    // HoverTo

    @Override
    WebFileInput hoverTo(boolean withOutOfBounds);

    // IsDisplayed

    @Override
    WebFileInput shouldBeDisplayed();

    @Override
    WebFileInput shouldNotBeDisplayed();

    // IsEnabled

    @Override
    WebFileInput shouldBeEnabled();

    @Override
    WebFileInput shouldBeDisabled();

    // IsInFocus

    @Override
    WebFileInput shouldBeInFocus();

    @Override
    WebFileInput shouldNotBeInFocus();

    // IsPresent

    @Override
    WebFileInput shouldBePresent();

    @Override
    WebFileInput shouldNotBePresent();

    // ScrollTo

    @Override
    WebFileInput scrollTo();

    // SendKeys

    @Override
    WebFileInput sendKeys(CharSequence... keys);

    // WebComponents

    @Override
    WebFileInput componentShouldBePresent(@NotNull String componentName);

    @Override
    WebFileInput componentShouldNotBePresent(@NotNull String componentName);

    @Override
    WebFileInput componentShouldBeDisplayed(@NotNull String componentName);

    @Override
    WebFileInput componentShouldNotBeDisplayed(@NotNull String componentName);

    // WebProperties

    @Override
    WebFileInput shouldHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebFileInput shouldHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

    @Override
    WebFileInput shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue);

    @Override
    WebFileInput shouldNotHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<?> expectedValue);

}
