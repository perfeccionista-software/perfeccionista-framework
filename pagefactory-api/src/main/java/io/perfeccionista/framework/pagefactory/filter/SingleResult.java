package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.Value;

public interface SingleResult<T> {

    /**
     * -1 если элемент всего один или индекса быть не может
     * @return
     */
    int getIndex();

    T get();

    String getElementHash();

    SingleResult<T> shouldHaveIndex(Value<Integer> integerValue);

    SingleResult<T> shouldHaveResult(Value<T> value);

    SingleResult<T> shouldHaveNull();

    SingleResult<T> shouldHaveNotNull();

}
