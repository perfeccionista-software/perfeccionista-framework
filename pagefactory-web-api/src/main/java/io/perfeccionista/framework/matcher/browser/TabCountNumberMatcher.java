package io.perfeccionista.framework.matcher.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabCount;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_TAB_COUNT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_TAB_COUNT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_METHOD;

public class TabCountNumberMatcher implements TabsDispatcherMatcher {

    protected final int expectedNumber;
    protected final boolean positive;

    public TabCountNumberMatcher(int expectedCount, boolean positive) {
        this.expectedNumber = expectedCount;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull TabsDispatcher tabsDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(BROWSER_SHOULD_HAVE_TABS_COUNT_NUMBER_METHOD, this, expectedNumber)
                : assertInvocation(BROWSER_SHOULD_NOT_HAVE_TABS_COUNT_NUMBER_METHOD, this, expectedNumber);

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

    protected void shouldHaveTabCount(TabsDispatcher tabsDispatcher, int actualTabCount) {
        if (expectedNumber != actualTabCount) {
            throw WebBrowserTabCount.assertionError(WEB_BROWSER_TAB_COUNT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumber, actualTabCount));
        }
    }

    protected void shouldNotHaveTabCount(TabsDispatcher tabsDispatcher, int actualTabCount) {
        if (expectedNumber == actualTabCount) {
            throw WebBrowserTabCount.assertionError(WEB_BROWSER_TAB_COUNT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedNumber, actualTabCount));
        }
    }

}
