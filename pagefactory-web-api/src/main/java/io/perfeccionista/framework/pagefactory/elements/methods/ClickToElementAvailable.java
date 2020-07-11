package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.CLICK_TO_ELEMENT_METHOD;

public interface ClickToElementAvailable<F extends WebFilter<?, ?>> extends WebLocatorChainAvailable {

    @ActionMethodType
    @MappedElementAction(CLICK_TO_ELEMENT_METHOD)
    WebChildElement clickToElement(@NotNull F filter);

}
