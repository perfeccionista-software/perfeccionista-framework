package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public interface WebFilter<I extends WebChildElement, R extends FilterResult> {

    R filter(I element);

}
