package io.perfeccionista.framework.matcher.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabTitle;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_CONTAINS_EXPECTED_VALUE;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.ACTIVE_TAB_SHOULD_HAVE_TITLE_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.ACTIVE_TAB_SHOULD_NOT_HAVE_TITLE_VALUE_METHOD;

public class ActiveTabShouldHaveTitleStringValueMatcher implements TabsDispatcherMatcher {

    protected final StringValue expectedStringValue;
    protected final boolean positive;

    public ActiveTabShouldHaveTitleStringValueMatcher(StringValue expectedStringValue, boolean positive) {
        this.expectedStringValue = expectedStringValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull TabsDispatcher tabsDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(ACTIVE_TAB_SHOULD_HAVE_TITLE_VALUE_METHOD, this, expectedStringValue)
                : assertInvocation(ACTIVE_TAB_SHOULD_NOT_HAVE_TITLE_VALUE_METHOD, this, expectedStringValue);

        runCheck(Environment.getCurrent(), invocationName,
                () -> {
                    String actualActiveTabTitleText = tabsDispatcher.getActiveTabTitle();
                    if (positive) {
                        shouldHaveTitle(tabsDispatcher, actualActiveTabTitleText);
                    } else {
                        shouldNotHaveTitle(tabsDispatcher, actualActiveTabTitleText);
                    }
                });
    }

    protected void shouldHaveTitle(TabsDispatcher tabsDispatcher, String actualTitle) {
        if (!expectedStringValue.check(actualTitle)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_DOES_NOT_CONTAIN_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

    protected void shouldNotHaveTitle(TabsDispatcher tabsDispatcher, String actualTitle) {
        if (expectedStringValue.check(actualTitle)) {
            throw WebBrowserTabTitle.assertionError(WEB_BROWSER_ACTIVE_TAB_TITLE_TEXT_CONTAINS_EXPECTED_VALUE.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedStringValue));
        }
    }

}
