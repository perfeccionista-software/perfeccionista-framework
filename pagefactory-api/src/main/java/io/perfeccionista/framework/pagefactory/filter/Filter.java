package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;

public interface Filter<I, R> {

    SingleResult<R> singleResult(I inputData) throws PerfeccionistaException;

    MultipleResult<R> multipleResult(I inputData) throws PerfeccionistaException;

}
