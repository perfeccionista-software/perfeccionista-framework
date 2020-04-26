package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.number.NumberValue;

public interface FilterResult  {

    FilterResult shouldHaveSize(NumberValue<Integer> integerValue);

}
