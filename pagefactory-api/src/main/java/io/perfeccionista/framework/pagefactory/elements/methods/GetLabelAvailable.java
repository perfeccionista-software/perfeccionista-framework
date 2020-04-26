package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;

public interface GetLabelAvailable extends Element {

    @MappedElementAction(GET_LABEL_METHOD)
    String getLabel();

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_TEXT_LABEL_METHOD)
    GetLabelAvailable shouldHaveLabel(StringValue stringValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_NUMBER_LABEL_METHOD)
    GetLabelAvailable shouldHaveLabel(NumberValue<?> numberValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD)
    GetLabelAvailable shouldNotHaveLabel(StringValue stringValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD)
    GetLabelAvailable shouldNotHaveLabel(NumberValue<?> numberValue);

}
