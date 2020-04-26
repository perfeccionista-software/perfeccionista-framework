package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.Element;

public interface Filter<I extends Element, R extends FilterResult> {

    R filter(I element);

}
