package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.method.MobileIsOpenAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.OPEN_METHOD;

public interface MobileDropDownAvailable extends MobileChildElement {

    @MobileMappedElementAction(IS_OPEN_METHOD)
    boolean isOpen();

    @ActionMethodType
    @MobileMappedElementAction(OPEN_METHOD)
    MobileDropDownAvailable open();

    @ActionMethodType
    @MobileMappedElementAction(CLOSE_METHOD)
    MobileDropDownAvailable close();

    @AssertMethodType
    MobileDropDownAvailable should(@NotNull MobileIsOpenAvailableMatcher matcher);

}
