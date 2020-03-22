package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;

// TODO: Сделать встроенную проверку того, что это изображение (по тегу src)
@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
public interface WebImage extends WebChildElement,
        ClickAvailable {
}
