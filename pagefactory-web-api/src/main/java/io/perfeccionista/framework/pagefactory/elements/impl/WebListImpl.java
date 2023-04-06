package io.perfeccionista.framework.pagefactory.elements.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.conditions.WebElementCondition;
import io.perfeccionista.framework.conditions.WebElementConditionExceptionHandler;
import io.perfeccionista.framework.conditions.WebListElementCondition;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebListFrame;
import io.perfeccionista.framework.pagefactory.elements.options.HoverOptions;
import io.perfeccionista.framework.pagefactory.elements.options.ScrollOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.emulator.keys.Key;
import io.perfeccionista.framework.pagefactory.extractor.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.invocation.wrapper.SingleAttemptInvocationWrapper.runInvocation;
import static io.perfeccionista.framework.utils.WebElementUtils.checkAndCollect;

public class WebListImpl<T extends WebBlock<?>> extends AbstractWebChildElement implements WebList<T> {

    protected WebListFrame<T> webItemFrame;

    @Override
    public @NotNull WebListFrame<T> getItemFrame() {
        return webItemFrame;
    }

    // Extractor

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull WebItemValueExtractor<R, T> extractor) {
        return WebListMultipleIndexedResult.of(this, extractor);
    }

    @Override
    public @NotNull <R> WebMultipleIndexedResult<R, WebList<T>> extractAll(@NotNull Function<T, ? extends WebItemValueExtractor<R, T>> extractorFunction) {
        return WebListMultipleIndexedResult.of(this, extractorFunction.apply(getItemFrame().getMappedItemFrame()));
    }

    // Filter
    @Override
    public @NotNull WebListFilter<T> filterBuilder(@NotNull WebListFilterBuilder<T> filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull WebListFilter<T> filterBuilder(@NotNull Function<T, ? extends WebListFilterBuilder<T>> filterBuilderFunction) {
        return filterBuilderFunction.apply(getItemFrame().getMappedItemFrame())
                .build(this);
    }

    @Override
    public @NotNull WebListFilter<T> filter(@NotNull WebItemCondition<T> filterCondition) {
        return with(filterCondition).build(this);
    }

    @Override
    public @NotNull WebListFilter<T> filter(@NotNull Function<T, ? extends WebItemCondition<T>> filterConditionFunction) {
        return with(filterConditionFunction.apply(getItemFrame().getMappedItemFrame()))
                .build(this);
    }

    // Checks

    @Override
    public WebList<T> forEach(@NotNull Consumer<T> blockConsumer) {
        filterBuilder(block -> emptyWebBlockFilter())
                .forEach(blockConsumer);
        return this;
    }

    @Override
    public WebList<T> forFirst(@NotNull Consumer<T> blockConsumer) {
        filterBuilder(block -> emptyWebBlockFilter())
                .forFirst(blockConsumer);
        return this;
    }

    @Override
    public WebList<T> forLast(@NotNull Consumer<T> blockConsumer) {
        filterBuilder(block -> emptyWebBlockFilter())
                .forLast(blockConsumer);
        return this;
    }

    // Actions

    @Override
    public WebList<T> executeAction(@NotNull String name, Object... args) {
        super.executeAction(name, args);
        return this;
    }

    // Add

    @Override
    public WebList<T> addName(@NotNull String elementName) {
        super.addName(elementName);
        return this;
    }

    @Override
    public WebList<T> addComponent(@NotNull String componentName, @NotNull WebSelectorHolder selector) {
        super.addComponent(componentName, selector);
        return this;
    }

    // Asserts

    @Override
    public WebList<T> should(@NotNull WebElementCondition... conditions) {
        super.should(conditions);
        return this;
    }

    @Override
    public WebList<T> should(@NotNull WebListElementCondition... conditions) {
        checkAndCollect(conditions).forEach(condition -> {
            condition.validate(this);
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            repeatInvocation(invocationInfo, () -> condition.check(this, invocationInfo));
        });
        return this;
    }

    @Override
    public WebList<T> shouldNot(@NotNull WebElementCondition... conditions) {
        super.shouldNot(conditions);
        return this;
    }

    @Override
    public WebList<T> shouldNot(@NotNull WebListElementCondition... conditions) {
        checkAndCollect(conditions).forEach(condition -> {
            condition.validate(this)
                    .inverse();
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            repeatInvocation(invocationInfo, () -> condition.check(this, invocationInfo));
        });
        return this;
    }

    // Checks

    @Override
    public boolean check(@NotNull WebListElementCondition... conditions) {
        return checkAndCollect(conditions).stream().allMatch(condition -> {
            condition.validate(this);
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            return runInvocation(invocationInfo, () -> WebElementConditionExceptionHandler.of(() -> condition.check(this, invocationInfo))
                    .isSuccess());
        });
    }

    @Override
    public boolean checkNot(@NotNull WebListElementCondition... conditions) {
        return checkAndCollect(conditions).stream().allMatch(condition -> {
            condition.validate(this)
                    .inverse();
            InvocationInfo invocationInfo = condition.createInvocationInfoForElement(this);
            return runInvocation(invocationInfo, () -> WebElementConditionExceptionHandler.of(() -> condition.check(this, invocationInfo))
                    .isSuccess());
        });
    }

    // HoverTo

    @Override
    public WebList<T> hoverTo() {
        super.hoverTo();
        return this;
    }

    @Override
    public WebList<T> hoverTo(@NotNull HoverOptions options) {
        super.hoverTo(options);
        return this;
    }

    // PressKey

    @Override
    public WebList<T> press(@NotNull Key key) {
        super.press(key);
        return this;
    }

    // ScrollTo

    @Override
    public WebList<T> scrollTo() {
        super.scrollTo();
        return this;
    }

    @Override
    public WebList<T> scrollTo(@NotNull ScrollOptions options) {
        super.scrollTo(options);
        return this;
    }

    // Size

    @Override
    public int size() {
        return extractAll(blockIndex()).getSize();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        if (Objects.nonNull(webItemFrame)) {
            rootNode.put("mappedBlock", webItemFrame.getMappedItemFrame().getClass().getCanonicalName());
        }
        return rootNode;
    }



    // TODO Эти методы вынести в WebListElementCondition
//    @Override
//    public WebList<T> should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
//        filterBuilder(block -> emptyWebBlockFilter()).should(matcher);
//        return this;
//    }
//
//    @Override
//    public WebList<T> should(@NotNull WebListMatcher matcher) {
//        matcher.check(this);
//        return this;
//    }
//
//    @Override
//    public WebList<T> should(@NotNull WebIndexesMatcher matcher) {
//        matcher.check(WebListMultipleIndexedResult.of(this, blockIndex()));
//        return this;
//    }



}
