package io.perfeccionista.framework.matcher;

import io.perfeccionista.framework.matcher.browser.ActiveTabShouldHaveTitleStringMatcher;
import io.perfeccionista.framework.matcher.browser.ActiveTabShouldHaveTitleStringValueMatcher;
import io.perfeccionista.framework.matcher.browser.ActiveTabShouldHaveUrlStringMatcher;
import io.perfeccionista.framework.matcher.browser.ActiveTabShouldHaveUrlStringValueMatcher;
import io.perfeccionista.framework.matcher.browser.BrowserShouldHaveTabWithTitleStringMatcher;
import io.perfeccionista.framework.matcher.browser.BrowserShouldHaveTabWithTitleStringValueMatcher;
import io.perfeccionista.framework.matcher.browser.BrowserShouldHaveTabWithUrlStringMatcher;
import io.perfeccionista.framework.matcher.browser.BrowserShouldHaveTabWithUrlStringValueMatcher;
import io.perfeccionista.framework.matcher.browser.TabCountNumberMatcher;
import io.perfeccionista.framework.matcher.browser.TabCountNumberValueMatcher;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public class WebBrowserAssertions {

    private WebBrowserAssertions() {
    }

    // ActiveTabShouldHaveTitle

    public static ActiveTabShouldHaveTitleStringMatcher activeTabHaveTitle(@NotNull String expectedText) {
        return new ActiveTabShouldHaveTitleStringMatcher(expectedText, true);
    }

    public static ActiveTabShouldHaveTitleStringValueMatcher activeTabHaveTitle(@NotNull StringValue expectedValue) {
        return new ActiveTabShouldHaveTitleStringValueMatcher(expectedValue, true);
    }

    public static ActiveTabShouldHaveTitleStringMatcher activeTabNotHaveTitle(@NotNull String expectedText) {
        return new ActiveTabShouldHaveTitleStringMatcher(expectedText, false);
    }

    public static ActiveTabShouldHaveTitleStringValueMatcher activeTabNotHaveTitle(@NotNull StringValue expectedValue) {
        return new ActiveTabShouldHaveTitleStringValueMatcher(expectedValue, false);
    }

    // ActiveTabShouldHaveUrl

    public static ActiveTabShouldHaveUrlStringMatcher activeTabHaveUrl(@NotNull String expectedText) {
        return new ActiveTabShouldHaveUrlStringMatcher(expectedText, true);
    }

    public static ActiveTabShouldHaveUrlStringValueMatcher activeTabHaveUrl(@NotNull StringValue expectedValue) {
        return new ActiveTabShouldHaveUrlStringValueMatcher(expectedValue, true);
    }

    public static ActiveTabShouldHaveUrlStringMatcher activeTabNotHaveUrl(@NotNull String expectedText) {
        return new ActiveTabShouldHaveUrlStringMatcher(expectedText, false);
    }

    public static ActiveTabShouldHaveUrlStringValueMatcher activeTabNotHaveUrl(@NotNull StringValue expectedValue) {
        return new ActiveTabShouldHaveUrlStringValueMatcher(expectedValue, false);
    }

    // BrowserShouldHaveTabWithTitle

    public static BrowserShouldHaveTabWithTitleStringMatcher haveTabWithTitle(@NotNull String expectedText) {
        return new BrowserShouldHaveTabWithTitleStringMatcher(expectedText, true);
    }

    public static BrowserShouldHaveTabWithTitleStringValueMatcher haveTabWithTitle(@NotNull StringValue expectedValue) {
        return new BrowserShouldHaveTabWithTitleStringValueMatcher(expectedValue, true);
    }

    public static BrowserShouldHaveTabWithTitleStringMatcher notHaveTabWithTitle(@NotNull String expectedText) {
        return new BrowserShouldHaveTabWithTitleStringMatcher(expectedText, false);
    }

    public static BrowserShouldHaveTabWithTitleStringValueMatcher notHaveTabWithTitle(@NotNull StringValue expectedValue) {
        return new BrowserShouldHaveTabWithTitleStringValueMatcher(expectedValue, false);
    }

    // BrowserShouldHaveTabWithUrl

    public static BrowserShouldHaveTabWithUrlStringMatcher haveTabWithUrl(@NotNull String expectedText) {
        return new BrowserShouldHaveTabWithUrlStringMatcher(expectedText, true);
    }

    public static BrowserShouldHaveTabWithUrlStringValueMatcher haveTabWithUrl(@NotNull StringValue expectedValue) {
        return new BrowserShouldHaveTabWithUrlStringValueMatcher(expectedValue, true);
    }

    public static BrowserShouldHaveTabWithUrlStringMatcher notHaveTabWithUrl(@NotNull String expectedText) {
        return new BrowserShouldHaveTabWithUrlStringMatcher(expectedText, false);
    }

    public static BrowserShouldHaveTabWithUrlStringValueMatcher notHaveTabWithUrl(@NotNull StringValue expectedValue) {
        return new BrowserShouldHaveTabWithUrlStringValueMatcher(expectedValue, false);
    }

    // Have tab count

    public static TabCountNumberMatcher haveTabCount(int expectedCount) {
        return new TabCountNumberMatcher(expectedCount, true);
    }

    public static TabCountNumberValueMatcher haveTabCount(@NotNull NumberValue<Integer> expectedCountValue) {
        return new TabCountNumberValueMatcher(expectedCountValue, true);
    }

    public static TabCountNumberMatcher notHaveTabCount(int expectedCount) {
        return new TabCountNumberMatcher(expectedCount, false);
    }

    public static TabCountNumberValueMatcher notHaveTabCount(@NotNull NumberValue<Integer> expectedCountValue) {
        return new TabCountNumberValueMatcher(expectedCountValue, false);

    }

}
