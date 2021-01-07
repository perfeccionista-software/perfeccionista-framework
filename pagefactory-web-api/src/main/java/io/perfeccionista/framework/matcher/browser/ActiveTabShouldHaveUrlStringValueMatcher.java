package io.perfeccionista.framework.matcher.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabUrl;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_URL_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_URL_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.ACTIVE_TAB_SHOULD_HAVE_URL_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.ACTIVE_TAB_SHOULD_NOT_HAVE_URL_VALUE_METHOD;

public class ActiveTabShouldHaveUrlStringValueMatcher implements TabsDispatcherMatcher {

    protected final StringValue expectedTextValue;
    protected final boolean positive;

    public ActiveTabShouldHaveUrlStringValueMatcher(StringValue expectedTextValue, boolean positive) {
        this.expectedTextValue = expectedTextValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull TabsDispatcher tabsDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(ACTIVE_TAB_SHOULD_HAVE_URL_VALUE_METHOD, this, expectedTextValue)
                : assertInvocation(ACTIVE_TAB_SHOULD_NOT_HAVE_URL_VALUE_METHOD, this, expectedTextValue);

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

    protected void shouldHaveUrl(TabsDispatcher tabsDispatcher, String actualUrl) {
        if (!expectedTextValue.check(actualUrl)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_ACTIVE_TAB_URL_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue));
        }
    }

    protected void shouldNotHaveUrl(TabsDispatcher tabsDispatcher, String actualUrl) {
        if (expectedTextValue.check(actualUrl)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_ACTIVE_TAB_URL_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue));
        }
    }

}
