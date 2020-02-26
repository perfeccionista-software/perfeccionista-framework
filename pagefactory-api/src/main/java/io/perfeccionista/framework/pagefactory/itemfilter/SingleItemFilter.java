package io.perfeccionista.framework.pagefactory.itemfilter;

@Deprecated
public interface SingleItemFilter<C, R> {

    R getBlockIndex(C conditions);

}
