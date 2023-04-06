package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.conditions.WebListElementCondition;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WebList<T extends WebBlock<?>> extends WebChildElement {

    @API(status = Status.MAINTAINED)
    @NotNull WebListFrame<T> getItemFrame();

    // Extractor
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebItemValueExtractor<R, T> extractor);
    @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, ? extends WebItemValueExtractor<R, T>> extractorFunction);

    // Filter
    @NotNull WebListFilter<T> filterBuilder(@NotNull WebListFilterBuilder<T> filterBuilder);
    @NotNull WebListFilter<T> filterBuilder(@NotNull Function<T, ? extends WebListFilterBuilder<T>> filterBuilderFunction);
    @NotNull WebListFilter<T> filter(@NotNull WebItemCondition<T> filterCondition);
    @NotNull WebListFilter<T> filter(@NotNull Function<T, ? extends WebItemCondition<T>> filterConditionFunction);

    // Checks
    WebList<T> forEach(@NotNull Consumer<T> blockConsumer);
    WebList<T> forFirst(@NotNull Consumer<T> blockConsumer);
    WebList<T> forLast(@NotNull Consumer<T> blockConsumer);

    // Actions
    @Override
    WebList<T> executeAction(@NotNull String name, Object... args);

    // Add
    @Override
    WebList<T> addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector);
    @Override
    WebList<T> addName(@NotNull String elementName);

    // Asserts
    @Override
    WebList<T> should(@NotNull WebElementCondition... conditions);
    WebList<T> should(@NotNull WebListElementCondition... conditions);
    @Override
    WebList<T> shouldNot(@NotNull WebElementCondition... conditions);
    WebList<T> shouldNot(@NotNull WebListElementCondition... conditions);

    // Checks
    boolean check(@NotNull WebListElementCondition... conditions);
    boolean checkNot(@NotNull WebListElementCondition... conditions);

    // HoverTo
    @Override
    WebList<T> hoverTo();
    @Override
    WebList<T> hoverTo(@NotNull HoverOptions options);

    // PressKey
    @Override
    WebList<T> press(@NotNull Key key);

    // ScrollTo
    @Override
    WebList<T> scrollTo();
    @Override
    WebList<T> scrollTo(@NotNull ScrollOptions options);

    // Size
    int size();

//    static WebList<WebNode> list(@NotNull WebSelectorHolder rootSelector, @NotNull WebSelectorHolder itemSelector) {
//        return WebPageService.getInstance().createWebList(rootSelector, itemSelector);
//    }

}
