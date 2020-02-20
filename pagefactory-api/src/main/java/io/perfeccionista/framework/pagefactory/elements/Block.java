package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;

public interface Block extends ParentElement, ChildElement {

    int getIndex();

}
