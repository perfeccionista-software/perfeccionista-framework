package io.perfeccionista.framework.matcher.dispatcher.implementations;

import io.perfeccionista.framework.exceptions.WebBrowserTabUrl;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.ValueAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.dispatcher.WebBrowserTabsDispatcherMatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.tabs.WebBrowserTabsDispatcher;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_URL;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_URL;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.assertInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_HAVE_TAB_WITH_URL_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SHOULD_NOT_HAVE_TAB_WITH_URL_METHOD;

public class WebBrowserShouldHaveTabWithUrlStringMatcher implements WebBrowserTabsDispatcherMatcher {

    protected final String expectedText;
    protected final boolean positive;

    public WebBrowserShouldHaveTabWithUrlStringMatcher(String expectedText, boolean positive) {
        this.expectedText = expectedText;
        this.positive = positive;
    }

    @Override
    public void check(@NotNull WebBrowserTabsDispatcher tabsDispatcher) {
        InvocationInfo invocationName = positive
                ? assertInvocation(BROWSER_SHOULD_HAVE_TAB_WITH_URL_METHOD, expectedText)
                : assertInvocation(BROWSER_SHOULD_NOT_HAVE_TAB_WITH_URL_METHOD, expectedText);

        runCheck(invocationName,
                () -> {
                    List<String> actualTabUrls = tabsDispatcher.getAllTabUrls();
                    if (positive) {
                        shouldHaveTabWithUrl(tabsDispatcher, actualTabUrls);
                    } else {
                        shouldNotHaveTabWithUrl(tabsDispatcher, actualTabUrls);
                    }
                });
    }

    protected void shouldHaveTabWithUrl(WebBrowserTabsDispatcher tabsDispatcher, List<String> actualTabUrls) {
        if (!actualTabUrls.contains(expectedText)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_DOES_NOT_CONTAIN_TAB_WITH_EXPECTED_URL.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, String.join("', '", actualTabUrls)));
        }
    }

    protected void shouldNotHaveTabWithUrl(WebBrowserTabsDispatcher tabsDispatcher, List<String> actualTabUrls) {
        if (actualTabUrls.contains(expectedText)) {
            throw WebBrowserTabUrl.assertionError(WEB_BROWSER_CONTAINS_TAB_WITH_EXPECTED_URL.getMessage())
                    .setProcessed(true)
                    .addLastAttachmentEntry(TextAttachmentEntry.of("Tabs", tabsDispatcher.getDescription()))
                    .addLastAttachmentEntry(ValueAttachmentEntry.of(expectedText, String.join("', '", actualTabUrls)));
        }
    }

}
