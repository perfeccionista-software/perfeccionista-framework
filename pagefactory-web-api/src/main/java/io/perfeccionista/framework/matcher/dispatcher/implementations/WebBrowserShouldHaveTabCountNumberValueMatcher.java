package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabCount;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_TAB_COUNT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_TAB_COUNT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_VALUE_METHOD;

public class WebBrowserShouldHaveTabCountNumberValueMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final NumberValue<Integer> expectedNumberValue;
    protected final boolean positive;

    public WebBrowserShouldHaveTabCountNumberValueMatcher(NumberValue<Integer> expectedNumberValue, boolean positive) {
        this.expectedNumberValue = expectedNumberValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_VALUE_METHOD, this, expectedNumberValue)
                : assertInvocation(BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_VALUE_METHOD, this, expectedNumberValue);

        runCheck(Environment.getCurrent(), invocationName,
                () -> {
                    int actualTabCount = tabsDispatcher.getTabCount();
                    if (positive) {
                        shouldHaveTabCount(tabsDispatcher, actualTabCount);
                    } else {
                        shouldNotHaveTabCount(tabsDispatcher, actualTabCount);
                    }
                });
    }

    protected void shouldHaveTabCount(WebBrowserTabsDispatcher tabsDispatcher, int actualTabCount) {
        if (!expectedNumberValue.check(actualTabCount)) {
            throw WebBrowserTabCount.assertionError(WEB_BROWSER_TAB_COUNT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

    protected void shouldNotHaveTabCount(WebBrowserTabsDispatcher tabsDispatcher, int actualTabCount) {
        if (expectedNumberValue.check(actualTabCount)) {
            throw WebBrowserTabCount.assertionError(WEB_BROWSER_TAB_COUNT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumberValue));
        }
    }

}
