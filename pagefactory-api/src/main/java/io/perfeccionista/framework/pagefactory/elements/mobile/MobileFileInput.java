package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SubmitAvailable;

public interface MobileFileInput extends MobileChildElement,
        GetTextAvailable, SendKeysAvailable, ClearAvailable, SubmitAvailable, IsEnabledAvailable, GetLabelAvailable {
}
