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
import io.perfeccionista.framework.matcher.element.implementations.WebTableShouldHaveColumnMatcher;
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
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveResultMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveSizeNumberMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveSizeNumberValueMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveSortedResultMatcher;
import io.perfeccionista.framework.matcher.result.implementations.WebShouldHaveStringValueResultMatcher;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebComponentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsPresentAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementComponentDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementComponentPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementComponentDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementComponentPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementEnabledMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementLabelValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableRowExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockIndexExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellElementExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableRowExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableRowIndexExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementHaveStateCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPropertyNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPropertyStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementPropertyTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockElementTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementComponentDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementComponentPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementDisplayedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementEnabledCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementLabelNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementLabelStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementLabelTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPresentCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPropertyNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPropertyStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementPropertyTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementSelectedCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementTextCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableCellElementTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockTextCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowEmptyCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowIndexCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowTextCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowTextNumberValueCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowTextStringValueCondition;
import io.perfeccionista.framework.pagefactory.limiter.WebListBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.limiter.WebTableCellContextLimiter;
import io.perfeccionista.framework.pagefactory.limiter.WebTableRowContextLimiter;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ROOT;
import static io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilderImpl.webListFilterBuilderWith;
import static io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilderImpl.webListFilterBuilderWithout;
import static io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilderImpl.webRadioGroupFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilderImpl.webTableFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilderImpl.webTextListFilterBuilder;
import static io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilderImpl.webTextTableFilterBuilder;
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

    public static WebTableShouldHaveColumnMatcher haveColumn(@NotNull String columnName) {
        return new WebTableShouldHaveColumnMatcher(columnName, true);
    }

    public static WebTableShouldHaveColumnMatcher notHaveColumn(@NotNull String columnName) {
        return new WebTableShouldHaveColumnMatcher(columnName, false);
    }

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
    public static <T> WebShouldHaveResultMatcher<T> haveResult(T expectedResult) {
        return new WebShouldHaveResultMatcher<>(expectedResult);
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

    // RadioButton

    public static WebRadioGroupFilterBuilder emptyWebRadioButtonFilter() {
        return webRadioGroupFilterBuilder()
                .add(allRadioButtons());
    }

    public static WebRadioGroupFilterBuilder with(@NotNull WebRadioButtonCondition condition) {
        return webRadioGroupFilterBuilder()
                .add(condition);
    }

    public static WebRadioGroupFilterBuilder without(@NotNull WebRadioButtonCondition condition) {
        return webRadioGroupFilterBuilder()
                .add(allRadioButtons())
                .subtract(condition);
    }

    // List

    public static <T extends WebBlock> WebListFilterBuilder<T> emptyWebListFilter() {
        return webListFilterBuilderWith(allBlocks());
    }

    public static <T extends WebBlock> WebListFilterBuilder<T> with(@NotNull WebListBlockCondition<T> condition) {
        return webListFilterBuilderWith(condition);
    }

    public static <T extends WebBlock> WebListFilterBuilder<T> without(@NotNull WebListBlockCondition<T> condition) {
        return webListFilterBuilderWithout(condition);
    }

    // TextList

    public static WebTextListFilterBuilder emptyWebTextListFilter() {
        return webTextListFilterBuilder()
                .add(allTextBlocks());
    }

    public static WebTextListFilterBuilder with(@NotNull WebTextListBlockCondition stringCondition) {
        return webTextListFilterBuilder()
                .add(stringCondition);
    }

    public static WebTextListFilterBuilder without(@NotNull WebTextListBlockCondition stringCondition) {
        return webTextListFilterBuilder()
                .add(allTextBlocks())
                .subtract(stringCondition);
    }

    // Table

    public static WebTableFilterBuilder emptyWebTableFilter() {
        return webTableFilterBuilder()
                .add(allRows());
    }

    public static WebTableFilterBuilder with(@NotNull WebTableRowCondition condition) {
        return webTableFilterBuilder()
                .add(condition);
    }

    public static WebTableFilterBuilder without(@NotNull WebTableRowCondition condition) {
        return webTableFilterBuilder()
                .add(allRows())
                .subtract(condition);
    }

    // TextTable

    public static WebTextTableFilterBuilder emptyWebTextTableFilter() {
        return webTextTableFilterBuilder()
                .add(allTextRows());
    }

    public static WebTextTableFilterBuilder with(@NotNull WebTextTableRowCondition stringCondition) {
        return webTextTableFilterBuilder()
                .add(stringCondition);
    }

    public static WebTextTableFilterBuilder without(@NotNull WebTextTableRowCondition stringCondition) {
        return webTextTableFilterBuilder()
                .add(allTextRows())
                .subtract(stringCondition);
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

    public static <T extends WebBlock> WebListBlockElementEmptyCondition<T> allBlocks() {
        return new WebListBlockElementEmptyCondition<T>().allBlocks();
    }

    public static <T extends WebBlock> WebListBlockElementEmptyCondition<T> noBlocks() {
        return new WebListBlockElementEmptyCondition<T>().noBlocks();
    }

    // Index

    public static <T extends WebBlock> WebListBlockIndexCondition<T> blockIndex(@NotNull Integer expectedValue) {
        return new WebListBlockIndexCondition<T>(expectedValue).withBlockIndex();
    }

    public static <T extends WebBlock> WebListBlockIndexCondition<T> blockIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebListBlockIndexCondition<T>(expectedValue).withBlockIndex();
    }

    public static <T extends WebBlock> WebListBlockIndexCondition<T> blockIndexNot(@NotNull Integer expectedValue) {
        return new WebListBlockIndexCondition<T>(expectedValue).withoutBlockIndexNot();
    }

    public static <T extends WebBlock> WebListBlockIndexCondition<T> blockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebListBlockIndexCondition<T>(expectedValue).withoutBlockIndexNot();
    }

    // ComponentDisplayed

    public static <T extends WebBlock> WebListBlockElementComponentDisplayedCondition<T> componentDisplayed(@NotNull WebChildElement elementFrame,
                                                                                                            @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition<T>(elementFrame, componentName).componentDisplayed();
    }

    public static <T extends WebBlock> WebListBlockElementComponentDisplayedCondition<T> componentDisplayed(@NotNull String elementPath,
                                                                                                            @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition<T>(elementPath, componentName).componentDisplayed();
    }

    public static <T extends WebBlock> WebListBlockElementComponentDisplayedCondition<T> componentNotDisplayed(@NotNull WebChildElement elementFrame,
                                                                                                               @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition<T>(elementFrame, componentName).componentNotDisplayed();
    }

    public static <T extends WebBlock> WebListBlockElementComponentDisplayedCondition<T> componentNotDisplayed(@NotNull String elementPath,
                                                                                                               @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedCondition<T>(elementPath, componentName).componentNotDisplayed();
    }

    // ComponentPresent

    public static <T extends WebBlock> WebListBlockElementComponentPresentCondition<T> componentPresent(@NotNull WebChildElement elementFrame,
                                                                                                        @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition<T>(elementFrame, componentName).componentPresent();
    }

    public static <T extends WebBlock> WebListBlockElementComponentPresentCondition<T> componentPresent(@NotNull String elementPath,
                                                                                                        @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition<T>(elementPath, componentName).componentPresent();
    }

    public static <T extends WebBlock> WebListBlockElementComponentPresentCondition<T> componentNotPresent(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition<T>(elementFrame, componentName).componentNotPresent();
    }

    public static <T extends WebBlock> WebListBlockElementComponentPresentCondition<T> componentNotPresent(@NotNull String elementPath,
                                                                                                           @NotNull String componentName) {
        return new WebListBlockElementComponentPresentCondition<T>(elementPath, componentName).componentNotPresent();
    }

    // Displayed

    public static <T extends WebBlock> WebListBlockElementDisplayedCondition<T> displayed(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebListBlockElementDisplayedCondition<T>(elementFrame).displayed();
    }

    public static <T extends WebBlock> WebListBlockElementDisplayedCondition<T> displayed(@NotNull String elementPath) {
        return new WebListBlockElementDisplayedCondition<T>(elementPath).displayed();
    }

    public static <T extends WebBlock> WebListBlockElementDisplayedCondition<T> notDisplayed(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebListBlockElementDisplayedCondition<T>(elementFrame).notDisplayed();
    }

    public static <T extends WebBlock> WebListBlockElementDisplayedCondition<T> notDisplayed(@NotNull String elementPath) {
        return new WebListBlockElementDisplayedCondition<T>(elementPath).notDisplayed();
    }

    // Present

    public static <T extends WebBlock> WebListBlockElementPresentCondition<T> present(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebListBlockElementPresentCondition<T>(elementFrame).present();
    }

    public static <T extends WebBlock> WebListBlockElementPresentCondition<T> present(@NotNull String elementPath) {
        return new WebListBlockElementPresentCondition<T>(elementPath).present();
    }

    public static <T extends WebBlock> WebListBlockElementPresentCondition<T> notPresent(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebListBlockElementPresentCondition<T>(elementFrame).notPresent();
    }

    public static <T extends WebBlock> WebListBlockElementPresentCondition<T> notPresent(@NotNull String elementPath) {
        return new WebListBlockElementPresentCondition<T>(elementPath).notPresent();
    }

    // Enabled

    public static <T extends WebBlock> WebListBlockElementEnabledCondition<T> enabled(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebListBlockElementEnabledCondition<T>(elementFrame).enabled();
    }

    public static <T extends WebBlock> WebListBlockElementEnabledCondition<T> enabled(@NotNull String elementPath) {
        return new WebListBlockElementEnabledCondition<T>(elementPath).enabled();
    }

    public static <T extends WebBlock> WebListBlockElementEnabledCondition<T> disabled(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebListBlockElementEnabledCondition<T>(elementFrame).disabled();
    }

    public static <T extends WebBlock> WebListBlockElementEnabledCondition<T> disabled(@NotNull String elementPath) {
        return new WebListBlockElementEnabledCondition<T>(elementPath).disabled();
    }

    // Selected

    public static <T extends WebBlock> WebListBlockElementSelectedCondition<T> selected(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebListBlockElementSelectedCondition<T>(elementFrame).selected();
    }

    public static <T extends WebBlock> WebListBlockElementSelectedCondition<T> selected(@NotNull String elementPath) {
        return new WebListBlockElementSelectedCondition<T>(elementPath).selected();
    }

    public static <T extends WebBlock> WebListBlockElementSelectedCondition<T> notSelected(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebListBlockElementSelectedCondition<T>(elementFrame).notSelected();
    }

    public static <T extends WebBlock> WebListBlockElementSelectedCondition<T> notSelected(@NotNull String elementPath) {
        return new WebListBlockElementSelectedCondition<T>(elementPath).notSelected();
    }

    // ContainsLabel

    public static <T extends WebBlock> WebListBlockElementLabelTextCondition<T> containsLabel(@NotNull String elementPath,
                                                                                              @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition<T>(elementPath, expectedText).containsLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelTextCondition<T> containsLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                              @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition<T>(elementFrame, expectedText).containsLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelTextCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                                @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition<T>(elementPath, expectedText).notContainLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelTextCondition<T> notContainLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                @NotNull String expectedText) {
        return new WebListBlockElementLabelTextCondition<T>(elementFrame, expectedText).notContainLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelStringValueCondition<T> containsLabel(@NotNull String elementPath,
                                                                                                     @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition<T>(elementPath, expectedStringValue).containsLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelStringValueCondition<T> containsLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                     @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition<T>(elementFrame, expectedStringValue).containsLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelStringValueCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                                       @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition<T>(elementPath, expectedStringValue).notContainLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelStringValueCondition<T> notContainLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                       @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementLabelStringValueCondition<T>(elementFrame, expectedStringValue).notContainLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelNumberValueCondition<T> containsLabel(@NotNull String elementPath,
                                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition<T>(elementPath, expectedNumberValue).containsLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelNumberValueCondition<T> containsLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition<T>(elementFrame, expectedNumberValue).containsLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelNumberValueCondition<T> notContainLabel(@NotNull String elementPath,
                                                                                                       @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition<T>(elementPath, expectedNumberValue).notContainLabel();
    }

    public static <T extends WebBlock> WebListBlockElementLabelNumberValueCondition<T> notContainLabel(@NotNull WebGetLabelAvailable elementFrame,
                                                                                                       @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementLabelNumberValueCondition<T>(elementFrame, expectedNumberValue).notContainLabel();
    }

    // ContainsProperty

    public static <T extends WebBlock> WebListBlockElementPropertyTextCondition<T> containsProperty(@NotNull WebChildElement elementFrame,
                                                                                                    @NotNull String propertyName,
                                                                                                    @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition<T>(elementFrame, propertyName, expectedText).containsProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyTextCondition<T> containsProperty(@NotNull String elementPath,
                                                                                                    @NotNull String propertyName,
                                                                                                    @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition<T>(elementPath, propertyName, expectedText).containsProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyTextCondition<T> notContainProperty(@NotNull WebChildElement elementFrame,
                                                                                                      @NotNull String propertyName,
                                                                                                      @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition<T>(elementFrame, propertyName, expectedText).notContainProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyTextCondition<T> notContainProperty(@NotNull String elementPath,
                                                                                                      @NotNull String propertyName,
                                                                                                      @NotNull String expectedText) {
        return new WebListBlockElementPropertyTextCondition<T>(elementPath, propertyName, expectedText).notContainProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyStringValueCondition<T> containsProperty(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String propertyName,
                                                                                                           @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition<T>(elementFrame, propertyName, expectedStringValue).containsProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyStringValueCondition<T> containsProperty(@NotNull String elementPath,
                                                                                                           @NotNull String propertyName,
                                                                                                           @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition<T>(elementPath, propertyName, expectedStringValue).containsProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyStringValueCondition<T> notContainProperty(@NotNull WebChildElement elementFrame,
                                                                                                             @NotNull String propertyName,
                                                                                                             @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition<T>(elementFrame, propertyName, expectedStringValue).notContainProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyStringValueCondition<T> notContainProperty(@NotNull String elementPath,
                                                                                                             @NotNull String propertyName,
                                                                                                             @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementPropertyStringValueCondition<T>(elementPath, propertyName, expectedStringValue).notContainProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyNumberValueCondition<T> containsProperty(@NotNull WebChildElement elementFrame,
                                                                                                           @NotNull String propertyName,
                                                                                                           @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition<T>(elementFrame, propertyName, expectedNumberValue).containsProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyNumberValueCondition<T> containsProperty(@NotNull String elementPath,
                                                                                                           @NotNull String propertyName,
                                                                                                           @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition<T>(elementPath, propertyName, expectedNumberValue).containsProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyNumberValueCondition<T> notContainProperty(@NotNull WebChildElement elementFrame,
                                                                                                             @NotNull String propertyName,
                                                                                                             @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition<T>(elementFrame, propertyName, expectedNumberValue).notContainProperty();
    }

    public static <T extends WebBlock> WebListBlockElementPropertyNumberValueCondition<T> notContainProperty(@NotNull String elementPath,
                                                                                                             @NotNull String propertyName,
                                                                                                             @NotNull NumberValue<? extends Number> expectedNumberValue) {
        return new WebListBlockElementPropertyNumberValueCondition<T>(elementPath, propertyName, expectedNumberValue).notContainProperty();
    }

    // ContainsText

    public static <T extends WebBlock> WebListBlockElementTextCondition<T> containsText(@NotNull String elementPath,
                                                                                        @NotNull String expectedText) {
        return new WebListBlockElementTextCondition<T>(elementPath, expectedText).containsText();
    }

    public static <T extends WebBlock> WebListBlockElementTextCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                        @NotNull String expectedText) {
        return new WebListBlockElementTextCondition<T>(elementFrame, expectedText).containsText();
    }

    public static <T extends WebBlock> WebListBlockElementTextCondition<T> notContainText(@NotNull String elementPath,
                                                                                          @NotNull String expectedText) {
        return new WebListBlockElementTextCondition<T>(elementPath, expectedText).notContainText();
    }

    public static <T extends WebBlock> WebListBlockElementTextCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                          @NotNull String expectedText) {
        return new WebListBlockElementTextCondition<T>(elementFrame, expectedText).notContainText();
    }

    public static <T extends WebBlock> WebListBlockElementTextStringValueCondition<T> containsText(@NotNull String elementPath,
                                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition<T>(elementPath, expectedStringValue).containsText();
    }

    public static <T extends WebBlock> WebListBlockElementTextStringValueCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition<T>(elementFrame, expectedStringValue).containsText();
    }

    public static <T extends WebBlock> WebListBlockElementTextStringValueCondition<T> notContainText(@NotNull String elementPath,
                                                                                                     @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition<T>(elementPath, expectedStringValue).notContainText();
    }

    public static <T extends WebBlock> WebListBlockElementTextStringValueCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                     @NotNull StringValue expectedStringValue) {
        return new WebListBlockElementTextStringValueCondition<T>(elementFrame, expectedStringValue).notContainText();
    }

    public static <T extends WebBlock> WebListBlockElementTextNumberValueCondition<T> containsText(@NotNull String elementPath,
                                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition<T>(elementPath, expectedNumberValue).containsText();
    }

    public static <T extends WebBlock> WebListBlockElementTextNumberValueCondition<T> containsText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition<T>(elementFrame, expectedNumberValue).containsText();
    }

    public static <T extends WebBlock> WebListBlockElementTextNumberValueCondition<T> notContainText(@NotNull String elementPath,
                                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition<T>(elementPath, expectedNumberValue).notContainText();
    }

    public static <T extends WebBlock> WebListBlockElementTextNumberValueCondition<T> notContainText(@NotNull WebGetTextAvailable elementFrame,
                                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
        return new WebListBlockElementTextNumberValueCondition<T>(elementFrame, expectedNumberValue).notContainText();
    }

    // haveState

    public static <T extends WebBlock> WebListBlockElementHaveStateCondition<T> haveState(@NotNull WebChildElement elementFrame,
                                                                                          @NotNull String stateName) {
        return new WebListBlockElementHaveStateCondition<T>(elementFrame, stateName).haveState();
    }

    public static <T extends WebBlock> WebListBlockElementHaveStateCondition<T> haveState(@NotNull String elementPath,
                                                                                          @NotNull String stateName) {
        return new WebListBlockElementHaveStateCondition<T>(elementPath, stateName).haveState();
    }

    public static <T extends WebBlock> WebListBlockElementHaveStateCondition<T> notHaveState(@NotNull WebChildElement elementFrame,
                                                                                             @NotNull String stateName) {
        return new WebListBlockElementHaveStateCondition<T>(elementFrame, stateName).notHaveState();
    }

    public static <T extends WebBlock> WebListBlockElementHaveStateCondition<T> notHaveState(@NotNull String elementPath,
                                                                                             @NotNull String stateName) {
        return new WebListBlockElementHaveStateCondition<T>(elementPath, stateName).notHaveState();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebTextList
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebTextListBlockEmptyCondition allTextBlocks() {
        return new WebTextListBlockEmptyCondition().allTextBlocks();
    }

    public static WebTextListBlockEmptyCondition noTextBlocks() {
        return new WebTextListBlockEmptyCondition().noTextBlocks();
    }

    // Index

    public static WebTextListBlockIndexCondition textBlockIndex(@NotNull Integer expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withTextBlockIndex();
    }

    public static WebTextListBlockIndexCondition textBlockIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withTextBlockIndex();
    }

    public static WebTextListBlockIndexCondition textBlockIndexNot(@NotNull Integer expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withoutTextBlockIndex();
    }

    public static WebTextListBlockIndexCondition textBlockIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextListBlockIndexCondition(expectedValue).withoutTextBlockIndex();
    }

    // ContainsText

    public static WebTextListBlockTextCondition containsTextBlock(@NotNull String expectedText) {
        return new WebTextListBlockTextCondition(expectedText).containsTextBlock();
    }

    public static WebTextListBlockTextCondition notContainTextBlock(@NotNull String expectedText) {
        return new WebTextListBlockTextCondition(expectedText).notContainTextBlock();
    }

    public static WebTextListBlockTextStringValueCondition containsTextBlock(@NotNull StringValue expectedStringValue) {
        return new WebTextListBlockTextStringValueCondition(expectedStringValue).containsTextBlock();
    }

    public static WebTextListBlockTextStringValueCondition notContainTextBlock(@NotNull StringValue expectedStringValue) {
        return new WebTextListBlockTextStringValueCondition(expectedStringValue).notContainTextBlock();
    }

    public static WebTextListBlockTextNumberValueCondition containsTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextListBlockTextNumberValueCondition(expectedNumberValue).containsTextBlock();
    }

    public static WebTextListBlockTextNumberValueCondition notContainTextBlock(@NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextListBlockTextNumberValueCondition(expectedNumberValue).notContainTextBlock();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebTable
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebTableRowEmptyCondition allRows() {
        return new WebTableRowEmptyCondition().allRows();
    }

    public static WebTableRowEmptyCondition noRows() {
        return new WebTableRowEmptyCondition().noRows();
    }

    // Index

    public static WebTableRowIndexCondition rowIndex(@NotNull Integer expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withRowIndex();
    }

    public static WebTableRowIndexCondition rowIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withRowIndex();
    }

    public static WebTableRowIndexCondition rowIndexNot(@NotNull Integer expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withoutRowIndexNot();
    }

    public static WebTableRowIndexCondition rowIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTableRowIndexCondition(expectedValue).withoutRowIndexNot();
    }

    // ComponentDisplayed

    public static WebTableCellElementComponentDisplayedCondition componentDisplayed(@NotNull String columnName,
                                                                                    @NotNull WebChildElement elementFrame,
                                                                                    @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementFrame, componentName).componentDisplayed();
    }

    public static WebTableCellElementComponentDisplayedCondition componentDisplayed(@NotNull String columnName,
                                                                                    @NotNull String elementPath,
                                                                                    @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementPath, componentName).componentDisplayed();
    }

    public static WebTableCellElementComponentDisplayedCondition componentNotDisplayed(@NotNull String columnName,
                                                                                       @NotNull WebChildElement elementFrame,
                                                                                       @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementFrame, componentName).componentNotDisplayed();
    }

    public static WebTableCellElementComponentDisplayedCondition componentNotDisplayed(@NotNull String columnName,
                                                                                       @NotNull String elementPath,
                                                                                       @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedCondition(columnName, elementPath, componentName).componentNotDisplayed();
    }

    // ComponentPresent

    public static WebTableCellElementComponentPresentCondition componentPresent(@NotNull String columnName,
                                                                                @NotNull WebChildElement elementFrame,
                                                                                @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementFrame, componentName).componentPresent();
    }

    public static WebTableCellElementComponentPresentCondition componentPresent(@NotNull String columnName,
                                                                                @NotNull String elementPath,
                                                                                @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementPath, componentName).componentPresent();
    }

    public static WebTableCellElementComponentPresentCondition componentNotPresent(@NotNull String columnName,
                                                                                   @NotNull WebChildElement elementFrame,
                                                                                   @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementFrame, componentName).componentNotPresent();
    }

    public static WebTableCellElementComponentPresentCondition componentNotPresent(@NotNull String columnName,
                                                                                   @NotNull String elementPath,
                                                                                   @NotNull String componentName) {
        return new WebTableCellElementComponentPresentCondition(columnName, elementPath, componentName).componentNotPresent();
    }

    // Displayed

    public static WebTableCellElementDisplayedCondition displayed(@NotNull String columnName, @NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebTableCellElementDisplayedCondition(columnName, elementFrame).displayed();
    }

    public static WebTableCellElementDisplayedCondition displayed(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementDisplayedCondition(columnName, elementPath).displayed();
    }

    public static WebTableCellElementDisplayedCondition notDisplayed(@NotNull String columnName, @NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebTableCellElementDisplayedCondition(columnName, elementFrame).notDisplayed();
    }

    public static WebTableCellElementDisplayedCondition notDisplayed(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementDisplayedCondition(columnName, elementPath).notDisplayed();
    }

    // Present

    public static WebTableCellElementPresentCondition present(@NotNull String columnName, @NotNull WebIsPresentAvailable elementFrame) {
        return new WebTableCellElementPresentCondition(columnName, elementFrame).present();
    }

    public static WebTableCellElementPresentCondition present(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementPresentCondition(columnName, elementPath).present();
    }

    public static WebTableCellElementPresentCondition notPresent(@NotNull String columnName, @NotNull WebIsPresentAvailable elementFrame) {
        return new WebTableCellElementPresentCondition(columnName, elementFrame).notPresent();
    }

    public static WebTableCellElementPresentCondition notPresent(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementPresentCondition(columnName, elementPath).notPresent();
    }

    // Enabled

    public static WebTableCellElementEnabledCondition enabled(@NotNull String columnName, @NotNull WebIsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledCondition(columnName, elementMock).enabled();
    }

    public static WebTableCellElementEnabledCondition enabled(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementEnabledCondition(columnName, elementName).enabled();
    }

    public static WebTableCellElementEnabledCondition disabled(@NotNull String columnName, @NotNull WebIsEnabledAvailable elementMock) {
        return new WebTableCellElementEnabledCondition(columnName, elementMock).disabled();
    }

    public static WebTableCellElementEnabledCondition disabled(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementEnabledCondition(columnName, elementName).disabled();
    }

    // Selected

    public static WebTableCellElementSelectedCondition selected(@NotNull String columnName, @NotNull WebIsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedCondition(columnName, elementMock).selected();
    }

    public static WebTableCellElementSelectedCondition selected(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementSelectedCondition(columnName, elementName).selected();
    }

    public static WebTableCellElementSelectedCondition notSelected(@NotNull String columnName, @NotNull WebIsSelectedAvailable elementMock) {
        return new WebTableCellElementSelectedCondition(columnName, elementMock).notSelected();
    }

    public static WebTableCellElementSelectedCondition notSelected(@NotNull String columnName, @NotNull String elementName) {
        return new WebTableCellElementSelectedCondition(columnName, elementName).notSelected();
    }

    // ContainsLabel

    public static WebTableCellElementLabelTextCondition containsLabel(@NotNull String columnName,
                                                                      @NotNull String elementPath,
                                                                      @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementPath, expectedText).containsLabel();
    }

    public static WebTableCellElementLabelTextCondition containsLabel(@NotNull String columnName,
                                                                      @NotNull WebGetLabelAvailable elementFrame,
                                                                      @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementFrame, expectedText).containsLabel();
    }

    public static WebTableCellElementLabelTextCondition notContainLabel(@NotNull String columnName,
                                                                        @NotNull String elementPath,
                                                                        @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementPath, expectedText).notContainLabel();
    }

    public static WebTableCellElementLabelTextCondition notContainLabel(@NotNull String columnName,
                                                                        @NotNull WebGetLabelAvailable elementFrame,
                                                                        @NotNull String expectedText) {
        return new WebTableCellElementLabelTextCondition(columnName, elementFrame, expectedText).notContainLabel();
    }

    public static WebTableCellElementLabelStringValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull String elementPath,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementPath, expectedStringValue).containsLabel();
    }

    public static WebTableCellElementLabelStringValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull WebGetLabelAvailable elementFrame,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementFrame, expectedStringValue).containsLabel();
    }

    public static WebTableCellElementLabelStringValueCondition notContainLabel(@NotNull String columnName,
                                                                               @NotNull String elementPath,
                                                                               @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementPath, expectedStringValue).notContainLabel();
    }

    public static WebTableCellElementLabelStringValueCondition notContainLabel(@NotNull String columnName,
                                                                               @NotNull WebGetLabelAvailable elementFrame,
                                                                               @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementLabelStringValueCondition(columnName, elementFrame, expectedStringValue).notContainLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull String elementPath,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementPath, expectedNumberValue).containsLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition containsLabel(@NotNull String columnName,
                                                                             @NotNull WebGetLabelAvailable elementFrame,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementFrame, expectedNumberValue).containsLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition notContainLabel(@NotNull String columnName,
                                                                               @NotNull String elementPath,
                                                                               @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementPath, expectedNumberValue).notContainLabel();
    }

    public static WebTableCellElementLabelNumberValueCondition notContainLabel(@NotNull String columnName,
                                                                               @NotNull WebGetLabelAvailable elementFrame,
                                                                               @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementLabelNumberValueCondition(columnName, elementFrame, expectedNumberValue).notContainLabel();
    }

    // ContainsProperty

    public static WebTableCellElementPropertyTextCondition containsProperty(@NotNull String columnName,
                                                                            @NotNull String elementPath,
                                                                            @NotNull String propertyName,
                                                                            @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementPath, propertyName, expectedText).containsProperty();
    }

    public static WebTableCellElementPropertyTextCondition containsProperty(@NotNull String columnName,
                                                                            @NotNull WebElementPropertyAvailable elementFrame,
                                                                            @NotNull String propertyName,
                                                                            @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementFrame, propertyName, expectedText).containsProperty();
    }

    public static WebTableCellElementPropertyTextCondition notContainProperty(@NotNull String columnName,
                                                                              @NotNull String elementPath,
                                                                              @NotNull String propertyName,
                                                                              @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementPath, propertyName, expectedText).notContainProperty();
    }

    public static WebTableCellElementPropertyTextCondition notContainProperty(@NotNull String columnName,
                                                                              @NotNull WebElementPropertyAvailable elementFrame,
                                                                              @NotNull String propertyName,
                                                                              @NotNull String expectedText) {
        return new WebTableCellElementPropertyTextCondition(columnName, elementFrame, propertyName, expectedText).notContainProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull String elementPath,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementPath, propertyName, expectedStringValue).containsProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull WebElementPropertyAvailable elementFrame,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementFrame, propertyName, expectedStringValue).containsProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition notContainProperty(@NotNull String columnName,
                                                                                     @NotNull String elementPath,
                                                                                     @NotNull String propertyName,
                                                                                     @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementPath, propertyName, expectedStringValue).notContainProperty();
    }

    public static WebTableCellElementPropertyStringValueCondition notContainProperty(@NotNull String columnName,
                                                                                     @NotNull WebElementPropertyAvailable elementFrame,
                                                                                     @NotNull String propertyName,
                                                                                     @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementPropertyStringValueCondition(columnName, elementFrame, propertyName, expectedStringValue).notContainProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull String elementPath,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementPath, propertyName, expectedNumberValue).containsProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition containsProperty(@NotNull String columnName,
                                                                                   @NotNull WebElementPropertyAvailable elementFrame,
                                                                                   @NotNull String propertyName,
                                                                                   @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementFrame, propertyName, expectedNumberValue).containsProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition notContainProperty(@NotNull String columnName,
                                                                                     @NotNull String elementPath,
                                                                                     @NotNull String propertyName,
                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementPath, propertyName, expectedNumberValue).notContainProperty();
    }

    public static WebTableCellElementPropertyNumberValueCondition notContainProperty(@NotNull String columnName,
                                                                                     @NotNull WebElementPropertyAvailable elementFrame,
                                                                                     @NotNull String propertyName,
                                                                                     @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementPropertyNumberValueCondition(columnName, elementFrame, propertyName, expectedNumberValue).notContainProperty();
    }

    // ContainsText

    public static WebTableCellElementTextCondition containsText(@NotNull String columnName,
                                                                @NotNull String elementPath,
                                                                @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementPath, expectedText).containsText();
    }

    public static WebTableCellElementTextCondition containsText(@NotNull String columnName,
                                                                @NotNull WebGetTextAvailable elementFrame,
                                                                @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementFrame, expectedText).containsText();
    }

    public static WebTableCellElementTextCondition notContainText(@NotNull String columnName,
                                                                  @NotNull String elementPath,
                                                                  @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementPath, expectedText).notContainText();
    }

    public static WebTableCellElementTextCondition notContainText(@NotNull String columnName,
                                                                  @NotNull WebGetTextAvailable elementFrame,
                                                                  @NotNull String expectedText) {
        return new WebTableCellElementTextCondition(columnName, elementFrame, expectedText).notContainText();
    }

    public static WebTableCellElementTextStringValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull String elementPath,
                                                                           @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementPath, expectedStringValue).containsText();
    }

    public static WebTableCellElementTextStringValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull WebGetTextAvailable elementFrame,
                                                                           @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementFrame, expectedStringValue).containsText();
    }

    public static WebTableCellElementTextStringValueCondition notContainText(@NotNull String columnName,
                                                                             @NotNull String elementPath,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementPath, expectedStringValue).notContainText();
    }

    public static WebTableCellElementTextStringValueCondition notContainText(@NotNull String columnName,
                                                                             @NotNull WebGetTextAvailable elementFrame,
                                                                             @NotNull StringValue expectedStringValue) {
        return new WebTableCellElementTextStringValueCondition(columnName, elementFrame, expectedStringValue).notContainText();
    }

    public static WebTableCellElementTextNumberValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull String elementPath,
                                                                           @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementPath, expectedNumberValue).containsText();
    }

    public static WebTableCellElementTextNumberValueCondition containsText(@NotNull String columnName,
                                                                           @NotNull WebGetTextAvailable elementFrame,
                                                                           @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementFrame, expectedNumberValue).containsText();
    }

    public static WebTableCellElementTextNumberValueCondition notContainText(@NotNull String columnName,
                                                                             @NotNull String elementPath,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementPath, expectedNumberValue).notContainText();
    }

    public static WebTableCellElementTextNumberValueCondition notContainText(@NotNull String columnName,
                                                                             @NotNull WebGetTextAvailable elementFrame,
                                                                             @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTableCellElementTextNumberValueCondition(columnName, elementFrame, expectedNumberValue).notContainText();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //   WebTextTable
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    // Empty

    public static WebTextTableRowEmptyCondition allTextRows() {
        return new WebTextTableRowEmptyCondition().allTextRows();
    }

    public static WebTextTableRowEmptyCondition noTextRows() {
        return new WebTextTableRowEmptyCondition().noTextRows();
    }

    // Index

    public static WebTextTableRowIndexCondition textRowIndex(@NotNull Integer expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withTextRowIndex();
    }

    public static WebTextTableRowIndexCondition textRowIndex(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withTextRowIndex();
    }

    public static WebTextTableRowIndexCondition textRowIndexNot(@NotNull Integer expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withoutTextRowIndex();
    }

    public static WebTextTableRowIndexCondition textRowIndexNot(@NotNull NumberValue<Integer> expectedValue) {
        return new WebTextTableRowIndexCondition(expectedValue).withoutTextRowIndex();
    }

    // ContainsText

    public static WebTextTableRowTextCondition containsTextCell(@NotNull String columnName, @NotNull String expectedText) {
        return new WebTextTableRowTextCondition(columnName, expectedText).containsTextCell();
    }

    public static WebTextTableRowTextCondition notContainTextCell(@NotNull String columnName, @NotNull String expectedText) {
        return new WebTextTableRowTextCondition(columnName, expectedText).notContainTextCell();
    }

    public static WebTextTableRowTextStringValueCondition containsTextCell(@NotNull String columnName, @NotNull StringValue expectedStringValue) {
        return new WebTextTableRowTextStringValueCondition(columnName, expectedStringValue).containsTextCell();
    }

    public static WebTextTableRowTextStringValueCondition notContainTextCell(@NotNull String columnName, @NotNull StringValue expectedStringValue) {
        return new WebTextTableRowTextStringValueCondition(columnName, expectedStringValue).notContainTextCell();
    }

    public static WebTextTableRowTextNumberValueCondition containsTextCell(@NotNull String columnName, @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextTableRowTextNumberValueCondition(columnName, expectedNumberValue).containsTextCell();
    }

    public static WebTextTableRowTextNumberValueCondition notContainTextCell(@NotNull String columnName, @NotNull NumberValue<?> expectedNumberValue) {
        return new WebTextTableRowTextNumberValueCondition(columnName, expectedNumberValue).notContainTextCell();
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

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull WebList fromList,
                                                                                        @NotNull WebListFilterBuilder blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlock(@NotNull String fromList,
                                                                                        @NotNull WebListFilterBuilder blockFilter) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull WebList fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         int expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebListBlockContextLimiter<T> selectWebListBlocks(@NotNull String fromList,
                                                                                         @NotNull WebListFilterBuilder blockFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebListBlockContextLimiter.of(fromList, blockFilter, expectedSize);
    }

    // WebTable row limiters

    public static WebTableRowContextLimiter selectWebTableRow(@NotNull String fromTable,
                                                              @NotNull WebTableFilterBuilder rowFilter) {
        return WebTableRowContextLimiter.of(fromTable, rowFilter, intEquals(1));
    }

    public static WebTableRowContextLimiter selectWebTableRows(@NotNull String fromTable,
                                                               @NotNull WebTableFilterBuilder rowFilter,
                                                               int expectedSize) {
        return WebTableRowContextLimiter.of(fromTable, rowFilter, intEquals(expectedSize));
    }

    public static WebTableRowContextLimiter selectWebTableRows(@NotNull String fromTable,
                                                               @NotNull WebTableFilterBuilder rowFilter,
                                                               @NotNull NumberValue<Integer> expectedSize) {
        return WebTableRowContextLimiter.of(fromTable, rowFilter, expectedSize);
    }

    // WebTable cell limiters

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCell(@NotNull WebTable fromTable,
                                                                                        @NotNull String fromColumn,
                                                                                        @NotNull WebTableFilterBuilder rowFilter) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCell(@NotNull String fromTable,
                                                                                        @NotNull String fromColumn,
                                                                                        @NotNull WebTableFilterBuilder rowFilter) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(1));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull WebTable fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         int expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull WebTable fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, expectedSize);
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull String fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         int expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, intEquals(expectedSize));
    }

    public static <T extends WebBlock> WebTableCellContextLimiter<T> selectWebTableCells(@NotNull String fromTable,
                                                                                         @NotNull String fromColumn,
                                                                                         @NotNull WebTableFilterBuilder rowFilter,
                                                                                         @NotNull NumberValue<Integer> expectedSize) {
        return WebTableCellContextLimiter.of(fromTable, fromColumn, rowFilter, expectedSize);
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

    public static <T extends WebBlock> WebListBlockIndexExtractor<T> blockIndex() {
        return new WebListBlockIndexExtractor<>();
    }

    public static <T extends WebBlock> WebListBlockExtractor<WebBlock, T> block() {
        return new WebListBlockExtractor<>(WebBlock.class);
    }

    public static <R extends WebBlock, T extends WebBlock> WebListBlockExtractor<R, T> block(@NotNull Class<R> blockClass) {
        return new WebListBlockExtractor<>(blockClass);
    }

    public static <R extends WebChildElement, T extends WebBlock> WebListBlockElementExtractor<R, T> element(@NotNull R elementFrame) {
        return new WebListBlockElementExtractor<>(elementFrame);
    }

    public static <R extends WebChildElement, T extends WebBlock> WebListBlockElementExtractor<R, T> element(@NotNull String elementPath,
                                                                                                             @NotNull Class<R> returnType) {
        return new WebListBlockElementExtractor<>(elementPath, returnType);
    }

    public static <T extends WebBlock> WebListBlockElementDisplayedMarkExtractor<T> displayedMark(@NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebListBlockElementDisplayedMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebListBlockElementDisplayedMarkExtractor<T> displayedMark(@NotNull String elementPath) {
        return new WebListBlockElementDisplayedMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebListBlockElementPresentMarkExtractor<T> presentMark(@NotNull WebIsPresentAvailable elementFrame) {
        return new WebListBlockElementPresentMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebListBlockElementPresentMarkExtractor<T> presentMark(@NotNull String elementPath) {
        return new WebListBlockElementPresentMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebListBlockElementEnabledMarkExtractor<T> enabledMark(@NotNull WebIsEnabledAvailable elementFrame) {
        return new WebListBlockElementEnabledMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebListBlockElementEnabledMarkExtractor<T> enabledMark(@NotNull String elementPath) {
        return new WebListBlockElementEnabledMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebListBlockElementSelectedMarkExtractor<T> selectedMark(@NotNull WebIsSelectedAvailable elementFrame) {
        return new WebListBlockElementSelectedMarkExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebListBlockElementSelectedMarkExtractor<T> selectedMark(@NotNull String elementPath) {
        return new WebListBlockElementSelectedMarkExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebListBlockElementTextValueExtractor<T> textValue(@NotNull WebGetTextAvailable elementFrame) {
        return new WebListBlockElementTextValueExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebListBlockElementTextValueExtractor<T> textValue(@NotNull String elementPath) {
        return new WebListBlockElementTextValueExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebListBlockElementLabelValueExtractor<T> labelValue(@NotNull WebGetLabelAvailable elementFrame) {
        return new WebListBlockElementLabelValueExtractor<>(elementFrame);
    }

    public static <T extends WebBlock> WebListBlockElementLabelValueExtractor<T> labelValue(@NotNull String elementPath) {
        return new WebListBlockElementLabelValueExtractor<>(elementPath);
    }

    public static <T extends WebBlock> WebListBlockElementPropertyValueExtractor<T> propertyValue(@NotNull WebChildElement elementFrame,
                                                                                                  @NotNull String propertyName) {
        return new WebListBlockElementPropertyValueExtractor<>(elementFrame, propertyName);
    }

    public static <T extends WebBlock> WebListBlockElementPropertyValueExtractor<T> propertyValue(@NotNull String elementPath,
                                                                                                  @NotNull String propertyName) {
        return new WebListBlockElementPropertyValueExtractor<>(elementPath, propertyName);
    }

    public static <T extends WebBlock> WebListBlockElementComponentDisplayedMarkExtractor<T> componentDisplayedMark(@NotNull WebComponentAvailable elementFrame,
                                                                                                                    @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedMarkExtractor<>(elementFrame, componentName);
    }

    public static <T extends WebBlock> WebListBlockElementComponentDisplayedMarkExtractor<T> componentDisplayedMark(@NotNull String elementPath,
                                                                                                                    @NotNull String componentName) {
        return new WebListBlockElementComponentDisplayedMarkExtractor<>(elementPath, componentName);
    }

    public static <T extends WebBlock> WebListBlockElementComponentPresentMarkExtractor<T> componentPresentMark(@NotNull WebComponentAvailable elementFrame,
                                                                                                                @NotNull String componentName) {
        return new WebListBlockElementComponentPresentMarkExtractor<>(elementFrame, componentName);
    }

    public static <T extends WebBlock> WebListBlockElementComponentPresentMarkExtractor<T> componentPresentMark(@NotNull String elementPath,
                                                                                                                @NotNull String componentName) {
        return new WebListBlockElementComponentPresentMarkExtractor<>(elementPath, componentName);
    }

    // WebTextList

    public static WebTextListBlockIndexExtractor textBlockIndex() {
        return new WebTextListBlockIndexExtractor();
    }

    public static WebTextListBlockExtractor textBlock() {
        return new WebTextListBlockExtractor();
    }

    public static WebTextListBlockElementExtractor textBlockElement() {
        return new WebTextListBlockElementExtractor();
    }

    public static WebTextListBlockTextValueExtractor textBlockValue() {
        return new WebTextListBlockTextValueExtractor();
    }

    // WebTable

    public static WebTableRowIndexExtractor rowIndex() {
        return new WebTableRowIndexExtractor();
    }

    public static WebTableRowExtractor row() {
        return new WebTableRowExtractor();
    }

    public static WebTableCellExtractor<WebBlock> cell(@NotNull String columnName) {
        return new WebTableCellExtractor<>(columnName, WebBlock.class);
    }

    public static <T extends WebBlock> WebTableCellExtractor<T> cell(@NotNull String columnName,
                                                                     @NotNull Class<T> cellClass) {
        return new WebTableCellExtractor<>(columnName, cellClass);
    }

    public static <T extends WebChildElement> WebTableCellElementExtractor<T> element(@NotNull String columnName,
                                                                                      @NotNull T elementFrame) {
        return new WebTableCellElementExtractor<>(columnName, elementFrame);
    }

    public static <T extends WebChildElement> WebTableCellElementExtractor<T> element(@NotNull String columnName,
                                                                                      @NotNull String elementPath,
                                                                                      @NotNull Class<T> returnType) {
        return new WebTableCellElementExtractor<>(columnName, elementPath, returnType);
    }

    public static WebTableCellElementDisplayedMarkExtractor displayedMark(@NotNull String columnName,
                                                                          @NotNull WebIsDisplayedAvailable elementFrame) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementDisplayedMarkExtractor displayedMark(@NotNull String columnName,
                                                                          @NotNull String elementPath) {
        return new WebTableCellElementDisplayedMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementPresentMarkExtractor presentMark(@NotNull String columnName,
                                                                      @NotNull WebIsPresentAvailable elementFrame) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementPresentMarkExtractor presentMark(@NotNull String columnName,
                                                                      @NotNull String elementPath) {
        return new WebTableCellElementPresentMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementEnabledMarkExtractor enabledMark(@NotNull String columnName,
                                                                      @NotNull WebIsEnabledAvailable elementFrame) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementEnabledMarkExtractor enabledMark(@NotNull String columnName,
                                                                      @NotNull String elementPath) {
        return new WebTableCellElementEnabledMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementSelectedMarkExtractor selectedMark(@NotNull String columnName,
                                                                        @NotNull WebIsSelectedAvailable elementFrame) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementSelectedMarkExtractor selectedMark(@NotNull String columnName,
                                                                        @NotNull String elementPath) {
        return new WebTableCellElementSelectedMarkExtractor(columnName, elementPath);
    }

    public static WebTableCellElementTextValueExtractor textValue(@NotNull String columnName,
                                                                  @NotNull WebGetTextAvailable elementFrame) {
        return new WebTableCellElementTextValueExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementTextValueExtractor textValue(@NotNull String columnName,
                                                                  @NotNull String elementPath) {
        return new WebTableCellElementTextValueExtractor(columnName, elementPath);
    }

    public static WebTableCellElementLabelValueExtractor labelValue(@NotNull String columnName,
                                                                    @NotNull WebGetLabelAvailable elementFrame) {
        return new WebTableCellElementLabelValueExtractor(columnName, elementFrame);
    }

    public static WebTableCellElementLabelValueExtractor labelValue(@NotNull String columnName,
                                                                    @NotNull String elementPath) {
        return new WebTableCellElementLabelValueExtractor(columnName, elementPath);
    }

    public static WebTableCellElementPropertyValueExtractor propertyValue(@NotNull String columnName,
                                                                          @NotNull WebChildElement elementFrame,
                                                                          @NotNull String propertyName) {
        return new WebTableCellElementPropertyValueExtractor(columnName, elementFrame, propertyName);
    }

    public static WebTableCellElementPropertyValueExtractor propertyValue(@NotNull String columnName,
                                                                          @NotNull String elementPath,
                                                                          @NotNull String propertyName) {
        return new WebTableCellElementPropertyValueExtractor(columnName, elementPath, propertyName);
    }

    public static WebTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String columnName,
                                                                                            @NotNull WebChildElement elementMock,
                                                                                            @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedMarkExtractor(columnName, elementMock, componentName);
    }

    public static WebTableCellElementComponentDisplayedMarkExtractor componentDisplayedMark(@NotNull String columnName,
                                                                                            @NotNull String elementName,
                                                                                            @NotNull String componentName) {
        return new WebTableCellElementComponentDisplayedMarkExtractor(columnName, elementName, componentName);
    }

    public static WebTableCellElementComponentPresentMarkExtractor componentPresentMark(@NotNull String columnName,
                                                                                        @NotNull WebChildElement elementMock,
                                                                                        @NotNull String componentName) {
        return new WebTableCellElementComponentPresentMarkExtractor(columnName, elementMock, componentName);
    }

    public static WebTableCellElementComponentPresentMarkExtractor componentPresentMark(@NotNull String columnName,
                                                                                        @NotNull String elementName,
                                                                                        @NotNull String componentName) {
        return new WebTableCellElementComponentPresentMarkExtractor(columnName, elementName, componentName);
    }

    // WebTextTable

    public static WebTextTableRowIndexExtractor textRowIndex() {
        return new WebTextTableRowIndexExtractor();
    }

    public static WebTextTableRowExtractor textRow() {
        return new WebTextTableRowExtractor();
    }

    public static WebTextTableCellElementExtractor textRowElement(@NotNull String columnName) {
        return new WebTextTableCellElementExtractor(columnName);
    }

    public static WebTextTableCellTextValueExtractor textCellValue(@NotNull String columnName) {
        return new WebTextTableCellTextValueExtractor(columnName);
    }

}
