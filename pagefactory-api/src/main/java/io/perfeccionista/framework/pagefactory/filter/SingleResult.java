package io.perfeccionista.framework.pagefactory.filter;

public interface SingleResult<T> {

    /**
     * -1 если элемент всего один или индекса быть не может
     * @return
     */
    int getIndex();

    T get();

    String getElementHash();

}
