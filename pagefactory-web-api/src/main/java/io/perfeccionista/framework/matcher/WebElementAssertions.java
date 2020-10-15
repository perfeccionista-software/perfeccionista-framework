package io.perfeccionista.framework.matcher;

import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.matcher.actions.GetColorMatcher;
import io.perfeccionista.framework.matcher.actions.GetDimensionsMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelNumberValueMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelStringValueMatcher;
import io.perfeccionista.framework.matcher.actions.GetLabelTextMatcher;
import io.perfeccionista.framework.matcher.actions.GetLocationMatcher;
import io.perfeccionista.framework.matcher.actions.GetScreenshotMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextNumberValueMatcher;
import io.perfeccionista.framework.matcher.actions.GetTextStringValueMatcher;
import io.perfeccionista.framework.matcher.actions.IsClosedMatcher;
import io.perfeccionista.framework.matcher.actions.IsDisplayedMatcher;
import io.perfeccionista.framework.matcher.actions.IsEnabledMatcher;
import io.perfeccionista.framework.matcher.actions.IsInFocusMatcher;
import io.perfeccionista.framework.matcher.actions.IsOnTheScreenMatcher;
import io.perfeccionista.framework.matcher.actions.IsOpenMatcher;
import io.perfeccionista.framework.matcher.actions.IsPresentMatcher;
import io.perfeccionista.framework.matcher.actions.IsSelectedMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentIsDisplayedMatcher;
import io.perfeccionista.framework.matcher.actions.WebComponentIsPresentMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyNumberValueMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyStringValueMatcher;
import io.perfeccionista.framework.matcher.actions.WebElementPropertyTextMatcher;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.screenshots.Screenshot;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class WebElementAssertions {

    protected WebElementAssertions() {
    }

    // GetColor

    public static GetColorMatcher haveColor(@NotNull String cssProperty, @NotNull Color expectedColor) {
        return new GetColorMatcher(ROOT, cssProperty, expectedColor, true);
    }

    public static GetColorMatcher haveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        return new GetColorMatcher(componentName, cssProperty, expectedColor, true);
    }

    public static GetColorMatcher notHaveColor(@NotNull String cssProperty, @NotNull Color expectedColor) {
        return new GetColorMatcher(ROOT, cssProperty, expectedColor, false);
    }

    public static GetColorMatcher notHaveColor(@NotNull String componentName, @NotNull String cssProperty, @NotNull Color expectedColor) {
        return new GetColorMatcher(componentName, cssProperty, expectedColor, false);
    }

    // GetDimensions

    public static GetDimensionsMatcher haveDimensions(@NotNull Dimensions expectedDimensions) {
        return new GetDimensionsMatcher(ROOT, expectedDimensions, true);
    }

    public static GetDimensionsMatcher haveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        return new GetDimensionsMatcher(componentName, expectedDimensions, true);
    }

    public static GetDimensionsMatcher notHaveDimensions(@NotNull Dimensions expectedDimensions) {
        return new GetDimensionsMatcher(ROOT, expectedDimensions, false);
    }

    public static GetDimensionsMatcher notHaveDimensions(@NotNull String componentName, @NotNull Dimensions expectedDimensions) {
        return new GetDimensionsMatcher(componentName, expectedDimensions, false);
    }

    // GetLabel

    public static GetLabelTextMatcher haveLabel(@NotNull String expectedText) {
        return new GetLabelTextMatcher(expectedText, true);
    }

    public static GetLabelStringValueMatcher haveLabel(@NotNull StringValue expectedValue) {
        return new GetLabelStringValueMatcher(expectedValue, true);
    }

    public static GetLabelNumberValueMatcher haveLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new GetLabelNumberValueMatcher(expectedValue, true);
    }

    public static GetLabelTextMatcher notHaveLabel(@NotNull String expectedText) {
        return new GetLabelTextMatcher(expectedText, false);
    }

    public static GetLabelStringValueMatcher notHaveLabel(@NotNull StringValue expectedValue) {
        return new GetLabelStringValueMatcher(expectedValue, false);
    }

    public static GetLabelNumberValueMatcher notHaveLabel(@NotNull NumberValue<? extends Number> expectedValue) {
        return new GetLabelNumberValueMatcher(expectedValue, false);
    }

    // GetLocation

    public static GetLocationMatcher haveLocation(@NotNull Location expectedLocation) {
        return new GetLocationMatcher(ROOT, expectedLocation, true);
    }

    public static GetLocationMatcher haveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        return new GetLocationMatcher(componentName, expectedLocation, true);
    }

    public static GetLocationMatcher notHaveLocation(@NotNull Location expectedLocation) {
        return new GetLocationMatcher(ROOT, expectedLocation, false);
    }

    public static GetLocationMatcher notHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation) {
        return new GetLocationMatcher(componentName, expectedLocation, false);
    }

    // GetScreenshot

    public static GetScreenshotMatcher looksLike(@NotNull Screenshot expectedScreenshot) {
        return new GetScreenshotMatcher(ROOT, expectedScreenshot, true);
    }

    public static GetScreenshotMatcher looksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        return new GetScreenshotMatcher(componentName, expectedScreenshot, true);
    }

    public static GetScreenshotMatcher notLooksLike(@NotNull Screenshot expectedScreenshot) {
        return new GetScreenshotMatcher(ROOT, expectedScreenshot, false);
    }

    public static GetScreenshotMatcher notLooksLike(@NotNull String componentName, @NotNull Screenshot expectedScreenshot) {
        return new GetScreenshotMatcher(componentName, expectedScreenshot, false);
    }

    // GetText

    public static GetTextMatcher haveText(@NotNull String expectedText) {
        return new GetTextMatcher(expectedText, true);
    }

    public static GetTextStringValueMatcher haveText(@NotNull StringValue expectedValue) {
        return new GetTextStringValueMatcher(expectedValue, true);
    }

    public static GetTextNumberValueMatcher haveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new GetTextNumberValueMatcher(expectedValue, true);
    }

    public static GetTextMatcher notHaveText(@NotNull String expectedText) {
        return new GetTextMatcher(expectedText, false);
    }

    public static GetTextStringValueMatcher notHaveText(@NotNull StringValue expectedValue) {
        return new GetTextStringValueMatcher(expectedValue, false);
    }

    public static GetTextNumberValueMatcher notHaveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new GetTextNumberValueMatcher(expectedValue, false);
    }

    // IsDisplayed

    public static IsDisplayedMatcher beDisplayed() {
        return new IsDisplayedMatcher(true);
    }

    public static IsDisplayedMatcher notBeDisplayed() {
        return new IsDisplayedMatcher(false);
    }

    // IsEnabled

    public static IsEnabledMatcher beEnabled() {
        return new IsEnabledMatcher(true);
    }

    public static IsEnabledMatcher beDisabled() {
        return new IsEnabledMatcher(false);
    }

    // IsInFocus

    public static IsInFocusMatcher beInFocus() {
        return new IsInFocusMatcher(true);
    }

    public static IsInFocusMatcher notBeInFocus() {
        return new IsInFocusMatcher(false);
    }

    // IsOnTheScreen

    public static IsOnTheScreenMatcher completelyBeOnTheScreen() {
        return new IsOnTheScreenMatcher(true);
    }

    public static IsOnTheScreenMatcher completelyNotBeOnTheScreen() {
        return new IsOnTheScreenMatcher(false);
    }

    // IsOpen

    public static IsOpenMatcher beOpen() {
        return new IsOpenMatcher();
    }

    public static IsClosedMatcher beClosed() {
        return new IsClosedMatcher();
    }

    // IsPresent

    public static IsPresentMatcher bePresent() {
        return new IsPresentMatcher(true);
    }

    public static IsPresentMatcher notBePresent() {
        return new IsPresentMatcher(false);
    }

    // IsSelected

    public static IsSelectedMatcher beSelected() {
        return new IsSelectedMatcher(true);
    }

    public static IsSelectedMatcher notBeSelected() {
        return new IsSelectedMatcher(false);
    }

    // WebComponent

    public static WebComponentIsDisplayedMatcher componentBeDisplayed(@NotNull String componentName) {
        return new WebComponentIsDisplayedMatcher(componentName, true);
    }

    public static WebComponentIsDisplayedMatcher componentNotBeDisplayed(@NotNull String componentName) {
        return new WebComponentIsDisplayedMatcher(componentName, false);
    }

    public static WebComponentIsPresentMatcher componentBePresent(@NotNull String componentName) {
        return new WebComponentIsPresentMatcher(componentName, true);
    }

    public static WebComponentIsPresentMatcher componentNotBePresent(@NotNull String componentName) {
        return new WebComponentIsPresentMatcher(componentName, false);
    }

    // WebElementProperty

    public static WebElementPropertyTextMatcher havePropertyValue(@NotNull String propertyName, @NotNull String expectedText) {
        return new WebElementPropertyTextMatcher(propertyName, expectedText, true);
    }

    public static WebElementPropertyStringValueMatcher havePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        return new WebElementPropertyStringValueMatcher(propertyName, expectedValue, true);
    }

    public static WebElementPropertyNumberValueMatcher havePropertyValue(@NotNull String propertyName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebElementPropertyNumberValueMatcher(propertyName, expectedValue, true);
    }

    public static WebElementPropertyTextMatcher notHavePropertyValue(@NotNull String propertyName, @NotNull String expectedText) {
        return new WebElementPropertyTextMatcher(propertyName, expectedText, false);
    }

    public static WebElementPropertyStringValueMatcher notHavePropertyValue(@NotNull String propertyName, @NotNull StringValue expectedValue) {
        return new WebElementPropertyStringValueMatcher(propertyName, expectedValue, false);
    }

    public static WebElementPropertyNumberValueMatcher notHavePropertyValue(@NotNull String propertyName, @NotNull NumberValue<? extends Number> expectedValue) {
        return new WebElementPropertyNumberValueMatcher(propertyName, expectedValue, false);
    }

    //

}
