package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.MobileMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.plugin.ActionMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SET_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SEND_KEY_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;

public interface MobileInputTextAvailable extends MobileChildElement {

    @ActionMethodType
    @MobileMappedElementAction(CLEAR_METHOD)
    MobileInputTextAvailable clear();

    @ActionMethodType
    @MobileMappedElementAction(TYPE_TEXT_METHOD)
    MobileInputTextAvailable typeText(@NotNull String keys);

    @ActionMethodType
    @MobileMappedElementAction(SET_VALUE_METHOD)
    MobileInputTextAvailable replaceText(@NotNull String keys);

    @ActionMethodType
    @MobileMappedElementAction(SEND_KEY_EVENTS_METHOD)
    MobileInputTextAvailable sendKeyEvents(@NotNull KeyEventsChain keyEvents);

}
