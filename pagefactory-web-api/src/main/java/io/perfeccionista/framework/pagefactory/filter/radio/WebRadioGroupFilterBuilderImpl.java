package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.Web.allRadioButtons;

public class WebRadioGroupFilterBuilderImpl implements WebRadioGroupFilterBuilder {

    private final Deque<WebRadioButtonFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private WebRadioGroupFilterBuilderImpl() {
    }

    public static WebRadioGroupFilterBuilder webRadioGroupFilterBuilderWith(@NotNull WebRadioButtonCondition condition) {
        return new WebRadioGroupFilterBuilderImpl()
                .add(condition);
    }

    public static WebRadioGroupFilterBuilder webRadioGroupFilterBuilderWithout(@NotNull WebRadioButtonCondition condition) {
        return new WebRadioGroupFilterBuilderImpl()
                .add(allRadioButtons())
                .subtract(condition);
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
