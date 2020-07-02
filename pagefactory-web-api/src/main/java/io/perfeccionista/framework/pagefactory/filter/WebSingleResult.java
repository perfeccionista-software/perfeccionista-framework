package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class WebSingleResult<T> implements SingleResult<T> {

    private final int index;
    private final T value;

    public WebSingleResult(int index, T value) {
        this.index = index;
        this.value = value;
    }

    public WebSingleResult() {
        this.index = -1;
        this.value = null;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public <R> SingleResult<R> convert(@NotNull Function<T, R> converter) {
        return new WebSingleResult<>(index, converter.apply(value));
    }

    @Override
    public SingleResult<T> shouldHaveIndex(@NotNull Value<Integer> integerValue) {
        return null;
    }

    @Override
    public SingleResult<T> shouldHaveResult(@NotNull Value<T> value) {
        return null;
    }

    @Override
    public SingleResult<T> shouldHaveNull() {
        return null;
    }

    @Override
    public SingleResult<T> shouldHaveNotNull() {
        // TODO: Implement
        return this;
    }

    @Override
    public String toString() {
        return "WebSingleResult: [\n"
                + String.format("%12s", index) + " -> " + value + "\n]";
    }
}
