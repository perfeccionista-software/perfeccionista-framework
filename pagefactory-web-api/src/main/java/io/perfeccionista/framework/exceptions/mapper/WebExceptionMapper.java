package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import org.jetbrains.annotations.NotNull;

public interface WebExceptionMapper extends ExceptionMapper {

    @NotNull PerfeccionistaRuntimeException mapElementException(@NotNull WebChildElementBase element, @NotNull RuntimeException exception);

    @NotNull PerfeccionistaRuntimeException mapPageException(@NotNull WebPage page, @NotNull RuntimeException exception);

}
