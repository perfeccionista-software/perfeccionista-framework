package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.SingleResultConvertingException;
import io.perfeccionista.framework.value.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.MULTIPLE_RESULT_HAS_MORE_THAN_ONE_VALUE;

public class WebMultipleResult<T> implements MultipleResult<T> {

    private final Map<Integer, T> values;

    public WebMultipleResult(Map<Integer, T> values) {
        this.values = values;
    }

    public WebMultipleResult() {
        this.values = new HashMap<>();
    }

    @Override
    public Map<Integer, T> getValues() {
        return Map.copyOf(values);
    }

    @Override
    public SingleResult<T> singleResult() {
        if (values.size() > 1) {
            throw new SingleResultConvertingException(MULTIPLE_RESULT_HAS_MORE_THAN_ONE_VALUE.getMessage())
                    .setProcessed(true)
                    .addAttachmentEntry(StringAttachmentEntry.of("Values", toString()));
        }
        return values.entrySet().stream()
                .findFirst()
                .map(entry -> new WebSingleResult<>(entry.getKey(), entry.getValue()))
                .orElseGet(WebSingleResult::new);
    }

    @Override
    public <R> MultipleResult<R> convert(@NotNull Function<T, R> converter) {
        Map<Integer, R> convertedValues = values.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> converter.apply(entry.getValue())));
        return new WebMultipleResult<>(convertedValues);
    }

    @Override
    public MultipleResult<T> shouldHaveIndex(@NotNull Value<Integer> indexValue) {
        return null;
    }

    @Override
    public MultipleResult<T> shouldHaveResult(@NotNull Value<T> value) {
        return null;
    }

    @Override
    public MultipleResult<T> shouldHaveSize(@NotNull Value<Integer> integerValue) {
        return null;
    }

    @Override
    public MultipleResult<T> shouldBeSorted(@NotNull Comparator<T> comparator) {
        return null;
    }

    @Override
    public String toString() {
        return "WebMultipleResult: [\n"
                + values.entrySet().stream()
                .map(entry -> String.format("%12s", entry.getKey()) + " -> " + entry.getValue())
                .collect(Collectors.joining("\n")) + ']';
    }

}
