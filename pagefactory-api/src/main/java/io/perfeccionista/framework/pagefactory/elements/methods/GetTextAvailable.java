package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_TEXT_METHOD;

public interface GetTextAvailable extends Element {

    @MappedElementAction(GET_TEXT_METHOD)
    String getText();

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_TEXT_METHOD)
    GetTextAvailable shouldHaveText(StringValue stringValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_NUMBER_METHOD)
    GetTextAvailable shouldHaveText(NumberValue<?> numberValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_TEXT_METHOD)
    GetTextAvailable shouldNotHaveText(StringValue stringValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_NUMBER_METHOD)
    GetTextAvailable shouldNotHaveText(NumberValue<?> numberValue);

}
