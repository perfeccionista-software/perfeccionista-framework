package io.perfeccionista.framework.pagefactory.operation.handler;

import io.perfeccionista.framework.json.JsonSerializable;

// TODO: Получает все значения из типа при создании
public interface EndpointHandler<T> extends JsonSerializable {

    T handle(Object endpoint);

}

