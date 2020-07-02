package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.plugin.Color;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_COLOR_METHOD;

public interface GetColorAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(GET_COLOR_METHOD)
    Color getColor(String componentName, String cssProperty);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_COLOR_METHOD)
    WebChildElement componentShouldHaveColor(String componentName, String cssProperty, Color expectedColor);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_COLOR_METHOD)
    WebChildElement componentShouldNotHaveColor(String componentName, String cssProperty, Color expectedColor);

}
