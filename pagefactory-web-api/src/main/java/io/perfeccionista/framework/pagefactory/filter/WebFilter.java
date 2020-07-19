package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.number.NumberValue;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface WebFilter {

    @API(status = Status.INTERNAL)
    @NotNull WebFilterResult getResult();

    @API(status = Status.STABLE)
    WebFilter setInitialHash(@Nullable String initialHash);

    @API(status = Status.STABLE)
    WebFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize);

}