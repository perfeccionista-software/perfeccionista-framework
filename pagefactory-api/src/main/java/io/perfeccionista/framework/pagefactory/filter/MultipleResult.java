package io.perfeccionista.framework.pagefactory.filter;

import java.util.Map;

public interface MultipleResult<T> {

    Map<Integer, T> get();

    String getElementHash();

    static <T> MultipleResult<T> of(Map<Integer, T> result) {
        return null;
    }

    static <T> MultipleResult<T> empty() {
        return null;
    }

}
