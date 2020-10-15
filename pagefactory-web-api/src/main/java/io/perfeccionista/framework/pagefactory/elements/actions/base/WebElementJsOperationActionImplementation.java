package io.perfeccionista.framework.pagefactory.elements.actions.base;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.jetbrains.annotations.NotNull;

public interface WebElementJsOperationActionImplementation<T> extends WebElementActionImplementation<T> {

    @NotNull JsOperation<T> getJsOperation(@NotNull WebChildElementBase element, Object... args);

}