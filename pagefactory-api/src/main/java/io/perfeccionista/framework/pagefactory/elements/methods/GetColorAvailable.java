package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.plugin.Color;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_COLOR_METHOD;

public interface GetColorAvailable extends Element {

    @MappedElementAction(GET_COLOR_METHOD)
    Color getColor(String component);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_COLOR_METHOD)
    GetColorAvailable shouldHaveColor(String component, Color color);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_COLOR_METHOD)
    GetColorAvailable shouldNotHaveColor(String component, Color color);

}
