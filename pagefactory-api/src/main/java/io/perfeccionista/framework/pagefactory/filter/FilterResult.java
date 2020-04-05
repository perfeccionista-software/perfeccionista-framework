package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.number.NumberValue;

public interface FilterResult<T extends Filter<?, ?>>  {

    FilterResult<T> shouldHaveSize(NumberValue<Integer> integerValue);

}
