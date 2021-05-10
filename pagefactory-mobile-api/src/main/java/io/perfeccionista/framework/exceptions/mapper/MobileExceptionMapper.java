package io.perfeccionista.framework.exceptions.mapper;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import org.jetbrains.annotations.NotNull;

public interface MobileExceptionMapper extends ExceptionMapper {

    @NotNull PerfeccionistaRuntimeException mapElementException(@NotNull MobileChildElementBase element, @NotNull RuntimeException exception);

    @NotNull PerfeccionistaRuntimeException mapPageException(@NotNull MobilePage page, @NotNull RuntimeException exception);


}
