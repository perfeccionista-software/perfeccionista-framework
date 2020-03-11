package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.OpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.TapAvailable;

public interface MobileDropDownList extends MobileUnorderedList,
        TapAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {
}
