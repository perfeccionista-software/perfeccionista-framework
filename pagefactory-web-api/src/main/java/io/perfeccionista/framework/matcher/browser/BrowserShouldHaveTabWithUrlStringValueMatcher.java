package io.perfeccionista.framework.matcher.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.WebBrowserTabUrl;
import io.perfeccionista.framework.exceptions.attachments.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.browser.tabs.TabsDispatcher;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_URL;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_URL;
import static io.perfeccionista.framework.invocation.runner.InvocationName.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TAB_WITH_URL_VALUE_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TAB_WITH_URL_VALUE_METHOD;

public class BrowserShouldHaveTabWithUrlStringValueMatcher implements TabsDispatcherMatcher {

    protected final StringValue expectedTextValue;
    protected final boolean positive;

    public BrowserShouldHaveTabWithUrlStringValueMatcher(StringValue expectedTextValue, boolean positive) {
        this.expectedTextValue = expectedTextValue;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull TabsDispatcher tabsDispatcher) {
        InvocationName invocationName = positive
                ? assertInvocation(BROWSER_SHOULD_HAVE_TAB_WITH_URL_VALUE_METHOD, this, expectedTextValue)
                : assertInvocation(BROWSER_SHOULD_NOT_HAVE_TAB_WITH_URL_VALUE_METHOD, this, expectedTextValue);

        runCheck(Environment.getCurrent(), invocationName,
                () -> {
                    List<String> actualTabUrls = tabsDispatcher.getAllTabUrls();
                    if (positive) {
                        shouldHaveTabWithUrl(tabsDispatcher, actualTabUrls);
                    } else {
                        shouldNotHaveTabWithUrl(tabsDispatcher, actualTabUrls);
                    }
                });
    }

    protected void shouldHaveTabWithUrl(TabsDispatcher tabsDispatcher, List<String> actualTabUrls) {
        boolean match = actualTabUrls.stream()
                .anyMatch(expectedTextValue::check);
        if (!match) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_URL.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue, String.join("', '", actualTabUrls)));
        }
    }

    protected void shouldNotHaveTabWithUrl(TabsDispatcher tabsDispatcher, List<String> actualTabUrls) {
        boolean match = actualTabUrls.stream()
                .anyMatch(expectedTextValue::check);
        if (match) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_URL.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(StringAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedTextValue, String.join("', '", actualTabUrls)));
        }
    }

}
