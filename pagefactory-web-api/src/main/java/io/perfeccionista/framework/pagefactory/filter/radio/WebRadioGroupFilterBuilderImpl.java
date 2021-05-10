package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebRadioGroupFilterBuilderImpl implements WebRadioGroupFilterBuilder {

    private final Deque<WebRadioButtonFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private WebRadioGroupFilterBuilderImpl() {
    }

    public static WebRadioGroupFilterBuilderImpl webRadioGroupFilterBuilder() {
        return new WebRadioGroupFilterBuilderImpl();
    }

    public WebRadioGroupFilterBuilder add(@NotNull WebRadioButtonCondition condition) {
        this.conditions.addLast(WebRadioButtonFilterResultGroupingHolder.of(FilterResultGrouping.ADD, condition));
        return this;
    }

    public WebRadioGroupFilterBuilder subtract(@NotNull WebRadioButtonCondition condition) {
        this.conditions.addLast(WebRadioButtonFilterResultGroupingHolder.of(FilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebRadioGroupFilter build(@NotNull WebRadioGroup element) {
        return WebRadioGroupFilterImpl.of(element, this);
    }

    public Deque<WebRadioButtonFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
