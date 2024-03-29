package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.exceptions.WebBrowserTabCount;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_TAB_COUNT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_TAB_COUNT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_METHOD;

public class WebBrowserShouldHaveTabCountNumberMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final int expectedNumber;
    protected final boolean positive;

    public WebBrowserShouldHaveTabCountNumberMatcher(int expectedCount, boolean positive) {
        this.expectedNumber = expectedCount;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
        InvocationInfo invocationName = positive
                ? assertInvocation(BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_METHOD, String.valueOf(expectedNumber))
                : assertInvocation(BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_METHOD, String.valueOf(expectedNumber));

        repeatInvocation(invocationName,
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
        if (expectedNumber != actualTabCount) {
            throw WebBrowserTabCount.assertionError(WEB_BROWSER_TAB_COUNT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumber, actualTabCount));
        }
    }

    protected void shouldNotHaveTabCount(WebBrowserTabsDispatcher tabsDispatcher, int actualTabCount) {
        if (expectedNumber == actualTabCount) {
            throw WebBrowserTabCount.assertionError(WEB_BROWSER_TAB_COUNT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumber, actualTabCount));
        }
    }

}
