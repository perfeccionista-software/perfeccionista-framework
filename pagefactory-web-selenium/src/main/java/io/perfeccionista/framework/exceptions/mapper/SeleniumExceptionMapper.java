package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.exceptions.js.ElementSearchJsException;
import io.perfeccionista.framework.exceptions.js.ElementStateJsException;
import io.perfeccionista.framework.exceptions.js.FunctionCallJsException;
import io.perfeccionista.framework.exceptions.js.IncorrectSearchQueryJsException;
import io.perfeccionista.framework.exceptions.js.JsExecutionException;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SeleniumExceptionMapper implements ExceptionMapper {

    @Override
    public <T> T map(Supplier<T> supplier) {
        return null;
    }

    @Override
    public void map(Runnable runnable) {

    }

    @Override
    public PerfeccionistaException createByName(@NotNull String name, @NotNull String message) {
        if ("IncorrectSearchQueryError".equals(name)) {
            return new IncorrectSearchQueryJsException(message);
        } else if ("ElementSearchError".equals(name)) {
            return new ElementSearchJsException(message);
        } else if ("ElementStateError".equals(name)) {
            return new ElementStateJsException(message);
        } else if ("FunctionCallError".equals(name)) {
            return new FunctionCallJsException(message);
        } else {
            return new JsExecutionException(message);
        }
    }

}
