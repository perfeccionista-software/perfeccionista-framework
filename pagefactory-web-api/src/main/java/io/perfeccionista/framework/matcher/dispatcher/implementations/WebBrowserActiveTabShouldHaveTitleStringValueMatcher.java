package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.exceptions.WebBrowserTabTitle;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.ACTIVE_TAB_SHOULD_HAVE_TITLE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.ACTIVE_TAB_SHOULD_NOT_HAVE_TITLE_VALUE_METHOD;

public class WebBrowserActiveTabShouldHaveTitleStringValueMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final StringValue expectedStringValue;
    protected final boolean positive;

    public WebBrowserActiveTabShouldHaveTitleStringValueMatcher(StringValue expectedStringValue, boolean positive) {
        this.expectedStringValue = expectedStringValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
        InvocationInfo invocationName = positive
                ? assertInvocation(ACTIVE_TAB_SHOULD_HAVE_TITLE_VALUE_METHOD, this, expectedStringValue)
                : assertInvocation(ACTIVE_TAB_SHOULD_NOT_HAVE_TITLE_VALUE_METHOD, this, expectedStringValue);

        runCheck(invocationName,
                () -> {
                    String actualActiveTabTitleText = tabsDispatcher.getActiveTabTitle();
                    if (positive) {
                        shouldHaveTitle(tabsDispatcher, actualActiveTabTitleText);
                    } else {
                        shouldNotHaveTitle(tabsDispatcher, actualActiveTabTitleText);
                    }
                });
    }

    protected void shouldHaveTitle(WebBrowserTabsDispatcher tabsDispatcher, String actualTitle) {
        if (!expectedStringValue.check(actualTitle)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

    protected void shouldNotHaveTitle(WebBrowserTabsDispatcher tabsDispatcher, String actualTitle) {
        if (expectedStringValue.check(actualTitle)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

}
