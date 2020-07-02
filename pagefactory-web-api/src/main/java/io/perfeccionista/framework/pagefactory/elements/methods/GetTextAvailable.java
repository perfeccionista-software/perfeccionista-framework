package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_METHOD;

public interface GetTextAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(GET_TEXT_METHOD)
    String getText();

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_TEXT_METHOD)
    WebChildElement shouldHaveText(StringValue expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_NUMBER_METHOD)
    WebChildElement shouldHaveText(NumberValue<?> expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_TEXT_METHOD)
    WebChildElement shouldNotHaveText(StringValue expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_NUMBER_METHOD)
    WebChildElement shouldNotHaveText(NumberValue<?> expectedValue);

}
