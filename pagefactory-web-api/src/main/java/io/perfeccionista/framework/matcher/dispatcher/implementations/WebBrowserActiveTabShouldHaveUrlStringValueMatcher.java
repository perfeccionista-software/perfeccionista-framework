package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.exceptions.WebBrowserTabUrl;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_URL_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_URL_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.ACTIVE_TAB_SHOULD_HAVE_URL_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.ACTIVE_TAB_SHOULD_NOT_HAVE_URL_VALUE_METHOD;

public class WebBrowserActiveTabShouldHaveUrlStringValueMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final StringValue expectedTextValue;
    protected final boolean positive;

    public WebBrowserActiveTabShouldHaveUrlStringValueMatcher(StringValue expectedTextValue, boolean positive) {
        this.expectedTextValue = expectedTextValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
        InvocationInfo invocationName = positive
                ? assertInvocation(ACTIVE_TAB_SHOULD_HAVE_URL_VALUE_METHOD, expectedTextValue.getShortDescription())
                : assertInvocation(ACTIVE_TAB_SHOULD_NOT_HAVE_URL_VALUE_METHOD, expectedTextValue.getShortDescription());

        repeatInvocation(invocationName,
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
        if (!expectedTextValue.check(actualUrl)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_ACTIVE_TAB_URL_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue));
        }
    }

    protected void shouldNotHaveUrl(WebBrowserTabsDispatcher tabsDispatcher, String actualUrl) {
        if (expectedTextValue.check(actualUrl)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_ACTIVE_TAB_URL_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue));
        }
    }

}
