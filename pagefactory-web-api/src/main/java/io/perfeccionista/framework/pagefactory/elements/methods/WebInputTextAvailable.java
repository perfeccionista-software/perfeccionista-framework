package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.options.TypeTextOptions;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SEND_KEY_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;

public interface WebInputTextAvailable<T extends WebInputTextAvailable> extends WebChildElement {

    @ActionMethodType
    @WebMappedElementAction(CLEAR_METHOD)
    T clear();

    @ActionMethodType
    @WebMappedElementAction(SEND_KEY_EVENTS_METHOD)
    T sendKeyEvents(@NotNull KeyEventsChain keyEvents);

    @ActionMethodType
    @WebMappedElementAction(TYPE_TEXT_METHOD)
    T setText(@NotNull String text);

    @ActionMethodType
    @WebMappedElementAction(TYPE_TEXT_METHOD)
    T setText(@NotNull TypeTextOptions options, @NotNull String text);

    @ActionMethodType
    @WebMappedElementAction(TYPE_TEXT_METHOD)
    T setValue(@NotNull String text);

}
