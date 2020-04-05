package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClose;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumOpen;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebStringListFilter;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.locators.Components.UL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.OPEN_METHOD;

@WebLocator(component = UL, xpath = ".//ul")
@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementMethod(type = OPEN_METHOD, implementation = SeleniumOpen.class)
@ElementMethod(type = CLOSE_METHOD, implementation = SeleniumClose.class)



public interface WebStringDropDownList extends WebStringList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    WebStringDropDownList select(WebStringListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebStringDropDownList click();

    @Override
    WebStringDropDownList open();

    @Override
    WebStringDropDownList close();

    @Override
    WebStringDropDownList hoverTo(boolean withOutOfBounds);

    @Override
    WebStringDropDownList scrollTo();

    @Override
    WebStringDropDownList scrollToElement(WebStringListFilter filter);

    @Override
    WebStringDropDownList shouldBeDisplayed();

    @Override
    WebStringDropDownList shouldNotBeDisplayed();

    @Override
    WebStringDropDownList shouldBeOpen();

    @Override
    WebStringDropDownList shouldBeClosed();

    @Override
    WebStringDropDownList shouldHaveBounds(Bounds bounds);

    @Override
    WebStringDropDownList shouldHaveLabel(StringValue stringValue);

    @Override
    WebStringDropDownList shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebStringDropDownList shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebStringDropDownList shouldHaveText(StringValue stringValue);

    @Override
    WebStringDropDownList shouldLooksLike(Screenshot screenshot);

    @Override
    WebStringDropDownList stateShouldBeDisplayed(String stateName);

}
