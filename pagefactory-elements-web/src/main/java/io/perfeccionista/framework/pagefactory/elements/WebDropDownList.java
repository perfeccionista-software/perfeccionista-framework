package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.JsSelectWebBlock;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClose;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumOpen;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
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
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SELECT_METHOD;

@WebLocator(component = UL, xpath = ".//ul")
@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementMethod(type = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementMethod(type = OPEN_METHOD, implementation = SeleniumOpen.class)
@ElementMethod(type = CLOSE_METHOD, implementation = SeleniumClose.class)
@ElementMethod(type = SELECT_METHOD, implementation = JsSelectWebBlock.class)
public interface WebDropDownList extends WebList,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {

    WebDropDownList select(WebListFilter filter);

    @Override
    WebDropDownList click();

    @Override
    WebDropDownList close();

    @Override
    WebDropDownList hoverTo(boolean withOutOfBounds);

    @Override
    WebDropDownList open();

    @Override
    WebDropDownList scrollTo();

    @Override
    WebDropDownList scrollToElement(WebListFilter filter);

    @Override
    WebDropDownList shouldBeOpen();

    @Override
    WebDropDownList shouldBeClosed();

    @Override
    WebDropDownList shouldBeDisplayed();

    @Override
    WebDropDownList shouldNotBeDisplayed();

    @Override
    WebDropDownList shouldHaveBounds(Bounds bounds);

    @Override
    WebDropDownList shouldHaveLabel(StringValue stringValue);

    @Override
    WebDropDownList shouldHavePropertyValue(String propertyValue, StringValue stringValue);

    @Override
    WebDropDownList shouldHaveSize(NumberValue<Integer> integerValue);

    @Override
    WebDropDownList shouldHaveText(StringValue stringValue);

    @Override
    WebDropDownList shouldLooksLike(Screenshot screenshot);

    @Override
    WebDropDownList stateShouldBeDisplayed(String stateName);

}
