package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabUrl;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_URL_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_URL_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.ACTIVE_TAB_SHOULD_HAVE_URL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.ACTIVE_TAB_SHOULD_NOT_HAVE_URL_METHOD;

public class WebBrowserActiveTabShouldHaveUrlStringMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final String expectedText;
    protected final boolean positive;

    public WebBrowserActiveTabShouldHaveUrlStringMatcher(String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(ACTIVE_TAB_SHOULD_HAVE_URL_METHOD, this, expectedText)
                : assertInvocation(ACTIVE_TAB_SHOULD_NOT_HAVE_URL_METHOD, this, expectedText);

        runCheck(Environment.getCurrent(), invocationName,
                () -> {
                    String actualActiveTabUrlText = tabsDispatcher.getActiveTabUrl();
                    if (positive) {
                        shouldHaveUrl(tabsDispatcher, actualActiveTabUrlText);
                    } else {
                        shouldNotHaveUrl(tabsDispatcher, actualActiveTabUrlText);
                    }
                });
    }

    protected void shouldHaveUrl(WebBrowserTabsDispatcher tabsDispatcher, String actualUrl) {
        if (!expectedText.equals(actualUrl)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_ACTIVE_TAB_URL_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualUrl));
        }
    }

    protected void shouldNotHaveUrl(WebBrowserTabsDispatcher tabsDispatcher, String actualUrl) {
        if (expectedText.equals(actualUrl)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_ACTIVE_TAB_URL_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, actualUrl));
        }
    }

}
