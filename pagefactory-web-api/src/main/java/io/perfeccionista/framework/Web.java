package io.perfeccionista.framework;

import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserActiveTabShouldHaveTitleStringMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserActiveTabShouldHaveTitleStringValueMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserActiveTabShouldHaveUrlStringMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserActiveTabShouldHaveUrlStringValueMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserShouldHaveTabCountNumberMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserShouldHaveTabCountNumberValueMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserShouldHaveTabWithTitleStringMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserShouldHaveTabWithTitleStringValueMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserShouldHaveTabWithUrlStringMatcher;
import io.perfeccionista.framework.matcher.dispatcher.implementations.WebBrowserShouldHaveTabWithUrlStringValueMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebComponentShouldBeDisplayedMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebComponentShouldBePresentMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBeClosedMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBeDisplayedMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBeEnabledMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBeInFocusMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBeOnTheScreenMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBeOpenMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBePresentMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldBeSelectedMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveAbsoluteLocationMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveCenterLocationMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveColorMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveDimensionsMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveLabelNumberValueMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveLabelStringMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveLabelStringValueMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHavePropertyNumberValueMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHavePropertyStringMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHavePropertyStringValueMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveScreenLocationMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveStateMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveTextNumberValueMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveTextStringMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldHaveTextStringValueMatcher;
import io.perfeccionista.framework.matcher.methods.implementations.WebShouldLooksLikeMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveIndexNumberMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveIndexNumberValueMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveNonNullResultMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveNullResultMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveExpectedResultMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveSizeNumberMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveSizeNumberValueMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveSortedResultMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveStringValueResultMatcher;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementComponentDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementComponentPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockTextValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementHaveStateCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementPropertyNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementPropertyStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementPropertyTextCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockElementTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockTextCondition;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.limiter.WebListBlockContextLimiter;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.function.Function;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilderImpl.webBlockFilterBuilderWith;
import static io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilterBuilderImpl.webBlockFilterBuilderWithout;
import static io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderImpl.webRadioGroupFilterBuilderWith;
import static io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderImpl.webRadioGroupFilterBuilderWithout;
import static io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilderImpl.webTextBlockFilterBuilderWith;
import static io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilderImpl.webTextBlockFilterBuilderWithout;
import static io.perfeccionista.framework.value.Values.intEquals;

// TODO: Возможно, тут стоит вынести отдельные разделы в классы
public class Web {

    private Web() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Assertions
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Element
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Method
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // GetColor

    public static WebShouldHaveColorMatcher haveColor(@NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorMatcher(ROOT, cssProperty, expectedColor, true);
    }

