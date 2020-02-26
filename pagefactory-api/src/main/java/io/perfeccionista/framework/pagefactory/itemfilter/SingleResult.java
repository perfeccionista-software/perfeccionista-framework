package io.perfeccionista.framework.pagefactory.itemfilter;

public interface SingleResult<T> {

    /**
     * -1 если элемент всего один или индекса быть не может
     * @return
     */
    int getItemIndex();

    T getItem();

    String getElementHash();

}
