package io.perfeccionista.framework.pagefactory.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.matcher.actions.GetColorAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebChildElementMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentAvailableMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.matcher.element.WebRadioGroupMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebRadioGroupFrame;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioGroupMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;

import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.element;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.index;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.containsLabel;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.radioButtonIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterConditions.selected;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.with;

public class WebRadioGroupImpl extends AbstractWebChildElement implements WebRadioGroup {

    protected WebRadioGroupFrame<DefaultWebRadioButtonBlock> webRadioGroupFrame;

    @Override
    public @NotNull WebRadioGroupFrame<DefaultWebRadioButtonBlock> getWebRadioGroupFrame() {
        return webRadioGroupFrame;
    }

    // Extractor

    @Override
    public @NotNull <V> WebMultipleIndexedResult<V, WebRadioGroup> extractAll(@NotNull WebRadioButtonValueExtractor<V> extractor) {
        return WebRadioGroupMultipleIndexedResult.of(this, extractor);
    }

    // Filter

    @Override
    public @NotNull WebRadioGroupFilter filter(@NotNull WebRadioGroupFilterBuilder filterBuilder) {
        return filterBuilder.build(this);
    }

    // Button Extractors

    @Override
    public @NotNull WebRadioButton getSelected() {
        //noinspection ConstantConditions
        return WebRadioGroupMultipleIndexedResult.of(this, with(selected()), element())
                .singleResult()
                .getValue();
    }

    @Override
    public @NotNull WebRadioButton getByIndex(int expectedIndex) {
        //noinspection ConstantConditions
        return WebRadioGroupMultipleIndexedResult.of(this, with(radioButtonIndex(expectedIndex)), element())
                .singleResult()
                .getValue();
    }

    @Override
    public @NotNull WebRadioButton getByLabel(@NotNull String expectedText) {
        //noinspection ConstantConditions
        return WebRadioGroupMultipleIndexedResult.of(this, with(containsLabel(expectedText)), element())
                .singleResult()
                .getValue();
    }

    // Actions

    @Override
    public WebRadioGroup executeAction(@NotNull String actionName, Object... args) {
        super.executeAction(actionName, args);
        return this;
    }

    @Override
    public WebRadioGroup executeInteraction(@NotNull String interactionName, @NotNull WebChildElement other, Object... args) {
        super.executeInteraction(interactionName, other, args);
        return this;
    }

    // Asserts

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
    public WebRadioGroup should(@NotNull GetColorAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull GetDimensionsAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull GetLocationAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull GetScreenshotAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull IsDisplayedAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull IsInFocusAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull IsOnTheScreenAvailableMatcher matcher) {
        super.should(matcher);
        return this;
    }

    @Override
    public WebRadioGroup should(@NotNull IsPresentAvailableMatcher matcher) {
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

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = (ObjectNode) super.toJson();
        rootNode.put("mappedBlock", webRadioGroupFrame.getClass().getCanonicalName());
        return rootNode;
    }

}