    public static WebShouldHaveColorMatcher haveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorMatcher(componentName, cssProperty, expectedColor, true);
    }

    public static WebShouldHaveColorMatcher notHaveColor(@NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorMatcher(ROOT, cssProperty, expectedColor, false);
    }

    public static WebShouldHaveColorMatcher notHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorMatcher(componentName, cssProperty, expectedColor, false);
    }

    // GetDimensions

    public static WebShouldHaveDimensionsMatcher haveDimensions(@NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsMatcher(ROOT, expectedDimensions, true);
    }

    public static WebShouldHaveDimensionsMatcher haveDimensions(@NotNull String componentName, @NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsMatcher(componentName, expectedDimensions, true);
    }

    public static WebShouldHaveDimensionsMatcher notHaveDimensions(@NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsMatcher(ROOT, expectedDimensions, false);
    }

    public static WebShouldHaveDimensionsMatcher notHaveDimensions(@NotNull String componentName, @NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsMatcher(componentName, expectedDimensions, false);
    }

    // GetLabel

    public static WebShouldHaveLabelStringMatcher haveLabel(@NotNull String expectedText) {
        return new WebShouldHaveLabelStringMatcher(expectedText, true);
    }

    public static WebShouldHaveLabelStringValueMatcher haveLabel(@NotNull StringValue expectedValue) {
        return new WebShouldHaveLabelStringValueMatcher(expectedValue, true);
    }

    public static WebShouldHaveLabelNumberValueMatcher haveLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveLabelNumberValueMatcher(expectedValue, true);
    }

    public static WebShouldHaveLabelStringMatcher notHaveLabel(@NotNull String expectedText) {
        return new WebShouldHaveLabelStringMatcher(expectedText, false);
    }

    public static WebShouldHaveLabelStringValueMatcher notHaveLabel(@NotNull StringValue expectedValue) {
        return new WebShouldHaveLabelStringValueMatcher(expectedValue, false);
    }

    public static WebShouldHaveLabelNumberValueMatcher notHaveLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveLabelNumberValueMatcher(expectedValue, false);
    }

    // GetLocation

    public static WebShouldHaveCenterLocationMatcher haveCenterLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationMatcher(ROOT, expectedLocation, true);
    }

    public static WebShouldHaveCenterLocationMatcher haveCenterLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationMatcher(componentName, expectedLocation, true);
    }

    public static WebShouldHaveCenterLocationMatcher notHaveCenterLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationMatcher(ROOT, expectedLocation, false);
    }

    public static WebShouldHaveCenterLocationMatcher notHaveCenterLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationMatcher(componentName, expectedLocation, false);
    }

    public static WebShouldHaveScreenLocationMatcher haveScreenLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationMatcher(ROOT, expectedLocation, true);
    }

    public static WebShouldHaveScreenLocationMatcher haveScreenLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationMatcher(componentName, expectedLocation, true);
    }

    public static WebShouldHaveScreenLocationMatcher notHaveScreenLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationMatcher(ROOT, expectedLocation, false);
    }

    public static WebShouldHaveScreenLocationMatcher notHaveScreenLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationMatcher(componentName, expectedLocation, false);
    }

    public static WebShouldHaveAbsoluteLocationMatcher haveAbsoluteLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationMatcher(ROOT, expectedLocation, true);
    }

    public static WebShouldHaveAbsoluteLocationMatcher haveAbsoluteLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationMatcher(componentName, expectedLocation, true);
    }

    public static WebShouldHaveAbsoluteLocationMatcher notHaveAbsoluteLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationMatcher(ROOT, expectedLocation, false);
    }

    public static WebShouldHaveAbsoluteLocationMatcher notHaveAbsoluteLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationMatcher(componentName, expectedLocation, false);
    }

    // GetScreenshot

    public static WebShouldLooksLikeMatcher looksLike(@NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeMatcher(ROOT, expectedScreenshot, true);
    }

    public static WebShouldLooksLikeMatcher looksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeMatcher(componentName, expectedScreenshot, true);
    }

    public static WebShouldLooksLikeMatcher notLooksLike(@NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeMatcher(ROOT, expectedScreenshot, false);
    }

    public static WebShouldLooksLikeMatcher notLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeMatcher(componentName, expectedScreenshot, false);
    }

    // GetText

    public static WebShouldHaveTextStringMatcher haveText(@NotNull String expectedText) {
        return new WebShouldHaveTextStringMatcher(expectedText, true);
    }

    public static WebShouldHaveTextStringValueMatcher haveText(@NotNull StringValue expectedValue) {
        return new WebShouldHaveTextStringValueMatcher(expectedValue, true);
    }

    public static WebShouldHaveTextNumberValueMatcher haveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveTextNumberValueMatcher(expectedValue, true);
    }

    public static WebShouldHaveTextStringMatcher notHaveText(@NotNull String expectedText) {
        return new WebShouldHaveTextStringMatcher(expectedText, false);
    }

    public static WebShouldHaveTextStringValueMatcher notHaveText(@NotNull StringValue expectedValue) {
        return new WebShouldHaveTextStringValueMatcher(expectedValue, false);
    }

    public static WebShouldHaveTextNumberValueMatcher notHaveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveTextNumberValueMatcher(expectedValue, false);
    }

    // IsDisplayed

    public static WebShouldBeDisplayedMatcher beDisplayed() {
        return new WebShouldBeDisplayedMatcher(true);
    }

    public static WebShouldBeDisplayedMatcher notBeDisplayed() {
        return new WebShouldBeDisplayedMatcher(false);
    }

    // IsEnabled

    public static WebShouldBeEnabledMatcher beEnabled() {
        return new WebShouldBeEnabledMatcher(true);
    }

    public static WebShouldBeEnabledMatcher beDisabled() {
        return new WebShouldBeEnabledMatcher(false);
    }

    // IsInFocus

    public static WebShouldBeInFocusMatcher beInFocus() {
        return new WebShouldBeInFocusMatcher(true);
    }

    public static WebShouldBeInFocusMatcher notBeInFocus() {
        return new WebShouldBeInFocusMatcher(false);
    }

    // IsOnTheScreen

    public static WebShouldBeOnTheScreenMatcher completelyBeOnTheScreen() {
        return new WebShouldBeOnTheScreenMatcher(true);
    }

    public static WebShouldBeOnTheScreenMatcher completelyNotBeOnTheScreen() {
        return new WebShouldBeOnTheScreenMatcher(false);
    }

    // IsOpen

    public static WebShouldBeOpenMatcher beOpen() {
        return new WebShouldBeOpenMatcher();
    }

    public static WebShouldBeClosedMatcher beClosed() {
        return new WebShouldBeClosedMatcher();
    }

    // IsPresent

    public static WebShouldBePresentMatcher bePresent() {
        return new WebShouldBePresentMatcher(true);
    }

    public static WebShouldBePresentMatcher notBePresent() {
        return new WebShouldBePresentMatcher(false);
    }

    // IsSelected

    public static WebShouldBeSelectedMatcher beSelected() {
        return new WebShouldBeSelectedMatcher(true);
    }

    public static WebShouldBeSelectedMatcher notBeSelected() {
        return new WebShouldBeSelectedMatcher(false);
    }

    // WebComponent

    public static WebComponentShouldBeDisplayedMatcher componentBeDisplayed(@NotNull String componentName) {
        return new WebComponentShouldBeDisplayedMatcher(componentName, true);
    }

    public static WebComponentShouldBeDisplayedMatcher componentNotBeDisplayed(@NotNull String componentName) {
        return new WebComponentShouldBeDisplayedMatcher(componentName, false);
    }

    public static WebComponentShouldBePresentMatcher componentBePresent(@NotNull String componentName) {
        return new WebComponentShouldBePresentMatcher(componentName, true);
    }

    public static WebComponentShouldBePresentMatcher componentNotBePresent(@NotNull String componentName) {
        return new WebComponentShouldBePresentMatcher(componentName, false);
    }

    // WebElementProperty

    public static WebShouldHavePropertyStringMatcher havePropertyValue(@NotNull String propertyName, @NotNull String expectedText) {
        return new WebShouldHavePropertyStringMatcher(propertyName, expectedText, true);
    }

    public static WebShouldHavePropertyStringValueMatcher havePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        return new WebShouldHavePropertyStringValueMatcher(propertyName, expectedValue, true);
    }

    public static WebShouldHavePropertyNumberValueMatcher havePropertyValue(@NotNull String propertyName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHavePropertyNumberValueMatcher(propertyName, expectedValue, true);
    }

    public static WebShouldHavePropertyStringMatcher notHavePropertyValue(@NotNull String propertyName, @NotNull String expectedText) {
        return new WebShouldHavePropertyStringMatcher(propertyName, expectedText, false);
    }

    public static WebShouldHavePropertyStringValueMatcher notHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        return new WebShouldHavePropertyStringValueMatcher(propertyName, expectedValue, false);
    }

    public static WebShouldHavePropertyNumberValueMatcher notHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHavePropertyNumberValueMatcher(propertyName, expectedValue, false);
    }

    // WebElementState

    public static WebShouldHaveStateMatcher haveState(@NotNull String stateName) {
        return new WebShouldHaveStateMatcher(stateName, true);
    }

    public static WebShouldHaveStateMatcher notHaveState(@NotNull String stateName) {
        return new WebShouldHaveStateMatcher(stateName, false);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Result
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Index

    public static WebShouldHaveIndexNumberMatcher haveIndex(@NotNull Integer expectedSize) {
        return new WebShouldHaveIndexNumberMatcher(expectedSize, true);
    }

    public static WebShouldHaveIndexNumberValueMatcher haveIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebShouldHaveIndexNumberValueMatcher(expectedValue, true);
    }

    public static WebShouldHaveIndexNumberMatcher notHaveIndex(@NotNull Integer expectedSize) {
        return new WebShouldHaveIndexNumberMatcher(expectedSize, false);
    }

    public static WebShouldHaveIndexNumberValueMatcher notHaveIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebShouldHaveIndexNumberValueMatcher(expectedValue, false);
    }

    // Size

    public static WebShouldHaveSizeNumberMatcher haveSize(@NotNull Integer expectedSize) {
        return new WebShouldHaveSizeNumberMatcher(expectedSize, true);
    }

    public static WebShouldHaveSizeNumberValueMatcher haveSize(@NotNull NumberValue<Integer> expectedValue) {
        return new WebShouldHaveSizeNumberValueMatcher(expectedValue, true);
    }

    public static WebShouldHaveSizeNumberMatcher notHaveSize(@NotNull Integer expectedSize) {
        return new WebShouldHaveSizeNumberMatcher(expectedSize, false);
    }

    public static WebShouldHaveSizeNumberValueMatcher notHaveSize(@NotNull NumberValue<Integer> expectedValue) {
        return new WebShouldHaveSizeNumberValueMatcher(expectedValue, false);
    }

    // Result

    // TODO: Добавить матчеры для результатов через Value
    public static <T> WebShouldHaveExpectedResultMatcher<T> haveResult(T expectedResult) {
        return new WebShouldHaveExpectedResultMatcher<>(expectedResult);
    }

    public static <T> WebShouldHaveNonNullResultMatcher<T> haveNotNullResults() {
        return new WebShouldHaveNonNullResultMatcher<>();
    }

    public static <T> WebShouldHaveNullResultMatcher<T> haveNullResults() {
        return new WebShouldHaveNullResultMatcher<>();
    }

    public static WebShouldHaveStringValueResultMatcher haveStringValueResult(@NotNull StringValue expectedValue) {
        return new WebShouldHaveStringValueResultMatcher(expectedValue);
    }

    // Sort

    public static <T> WebShouldHaveSortedResultMatcher<T> beSorted(@NotNull Comparator<T> comparator) {
        return new WebShouldHaveSortedResultMatcher<>(comparator);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Browser
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // ActiveTabShouldHaveTitle

    public static WebBrowserActiveTabShouldHaveTitleStringMatcher activeTabHaveTitle(@NotNull String expectedText) {
        return new WebBrowserActiveTabShouldHaveTitleStringMatcher(expectedText, true);
    }

    public static WebBrowserActiveTabShouldHaveTitleStringValueMatcher activeTabHaveTitle(@NotNull StringValue expectedValue) {
        return new WebBrowserActiveTabShouldHaveTitleStringValueMatcher(expectedValue, true);
    }

    public static WebBrowserActiveTabShouldHaveTitleStringMatcher activeTabNotHaveTitle(@NotNull String expectedText) {
        return new WebBrowserActiveTabShouldHaveTitleStringMatcher(expectedText, false);
    }

    public static WebBrowserActiveTabShouldHaveTitleStringValueMatcher activeTabNotHaveTitle(@NotNull StringValue expectedValue) {
        return new WebBrowserActiveTabShouldHaveTitleStringValueMatcher(expectedValue, false);
    }

    // ActiveTabShouldHaveUrl

    public static WebBrowserActiveTabShouldHaveUrlStringMatcher activeTabHaveUrl(@NotNull String expectedText) {
        return new WebBrowserActiveTabShouldHaveUrlStringMatcher(expectedText, true);
    }

    public static WebBrowserActiveTabShouldHaveUrlStringValueMatcher activeTabHaveUrl(@NotNull StringValue expectedValue) {
        return new WebBrowserActiveTabShouldHaveUrlStringValueMatcher(expectedValue, true);
    }

    public static WebBrowserActiveTabShouldHaveUrlStringMatcher activeTabNotHaveUrl(@NotNull String expectedText) {
        return new WebBrowserActiveTabShouldHaveUrlStringMatcher(expectedText, false);
    }

    public static WebBrowserActiveTabShouldHaveUrlStringValueMatcher activeTabNotHaveUrl(@NotNull StringValue expectedValue) {
        return new WebBrowserActiveTabShouldHaveUrlStringValueMatcher(expectedValue, false);
    }

    // BrowserShouldHaveTabWithTitle

    public static WebBrowserShouldHaveTabWithTitleStringMatcher haveTabWithTitle(@NotNull String expectedText) {
        return new WebBrowserShouldHaveTabWithTitleStringMatcher(expectedText, true);
    }

    public static WebBrowserShouldHaveTabWithTitleStringValueMatcher haveTabWithTitle(@NotNull StringValue expectedValue) {
        return new WebBrowserShouldHaveTabWithTitleStringValueMatcher(expectedValue, true);
    }

    public static WebBrowserShouldHaveTabWithTitleStringMatcher notHaveTabWithTitle(@NotNull String expectedText) {
        return new WebBrowserShouldHaveTabWithTitleStringMatcher(expectedText, false);
    }

    public static WebBrowserShouldHaveTabWithTitleStringValueMatcher notHaveTabWithTitle(@NotNull StringValue expectedValue) {
        return new WebBrowserShouldHaveTabWithTitleStringValueMatcher(expectedValue, false);
    }

    // BrowserShouldHaveTabWithUrl

    public static WebBrowserShouldHaveTabWithUrlStringMatcher haveTabWithUrl(@NotNull String expectedText) {
        return new WebBrowserShouldHaveTabWithUrlStringMatcher(expectedText, true);
    }

    public static WebBrowserShouldHaveTabWithUrlStringValueMatcher haveTabWithUrl(@NotNull StringValue expectedValue) {
        return new WebBrowserShouldHaveTabWithUrlStringValueMatcher(expectedValue, true);
    }

    public static WebBrowserShouldHaveTabWithUrlStringMatcher notHaveTabWithUrl(@NotNull String expectedText) {
        return new WebBrowserShouldHaveTabWithUrlStringMatcher(expectedText, false);
    }

    public static WebBrowserShouldHaveTabWithUrlStringValueMatcher notHaveTabWithUrl(@NotNull StringValue expectedValue) {
        return new WebBrowserShouldHaveTabWithUrlStringValueMatcher(expectedValue, false);
    }

    // Have tab count

    public static WebBrowserShouldHaveTabCountNumberMatcher haveTabCount(int expectedCount) {
        return new WebBrowserShouldHaveTabCountNumberMatcher(expectedCount, true);
    }

    public static WebBrowserShouldHaveTabCountNumberValueMatcher haveTabCount(@NotNull NumberValue<Integer> expectedCountValue) {
        return new WebBrowserShouldHaveTabCountNumberValueMatcher(expectedCountValue, true);
    }

    public static WebBrowserShouldHaveTabCountNumberMatcher notHaveTabCount(int expectedCount) {
        return new WebBrowserShouldHaveTabCountNumberMatcher(expectedCount, false);
    }

    public static WebBrowserShouldHaveTabCountNumberValueMatcher notHaveTabCount(@NotNull NumberValue<Integer> expectedCountValue) {
        return new WebBrowserShouldHaveTabCountNumberValueMatcher(expectedCountValue, false);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Filters
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Block

    public static <T extends WebBlock> WebBlockFilterBuilder<T> emptyWebBlockFilter() {
        return webBlockFilterBuilderWith(allItems());
    }

    public static <T extends WebBlock> WebBlockFilterBuilder<T> with(@NotNull WebBlockCondition<T> condition) {
        return webBlockFilterBuilderWith(condition);
    }

    public static <T extends WebBlock> WebBlockFilterBuilder<T> without(@NotNull WebBlockCondition<T> condition) {
        return webBlockFilterBuilderWithout(condition);
    }

    // TextBlock

    public static WebTextBlockFilterBuilder emptyWebTextBlockFilter() {
        return webTextBlockFilterBuilderWith(allTextBlocks());
    }

    public static WebTextBlockFilterBuilder with(@NotNull WebTextBlockCondition stringCondition) {
        return webTextBlockFilterBuilderWith(stringCondition);
    }

    public static WebTextBlockFilterBuilder without(@NotNull WebTextBlockCondition stringCondition) {
        return webTextBlockFilterBuilderWithout(stringCondition);
    }


    // RadioButton

    public static WebRadioGroupFilterBuilder emptyWebRadioButtonFilter() {
        return webRadioGroupFilterBuilderWith(allRadioButtons());
    }

    public static WebRadioGroupFilterBuilder with(@NotNull WebRadioButtonCondition condition) {
        return webRadioGroupFilterBuilderWith(condition);
    }

    public static WebRadioGroupFilterBuilder without(@NotNull WebRadioButtonCondition condition) {
        return webRadioGroupFilterBuilderWithout(condition);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   FilterConditions
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebRadioButton
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebRadioButtonEmptyCondition allRadioButtons() {
        return new WebRadioButtonEmptyCondition().allRadioButtons();
    }

    public static WebRadioButtonEmptyCondition noRadioButtons() {
        return new WebRadioButtonEmptyCondition().noRadioButtons();
    }

    // Index

    public static WebRadioButtonIndexCondition radioButtonIndex(@NotNull Integer expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withRadioButtonIndex();
    }

    public static WebRadioButtonIndexCondition radioButtonIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withRadioButtonIndex();
    }

    public static WebRadioButtonIndexCondition radioButtonIndexNot(@NotNull Integer expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withoutRadioButtonIndex();
    }

    public static WebRadioButtonIndexCondition radioButtonIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebRadioButtonIndexCondition(expectedValue).withoutRadioButtonIndex();
    }

    // Enabled

    public static WebRadioButtonEnabledCondition enabled() {
        return new WebRadioButtonEnabledCondition().enabled();
    }

    public static WebRadioButtonEnabledCondition disabled() {
        return new WebRadioButtonEnabledCondition().disabled();
    }

    // Label

    public static WebRadioButtonLabelTextCondition containsLabel(@NotNull String expectedText) {
        return new WebRadioButtonLabelTextCondition(expectedText).containsLabel();
    }

    public static WebRadioButtonLabelStringValueCondition containsLabel(@NotNull StringValue expectedValue) {
        return new WebRadioButtonLabelStringValueCondition(expectedValue).containsLabel();
    }

    public static WebRadioButtonLabelNumberValueCondition containsLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebRadioButtonLabelNumberValueCondition(expectedValue).containsLabel();
    }

    public static WebRadioButtonLabelTextCondition notContainLabel(@NotNull String expectedText) {
        return new WebRadioButtonLabelTextCondition(expectedText).notContainLabel();
    }

    public static WebRadioButtonLabelStringValueCondition notContainLabel(@NotNull StringValue expectedValue) {
        return new WebRadioButtonLabelStringValueCondition(expectedValue).notContainLabel();
    }

    public static WebRadioButtonLabelNumberValueCondition notContainLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebRadioButtonLabelNumberValueCondition(expectedValue).notContainLabel();
    }

    // Selected

    public static WebRadioButtonSelectedCondition selected() {
        return new WebRadioButtonSelectedCondition().selected();
    }

    public static WebRadioButtonSelectedCondition notSelected() {
        return new WebRadioButtonSelectedCondition().notSelected();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static <T extends WebBlock> WebBlockElementEmptyCondition<T> allItems() {
        return new WebBlockElementEmptyCondition<T>().allBlocks();
    }

    public static <T extends WebBlock> WebBlockElementEmptyCondition<T> noItems() {
        return new WebBlockElementEmptyCondition<T>().noBlocks();
    }

    // Index

    public static <T extends WebBlock> WebBlockIndexCondition<T> index(@NotNull Integer expectedValue) {
        return new WebBlockIndexCondition<T>(expectedValue).withBlockIndex();
    }

    public static <T extends WebBlock> WebBlockIndexCondition<T> index(@NotNull NumberValue<Integer> expectedValue) {
        return new WebBlockIndexCondition<T>(expectedValue).withBlockIndex();
    }

    public static <T extends WebBlock> WebBlockIndexCondition<T> indexNot(@NotNull Integer expectedValue) {
        return new WebBlockIndexCondition<T>(expectedValue).withoutBlockIndexNot();
    }

    public static <T extends WebBlock> WebBlockIndexCondition<T> indexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebBlockIndexCondition<T>(expectedValue).withoutBlockIndexNot();
    }

    // ComponentDisplayed

    public static <T extends WebBlock> WebBlockElementComponentDisplayedCondition<T> componentDisplayed(@NotNull WebChildElement elementFrame,
                                                                                                        @NotNull String componentName) {
        return new WebBlockElementComponentDisplayedCondition<T>(elementFrame, componentName).componentDisplayed();
    }

    public static <T extends WebBlock> WebBlockElementComponentDisplayedCondition<T> componentDisplayed(@NotNull String elementPath,
                                                                                                        @NotNull String componentName) {
        return new WebBlockElementComponentDisplayedCondition<T>(elementPath, componentName).componentDisplayed();
    }

    public static <T extends WebBlock> WebBlockElementComponentDisplayedCondition<T> componentNotDisplayed(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String componentName) {
        return new WebBlockElementComponentDisplayedCondition<T>(elementFrame, componentName).componentNotDisplayed();
    }

    public static <T extends WebBlock> WebBlockElementComponentDisplayedCondition<T> componentNotDisplayed(@NotNull String elementPath,
                                                                                                           @NotNull String componentName) {
        return new WebBlockElementComponentDisplayedCondition<T>(elementPath, componentName).componentNotDisplayed();
    }

    // ComponentPresent

    public static <T extends WebBlock> WebBlockElementComponentPresentCondition<T> componentPresent(@NotNull WebChildElement elementFrame,
                                                                                                    @NotNull String componentName) {
        return new WebBlockElementComponentPresentCondition<T>(elementFrame, componentName).componentPresent();
    }

    public static <T extends WebBlock> WebBlockElementComponentPresentCondition<T> componentPresent(@NotNull String elementPath,
                                                                                                    @NotNull String componentName) {
        return new WebBlockElementComponentPresentCondition<T>(elementPath, componentName).componentPresent();
    }

    public static <T extends WebBlock> WebBlockElementComponentPresentCondition<T> componentNotPresent(@NotNull WebChildElement elementFrame,
                                                                                                       @NotNull String componentName) {
        return new WebBlockElementComponentPresentCondition<T>(elementFrame, componentName).componentNotPresent();
    }

    public static <T extends WebBlock> WebBlockElementComponentPresentCondition<T> componentNotPresent(@NotNull String elementPath,
                                                                                                       @NotNull String componentName) {
        return new WebBlockElementComponentPresentCondition<T>(elementPath, componentName).componentNotPresent();
    }

    // Displayed

    public static <T extends WebBlock> WebBlockElementDisplayedCondition<T> displayed(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebBlockElementDisplayedCondition<T>(elementFrame).displayed();
    }

    public static <T extends WebBlock> WebBlockElementDisplayedCondition<T> displayed(@NotNull String elementPath) {
        return new WebBlockElementDisplayedCondition<T>(elementPath).displayed();
    }

    public static <T extends WebBlock> WebBlockElementDisplayedCondition<T> notDisplayed(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebBlockElementDisplayedCondition<T>(elementFrame).notDisplayed();
    }

    public static <T extends WebBlock> WebBlockElementDisplayedCondition<T> notDisplayed(@NotNull String elementPath) {
        return new WebBlockElementDisplayedCondition<T>(elementPath).notDisplayed();
    }

    // Present

    public static <T extends WebBlock> WebBlockElementPresentCondition<T> present(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebBlockElementPresentCondition<T>(elementFrame).present();
    }

    public static <T extends WebBlock> WebBlockElementPresentCondition<T> present(@NotNull String elementPath) {
        return new WebBlockElementPresentCondition<T>(elementPath).present();
    }

    public static <T extends WebBlock> WebBlockElementPresentCondition<T> notPresent(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebBlockElementPresentCondition<T>(elementFrame).notPresent();
    }

    public static <T extends WebBlock> WebBlockElementPresentCondition<T> notPresent(@NotNull String elementPath) {
        return new WebBlockElementPresentCondition<T>(elementPath).notPresent();
    }

    // Enabled

    public static <T extends WebBlock> WebBlockElementEnabledCondition<T> enabled(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebBlockElementEnabledCondition<T>(elementFrame).enabled();
    }

    public static <T extends WebBlock> WebBlockElementEnabledCondition<T> enabled(@NotNull String elementPath) {
        return new WebBlockElementEnabledCondition<T>(elementPath).enabled();
    }

    public static <T extends WebBlock> WebBlockElementEnabledCondition<T> disabled(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebBlockElementEnabledCondition<T>(elementFrame).disabled();
    }

    public static <T extends WebBlock> WebBlockElementEnabledCondition<T> disabled(@NotNull String elementPath) {
        return new WebBlockElementEnabledCondition<T>(elementPath).disabled();
    }

    // Selected

    public static <T extends WebBlock> WebBlockElementSelectedCondition<T> selected(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebBlockElementSelectedCondition<T>(elementFrame).selected();
    }

    public static <T extends WebBlock> WebBlockElementSelectedCondition<T> selected(@NotNull String elementPath) {
        return new WebBlockElementSelectedCondition<T>(elementPath).selected();
    }

    public static <T extends WebBlock> WebBlockElementSelectedCondition<T> notSelected(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebBlockElementSelectedCondition<T>(elementFrame).notSelected();
    }

    public static <T extends WebBlock> WebBlockElementSelectedCondition<T> notSelected(@NotNull String elementPath) {
        return new WebBlockElementSelectedCondition<T>(elementPath).notSelected();
    }

    // ContainsLabel

    public static <T extends WebBlock> WebBlockElementLabelTextCondition<T> containsLabel(@NotNull String elementPath,
                                                                                          @NotNull String expectedText) {
        return new WebBlockElementLabelTextCondition<T>(elementPath, expectedText).containsLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelTextCondition<T> containsLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                          @NotNull String expectedText) {
        return new WebBlockElementLabelTextCondition<T>(elementFrame, expectedText).containsLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelTextCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                            @NotNull String expectedText) {
        return new WebBlockElementLabelTextCondition<T>(elementPath, expectedText).notContainLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelTextCondition<T> notContainLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                            @NotNull String expectedText) {
        return new WebBlockElementLabelTextCondition<T>(elementFrame, expectedText).notContainLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelStringValueCondition<T> containsLabel(@NotNull String elementPath,
                                                                                                 @NotNull StringValue expectedStringValue) {
        return new WebBlockElementLabelStringValueCondition<T>(elementPath, expectedStringValue).containsLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelStringValueCondition<T> containsLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                 @NotNull StringValue expectedStringValue) {
        return new WebBlockElementLabelStringValueCondition<T>(elementFrame, expectedStringValue).containsLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelStringValueCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebBlockElementLabelStringValueCondition<T>(elementPath, expectedStringValue).notContainLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelStringValueCondition<T> notContainLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebBlockElementLabelStringValueCondition<T>(elementFrame, expectedStringValue).notContainLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelNumberValueCondition<T> containsLabel(@NotNull String elementPath,
                                                                                                 @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementLabelNumberValueCondition<T>(elementPath, expectedNumberValue).containsLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelNumberValueCondition<T> containsLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                 @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementLabelNumberValueCondition<T>(elementFrame, expectedNumberValue).containsLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelNumberValueCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementLabelNumberValueCondition<T>(elementPath, expectedNumberValue).notContainLabel();
    }

    public static <T extends WebBlock> WebBlockElementLabelNumberValueCondition<T> notContainLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementLabelNumberValueCondition<T>(elementFrame, expectedNumberValue).notContainLabel();
    }

    // ContainsProperty

    public static <T extends WebBlock> WebBlockElementPropertyTextCondition<T> containsProperty(@NotNull WebChildElement elementFrame,
                                                                                                @NotNull String propertyName,
                                                                                                @NotNull String expectedText) {
        return new WebBlockElementPropertyTextCondition<T>(elementFrame, propertyName, expectedText).containsProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyTextCondition<T> containsProperty(@NotNull String elementPath,
                                                                                                @NotNull String propertyName,
                                                                                                @NotNull String expectedText) {
        return new WebBlockElementPropertyTextCondition<T>(elementPath, propertyName, expectedText).containsProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyTextCondition<T> notContainProperty(@NotNull WebChildElement elementFrame,
                                                                                                  @NotNull String propertyName,
                                                                                                  @NotNull String expectedText) {
        return new WebBlockElementPropertyTextCondition<T>(elementFrame, propertyName, expectedText).notContainProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyTextCondition<T> notContainProperty(@NotNull String elementPath,
                                                                                                  @NotNull String propertyName,
                                                                                                  @NotNull String expectedText) {
        return new WebBlockElementPropertyTextCondition<T>(elementPath, propertyName, expectedText).notContainProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyStringValueCondition<T> containsProperty(@NotNull WebChildElement elementFrame,
                                                                                                       @NotNull String propertyName,
                                                                                                       @NotNull StringValue expectedStringValue) {
        return new WebBlockElementPropertyStringValueCondition<T>(elementFrame, propertyName, expectedStringValue).containsProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyStringValueCondition<T> containsProperty(@NotNull String elementPath,
                                                                                                       @NotNull String propertyName,
                                                                                                       @NotNull StringValue expectedStringValue) {
        return new WebBlockElementPropertyStringValueCondition<T>(elementPath, propertyName, expectedStringValue).containsProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyStringValueCondition<T> notContainProperty(@NotNull WebChildElement elementFrame,
                                                                                                         @NotNull String propertyName,
                                                                                                         @NotNull StringValue expectedStringValue) {
        return new WebBlockElementPropertyStringValueCondition<T>(elementFrame, propertyName, expectedStringValue).notContainProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyStringValueCondition<T> notContainProperty(@NotNull String elementPath,
                                                                                                         @NotNull String propertyName,
                                                                                                         @NotNull StringValue expectedStringValue) {
        return new WebBlockElementPropertyStringValueCondition<T>(elementPath, propertyName, expectedStringValue).notContainProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyNumberValueCondition<T> containsProperty(@NotNull WebChildElement elementFrame,
                                                                                                       @NotNull String propertyName,
                                                                                                       @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebBlockElementPropertyNumberValueCondition<T>(elementFrame, propertyName, expectedNumberValue).containsProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyNumberValueCondition<T> containsProperty(@NotNull String elementPath,
                                                                                                       @NotNull String propertyName,
                                                                                                       @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebBlockElementPropertyNumberValueCondition<T>(elementPath, propertyName, expectedNumberValue).containsProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyNumberValueCondition<T> notContainProperty(@NotNull WebChildElement elementFrame,
                                                                                                         @NotNull String propertyName,
                                                                                                         @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebBlockElementPropertyNumberValueCondition<T>(elementFrame, propertyName, expectedNumberValue).notContainProperty();
    }

    public static <T extends WebBlock> WebBlockElementPropertyNumberValueCondition<T> notContainProperty(@NotNull String elementPath,
                                                                                                         @NotNull String propertyName,
                                                                                                         @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebBlockElementPropertyNumberValueCondition<T>(elementPath, propertyName, expectedNumberValue).notContainProperty();
    }

    // ContainsText

    public static <T extends WebBlock> WebBlockElementTextCondition<T> containsText(@NotNull String elementPath,
                                                                                    @NotNull String expectedText) {
        return new WebBlockElementTextCondition<T>(elementPath, expectedText).containsText();
    }

    public static <T extends WebBlock> WebBlockElementTextCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                    @NotNull String expectedText) {
        return new WebBlockElementTextCondition<T>(elementFrame, expectedText).containsText();
    }

    public static <T extends WebBlock> WebBlockElementTextCondition<T> notContainText(@NotNull String elementPath,
                                                                                      @NotNull String expectedText) {
        return new WebBlockElementTextCondition<T>(elementPath, expectedText).notContainText();
    }

    public static <T extends WebBlock> WebBlockElementTextCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                      @NotNull String expectedText) {
        return new WebBlockElementTextCondition<T>(elementFrame, expectedText).notContainText();
    }

    public static <T extends WebBlock> WebBlockElementTextStringValueCondition<T> containsText(@NotNull String elementPath,
                                                                                               @NotNull StringValue expectedStringValue) {
        return new WebBlockElementTextStringValueCondition<T>(elementPath, expectedStringValue).containsText();
    }

    public static <T extends WebBlock> WebBlockElementTextStringValueCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                               @NotNull StringValue expectedStringValue) {
        return new WebBlockElementTextStringValueCondition<T>(elementFrame, expectedStringValue).containsText();
    }

    public static <T extends WebBlock> WebBlockElementTextStringValueCondition<T> notContainText(@NotNull String elementPath,
                                                                                                 @NotNull StringValue expectedStringValue) {
        return new WebBlockElementTextStringValueCondition<T>(elementPath, expectedStringValue).notContainText();
    }

    public static <T extends WebBlock> WebBlockElementTextStringValueCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                 @NotNull StringValue expectedStringValue) {
        return new WebBlockElementTextStringValueCondition<T>(elementFrame, expectedStringValue).notContainText();
    }

    public static <T extends WebBlock> WebBlockElementTextNumberValueCondition<T> containsText(@NotNull String elementPath,
                                                                                               @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementTextNumberValueCondition<T>(elementPath, expectedNumberValue).containsText();
    }

    public static <T extends WebBlock> WebBlockElementTextNumberValueCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                               @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementTextNumberValueCondition<T>(elementFrame, expectedNumberValue).containsText();
    }

    public static <T extends WebBlock> WebBlockElementTextNumberValueCondition<T> notContainText(@NotNull String elementPath,
                                                                                                 @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementTextNumberValueCondition<T>(elementPath, expectedNumberValue).notContainText();
    }

    public static <T extends WebBlock> WebBlockElementTextNumberValueCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                 @NotNull NumberValue<?> expectedNumberValue) {
        return new WebBlockElementTextNumberValueCondition<T>(elementFrame, expectedNumberValue).notContainText();
    }

    // haveState

    public static <T extends WebBlock> WebBlockElementHaveStateCondition<T> haveState(@NotNull WebChildElement elementFrame,
                                                                                      @NotNull String stateName) {
        return new WebBlockElementHaveStateCondition<T>(elementFrame, stateName).haveState();
    }

    public static <T extends WebBlock> WebBlockElementHaveStateCondition<T> haveState(@NotNull String elementPath,
                                                                                      @NotNull String stateName) {
        return new WebBlockElementHaveStateCondition<T>(elementPath, stateName).haveState();
    }

    public static <T extends WebBlock> WebBlockElementHaveStateCondition<T> notHaveState(@NotNull WebChildElement elementFrame,
                                                                                         @NotNull String stateName) {
        return new WebBlockElementHaveStateCondition<T>(elementFrame, stateName).notHaveState();
    }

    public static <T extends WebBlock> WebBlockElementHaveStateCondition<T> notHaveState(@NotNull String elementPath,
                                                                                         @NotNull String stateName) {
        return new WebBlockElementHaveStateCondition<T>(elementPath, stateName).notHaveState();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebTextList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebTextBlockEmptyCondition allTextBlocks() {
        return new WebTextBlockEmptyCondition().allTextBlocks();
    }

    public static WebTextBlockEmptyCondition noTextBlocks() {
        return new WebTextBlockEmptyCondition().noTextBlocks();
    }

    // Index

    public static WebTextBlockIndexCondition textBlockIndex(@NotNull Integer expectedValue) {
        return new WebTextBlockIndexCondition(expectedValue).withTextBlockIndex();
    }

    public static WebTextBlockIndexCondition textBlockIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextBlockIndexCondition(expectedValue).withTextBlockIndex();
    }

    public static WebTextBlockIndexCondition textBlockIndexNot(@NotNull Integer expectedValue) {
        return new WebTextBlockIndexCondition(expectedValue).withoutTextBlockIndex();
    }

    public static WebTextBlockIndexCondition textBlockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextBlockIndexCondition(expectedValue).withoutTextBlockIndex();
    }

    // ContainsText

    public static WebTextBlockTextCondition containsTextBlock(@NotNull String expectedText) {
        return new WebTextBlockTextCondition(expectedText).containsTextBlock();
    }

    public static WebTextBlockTextCondition notContainTextBlock(@NotNull String expectedText) {
        return new WebTextBlockTextCondition(expectedText).notContainTextBlock();
    }

    public static WebTextBlockTextStringValueCondition containsTextBlock(@NotNull StringValue expectedStringValue) {
        return new WebTextBlockTextStringValueCondition(expectedStringValue).containsTextBlock();
    }

    public static WebTextBlockTextStringValueCondition notContainTextBlock(@NotNull StringValue expectedStringValue) {
        return new WebTextBlockTextStringValueCondition(expectedStringValue).notContainTextBlock();
    }

    public static WebTextBlockTextNumberValueCondition containsTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextBlockTextNumberValueCondition(expectedNumberValue).containsTextBlock();
    }

    public static WebTextBlockTextNumberValueCondition notContainTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextBlockTextNumberValueCondition(expectedNumberValue).notContainTextBlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Limiters
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // WebList block limiters

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull WebList<T> fromList,
                                                                                        @NotNull WebBlockFilterBuilder<T> blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull WebList<T> fromList,
                                                                                        @NotNull Function<T, WebBlockFilterBuilder<T>> blockFilterFunction) {
        return WebListBlockContextLimiter.of(fromList, blockFilterFunction.apply(fromList.getBlockFrame().getMappedBlockFrame()), intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull String fromList,
                                                                                        @NotNull WebBlockFilterBuilder<T> blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                         @NotNull WebBlockFilterBuilder<T> blockFilter,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                         @NotNull Function<T, WebBlockFilterBuilder<T>> blockFilterFunction,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilterFunction.apply(fromList.getBlockFrame().getMappedBlockFrame()), intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                         @NotNull WebBlockFilterBuilder<T> blockFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                         @NotNull Function<T, WebBlockFilterBuilder<T>> blockFilterFunction,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilterFunction.apply(fromList.getBlockFrame().getMappedBlockFrame()), expectedSize);
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                         @NotNull WebBlockFilterBuilder<T> blockFilter,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                         @NotNull WebBlockFilterBuilder<T> blockFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   Extractors
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // TODO: Сделать экстракторы для скриншотов, границ элемента и т.п.

    // WebRadioButton

    public static WebRadioButtonIndexExtractor index() {
        return new WebRadioButtonIndexExtractor();
    }

    public static WebRadioButtonElementExtractor element() {
        return new WebRadioButtonElementExtractor();
    }

    public static WebRadioButtonEnabledMarkExtractor enabledMark() {
        return new WebRadioButtonEnabledMarkExtractor();
    }

    public static WebRadioButtonSelectedMarkExtractor selectedMark() {
        return new WebRadioButtonSelectedMarkExtractor();
    }

    public static WebRadioButtonLabelValueExtractor labelValue() {
        return new WebRadioButtonLabelValueExtractor();
    }

    // WebList

    public static <T extends WebBlock> WebBlockIndexExtractor<T> blockIndex() {
        return new WebBlockIndexExtractor<>();
    }

    public static <T extends WebBlock> WebBlockExtractor<WebBlock, T> block() {
        return new WebBlockExtractor<>(WebBlock.class);
    }

    public static <R extends WebBlock, T extends WebBlock> WebBlockExtractor<R, T> block(@NotNull Class<R> blockClass) {
        return new WebBlockExtractor<>(blockClass);
    }

    public static <R extends WebChildElement, T extends WebBlock> WebBlockElementExtractor<R, T> element(@NotNull R elementFrame) {
        return new WebBlockElementExtractor<>(elementFrame);
    }

    public static <R extends WebChildElement, T extends WebBlock> WebBlockElementExtractor<R, T> element(@NotNull String elementPath,
                                                                                                         @NotNull Class<R> returnType) {
        return new WebBlockElementExtractor<>(elementPath, returnType);
    }

    public static <T extends WebBlock> WebBlockElementDisplayedMarkExtractor<T> displayedMark(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebBlockElementDisplayedMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebBlockElementDisplayedMarkExtractor<T> displayedMark(@NotNull String elementPath) {
        return new WebBlockElementDisplayedMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebBlockElementPresentMarkExtractor<T> presentMark(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebBlockElementPresentMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebBlockElementPresentMarkExtractor<T> presentMark(@NotNull String elementPath) {
        return new WebBlockElementPresentMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebBlockElementEnabledMarkExtractor<T> enabledMark(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebBlockElementEnabledMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebBlockElementEnabledMarkExtractor<T> enabledMark(@NotNull String elementPath) {
        return new WebBlockElementEnabledMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebBlockElementSelectedMarkExtractor<T> selectedMark(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebBlockElementSelectedMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebBlockElementSelectedMarkExtractor<T> selectedMark(@NotNull String elementPath) {
        return new WebBlockElementSelectedMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebBlockElementTextValueExtractor<T> textValue(@NotNull WebGetTextAvailable elementFrame) {
        return new WebBlockElementTextValueExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebBlockElementTextValueExtractor<T> textValue(@NotNull String elementPath) {
        return new WebBlockElementTextValueExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebBlockElementLabelValueExtractor<T> labelValue(@NotNull WebGetLabelAvailable elementFrame) {
        return new WebBlockElementLabelValueExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebBlockElementLabelValueExtractor<T> labelValue(@NotNull String elementPath) {
        return new WebBlockElementLabelValueExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebBlockElementPropertyValueExtractor<T> propertyValue(@NotNull WebChildElement elementFrame,
                                                                                              @NotNull String propertyName) {
        return new WebBlockElementPropertyValueExtractor<>(elementFrame, propertyName);
    }

    public static <T extends WebBlock> WebBlockElementPropertyValueExtractor<T> propertyValue(@NotNull String elementPath,
                                                                                              @NotNull String propertyName) {
        return new WebBlockElementPropertyValueExtractor<>(elementPath, propertyName);
    }

    public static <T extends WebBlock> WebBlockElementComponentDisplayedMarkExtractor<T> componentDisplayedMark(@NotNull WebComponentAvailable elementFrame,
                                                                                                                @NotNull String componentName) {
        return new WebBlockElementComponentDisplayedMarkExtractor<>(elementFrame, componentName);
    }

    public static <T extends WebBlock> WebBlockElementComponentDisplayedMarkExtractor<T> componentDisplayedMark(@NotNull String elementPath,
                                                                                                                @NotNull String componentName) {
        return new WebBlockElementComponentDisplayedMarkExtractor<>(elementPath, componentName);
    }

    public static <T extends WebBlock> WebBlockElementComponentPresentMarkExtractor<T> componentPresentMark(@NotNull WebComponentAvailable elementFrame,
                                                                                                            @NotNull String componentName) {
        return new WebBlockElementComponentPresentMarkExtractor<>(elementFrame, componentName);
    }

    public static <T extends WebBlock> WebBlockElementComponentPresentMarkExtractor<T> componentPresentMark(@NotNull String elementPath,
                                                                                                            @NotNull String componentName) {
        return new WebBlockElementComponentPresentMarkExtractor<>(elementPath, componentName);
    }

    // WebTextList

    public static WebTextListBlockIndexExtractor textBlockIndex() {
        return new WebTextListBlockIndexExtractor();
    }

    public static WebTextListBlockElementExtractor textBlockElement() {
        return new WebTextListBlockElementExtractor();
    }

    public static WebTextListBlockTextValueExtractor textBlockValue() {
        return new WebTextListBlockTextValueExtractor();
    }

}
