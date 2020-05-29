package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

// TODO: Сделать встроенную проверку того, что это изображение (по тегу src)
// TODO: Добавить метод saveToFile(Path file); который сохранит изображение в указанный файл.
public interface WebImage extends WebChildElement,
        ClickAvailable {

    @Override
    WebImage executeAction(String name, Object... args);

    @Override
    WebImage executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebImage click();

    @Override
    WebImage hoverTo(boolean withOutOfBounds);

    @Override
    WebImage scrollTo();


    @Override
    WebImage shouldBePresent();

    @Override
    WebImage shouldNotBePresent();

    @Override
    WebImage shouldBeDisplayed();

    @Override
    WebImage shouldNotBeDisplayed();

    @Override
    WebImage shouldBeInFocus();

    @Override
    WebImage shouldNotBeInFocus();


    @Override
    WebImage shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebImage shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebImage shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebImage shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebImage componentShouldBePresent(String componentName);

    @Override
    WebImage componentShouldNotBePresent(String componentName);

    @Override
    WebImage componentShouldBeDisplayed(String componentName);

    @Override
    WebImage componentShouldNotBeDisplayed(String componentName);

    @Override
    WebImage componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebImage componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebImage componentShouldHaveLocation(String componentName, Location location);

    @Override
    WebImage componentShouldNotHaveLocation(String componentName, Location location);

    @Override
    WebImage componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebImage componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
