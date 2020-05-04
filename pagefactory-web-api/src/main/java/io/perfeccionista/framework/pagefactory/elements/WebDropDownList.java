package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public interface WebDropDownList extends WebList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    @Override
    WebDropDownList executeAction(String name, Object... args);

    @Override
    WebDropDownList executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebDropDownList click();

    @Override
    WebDropDownList clickToElement(WebListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebDropDownList open();

    @Override
    WebDropDownList close();

    @Override
    WebDropDownList hoverTo(boolean withOutOfBounds);

    @Override
    WebDropDownList scrollTo();

    @Override
    WebDropDownList scrollToElement(WebListFilter filter);


    @Override
    WebDropDownList shouldBePresent();

    @Override
    WebDropDownList shouldNotBePresent();

    @Override
    WebDropDownList shouldBeDisplayed();

    @Override
    WebDropDownList shouldNotBeDisplayed();

    @Override
    WebDropDownList shouldBeInFocus();

    @Override
    WebDropDownList shouldNotBeInFocus();

    @Override
    WebDropDownList shouldBeOpen();

    @Override
    WebDropDownList shouldBeClosed();


    @Override
    WebDropDownList shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebDropDownList shouldHaveText(StringValue stringValue);

    @Override
    WebDropDownList shouldHaveText(NumberValue<?> numberValue);

    @Override
    WebDropDownList shouldNotHaveText(StringValue stringValue);

    @Override
    WebDropDownList shouldNotHaveText(NumberValue<?> numberValue);

    @Override
    WebDropDownList shouldHaveLabel(StringValue stringValue);

    @Override
    WebDropDownList shouldHaveLabel(NumberValue<?> numberValue);

    @Override
    WebDropDownList shouldNotHaveLabel(StringValue stringValue);

    @Override
    WebDropDownList shouldNotHaveLabel(NumberValue<?> numberValue);

    @Override
    WebDropDownList shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebDropDownList shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebDropDownList shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebDropDownList shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebDropDownList componentShouldBePresent(String componentName);

    @Override
    WebDropDownList componentShouldNotBePresent(String componentName);

    @Override
    WebDropDownList componentShouldBeDisplayed(String componentName);

    @Override
    WebDropDownList componentShouldNotBeDisplayed(String componentName);

    @Override
    WebDropDownList componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebDropDownList componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebDropDownList componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebDropDownList componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}
