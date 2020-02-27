package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface WebPage extends WebParentElement {

        ElementsConfiguration getElementsConfiguration();

        OperationResult<Boolean> isPageOpen();

}
