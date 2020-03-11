package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.TapAvailable;

public interface MobileRadioButton extends MobileChildElement,
        TapAvailable, IsSelectedAvailable, IsEnabledAvailable, GetLabelAvailable {
}
