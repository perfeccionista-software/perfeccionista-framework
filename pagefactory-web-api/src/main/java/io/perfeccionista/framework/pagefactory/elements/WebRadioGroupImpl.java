package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.matcher.methods.WebElementStateAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebGetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebIsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.methods.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebRadioGroupMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebRadioGroupFrame;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioGroupMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;

import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.Web.*;

public class WebRadioGroupImpl extends AbstractWebChildElement implements WebRadioGroup {

    protected WebRadioGroupFrame<DefaultWebRadioButtonBlock> webRadioGroupFrame;

    @Override
    public @NotNull WebRadioGroupFrame<DefaultWebRadioButtonBlock> getBlockFrame() {
        return webRadioGroupFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebRadioGroup> extractAll(@NotNull WebRadioButtonValueExtractor<V> extractor) {
        return WebRadioGroupMultipleIndexedResult.of(this, extractor);
    }

    // Filter

    @Override
    public @NotNull WebRadioGroupFilter filterBuilder(@NotNull WebRadioGroupFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    @Override
    public @NotNull WebRadioGroupFilter filter(@NotNull WebRadioButtonCondition filterCondition) {
        return with(filterCondition).build(this);
    }

    // Button Extractors

    @Override
    public @NotNull WebRadioButton getSelected() {
        //noinspection ConstantConditions
        return WebRadioGroupMultipleIndexedResult.of(this, with(selected()), element())
                .singleResult()
                .getResult();
    }

    @Override
    public @NotNull WebRadioButton getByIndex(int expectedIndex) {
        //noinspection ConstantConditions
        return WebRadioGroupMultipleIndexedResult.of(this, with(radioButtonIndex(expectedIndex)), element())
                .singleResult()
                .getResult();
    }

    @Override
    public @NotNull WebRadioButton getByLabel(@NotNull String expectedText) {
        //noinspection ConstantConditions
        return WebRadioGroupMultipleIndexedResult.of(this, with(containsLabel(expectedText)), element())
                .singleResult()
                .getResult();
    }

    // Actions

    @Override
    public WebRadioGroup executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    // Asserts

    @Override
    public WebRadioGroup should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        filter(allRadioButtons()).should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebRadioGroupMatcher matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebIndexesMatcher matcher) {
        matcher.check(WebRadioGroupMultipleIndexedResult.of(this, index()));
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebChildElementMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebGetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebGetElementBoundsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebGetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebIsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebIsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebIsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebIsPresentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebComponentAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebElementPropertyAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull WebElementStateAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    // HoverTo

    @Override
    public WebRadioGroup hoverTo(boolean withOutOfBounds) {
        super.hoverTo(withOutOfBounds);
        return this;
    }

    // ScrollTo

    @Override
    public WebRadioGroup scrollTo() {
        super.scrollTo();
        return this;
    }

    // Size

    @Override
    public int size() {
        return extractAll(index()).getSize();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mappedBlock", webRadioGroupFrame.getClass().getCanonicalName());
        return rootNode;
    }

}
