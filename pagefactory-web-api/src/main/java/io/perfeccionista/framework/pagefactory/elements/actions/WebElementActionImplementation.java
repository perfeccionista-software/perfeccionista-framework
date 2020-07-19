package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;

import java.util.Optional;

public interface WebElementActionImplementation<T> {

    T execute(WebChildElement element, Object... args);

    // TODO: Возможно, этот метод лучше куда-то вынести или переделать,
    //  чтобы он не участвовал во всех имплементациях, поскольку нужен только для гет-запросов к элементам
    Optional<JsOperation<T>> getJsOperation(WebChildElement element, Object... args);

}