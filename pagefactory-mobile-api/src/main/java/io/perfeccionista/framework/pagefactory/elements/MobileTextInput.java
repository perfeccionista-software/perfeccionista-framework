package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.TapAvailable;

public interface MobileTextInput extends MobileChildElement,
        TapAvailable, GetTextAvailable, SendKeysAvailable, ClearAvailable, GetLabelAvailable {
}
