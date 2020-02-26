package io.perfeccionista.framework.pagefactory.itemfilter;

import java.util.Map;

public interface MultipleResult<T> {

    Map<Integer, T> getItems();

    String getElementHash();

}
