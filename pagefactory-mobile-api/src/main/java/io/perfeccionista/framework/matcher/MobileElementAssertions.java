package io.perfeccionista.framework.matcher;

import io.perfeccionista.framework.matcher.method.implementations.MobileComponentShouldBeDisplayedMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileComponentShouldBePresentMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileShouldBeDisplayedMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileShouldBePresentMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileShouldBeSelectedAvailableMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileShouldHaveStateMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileShouldHaveTextNumberValueMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileShouldHaveTextStringMatcher;
import io.perfeccionista.framework.matcher.method.implementations.MobileShouldHaveTextStringValueMatcher;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public class MobileElementAssertions {

    private MobileElementAssertions() {
    }

    // GetText

    public static MobileShouldHaveTextStringMatcher haveText(@NotNull String expectedText) {
        return new MobileShouldHaveTextStringMatcher(expectedText, true);
    }

    public static MobileShouldHaveTextStringValueMatcher haveText(@NotNull StringValue expectedValue) {
        return new MobileShouldHaveTextStringValueMatcher(expectedValue, true);
    }

    public static MobileShouldHaveTextNumberValueMatcher haveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new MobileShouldHaveTextNumberValueMatcher(expectedValue, true);
    }

    public static MobileShouldHaveTextStringMatcher notHaveText(@NotNull String expectedText) {
        return new MobileShouldHaveTextStringMatcher(expectedText, false);
    }

    public static MobileShouldHaveTextStringValueMatcher notHaveText(@NotNull StringValue expectedValue) {
        return new MobileShouldHaveTextStringValueMatcher(expectedValue, false);
    }

    public static MobileShouldHaveTextNumberValueMatcher notHaveText(@NotNull NumberValue<? extends Number> expectedValue) {
        return new MobileShouldHaveTextNumberValueMatcher(expectedValue, false);
    }

    // IsDisplayed

    public static MobileShouldBeDisplayedMatcher beDisplayed() {
        return new MobileShouldBeDisplayedMatcher(true);
    }

    public static MobileShouldBeDisplayedMatcher notBeDisplayed() {
        return new MobileShouldBeDisplayedMatcher(false);
    }

    // IsSelected

    public static MobileShouldBeSelectedAvailableMatcher beSelected() {
        return new MobileShouldBeSelectedAvailableMatcher(true);
    }

    public static MobileShouldBeSelectedAvailableMatcher notBeSelected() {
        return new MobileShouldBeSelectedAvailableMatcher(false);
    }

    // IsPresent

    public static MobileShouldBePresentMatcher bePresent() {
        return new MobileShouldBePresentMatcher(true);
    }

    public static MobileShouldBePresentMatcher notBePresent() {
        return new MobileShouldBePresentMatcher(false);
    }



    // MobileComponent

    public static MobileComponentShouldBeDisplayedMatcher componentBeDisplayed(@NotNull String componentName) {
        return new MobileComponentShouldBeDisplayedMatcher(componentName, true);
    }

    public static MobileComponentShouldBeDisplayedMatcher componentNotBeDisplayed(@NotNull String componentName) {
        return new MobileComponentShouldBeDisplayedMatcher(componentName, false);
    }

    public static MobileComponentShouldBePresentMatcher componentBePresent(@NotNull String componentName) {
        return new MobileComponentShouldBePresentMatcher(componentName, true);
    }

    public static MobileComponentShouldBePresentMatcher componentNotBePresent(@NotNull String componentName) {
        return new MobileComponentShouldBePresentMatcher(componentName, false);
    }

    // GetState

    public static MobileShouldHaveStateMatcher haveState(@NotNull String stateName) {
        return new MobileShouldHaveStateMatcher(stateName, true);
    }

    public static MobileShouldHaveStateMatcher notHaveState(@NotNull String stateName) {
        return new MobileShouldHaveStateMatcher(stateName, false);
    }

}
