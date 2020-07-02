package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

// TODO: Возможно, тут нужны еще методы с передачей туда кастомного сообщения об ошибке
public interface SingleResult<T> {

    int getIndex();

    T get();

    <R> SingleResult<R> convert(@NotNull Function<T, R> converter);

    SingleResult<T> shouldHaveIndex(@NotNull Value<Integer> integerValue);

    SingleResult<T> shouldHaveResult(@NotNull Value<T> value);

    SingleResult<T> shouldHaveNull();

    SingleResult<T> shouldHaveNotNull();

}
