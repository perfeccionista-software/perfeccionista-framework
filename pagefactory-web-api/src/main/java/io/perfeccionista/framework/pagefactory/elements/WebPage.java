package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.context.WebSearchLimiterRegistry;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface WebPage extends WebParentElement {

    WebSearchLimiterRegistry getSearchLimiterRegistry();

    ElementsConfiguration getElementsConfiguration();

    OperationResult<Boolean> isPageOpen();

}
