package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebParentElement;

public interface WebSearchContextLimiter extends SearchContextLimiter<WebParentElement> {

    WebParentElement getContext();

}
