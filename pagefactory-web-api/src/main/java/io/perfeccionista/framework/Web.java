package io.perfeccionista.framework;

import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.conditions.impl.WebShouldBeDisplayedElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldBeEnabledElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldBeInFocusElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldBeOnTheScreenElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldBePresentElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldBeSelectedElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveAbsoluteLocationElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveAttributeNumberValueElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveAttributeStringElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveAttributeStringValueElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveCenterLocationElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveColorElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveDimensionsElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveLabelNumberValueElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveLabelStringElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveLabelStringValueElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveScreenLocationElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveStateElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveTextNumberValueElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveTextStringElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldHaveTextStringValueElementCondition;
import io.perfeccionista.framework.conditions.impl.WebShouldLooksLikeElementCondition;
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
import io.perfeccionista.framework.pagefactory.elements.WebNode;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementComponentDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementComponentPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementAttributeValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemIndexExtractor;
import io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementHaveStateCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementAttributeNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementAttributeStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementAttributeTextCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemElementTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemIndexCondition;
import io.perfeccionista.framework.pagefactory.limiter.WebListBlockContextLimiter;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.function.Function;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilderImpl.webBlockFilterBuilderWith;
import static io.perfeccionista.framework.pagefactory.filter.WebListFilterBuilderImpl.webBlockFilterBuilderWithout;
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

    public static WebShouldHaveColorElementCondition haveColor(@NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorElementCondition(cssProperty, expectedColor);
    }

    public static WebShouldHaveColorElementCondition haveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorElementCondition(cssProperty, expectedColor)
                .forComponent(componentName);
    }

    public static WebShouldHaveColorElementCondition notHaveColor(@NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorElementCondition(cssProperty, expectedColor)
                .inverse();
    }

    public static WebShouldHaveColorElementCondition notHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        return new WebShouldHaveColorElementCondition(cssProperty, expectedColor)
                .forComponent(componentName)
                .inverse();
    }

    // GetDimensions

    public static WebShouldHaveDimensionsElementCondition haveDimensions(@NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsElementCondition(expectedDimensions);
    }

    public static WebShouldHaveDimensionsElementCondition haveDimensions(@NotNull String componentName, @NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsElementCondition(expectedDimensions)
                .forComponent(componentName);
    }

    public static WebShouldHaveDimensionsElementCondition notHaveDimensions(@NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsElementCondition(expectedDimensions)
                .inverse();
    }

    public static WebShouldHaveDimensionsElementCondition notHaveDimensions(@NotNull String componentName, @NotNull Dimensions2D expectedDimensions) {
        return new WebShouldHaveDimensionsElementCondition(expectedDimensions)
                .forComponent(componentName)
                .inverse();
    }

    // GetLabel

    public static WebShouldHaveLabelStringElementCondition haveLabel(@NotNull String expectedText) {
        return new WebShouldHaveLabelStringElementCondition(expectedText);
    }

    public static WebShouldHaveLabelStringValueElementCondition haveLabel(@NotNull StringValue expectedValue) {
        return new WebShouldHaveLabelStringValueElementCondition(expectedValue);
    }

    public static WebShouldHaveLabelNumberValueElementCondition haveLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveLabelNumberValueElementCondition(expectedValue);
    }

    public static WebShouldHaveLabelStringElementCondition notHaveLabel(@NotNull String expectedText) {
        return new WebShouldHaveLabelStringElementCondition(expectedText)
                .inverse();
    }

    public static WebShouldHaveLabelStringValueElementCondition notHaveLabel(@NotNull StringValue expectedValue) {
        return new WebShouldHaveLabelStringValueElementCondition(expectedValue)
                .inverse();
    }

    public static WebShouldHaveLabelNumberValueElementCondition notHaveLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveLabelNumberValueElementCondition(expectedValue)
                .inverse();
    }

    // GetLocation

    public static WebShouldHaveCenterLocationElementCondition haveCenterLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationElementCondition(expectedLocation);
    }

    public static WebShouldHaveCenterLocationElementCondition haveCenterLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationElementCondition(expectedLocation)
                .forComponent(componentName);
    }

    public static WebShouldHaveCenterLocationElementCondition notHaveCenterLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationElementCondition(expectedLocation)
                .inverse();
    }

    public static WebShouldHaveCenterLocationElementCondition notHaveCenterLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveCenterLocationElementCondition(expectedLocation)
                .forComponent(componentName)
                .inverse();
    }

    public static WebShouldHaveScreenLocationElementCondition haveScreenLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationElementCondition(expectedLocation);
    }

    public static WebShouldHaveScreenLocationElementCondition haveScreenLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationElementCondition(expectedLocation)
                .forComponent(componentName);
    }

    public static WebShouldHaveScreenLocationElementCondition notHaveScreenLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationElementCondition(expectedLocation)
                .inverse();
    }

    public static WebShouldHaveScreenLocationElementCondition notHaveScreenLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveScreenLocationElementCondition(expectedLocation)
                .forComponent(componentName)
                .inverse();
    }

    public static WebShouldHaveAbsoluteLocationElementCondition haveAbsoluteLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationElementCondition(expectedLocation);
    }

    public static WebShouldHaveAbsoluteLocationElementCondition haveAbsoluteLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationElementCondition(expectedLocation)
                .forComponent(componentName);
    }

    public static WebShouldHaveAbsoluteLocationElementCondition notHaveAbsoluteLocation(@NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationElementCondition(expectedLocation)
                .inverse();
    }

    public static WebShouldHaveAbsoluteLocationElementCondition notHaveAbsoluteLocation(@NotNull String componentName, @NotNull Point2D expectedLocation) {
        return new WebShouldHaveAbsoluteLocationElementCondition(expectedLocation)
                .forComponent(componentName)
                .inverse();
    }

    // GetScreenshot

    public static WebShouldLooksLikeElementCondition looksLike(@NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeElementCondition(expectedScreenshot);
    }

    public static WebShouldLooksLikeElementCondition looksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeElementCondition(expectedScreenshot)
                .forComponent(componentName);
    }

    public static WebShouldLooksLikeElementCondition notLooksLike(@NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeElementCondition(expectedScreenshot)
                .inverse();
    }

    public static WebShouldLooksLikeElementCondition notLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        return new WebShouldLooksLikeElementCondition(expectedScreenshot)
                .forComponent(componentName)
                .inverse();
    }

    // GetText

    public static WebShouldHaveTextStringElementCondition haveText(@NotNull String expectedText) {
        return new WebShouldHaveTextStringElementCondition(expectedText);
    }

    public static WebShouldHaveTextStringValueElementCondition haveText(@NotNull StringValue expectedValue) {
        return new WebShouldHaveTextStringValueElementCondition(expectedValue);
    }

    public static WebShouldHaveTextNumberValueElementCondition haveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveTextNumberValueElementCondition(expectedValue);
    }

    public static WebShouldHaveTextStringElementCondition notHaveText(@NotNull String expectedText) {
        return new WebShouldHaveTextStringElementCondition(expectedText)
                .inverse();
    }

    public static WebShouldHaveTextStringValueElementCondition notHaveText(@NotNull StringValue expectedValue) {
        return new WebShouldHaveTextStringValueElementCondition(expectedValue)
                .inverse();
    }

    public static WebShouldHaveTextNumberValueElementCondition notHaveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveTextNumberValueElementCondition(expectedValue)
                .inverse();
    }

    // IsDisplayed

    public static WebShouldBeDisplayedElementCondition beDisplayed() {
        return new WebShouldBeDisplayedElementCondition();
    }

    public static WebShouldBeDisplayedElementCondition notBeDisplayed() {
        return new WebShouldBeDisplayedElementCondition()
                .inverse();
    }

    // IsEnabled

    public static WebShouldBeEnabledElementCondition beEnabled() {
        return new WebShouldBeEnabledElementCondition();
    }

    public static WebShouldBeEnabledElementCondition beDisabled() {
        return new WebShouldBeEnabledElementCondition()
                .inverse();
    }

    // IsInFocus

    public static WebShouldBeInFocusElementCondition beInFocus() {
        return new WebShouldBeInFocusElementCondition();
    }

    public static WebShouldBeInFocusElementCondition notBeInFocus() {
        return new WebShouldBeInFocusElementCondition()
                .inverse();
    }

    // IsOnTheScreen

    public static WebShouldBeOnTheScreenElementCondition completelyBeOnTheScreen() {
        return new WebShouldBeOnTheScreenElementCondition();
    }

    public static WebShouldBeOnTheScreenElementCondition completelyNotBeOnTheScreen() {
        return new WebShouldBeOnTheScreenElementCondition()
                .inverse();
    }

    // IsPresent

    public static WebShouldBePresentElementCondition bePresent() {
        return new WebShouldBePresentElementCondition();
    }

    public static WebShouldBePresentElementCondition notBePresent() {
        return new WebShouldBePresentElementCondition()
                .inverse();
    }

    // IsSelected

    public static WebShouldBeSelectedElementCondition beSelected() {
        return new WebShouldBeSelectedElementCondition();
    }

    public static WebShouldBeSelectedElementCondition notBeSelected() {
        return new WebShouldBeSelectedElementCondition()
                .inverse();
    }

    // WebComponent

    public static WebShouldBeDisplayedElementCondition componentBeDisplayed(@NotNull String componentName) {
        return new WebShouldBeDisplayedElementCondition()
                .forComponent(componentName);
    }

    public static WebShouldBeDisplayedElementCondition componentNotBeDisplayed(@NotNull String componentName) {
        return new WebShouldBeDisplayedElementCondition()
                .forComponent(componentName)
                .inverse();
    }

    public static WebShouldBePresentElementCondition componentBePresent(@NotNull String componentName) {
        return new WebShouldBePresentElementCondition()
                .forComponent(componentName);
    }

    public static WebShouldBePresentElementCondition componentNotBePresent(@NotNull String componentName) {
        return new WebShouldBePresentElementCondition()
                .forComponent(componentName)
                .inverse();
    }

    // WebElementProperty

    public static WebShouldHaveAttributeStringElementCondition haveAttributeValue(@NotNull String attributeName, @NotNull String expectedText) {
        return new WebShouldHaveAttributeStringElementCondition(attributeName, expectedText);
    }

    public static WebShouldHaveAttributeStringElementCondition haveAttributeValue(@NotNull String componentName, @NotNull String attributeName, @NotNull String expectedText) {
        return new WebShouldHaveAttributeStringElementCondition(attributeName, expectedText)
                .forComponent(componentName);
    }

    public static WebShouldHaveAttributeStringValueElementCondition haveAttributeValue(@NotNull String attributeName, @NotNull StringValue expectedValue) {
        return new WebShouldHaveAttributeStringValueElementCondition(attributeName, expectedValue);
    }

    public static WebShouldHaveAttributeStringValueElementCondition haveAttributeValue(@NotNull String componentName, @NotNull String attributeName, @NotNull StringValue expectedValue) {
        return new WebShouldHaveAttributeStringValueElementCondition(attributeName, expectedValue)
                .forComponent(componentName);
    }

    public static WebShouldHaveAttributeNumberValueElementCondition haveAttributeValue(@NotNull String attributeName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveAttributeNumberValueElementCondition(attributeName, expectedValue);
    }

    public static WebShouldHaveAttributeNumberValueElementCondition haveAttributeValue(@NotNull String componentName, @NotNull String attributeName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveAttributeNumberValueElementCondition(attributeName, expectedValue)
                .forComponent(componentName);
    }

    public static WebShouldHaveAttributeStringElementCondition notHaveAttributeValue(@NotNull String attributeName, @NotNull String expectedText) {
        return new WebShouldHaveAttributeStringElementCondition(attributeName, expectedText)
                .inverse();
    }

    public static WebShouldHaveAttributeStringElementCondition notHaveAttributeValue(@NotNull String componentName, @NotNull String attributeName, @NotNull String expectedText) {
        return new WebShouldHaveAttributeStringElementCondition(attributeName, expectedText)
                .forComponent(componentName)
                .inverse();
    }

    public static WebShouldHaveAttributeStringValueElementCondition notHaveAttributeValue(@NotNull String attributeName, @NotNull StringValue expectedValue) {
        return new WebShouldHaveAttributeStringValueElementCondition(attributeName, expectedValue)
                .inverse();
    }

    public static WebShouldHaveAttributeStringValueElementCondition notHaveAttributeValue(@NotNull String componentName, @NotNull String attributeName, @NotNull StringValue expectedValue) {
        return new WebShouldHaveAttributeStringValueElementCondition(attributeName, expectedValue)
                .forComponent(componentName)
                .inverse();
    }

    public static WebShouldHaveAttributeNumberValueElementCondition notHaveAttributeValue(@NotNull String attributeName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveAttributeNumberValueElementCondition(attributeName, expectedValue)
                .inverse();
    }

    public static WebShouldHaveAttributeNumberValueElementCondition notHaveAttributeValue(@NotNull String componentName, @NotNull String attributeName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebShouldHaveAttributeNumberValueElementCondition(attributeName, expectedValue)
                .forComponent(componentName)
                .inverse();
    }

    // WebElementState

    public static WebShouldHaveStateElementCondition haveState(@NotNull String stateName) {
        return new WebShouldHaveStateElementCondition(stateName);
    }

    public static WebShouldHaveStateElementCondition notHaveState(@NotNull String stateName) {
        return new WebShouldHaveStateElementCondition(stateName)
                .inverse();
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

    public static <T extends WebBlock<?>> WebListFilterBuilder<T> emptyWebBlockFilter() {
        return webBlockFilterBuilderWith(allItems());
    }

    public static <T extends WebBlock<?>> WebListFilterBuilder<T> with(@NotNull WebItemCondition<T> condition) {
        return webBlockFilterBuilderWith(condition);
    }

    public static <T extends WebBlock<?>> WebListFilterBuilder<T> without(@NotNull WebItemCondition<T> condition) {
        return webBlockFilterBuilderWithout(condition);
    }

//    // TextBlock
//
//    public static WebTextBlockFilterBuilder emptyWebTextBlockFilter() {
//        return webTextBlockFilterBuilderWith(allTextBlocks());
//    }
//
//    public static WebTextBlockFilterBuilder with(@NotNull WebTextBlockCondition stringCondition) {
//        return webTextBlockFilterBuilderWith(stringCondition);
//    }
//
//    public static WebTextBlockFilterBuilder without(@NotNull WebTextBlockCondition stringCondition) {
//        return webTextBlockFilterBuilderWithout(stringCondition);
//    }


//    // RadioButton
//
//    public static WebRadioGroupFilterBuilder emptyWebRadioButtonFilter() {
//        return webRadioGroupFilterBuilderWith(allRadioButtons());
//    }
//
//    public static WebRadioGroupFilterBuilder with(@NotNull WebRadioButtonCondition condition) {
//        return webRadioGroupFilterBuilderWith(condition);
//    }
//
//    public static WebRadioGroupFilterBuilder without(@NotNull WebRadioButtonCondition condition) {
//        return webRadioGroupFilterBuilderWithout(condition);
//    }

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

//    // Empty
//
//    public static WebRadioButtonEmptyCondition allRadioButtons() {
//        return new WebRadioButtonEmptyCondition().allRadioButtons();
//    }
//
//    public static WebRadioButtonEmptyCondition noRadioButtons() {
//        return new WebRadioButtonEmptyCondition().noRadioButtons();
//    }
//
//    // Index
//
//    public static WebRadioButtonIndexCondition radioButtonIndex(@NotNull Integer expectedValue) {
//        return new WebRadioButtonIndexCondition(expectedValue).withRadioButtonIndex();
//    }
//
//    public static WebRadioButtonIndexCondition radioButtonIndex(@NotNull NumberValue<Integer> expectedValue) {
//        return new WebRadioButtonIndexCondition(expectedValue).withRadioButtonIndex();
//    }
//
//    public static WebRadioButtonIndexCondition radioButtonIndexNot(@NotNull Integer expectedValue) {
//        return new WebRadioButtonIndexCondition(expectedValue).withoutRadioButtonIndex();
//    }
//
//    public static WebRadioButtonIndexCondition radioButtonIndexNot(@NotNull NumberValue<Integer> expectedValue) {
//        return new WebRadioButtonIndexCondition(expectedValue).withoutRadioButtonIndex();
//    }
//
//    // Enabled
//
//    public static WebRadioButtonEnabledCondition enabled() {
//        return new WebRadioButtonEnabledCondition().enabled();
//    }
//
//    public static WebRadioButtonEnabledCondition disabled() {
//        return new WebRadioButtonEnabledCondition().disabled();
//    }
//
//    // Label
//
//    public static WebRadioButtonLabelTextCondition containsLabel(@NotNull String expectedText) {
//        return new WebRadioButtonLabelTextCondition(expectedText).containsLabel();
//    }
//
//    public static WebRadioButtonLabelStringValueCondition containsLabel(@NotNull StringValue expectedValue) {
//        return new WebRadioButtonLabelStringValueCondition(expectedValue).containsLabel();
//    }
//
//    public static WebRadioButtonLabelNumberValueCondition containsLabel(@NotNull NumberValue<? extends Number> expectedValue) {
//        return new WebRadioButtonLabelNumberValueCondition(expectedValue).containsLabel();
//    }
//
//    public static WebRadioButtonLabelTextCondition notContainLabel(@NotNull String expectedText) {
//        return new WebRadioButtonLabelTextCondition(expectedText).notContainLabel();
//    }
//
//    public static WebRadioButtonLabelStringValueCondition notContainLabel(@NotNull StringValue expectedValue) {
//        return new WebRadioButtonLabelStringValueCondition(expectedValue).notContainLabel();
//    }
//
//    public static WebRadioButtonLabelNumberValueCondition notContainLabel(@NotNull NumberValue<? extends Number> expectedValue) {
//        return new WebRadioButtonLabelNumberValueCondition(expectedValue).notContainLabel();
//    }
//
//    // Selected
//
//    public static WebRadioButtonSelectedCondition selected() {
//        return new WebRadioButtonSelectedCondition().selected();
//    }
//
//    public static WebRadioButtonSelectedCondition notSelected() {
//        return new WebRadioButtonSelectedCondition().notSelected();
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static <T extends WebBlock<?>> WebItemElementEmptyCondition<T> allItems() {
        return new WebItemElementEmptyCondition<T>().allBlocks();
    }

    public static <T extends WebBlock<?>> WebItemElementEmptyCondition<T> noItems() {
        return new WebItemElementEmptyCondition<T>().noBlocks();
    }

    // Index

    public static <T extends WebBlock<?>> WebItemIndexCondition<T> index(@NotNull Integer expectedValue) {
        return new WebItemIndexCondition<T>(expectedValue).withBlockIndex();
    }

    public static <T extends WebBlock<?>> WebItemIndexCondition<T> index(@NotNull NumberValue<Integer> expectedValue) {
        return new WebItemIndexCondition<T>(expectedValue).withBlockIndex();
    }

    public static <T extends WebBlock<?>> WebItemIndexCondition<T> indexNot(@NotNull Integer expectedValue) {
        return new WebItemIndexCondition<T>(expectedValue).withoutBlockIndexNot();
    }

    public static <T extends WebBlock<?>> WebItemIndexCondition<T> indexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebItemIndexCondition<T>(expectedValue).withoutBlockIndexNot();
    }

    // ComponentDisplayed

    public static <T extends WebBlock<?>> WebItemComponentDisplayedCondition<T> componentDisplayed(@NotNull String componentName) {
        return new WebItemComponentDisplayedCondition<T>(componentName).componentDisplayed();
    }

    public static <T extends WebBlock<?>> WebItemElementComponentDisplayedCondition<T> componentDisplayed(@NotNull WebChildElement elementFrame,
                                                                                                       @NotNull String componentName) {
        return new WebItemElementComponentDisplayedCondition<T>(elementFrame, componentName).componentDisplayed();
    }

    public static <T extends WebBlock<?>> WebItemElementComponentDisplayedCondition<T> componentDisplayed(@NotNull String elementPath,
                                                                                                              @NotNull String componentName) {
        return new WebItemElementComponentDisplayedCondition<T>(elementPath, componentName).componentDisplayed();
    }

    public static <T extends WebBlock<?>> WebItemElementComponentDisplayedCondition<T> componentNotDisplayed(@NotNull WebChildElement elementFrame,
                                                                                                                 @NotNull String componentName) {
        return new WebItemElementComponentDisplayedCondition<T>(elementFrame, componentName).componentNotDisplayed();
    }

    public static <T extends WebBlock<?>> WebItemElementComponentDisplayedCondition<T> componentNotDisplayed(@NotNull String elementPath,
                                                                                                                 @NotNull String componentName) {
        return new WebItemElementComponentDisplayedCondition<T>(elementPath, componentName).componentNotDisplayed();
    }

    // ComponentPresent

    public static <T extends WebBlock<?>> WebItemElementComponentPresentCondition<T> componentPresent(@NotNull WebChildElement elementFrame,
                                                                                                   @NotNull String componentName) {
        return new WebItemElementComponentPresentCondition<T>(elementFrame, componentName).componentPresent();
    }

    public static <T extends WebBlock<?>> WebItemElementComponentPresentCondition<T> componentPresent(@NotNull String elementPath,
                                                                                                   @NotNull String componentName) {
        return new WebItemElementComponentPresentCondition<T>(elementPath, componentName).componentPresent();
    }

    public static <T extends WebBlock<?>> WebItemElementComponentPresentCondition<T> componentNotPresent(@NotNull WebChildElement elementFrame,
                                                                                                      @NotNull String componentName) {
        return new WebItemElementComponentPresentCondition<T>(elementFrame, componentName).componentNotPresent();
    }

    public static <T extends WebBlock<?>> WebItemElementComponentPresentCondition<T> componentNotPresent(@NotNull String elementPath,
                                                                                                      @NotNull String componentName) {
        return new WebItemElementComponentPresentCondition<T>(elementPath, componentName).componentNotPresent();
    }

    // Displayed

    public static <T extends WebBlock<?>> WebItemElementDisplayedCondition<T> displayed(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebItemElementDisplayedCondition<T>(elementFrame).displayed();
    }

    public static <T extends WebBlock<?>> WebItemElementDisplayedCondition<T> displayed(@NotNull String elementPath) {
        return new WebItemElementDisplayedCondition<T>(elementPath).displayed();
    }

    public static <T extends WebBlock<?>> WebItemElementDisplayedCondition<T> notDisplayed(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebItemElementDisplayedCondition<T>(elementFrame).notDisplayed();
    }

    public static <T extends WebBlock<?>> WebItemElementDisplayedCondition<T> notDisplayed(@NotNull String elementPath) {
        return new WebItemElementDisplayedCondition<T>(elementPath).notDisplayed();
    }

    // Present

    public static <T extends WebBlock<?>> WebItemElementPresentCondition<T> present(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebItemElementPresentCondition<T>(elementFrame).present();
    }

    public static <T extends WebBlock<?>> WebItemElementPresentCondition<T> present(@NotNull String elementPath) {
        return new WebItemElementPresentCondition<T>(elementPath).present();
    }

    public static <T extends WebBlock<?>> WebItemElementPresentCondition<T> notPresent(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebItemElementPresentCondition<T>(elementFrame).notPresent();
    }

    public static <T extends WebBlock<?>> WebItemElementPresentCondition<T> notPresent(@NotNull String elementPath) {
        return new WebItemElementPresentCondition<T>(elementPath).notPresent();
    }

    // Enabled

    public static <T extends WebBlock<?>> WebItemElementEnabledCondition<T> enabled(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebItemElementEnabledCondition<T>(elementFrame).enabled();
    }

    public static <T extends WebBlock<?>> WebItemElementEnabledCondition<T> enabled(@NotNull String elementPath) {
        return new WebItemElementEnabledCondition<T>(elementPath).enabled();
    }

    public static <T extends WebBlock<?>> WebItemElementEnabledCondition<T> disabled(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebItemElementEnabledCondition<T>(elementFrame).disabled();
    }

    public static <T extends WebBlock<?>> WebItemElementEnabledCondition<T> disabled(@NotNull String elementPath) {
        return new WebItemElementEnabledCondition<T>(elementPath).disabled();
    }

    // Selected

    public static <T extends WebBlock<?>> WebItemElementSelectedCondition<T> selected(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebItemElementSelectedCondition<T>(elementFrame).selected();
    }

    public static <T extends WebBlock<?>> WebItemElementSelectedCondition<T> selected(@NotNull String elementPath) {
        return new WebItemElementSelectedCondition<T>(elementPath).selected();
    }

    public static <T extends WebBlock<?>> WebItemElementSelectedCondition<T> notSelected(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebItemElementSelectedCondition<T>(elementFrame).notSelected();
    }

    public static <T extends WebBlock<?>> WebItemElementSelectedCondition<T> notSelected(@NotNull String elementPath) {
        return new WebItemElementSelectedCondition<T>(elementPath).notSelected();
    }

    // ContainsLabel

    public static <T extends WebBlock<?>> WebItemElementLabelTextCondition<T> containsLabel(@NotNull String elementPath,
                                                                                            @NotNull String expectedText) {
        return new WebItemElementLabelTextCondition<T>(elementPath, expectedText).containsLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelTextCondition<T> containsLabel(@NotNull WebGetTextAvailable elementFrame,
                                                                                            @NotNull String expectedText) {
        return new WebItemElementLabelTextCondition<T>(elementFrame, expectedText).containsLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelTextCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                           @NotNull String expectedText) {
        return new WebItemElementLabelTextCondition<T>(elementPath, expectedText).notContainLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelTextCondition<T> notContainLabel(@NotNull WebGetTextAvailable elementFrame,
                                                                                           @NotNull String expectedText) {
        return new WebItemElementLabelTextCondition<T>(elementFrame, expectedText).notContainLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelStringValueCondition<T> containsLabel(@NotNull String elementPath,
                                                                                                @NotNull StringValue expectedStringValue) {
        return new WebItemElementLabelStringValueCondition<T>(elementPath, expectedStringValue).containsLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelStringValueCondition<T> containsLabel(@NotNull WebGetTextAvailable elementFrame,
                                                                                                @NotNull StringValue expectedStringValue) {
        return new WebItemElementLabelStringValueCondition<T>(elementFrame, expectedStringValue).containsLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelStringValueCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                                  @NotNull StringValue expectedStringValue) {
        return new WebItemElementLabelStringValueCondition<T>(elementPath, expectedStringValue).notContainLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelStringValueCondition<T> notContainLabel(@NotNull WebGetTextAvailable elementFrame,
                                                                                                  @NotNull StringValue expectedStringValue) {
        return new WebItemElementLabelStringValueCondition<T>(elementFrame, expectedStringValue).notContainLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelNumberValueCondition<T> containsLabel(@NotNull String elementPath,
                                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementLabelNumberValueCondition<T>(elementPath, expectedNumberValue).containsLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelNumberValueCondition<T> containsLabel(@NotNull WebGetTextAvailable elementFrame,
                                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementLabelNumberValueCondition<T>(elementFrame, expectedNumberValue).containsLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelNumberValueCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                                  @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementLabelNumberValueCondition<T>(elementPath, expectedNumberValue).notContainLabel();
    }

    public static <T extends WebBlock<?>> WebItemElementLabelNumberValueCondition<T> notContainLabel(@NotNull WebGetTextAvailable elementFrame,
                                                                                                  @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementLabelNumberValueCondition<T>(elementFrame, expectedNumberValue).notContainLabel();
    }

    // ContainsAttribute

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> containsAttribute(@NotNull WebChildElement elementFrame,
                                                                                                    @NotNull String attributeName,
                                                                                                    @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementFrame, ROOT, attributeName, expectedText).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> containsAttribute(@NotNull WebChildElement elementFrame,
                                                                                                    @NotNull String componentName,
                                                                                                    @NotNull String attributeName,
                                                                                                    @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementFrame, componentName, attributeName, expectedText).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> containsAttribute(@NotNull String elementPath,
                                                                                                    @NotNull String attributeName,
                                                                                                    @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementPath, ROOT, attributeName, expectedText).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> containsAttribute(@NotNull String elementPath,
                                                                                                    @NotNull String componentName,
                                                                                                    @NotNull String attributeName,
                                                                                                    @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementPath, componentName, attributeName, expectedText).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> notContainAttribute(@NotNull WebChildElement elementFrame,
                                                                                                      @NotNull String attributeName,
                                                                                                      @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementFrame, ROOT, attributeName, expectedText).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> notContainAttribute(@NotNull WebChildElement elementFrame,
                                                                                                      @NotNull String componentName,
                                                                                                      @NotNull String attributeName,
                                                                                                      @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementFrame, componentName, attributeName, expectedText).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> notContainAttribute(@NotNull String elementPath,
                                                                                                      @NotNull String attributeName,
                                                                                                      @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementPath, ROOT, attributeName, expectedText).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeTextCondition<T> notContainAttribute(@NotNull String elementPath,
                                                                                                      @NotNull String componentName,
                                                                                                      @NotNull String attributeName,
                                                                                                      @NotNull String expectedText) {
        return new WebItemElementAttributeTextCondition<T>(elementPath, componentName, attributeName, expectedText).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> containsAttribute(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementFrame, ROOT, attributeName, expectedStringValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> containsAttribute(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String componentName,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementFrame, componentName, attributeName, expectedStringValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> containsAttribute(@NotNull String elementPath,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementPath, ROOT, attributeName, expectedStringValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> containsAttribute(@NotNull String elementPath,
                                                                                                           @NotNull String componentName,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementPath, componentName, attributeName, expectedStringValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> notContainAttribute(@NotNull WebChildElement elementFrame,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementFrame, ROOT, attributeName, expectedStringValue).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> notContainAttribute(@NotNull WebChildElement elementFrame,
                                                                                                             @NotNull String componentName,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementFrame, componentName, attributeName, expectedStringValue).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> notContainAttribute(@NotNull String elementPath,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementPath, ROOT, attributeName, expectedStringValue).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeStringValueCondition<T> notContainAttribute(@NotNull String elementPath,
                                                                                                             @NotNull String componentName,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull StringValue expectedStringValue) {
        return new WebItemElementAttributeStringValueCondition<T>(elementPath, componentName, attributeName, expectedStringValue).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> containsAttribute(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementFrame, ROOT, attributeName, expectedNumberValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> containsAttribute(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String componentName,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementFrame, componentName, attributeName, expectedNumberValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> containsAttribute(@NotNull String elementPath,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementPath, ROOT, attributeName, expectedNumberValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> containsAttribute(@NotNull String elementPath,
                                                                                                           @NotNull String componentName,
                                                                                                           @NotNull String attributeName,
                                                                                                           @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementPath, componentName, attributeName, expectedNumberValue).containsAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> notContainAttribute(@NotNull WebChildElement elementFrame,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementFrame, ROOT, attributeName, expectedNumberValue).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> notContainAttribute(@NotNull WebChildElement elementFrame,
                                                                                                             @NotNull String componentName,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementFrame, componentName, attributeName, expectedNumberValue).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> notContainAttribute(@NotNull String elementPath,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementPath, ROOT, attributeName, expectedNumberValue).notContainAttribute();
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeNumberValueCondition<T> notContainAttribute(@NotNull String elementPath,
                                                                                                             @NotNull String componentName,
                                                                                                             @NotNull String attributeName,
                                                                                                             @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebItemElementAttributeNumberValueCondition<T>(elementPath, componentName, attributeName, expectedNumberValue).notContainAttribute();
    }

    // ContainsText

    public static <T extends WebBlock<?>> WebItemElementTextCondition<T> containsText(@NotNull String elementPath,
                                                                                   @NotNull String expectedText) {
        return new WebItemElementTextCondition<T>(elementPath, expectedText).containsText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                   @NotNull String expectedText) {
        return new WebItemElementTextCondition<T>(elementFrame, expectedText).containsText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextCondition<T> notContainText(@NotNull String elementPath,
                                                                                     @NotNull String expectedText) {
        return new WebItemElementTextCondition<T>(elementPath, expectedText).notContainText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                     @NotNull String expectedText) {
        return new WebItemElementTextCondition<T>(elementFrame, expectedText).notContainText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextStringValueCondition<T> containsText(@NotNull String elementPath,
                                                                                              @NotNull StringValue expectedStringValue) {
        return new WebItemElementTextStringValueCondition<T>(elementPath, expectedStringValue).containsText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextStringValueCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                              @NotNull StringValue expectedStringValue) {
        return new WebItemElementTextStringValueCondition<T>(elementFrame, expectedStringValue).containsText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextStringValueCondition<T> notContainText(@NotNull String elementPath,
                                                                                                @NotNull StringValue expectedStringValue) {
        return new WebItemElementTextStringValueCondition<T>(elementPath, expectedStringValue).notContainText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextStringValueCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                @NotNull StringValue expectedStringValue) {
        return new WebItemElementTextStringValueCondition<T>(elementFrame, expectedStringValue).notContainText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextNumberValueCondition<T> containsText(@NotNull String elementPath,
                                                                                              @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementTextNumberValueCondition<T>(elementPath, expectedNumberValue).containsText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextNumberValueCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                              @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementTextNumberValueCondition<T>(elementFrame, expectedNumberValue).containsText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextNumberValueCondition<T> notContainText(@NotNull String elementPath,
                                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementTextNumberValueCondition<T>(elementPath, expectedNumberValue).notContainText();
    }

    public static <T extends WebBlock<?>> WebItemElementTextNumberValueCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                @NotNull NumberValue<?> expectedNumberValue) {
        return new WebItemElementTextNumberValueCondition<T>(elementFrame, expectedNumberValue).notContainText();
    }

    // haveState

    public static <T extends WebBlock<?>> WebItemElementHaveStateCondition<T> haveState(@NotNull WebChildElement elementFrame,
                                                                                     @NotNull String stateName) {
        return new WebItemElementHaveStateCondition<T>(elementFrame, stateName).haveState();
    }

    public static <T extends WebBlock<?>> WebItemElementHaveStateCondition<T> haveState(@NotNull String elementPath,
                                                                                     @NotNull String stateName) {
        return new WebItemElementHaveStateCondition<T>(elementPath, stateName).haveState();
    }

    public static <T extends WebBlock<?>> WebItemElementHaveStateCondition<T> notHaveState(@NotNull WebChildElement elementFrame,
                                                                                        @NotNull String stateName) {
        return new WebItemElementHaveStateCondition<T>(elementFrame, stateName).notHaveState();
    }

    public static <T extends WebBlock<?>> WebItemElementHaveStateCondition<T> notHaveState(@NotNull String elementPath,
                                                                                        @NotNull String stateName) {
        return new WebItemElementHaveStateCondition<T>(elementPath, stateName).notHaveState();
    }

//    ////////////////////////////////////////////////////////////////////////////////////////////////
//    //
//    //   WebTextList
//    //
//    ////////////////////////////////////////////////////////////////////////////////////////////////
//
//    // Empty
//
//    public static WebTextBlockEmptyCondition allTextBlocks() {
//        return new WebTextBlockEmptyCondition().allTextBlocks();
//    }
//
//    public static WebTextBlockEmptyCondition noTextBlocks() {
//        return new WebTextBlockEmptyCondition().noTextBlocks();
//    }
//
//    // Index
//
//    public static WebTextBlockIndexCondition textBlockIndex(@NotNull Integer expectedValue) {
//        return new WebTextBlockIndexCondition(expectedValue).withTextBlockIndex();
//    }
//
//    public static WebTextBlockIndexCondition textBlockIndex(@NotNull NumberValue<Integer> expectedValue) {
//        return new WebTextBlockIndexCondition(expectedValue).withTextBlockIndex();
//    }
//
//    public static WebTextBlockIndexCondition textBlockIndexNot(@NotNull Integer expectedValue) {
//        return new WebTextBlockIndexCondition(expectedValue).withoutTextBlockIndex();
//    }
//
//    public static WebTextBlockIndexCondition textBlockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
//        return new WebTextBlockIndexCondition(expectedValue).withoutTextBlockIndex();
//    }
//
//    // ContainsText
//
//    public static WebTextBlockTextCondition containsTextBlock(@NotNull String expectedText) {
//        return new WebTextBlockTextCondition(expectedText).containsTextBlock();
//    }
//
//    public static WebTextBlockTextCondition notContainTextBlock(@NotNull String expectedText) {
//        return new WebTextBlockTextCondition(expectedText).notContainTextBlock();
//    }
//
//    public static WebTextBlockTextStringValueCondition containsTextBlock(@NotNull StringValue expectedStringValue) {
//        return new WebTextBlockTextStringValueCondition(expectedStringValue).containsTextBlock();
//    }
//
//    public static WebTextBlockTextStringValueCondition notContainTextBlock(@NotNull StringValue expectedStringValue) {
//        return new WebTextBlockTextStringValueCondition(expectedStringValue).notContainTextBlock();
//    }
//
//    public static WebTextBlockTextNumberValueCondition containsTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
//        return new WebTextBlockTextNumberValueCondition(expectedNumberValue).containsTextBlock();
//    }
//
//    public static WebTextBlockTextNumberValueCondition notContainTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
//        return new WebTextBlockTextNumberValueCondition(expectedNumberValue).notContainTextBlock();
//    }

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

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull WebList<T> fromList,
                                                                                           @NotNull WebListFilterBuilder<T> blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull WebList<T> fromList,
                                                                                           @NotNull Function<T, WebListFilterBuilder<T>> blockFilterFunction) {
        return WebListBlockContextLimiter.of(fromList, blockFilterFunction.apply(fromList.getItemFrame().getMappedItemFrame()), intEquals(1));
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull String fromList,
                                                                                           @NotNull WebListFilterBuilder<T> blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                            @NotNull WebListFilterBuilder<T> blockFilter,
                                                                                            int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                            @NotNull Function<T, WebListFilterBuilder<T>> blockFilterFunction,
                                                                                            int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilterFunction.apply(fromList.getItemFrame().getMappedItemFrame()), intEquals(expectedSize));
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                            @NotNull WebListFilterBuilder<T> blockFilter,
                                                                                            @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList<T> fromList,
                                                                                            @NotNull Function<T, WebListFilterBuilder<T>> blockFilterFunction,
                                                                                            @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilterFunction.apply(fromList.getItemFrame().getMappedItemFrame()), expectedSize);
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                            @NotNull WebListFilterBuilder<T> blockFilter,
                                                                                            int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock<?>> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                            @NotNull WebListFilterBuilder<T> blockFilter,
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

//    // WebRadioButton
//
//    public static WebRadioButtonIndexExtractor index() {
//        return new WebRadioButtonIndexExtractor();
//    }
//
//    public static WebRadioButtonElementExtractor element() {
//        return new WebRadioButtonElementExtractor();
//    }
//
//    public static WebRadioButtonEnabledMarkExtractor enabledMark() {
//        return new WebRadioButtonEnabledMarkExtractor();
//    }
//
//    public static WebRadioButtonSelectedMarkExtractor selectedMark() {
//        return new WebRadioButtonSelectedMarkExtractor();
//    }
//
//    public static WebRadioButtonLabelValueExtractor labelValue() {
//        return new WebRadioButtonLabelValueExtractor();
//    }

    // WebList

    public static <T extends WebBlock<?>> WebItemIndexExtractor<T> blockIndex() {
        return new WebItemIndexExtractor<>();
    }

    public static <T extends WebBlock<?>> WebItemExtractor<WebNode, T> block() {
        return new WebItemExtractor<>(WebNode.class);
    }

    public static <R extends WebBlock<?>, T extends WebBlock<?>> WebItemExtractor<R, T> block(@NotNull Class<R> blockClass) {
        return new WebItemExtractor<>(blockClass);
    }

    public static <R extends WebChildElement, T extends WebBlock<?>> WebItemElementExtractor<R, T> element(@NotNull R elementFrame) {
        return new WebItemElementExtractor<>(elementFrame);
    }

    public static <R extends WebChildElement, T extends WebBlock<?>> WebItemElementExtractor<R, T> element(@NotNull String elementPath,
                                                                                                        @NotNull Class<R> returnType) {
        return new WebItemElementExtractor<>(elementPath, returnType);
    }

    public static <T extends WebBlock<?>> WebItemElementDisplayedMarkExtractor<T> displayedMark(@NotNull WebChildElement elementFrame) {
        return new WebItemElementDisplayedMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock<?>> WebItemElementDisplayedMarkExtractor<T> displayedMark(@NotNull String elementPath) {
        return new WebItemElementDisplayedMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock<?>> WebItemElementPresentMarkExtractor<T> presentMark(@NotNull WebChildElement elementFrame) {
        return new WebItemElementPresentMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock<?>> WebItemElementPresentMarkExtractor<T> presentMark(@NotNull String elementPath) {
        return new WebItemElementPresentMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock<?>> WebItemElementEnabledMarkExtractor<T> enabledMark(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebItemElementEnabledMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock<?>> WebItemElementEnabledMarkExtractor<T> enabledMark(@NotNull String elementPath) {
        return new WebItemElementEnabledMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock<?>> WebItemElementSelectedMarkExtractor<T> selectedMark(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebItemElementSelectedMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock<?>> WebItemElementSelectedMarkExtractor<T> selectedMark(@NotNull String elementPath) {
        return new WebItemElementSelectedMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock<?>> WebItemElementTextValueExtractor<T> textValue(@NotNull WebGetTextAvailable elementFrame) {
        return new WebItemElementTextValueExtractor<>(elementFrame);
    }

    public static <T extends WebBlock<?>> WebItemElementTextValueExtractor<T> textValue(@NotNull String elementPath) {
        return new WebItemElementTextValueExtractor<>(elementPath);
    }

    public static <T extends WebBlock<?>> WebItemElementLabelValueExtractor<T> labelValue(@NotNull WebGetTextAvailable elementFrame) {
        return new WebItemElementLabelValueExtractor<>(elementFrame);
    }

    public static <T extends WebBlock<?>> WebItemElementLabelValueExtractor<T> labelValue(@NotNull String elementPath) {
        return new WebItemElementLabelValueExtractor<>(elementPath);
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeValueExtractor<T> attributeValue(@NotNull WebChildElement elementFrame,
                                                                                                  @NotNull String attributeName) {
        return new WebItemElementAttributeValueExtractor<>(elementFrame, ROOT, attributeName);
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeValueExtractor<T> attributeValue(@NotNull WebChildElement elementFrame,
                                                                                                  @NotNull String componentName,
                                                                                                  @NotNull String attributeName) {
        return new WebItemElementAttributeValueExtractor<>(elementFrame, componentName, attributeName);
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeValueExtractor<T> attributeValue(@NotNull String elementPath,
                                                                                                  @NotNull String attributeName) {
        return new WebItemElementAttributeValueExtractor<>(elementPath, ROOT, attributeName);
    }

    public static <T extends WebBlock<?>> WebItemElementAttributeValueExtractor<T> attributeValue(@NotNull String elementPath,
                                                                                                  @NotNull String componentName,
                                                                                                  @NotNull String attributeName) {
        return new WebItemElementAttributeValueExtractor<>(elementPath, componentName, attributeName);
    }

    public static <T extends WebBlock<?>> WebItemElementComponentDisplayedMarkExtractor<T> componentDisplayedMark(@NotNull WebChildElement elementFrame,
                                                                                                                  @NotNull String componentName) {
        return new WebItemElementComponentDisplayedMarkExtractor<>(elementFrame, componentName);
    }

    public static <T extends WebBlock<?>> WebItemElementComponentDisplayedMarkExtractor<T> componentDisplayedMark(@NotNull String elementPath,
                                                                                                                  @NotNull String componentName) {
        return new WebItemElementComponentDisplayedMarkExtractor<>(elementPath, componentName);
    }

    public static <T extends WebBlock<?>> WebItemElementComponentPresentMarkExtractor<T> componentPresentMark(@NotNull WebChildElement elementFrame,
                                                                                                              @NotNull String componentName) {
        return new WebItemElementComponentPresentMarkExtractor<>(elementFrame, componentName);
    }

    public static <T extends WebBlock<?>> WebItemElementComponentPresentMarkExtractor<T> componentPresentMark(@NotNull String elementPath,
                                                                                                              @NotNull String componentName) {
        return new WebItemElementComponentPresentMarkExtractor<>(elementPath, componentName);
    }

    // WebTextList

//    public static WebTextListBlockIndexExtractor textBlockIndex() {
//        return new WebTextListBlockIndexExtractor();
//    }
//
//    public static WebTextListBlockElementExtractor textBlockElement() {
//        return new WebTextListBlockElementExtractor();
//    }
//
//    public static WebTextListBlockTextValueExtractor textBlockValue() {
//        return new WebTextListBlockTextValueExtractor();
//    }

}
