package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebDropDownAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.OPEN_METHOD;

public interface WebDropDownAvailable extends WebChildElement {

    @WebMappedElementAction(IS_OPEN_METHOD)
    boolean isOpen();

    @ActionMethodType
    @WebMappedElementAction(OPEN_METHOD)
    WebDropDownAvailable open();

    @ActionMethodType
    @WebMappedElementAction(CLOSE_METHOD)
    WebDropDownAvailable close();

    @AssertMethodType
    WebDropDownAvailable should(@NotNull WebDropDownAvailableMatcher matcher);

}
