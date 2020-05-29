package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;


public interface WebTextDropDownList extends WebTextList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    @Override
    WebTextDropDownList executeAction(String name, Object... args);

    @Override
    WebTextDropDownList executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebTextDropDownList click();

    @Override
    WebTextDropDownList clickToElement(WebTextListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebTextDropDownList open();

    @Override
    WebTextDropDownList close();

    @Override
    WebTextDropDownList hoverTo(boolean withOutOfBounds);

    @Override
    WebTextDropDownList scrollTo();

    @Override
    WebTextDropDownList scrollToElement(WebTextListFilter filter);


    @Override
    WebTextDropDownList shouldBePresent();

    @Override
    WebTextDropDownList shouldNotBePresent();

    @Override
    WebTextDropDownList shouldBeDisplayed();

    @Override
    WebTextDropDownList shouldNotBeDisplayed();

    @Override
    WebTextDropDownList shouldBeInFocus();

    @Override
    WebTextDropDownList shouldNotBeInFocus();

    @Override
    WebTextDropDownList shouldBeOpen();

    @Override
    WebTextDropDownList shouldBeClosed();


    @Override
    WebTextDropDownList shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebTextDropDownList shouldHaveText(StringValue stringValue);

    @Override
    WebTextDropDownList shouldHaveText(NumberValue<?> numberValue);

    @Override
    WebTextDropDownList shouldNotHaveText(StringValue stringValue);

    @Override
    WebTextDropDownList shouldNotHaveText(NumberValue<?> numberValue);

    @Override
    WebTextDropDownList shouldHaveLabel(StringValue stringValue);

    @Override
    WebTextDropDownList shouldHaveLabel(NumberValue<?> numberValue);

    @Override
    WebTextDropDownList shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebTextDropDownList shouldNotHaveLabel(NumberValue<?> numberValue);

    @Override
    WebTextDropDownList shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextDropDownList shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebTextDropDownList shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextDropDownList shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebTextDropDownList componentShouldBePresent(String componentName);

    @Override
    WebTextDropDownList componentShouldNotBePresent(String componentName);

    @Override
    WebTextDropDownList componentShouldBeDisplayed(String componentName);

    @Override
    WebTextDropDownList componentShouldNotBeDisplayed(String componentName);

    @Override
    WebTextDropDownList componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebTextDropDownList componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

    @Override
    WebTextDropDownList componentShouldHaveLocation(String componentName, Location location);

    @Override
    WebTextDropDownList componentShouldNotHaveLocation(String componentName, Location location);

    @Override
    WebTextDropDownList componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebTextDropDownList componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
