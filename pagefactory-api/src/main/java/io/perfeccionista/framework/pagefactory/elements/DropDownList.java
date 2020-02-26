package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsOpenAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.OpenAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;

public interface DropDownList<F extends Filter<?, ?>> extends UnorderedList<F>,
        ClickAvailable, GetTextAvailable, GetLabelAvailable, IsOpenAvailable, OpenAvailable, CloseAvailable {
}
